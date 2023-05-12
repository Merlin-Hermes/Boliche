package com.tiago.boliche.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private String nome;

    private int rodada;

    private boolean strike;

    private boolean spare;

    private Integer pontosPrimeiraJogada;

    private Integer pontosSegundaJogada;

    private Integer pontosTerceiraJogada;

    private Integer faltas;
}
