package com.tiago.boliche.service.usuario;

import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.usuario.UsuarioRequest;
import com.tiago.boliche.model.usuario.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> getAll();

    Usuario create(Usuario usuario);

    Usuario addPoint(UsuarioRequest usuarioRequest);
}
