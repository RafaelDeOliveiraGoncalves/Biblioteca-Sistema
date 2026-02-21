package com.sistema.biblioteca.controller.mappers;

import com.sistema.biblioteca.controller.dto.UsuarioLivroDTO;
import com.sistema.biblioteca.model.UsuarioLivro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioLivroMapper {

    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "livro.id", source = "idLivro")
    UsuarioLivro toEntity (UsuarioLivroDTO dto);

    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "idLivro", source = "livro.id")
    UsuarioLivroDTO toDTO (UsuarioLivro usuarioLivro);
}
