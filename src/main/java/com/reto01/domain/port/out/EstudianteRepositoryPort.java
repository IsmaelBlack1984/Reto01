package com.reto01.domain.port.out;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.specification.Specification; // Nueva importación
import java.util.List;

public interface EstudianteRepositoryPort {
    List<Estudiante> getEstudiantes();
    List<Estudiante> findByNombre(String nombre);
    List<Estudiante> findByNumeroCelular(String numeroCelular);
    List<Estudiante> findAllByOrderByPromedioNotasDesc();
    List<Estudiante> find(Specification<Estudiante> spec); // Nuevo método
}
