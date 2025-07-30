package Logico;

import java.io.Serializable;

public class Postulacion implements Serializable{
	/**
	 * */
	private static final long serialVersionUID = -1726739163634744025L;
	private String identificador;
	private String cedulaCliente;
	private String nivelEstudio; // 2pnt 1pnt
	private String detalleNivelEstudio; // Nuevo atributo para guardar la carrera, area o habilidades
	private int aniosExperiencia; // Nuevo atributo para guardar a�os de experiencia para tecnicos
	private String tipoContrato;	// 2punt  1pnt
	private String paisResidencia;	// 1punt
	private String ciudadResidencia; // 1punt
	private boolean mudanza; // 2punt 1punt
	private boolean disponibilidadVehiculo; // 3punt 1punt
	private boolean licencia; // 1punt
	private float pretensionSalarial; //3pnt
	private boolean status;
	
	public Postulacion(String identificador, String cedulaCliente, String nivelEstudio, String detalleNivelEstudio, int aniosExperiencia, String tipoContrato,
			String paisResidencia, String ciudadResidencia, boolean mudanza, boolean disponibilidadVehiculo,
			boolean licencia, float pretensionSalarial, boolean status) {
		super();
		this.identificador = identificador;
		this.cedulaCliente = cedulaCliente;
		this.nivelEstudio = nivelEstudio;
		this.detalleNivelEstudio = detalleNivelEstudio; // Inicializaci�n del nuevo atributo
		this.aniosExperiencia = aniosExperiencia;     // Inicializaci�n del nuevo atributo
		this.tipoContrato = tipoContrato;
		this.paisResidencia = paisResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.mudanza = mudanza;
		this.disponibilidadVehiculo = disponibilidadVehiculo;
		this.licencia = licencia;
		this.pretensionSalarial = pretensionSalarial;
		this.status = status; // Este atributo estaba en el constructor pero no se asignaba
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getNivelEstudio() {
		return nivelEstudio;
	}

	public void setNivelEstudio(String nivelEstudio) {
		this.nivelEstudio = nivelEstudio;
	}

	// Nuevos getters y setters para detalleNivelEstudio
	public String getDetalleNivelEstudio() {
		return detalleNivelEstudio;
	}

	public void setDetalleNivelEstudio(String detalleNivelEstudio) {
		this.detalleNivelEstudio = detalleNivelEstudio;
	}

	// Nuevos getters y setters para aniosExperiencia
	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}