package com.tiago.boliche.service.partida;

import com.tiago.boliche.entity.Partida;
import com.tiago.boliche.model.partida.Resultado;

import java.util.List;

public interface IPartidaService {
    Partida create(List<String> Jogadores);

    Resultado getVencedor(Long id);
}
