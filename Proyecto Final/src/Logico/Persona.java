package Logico;
import java.util.ArrayList;

public abstract class Persona {
	protected String identificador;
	protected String nombre;
	protected String apellido;
	protected char sexo;
	protected String telefono;
	protected String correo;
	protected String nacionalidad;
	private ArrayList<FormularioPersona> misFormulariosPersona;

	public Persona(String identificador, String nombre, String apellido, char sexo, String telefono, String correo,
			String nacionalidad) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.correo = correo;
		this.nacionalidad = nacionalidad;
		this.misFormulariosPersona = new ArrayList<>();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public ArrayList<FormularioPersona> getMisFormulariosPersona() {
		return misFormulariosPersona;
	}

	public void setMisFormulariosPersona(ArrayList<FormularioPersona> misFormulariosPersona) {
		this.misFormulariosPersona = misFormulariosPersona;
	}
	
	
}
