package com.tiago.boliche.service.partida;

import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.entity.Partida;
import com.tiago.boliche.repository.partida.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartidaService implements IPartidaService {

    private final PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
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
            jogadors.add(jogador);
        }
        partida.setJogadores(jogadors);
        return partidaRepository.save(partida);
    }

    private Map<Integer, Integer> createFrames() {
        HashMap<Integer, Integer> frames = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            frames.put(i, 0);
        }
        return frames;
    }
}
