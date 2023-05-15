package com.tiago.boliche.model.jogador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JogadorRequest {

    private Long id;

    private String nome;

    private Map<Integer, Integer> frames;
}
