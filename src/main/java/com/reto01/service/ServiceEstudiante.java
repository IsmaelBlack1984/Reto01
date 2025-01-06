package com.reto01.service;

import org.springframework.stereotype.Service;

import com.reto01.model.Estudiante;
import com.reto01.repository.RepositorioEstudiante;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class ServiceEstudiante {

    private final RepositorioEstudiante repositorioEstudiante;

    // Constructor
    public ServiceEstudiante(RepositorioEstudiante repositorioEstudiante) {
        this.repositorioEstudiante = repositorioEstudiante;
    }


    public List<Estudiante> listarEstudiantes() {
        return repositorioEstudiante.getEstudiantes(); 
    }

    public List<Estudiante> filtrarPorNombre(String nombre) {
        return repositorioEstudiante.getEstudiantes().stream()
                .filter(estudiante -> estudiante.getNombre().equalsIgnoreCase(nombre)) 
                .collect(Collectors.toList()); 
    }


    public List<Estudiante> filtrarPorNumeroCelular(String numeroCelular) {
        return repositorioEstudiante.getEstudiantes().stream()
                .filter(estudiante -> estudiante.getNumeroCelular().equals(numeroCelular)) // Filtra por número de celular
                .collect(Collectors.toList()); 
    }


    public List<Estudiante> ordenarPorPromedioNotas() {
        return repositorioEstudiante.getEstudiantes().stream()
                .sorted(Comparator.comparingDouble(Estudiante::getPromedioNotas).reversed()) // Ordena por promedio de notas (de mayor a menor)
                .collect(Collectors.toList()); 
    }
}