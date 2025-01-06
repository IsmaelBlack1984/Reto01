package com.reto01.model;

public class Persona {
    private String nombre;
    private String numeroCelular;
    private String correoElectronico;
    private boolean comprarPaseEstacionamiento;
    private Direccion direccion;

    // Constructor
    public Persona(String nombre, String numeroCelular, String correoElectronico,
                   boolean comprarPaseEstacionamiento, Direccion direccion) {
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.correoElectronico = correoElectronico;
        this.comprarPaseEstacionamiento = comprarPaseEstacionamiento;
        this.direccion = direccion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean isComprarPaseEstacionamiento() {
        return comprarPaseEstacionamiento;
    }

    public void setComprarPaseEstacionamiento(boolean comprarPaseEstacionamiento) {
        this.comprarPaseEstacionamiento = comprarPaseEstacionamiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}