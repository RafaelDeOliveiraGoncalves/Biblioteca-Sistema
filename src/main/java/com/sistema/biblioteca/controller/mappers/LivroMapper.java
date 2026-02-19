package com.sistema.biblioteca.controller.mappers;

import com.sistema.biblioteca.controller.dto.LivroDTO;
import com.sistema.biblioteca.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toEntity(LivroDTO dto);

    LivroDTO toDTO(Livro livro);
}
