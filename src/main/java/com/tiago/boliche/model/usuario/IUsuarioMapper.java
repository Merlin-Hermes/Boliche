package com.tiago.boliche.model.usuario;

import com.tiago.boliche.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    UsuarioResponse toResponse(Usuario usuario);

    List<UsuarioResponse> toList(List<Usuario> usuarios);
}
