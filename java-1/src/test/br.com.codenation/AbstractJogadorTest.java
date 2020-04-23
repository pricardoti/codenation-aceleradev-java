package br.com.codenation;

import org.junit.Before;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AbstractJogadorTest extends AbstractTest {

    protected static final long CODIGO_TIME_1 = 1L;
    protected static final long CODIGO_TIME_2 = 2L;
    protected static final long CODIGO_TIME_3 = 3L;

    protected static Long idJogador = LongStream.range(1, 20).findFirst().getAsLong();

    @Before
    public void before() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_1, "Time 1", data, "Vermelho", "Preto");
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_2, "Time 2", data, "Azul", "Branco");
        desafioMeuTimeApplication.incluirTime(CODIGO_TIME_3, "Time 3", data, "Amarelo", "Vermelho");
    }

    protected Integer getRandomNivelHablidade() {
        return IntStream.range(0, 100).findFirst().getAsInt();
    }

    protected BigDecimal getRandomSalario() {
        return BigDecimal.valueOf(LongStream.range(1, 1000000000).findFirst().getAsLong());
    }
}
