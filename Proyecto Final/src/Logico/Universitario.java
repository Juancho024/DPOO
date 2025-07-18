package Logico;

import java.util.Date;

public class Universitario extends Candidato {
	private String carreraGraduada;

	public Universitario(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento,
			String telefono, String correo, String nacionalidad, String carreraGraduada) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad);
		this.carreraGraduada = carreraGraduada;
	}

	public String getCarreraGraduada() {
		return carreraGraduada;
	}

	public void setCarreraGraduada(String carreraGraduada) {
		this.carreraGraduada = carreraGraduada;
	}
	
	
}
