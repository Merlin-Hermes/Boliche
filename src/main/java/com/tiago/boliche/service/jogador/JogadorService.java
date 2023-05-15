package com.tiago.boliche.service.jogador;

import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.jogador.JogadorRequest;
import com.tiago.boliche.repository.jogador.JogadorRepository;

public class JogadorService implements IJogadorService {

    private final JogadorRepository repository;

    public JogadorService(JogadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jogador marcarPonto(JogadorRequest jogadorRequest) {
        Jogador jogador = re
    }
}
