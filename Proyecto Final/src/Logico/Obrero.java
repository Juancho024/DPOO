package Logico;

import java.io.Serializable;
import java.util.Date;

public class Obrero extends Candidato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 611747791953002589L;
	private String[] misHabilidades;

	public Obrero(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento, String telefono,
			String correo, String nacionalidad, byte[] imagen, boolean status, String[] misHabilidades) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad, imagen, status);
		this.misHabilidades = misHabilidades;
	}

	public String[] getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(String[] misHabilidades) {
		this.misHabilidades = misHabilidades;
	}
	
}
