package com.tiago.boliche.model.usuario;

import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.rodada.RodadaResponse;
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
public class UsuarioResponse {

    private Long id;

    private String nome;

    private List<RodadaResponse> rodadas;
}
