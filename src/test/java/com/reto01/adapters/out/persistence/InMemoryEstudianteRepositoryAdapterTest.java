package com.reto01.adapters.out.persistence;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.specification.Specification;
import com.reto01.domain.specification.estudiante.NombreEqualsSpecification;
import com.reto01.domain.specification.estudiante.NumeroCelularEqualsSpecification;
import com.reto01.domain.specification.AndSpecification;
import com.reto01.domain.specification.OrSpecification;
import com.reto01.domain.specification.NotSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList; // Para crear listas mutables si es necesario

class InMemoryEstudianteRepositoryAdapterTest {

    private InMemoryEstudianteRepositoryAdapter adapter;
    private Estudiante estudianteJuan;
    private Estudiante estudianteAna;
    private Estudiante estudianteCarlos;

    @BeforeEach
    void setUp() {
        adapter = new InMemoryEstudianteRepositoryAdapter(); // Esto inicializará con datos de ejemplo
        // Obtener referencias a algunos estudiantes de la lista inicializada para facilitar las aserciones,
        // o definirlos aquí si se prefiere controlar totalmente los datos de prueba.
        // Por simplicidad, asumimos que conocemos algunos datos de inicializarEstudiantes()
        // Estudiante("123", "Juan Pérez", "123456789", "juan@email.com", 9.5, "Matemáticas", "Seminario IA")
        // Estudiante("124", "Ana Gómez", "987654321", "ana@email.com", 8.3, "Historia", "Seminario Física")
        // Estudiante("125", "Carlos Sánchez", "555555555", "carlos@email.com", 7.8, "Literatura", "Seminario Literatura")
        
        // Para un control más preciso de los datos de prueba, podríamos sobreescribir la lista:
        adapter = new InMemoryEstudianteRepositoryAdapter(); // Reinicia para control
        List<Estudiante> controlledList = new ArrayList<>();
        estudianteJuan = new Estudiante("123", "Juan Pérez", "123456789", "juan@email.com", 9.5, "Matemáticas", "Seminario IA");
        estudianteAna = new Estudiante("124", "Ana Gómez", "987654321", "ana@email.com", 8.3, "Historia", "Seminario Física");
        estudianteCarlos = new Estudiante("125", "Carlos Sánchez", "555555555", "carlos@email.com", 7.8, "Literatura", "Seminario Literatura");
        controlledList.add(estudianteJuan);
        controlledList.add(estudianteAna);
        controlledList.add(estudianteCarlos);
        // Como 'estudiantes' es privado en el adapter, no podemos setearlo directamente.
        // Por tanto, los tests dependerán de los datos de inicializarEstudiantes() o crearemos una nueva instancia
        // del adapter para cada test con datos específicos si fuera necesario, aunque para estos ejemplos,
        // usaremos los datos que ya tiene el adapter por defecto y filtraremos sobre ellos.
        // Re-instanciamos el adapter para asegurar que los datos son los de `inicializarEstudiantes()`
        adapter = new InMemoryEstudianteRepositoryAdapter(); 
    }

    @Test
    void find_conEspecificacionNull_debeDevolverTodosLosEstudiantes() {
        List<Estudiante> resultado = adapter.find(null);
        // InMemoryEstudianteRepositoryAdapter.inicializarEstudiantes() añade 10 estudiantes.
        assertEquals(10, resultado.size()); 
    }

    @Test
    void find_conNombreEqualsSpecification_encuentraCorrectamente() {
        Specification<Estudiante> spec = new NombreEqualsSpecification("Juan Pérez");
        List<Estudiante> resultado = adapter.find(spec);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
    }

    @Test
    void find_conNombreEqualsSpecification_noEncuentraSiNoExiste() {
        Specification<Estudiante> spec = new NombreEqualsSpecification("NombreInventado");
        List<Estudiante> resultado = adapter.find(spec);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void find_conNumeroCelularEqualsSpecification_encuentraCorrectamente() {
        Specification<Estudiante> spec = new NumeroCelularEqualsSpecification("987654321"); // Ana Gómez
        List<Estudiante> resultado = adapter.find(spec);
        assertEquals(1, resultado.size());
        assertEquals("Ana Gómez", resultado.get(0).getNombre());
    }

    @Test
    void find_conAndSpecification_encuentraCorrectamente() {
        // Buscar "Juan Pérez" con celular "123456789"
        Specification<Estudiante> nombreSpec = new NombreEqualsSpecification("Juan Pérez");
        Specification<Estudiante> celularSpec = new NumeroCelularEqualsSpecification("123456789");
        Specification<Estudiante> andSpec = new AndSpecification<>(nombreSpec, celularSpec);
        
        List<Estudiante> resultado = adapter.find(andSpec);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
    }

    @Test
    void find_conAndSpecification_noEncuentraSiUnaNoCumple() {
        Specification<Estudiante> nombreSpec = new NombreEqualsSpecification("Juan Pérez");
        Specification<Estudiante> celularSpecInvalido = new NumeroCelularEqualsSpecification("000000");
        Specification<Estudiante> andSpec = new AndSpecification<>(nombreSpec, celularSpecInvalido);
        
        List<Estudiante> resultado = adapter.find(andSpec);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void find_conOrSpecification_encuentraSiUnaCumple() {
        // Buscar "Juan Pérez" O celular "987654321" (Ana Gómez)
        Specification<Estudiante> nombreSpec = new NombreEqualsSpecification("Juan Pérez");
        Specification<Estudiante> celularSpec = new NumeroCelularEqualsSpecification("987654321");
        Specification<Estudiante> orSpec = new OrSpecification<>(nombreSpec, celularSpec);
        
        List<Estudiante> resultado = adapter.find(orSpec);
        assertEquals(2, resultado.size());
        // Verifica que ambos estudiantes estén presentes (el orden no está garantizado por defecto)
        assertTrue(resultado.stream().anyMatch(e -> e.getNombre().equals("Juan Pérez")));
        assertTrue(resultado.stream().anyMatch(e -> e.getNombre().equals("Ana Gómez")));
    }
    
    @Test
    void find_conNotSpecification_encuentraCorrectamente() {
        // Todos los estudiantes EXCEPTO "Juan Pérez"
        Specification<Estudiante> nombreSpec = new NombreEqualsSpecification("Juan Pérez");
        Specification<Estudiante> notSpec = new NotSpecification<>(nombreSpec);
        
        List<Estudiante> resultado = adapter.find(notSpec);
        assertEquals(9, resultado.size()); // Si hay 10 estudiantes en total
        assertFalse(resultado.stream().anyMatch(e -> e.getNombre().equals("Juan Pérez")));
    }
}
