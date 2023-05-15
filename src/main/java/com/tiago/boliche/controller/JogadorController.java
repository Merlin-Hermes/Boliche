package com.tiago.boliche.controller;

import com.tiago.boliche.model.jogador.IJogadorMapper;
import com.tiago.boliche.model.jogador.JogadorRequest;
import com.tiago.boliche.model.jogador.JogadorResponse;
import com.tiago.boliche.service.jogador.IJogadorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    private final IJogadorMapper mapper;

    private final IJogadorService service;


    public JogadorController(IJogadorMapper mapper, IJogadorService service) {
        this.mapper = mapper;
        this.service = service;
    }


    public JogadorResponse marcarPonto(@RequestBody JogadorRequest jogadorRequest) {
        return mapper.toResponse(service.marcarPonto(jogadorRequest));
    }
}
