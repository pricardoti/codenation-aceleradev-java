package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculadorDeClassesTest {

    private CalculadorDeClasses calculadorDeClasses;

    @Before
    public void before() {
        calculadorDeClasses = new CalculadorDeClasses();
    }

    @Test
    public void somarResultadoZeroTest() {
        assertEquals(BigDecimal.ZERO, calculadorDeClasses.somar(new ClasseValorZero()));
    }

    @Test
    public void somarTest() {
        assertEquals(BigDecimal.valueOf(500), calculadorDeClasses.somar(new ClasseValoresTest()));
    }

    @Test
    public void subtrairResultadoZeroTest() {
        assertEquals(BigDecimal.ZERO, calculadorDeClasses.subtrair(new ClasseValorZero()));
    }

    @Test
    public void subtrairTest() {
        assertEquals(BigDecimal.valueOf(-250), calculadorDeClasses.subtrair(new ClasseValoresTest()));
    }

    @Test
    public void totalizarTest() {
        assertEquals(BigDecimal.ZERO, calculadorDeClasses.totalizar(new ClasseValorZero()));
        assertEquals(BigDecimal.valueOf(750), calculadorDeClasses.totalizar(new ClasseValoresTest()));
    }

    private class ClasseValoresTest {

        private BigDecimal campoSemAnnotation = BigDecimal.valueOf(150);
        private String campoString = "";

        @Somar
        private BigDecimal valorSoma1 = BigDecimal.valueOf(250);

        @Somar
        private BigDecimal valorSoma2 = BigDecimal.valueOf(300);

        @Somar
        private BigDecimal valorSoma3 = BigDecimal.valueOf(-50);

        @Somar
        private Double valorSoma4 = 2.5;

        @Subtrair
        private BigDecimal valorSubtrair1 = BigDecimal.valueOf(-150);

        @Subtrair
        private BigDecimal valorSubtrair2 = BigDecimal.valueOf(-150);

        @Subtrair
        private BigDecimal valorSubtrair3 = BigDecimal.valueOf(50);

        @Subtrair
        private Integer valorSubtrair4 = 50;
    }

    private class ClasseValorZero {
    }
}
