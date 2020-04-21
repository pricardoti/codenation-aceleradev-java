package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DesafioMeuTimeApplicationTest {

    private static final Long idTime = new Random().nextLong();
    private static final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();

    private LocalDate dataCriacao = LocalDate.now();

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarNomeObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "", dataCriacao, "Vermelho", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarDataCriacaoObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", null, "Vermelho", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarCorUniformePrincipalObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", dataCriacao, "", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarCorUniformeSecundarioObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", dataCriacao, "Vermelho", "");
    }

    @Test
    public void incluirTimeTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", dataCriacao, "Vermelho", "Preto");
        assertEquals("Time 1", desafioMeuTimeApplication.buscarNomeTime(idTime));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeIdDuplicadoTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", dataCriacao, "Vermelho", "Preto");
    }
}
