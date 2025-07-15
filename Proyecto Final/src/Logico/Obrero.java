package Logico;
import java.util.ArrayList;

public class Obrero extends Candidato {
	private ArrayList<String> misHabilidades;

	public Obrero(String identificador, String nombre, String apellido, char sexo, String telefono, String correo,
			String nacionalidad, ArrayList<String> misHabilidades) {
		super(identificador, nombre, apellido, sexo, telefono, correo, nacionalidad);
		this.misHabilidades = misHabilidades;
	}

	public ArrayList<String> getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(ArrayList<String> misHabilidades) {
		this.misHabilidades = misHabilidades;
	}
	
}
