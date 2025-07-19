package Logico;

import java.util.Date;

public class Obrero extends Candidato {
	private String[] misHabilidades;

	public Obrero(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento, String telefono,
			String correo, String nacionalidad, String[] misHabilidades) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad);
		this.misHabilidades = misHabilidades;
	}

	public String[] getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(String[] misHabilidades) {
		this.misHabilidades = misHabilidades;
	}
	
}
