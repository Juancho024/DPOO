package Logico;

public class PorcentajeMatch {
	
	private String cedula;
	private int punts;
	
	public PorcentajeMatch(String cedula, int punts) {
		this.cedula = cedula;
		this.punts = punts;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getPunts() {
		return punts;
	}
	public void setPunts(int punts) {
		this.punts = punts;
	}
}
