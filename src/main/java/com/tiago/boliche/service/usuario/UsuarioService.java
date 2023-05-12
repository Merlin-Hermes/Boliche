package com.tiago.boliche.service.usuario;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.usuario.UsuarioRequest;
import com.tiago.boliche.repository.usuario.UsuarioRepository;
import com.tiago.boliche.service.rodada.RodadaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RodadaService rodadaService;

    public UsuarioService(UsuarioRepository usuarioRepository, RodadaService rodadaService) {
        this.usuarioRepository = usuarioRepository;
        this.rodadaService = rodadaService;
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario addPoint(UsuarioRequest usuarioRequest) {
        var usuario = usuarioRepository.findByNome(usuarioRequest.getNome());
        var frame = createframe(usuarioRequest);

        usuario.getRodadas().get(usuarioRequest.getRodada()).setFrame(frame);
        usuario.getRodadas().get(usuarioRequest.getRodada()).setPontos(rodadaService.getPontos(usuario.getRodadas().get(usuarioRequest.getRodada())));

        return usuarioRepository.save(usuario);
    }

    private Frame createframe(UsuarioRequest usuarioRequest) {
        var frame = new Frame();

        frame.setPontosPrimeiraJogada(usuarioRequest.getPontosPrimeiraJogada());
        frame.setPontosSegundaJogada(usuarioRequest.getPontosSegundaJogada());

        if (usuarioRequest.isStrike() && usuarioRequest.getFaltas() < 1) {
            frame.setPontosTerceiraJogada(usuarioRequest.getPontosTerceiraJogada());
        }

        if (usuarioRequest.isSpare()) {
            frame.setPontosTerceiraJogada(usuarioRequest.getPontosSegundaJogada());
        }

        frame.setFaltas(usuarioRequest.getFaltas());

        return frame;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
