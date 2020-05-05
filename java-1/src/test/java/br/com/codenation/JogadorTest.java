package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test
    public void incluirJogadorSucessoTest() {
        desafioMeuTimeApplication.incluirJogador(ThreadLocalRandom.current().nextLong(1, 100), CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarNomeJogadorNaoEncontratoTest() {
        Long idJogadorBuscarNome = ThreadLocalRandom.current().nextLong(1, 99);
        incluirJogadoresBuscaNome(idJogadorBuscarNome);
        desafioMeuTimeApplication.buscarNomeJogador(100L);
    }

    @Test
    public void buscarNomeJogadorTest() {
        Long idJogadorBuscarNome = ThreadLocalRandom.current().nextLong(2, 100);
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
        List<Long> jogadoresTime = desafioMeuTimeApplication.buscarJogadoresDoTime(CODIGO_TIME_DEFAULT);
        assertEquals(jogadoresTime.size(), 0);

        incluirJogadores(CODIGO_TIME_DEFAULT, 1L, 2L, 3L);

        jogadoresTime = desafioMeuTimeApplication.buscarJogadoresDoTime(CODIGO_TIME_DEFAULT);
        assertEquals(jogadoresTime.size(), 3);
    }

    private void incluirJogadoresBuscaNome(Long idJogadorBuscarNome) {
        desafioMeuTimeApplication.incluirJogador(idJogadorBuscarNome, CODIGO_TIME_DEFAULT, "Jogador Selecionado", data, getRandomNivelHablidade(), getRandomSalario());
        incluirJogadores(CODIGO_TIME_DEFAULT, idJogador, idJogador + 3, idJogador + 6);
    }

    private void incluirJogadores(Long idTime, Long idJogador1, Long idJogador2, Long idJogador3) {
        desafioMeuTimeApplication.incluirJogador(idJogador1, idTime, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador2, idTime, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador3, idTime, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarMelhorJogadorDoTimeNaoEncontradoTest() {
        incluirJogadoresPadroesTime();
        desafioMeuTimeApplication.buscarMelhorJogadorDoTime(5L);
    }

    @Test
    public void buscarMelhorJogadorDoTimeTest() {
        Long idJogador = ThreadLocalRandom.current().nextLong(6, 100);
        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(3L, CODIGO_TIME_DEFAULT, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(4L, CODIGO_TIME_DEFAULT, "Jogador 4", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Melhor Jogador", data, 100, getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(5L, CODIGO_TIME_DEFAULT, "Jogador 5", data, getRandomNivelHablidade(), getRandomSalario());
        assertEquals(desafioMeuTimeApplication.buscarMelhorJogadorDoTime(CODIGO_TIME_DEFAULT), idJogador);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorMaisVelhoTimeNaoEncontradoTest() {
        incluirJogadoresPadroesTime();
        desafioMeuTimeApplication.buscarJogadorMaisVelho(5L);
    }

    @Test
    public void buscarJogadorMaisVelhoTimeTest() {
        Long idJogador = ThreadLocalRandom.current().nextLong(10, 100);
        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", LocalDate.of(1993, 8, 18), getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(3L, CODIGO_TIME_DEFAULT, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(4L, CODIGO_TIME_DEFAULT, "Jogador 4", LocalDate.of(2015, 6, 25), getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador Mais Velho", LocalDate.of(1990, 10, 28), getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(5L, CODIGO_TIME_DEFAULT, "Jogador 5", data, getRandomNivelHablidade(), getRandomSalario());
        assertEquals(idJogador, desafioMeuTimeApplication.buscarJogadorMaisVelho(CODIGO_TIME_DEFAULT));
    }

    @Test
    public void buscarTimesVazioTest() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        assertEquals(desafioMeuTimeApplication.buscarTimes().size(), 0);
    }

    @Test
    public void buscarTimesTest() {
        assertEquals(desafioMeuTimeApplication.buscarTimes().size(), 3);

        desafioMeuTimeApplication.incluirTime(4L, "Time 4", data, "Marrom", "Branco");
        desafioMeuTimeApplication.incluirTime(5L, "Time 5", data, "Verde", "Branco");
        assertEquals(desafioMeuTimeApplication.buscarTimes().size(), 5);
    }

    private void incluirJogadoresPadroesTime() {
        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(3L, CODIGO_TIME_DEFAULT, "Jogador 3", data, getRandomNivelHablidade(), getRandomSalario());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorMaiorSalarioNaoEncontradoTest() {
        incluirJogadoresPadroesTime();
        desafioMeuTimeApplication.buscarJogadorMaiorSalario(5L);
    }

    @Test
    public void buscarJogadorMaiorSalarioTest() {
        Long idJogador = ThreadLocalRandom.current().nextLong(10, 100);
        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador Maior Salario", data, getRandomNivelHablidade(), BigDecimal.valueOf(250000));
        desafioMeuTimeApplication.incluirJogador(4L, CODIGO_TIME_DEFAULT, "Jogador 4", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(5L, CODIGO_TIME_DEFAULT, "Jogador 5", data, getRandomNivelHablidade(), getRandomSalario());
        assertEquals(idJogador, desafioMeuTimeApplication.buscarJogadorMaiorSalario(CODIGO_TIME_DEFAULT));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarSalarioDoJogadorNaoEncontradoTest() {
        incluirJogadoresPadroesTime();
        desafioMeuTimeApplication.buscarSalarioDoJogador(5L);
    }

    @Test
    public void buscarSalarioDoJogadorTest() {
        Long idJogador = ThreadLocalRandom.current().nextLong(10, 100);
        BigDecimal salarioJogador = BigDecimal.valueOf(250000);

        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", data, getRandomNivelHablidade(), getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(idJogador, CODIGO_TIME_DEFAULT, "Jogador Salario", data, getRandomNivelHablidade(), salarioJogador);

        assertEquals(salarioJogador, desafioMeuTimeApplication.buscarSalarioDoJogador(idJogador));
    }

    @Test
    public void buscarTopJogadoresVazioTest() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        assertEquals(desafioMeuTimeApplication.buscarTopJogadores(3).size(), 0);
    }

    @Test
    public void buscarTopJogadoresTest() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();

        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_DEFAULT, "Time 1", data, "Vermelho", "Preto");

        desafioMeuTimeApplication.incluirJogador(1L, CODIGO_TIME_DEFAULT, "Jogador 1", data, 3, getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(2L, CODIGO_TIME_DEFAULT, "Jogador 2", data, 5, getRandomSalario());
        desafioMeuTimeApplication.incluirJogador(3L, CODIGO_TIME_DEFAULT, "Jogador 3", data, 1, getRandomSalario());

        // TOP 1
        List<Long> topJogadores = desafioMeuTimeApplication.buscarTopJogadores(1);
        assertEquals(topJogadores.size(), 1);
//        assertEquals(topJogadores.get(0), 2L);

        // TOP 3
        topJogadores = desafioMeuTimeApplication.buscarTopJogadores(3);
        assertEquals(topJogadores.size(), 3);
//        assertEquals(topJogadores.get(0), 2L);
//        assertEquals(topJogadores.get(1), 1L);
//        assertEquals(topJogadores.get(2), 3L);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarCorCamisaTimeDeForaNaoEncontradoTest() {
        desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(CODIGO_TIME_DEFAULT, 5L);
    }

    @Test
    public void buscarCorCamisaTimeDeFora() {
        assertEquals("Azul", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(CODIGO_TIME_DEFAULT, CODIGO_TIME_2));
        assertEquals("Branco", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(CODIGO_TIME_DEFAULT, CODIGO_TIME_3));
        assertEquals("Vermelho", desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(CODIGO_TIME_2, CODIGO_TIME_3));
    }

}
