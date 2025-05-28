package com.reto01.domain.specification.estudiante;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.specification.Specification;

public class NombreEqualsSpecification implements Specification<Estudiante> {
    private final String nombre;

    public NombreEqualsSpecification(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean isSatisfiedBy(Estudiante candidato) {
        if (candidato == null || candidato.getNombre() == null || nombre == null) {
            return false;
        }
        return candidato.getNombre().equalsIgnoreCase(nombre);
    }

    // Getter para el criterio, útil para traducción a queries de BD
    public String getNombre() {
        return nombre;
    }
}
