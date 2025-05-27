package com.reto01.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends Persona {

    private String numeroEstudiante;
    private double promedioNotas;
    private String listadoAsignaturas;
    private String seminariosTomados;

    // Constructor
    public Estudiante(String numeroEstudiante, String nombre, String numeroCelular, String correoElectronico, 
                      double promedioNotas, String listadoAsignaturas, String seminariosTomados) {

        super(nombre, numeroCelular, correoElectronico, false, null); 
        this.numeroEstudiante = numeroEstudiante;
        this.promedioNotas = promedioNotas;
        this.listadoAsignaturas = listadoAsignaturas;
        this.seminariosTomados = seminariosTomados;
    }
}