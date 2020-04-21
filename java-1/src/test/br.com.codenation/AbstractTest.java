package br.com.codenation;

import java.time.LocalDate;
import java.util.stream.LongStream;

public class AbstractTest {

    protected static DesafioMeuTimeApplication desafioMeuTimeApplication;
    protected static final Long idTime = LongStream.range(1, 20).findFirst().getAsLong();
    protected LocalDate data = LocalDate.now();
}
