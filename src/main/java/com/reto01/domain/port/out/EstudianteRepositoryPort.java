package com.reto01.domain.port.out;

import com.reto01.domain.model.Estudiante;
import java.util.List;

public interface EstudianteRepositoryPort {
    List<Estudiante> getEstudiantes();
    List<Estudiante> findByNombre(String nombre);
    List<Estudiante> findByNumeroCelular(String numeroCelular);
    List<Estudiante> findAllByOrderByPromedioNotasDesc();
}
