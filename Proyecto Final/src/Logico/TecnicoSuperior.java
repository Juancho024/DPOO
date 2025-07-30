package Logico;

import java.io.Serializable;
import java.util.Date;

public class TecnicoSuperior extends Candidato implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4367947349365974318L;
	private String areaEspecialidad;
	private int anyoExperiencia;

	public TecnicoSuperior(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento,
			String telefono, String correo, String nacionalidad, byte[] imagen, String areaEspecialidad,
			int anyoExperiencia) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad, imagen);
		this.areaEspecialidad = areaEspecialidad;
		this.anyoExperiencia = anyoExperiencia;
	}

	public String getAreaEspecialidad() {
		return areaEspecialidad;
	}

	public void setAreaEspecialidad(String areaEspecialidad) {
		this.areaEspecialidad = areaEspecialidad;
	}

	public int getAnyoExperiencia() {
		return anyoExperiencia;
	}

	public void setAnyoExperiencia(int anyoExperiencia) {
		this.anyoExperiencia = anyoExperiencia;
	}
	
	
}
