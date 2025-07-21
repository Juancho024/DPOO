package Logico;

import java.util.ArrayList;

public class Vacante {
	private String rnc;
	private String nivelEstudio;
	private String tipoContrato;
	private String nombreVacante;
	private String paisResidencia;
	private String ciudadResidencia;
	private boolean mudanza;
	private boolean disponibilidadVehiculo;
	private boolean licencia;
	private float pretensionSalarial;
	protected boolean status;
	
	public Vacante(String identificador, String nivelEstudio, String tipoContrato,String nombreVacante,
			String paisResidencia, String ciudadResidencia, boolean mudanza, boolean disponibilidadVehiculo,
			boolean licencia, float pretensionSalarial) {
		super();
		this.rnc = identificador;
		this.nivelEstudio = nivelEstudio;
		this.tipoContrato = tipoContrato;
		this.nombreVacante = nombreVacante;
		this.paisResidencia = paisResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.mudanza = mudanza;
		this.disponibilidadVehiculo = disponibilidadVehiculo;
		this.licencia = licencia;
		this.pretensionSalarial = pretensionSalarial;
	}

	public String getIdentificador() {
		return rnc;
	}

	public void setIdentificador(String identificador) {
		this.rnc = identificador;
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

	public String getNombreVacante() {
		return nombreVacante;
	}

	public void setNombreVacante(String nombreVacante) {
		this.nombreVacante = nombreVacante;
	}

}
