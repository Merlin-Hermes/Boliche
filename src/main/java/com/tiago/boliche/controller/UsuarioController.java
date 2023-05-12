package com.tiago.boliche.controller;

import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.usuario.IUsuarioMapper;
import com.tiago.boliche.model.usuario.UsuarioRequest;
import com.tiago.boliche.model.usuario.UsuarioResponse;
import com.tiago.boliche.service.usuario.IUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioService service;

    private final IUsuarioMapper mapper;

    public UsuarioController(IUsuarioService service, IUsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;

    }

    @PostMapping
    public UsuarioResponse create(@RequestBody Usuario usuario) {
        return mapper.toResponse(service.create(usuario));
    }

    @PutMapping
    public UsuarioResponse addPoint(@RequestBody UsuarioRequest usuarioRequest) {
        return mapper.toResponse(service.addPoint(usuarioRequest));
    }


    @GetMapping("/all")
    public List<UsuarioResponse> getAll() {
        return mapper.toList(service.getAll());
    }
}
