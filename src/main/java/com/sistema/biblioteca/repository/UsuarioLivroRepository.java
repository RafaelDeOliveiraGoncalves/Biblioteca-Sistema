package com.sistema.biblioteca.repository;

import com.sistema.biblioteca.model.UsuarioLivro;
import com.sistema.biblioteca.model.UsuarioLivro.UsuarioLivroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioLivroRepository extends JpaRepository<UsuarioLivro, UsuarioLivroId> {
}
