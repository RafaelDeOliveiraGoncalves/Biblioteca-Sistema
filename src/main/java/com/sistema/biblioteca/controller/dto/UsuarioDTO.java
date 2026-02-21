package com.sistema.biblioteca.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.biblioteca.validator.annotation.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTO (
    @NotBlank(message = "campo obrigatório")
    @Size(min = 2, max = 150, message = "nome com tamanho fora do padrao do sistema")
    String nome,
    @NotNull(message = "campo obrigatorio")
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento,
    @CPF(message = "CPF Inválido")
    String cpf,
    @Endereco(message = "endereço inválido")
    String endereco,

    Integer qtdLivrosEmprestados
){
    public UsuarioDTO(String nome, LocalDate dataNascimento, String cpf, String endereco) {
        this(nome, dataNascimento, cpf, endereco, 0);
    }
}
