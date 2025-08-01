package Logico;
import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identificador; //RNC
	private byte[] imagen;
	private String nombre;
	private String pais;
	private String sector;
	private ArrayList <Vacante> misFormulariosEmpresa;
	
	public Empresa(String identificador, byte[] imagen, String nombre, String pais, String sector) {
		super();
		this.identificador = identificador;
		this.setImagen(imagen);
		this.nombre = nombre;
		this.pais = pais;
		this.sector = sector;
		this.misFormulariosEmpresa = new ArrayList<>();
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public ArrayList<Vacante> getMisFormulariosEmpresa() {
		return misFormulariosEmpresa;
	}

	public void setMisFormulariosEmpresa(ArrayList<Vacante> misFormulariosEmpresa) {
		this.misFormulariosEmpresa = misFormulariosEmpresa;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}
