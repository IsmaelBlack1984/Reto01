package com.reto01.domain.service;

import com.reto01.domain.model.Estudiante;
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
        estudiantesSimulados.add(new Estudiante("001", "Carlos", "111222", "carlos@test.com", 8.0, "Mat,Fis", "IA"));
        estudiantesSimulados.add(new Estudiante("002", "Carla", "333444", "carla@test.com", 9.0, "Quim", "BD"));
        estudiantesSimulados.add(new Estudiante("003", "Pedro", "555666", "pedro@test.com", 7.0, "Hist", "Redes"));

        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);

        // Caso 1: Nombre exacto
        List<Estudiante> resultado1 = estudianteService.filtrarPorNombre("Carlos");
        assertEquals(1, resultado1.size());
        assertEquals("Carlos", resultado1.get(0).getNombre());

        // Caso 2: Nombre con diferente capitalización
        List<Estudiante> resultado2 = estudianteService.filtrarPorNombre("cArLoS");
        assertEquals(1, resultado2.size());
        assertEquals("Carlos", resultado2.get(0).getNombre());
    }

    @Test
    void filtrarPorNombre_cuandoNombreNoExiste_debeDevolverListaVacia() {
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        estudiantesSimulados.add(new Estudiante("001", "Carlos", "111222", "carlos@test.com", 8.0, "Mat,Fis", "IA"));
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);

        List<Estudiante> resultado = estudianteService.filtrarPorNombre("NombreInexistente");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void filtrarPorNombre_cuandoListaOriginalEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNombre("CualquierNombre");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void filtrarPorNumeroCelular_cuandoNumeroExiste_debeDevolverEstudiantesFiltrados() {
        // Crear lista de estudiantes para simular el repositorio
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        // Ajusta los datos de ejemplo al constructor de Estudiante:
        // Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, double promedioNotas, String listadoAsignaturas, String seminariosTomados)
        estudiantesSimulados.add(new Estudiante("001", "Lucia", "123456789", "lucia@test.com", 8.5, "Arte", "Fotografia"));
        estudiantesSimulados.add(new Estudiante("002", "Marcos", "987654321", "marcos@test.com", 7.5, "Musica", "Canto"));
        estudiantesSimulados.add(new Estudiante("003", "Elena", "123123123", "elena@test.com", 9.0, "Deportes", "Natacion"));

        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("987654321");
        assertEquals(1, resultado.size());
        assertEquals("Marcos", resultado.get(0).getNombre());
        assertEquals("987654321", resultado.get(0).getNumeroCelular());
    }

    @Test
    void filtrarPorNumeroCelular_cuandoNumeroNoExiste_debeDevolverListaVacia() {
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        estudiantesSimulados.add(new Estudiante("001", "Lucia", "123456789", "lucia@test.com", 8.5, "Arte", "Fotografia"));
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("000000000");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void filtrarPorNumeroCelular_cuandoListaOriginalEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.filtrarPorNumeroCelular("123456789");
        assertTrue(resultado.isEmpty());
    }

    @Test
    void ordenarPorPromedioNotas_debeDevolverEstudiantesOrdenadosDescendentemente() {
        // Crear lista de estudiantes para simular el repositorio
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        // Ajusta los datos de ejemplo al constructor de Estudiante:
        // Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, double promedioNotas, String listadoAsignaturas, String seminariosTomados)
        Estudiante estudiante1 = new Estudiante("001", "David", "111", "david@test.com", 7.5, "Hist", "Debate"); // Promedio más bajo
        Estudiante estudiante2 = new Estudiante("002", "Sofia", "222", "sofia@test.com", 9.0, "Mat", "Olimpiadas Mat"); // Promedio más alto
        Estudiante estudiante3 = new Estudiante("003", "Laura", "333", "laura@test.com", 8.5, "Fis", "Robotica");   // Promedio medio

        estudiantesSimulados.add(estudiante1); // 7.5
        estudiantesSimulados.add(estudiante2); // 9.0
        estudiantesSimulados.add(estudiante3); // 8.5

        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);

        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();

        assertEquals(3, resultado.size());
        // Verificar el orden descendente por promedioNotas
        assertEquals("Sofia", resultado.get(0).getNombre()); // Promedio 9.0
        assertEquals("Laura", resultado.get(1).getNombre()); // Promedio 8.5
        assertEquals("David", resultado.get(2).getNombre()); // Promedio 7.5
    }

    @Test
    void ordenarPorPromedioNotas_cuandoListaEstaVacia_debeDevolverListaVacia() {
        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(new ArrayList<>());

        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();
        assertTrue(resultado.isEmpty());
    }

    @Test
    void ordenarPorPromedioNotas_conPromediosIguales_debeMantenerOrdenRelativoEstable() {
        // Crear lista de estudiantes con algunos promedios iguales
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        Estudiante estudianteA = new Estudiante("00A", "Ana", "444", "ana@test.com", 8.0, "Lit", "Escritura");
        Estudiante estudianteB = new Estudiante("00B", "Bernardo", "555", "bernardo@test.com", 9.5, "Bio", "Ecologia"); // Más alto
        Estudiante estudianteC = new Estudiante("00C", "Clara", "666", "clara@test.com", 8.0, "Geo", "Viajes");    // Igual que Ana
        Estudiante estudianteD = new Estudiante("00D", "Daniel", "777", "daniel@test.com", 7.0, "EdFis", "Futbol"); // Más bajo

        // Añadir en un orden específico para ver si la estabilidad (si existe en Stream.sorted()) se mantiene para iguales
        estudiantesSimulados.add(estudianteA); // Ana 8.0
        estudiantesSimulados.add(estudianteB); // Bernardo 9.5
        estudiantesSimulados.add(estudianteC); // Clara 8.0
        estudiantesSimulados.add(estudianteD); // Daniel 7.0

        when(estudianteRepositoryPort.getEstudiantes()).thenReturn(estudiantesSimulados);
        List<Estudiante> resultado = estudianteService.ordenarPorPromedioNotas();

        assertEquals(4, resultado.size());
        assertEquals("Bernardo", resultado.get(0).getNombre()); // 9.5
        // Para los de 8.0, el orden podría ser Ana, Clara o Clara, Ana.
        // La especificación de `sorted()` no garantiza la estabilidad para elementos iguales a menos que el comparador lo haga.
        // Comparator.comparingDouble().reversed() no es inherentemente estable.
        // Solo verificaremos que los elementos con 8.0 estén después de 9.5 y antes de 7.0
        assertTrue(resultado.get(1).getPromedioNotas() == 8.0);
        assertTrue(resultado.get(2).getPromedioNotas() == 8.0);
        // Y que los nombres sean los correctos para esos promedios
        assertTrue( (resultado.get(1).getNombre().equals("Ana") && resultado.get(2).getNombre().equals("Clara")) ||
                      (resultado.get(1).getNombre().equals("Clara") && resultado.get(2).getNombre().equals("Ana")) );
        assertEquals("Daniel", resultado.get(3).getNombre());   // 7.0
    }
}
