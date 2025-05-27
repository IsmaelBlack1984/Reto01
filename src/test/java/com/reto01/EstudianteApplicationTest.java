package com.reto01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EstudianteApplicationTest {

    @Autowired
    private ApplicationContext applicationContext; // Para verificar que el contexto carga

    @Test
    void main_shouldExecuteApplication() {
        // Simplemente llamamos al main. SpringBootTest se encarga del contexto.
        // No es necesario capturar excepciones a menos que esperemos una específica.
        // Si la aplicación falla al iniciar, la prueba fallará.
        EstudianteApplication.main(new String[]{});
    }

    @Test
    void constructor_andContext_shouldLoad() {
        // La anotación @SpringBootTest ya se encarga de instanciar la aplicación
        // y cargar el contexto.
        // Podemos verificar que el contexto no es nulo.
        assertNotNull(applicationContext, "El contexto de la aplicación no debería ser nulo.");
        
        // Adicionalmente, podemos obtener el bean de la aplicación para asegurar que se creó
        EstudianteApplication appBean = applicationContext.getBean(EstudianteApplication.class);
        assertNotNull(appBean, "El bean de EstudianteApplication no debería ser nulo.");
    }
}
