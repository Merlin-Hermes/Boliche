package com.tiago.boliche.service.partida;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.entity.Partida;
import com.tiago.boliche.model.partida.Resultado;
import com.tiago.boliche.repository.partida.PartidaRepository;
import com.tiago.boliche.service.jogador.JogadorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartidaService implements IPartidaService {

    private final PartidaRepository partidaRepository;

    private final JogadorService jogadorService;

    public PartidaService(PartidaRepository partidaRepository, JogadorService jogadorService) {
        this.partidaRepository = partidaRepository;
        this.jogadorService = jogadorService;
    }

    @Override
    public Partida create(List<String> Jogadores) {
        Partida partida = new Partida();
        List<Jogador> jogadors = new ArrayList<>();
        for (String nome : Jogadores) {
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogador.setPartida(partida);
            jogador.setFrames(createFrames());
            jogador.setPontuacao(0);
            jogadors.add(jogador);
        }
        partida.setJogadores(jogadors);
        return partidaRepository.save(partida);
    }

    public Resultado getVencedor(Long id) {
        Partida partida = partidaRepository.findById(id).orElseThrow();
        Resultado resultado = new Resultado();
        partida.setJogadores(calcularPontosAllJogadores(partida.getJogadores()));
        resultado.setVencedor(compararPontuacao(partida));
        resultado.setJogadores(partida.getJogadores());
        return resultado;
    }

    private List<Jogador> calcularPontosAllJogadores(List<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
          jogadorService.calcularPontos(jogador);
        }
        return jogadores;
    }

    private String compararPontuacao(Partida partida) {
      return partida.getJogadores().stream().max((j1, j2) -> j1.getPontuacao() - j2.getPontuacao()).orElseThrow().getNome();
    }

    private Map<Integer, Frame> createFrames() {
        HashMap<Integer, Frame> frames = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            frames.put(i, new Frame(null, 0, 0, 0));
        }
        return frames;
    }
}
