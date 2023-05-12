package com.tiago.boliche.service;

import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.usuario.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> getAll();

    Usuario create(Usuario usuario);

}
