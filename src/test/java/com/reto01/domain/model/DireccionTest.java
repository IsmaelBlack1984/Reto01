package com.reto01.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DireccionTest {

    @Test
    void constructor_debeAsignarValoresCorrectamente() {
        Direccion direccion = new Direccion("Ciudad Ejemplo", "Estado Ejemplo", "12345", "País Ejemplo");
        assertEquals("Ciudad Ejemplo", direccion.getCiudad());
        assertEquals("Estado Ejemplo", direccion.getEstado());
        assertEquals("12345", direccion.getCodigoPostal());
        assertEquals("País Ejemplo", direccion.getPais());
    }

    @Test
    void setCiudad_y_getCiudad() {
        Direccion direccion = new Direccion(null, null, null, null);
        direccion.setCiudad("Nueva Ciudad");
        assertEquals("Nueva Ciudad", direccion.getCiudad());
    }

    @Test
    void setEstado_y_getEstado() {
        Direccion direccion = new Direccion(null, null, null, null);
        direccion.setEstado("Nuevo Estado");
        assertEquals("Nuevo Estado", direccion.getEstado());
    }

    @Test
    void setCodigoPostal_y_getCodigoPostal() {
        Direccion direccion = new Direccion(null, null, null, null);
        direccion.setCodigoPostal("54321");
        assertEquals("54321", direccion.getCodigoPostal());
    }

    @Test
    void setPais_y_getPais() {
        Direccion direccion = new Direccion(null, null, null, null);
        direccion.setPais("Nuevo País");
        assertEquals("Nuevo País", direccion.getPais());
    }
}
