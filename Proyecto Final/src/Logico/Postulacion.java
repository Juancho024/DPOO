package Logico;

import java.util.ArrayList;

public class Postulacion {
	private String identificador;
	private String nivelEstudio;
	private String[] infoEstudio;
	private String tipoContrato;
	private String paisResidencia;
	private String ciudadResidencia;
	private boolean mudanza;
	private boolean disponibilidadVehiculo;
	private boolean licencia;
	private float pretensionSalarial;
	private ArrayList<Candidato> misPersonas;
	
	public Postulacion(String identificador, String tipoEstudio, String[] infoEstudio, String tipoContrato,
			String paisResidencia, String ciudadResidencia, boolean mudanza, boolean disponibilidadVehiculo,
			boolean licencia, float pretensionSalarial) {
		super();
		this.identificador = identificador;
		this.nivelEstudio = tipoEstudio;
		this.infoEstudio = infoEstudio;
		this.tipoContrato = tipoContrato;
		this.paisResidencia = paisResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.mudanza = mudanza;
		this.disponibilidadVehiculo = disponibilidadVehiculo;
		this.licencia = licencia;
		this.pretensionSalarial = pretensionSalarial;
		this.misPersonas = new ArrayList<>();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipoEstudio() {
		return nivelEstudio;
	}

	public void setTipoEstudio(String tipoEstudio) {
		this.nivelEstudio = tipoEstudio;
	}

	public String[] getInfoEstudio() {
		return infoEstudio;
	}

	public void setInfoEstudio(String[] infoEstudio) {
		this.infoEstudio = infoEstudio;
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

	public ArrayList<Candidato> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Candidato> misPersonas) {
		this.misPersonas = misPersonas;
	}
	
	
	
}
