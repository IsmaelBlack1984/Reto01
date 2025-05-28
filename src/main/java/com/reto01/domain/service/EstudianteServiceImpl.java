package com.reto01.domain.service;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.in.EstudianteUseCase;
import com.reto01.domain.port.out.EstudianteRepositoryPort;
import com.reto01.domain.specification.Specification;
import com.reto01.domain.specification.estudiante.NombreEqualsSpecification;
import com.reto01.domain.specification.estudiante.NumeroCelularEqualsSpecification; // Nueva importación

import java.util.List;

public class EstudianteServiceImpl implements EstudianteUseCase {

    private final EstudianteRepositoryPort estudianteRepositoryPort;

    // Constructor
    public EstudianteServiceImpl(EstudianteRepositoryPort estudianteRepositoryPort) {
        this.estudianteRepositoryPort = estudianteRepositoryPort;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepositoryPort.getEstudiantes();
    }

    @Override
    public List<Estudiante> filtrarPorNombre(String nombre) {
        Specification<Estudiante> spec = new NombreEqualsSpecification(nombre);
        return buscarEstudiantesPorCriterio(spec);
    }

    @Override
    public List<Estudiante> filtrarPorNumeroCelular(String numeroCelular) {
        Specification<Estudiante> spec = new NumeroCelularEqualsSpecification(numeroCelular);
        return buscarEstudiantesPorCriterio(spec);
    }

    @Override
    public List<Estudiante> ordenarPorPromedioNotas() {
        return estudianteRepositoryPort.findAllByOrderByPromedioNotasDesc();
    }

    @Override
    public List<Estudiante> buscarEstudiantesPorCriterio(Specification<Estudiante> spec) {
        return estudianteRepositoryPort.find(spec);
    }
}
