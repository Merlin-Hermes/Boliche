package com.tiago.boliche.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FRAME")
public class Frame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PONTOS_PRIMEIRA_JOGADA")
    private Integer pontosPrimeiraJogada;

    @Column(name = "PONTOS_SEGUNDA_JOGADA")
    private Integer pontosSegundaJogada;

    @Column(name = "PONTOS_TERCEIRA_JOGADA")
    private Integer pontosTerceiraJogada;

    @Column(name = "PONTOS_QUARTA_JOGADA")
    private Integer faltas;

    @Column(name = "STRIKE")
    private boolean strike;
}
