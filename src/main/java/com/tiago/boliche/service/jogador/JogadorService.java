package com.tiago.boliche.service.jogador;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.jogador.JogadorRequest;
import com.tiago.boliche.repository.frame.FrameRepository;
import com.tiago.boliche.repository.jogador.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JogadorService implements IJogadorService {

    private final JogadorRepository repository;

    private final FrameRepository frameRepository;

    public JogadorService(JogadorRepository repository, FrameRepository frameRepository) {
        this.repository = repository;
        this.frameRepository = frameRepository;
    }

    @Override
    public Jogador marcarPonto(JogadorRequest jogadorRequest) {
        Jogador jogador = repository.findById(jogadorRequest.getId()).orElseThrow();
        var frames = jogadorRequest.getFrames();

        int pontuacao = 0;

        for (int i = 1; i <= 10; i++) {
            if (isStrike(frames, i)) {
                pontuacao += BonusStike(frames, i);
            } else if (isSpare(frames, i)) {
                pontuacao += BonusSpare(frames, i);
            } else {
                pontuacao += BonusNormal(frames, i);
            }
            frameRepository.save(frames.get(i));
        }
        jogador.setPontuacao(pontuacao);
        jogador.setFrames(frames);

        return repository.save(jogador);
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

    public List<Jogador> calcularPontos(List<Jogador> jogadores) {
        for (int i = 0; i < jogadores.size(); i++) {
            int pontuacao = 0;
            var frames = jogadores.get(i).getFrames();
            for (int j = 1; j <= 10; j++) {
                if (isStrike(frames, j)) {
                    pontuacao += BonusStike(frames, j);
                } else if (isSpare(frames, j)) {
                    pontuacao += BonusSpare(frames, j);
                } else {
                    pontuacao += BonusNormal(frames, j);
                }
            }
            jogadores.get(i).setPontuacao(pontuacao);
        }
        return jogadores;
    }
}
