package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacante implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8961884375339861710L;
	private String identificador;
	private String rncEmpresa;
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
	
	public Vacante(String identificador, String rncEmpresa, String nivelEstudio, String tipoContrato,
			String nombreVacante, String paisResidencia, String ciudadResidencia, boolean mudanza,
			boolean disponibilidadVehiculo, boolean licencia, float pretensionSalarial, boolean status) {
		super();
		this.identificador = identificador;
		this.rncEmpresa = rncEmpresa;
		this.nivelEstudio = nivelEstudio;
		this.tipoContrato = tipoContrato;
		this.nombreVacante = nombreVacante;
		this.paisResidencia = paisResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.mudanza = mudanza;
		this.disponibilidadVehiculo = disponibilidadVehiculo;
		this.licencia = licencia;
		this.pretensionSalarial = pretensionSalarial;
		this.status = status;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getRncEmpresa() {
		return rncEmpresa;
	}

	public void setRncEmpresa(String rncEmpresa) {
		this.rncEmpresa = rncEmpresa;
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

	public String getNombreVacante() {
		return nombreVacante;
	}

	public void setNombreVacante(String nombreVacante) {
		this.nombreVacante = nombreVacante;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
