package com.tiago.boliche.model.jogador;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.model.frame.FrameResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class JogadorResponse {

    private Long id;

    private String nome;

    private Map<Integer, FrameResponse> frames;

    private int pontuacao;

}
