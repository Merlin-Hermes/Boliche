package com.tiago.boliche.service.rodada;

import com.tiago.boliche.entity.Rodada;
import org.springframework.stereotype.Service;

@Service
public class RodadaService {
    public Integer getPontos(Rodada rodada) {

        int pontos = 0;

        if (rodada.getFrame().getPontosPrimeiraJogada() == 10) {
            pontos = 10 + rodada.getFrame().getPontosSegundaJogada() + rodada.getFrame().getPontosTerceiraJogada();
        } else if (rodada.getFrame().getPontosPrimeiraJogada() + rodada.getFrame().getPontosSegundaJogada() == 10) {
            pontos = 10 + rodada.getFrame().getPontosTerceiraJogada();
        } else {
            pontos = rodada.getFrame().getPontosPrimeiraJogada() + rodada.getFrame().getPontosSegundaJogada();
        }

        return pontos;
    }

}
