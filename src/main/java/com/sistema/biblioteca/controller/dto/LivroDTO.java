package com.sistema.biblioteca.controller.dto;

import com.sistema.biblioteca.model.CategoriaLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record LivroDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório")
        @Size(min = 1, max = 100, message = "campo fora do tamanho padrão do sistema.")
        String titulo,
        @NotBlank(message = "campo obrigatório")
        CategoriaLivro categoria,
        @NotNull(message = "campo obrigatório")
        Integer qtdDisponivel,
        @NotNull(message = "campo obrigatório")
        Integer qtdEmprestimo
) {}
