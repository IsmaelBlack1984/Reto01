package com.reto01.domain.specification.estudiante;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.specification.Specification;

public class NumeroCelularEqualsSpecification implements Specification<Estudiante> {
    private final String numeroCelular;

    public NumeroCelularEqualsSpecification(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    @Override
    public boolean isSatisfiedBy(Estudiante candidato) {
        if (candidato == null || candidato.getNumeroCelular() == null || numeroCelular == null) {
            return false;
        }
        return candidato.getNumeroCelular().equals(numeroCelular);
    }

    // Getter para el criterio
    public String getNumeroCelular() {
        return numeroCelular;
    }
}
