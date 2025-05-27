package com.reto01.domain.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProfesorTest {

    @Test
    void constructor_debeAsignarValoresCorrectamente() {
        Direccion direccionMock = mock(Direccion.class); // Usamos un mock para Direccion ya que no es el foco de esta prueba
        List<String> materias = new ArrayList<>(List.of("Matemáticas", "Física"));
        List<String> seminarios = new ArrayList<>(List.of("IA", "Robótica"));

        Profesor profesor = new Profesor(
                "Nombre Profesor", "123456789", "profesor@test.com", true,
                direccionMock, 50000.0, materias, seminarios
        );

        // Verificamos los campos de Persona (opcionalmente, pero bueno para asegurar que el constructor los pasa)
        assertEquals("Nombre Profesor", profesor.getNombre());
        assertEquals("123456789", profesor.getNumeroCelular());
        assertEquals("profesor@test.com", profesor.getCorreoElectronico());
        assertTrue(profesor.isComprarPaseEstacionamiento());
        assertSame(direccionMock, profesor.getDireccion()); // Verifica que la instancia de Direccion es la misma

        // Verificamos los campos específicos de Profesor
        assertEquals(50000.0, profesor.getSalario());
        assertEquals(materias, profesor.getMateriasDictadas());
        assertEquals(seminarios, profesor.getSeminarios());
    }

    @Test
    void setSalario_y_getSalario() {
        Profesor profesor = new Profesor(null, null, null, false, null, 0.0, null, null);
        profesor.setSalario(60000.0);
        assertEquals(60000.0, profesor.getSalario());
    }

    @Test
    void setMateriasDictadas_y_getMateriasDictadas() {
        Profesor profesor = new Profesor(null, null, null, false, null, 0.0, null, null);
        List<String> nuevasMaterias = new ArrayList<>(List.of("Química"));
        profesor.setMateriasDictadas(nuevasMaterias);
        assertEquals(nuevasMaterias, profesor.getMateriasDictadas());
    }

    @Test
    void setSeminarios_y_getSeminarios() {
        Profesor profesor = new Profesor(null, null, null, false, null, 0.0, null, null);
        List<String> nuevosSeminarios = new ArrayList<>(List.of("Big Data"));
        profesor.setSeminarios(nuevosSeminarios);
        assertEquals(nuevosSeminarios, profesor.getSeminarios());
    }
}
