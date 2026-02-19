package com.sistema.biblioteca.controller.mappers;

import com.sistema.biblioteca.controller.dto.UsuarioDTO;
import com.sistema.biblioteca.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario usuario);
}
