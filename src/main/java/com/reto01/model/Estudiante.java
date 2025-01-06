package com.reto01.model;

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


    public String getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public void setNumeroEstudiante(String numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }

    public double getPromedioNotas() {
        return promedioNotas;
    }

    public void setPromedioNotas(double promedioNotas) {
        this.promedioNotas = promedioNotas;
    }

    public String getListadoAsignaturas() {
        return listadoAsignaturas;
    }

    public void setListadoAsignaturas(String listadoAsignaturas) {
        this.listadoAsignaturas = listadoAsignaturas;
    }

    public String getSeminariosTomados() {
        return seminariosTomados;
    }

    public void setSeminariosTomados(String seminariosTomados) {
        this.seminariosTomados = seminariosTomados;
    }
}