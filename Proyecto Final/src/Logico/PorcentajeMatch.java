package Logico;

import java.io.Serializable;

public class PorcentajeMatch implements Serializable{

	private static final long serialVersionUID = 1590794034578801408L;
	private Vacante misVacantes;
	private String[] mis3Postulaciones;
	private int[] puntos;
	private float[] porcentaje;
	
	public PorcentajeMatch(Vacante misVacantes, String[] mis3Postulaciones, int[] puntos, float[] porcentaje) {
		super();
		this.misVacantes = misVacantes;
		this.mis3Postulaciones = mis3Postulaciones;
		this.puntos = puntos;
		this.porcentaje = porcentaje;
	}

	public Vacante getMisVacantes() {
		return misVacantes;
	}

	public void setMisVacantes(Vacante misVacantes) {
		this.misVacantes = misVacantes;
	}

	public String[] getMis3Postulaciones() {
		return mis3Postulaciones;
	}

	public void setMis3Postulaciones(String[] mis3Postulaciones) {
		this.mis3Postulaciones = mis3Postulaciones;
	}

	public int[] getPuntos() {
		return puntos;
	}

	public void setPuntos(int[] puntos) {
		this.puntos = puntos;
	}

	public float[] getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float[] porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
