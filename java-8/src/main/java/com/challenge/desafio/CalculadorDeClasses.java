package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

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

    private BigDecimal somarPorAnotacao(Object obj, Class<? extends Annotation> anotacao) {
        BigDecimal result = BigDecimal.ZERO;
        Field[] fields = obj.getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                if (field.getAnnotation(anotacao) != null) {
                    if (field.getType().equals(BigDecimal.class)) {
                        field.setAccessible(true);
                        result = result.add((BigDecimal) field.get(obj));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}
