package Logico;

import java.io.Serializable;

public class HistorialMatch implements Serializable{

	private static final long serialVersionUID = -420727446504518465L;
	private Vacante vacanteEmpleada;
	private Postulacion postulacionEmpleada;
	
	public HistorialMatch(Vacante vacanteEmpleada, Postulacion postulacionEmpleada) {
		this.vacanteEmpleada = vacanteEmpleada;
		this.postulacionEmpleada = postulacionEmpleada;
	}
	
	public Vacante getVacanteEmpleada() {
		return vacanteEmpleada;
	}
	public void setVacanteEmpleada(Vacante vacanteEmpleada) {
		this.vacanteEmpleada = vacanteEmpleada;
	}
	public Postulacion getPostulacionEmpleada() {
		return postulacionEmpleada;
	}
	public void setPostulacionEmpleada(Postulacion postulacionEmpleada) {
		this.postulacionEmpleada = postulacionEmpleada;
	} 
	
	

}
