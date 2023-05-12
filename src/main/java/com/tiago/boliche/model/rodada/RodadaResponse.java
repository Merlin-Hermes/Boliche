package com.tiago.boliche.model.rodada;

import com.tiago.boliche.model.frame.FrameResponse;
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
public class RodadaResponse {

    private Long id;

    private Integer pontos;

    private int numeroRodada;

    private FrameResponse frame;

}
