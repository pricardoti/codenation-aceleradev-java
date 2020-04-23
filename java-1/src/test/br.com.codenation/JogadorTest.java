package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JogadorTest extends AbstractJogadorTest {

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNomeTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeNaoInformadaTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, null, getRandomSalario());
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeValorInvalidoMaiorTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, 101, getRandomSalario());
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeValorInvalidoMenorTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, -1, getRandomSalario());
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarDataTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", null, -1, getRandomSalario());
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarSalarioNegativoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, getRandomNivelHablidade(), BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarSalarioZeroTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, getRandomNivelHablidade(), BigDecimal.valueOf(-1));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void incluirJogadorValidarIdTimeNaoEncontradoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, 100L, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirJogadorFalhaTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test
    public void incluirJogadorSucessoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarNomeJogadorNaoEncontratoTest() {
        Long idJogadorBuscarNome = idJogador + 10;
        incluirJogadoresBuscaNome(idJogadorBuscarNome);
        desafioMeuTimeApplication.buscarNomeJogador(100L);
    }

    @Test
    public void buscarNomeJogadorTest() {
        Long idJogadorBuscarNome = idJogador + 2;
        incluirJogadoresBuscaNome(idJogadorBuscarNome);

        String nomeJogador = desafioMeuTimeApplication.buscarNomeJogador(idJogadorBuscarNome);

        assertFalse(nomeJogador.isEmpty());
        assertEquals("Jogador Selecionado", nomeJogador);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadoresTimeNaoEncotradoTest() {
        desafioMeuTimeApplication.buscarJogadoresDoTime(100L);
    }

    @Test
    public void buscarJogadoresDoTimeTest() {
        List<Long> jogadoresTime = desafioMeuTimeApplication.buscarJogadoresDoTime(CODIGO_TIME_1);
        assertEquals(jogadoresTime.size(), 0);
    }

    private void incluirJogadoresBuscaNome(Long idJogadorBuscarNome) {
        desafioMeuTimeApplication.incluirJogador(idJogadorBuscarNome, idTime, "Jogador Selecionado", data, getRandomNivelHablidade(), getRandomSalario());
        incluirJogadores(idTime, idJogador, idJogador + 3, idJogador + 6);
    }

    private void incluirJogadores(Long idTime, Long idJogador1, Long idJogador2, Long idJogador3) {
        desafioMeuTimeApplication.incluirJogador(idJogador1, idTime, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador2, idTime, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador3, idTime, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
    }
}
