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
    
    public void ordenarMacht(ArrayList<PorcentajeMacht>porcentajeMacht) {
    	PorcentajeMacht temp;
    	
    	for (int i = 1; i < porcentajeMacht.size(); i++) {
    		for (int j = 0; j < porcentajeMacht.size() - i; j++) {
    			if(porcentajeMacht.get(j).getPunts() < porcentajeMacht.get(j + 1).getPunts()) {
    				temp = porcentajeMacht.get(j);
    				porcentajeMacht.set(j, porcentajeMacht.get(j + 1));
    				porcentajeMacht.set(j + 1, temp);   			}
    		}
		}
    	
    	
    }
    
    public ArrayList<PorcentajeMacht>macht(Vacante auxVac) {
    	int cont = 0;
    	PorcentajeMacht auxMacht;
    	ArrayList<PorcentajeMacht>porcentajeMacht = new ArrayList<>();
    	
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
    		
			auxMacht = new PorcentajeMacht(auxPostulacion.getIdentificador(), cont);
			porcentajeMacht.add(auxMacht);
			
			cont = 0;
		}
    	
    	ordenarMacht(porcentajeMacht);
    	return porcentajeMacht;
    }
    
}
