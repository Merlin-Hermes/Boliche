package com.tiago.boliche.model.jogador;

import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.frame.IFrameMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IFrameMapper.class})
public interface IJogadorMapper {

    JogadorResponse toResponse(Jogador jogador);
}
