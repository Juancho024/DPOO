package Logico;

import java.util.ArrayList;

public class Bolsa {
	private static Bolsa instance = null;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Candidato> misCandidatos;
	private ArrayList<Postulacion> misPostulaciones;
	private ArrayList<Vacante> misVacantes;

	private Bolsa() {
		misEmpresas = new ArrayList<>();
		misCandidatos = new ArrayList<>();
		misPostulaciones = new ArrayList<>();
		misVacantes = new ArrayList<>();
	}
	public boolean validarCorreo(String correo) {
		int contArroba = 0, contPunto = 0;
		int posArroba = -1;
		int posPunto = -1;

		for (int i = 0; i < correo.length(); i++) {
			char c = correo.charAt(i);
			if (c == '@') {
				posArroba = i;
				contArroba++;
			} else if (c == '.') {
				posPunto = i;
				contPunto++;
			}
		}

		// No encontró @ o .
		if (posArroba == -1 || posPunto == -1)
			return false;

		// Solo debe haber un @ y un .
		if (contArroba != 1 || contPunto != 1)
			return false;

		// El @ debe estar antes que el .
		if (posArroba > posPunto)
			return false;

		int longitudDominio = posPunto - posArroba - 1;
		int longitudSufijo = correo.length() - posPunto - 1;

		if (longitudDominio < 3 || longitudSufijo < 2 || longitudSufijo > 3)
			return false;

		return true;
	}

	public static Bolsa getInstance() {
		if (instance == null) {
			instance = new Bolsa();
		}
		return instance;
	}

	// M�todos para registrar
	public void registrarEmpresa(Empresa empresa) {
		misEmpresas.add(empresa);
	}

	public void registrarCandidato(Candidato candidato) {
		misCandidatos.add(candidato);
	}

	// Resto de getters y setters
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

	public Empresa buscarEmpresaByCod(String rnc) {
		Empresa aux = null;
		int i = 0;
		boolean encontrado = false;
		while(!encontrado && i < misEmpresas.size()) {
			if(misEmpresas.get(i).getIdentificador().equals(rnc)) {
				aux = misEmpresas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Candidato buscarCandidatoByCod(String cedula) {
		Candidato aux = null;
		int i = 0;
		boolean encontrado = false;
		while(!encontrado && i < misCandidatos.size()) {
			if(misCandidatos.get(i).getCedula().equals(cedula)) {
				aux = misCandidatos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public void ordenarMatch(ArrayList<PorcentajeMatch>porcentajeMatch) {
		PorcentajeMatch temp;

		for (int i = 1; i < porcentajeMatch.size(); i++) {
			for (int j = 0; j < porcentajeMatch.size() - i; j++) {
				if(porcentajeMatch.get(j).getPunts() < porcentajeMatch.get(j + 1).getPunts()) {
					temp = porcentajeMatch.get(j);
					porcentajeMatch.set(j, porcentajeMatch.get(j + 1));
					porcentajeMatch.set(j + 1, temp);   			
				}
			}
		}
	}

	public ArrayList<PorcentajeMatch>match(Vacante auxVac) {
		int cont = 0;
		PorcentajeMatch auxMatch;
		ArrayList<PorcentajeMatch>porcentajeMatch = new ArrayList<>();

		for (Postulacion auxPostulacion : misPostulaciones) {

			if(auxPostulacion.getNivelEstudio().equalsIgnoreCase(auxVac.getNivelEstudio())) {
				cont += 2;
			}else cont += 1;

			if(auxPostulacion.getTipoContrato().equalsIgnoreCase(auxVac.getTipoContrato())) {
				cont += 2;
			}else cont += 1;

			if(auxPostulacion.getPaisResidencia().equalsIgnoreCase(auxVac.getPaisResidencia())) {
				cont += 1;
			}

			if(auxPostulacion.getCiudadResidencia().equalsIgnoreCase(auxVac.getCiudadResidencia())) {
				cont += 1;
			}

			if(auxPostulacion.isMudanza() == auxVac.isMudanza()) {
				cont += 2;
			}else cont += 1;

			if(auxPostulacion.isDisponibilidadVehiculo() == auxVac.isDisponibilidadVehiculo()) {
				cont += 3;
			}else cont += 1;

			if(auxPostulacion.isLicencia() == auxVac.isLicencia()) {
				cont += 1;
			}

			if(auxPostulacion.getPretensionSalarial() <= auxVac.getPretensionSalarial()) {
				cont += 3;
			}else cont += 1;

			auxMatch = new PorcentajeMatch(auxPostulacion.getIdentificador(), cont);
			porcentajeMatch.add(auxMatch);

			cont = 0;
		}

		ordenarMatch(porcentajeMatch);
		return porcentajeMatch;
	}
	public boolean validarExistenciaRNC(String text) {
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misEmpresas.size()) {
			if(misEmpresas.get(i).getIdentificador().equals(text)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	public boolean validarExistenciaCedula(String text) {
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misCandidatos.size()) {
			if(misCandidatos.get(i).getCedula().equals(text)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	public Postulacion buscarPostulacionByCode(String cedula) {
		Postulacion aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misPostulaciones.size()) {
			if(misPostulaciones.get(i).getIdentificador().equals(cedula)) {
				aux = misPostulaciones.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Vacante buscarVacanteByCode(String rnc) {
		Vacante aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misVacantes.size()) {
			if(misVacantes.get(i).getIdentificador().equals(rnc)) {
				aux = misVacantes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

}
