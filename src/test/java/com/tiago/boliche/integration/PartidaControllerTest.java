package com.tiago.boliche.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.entity.Partida;
import com.tiago.boliche.model.partida.PartidaResponse;
import com.tiago.boliche.model.partida.Resultado;
import com.tiago.boliche.repository.jogador.JogadorRepository;
import com.tiago.boliche.repository.partida.PartidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Rollback
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartidaControllerTest {

    private final String PARTIDA_API = "/partida";

    @Autowired
    JogadorRepository jogadorRepository;

    @SpyBean
    PartidaRepository partidaRepository;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        Mockito.reset(partidaRepository);
    }

    @Test
    public void test_Criar_Partida() throws Exception {

        List<String> jogadores = new ArrayList<>();
        jogadores.add("Tiago");
        jogadores.add("João");
        jogadores.add("Maria");

        MockHttpServletRequestBuilder request = post(PARTIDA_API).content(objectMapper.writeValueAsString(jogadores)).contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        final PartidaResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });

        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void test_Declarara_Vencedor() throws Exception {
        List<Jogador> jogadors = createJogador();
        Partida partida = new Partida();
        partida.setJogadores(jogadors);
        partidaRepository.save(partida);

        MockHttpServletRequestBuilder request = get(PARTIDA_API + "/" + partida.getId()).contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        final Resultado response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });

        Assertions.assertEquals("Jogador 0", response.getVencedor());
    }

    private List<Jogador> createJogador() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador());
        jogadores.add(new Jogador());

        for (int i = 0; i < jogadores.size(); i++) {
            jogadores.get(i).setNome("Jogador " + i);
            jogadores.get(i).setFrames(createFrames());
        }
        jogadores.set(0, marcaPonto1(jogadores.get(0)));
        jogadores.set(1, marcaPonto2(jogadores.get(1)));

        return jogadores;
    }

    private Jogador marcaPonto1(Jogador jogador) {
        for (int i = 1; i <= 10; i++) {
            jogador.getFrames().get(i).setPrimeiraBola(10);
            jogador.getFrames().get(i).setSegundaBola(10);
            jogador.getFrames().get(i).setTerceiraBola(10);
        }

        return jogador;
    }

    private Jogador marcaPonto2(Jogador jogador) {
        for (int i = 1; i <= 10; i++) {
            jogador.getFrames().get(i).setPrimeiraBola(5);
            jogador.getFrames().get(i).setSegundaBola(5);
            jogador.getFrames().get(i).setTerceiraBola(9);
        }
        return jogador;
    }

    private Map<Integer, Frame> createFrames() {
        var frames = new HashMap<Integer, Frame>();

        for (int i = 1; i <= 10; i++) {
            frames.put(i, new Frame());
        }
        return frames;
    }

}
