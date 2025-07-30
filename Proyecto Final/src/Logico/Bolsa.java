package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Bolsa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Bolsa instance = null;
	private ArrayList<User> misUsers;
	private static User loginUser;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Candidato> misCandidatos;
	private ArrayList<Postulacion> misPostulaciones;
	private ArrayList<Vacante> misVacantes;
	private ArrayList<PorcentajeMatch> misPorcentajesMatches;
	private ArrayList<DatosMatch> misDatosMatches;
	public static int genCodVac = 0;
	public static int genCodPost = 0;

	private Bolsa() {
		misUsers = new ArrayList<>();
		misEmpresas = new ArrayList<>();
		misCandidatos = new ArrayList<>();
		misPostulaciones = new ArrayList<>();
		misVacantes = new ArrayList<>();
		misPorcentajesMatches = new ArrayList<>();
		misDatosMatches = new ArrayList<>();
	}
	
	// Método para generar el código de Postulacion
	public String generarCodigoPostulacion() {
		genCodPost++;
		return String.format("Post - %02d", genCodPost);
	}
	
	//Validar correo, tengo que corregir
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

	// Metodos para registrar
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
	//Actualizar Match de las Postulaciones
	public void actualizarMatchPorPostulacion(Postulacion aux) {
		for(Vacante auxV: misVacantes) {
			if(auxV.isStatus() == true) {
				int puntos = contarPuntosMatch(aux, auxV);
				
				PorcentajeMatch VacMatch = buscarMatchByVacante(auxV.getIdentificador());
				ArrayList<DatosMatch> misDatosMatch = new ArrayList<>();
				if(VacMatch != null) { //si es diferente de null, ya estaba guarda en un match
					for(int i = 0; i < 3; i++) {
						if(!VacMatch.getMis3Postulaciones()[i].isEmpty()) { //saber si queda espacio
							misDatosMatch.add(new DatosMatch(VacMatch.getMis3Postulaciones()[i], VacMatch.getPuntos()[i]));
						}
					}
				}
				
				boolean encontrado = false;
				int i = 0;
				while(!encontrado && i < misDatosMatches.size()) {
					if(misDatosMatches.get(i).getCedula().equals(aux.getCedulaCliente())) {
						misDatosMatches.get(i).setPuntos(puntos);
						encontrado = true;
					}
					i++;
				}
				
				if(!encontrado) {
					misDatosMatch.add(new DatosMatch(aux.getCedulaCliente(), puntos));
				}
				misDatosMatch.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));
				String[] cedulaM = new String[3];
				int[] puntosM = new int[3];
				float[] porcentajeM = new float[3];
				
				for(int j = 0; j < 3; j++) {
					if(j < misDatosMatch.size()) {
						cedulaM[j] = misDatosMatch.get(j).getCedula();
						puntosM[j] = misDatosMatch.get(j).getPuntos();
						porcentajeM[j] = ((float) puntosM[j] / 15f) * 100f;
					} else {
						cedulaM[j] = "";
						puntosM[j] = 0;
						porcentajeM[j] = 0.00f;
					}
				}
				PorcentajeMatch newMatch = new PorcentajeMatch(auxV, cedulaM, puntosM, porcentajeM);
				eliminarMatchGuardadoVacante(auxV.getIdentificador());
				misPorcentajesMatches.add(newMatch);
				
			}
		}
	}
	public ArrayList<PorcentajeMatch> getMisPorcentajesMatches() {
		return misPorcentajesMatches;
	}

	public void setMisPorcentajesMatches(ArrayList<PorcentajeMatch> misPorcentajesMatches) {
		this.misPorcentajesMatches = misPorcentajesMatches;
	}
	
	//Revisar
	private PorcentajeMatch buscarMatchByVacante(String identificador) {
	    PorcentajeMatch aux = null;
	    boolean encontrado = false;
	    int i = 0;

	    while (!encontrado && i < misPorcentajesMatches.size()) {
	        PorcentajeMatch match = misPorcentajesMatches.get(i);
	        if (match != null && match.getMisVacantes() != null && match.getMisVacantes().getIdentificador() != null) {
	            if (match.getMisVacantes().getIdentificador().equals(identificador)) {
	                aux = match;
	                encontrado = true;
	            }
	        }
	        i++;
	    }

	    return aux;
	}

	//Actualizar Match de las Vacantes 
	private void actualizarMatchPorVacante(Vacante aux) {
		ArrayList<DatosMatch> misDatosMatch = new ArrayList<>();
		for(Postulacion auxP: misPostulaciones) {
			if(auxP.isStatus() == true) {
				int puntos = contarPuntosMatch(auxP, aux);
				misDatosMatch.add(new DatosMatch(auxP.getCedulaCliente(), puntos));
			}
		}
		
		misDatosMatch.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));
		String[] cedula = new String[3];
		int[] puntos = new int[3];
		float[] porcentaje = new float[3];
		
		for(int i = 0; i < 3; i++) {
			if(i < misDatosMatch.size()) {
				cedula[i] = misDatosMatch.get(i).getCedula();
				puntos[i] = misDatosMatch.get(i).getPuntos();
				porcentaje[i] = ((float) puntos[i] / 15f) * 100f;
			} else {
				cedula[i] = "";
				puntos[i] = 0;
				porcentaje[i] = 0.00f;
			}
		}
		PorcentajeMatch newMatch = new PorcentajeMatch(aux, cedula, puntos, porcentaje);
		eliminarMatchGuardadoVacante(aux.getIdentificador());
		misPorcentajesMatches.add(newMatch);
	}
	
	private void eliminarMatchGuardadoVacante(String identificador) {
		PorcentajeMatch match = buscarMatchByVacante(identificador);
		if (match != null) {
			misPorcentajesMatches.remove(match);
		}
	}
	

	public int contarPuntosMatch(Postulacion auxP, Vacante auxV) {
		int totalPuntos = 0;
		if(auxP.getNivelEstudio().equalsIgnoreCase(auxV.getNivelEstudio())) {
			totalPuntos += 2;
		} else {
			totalPuntos += 1;
		}
		if(auxP.getTipoContrato().equalsIgnoreCase(auxV.getTipoContrato())) {
			totalPuntos += 2;
		} else {
			totalPuntos += 1;
		}
		if(auxP.getPaisResidencia().equalsIgnoreCase(auxV.getPaisResidencia()))
		{
			totalPuntos += 1;
		}
		if(auxP.getCiudadResidencia().equalsIgnoreCase(auxV.getCiudadResidencia())) {
			totalPuntos += 1;
		}
		if(auxP.isMudanza() == auxV.isMudanza()) {
			totalPuntos += 2;
		} else {
			totalPuntos += 1;
		}
		if(auxP.isDisponibilidadVehiculo() == auxV.isDisponibilidadVehiculo()) {
			totalPuntos += 2;
		} else {
			totalPuntos += 1;
		}
		if(auxP.isLicencia() == auxV.isLicencia()) {
			totalPuntos += 1;
		}
		if(auxP.getPretensionSalarial() <= auxV.getPretensionSalarial()) {
			totalPuntos += 3;
		}
		return totalPuntos;
	}
	
	//Buscar postulacion
	public Postulacion buscarPostulacionByCode(String identificador) {
		Postulacion aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misPostulaciones.size()) {
			if(misPostulaciones.get(i).getIdentificador().equals(identificador)) {
				aux = misPostulaciones.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public Postulacion buscarPostulacionById(String id) {
	    for (Postulacion p : misPostulaciones) {
	        if (p.getIdentificador().equalsIgnoreCase(id)) {
	            return p;
	        }
	    }
	    return null;
	}
	
	// En la clase Bolsa.java
	public void eliminarPostulacion(Postulacion p) {
		genCodPost--;
	    misPostulaciones.remove(p);
	}
	
	//Buscar vacantes
	public Vacante buscarVacanteByCode(String identificador) {
		Vacante aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misVacantes.size()) {
			if(misVacantes.get(i).getIdentificador().equals(identificador)) {
				aux = misVacantes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	// Buscar si una Candidato tiene una postulacion 
	public boolean buscarCandidatoInPostulacion(String cedula) {
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misPostulaciones.size()) {
			if(misPostulaciones.get(i).getCedulaCliente().equals(cedula)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	
	// Buscar si una empresa tiene una vacante abierta
	public boolean buscarEmpresaInVacante(String rnc) {
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misVacantes.size()) {
			if(misVacantes.get(i).getRncEmpresa().equals(rnc) && misVacantes.get(i).isStatus() == true) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}
	
	//Modificar Empresa
	public void modificarEmpresa(Empresa selected) {
		// TODO Auto-generated method stub
		int index = buscarIndexEmpresaByCode(selected.getIdentificador());
		misEmpresas.set(index, selected);
	}
	private int buscarIndexEmpresaByCode(String identificador) {
		boolean encontrado = false;
		int aux = -1;
		int i = 0;
		while(!encontrado && i < misEmpresas.size()) {
			if(misEmpresas.get(i).getIdentificador().equals(identificador)) {
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	//Modificar Candidatos
	public void modificarCandidatos(Candidato selected) {
		// TODO Auto-generated method stub
		int index = buscarIndexCandidatoByCode(selected.getCedula());
		misCandidatos.set(index, selected);
	}
	private int buscarIndexCandidatoByCode(String cedula) {
		boolean encontrado = false;
		int aux = -1;
		int i = 0;
		while(!encontrado && i < misCandidatos.size()) {
			if(misCandidatos.get(i).getCedula().equals(cedula)) {
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public static void setControl(Bolsa temp) {
		instance = temp;
	}
	
	//Registrar Usuario
	public void registrarUser(User aux) {
		misUsers.add(aux);
	}
	public ArrayList<User> getMisUsers() {
		return misUsers;
	}
	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}
	
	//Confirmar la existencia del Usuario
	public boolean confirmUser(String text, String password) {
		// TODO Auto-generated method stub
		boolean login = false;
		for(User aux: misUsers) {
			if(aux.getUserName().equals(text) && aux.getPassword().equals(password)) {
				loginUser = aux;
				login = true;
			}
		}
		return login;
	}
	
	public static User getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(User loginUser) {
		Bolsa.loginUser = loginUser;
	}
	
	//Buscar Usuario
	public User buscarUserByUser(String user) {
		User aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misUsers.size()) {
			if(misUsers.get(i).getUserName().equals(user)) {
				aux = misUsers.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	//Funciones para modificar Usuarios
	public void modificarUser(User selected) {
		// TODO Auto-generated method stub
		int index = buscarIndexUserByCod(selected.getUserName());
		misUsers.set(index, selected);
	}
	private int buscarIndexUserByCod(String userName) {
		boolean encontrado = false;
		int i = 0;
		int aux = -1;
		while(!encontrado && i < misUsers.size()) {
			if(misUsers.get(i).getUserName().equals(userName)) {
				aux = i;
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public void eliminarCandidato(Candidato selected) {
		// TODO Auto-generated method stub
		misCandidatos.remove(selected);
	}

	public void eliminarEmpresa(Empresa selected) {
		// TODO Auto-generated method stub
		misEmpresas.remove(selected);
	}
	
	public void eliminar(Empresa selected) {
		// TODO Auto-generated method stub
		misEmpresas.remove(selected);
	}
	
	public void eliminarVacante(Vacante vacante) {
	    if (vacante != null) {
	    	genCodVac--;
	        misVacantes.remove(vacante);
	    }
	}
	
	public void modificarVacante(Vacante selected) {
	    int index = buscarIndexVacanteById(selected.getIdentificador());
	    if (index != -1) {
	        misVacantes.set(index, selected);
	    }
	}
	
	private int buscarIndexVacanteById(String identificador) {
	    for (int i = 0; i < misVacantes.size(); i++) {
	        if (misVacantes.get(i).getIdentificador().equalsIgnoreCase(identificador)) {
	            return i;
	        }
	    }
	    return -1; 
	}
	
	//Eliminar Users y su validacion de existir por lo menos uno
	public boolean validarEliminarUser(String userName) {
		int admin = contarAdmins();
		User aux = buscarUserByUser(userName);
		boolean encontrado = false;
		if(aux != null) {
			if(aux.getUserName().equals(userName) && aux.getTipoUser().equalsIgnoreCase("Administrador")) {
				if(admin <= 1) {
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
	
	public int contarAdmins() {
		int cont = 0;
		for(User aux: misUsers) {
			if(aux.getTipoUser().equalsIgnoreCase("Administrador")) {
				cont++;
			}
		}
		return cont;
	}
	public void eliminarUser(User selected) {
		misUsers.remove(selected);
	}

	//Registrar Vacante 
	public void registrarVacante(Vacante vacante) {
		// TODO Auto-generated method stub
		misVacantes.add(vacante);
		actualizarMatchPorVacante(vacante);
		genCodVac++;
	}
	
	

}
