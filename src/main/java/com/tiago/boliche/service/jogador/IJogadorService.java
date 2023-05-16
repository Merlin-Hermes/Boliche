package com.tiago.boliche.service.jogador;

import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.jogador.JogadorRequest;

public interface IJogadorService {
    Jogador marcarPonto(JogadorRequest jogadorRequest);

    Jogador getPontuacao(Long id);
}
