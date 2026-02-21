package com.sistema.biblioteca.repository;

import com.sistema.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
