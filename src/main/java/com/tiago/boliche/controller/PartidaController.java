package com.tiago.boliche.controller;


import com.tiago.boliche.model.partida.IPartidaMapper;
import com.tiago.boliche.model.partida.PartidaResponse;
import com.tiago.boliche.model.partida.Resultado;
import com.tiago.boliche.service.partida.IPartidaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    private final IPartidaService service;

    private final IPartidaMapper mapper;

    public PartidaController(IPartidaService service, IPartidaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PartidaResponse create(@RequestBody List<String> jogadores) {
        return mapper.toResponse(service.create(jogadores));
    }

    @GetMapping("/{id}")
    public Resultado getVencendor(@PathVariable Long id) {
        return service.getVencedor(id);
    }
}
