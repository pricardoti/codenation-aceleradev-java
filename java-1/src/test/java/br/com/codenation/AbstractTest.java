package java.br.com.codenation;

import br.com.codenation.DesafioMeuTimeApplication;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

public class AbstractTest {

    protected static DesafioMeuTimeApplication desafioMeuTimeApplication;
    protected static Long idTime = ThreadLocalRandom
            .current().nextLong(1, 20);
    protected LocalDate data = LocalDate.now();
}
