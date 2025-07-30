package Logico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Candidato implements Serializable{
	private static final long serialVersionUID = 5229150145884640197L;
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected char sexo;
	protected Date fechaNacimiento;
	protected String telefono;
	protected String correo;
	protected String nacionalidad;
	protected boolean status;
	protected byte[] imagen;
	private ArrayList<Postulacion> misPostulaciones;
	
	public Candidato(String cedula, String nombre, String apellido, char sexo, Date fechaNacimiento,
			String telefono, String correo, String nacionalidad, byte[] imagen) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.correo = correo;
		this.nacionalidad = nacionalidad;
		this.imagen = imagen;
		this.misPostulaciones = new ArrayList<>();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public ArrayList<Postulacion> getMisPostulaciones() {
		return misPostulaciones;
	}

	public void setMisPostulaciones(ArrayList<Postulacion> misPostulaciones) {
		this.misPostulaciones = misPostulaciones;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
}
