package com.sistema.biblioteca.repository;

import com.sistema.biblioteca.model.Livro;
import com.sistema.biblioteca.model.Usuario;
import com.sistema.biblioteca.model.UsuarioLivro;
import com.sistema.biblioteca.model.UsuarioLivro.UsuarioLivroId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioLivroRepository extends JpaRepository<UsuarioLivro, UsuarioLivroId> {

    Optional<UsuarioLivro> findByUsuarioAndLivroAndDataDevolucaoIsNull(Usuario usuario, Livro livro);

    Optional<List<UsuarioLivro>> findByUsuarioAndDataDevolucaoIsNull(Usuario usuario);
}
