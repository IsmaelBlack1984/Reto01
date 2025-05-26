package com.reto01.domain.port.out;

import com.reto01.domain.model.Estudiante;
import java.util.List;

public interface EstudianteRepositoryPort {
    List<Estudiante> getEstudiantes();
}
