package com.sistema.biblioteca.service;

import com.sistema.biblioteca.controller.mappers.UsuarioLivroMapper;
import com.sistema.biblioteca.exceptions.LimiteMaximoLivrosException;
import com.sistema.biblioteca.exceptions.LivroJaEmprestadoException;
import com.sistema.biblioteca.exceptions.UsuarioTemLivroPendenteException;
import com.sistema.biblioteca.model.Livro;
import com.sistema.biblioteca.model.Usuario;
import com.sistema.biblioteca.model.UsuarioLivro;
import com.sistema.biblioteca.repository.UsuarioLivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioLivroRepository repository;

    public void empresta(Usuario usuario, Livro livro, Integer maximoLivros, long maximoDias){
        if(usuarioAtingiuLimiteLivrosEmprestados(usuario, maximoLivros)){
            throw new LimiteMaximoLivrosException();
        }
        if(usuarioJaTemLivroEmprestado(usuario, livro)){
            throw new LivroJaEmprestadoException();
        }
        if(usuarioTemLivroPendente(usuario, maximoLivros)){
            throw new UsuarioTemLivroPendenteException();
        }
        int qtdLivrosEmprestados = usuario.getQtdLivrosEmprestados();
        usuario.setQtdLivrosEmprestados(qtdLivrosEmprestados + 1);
    }

    public void devolve(Usuario usuario, Livro livro){
        int qtdLivrosEmprestados = usuario.getQtdLivrosEmprestados();
        usuario.setQtdLivrosEmprestados(qtdLivrosEmprestados - 1);
    }

    private boolean usuarioAtingiuLimiteLivrosEmprestados(Usuario usuario, Integer maximoLivros){
        return usuario.getQtdLivrosEmprestados() >= maximoLivros;
    }

    private boolean usuarioJaTemLivroEmprestado(Usuario usuario, Livro livro){
        var listaLivros = livrosDoUsuario(usuario);
        return listaLivros.stream().anyMatch(book -> book.equals(livro));
    }

    private boolean ultrapassouLimiteDeDias(long diasUsados, long diasLimite){
        return diasUsados > diasLimite;
    }

    public boolean usuarioTemLivroPendente(Usuario usuario, long maximoDias){
        var livros = livrosDoUsuario(usuario);
        for (Livro livro : livros){
            Optional<UsuarioLivro> resultado = repository.findByUsuarioAndLivroAndDataDevolucaoIsNull(usuario,livro);
            var emprestimo = resultado.get();
            LocalDate hoje = LocalDate.now();
            LocalDate dataEmprestimo = emprestimo.getDataEmprestimo();
            long diasEmprestados = EmprestimoService.calculaDiasUsando(dataEmprestimo, hoje);
            if(ultrapassouLimiteDeDias(diasEmprestados, maximoDias)){
                return true;
            }
        }
        return false;
    }

    public boolean usuarioTemLivroEmprestado(Usuario usuario){
        return usuario.getQtdLivrosEmprestados() > 0;
    }

    private List<Livro> livrosDoUsuario(Usuario usuario){
        Optional<List<UsuarioLivro>> resultado = repository.findByUsuarioAndDataDevolucaoIsNull(usuario);
        List<UsuarioLivro> lista = resultado.get();
        List<Livro> listaLivros = lista.stream().map(UsuarioLivro::getLivro).collect(Collectors.toList());
        return listaLivros;
    }
}
