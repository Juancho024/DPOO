package Logico;
import java.util.ArrayList;

public class Empresa {
	private String identificador;
	private String nombre;
	private String pais;
	private String sector;
	private ArrayList <FormularioEmpresa> misFormulariosEmpresa;
	
	public Empresa(String identificador, String nombre, String pais, String sector) {
		super();
		this.identificador = identificador;
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

	public ArrayList<FormularioEmpresa> getMisFormulariosEmpresa() {
		return misFormulariosEmpresa;
	}

	public void setMisFormulariosEmpresa(ArrayList<FormularioEmpresa> misFormulariosEmpresa) {
		this.misFormulariosEmpresa = misFormulariosEmpresa;
	}
	
	
}
