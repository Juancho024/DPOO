package Logico;

public class TecnicoSuperior extends Persona {
	private String areaEspecialidad;
	private int anyoExperiencia;
	
	public TecnicoSuperior(String identificador, String nombre, String apellido, char sexo, String telefono,
			String correo, String nacionalidad, String areaEspecialidad, int anyoExperiencia) {
		super(identificador, nombre, apellido, sexo, telefono, correo, nacionalidad);
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
