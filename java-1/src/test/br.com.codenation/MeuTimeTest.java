package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeuTimeTest extends AbstractTest {

    @Before
    public void before() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarNomeObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "", data, "Vermelho", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarDataCriacaoObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", null, "Vermelho", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarCorUniformePrincipalObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", data, "", "Preto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarCorUniformeSecundarioObrigatorioTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", data, "Vermelho", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incluirTimeValidarIdNegativoTest() {
        desafioMeuTimeApplication.incluirTime(-1L, "Time 1", data, "Vermelho", "Preto");
        assertEquals("Time 1", desafioMeuTimeApplication.buscarNomeTime(idTime));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarNomeTimeNaoEncontratoTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", data, "Vermelho", "Preto");
        desafioMeuTimeApplication.buscarNomeTime(100L);
    }

    @Test
    public void incluirTimeTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", data, "Vermelho", "Preto");
        assertEquals("Time 1", desafioMeuTimeApplication.buscarNomeTime(idTime));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeIdDuplicadoTest() {
        desafioMeuTimeApplication.incluirTime(idTime, "Time 1", data, "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(idTime, "Time 2", data, "Preto", "Vermelho");
    }
}
