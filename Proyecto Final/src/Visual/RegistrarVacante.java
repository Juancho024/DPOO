package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Empresa;
import Logico.Vacante;

public class RegistrarVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JComboBox<String> cbxTipoContrato;
    private JTextField txtPaisResidencia;
    private JTextField txtCiudadResidencia;
    private JCheckBox chkMudanza;
    private JCheckBox chkDisponibilidadVehiculo;
    private JCheckBox chkLicencia;
    private JTextField txtPretensionSalarial;
    private JRadioButton rdbtnUniversitario;
    private JRadioButton rdbtnTecnicoSuperior;
    private JRadioButton rdbtnObrero;
    private JPanel panelUniversitario;
    private JPanel panelTecnicoSuperior;
    private JPanel panelObrero;
    private JComboBox<Empresa> cbxEmpresa;
    private JComboBox<String> cbxCarreraUniversitario;
    private JComboBox<String> cbxEspecialidadTecnico;
    private JCheckBox chkElectricidad;
    private JCheckBox chkSoldadura;
    private JCheckBox chkTecnicaPintura;
    private JCheckBox chkTuberias;
    private JCheckBox chkMantenimiento;
    private JCheckBox chkMaquinaria;

    public static void main(String[] args) {
        try {
            RegistrarVacante dialog = new RegistrarVacante();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrarVacante() {
        setTitle("Registrar Vacante");
        setBounds(100, 100, 600, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panelEmpresa = new JPanel();
        panelEmpresa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Empresa", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelEmpresa.setBounds(10, 11, 564, 70);
        contentPanel.add(panelEmpresa);
        panelEmpresa.setLayout(null);
        
        Label lblEmpresa = new Label("Empresa:");
        lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmpresa.setBounds(10, 30, 80, 22);
        panelEmpresa.add(lblEmpresa);
        
        cbxEmpresa = new JComboBox<>();
        cbxEmpresa.setBounds(100, 30, 450, 22);
        panelEmpresa.add(cbxEmpresa);
        
        JPanel panelVacante = new JPanel();
        panelVacante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Vacante", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelVacante.setBounds(10, 92, 564, 200);
        contentPanel.add(panelVacante);
        panelVacante.setLayout(null);
        
        Label lblIdentificador = new Label("Identificador:");
        lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 30, 100, 22);
        panelVacante.add(lblIdentificador);
        
        txtIdentificador = new JTextField();
        txtIdentificador.setBounds(120, 30, 200, 22);
        panelVacante.add(txtIdentificador);
        txtIdentificador.setColumns(10);
        
        Label lblTipoContrato = new Label("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 70, 120, 22);
        panelVacante.add(lblTipoContrato);
        
        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tiempo Completo", "Medio Tiempo", "Por Horas", "Por Proyecto"
        }));
        cbxTipoContrato.setBounds(140, 70, 200, 22);
        panelVacante.add(cbxTipoContrato);
        
        Label lblPais = new Label("Pa�s Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 110, 120, 22);
        panelVacante.add(lblPais);
        
        txtPaisResidencia = new JTextField();
        txtPaisResidencia.setBounds(140, 110, 200, 22);
        panelVacante.add(txtPaisResidencia);
        txtPaisResidencia.setColumns(10);
        
        Label lblCiudad = new Label("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 150, 120, 22);
        panelVacante.add(lblCiudad);
        
        txtCiudadResidencia = new JTextField();
        txtCiudadResidencia.setBounds(140, 150, 200, 22);
        panelVacante.add(txtCiudadResidencia);
        txtCiudadResidencia.setColumns(10);
        
        Label lblMudanza = new Label("Disponibilidad Mudanza:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(330, 30, 170, 22);
        panelVacante.add(lblMudanza);
        
        chkMudanza = new JCheckBox("");
        chkMudanza.setBounds(510, 30, 30, 22);
        panelVacante.add(chkMudanza);
        
        Label lblVehiculo = new Label("Veh�culo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(330, 70, 120, 22);
        panelVacante.add(lblVehiculo);
        
        chkDisponibilidadVehiculo = new JCheckBox("");
        chkDisponibilidadVehiculo.setBounds(460, 70, 30, 22);
        panelVacante.add(chkDisponibilidadVehiculo);
        
        Label lblLicencia = new Label("Licencia Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(330, 110, 120, 22);
        panelVacante.add(lblLicencia);
        
        chkLicencia = new JCheckBox("");
        chkLicencia.setBounds(460, 110, 30, 22);
        panelVacante.add(chkLicencia);
        
        Label lblSalario = new Label("Pretensi�n Salarial:");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(330, 150, 120, 22);
        panelVacante.add(lblSalario);
        
        txtPretensionSalarial = new JTextField();
        txtPretensionSalarial.setBounds(460, 150, 80, 22);
        panelVacante.add(txtPretensionSalarial);
        txtPretensionSalarial.setColumns(10);
        
        JPanel panelEstudios = new JPanel();
        panelEstudios.setBorder(new TitledBorder(null, "Nivel de Estudios Requerido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelEstudios.setBounds(10, 303, 564, 70);
        contentPanel.add(panelEstudios);
        panelEstudios.setLayout(null);
        
        rdbtnUniversitario = new JRadioButton("Universitario");
        rdbtnUniversitario.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnUniversitario.setBounds(20, 30, 120, 23);
        panelEstudios.add(rdbtnUniversitario);
        
        rdbtnTecnicoSuperior = new JRadioButton("T�cnico Superior");
        rdbtnTecnicoSuperior.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnTecnicoSuperior.setBounds(170, 30, 150, 23);
        panelEstudios.add(rdbtnTecnicoSuperior);
        
        rdbtnObrero = new JRadioButton("Obrero");
        rdbtnObrero.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnObrero.setBounds(350, 30, 100, 23);
        panelEstudios.add(rdbtnObrero);
        
        panelUniversitario = new JPanel();
        panelUniversitario.setBorder(new TitledBorder(null, "Detalles Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelUniversitario.setBounds(10, 384, 564, 70);
        contentPanel.add(panelUniversitario);
        panelUniversitario.setLayout(null);
        panelUniversitario.setVisible(false);
        
        Label lblCarrera = new Label("Carrera Requerida:");
        lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCarrera.setBounds(20, 30, 150, 22);
        panelUniversitario.add(lblCarrera);
        
        cbxCarreraUniversitario = new JComboBox<>();
        cbxCarreraUniversitario.setModel(new DefaultComboBoxModel<>(new String[] {
            "Administraci�n de Empresas", "Contabilidad", "Econom�a", "Marketing", "Finanzas", 
            "Psicolog�a", "Derecho", "Educaci�n / Pedagog�a", "Trabajo Social", 
            "Comunicaci�n Social / Periodismo", "Relaciones Internacionales", 
            "Ingenier�a en Sistemas / Inform�tica", "Ingenier�a Civil", "Ingenier�a Industrial", 
            "Ingenier�a El�ctrica / Electr�nica", "Medicina", "Enfermer�a", "Odontolog�a", 
            "Farmacia", "Nutrici�n", "Fisioterapia", "Veterinaria", "Ciencias de la Computaci�n", 
            "Desarrollo de Software", "Ciberseguridad", "Arquitectura", "Dise�o Gr�fico", 
            "Dise�o Industrial", "Turismo y Hoteler�a", "Gastronom�a / Artes Culinarias"
        }));
        cbxCarreraUniversitario.setBounds(180, 30, 350, 22);
        panelUniversitario.add(cbxCarreraUniversitario);
        
        panelTecnicoSuperior = new JPanel();
        panelTecnicoSuperior.setBorder(new TitledBorder(null, "Detalles T�cnico Superior", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTecnicoSuperior.setBounds(10, 384, 564, 70);
        contentPanel.add(panelTecnicoSuperior);
        panelTecnicoSuperior.setLayout(null);
        panelTecnicoSuperior.setVisible(false);
        
        Label lblEspecialidad = new Label("Especialidad Requerida:");
        lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEspecialidad.setBounds(20, 30, 180, 22);
        panelTecnicoSuperior.add(lblEspecialidad);
        
        cbxEspecialidadTecnico = new JComboBox<>();
        cbxEspecialidadTecnico.setModel(new DefaultComboBoxModel<>(new String[] {
            "T�cnico en Inform�tica", "T�cnico en Contabilidad", "T�cnico en Electr�nica", 
            "T�cnico en Electricidad", "T�cnico en Mec�nica Industrial", 
            "T�cnico en Refrigeraci�n y Climatizaci�n", "T�cnico en Enfermer�a", 
            "T�cnico en Farmacia", "T�cnico en An�lisis de Sistemas", 
            "T�cnico en Gesti�n Administrativa", "T�cnico en Desarrollo de Software", 
            "T�cnico en Redes", "T�cnico en Seguridad Industrial", 
            "T�cnico en Construcci�n Civil", "T�cnico en Dise�o Gr�fico", 
            "T�cnico en Producci�n Audiovisual", "T�cnico en Turismo", 
            "T�cnico en Gastronom�a", "T�cnico en Log�stica", 
            "T�cnico en Recursos Humanos", "T�cnico en Mercadeo", 
            "T�cnico en Agronom�a", "T�cnico en Educaci�n Inicial", 
            "T�cnico en Gesti�n Ambiental", "T�cnico en Mantenimiento Industrial"
        }));
        cbxEspecialidadTecnico.setBounds(210, 30, 320, 22);
        panelTecnicoSuperior.add(cbxEspecialidadTecnico);
        
        panelObrero = new JPanel();
        panelObrero.setBorder(new TitledBorder(null, "Habilidades Requeridas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelObrero.setBounds(10, 384, 564, 130);
        contentPanel.add(panelObrero);
        panelObrero.setLayout(null);
        panelObrero.setVisible(false);
        
        chkElectricidad = new JCheckBox("Electricidad b�sica");
        chkElectricidad.setBounds(20, 30, 200, 23);
        panelObrero.add(chkElectricidad);
        
        chkSoldadura = new JCheckBox("Soldadura");
        chkSoldadura.setBounds(20, 60, 200, 23);
        panelObrero.add(chkSoldadura);
        
        chkTecnicaPintura = new JCheckBox("T�cnicas de pintura o alba�iler�a");
        chkTecnicaPintura.setBounds(20, 90, 250, 23);
        panelObrero.add(chkTecnicaPintura);
        
        chkTuberias = new JCheckBox("Instalaci�n de tuber�as");
        chkTuberias.setBounds(300, 30, 250, 23);
        panelObrero.add(chkTuberias);
        
        chkMantenimiento = new JCheckBox("Mantenimiento b�sico de equipos");
        chkMantenimiento.setBounds(300, 60, 250, 23);
        panelObrero.add(chkMantenimiento);
        
        chkMaquinaria = new JCheckBox("Lectura de planos");
        chkMaquinaria.setBounds(300, 90, 250, 23);
        panelObrero.add(chkMaquinaria);
        
        // Listeners para los radio buttons
        rdbtnUniversitario.addActionListener(e -> mostrarPanelEstudio(panelUniversitario));
        rdbtnTecnicoSuperior.addActionListener(e -> mostrarPanelEstudio(panelTecnicoSuperior));
        rdbtnObrero.addActionListener(e -> mostrarPanelEstudio(panelObrero));
        
        // Cargar empresas
        cargarEmpresas();
        
        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarVacante());
        buttonPane.add(btnRegistrar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    private void mostrarPanelEstudio(JPanel panel) {
        panelUniversitario.setVisible(false);
        panelTecnicoSuperior.setVisible(false);
        panelObrero.setVisible(false);
        panel.setVisible(true);
    }
    
    private void cargarEmpresas() {
        cbxEmpresa.removeAllItems();
        for (Empresa empresa : Bolsa.getInstance().getMisEmpresas()) {
            cbxEmpresa.addItem(empresa);
        }
    }
    
    private void registrarVacante() {
        try {
            // Validar campos b�sicos
            String identificador = txtIdentificador.getText();
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            String pais = txtPaisResidencia.getText();
            String ciudad = txtCiudadResidencia.getText();
            String salarioStr = txtPretensionSalarial.getText();
            
            if (identificador.isEmpty() || pais.isEmpty() || ciudad.isEmpty() || salarioStr.isEmpty()) {
                throw new Exception("Complete todos los campos obligatorios");
            }
            
            // Validar empresa
            if (cbxEmpresa.getSelectedItem() == null) {
                throw new Exception("Seleccione una empresa");
            }
            
            // Validar nivel de estudio
            String nivelEstudio = "";
            String[] infoEstudio = new String[1];
            
            if (rdbtnUniversitario.isSelected()) {
                nivelEstudio = "Universitario";
                infoEstudio[0] = (String) cbxCarreraUniversitario.getSelectedItem();
            } 
            else if (rdbtnTecnicoSuperior.isSelected()) {
                nivelEstudio = "T�cnico Superior";
                infoEstudio[0] = (String) cbxEspecialidadTecnico.getSelectedItem();
            } 
            else if (rdbtnObrero.isSelected()) {
                nivelEstudio = "Obrero";
                ArrayList<String> habilidades = new ArrayList<>();
                if (chkElectricidad.isSelected()) habilidades.add("Electricidad b�sica");
                if (chkSoldadura.isSelected()) habilidades.add("Soldadura");
                if (chkTecnicaPintura.isSelected()) habilidades.add("T�cnicas de pintura");
                if (chkTuberias.isSelected()) habilidades.add("Instalaci�n de tuber�as");
                if (chkMantenimiento.isSelected()) habilidades.add("Mantenimiento b�sico");
                if (chkMaquinaria.isSelected()) habilidades.add("Lectura de planos");
                
                if (habilidades.isEmpty()) {
                    throw new Exception("Seleccione al menos una habilidad");
                }
                infoEstudio = habilidades.toArray(new String[0]);
            } 
            else {
                throw new Exception("Seleccione un nivel de estudio requerido");
            }
            
            // Convertir salario
            float pretensionSalarial = Float.parseFloat(salarioStr);
            
            // Obtener empresa
            Empresa empresa = (Empresa) cbxEmpresa.getSelectedItem();
            
            // Crear vacante
            Vacante vacante = new Vacante(
                identificador,
                nivelEstudio,
                infoEstudio,
                tipoContrato,
                pais,
                ciudad,
                chkMudanza.isSelected(),
                chkDisponibilidadVehiculo.isSelected(),
                chkLicencia.isSelected(),
                pretensionSalarial
            );
            
            // Registrar vacante
            Bolsa bolsa = Bolsa.getInstance();
            bolsa.getMisVacantes().add(vacante);
            
            // Asociar vacante a empresa
            empresa.getMisFormulariosEmpresa().add(vacante);
            
            JOptionPane.showMessageDialog(this, "Vacante registrada con �xito!");
            dispose();
        } 
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de salario inv�lido", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}