package com.reto01.adapters.out.persistence;

import com.reto01.domain.model.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryEstudianteRepositoryAdapterTest {

    private InMemoryEstudianteRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new InMemoryEstudianteRepositoryAdapter();
    }

    @Test
    void constructor_debeInicializarListaDeEstudiantes() {
        // El constructor llama a inicializarEstudiantes()
        // Verificamos que la lista no sea nula y tenga el tamaño esperado
        List<Estudiante> estudiantes = repositoryAdapter.getEstudiantes();
        assertNotNull(estudiantes);
        assertEquals(10, estudiantes.size(), "El repositorio debería inicializarse con 10 estudiantes.");

        // Opcional: Verificar algún dato específico para asegurar que la inicialización ocurrió como se esperaba
        // Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, double promedioNotas, String listadoAsignaturas, String seminariosTomados)
        Estudiante primerEstudiante = estudiantes.get(0);
        assertEquals("123", primerEstudiante.getNumeroEstudiante());
        assertEquals("Juan Pérez", primerEstudiante.getNombre());
        assertEquals(9.5, primerEstudiante.getPromedioNotas());
    }

    @Test
    void getEstudiantes_debeDevolverListaInicializada() {
        List<Estudiante> estudiantes = repositoryAdapter.getEstudiantes();
        assertNotNull(estudiantes);
        assertEquals(10, estudiantes.size());

        // Adicionalmente, podemos verificar que dos llamadas a getEstudiantes devuelven la misma instancia de la lista
        // (ya que el adaptador la mantiene en memoria)
        List<Estudiante> estudiantesOtraVez = repositoryAdapter.getEstudiantes();
        assertSame(estudiantes, estudiantesOtraVez, "getEstudiantes debería devolver la misma instancia de la lista cada vez.");
    }
}
