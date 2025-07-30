package Logico;

import java.io.Serializable;
import java.util.Date;

public class Universitario extends Candidato implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1461563482807504157L;
	private String carreraGraduada;

	public Universitario(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento,
			String telefono, String correo, String nacionalidad, byte[] imagen, String carreraGraduada) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad, imagen);
		this.carreraGraduada = carreraGraduada;
	}

	public String getCarreraGraduada() {
		return carreraGraduada;
	}

	public void setCarreraGraduada(String carreraGraduada) {
		this.carreraGraduada = carreraGraduada;
	}
	
	
}
