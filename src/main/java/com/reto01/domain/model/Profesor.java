package com.reto01.domain.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Profesor extends Persona {
	private double salario;
	private List<String> materiasDictadas;
	private List<String> seminarios;

	// Constructor
	public Profesor(String nombre, String numeroCelular, String correoElectronico, boolean comprarPaseEstacionamiento,
			Direccion direccion, double salario, List<String> materiasDictadas, List<String> seminarios) {
		super(nombre, numeroCelular, correoElectronico, comprarPaseEstacionamiento, direccion);
		this.salario = salario;
		this.materiasDictadas = materiasDictadas;
		this.seminarios = seminarios;
	}
}