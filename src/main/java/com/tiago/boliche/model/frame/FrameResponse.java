package com.tiago.boliche.model.frame;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FrameResponse {

    private Long id;

    private int primeiraBola;

    private int segundaBola;

    private int terceiraBola;
}
