package com.sistema.biblioteca.service;

import com.sistema.biblioteca.controller.dto.LivroDTO;
import com.sistema.biblioteca.controller.dto.UsuarioDTO;
import com.sistema.biblioteca.controller.mappers.LivroMapper;
import com.sistema.biblioteca.controller.mappers.UsuarioMapper;
import com.sistema.biblioteca.exceptions.*;
import com.sistema.biblioteca.model.Livro;
import com.sistema.biblioteca.model.Usuario;
import com.sistema.biblioteca.model.UsuarioLivro;
import com.sistema.biblioteca.repository.LivroRepository;
import com.sistema.biblioteca.repository.UsuarioLivroRepository;
import com.sistema.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private static long maximoLivros;
    private static int maximoDias;

    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    private final UsuarioLivroRepository usuarioLivroRepository;
    private final LivroService livroService;
    private final LivroMapper livroMapper;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioService usuarioService;

    public void cadastrarUsuario(Usuario usuario){
        String cpf = usuario.getCpf();
        if (usuarioCadastrado(cpf)){
            throw new CpfDuplicadoException();
        }
        if(usuario.getQtdLivrosEmprestados() == null){
            usuario.setQtdLivrosEmprestados(0);
        }
        usuarioRepository.save(usuario);
    }

    public void cadastrarLivro(Livro livro){
        Integer idLivro = livro.getId();
        if (livroCadastrado(idLivro)){
            throw new CodigoLivroDuplicadoException(idLivro);
        }
        livroRepository.save(livro);
    }

    public void deletarUsuario(Usuario usuario){
        if(usuarioService.usuarioTemLivroEmprestado(usuario)){
            throw new UsuarioTemLivroEmprestadoExeption();
        }
        if(usuarioService.usuarioTemLivroPendente(usuario, maximoLivros)){
            throw new UsuarioTemLivroPendenteException();
        }
        usuarioRepository.delete(usuario);
    }

    public void deletarLivro(Livro livro){
        if(algumaCopiaEmprestada(livro)){
            throw new CopiaEmprestadaException();
        }
    }

    public void atualizarUsuario(Usuario usuario){
        String cpf = usuario.getCpf();
        if (usuarioCadastrado(cpf)){
            usuarioRepository.save(usuario);
        }
        else{
            throw new UsuarioNaoCadastradoException();
        }
    }

    public void atualizarLivro(Livro livro){
        int codLivro = livro.getId();
        if(livroCadastrado(codLivro)){
            livroRepository.save(livro);
        }
        else{
            throw new LivroNaoCadastradoException();
        }
    }

    public Optional<Livro> obterLivroPorId(Integer id){
        if (!livroCadastrado(id)){
            throw new LivroNaoCadastradoException();
        }
        return livroRepository.findById(id);
    }

    public Optional<Usuario> obterUsuarioPorId(String cpf){
        if(!usuarioCadastrado(cpf)){
            throw new UsuarioNaoCadastradoException();
        }
        return usuarioRepository.findById(cpf);
    }

    @Transactional
    public void emprestaLivro(Usuario usuario, Livro livro, Integer maximoLivros){
        if (!usuarioCadastrado(usuario.getCpf())) {
            throw new UsuarioNaoCadastradoException();
        }
        if(!livroCadastrado(livro.getId())) {
            throw new LivroNaoCadastradoException();
        }

        LocalDate hoje = LocalDate.now();
        livroService.empresta(livro);
        usuarioService.empresta(usuario, livro, maximoLivros, maximoDias);
        UsuarioLivro emprestimo = new UsuarioLivro(usuario, livro, hoje);
        usuarioLivroRepository.save(emprestimo);
        livroRepository.save(livro);
    }

    @Transactional
    public void devolveLivro(Usuario usuario, Livro livro){
        if (!usuarioCadastrado(usuario.getCpf())) {
            throw new UsuarioNaoCadastradoException();
        }
        if(!livroCadastrado(livro.getId())){
            throw new LivroNaoCadastradoException();
        }
        UsuarioLivro emprestimo = usuarioLivroRepository
                .findByUsuarioAndLivroAndDataDevolucaoIsNull(usuario, livro)
                .orElseThrow(() -> new NenhumaCopiaEmprestadaException());
        LocalDate hoje = LocalDate.now();
        livroService.devolve(livro);
        emprestimo.setDataDevolucao(hoje);
        livroRepository.save(livro);
        usuarioLivroRepository.save(emprestimo);
        usuarioService.devolve(usuario,livro);
        verificaMulta(emprestimo);
    }

    public List<LivroDTO> imprimeLivros(){
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(livro -> livroMapper.toDTO(livro)).collect(Collectors.toList());
    }

    public List<UsuarioDTO> imprimeUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> usuarioMapper.toDTO(usuario)).collect(Collectors.toList());
    }

    public void verificaMulta(UsuarioLivro registro){
        LocalDate dataEmprestimo = registro.getDataEmprestimo();
        LocalDate dataDevolucao = registro.getDataDevolucao();
        long diasEmprestados = calculaDiasUsando(dataEmprestimo, dataDevolucao);
        if(diasEmprestados > maximoDias){
            System.out.println("Usuário: "
                    + registro.getUsuario().getNome()
                    + " está com "
                    + (diasEmprestados - maximoDias)
                    + " dias atrasados"
            );
        }
    }

    public static long calculaDiasUsando(LocalDate dataEmprestimo, LocalDate dataDevolucao){
        return ChronoUnit.DAYS.between(dataDevolucao,dataEmprestimo);
    }

    private boolean usuarioCadastrado(String cpf){
        return usuarioRepository.existsById(cpf);
    }

    private boolean livroCadastrado (Integer idLivro){
        return livroRepository.existsById(idLivro);
    }

    private boolean algumaCopiaEmprestada(Livro livro){
        return livro.getQtdEmprestado() == 0;
    }

}
