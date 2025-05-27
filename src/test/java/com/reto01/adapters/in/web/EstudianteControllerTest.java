package com.reto01.adapters.in.web;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.in.EstudianteUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteUseCase estudianteUseCase;

    // Constructor de Estudiante: Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, double promedioNotas, String listadoAsignaturas, String seminariosTomados)
    private Estudiante estudiante1 = new Estudiante("001", "Ana", "111222", "ana@test.com", 8.5, "Mat", "IA");
    private Estudiante estudiante2 = new Estudiante("002", "Luis", "333444", "luis@test.com", 9.0, "Fis", "BD");

    @Test
    void listarEstudiantes_debeDevolverListaDeEstudiantes() throws Exception {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        listaEstudiantes.add(estudiante1);
        listaEstudiantes.add(estudiante2);

        when(estudianteUseCase.listarEstudiantes()).thenReturn(listaEstudiantes);

        mockMvc.perform(get("/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Ana")))
                .andExpect(jsonPath("$[1].nombre", is("Luis")));

        verify(estudianteUseCase, times(1)).listarEstudiantes();
    }

    @Test
    void filtrarPorNombre_cuandoNombreValido_debeDevolverEstudiantesFiltrados() throws Exception {
        List<Estudiante> listaFiltrada = Collections.singletonList(estudiante1);
        String nombreFiltro = "Ana";

        when(estudianteUseCase.filtrarPorNombre(nombreFiltro)).thenReturn(listaFiltrada);

        mockMvc.perform(get("/estudiantes/nombre").param("nombre", nombreFiltro))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is(nombreFiltro)));

        verify(estudianteUseCase, times(1)).filtrarPorNombre(nombreFiltro);
    }
    
    @Test
    void filtrarPorNombre_cuandoNombreNoEncontrado_debeDevolverListaVacia() throws Exception {
        String nombreFiltro = "Inexistente";
        when(estudianteUseCase.filtrarPorNombre(nombreFiltro)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/estudiantes/nombre").param("nombre", nombreFiltro))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(estudianteUseCase, times(1)).filtrarPorNombre(nombreFiltro);
    }

    @Test
    void filtrarPorNumeroCelular_cuandoCelularValido_debeDevolverEstudiantesFiltrados() throws Exception {
        List<Estudiante> listaFiltrada = Collections.singletonList(estudiante2);
        String celularFiltro = "333444";

        when(estudianteUseCase.filtrarPorNumeroCelular(celularFiltro)).thenReturn(listaFiltrada);

        mockMvc.perform(get("/estudiantes/celular").param("numeroCelular", celularFiltro))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numeroCelular", is(celularFiltro)));

        verify(estudianteUseCase, times(1)).filtrarPorNumeroCelular(celularFiltro);
    }
    
    @Test
    void filtrarPorNumeroCelular_cuandoCelularNoEncontrado_debeDevolverListaVacia() throws Exception {
        String celularFiltro = "000000";
        when(estudianteUseCase.filtrarPorNumeroCelular(celularFiltro)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/estudiantes/celular").param("numeroCelular", celularFiltro))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        
        verify(estudianteUseCase, times(1)).filtrarPorNumeroCelular(celularFiltro);
    }

    @Test
    void ordenarPorPromedioNotas_debeDevolverEstudiantesOrdenados() throws Exception {
        List<Estudiante> listaOrdenada = new ArrayList<>();
        listaOrdenada.add(estudiante2); // Luis 9.0
        listaOrdenada.add(estudiante1); // Ana 8.5

        when(estudianteUseCase.ordenarPorPromedioNotas()).thenReturn(listaOrdenada);

        mockMvc.perform(get("/estudiantes/ordenar-promedio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Luis")))
                .andExpect(jsonPath("$[1].nombre", is("Ana")));

        verify(estudianteUseCase, times(1)).ordenarPorPromedioNotas();
    }
}
