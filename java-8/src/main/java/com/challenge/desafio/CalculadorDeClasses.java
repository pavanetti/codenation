package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

// Porque não poderia ter nada menos orientado a objetos
public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object obj) {
        return somarPorAnotacao(obj, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object obj) {
        return somarPorAnotacao(obj, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object obj) {
        return somar(obj).subtract(subtrair(obj));
    }

    private BigDecimal somarPorAnotacao(Object obj, Class<? extends  Annotation> anotacao) {
        Stream<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields());
        Stream<Field> valids = fields.filter(f -> f.isAnnotationPresent(anotacao) && f.getType().equals(BigDecimal.class));
        return valids.map(f -> {
            try {
                f.setAccessible(true);
                return (BigDecimal) f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return BigDecimal.ZERO;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
