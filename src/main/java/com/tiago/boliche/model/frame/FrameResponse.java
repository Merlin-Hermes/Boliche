package com.tiago.boliche.model.frame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FrameResponse {

    private Long id;

    private Integer pontosPrimeiraJogada;

    private Integer pontosSegundaJogada;

    private Integer pontosTerceiraJogada;

    private Integer faltas;

    private boolean strike;


}
