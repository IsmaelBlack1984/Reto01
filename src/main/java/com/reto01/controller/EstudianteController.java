package com.reto01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reto01.model.Estudiante;
import com.reto01.service.ServiceEstudiante;

import java.util.List;

@RestController
public class EstudianteController {

    private final ServiceEstudiante serviceEstudiante;

    @Autowired
    public EstudianteController(ServiceEstudiante serviceEstudiante) {
        this.serviceEstudiante = serviceEstudiante;
    }


    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes() {
        return serviceEstudiante.listarEstudiantes();
    }


    @GetMapping("/estudiantes/nombre")
    public List<Estudiante> filtrarPorNombre(@RequestParam String nombre) {
        return serviceEstudiante.filtrarPorNombre(nombre);
    }


    @GetMapping("/estudiantes/celular")
    public List<Estudiante> filtrarPorNumeroCelular(@RequestParam String numeroCelular) {
        return serviceEstudiante.filtrarPorNumeroCelular(numeroCelular);
    }


    @GetMapping("/estudiantes/ordenar-promedio")
    public List<Estudiante> ordenarPorPromedioNotas() {
        return serviceEstudiante.ordenarPorPromedioNotas();
    }
}