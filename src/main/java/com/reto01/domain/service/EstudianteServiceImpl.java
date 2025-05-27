package com.reto01.domain.service;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.in.EstudianteUseCase;
import com.reto01.domain.port.out.EstudianteRepositoryPort;

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
        return estudianteRepositoryPort.findByNombre(nombre);
    }

    @Override
    public List<Estudiante> filtrarPorNumeroCelular(String numeroCelular) {
        return estudianteRepositoryPort.findByNumeroCelular(numeroCelular);
    }

    @Override
    public List<Estudiante> ordenarPorPromedioNotas() {
        return estudianteRepositoryPort.findAllByOrderByPromedioNotasDesc();
    }
}
