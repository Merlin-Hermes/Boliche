package com.tiago.boliche.model.partida;

import com.tiago.boliche.entity.Partida;
import com.tiago.boliche.model.jogador.IJogadorMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IJogadorMapper.class})
public interface IPartidaMapper {

    PartidaResponse toResponse(Partida partida);
}
