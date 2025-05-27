package com.reto01.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PersonaTest {

    private Persona persona;
    private Direccion direccionMock; // Mock para la dependencia Direccion

    @BeforeEach
    void setUp() {
        direccionMock = mock(Direccion.class);
        // Inicializar con valores nulos o de ejemplo, ya que los setters los cambiarán
        persona = new Persona(null, null, null, false, null);
    }

    @Test
    void setNombre_y_getNombre() {
        persona.setNombre("Nuevo Nombre");
        assertEquals("Nuevo Nombre", persona.getNombre());
    }

    @Test
    void setNumeroCelular_y_getNumeroCelular() {
        persona.setNumeroCelular("987654321");
        assertEquals("987654321", persona.getNumeroCelular());
    }

    @Test
    void setCorreoElectronico_y_getCorreoElectronico() {
        persona.setCorreoElectronico("nuevo@test.com");
        assertEquals("nuevo@test.com", persona.getCorreoElectronico());
    }

    @Test
    void setComprarPaseEstacionamiento_y_isComprarPaseEstacionamiento() {
        persona.setComprarPaseEstacionamiento(true);
        assertTrue(persona.isComprarPaseEstacionamiento());
        
        persona.setComprarPaseEstacionamiento(false);
        assertFalse(persona.isComprarPaseEstacionamiento());
    }

    @Test
    void setDireccion_y_getDireccion() {
        Direccion nuevaDireccionMock = mock(Direccion.class);
        persona.setDireccion(nuevaDireccionMock);
        assertSame(nuevaDireccionMock, persona.getDireccion());
    }
    
    @Test
    void constructor_debeAsignarValoresCorrectamente() {
        // Esta prueba es para asegurar que el constructor que usamos en setUp también esté cubierto.
        // Si ya existe una prueba exhaustiva del constructor (por ejemplo, de ProfesorTest que lo llama vía super),
        // esta podría ser más simple o incluso omitirse si la cobertura ya es del 100% para el constructor.
        // Sin embargo, para una clase base como Persona, es bueno tener su propia prueba de constructor.
        Direccion dir = mock(Direccion.class);
        Persona p = new Persona("TestNombre", "111222", "test@email.com", true, dir);
        assertEquals("TestNombre", p.getNombre());
        assertEquals("111222", p.getNumeroCelular());
        assertEquals("test@email.com", p.getCorreoElectronico());
        assertTrue(p.isComprarPaseEstacionamiento());
        assertSame(dir, p.getDireccion());
    }
}
