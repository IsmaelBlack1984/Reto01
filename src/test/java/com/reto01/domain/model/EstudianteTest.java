package com.reto01.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        // Inicializar con valores de ejemplo, los setters los cambiarán
        estudiante = new Estudiante("N001", "Nombre Inicial", "12345", "inicial@test.com", 5.0, "Asignaturas Viejas", "Seminarios Viejos");
    }

    @Test
    void constructor_debeAsignarValoresCorrectamente() {
        // Prueba del constructor específico de Estudiante
        Estudiante est = new Estudiante("E123", "Juan Perez", "111222333", "juan.perez@example.com", 8.5, "Matemáticas,Física", "IA Aplicada");
        
        // Campos de Persona (a través de super())
        assertEquals("Juan Perez", est.getNombre());
        assertEquals("111222333", est.getNumeroCelular());
        assertEquals("juan.perez@example.com", est.getCorreoElectronico());
        assertFalse(est.isComprarPaseEstacionamiento()); // Valor por defecto en constructor de Estudiante
        assertNull(est.getDireccion()); // Valor por defecto en constructor de Estudiante

        // Campos específicos de Estudiante
        assertEquals("E123", est.getNumeroEstudiante());
        assertEquals(8.5, est.getPromedioNotas());
        assertEquals("Matemáticas,Física", est.getListadoAsignaturas());
        assertEquals("IA Aplicada", est.getSeminariosTomados());
    }

    @Test
    void setNumeroEstudiante_y_getNumeroEstudiante() {
        estudiante.setNumeroEstudiante("N002");
        assertEquals("N002", estudiante.getNumeroEstudiante());
    }

    @Test
    void setPromedioNotas_y_getPromedioNotas() {
        estudiante.setPromedioNotas(9.5);
        assertEquals(9.5, estudiante.getPromedioNotas());
    }

    @Test
    void setListadoAsignaturas_y_getListadoAsignaturas() {
        estudiante.setListadoAsignaturas("Nuevas Asignaturas");
        assertEquals("Nuevas Asignaturas", estudiante.getListadoAsignaturas());
    }

    @Test
    void setSeminariosTomados_y_getSeminariosTomados() {
        estudiante.setSeminariosTomados("Nuevos Seminarios");
        assertEquals("Nuevos Seminarios", estudiante.getSeminariosTomados());
    }
}
