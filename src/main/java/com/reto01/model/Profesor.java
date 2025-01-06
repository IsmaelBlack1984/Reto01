package com.reto01.model;

import java.util.List;

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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<String> getMateriasDictadas() {
		return materiasDictadas;
	}

	public void setMateriasDictadas(List<String> materiasDictadas) {
		this.materiasDictadas = materiasDictadas;
	}

	public List<String> getSeminarios() {
		return seminarios;
	}

	public void setSeminarios(List<String> seminarios) {
		this.seminarios = seminarios;
	}
}