package com.sistema.biblioteca.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioLivroDTO(
        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,
        @NotNull(message = "Código do livro é obrigatório")
        Integer idLivro,
        @NotNull(message = "A data de empréstimo é obrigatória")
        @PastOrPresent(message = "Não pode ser uma data futura")
        LocalDate dataEmprestimo
) {}
