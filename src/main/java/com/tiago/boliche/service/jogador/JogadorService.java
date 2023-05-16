package com.tiago.boliche.service.jogador;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.jogador.JogadorRequest;
import com.tiago.boliche.repository.jogador.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JogadorService implements IJogadorService {

    private final JogadorRepository repository;

    public JogadorService(JogadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jogador marcarPonto(JogadorRequest jogadorRequest) {
        Jogador jogador = repository.findById(jogadorRequest.getId()).orElseThrow();
        jogador.setFrames(jogadorRequest.getFrames());
        calcularPontos(jogador);

        return repository.save(jogador);
    }

    @Override
    public Jogador getPontuacao(Long id) {
        return repository.findById(id).orElseThrow();
    }

    private boolean isStrike(Map<Integer, Frame> frames, int i) {
        return frames.get(i).getPrimeiraBola() == 10;
    }

    private boolean isSpare(Map<Integer, Frame> frames, int i) {
        return frames.get(i).getPrimeiraBola() + frames.get(i).getSegundaBola() == 10;
    }


    private int BonusStike(Map<Integer, Frame> frames, int i) {
        return 10 + frames.get(i).getSegundaBola() + frames.get(i).getTerceiraBola();
    }

    private int BonusSpare(Map<Integer, Frame> frames, int i) {
        return 10 + frames.get(i).getTerceiraBola();
    }

    private int BonusNormal(Map<Integer, Frame> frames, int i) {
        return frames.get(i).getPrimeiraBola() + frames.get(i).getSegundaBola();
    }

    public Jogador calcularPontos(Jogador jogador) {
        int pontuacao = 0;
        var frames = jogador.getFrames();
        for (int j = 1; j <= 10; j++) {
            if (isStrike(frames, j)) {
                pontuacao += BonusStike(frames, j);
            } else if (isSpare(frames, j)) {
                pontuacao += BonusSpare(frames, j);
            } else {
                pontuacao += BonusNormal(frames, j);
            }
        }
        jogador.setPontuacao(pontuacao);

        return jogador;
    }
}
