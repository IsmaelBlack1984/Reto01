package com.reto01.domain.service;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.in.EstudianteUseCase;
import com.reto01.domain.port.out.EstudianteRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

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
        return estudianteRepositoryPort.getEstudiantes().stream()
                .filter(estudiante -> estudiante.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> filtrarPorNumeroCelular(String numeroCelular) {
        return estudianteRepositoryPort.getEstudiantes().stream()
                .filter(estudiante -> estudiante.getNumeroCelular().equals(numeroCelular))
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> ordenarPorPromedioNotas() {
        return estudianteRepositoryPort.getEstudiantes().stream()
                .sorted(Comparator.comparingDouble(Estudiante::getPromedioNotas).reversed())
                .collect(Collectors.toList());
    }
}
