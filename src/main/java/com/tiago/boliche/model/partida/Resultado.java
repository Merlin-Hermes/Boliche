package com.tiago.boliche.model.partida;

import com.tiago.boliche.entity.Jogador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resultado {

    private String vencedor;

    private List<Jogador> jogadores;
}
