package Logico;
public class Universitario extends Persona {
	private String carreraGraduada;

	
	public Universitario(String identificador, String nombre, String apellido, char sexo, String telefono,
			String correo, String nacionalidad, String carreraGraduada) {
		super(identificador, nombre, apellido, sexo, telefono, correo, nacionalidad);
		this.carreraGraduada = carreraGraduada;
	}

	public String getCarreraGraduada() {
		return carreraGraduada;
	}

	public void setCarreraGraduada(String carreraGraduada) {
		this.carreraGraduada = carreraGraduada;
	}
	
	
}
