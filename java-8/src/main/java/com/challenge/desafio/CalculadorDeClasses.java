package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object objeto) {
        return obterValuesFields(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object objeto) {
        return obterValuesFields(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object objeto) {
        return somar(objeto).subtract(subtrair(objeto));
    }

    private BigDecimal obterValuesFields(Object object, Class<? extends Annotation> annotationClass) {
        return Stream.of(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotationClass)
                        && field.getType().equals(BigDecimal.class))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (BigDecimal) field.get(object);
                    } catch (IllegalAccessException e) {
                        return BigDecimal.ZERO;
                    }
                }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
