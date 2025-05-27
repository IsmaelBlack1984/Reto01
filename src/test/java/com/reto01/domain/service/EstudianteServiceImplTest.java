package com.reto01.domain.service;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.specification.Specification; // Nueva importación
import com.reto01.domain.specification.estudiante.NombreEqualsSpecification; // Nueva importación
// import static org.mockito.ArgumentMatchers.any; // any() no se usa directamente en los nuevos tests, pero es bueno tenerlo
import com.reto01.domain.port.out.EstudianteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceImplTest {

    @Mock
    private EstudianteRepositoryPort estudianteRepositoryPort;

    private EstudianteServiceImpl estudianteService;

    @BeforeEach
    void setUp() {
        estudianteService = new EstudianteServiceImpl(estudianteRepositoryPort);
    }

    // Aquí se agregarán los métodos de prueba más adelante

    @Test
    void buscarEstudiantesPorCriterio_conEspecificacionNombre_debeLlamarAlRepositorioYDevolverResultado() {
        // Arrange
        String nombreBusqueda = "Laura";
        Specification<Estudiante> spec = new NombreEqualsSpecification(nombreBusqueda);
        
        List<Estudiante> estudiantesEsperados = new ArrayList<>();
        estudiantesEsperados.add(new Estudiante("001", "Laura", "123", "laura@test.com", 8.0, "Arte", "Pintura"));
        
        when(estudianteRepositoryPort.find(spec)).thenReturn(estudiantesEsperados);

        // Act
        List<Estudiante> resultado = estudianteService.buscarEstudiantesPorCriterio(spec);

        // Assert
        assertEquals(estudiantesEsperados, resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laura", resultado.get(0).getNombre());
        verify(estudianteRepositoryPort, times(1)).find(spec);
    }

    @Test
    void buscarEstudiantesPorCriterio_conEspecificacionNula_debeLlamarAlRepositorioYDevolverResultado() {
        // Arrange
        Specification<Estudiante> spec = null; // O una especificación que el repo trate como "todos"
        
        List<Estudiante> todosLosEstudiantes = new ArrayList<>();
        todosLosEstudiantes.add(new Estudiante("001", "Laura", "123", "laura@test.com", 8.0, "Arte", "Pintura"));
        todosLosEstudiantes.add(new Estudiante("002", "Carlos", "456", "carlos@test.com", 9.0, "Musica", "Guitarra"));

        // Asumimos que el puerto del repositorio (y su mock) devolverá todos los estudiantes si la spec es null,
        // basándonos en la implementación de InMemoryEstudianteRepositoryAdapter.find(null)
        when(estudianteRepositoryPort.find(null)).thenReturn(todosLosEstudiantes);

        // Act
        List<Estudiante> resultado = estudianteService.buscarEstudiantesPorCriterio(spec);

        // Assert
        assertEquals(todosLosEstudiantes, resultado);
        assertEquals(2, resultado.size());
        verify(estudianteRepositoryPort, times(1)).find(null);
    }

    @Test
    void buscarEstudiantesPorCriterio_conEspecificacionQueNoEncuentraNada_debeDevolverListaVacia() {
        // Arrange
        String nombreBusqueda = "NombreInexistente";
        Specification<Estudiante> spec = new NombreEqualsSpecification(nombreBusqueda);
        
        List<Estudiante> listaVacia = new ArrayList<>();
        
        when(estudianteRepositoryPort.find(spec)).thenReturn(listaVacia);

        // Act
        List<Estudiante> resultado = estudianteService.buscarEstudiantesPorCriterio(spec);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).find(spec);
    }

    @Test
    void listarEstudiantes_debeLlamarAlRepositorio() {
        // Configurar el mock para devolver una lista vacía (o cualquier lista)
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(new ArrayList<>());

        // Llamar al método que se está probando
        estudianteService.listarEstudiantes();

        // Verificar que el método getEstudiantes() del repositorio fue llamado una vez
        verify(estudianteRepositoryPort, times(1)).getEstudiantes();
    }

    @Test
    void listarEstudiantes_debeDevolverListaDelRepositorio() {
        // Crear una lista de estudiantes esperada
        List<Estudiante> estudiantesEsperados = new ArrayList<>();
        // Ajustar los datos de ejemplo para que coincidan con el constructor de Estudiante
        // Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, 
        // double promedioNotas, String listadoAsignaturas, String seminariosTomados)
        estudiantesEsperados.add(new Estudiante("10", "Juan Perez", "12345", "juan@test.com", 7.5, "Matematicas, Fisica", "Seminario IA"));
        estudiantesEsperados.add(new Estudiante("11", "Ana Gomez", "67890", "ana@test.com", 8.5, "Quimica, Biologia", "Seminario Robotica"));

        // Configurar el mock para devolver la lista esperada
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesEsperados);

        // Llamar al método que se está probando
        List<Estudiante> estudiantesActuales = estudianteService.listarEstudiantes();

        // Verificar que la lista devuelta es la misma que la del repositorio
        assertEquals(estudiantesEsperados, estudiantesActuales);
        assertEquals(2, estudiantesActuales.size()); // Opcional: verificar el tamaño
    }

    @Test
    void filtrarPorNombre_cuandoNombreExiste_debeDevolverEstudiantesFiltrados() {
        // Crear lista de estudiantes para simular el repositorio
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        // Ajusta los datos de ejemplo al constructor de Estudiante:
        // Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, double promedioNotas, String listadoAsignaturas, String seminariosTomados)
        Estudiante carlos = new Estudiante("001", "Carlos", "111222", "carlos@test.com", 8.0, "Mat,Fis", "IA");
        List<Estudiante> listaFiltradaCarlos = List.of(carlos);

        when(estudianteRepositoryPort.findByNombre("Carlos")).thenReturn(listaFiltradaCarlos);
        when(estudianteRepositoryPort.findByNombre("cArLoS")).thenReturn(listaFiltradaCarlos);

        // Caso 1: Nombre exacto
        List<Estudiante> resultado1 = estudianteService.filtrarPorNombre("Carlos");
        assertEquals(1, resultado1.size());
        assertEquals("Carlos", resultado1.get(0).getNombre());
        verify(estudianteRepositoryPort, times(1)).findByNombre("Carlos");

        // Caso 2: Nombre con diferente capitalización
        List<Estudiante> resultado2 = estudianteService.filtrarPorNombre("cArLoS");
        assertEquals(1, resultado2.size());
        assertEquals("Carlos", resultado2.get(0).getNombre());
        verify(estudianteRepositoryPort, times(1)).findByNombre("cArLoS");
    }

    @Test
    void filtrarPorNombre_cuandoNombreNoExiste_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.findByNombre("NombreInexistente")).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNombre("NombreInexistente");
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).findByNombre("NombreInexistente");
    }

    @Test
    void filtrarPorNombre_cuandoListaOriginalEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.findByNombre(anyString())).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNombre("CualquierNombre");
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).findByNombre("CualquierNombre");
    }

    @Test
    void filtrarPorNumeroCelular_cuandoNumeroExiste_debeDevolverEstudiantesFiltrados() {
        Estudiante marcos = new Estudiante("002", "Marcos", "987654321", "marcos@test.com", 7.5, "Musica", "Canto");
        List<Estudiante> listaFiltradaMarcos = List.of(marcos);
        when(estudianteRepositoryPort.findByNumeroCelular("987654321")).thenReturn(listaFiltradaMarcos);

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("987654321");
        assertEquals(1, resultado.size());
        assertEquals("Marcos", resultado.get(0).getNombre());
        assertEquals("987654321", resultado.get(0).getNumeroCelular());
        verify(estudianteRepositoryPort, times(1)).findByNumeroCelular("987654321");
    }

    @Test
    void filtrarPorNumeroCelular_cuandoNumeroNoExiste_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.findByNumeroCelular("000000000")).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("000000000");
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).findByNumeroCelular("000000000");
    }

    @Test
    void filtrarPorNumeroCelular_cuandoListaOriginalEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.findByNumeroCelular(anyString())).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("123456789");
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).findByNumeroCelular("123456789");
    }

    @Test
    void ordenarPorPromedioNotas_debeDevolverEstudiantesOrdenadosDescendentemente() {
        Estudiante estudiante1 = new Estudiante("001", "David", "111", "david@test.com", 7.5, "Hist", "Debate");
        Estudiante estudiante2 = new Estudiante("002", "Sofia", "222", "sofia@test.com", 9.0, "Mat", "Olimpiadas Mat");
        Estudiante estudiante3 = new Estudiante("003", "Laura", "333", "laura@test.com", 8.5, "Fis", "Robotica");

        List<Estudiante> estudiantesOrdenadosEsperados = new ArrayList<>();
        estudiantesOrdenadosEsperados.add(estudiante2); // Sofia 9.0
        estudiantesOrdenadosEsperados.add(estudiante3); // Laura 8.5
        estudiantesOrdenadosEsperados.add(estudiante1); // David 7.5

        when(estudianteRepositoryPort.findAllByOrderByPromedioNotasDesc()).thenReturn(estudiantesOrdenadosEsperados);

        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();

        assertEquals(3, resultado.size());
        assertEquals("Sofia", resultado.get(0).getNombre()); 
        assertEquals("Laura", resultado.get(1).getNombre()); 
        assertEquals("David", resultado.get(2).getNombre()); 
        verify(estudianteRepositoryPort, times(1)).findAllByOrderByPromedioNotasDesc();
    }

    @Test
    void ordenarPorPromedioNotas_cuandoListaEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.findAllByOrderByPromedioNotasDesc()).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();
        assertTrue(resultado.isEmpty());
        verify(estudianteRepositoryPort, times(1)).findAllByOrderByPromedioNotasDesc();
    }

    @Test
    void ordenarPorPromedioNotas_conPromediosIguales_debeMantenerOrdenRelativoEstable() {
        Estudiante estudianteA = new Estudiante("00A", "Ana", "444", "ana@test.com", 8.0, "Lit", "Escritura");
        Estudiante estudianteB = new Estudiante("00B", "Bernardo", "555", "bernardo@test.com", 9.5, "Bio", "Ecologia"); 
        Estudiante estudianteC = new Estudiante("00C", "Clara", "666", "clara@test.com", 8.0, "Geo", "Viajes");    
        Estudiante estudianteD = new Estudiante("00D", "Daniel", "777", "daniel@test.com", 7.0, "EdFis", "Futbol"); 

        List<Estudiante> estudiantesOrdenadosEsperados = new ArrayList<>();
        estudiantesOrdenadosEsperados.add(estudianteB); // Bernardo 9.5
        // Asumimos un orden estable para los iguales, por ejemplo, Ana antes que Clara si así se mockea
        estudiantesOrdenadosEsperados.add(estudianteA); // Ana 8.0 
        estudiantesOrdenadosEsperados.add(estudianteC); // Clara 8.0
        estudiantesOrdenadosEsperados.add(estudianteD); // Daniel 7.0
        
        when(estudianteRepositoryPort.findAllByOrderByPromedioNotasDesc()).thenReturn(estudiantesOrdenadosEsperados);
        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();

        assertEquals(4, resultado.size());
        assertEquals("Bernardo", resultado.get(0).getNombre()); 
        assertEquals("Ana", resultado.get(1).getNombre()); 
        assertEquals("Clara", resultado.get(2).getNombre()); 
        assertEquals("Daniel", resultado.get(3).getNombre());   
        verify(estudianteRepositoryPort, times(1)).findAllByOrderByPromedioNotasDesc();
    }
}
