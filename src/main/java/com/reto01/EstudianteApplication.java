package com.reto01;

import com.reto01.domain.port.in.EstudianteUseCase;
import com.reto01.domain.port.out.EstudianteRepositoryPort;
import com.reto01.domain.service.EstudianteServiceImpl;
// No es necesario importar InMemoryEstudianteRepositoryAdapter aquí si la configuración es por tipo
// y la anotación @Repository es suficiente, lo cual debería ser el caso.
// Spring inyectará la instancia de InMemoryEstudianteRepositoryAdapter
// donde se requiera un EstudianteRepositoryPort.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // Asegúrate de esta importación

@SpringBootApplication // Esta anotación ya incluye @ComponentScan para los subpaquetes
public class EstudianteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudianteApplication.class, args);
    }

    @Bean
    public EstudianteUseCase estudianteUseCase(EstudianteRepositoryPort estudianteRepositoryPort) {
        return new EstudianteServiceImpl(estudianteRepositoryPort);
    }

    // No es necesario definir un Bean para InMemoryEstudianteRepositoryAdapter explícitamente aquí
    // si ya está anotado con @Repository y es la única implementación de EstudianteRepositoryPort.
    // Spring lo detectará y lo usará para inyectar en el bean estudianteUseCase.
}