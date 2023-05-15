package com.tiago.boliche.model.jogador;

import com.tiago.boliche.entity.Jogador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IJogadorMapper {

    JogadorResponse toResponse(Jogador jogador);
}
