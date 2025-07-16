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
    
    public static Bolsa getInstance() {
        if (instance == null) {
            instance = new Bolsa();
        }
        return instance;
    }
    
    // Métodos para registrar
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
}