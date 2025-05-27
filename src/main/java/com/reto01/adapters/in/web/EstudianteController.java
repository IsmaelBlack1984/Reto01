package com.reto01.adapters.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reto01.domain.model.Estudiante;
import com.reto01.domain.port.in.EstudianteUseCase;

import java.util.List;

@RestController
public class EstudianteController {

    private final EstudianteUseCase estudianteUseCase;

    public EstudianteController(EstudianteUseCase estudianteUseCase) {
        this.estudianteUseCase = estudianteUseCase;
    }


    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes() {
        return estudianteUseCase.listarEstudiantes();
    }


    @GetMapping("/estudiantes/nombre")
    public List<Estudiante> filtrarPorNombre(@RequestParam String nombre) {
        return estudianteUseCase.filtrarPorNombre(nombre);
    }


    @GetMapping("/estudiantes/celular")
    public List<Estudiante> filtrarPorNumeroCelular(@RequestParam String numeroCelular) {
        return estudianteUseCase.filtrarPorNumeroCelular(numeroCelular);
    }


    @GetMapping("/estudiantes/ordenar-promedio")
    public List<Estudiante> ordenarPorPromedioNotas() {
        return estudianteUseCase.ordenarPorPromedioNotas();
    }
}