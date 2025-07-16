package Logico;

import java.util.ArrayList;

public class Bolsa {
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Candidato> misCandidatos;
	private ArrayList<Postulacion> misPostulaciones;
	private ArrayList<Vacante> misVacantes;
	
	public Bolsa(ArrayList<Empresa> misEmpresas, ArrayList<Candidato> misCandidatos,
			ArrayList<Postulacion> misPostulaciones, ArrayList<Vacante> misVacantes) {
		this.misEmpresas = misEmpresas;
		this.misCandidatos = misCandidatos;
		this.misPostulaciones = misPostulaciones;
		this.misVacantes = misVacantes;
	}
	public ArrayList<Empresa> getMisEmpresas() {
		return misEmpresas;
	}
	public void setMisEmpresas(ArrayList<Empresa> misEmpresas) {
		this.misEmpresas = misEmpresas;
	}
	public ArrayList<Candidato> getMisCandidatos() {
		return misCandidatos;
	}
	public void setMisCandidatos(ArrayList<Candidato> misCandidatos) {
		this.misCandidatos = misCandidatos;
	}
	public ArrayList<Postulacion> getMisPostulaciones() {
		return misPostulaciones;
	}
	public void setMisPostulaciones(ArrayList<Postulacion> misPostulaciones) {
		this.misPostulaciones = misPostulaciones;
	}
	public ArrayList<Vacante> getMisVacantes() {
		return misVacantes;
	}
	public void setMisVacantes(ArrayList<Vacante> misVacantes) {
		this.misVacantes = misVacantes;
	}
	
	
	
}
