package com.tiago.boliche.integration;

import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Rodada;
import com.tiago.boliche.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@Rollback
public class UsuarioControllerTest {

    @Test
    public void test_Listar_Pontos_por_Rodada_Return_OK() {

        createUsuario();



    }


    private void createUsuario() {
        var usuario = new Usuario();
        var rodadas = createRodadas();
        usuario.setNome("Tiago");
        usuario.setRodadas(rodadas);
    }

    private List<Rodada> createRodadas() {
        var rodadas = new ArrayList<Rodada>();
        do {
            Rodada rodada = new Rodada();
            rodada.setNumeroRodada(1);
            rodada.setPontos(30);
            rodada.setFrame(createFrame());
            rodadas.add(rodada);
        }while (rodadas.size() < 10);

        return rodadas;
    }

    private Frame createFrame() {
        Frame frame = new Frame();
        frame.setPontosPrimeiraJogada(10);
        frame.setPontosSegundaJogada(10);
        frame.setPontosTerceiraJogada(10);
        frame.setStrike(true);
        frame.setFaltas(0);
        return frame;
    }
}
