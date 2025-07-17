package Logico;

import java.util.ArrayList;

public class Postulacion {
	private String cedula;
	private String nivelEstudio; // 2pnt 1pnt
	private String tipoContrato;	// 2punt  1pnt
	private String paisResidencia;	// 1punt
	private String ciudadResidencia; // 1punt
	private boolean mudanza; // 2punt 1punt
	private boolean disponibilidadVehiculo; // 3punt 1punt
	private boolean licencia; // 1punt
	private float pretensionSalarial; //3pnt
	
	public Postulacion(String identificador, String tipoEstudio, String tipoContrato,
			String paisResidencia, String ciudadResidencia, boolean mudanza, boolean disponibilidadVehiculo,
			boolean licencia, float pretensionSalarial) {
		super();
		this.cedula = identificador;
		this.nivelEstudio = tipoEstudio;
		this.tipoContrato = tipoContrato;
		this.paisResidencia = paisResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.mudanza = mudanza;
		this.disponibilidadVehiculo = disponibilidadVehiculo;
		this.licencia = licencia;
		this.pretensionSalarial = pretensionSalarial;
	}

	public String getIdentificador() {
		return cedula;
	}

	public void setIdentificador(String identificador) {
		this.cedula = identificador;
	}

	public String getNivelEstudio() {
		return nivelEstudio;
	}

	public void setNivelEstudio(String nivelEstudio) {
		this.nivelEstudio = nivelEstudio;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getPaisResidencia() {
		return paisResidencia;
	}

	public void setPaisResidencia(String paisResidencia) {
		this.paisResidencia = paisResidencia;
	}

	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	public boolean isMudanza() {
		return mudanza;
	}

	public void setMudanza(boolean mudanza) {
		this.mudanza = mudanza;
	}

	public boolean isDisponibilidadVehiculo() {
		return disponibilidadVehiculo;
	}

	public void setDisponibilidadVehiculo(boolean disponibilidadVehiculo) {
		this.disponibilidadVehiculo = disponibilidadVehiculo;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public float getPretensionSalarial() {
		return pretensionSalarial;
	}

	public void setPretensionSalarial(float pretensionSalarial) {
		this.pretensionSalarial = pretensionSalarial;
	}

}
