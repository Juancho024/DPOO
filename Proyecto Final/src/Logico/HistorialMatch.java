package Logico;

import java.io.Serializable;
import java.util.Date;

public class HistorialMatch implements Serializable{

	private static final long serialVersionUID = -420727446504518465L;
	private Vacante vacanteEmpleada;
	private Postulacion postulacionEmpleada;
	private Date fechaContratacion;
	
	public HistorialMatch(Vacante vacanteEmpleada, Postulacion postulacionEmpleada) {
		super();
		this.vacanteEmpleada = vacanteEmpleada;
		this.postulacionEmpleada = postulacionEmpleada;
		this.fechaContratacion = new Date();
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

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	

}
