package com.tiago.boliche.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Jogador;
import com.tiago.boliche.model.jogador.JogadorRequest;
import com.tiago.boliche.model.jogador.JogadorResponse;
import com.tiago.boliche.repository.jogador.JogadorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Rollback
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JogadorControllerTest {

    private final String API = "/jogador";

    @SpyBean
    JogadorRepository jogadorRepository;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        Mockito.reset(jogadorRepository);
    }

    @Test
    public void Test_Marca_Ponto() throws Exception {

        Jogador jogador = Jogador.builder()
                .nome("Tiago")
                .frames(CreateFrames())
                .build();

        jogadorRepository.save(jogador);

        Map<Integer, Frame> frames = CreateFrames();
        frames.put(1, new Frame(null, 4, 0, 0));

        JogadorRequest jogadorRequest = new JogadorRequest();
        jogadorRequest.setId(jogador.getId());
        jogadorRequest.setFrames(frames);

        MockHttpServletRequestBuilder request = put(API).content(objectMapper.writeValueAsString(jogadorRequest)).contentType("application/json");

        ResultActions result = mvc.perform(request);

        final JogadorResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<JogadorResponse>() {
        });

        Assertions.assertEquals(4, response.getFrames().get(1).getPrimeiraBola());
    }

    @Test
    public void Test_Marca_Strike() throws Exception {
        Jogador jogador = Jogador.builder()
                .nome("Tiago")
                .frames(CreateFrames())
                .build();

        jogadorRepository.save(jogador);

        Map<Integer, Frame> frames = CreateFrames();
        frames.put(1, new Frame(null, 10, 0, 0));

        JogadorRequest jogadorRequest = new JogadorRequest();
        jogadorRequest.setId(jogador.getId());
        jogadorRequest.setFrames(frames);

        MockHttpServletRequestBuilder request = put(API).content(objectMapper.writeValueAsString(jogadorRequest)).contentType("application/json");

        ResultActions result = mvc.perform(request);

        final JogadorResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<JogadorResponse>() {
        });

        Assertions.assertEquals(10, response.getFrames().get(1).getPrimeiraBola());
        Assertions.assertEquals(10, response.getPontuacao());
    }

    @Test
    public void Test_Marca_Spare() throws Exception {
        Jogador jogador = Jogador.builder()
                .nome("Tiago")
                .frames(CreateFrames())
                .build();

        jogadorRepository.save(jogador);

        Map<Integer, Frame> frames = CreateFrames();
        frames.put(1, new Frame(null, 5, 5, 6));

        JogadorRequest jogadorRequest = new JogadorRequest();
        jogadorRequest.setId(jogador.getId());
        jogadorRequest.setFrames(frames);

        MockHttpServletRequestBuilder request = put(API).content(objectMapper.writeValueAsString(jogadorRequest)).contentType("application/json");

        ResultActions result = mvc.perform(request);

        final JogadorResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<JogadorResponse>() {
        });

        Assertions.assertEquals(16, response.getPontuacao());

    }

    @Test
    public void Test_Marca_Total() throws Exception {
        Jogador jogador = Jogador.builder()
                .nome("Tiago")
                .frames(CreateFrames())
                .build();

        jogadorRepository.save(jogador);

        Map<Integer, Frame> frames = CreateFrames();

        for (int i = 1; i <= 10; i++){
            frames.put(i, new Frame(null, 10, 10, 10));
        }

        JogadorRequest jogadorRequest = new JogadorRequest();
        jogadorRequest.setId(jogador.getId());
        jogadorRequest.setFrames(frames);

        MockHttpServletRequestBuilder request = put(API).content(objectMapper.writeValueAsString(jogadorRequest)).contentType("application/json");

        ResultActions result = mvc.perform(request);

        final JogadorResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<JogadorResponse>() {
        });

        Assertions.assertEquals(300, response.getPontuacao());
    }

    private Map<Integer, Frame> CreateFrames() {
        HashMap<Integer, Frame> frames = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            frames.put(i, new Frame(null, 0, 0, 0));
        }
        return frames;
    }

}
