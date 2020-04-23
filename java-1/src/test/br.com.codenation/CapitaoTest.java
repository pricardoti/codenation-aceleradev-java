package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class CapitaoTest extends AbstractJogadorTest {

    private Long idCapitaoPrimeiroTime = idJogador + 5;
    private Long idCapitaoSegundoTime = idJogador + 4;
    private Long idCapitaoTerceiroTime = idJogador + 6;

    @Before
    public void before() {
        super.before();

        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idCapitaoPrimeiroTime, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador + 10, CODIGO_TIME_DEFAULT, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());

        desafioMeuTimeApplication.incluirJogador(idJogador + 2, CODIGO_TIME_2, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idCapitaoSegundoTime, CODIGO_TIME_2, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador + 8, CODIGO_TIME_2, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());

        desafioMeuTimeApplication.incluirJogador(idJogador + 3, CODIGO_TIME_3, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idCapitaoTerceiroTime, CODIGO_TIME_3, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador + 9, CODIGO_TIME_3, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void definirCapitaoNaoEncontradoTest() {
        Long idCapitao = idJogador * -1;
        desafioMeuTimeApplication.definirCapitao(idCapitao);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarCapitaoDoTimeNaoEncontradoTest() {
        Long idTime = 1000L;
        desafioMeuTimeApplication.buscarCapitaoDoTime(idTime);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void buscarCapitaoDoTimeNaoInformadoTest() {
        desafioMeuTimeApplication.buscarCapitaoDoTime(ThreadLocalRandom
                .current().nextLong(1, 3));
    }

    @Test
    public void definirCapitaoTest() {
        desafioMeuTimeApplication.definirCapitao(idCapitaoPrimeiroTime);
        desafioMeuTimeApplication.definirCapitao(idCapitaoSegundoTime);
        desafioMeuTimeApplication.definirCapitao(idCapitaoTerceiroTime);

        assertEquals(idCapitaoPrimeiroTime, desafioMeuTimeApplication.buscarCapitaoDoTime(CODIGO_TIME_DEFAULT));
        assertEquals(idCapitaoSegundoTime, desafioMeuTimeApplication.buscarCapitaoDoTime(CODIGO_TIME_2));
        assertEquals(idCapitaoTerceiroTime, desafioMeuTimeApplication.buscarCapitaoDoTime(CODIGO_TIME_3));
    }
}
