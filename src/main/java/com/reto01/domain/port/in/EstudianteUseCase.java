package com.reto01.domain.port.in;

import com.reto01.domain.model.Estudiante;
import java.util.List;

public interface EstudianteUseCase {
    List<Estudiante> listarEstudiantes();
    List<Estudiante> filtrarPorNombre(String nombre);
    List<Estudiante> filtrarPorNumeroCelular(String numeroCelular);
    List<Estudiante> ordenarPorPromedioNotas();
}
