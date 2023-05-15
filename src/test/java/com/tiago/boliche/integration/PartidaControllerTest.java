package com.tiago.boliche.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiago.boliche.model.partida.PartidaResponse;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    public void Test_Criar_Partida() throws Exception {

        List<String> jogadores = new ArrayList<>();
        jogadores.add("Tiago");
        jogadores.add("João");
        jogadores.add("Maria");

        MockHttpServletRequestBuilder request = post(PARTIDA_API).content(objectMapper.writeValueAsString(jogadores)).contentType("application/json");

        ResultActions result = mvc.perform(request);

        final PartidaResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<PartidaResponse>() {
        });

        Assertions.assertNotNull(response.getId());
    }
}
