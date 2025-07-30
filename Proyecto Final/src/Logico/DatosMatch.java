package Logico;

import java.io.Serializable;

public class DatosMatch implements Serializable{
	private static final long serialVersionUID = -1077160881655662197L;
	private String cedula;
	private int puntos;
	
	public DatosMatch(String cedula, int puntos) {
		super();
		this.cedula = cedula;
		this.puntos = puntos;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
