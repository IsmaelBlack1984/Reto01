package com.reto01.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Persona {
    private String nombre;
    private String numeroCelular;
    private String correoElectronico;
    private boolean comprarPaseEstacionamiento;
    private Direccion direccion;
}