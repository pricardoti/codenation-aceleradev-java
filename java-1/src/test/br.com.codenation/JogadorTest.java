package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class JogadorTest extends AbstractJogadorTest {

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNomeTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "", data, nivelHabilidade, salario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeNaoInformadaTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, null, salario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeValorInvalidoMaiorTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, 101, salario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarNivelHabilidadeValorInvalidoMenorTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, -1, salario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarDataTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", null, -1, salario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarSalarioNegativoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, nivelHabilidade, BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirJogadorValidarSalarioZeroTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, nivelHabilidade, BigDecimal.valueOf(-1));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void incluirJogadorValidarIdTimeNaoEncontradoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, 100L, "Jogador 1", data, nivelHabilidade, salario);
    }

    @Test
    public void incluirJogadorSucessoTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, nivelHabilidade, salario);
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirJogadorFalhaTest() {
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 1", data, nivelHabilidade, salario);
        desafioMeuTimeApplication.incluirJogador(idJogador, idTime, "Jogador 2", data, nivelHabilidade, salario);
    }

}
