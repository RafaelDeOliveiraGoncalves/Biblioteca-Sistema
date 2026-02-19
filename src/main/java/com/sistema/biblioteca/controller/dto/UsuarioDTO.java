package com.sistema.biblioteca.controller.dto;

import com.sistema.biblioteca.validator.anotation.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioDTO (
    UUID id,
    @NotBlank(message = "campo obrigatório")
    @Size(min = 2, max = 150, message = "nome com tamanho fora do padrao do sistema")
    String nome,
    @NotNull(message = "campo obrigatorio")
    @Past
    LocalDate dataNascimento,
    @CPF(message = "CPF Inválido")
    String cpf,
    @Endereco(mensagem = "endereço inválido")
    String endereco
){}
