package com.tiago.boliche.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiago.boliche.entity.Frame;
import com.tiago.boliche.entity.Rodada;
import com.tiago.boliche.entity.Usuario;
import com.tiago.boliche.model.usuario.UsuarioRequest;
import com.tiago.boliche.model.usuario.UsuarioResponse;
import com.tiago.boliche.repository.rodada.RodadaRepository;
import com.tiago.boliche.repository.usuario.UsuarioRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Rollback
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

    private final String USUARIO_API = "/usuario";

    @Autowired
    RodadaRepository rodadaRepository;

    @SpyBean
    UsuarioRepository usuarioRepository;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        Mockito.reset(usuarioRepository);
    }

    @Test
    public void test_Listar_Pontos_por_Rodada_Return_OK() throws Exception {

        createUsuario();
        createUsuario();
        createUsuario();

        MockHttpServletRequestBuilder request = get(USUARIO_API + "/all");

        ResultActions result = mvc.perform(request);

        final List<UsuarioResponse> response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<List<UsuarioResponse>>() {
        });

        Assertions.assertEquals(200, result.andReturn().getResponse().getStatus());
        Assertions.assertEquals("application/json", result.andReturn().getResponse().getContentType());
        Assertions.assertEquals(3, response.size());
    }

    @Test
    public void test_criar_usuario() throws Exception {

        var usuario = Usuario.builder().nome("Tiago").rodadas(createRodadas()).build();

        MockHttpServletRequestBuilder request = post(USUARIO_API).contentType("application/json").content(objectMapper.writeValueAsString(usuario));

        ResultActions result = mvc.perform(request);
        final UsuarioResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<UsuarioResponse>() {
        });

        Assertions.assertEquals(200, result.andReturn().getResponse().getStatus());
        Assertions.assertEquals("application/json", result.andReturn().getResponse().getContentType());
        Assertions.assertEquals("Tiago", response.getNome());
    }

    @Test
    public void test_adicionar_frame() throws Exception {

        createUsuarioInicial();

        var usuarioRequest = UsuarioRequest.builder()
                .nome("Tiago")
                .pontosPrimeiraJogada(10)
                .strike(true)
                .faltas(0)
                .pontosSegundaJogada(8)
                .pontosTerceiraJogada(5)
                .rodada(1)
                .spare(false)
                .build();


        MockHttpServletRequestBuilder request = put(USUARIO_API).contentType("application/json").content(objectMapper.writeValueAsString(usuarioRequest));

        ResultActions result = mvc.perform(request);
        final UsuarioResponse response = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<UsuarioResponse>() {
        });

        Assertions.assertEquals(200, result.andReturn().getResponse().getStatus());
        Assertions.assertEquals("application/json", result.andReturn().getResponse().getContentType());
        Assertions.assertEquals("Tiago", response.getNome());
        Assertions.assertEquals(23, response.getRodadas().get(0).getPontos());
    }

    private void createUsuarioInicial() {
        Usuario usuario = new Usuario();
        usuario.setNome("Tiago");
        usuario.setRodadas(createRodadasIniciais());
        usuarioRepository.save(usuario);
    }

    private List<Rodada> createRodadasIniciais() {
        List<Rodada> rodadas = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Rodada rodada = new Rodada();
            rodada.setNumeroRodada(i + 1);
            rodadas.add(rodada);
        }
        return rodadas;
    }


    private void createUsuario() {
        var usuario = new Usuario();
        var rodadas = createRodadas();
        usuario.setNome("Tiago");
        usuario.setRodadas(rodadas);
        usuarioRepository.save(usuario);
    }

    private List<Rodada> createRodadas() {
        var rodadas = new ArrayList<Rodada>();
        do {
            Rodada rodada = new Rodada();
            rodada.setNumeroRodada(1);
            rodada.setPontos(30);
            rodada.setFrame(createFrame());
            rodadas.add(rodada);
        } while (rodadas.size() < 10);

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
