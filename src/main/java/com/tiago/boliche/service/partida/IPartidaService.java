package com.tiago.boliche.service.partida;

import com.tiago.boliche.entity.Partida;

import java.util.List;

public interface IPartidaService {
    Partida create(List<String> Jogadores);
}
