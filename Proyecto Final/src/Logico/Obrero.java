package Logico;
import java.util.ArrayList;
import java.util.Date;

public class Obrero extends Candidato {
	private ArrayList<String> misHabilidades;

	public Obrero(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento, String telefono,
			String correo, String nacionalidad, ArrayList<String> misHabilidades) {
		super(cedula, nombre, apellido, sexo, fechaNacimiento, telefono, correo, nacionalidad);
		this.misHabilidades = misHabilidades;
	}

	public ArrayList<String> getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(ArrayList<String> misHabilidades) {
		this.misHabilidades = misHabilidades;
	}
	
}
