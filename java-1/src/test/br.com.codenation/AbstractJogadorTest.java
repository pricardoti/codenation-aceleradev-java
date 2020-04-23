package br.com.codenation;

import org.junit.Before;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class AbstractJogadorTest extends AbstractTest {

    protected static final long CODIGO_TIME_DEFAULT = 1L;
    protected static final long CODIGO_TIME_2 = 2L;
    protected static final long CODIGO_TIME_3 = 3L;

    protected static final long idJogador = 1L;

    @Before
    public void before() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_DEFAULT, "Time 1", data, "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_2, "Time 2", data, "Azul", "Branco");
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_3, "Time 3", data, "Amarelo", "Vermelho");
    }

    protected Integer getRandomNivelHablidade() {
        return ThreadLocalRandom
                .current().nextInt(0, 100);
    }

    protected BigDecimal getRandomSalario() {
        return BigDecimal.valueOf(ThreadLocalRandom
                .current().nextLong(1, 100000));
    }
}
