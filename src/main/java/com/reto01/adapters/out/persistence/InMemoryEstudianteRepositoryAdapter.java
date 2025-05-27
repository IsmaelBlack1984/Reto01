package com.reto01.adapters.out.persistence;

import org.springframework.stereotype.Repository;
import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.out.EstudianteRepositoryPort;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Repository
public class InMemoryEstudianteRepositoryAdapter implements EstudianteRepositoryPort {

    private List<Estudiante> estudiantes;

    // Constructor
    public InMemoryEstudianteRepositoryAdapter() {
        estudiantes = new ArrayList<>();
        inicializarEstudiantes();
    }


    private void inicializarEstudiantes() {
        estudiantes.add(new Estudiante("123", "Juan Pérez", "123456789", "juan@email.com", 9.5, "Matemáticas", "Seminario IA"));
        estudiantes.add(new Estudiante("124", "Ana Gómez", "987654321", "ana@email.com", 8.3, "Historia", "Seminario Física"));
        estudiantes.add(new Estudiante("125", "Carlos Sánchez", "555555555", "carlos@email.com", 7.8, "Literatura", "Seminario Literatura"));
        estudiantes.add(new Estudiante("126", "Laura Ruiz", "444444444", "laura@email.com", 9.0, "Física", "Seminario Química"));
        estudiantes.add(new Estudiante("127", "Pedro López", "333333333", "pedro@email.com", 6.7, "Biología", "Seminario Matemáticas"));
        estudiantes.add(new Estudiante("128", "Marta Fernández", "222222222", "marta@email.com", 8.9, "Química", "Seminario Tecnología"));
        estudiantes.add(new Estudiante("129", "Luis García", "111111111", "luis@email.com", 7.5, "Geografía", "Seminario Historia"));
        estudiantes.add(new Estudiante("130", "Sofía Martínez", "666666666", "sofia@email.com", 9.2, "Filosofía", "Seminario Filosofía"));
        estudiantes.add(new Estudiante("131", "David Pérez", "777777777", "david@email.com", 8.5, "Arte", "Seminario Pintura"));
        estudiantes.add(new Estudiante("132", "Isabel Rodríguez", "888888888", "isabel@email.com", 7.3, "Inglés", "Seminario Literatura"));
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    @Override
    public List<Estudiante> findByNombre(String nombre) {
        return estudiantes.stream()
                .filter(estudiante -> estudiante.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> findByNumeroCelular(String numeroCelular) {
        return estudiantes.stream()
                .filter(estudiante -> estudiante.getNumeroCelular().equals(numeroCelular))
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> findAllByOrderByPromedioNotasDesc() {
        return estudiantes.stream()
                .sorted(Comparator.comparingDouble(Estudiante::getPromedioNotas).reversed())
                .collect(Collectors.toList());
    }
}