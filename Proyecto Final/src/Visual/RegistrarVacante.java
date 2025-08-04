package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Empresa;
import Logico.Vacante;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.EmptyBorder;

public class RegistrarVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtNombreVacante;
    private JComboBox<String> cbxTipoContrato;
    private JRadioButton rdbtnUniversitario;
    private JRadioButton rdbtnTecnicoSuperior;
    private JRadioButton rdbtnObrero;
    private JPanel panelUniversitario;
    private JPanel panelTecnicoSuperior;
    private JPanel panelObrero;
    private JComboBox<String> cbxEmpresa;
    private JComboBox<String> cbxCarreraUniversitario;
    private JComboBox<String> cbxEspecialidadTecnico;
    private JCheckBox chkElectricidad;
    private JCheckBox chkSoldadura;
    private JCheckBox chkTecnicaPintura;
    private JCheckBox chkTuberias;
    private JCheckBox chkMantenimiento;
    private JCheckBox chkMaquinaria;
    private JRadioButton rdbtnMudanzaSi;
    private JRadioButton rdbtnMudanzaNo;
    private JRadioButton rdbtnVehiculoSi;
    private JRadioButton rdbtnVehiculoNo;
    private JRadioButton rdbtnLicenciaSi;
    private JRadioButton rdbtnLicenciaNo;
    private JSpinner spnPretensionSalarial;
    private JComboBox<String> cbxPaisResidencia;
    private Map<String, String[]> ciudadesPorPais = new HashMap<>();
    private JComboBox<String> cbxCiudades;
    private String rnc;
    private JTextField txtRNC;
    private JTextField txtUbicacion;
    private JTextField txtSector;
    private JSpinner spnAniosExperienciaTecnico;
    private JLabel lbLogoEmpresa;
    private JLabel lbLogo;

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
        setBounds(100, 100, 1001, 760);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBackground(new Color(70, 130, 180));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        inicializarCiudadesPorPais();

        // --- Panel de Selecci�n de Empresa (Izquierda) ---
        JPanel panelSeleccionEmpresa = new JPanel();
        panelSeleccionEmpresa.setBackground(Color.decode("#ecf0f1"));
        panelSeleccionEmpresa.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")),
            "Seleccionar Empresa", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelSeleccionEmpresa.setBounds(10, 11, 470, 70);
        contentPanel.add(panelSeleccionEmpresa);
        panelSeleccionEmpresa.setLayout(null);

        JLabel lblEmpresa = new JLabel("Empresa:");
        lblEmpresa.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEmpresa.setBounds(10, 30, 60, 22);
        panelSeleccionEmpresa.add(lblEmpresa);

        cbxEmpresa = new JComboBox<>();
        cbxEmpresa.setBackground(new Color(255, 255, 224));
        cbxEmpresa.setBounds(85, 30, 370, 22);
        panelSeleccionEmpresa.add(cbxEmpresa);
        cbxEmpresa.addActionListener(e -> cargarDatosEmpresa());


        // --- Panel de Informaci�n de Empresa (Derecha) ---
        JPanel panelInfoEmpresa = new JPanel();
        panelInfoEmpresa.setBackground(Color.decode("#ecf0f1"));
        panelInfoEmpresa.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")), 
            "Informaci�n de la Empresa", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelInfoEmpresa.setBounds(490, 11, 485, 323);
        contentPanel.add(panelInfoEmpresa);
        panelInfoEmpresa.setLayout(null);

        JLabel lblRNC = new JLabel("RNC:");
        lblRNC.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblRNC.setBounds(20, 194, 80, 22);
        panelInfoEmpresa.add(lblRNC);

        txtRNC = new JTextField();
        txtRNC.setBackground(new Color(255, 255, 224));
        txtRNC.setEditable(false);
        txtRNC.setBounds(106, 194, 348, 22);
        panelInfoEmpresa.add(txtRNC);
        txtRNC.setColumns(10);

        JLabel lblUbicacion = new JLabel("Ubicaci�n:");
        lblUbicacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUbicacion.setBounds(20, 237, 80, 22);
        panelInfoEmpresa.add(lblUbicacion);

        txtUbicacion = new JTextField();
        txtUbicacion.setBackground(new Color(255, 255, 224));
        txtUbicacion.setEditable(false);
        txtUbicacion.setBounds(106, 237, 348, 22);
        panelInfoEmpresa.add(txtUbicacion);
        txtUbicacion.setColumns(10);

        JLabel lblArea = new JLabel("�rea:");
        lblArea.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblArea.setBounds(20, 280, 80, 22);
        panelInfoEmpresa.add(lblArea);

        txtSector = new JTextField();
        txtSector.setBackground(new Color(255, 255, 224));
        txtSector.setEditable(false);
        txtSector.setBounds(106, 280, 348, 22);
        panelInfoEmpresa.add(txtSector);
        txtSector.setColumns(10);
        
        lbLogoEmpresa = new JLabel("Logo de la Empresa");
        lbLogoEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogoEmpresa.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbLogoEmpresa.setBounds(200, 161, 159, 22);
        panelInfoEmpresa.add(lbLogoEmpresa);
        
        lbLogo = new JLabel("Sin imagen", SwingConstants.CENTER);
        lbLogo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lbLogo.setBounds(193, 34, 166, 116);
        panelInfoEmpresa.add(lbLogo);


        // --- Panel Principal de Vacante (Izquierda) ---
        JPanel panelVacante = new JPanel();
        panelVacante.setBackground(Color.decode("#ecf0f1"));
        panelVacante.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")),
            "Datos de la Vacante", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelVacante.setBounds(10, 92, 470, 395);
        contentPanel.add(panelVacante);
        panelVacante.setLayout(null);

        JLabel lblIdentificador = new JLabel("Identificador:");
        lblIdentificador.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 35, 100, 22);
        panelVacante.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setBackground(new Color(255, 255, 224));
        txtIdentificador.setText("Vac - 00"+(Bolsa.getInstance().genCodVac+5));
        txtIdentificador.setEditable(false);
        txtIdentificador.setBounds(10, 63, 440, 22);
        panelVacante.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        JLabel lblNombreVacante = new JLabel("Nombre Vacante:");
        lblNombreVacante.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNombreVacante.setBounds(10, 101, 120, 22);
        panelVacante.add(lblNombreVacante);

        txtNombreVacante = new JTextField();
        txtNombreVacante.setBackground(new Color(255, 255, 224));
        txtNombreVacante.setBounds(10, 136, 440, 22);
        panelVacante.add(txtNombreVacante);
        txtNombreVacante.setColumns(10);

        JLabel lblTipoContrato = new JLabel("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 164, 120, 22);
        panelVacante.add(lblTipoContrato);

        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setBackground(new Color(255, 255, 224));
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tiempo Completo", "Medio Tiempo", "Por Horas", "Por Proyecto"
        }));
        cbxTipoContrato.setBounds(10, 192, 440, 22);
        panelVacante.add(cbxTipoContrato);

        JLabel lblPais = new JLabel("Pa�s Residencia:");
        lblPais.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPais.setBounds(10, 220, 120, 22);
        panelVacante.add(lblPais);

        cbxPaisResidencia = new JComboBox<>();
        cbxPaisResidencia.setBackground(new Color(255, 255, 224));
        cbxPaisResidencia.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una Opci�n",
            "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per�", "M�xico",
            "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam�",
            "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep�blica Dominicana",
            "Puerto Rico", "Espa�a", "Estados Unidos", "Canad�", "Italia", "Francia",
            "Alemania", "Reino Unido", "Portugal", "Jap�n", "Corea del Sur", "China",
            "India", "Australia", "Sud�frica", "Egipto", "Nigeria", "Marruecos",
            "Arabia Saudita", "Turqu�a", "Rusia", "Noruega", "Suecia", "Finlandia",
            "Polonia", "Grecia", "Suiza", "Austria", "B�lgica", "Pa�ses Bajos", "Nueva Zelanda"}));
        cbxPaisResidencia.setBounds(10, 248, 440, 22);
        panelVacante.add(cbxPaisResidencia);

        JLabel lblCiudad = new JLabel("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblCiudad.setBounds(10, 276, 120, 22);
        panelVacante.add(lblCiudad);

        cbxCiudades = new JComboBox<>();
        cbxCiudades.setBackground(new Color(255, 255, 224));
        cbxCiudades.setBounds(10, 304, 440, 22);
        panelVacante.add(cbxCiudades);
        cbxCiudades.setEnabled(false);

        cbxPaisResidencia.addActionListener(e -> {
            String paisSeleccionado = (String) cbxPaisResidencia.getSelectedItem();
            if (paisSeleccionado != null && !"Seleccione una Opci�n".equals(paisSeleccionado)) {
                cargarCiudadesPorPais(paisSeleccionado);
                cbxCiudades.setEnabled(true);
            } else {
                cbxCiudades.setModel(new DefaultComboBoxModel<>());
                cbxCiudades.setEnabled(false);
            }
        });

        JLabel lblSalario = new JLabel("Pretensi�n Salarial:");
        lblSalario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSalario.setBounds(10, 332, 120, 22);
        panelVacante.add(lblSalario);

        spnPretensionSalarial = new JSpinner();
        spnPretensionSalarial.setModel(new SpinnerNumberModel(0, 0, null, 10));
        spnPretensionSalarial.setBounds(10, 360, 440, 22);
        panelVacante.add(spnPretensionSalarial);


        // --- Panel de Requisitos Adicionales (Derecha) ---
        JPanel panelRequisitos = new JPanel();
        panelRequisitos.setBackground(Color.decode("#ecf0f1"));
        panelRequisitos.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")), 
            "Requisitos Adicionales", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelRequisitos.setBounds(490, 347, 485, 140);
        contentPanel.add(panelRequisitos);
        panelRequisitos.setLayout(null);

        JLabel lblMudanza = new JLabel("Dispuesto a Mudarse:");
        lblMudanza.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMudanza.setBounds(20, 30, 140, 22);
        panelRequisitos.add(lblMudanza);

        JPanel panelMudanza = new JPanel();
        panelMudanza.setOpaque(false);
        panelMudanza.setBounds(170, 30, 120, 22);
        panelRequisitos.add(panelMudanza);
        panelMudanza.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rdbtnMudanzaSi = new JRadioButton("S�");
        rdbtnMudanzaSi.setOpaque(false);
        rdbtnMudanzaNo = new JRadioButton("No");
        rdbtnMudanzaNo.setOpaque(false);
        ButtonGroup grupoMudanza = new ButtonGroup();
        grupoMudanza.add(rdbtnMudanzaSi);
        grupoMudanza.add(rdbtnMudanzaNo);
        panelMudanza.add(rdbtnMudanzaSi);
        panelMudanza.add(rdbtnMudanzaNo);
        rdbtnMudanzaNo.setSelected(true);

        JLabel lblVehiculo = new JLabel("Veh�culo Propio:");
        lblVehiculo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblVehiculo.setBounds(20, 65, 140, 22);
        panelRequisitos.add(lblVehiculo);

        JPanel panelVehiculo = new JPanel();
        panelVehiculo.setOpaque(false);
        panelVehiculo.setBounds(170, 65, 120, 22);
        panelRequisitos.add(panelVehiculo);
        panelVehiculo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rdbtnVehiculoSi = new JRadioButton("S�");
        rdbtnVehiculoSi.setOpaque(false);
        rdbtnVehiculoNo = new JRadioButton("No");
        rdbtnVehiculoNo.setOpaque(false);
        ButtonGroup grupoVehiculo = new ButtonGroup();
        grupoVehiculo.add(rdbtnVehiculoSi);
        grupoVehiculo.add(rdbtnVehiculoNo);
        panelVehiculo.add(rdbtnVehiculoSi);
        panelVehiculo.add(rdbtnVehiculoNo);
        rdbtnVehiculoNo.setSelected(true);

        JLabel lblLicencia = new JLabel("Licencia de Conducir:");
        lblLicencia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLicencia.setBounds(20, 100, 140, 22);
        panelRequisitos.add(lblLicencia);

        JPanel panelLicencia = new JPanel();
        panelLicencia.setOpaque(false);
        panelLicencia.setBounds(170, 100, 120, 22);
        panelRequisitos.add(panelLicencia);
        panelLicencia.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rdbtnLicenciaSi = new JRadioButton("S�");
        rdbtnLicenciaSi.setOpaque(false);
        rdbtnLicenciaNo = new JRadioButton("No");
        rdbtnLicenciaNo.setOpaque(false);
        ButtonGroup grupoLicencia = new ButtonGroup();
        grupoLicencia.add(rdbtnLicenciaSi);
        grupoLicencia.add(rdbtnLicenciaNo);
        panelLicencia.add(rdbtnLicenciaSi);
        panelLicencia.add(rdbtnLicenciaNo);
        rdbtnLicenciaNo.setSelected(true);


        // --- Paneles de Nivel de Estudio ---
        JPanel panelEstudios = new JPanel();
        panelEstudios.setBackground(Color.decode("#ecf0f1"));
        panelEstudios.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")),
            "Nivel de Estudios Requerido", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelEstudios.setBounds(10, 500, 965, 70);  
        contentPanel.add(panelEstudios);
        panelEstudios.setLayout(null);

        ButtonGroup grupoNivelEstudio = new ButtonGroup();
        rdbtnUniversitario = new JRadioButton("Universitario");
        rdbtnUniversitario.setOpaque(false);
        rdbtnUniversitario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnUniversitario.setBounds(20, 30, 120, 23);
        grupoNivelEstudio.add(rdbtnUniversitario);
        panelEstudios.add(rdbtnUniversitario);

        rdbtnTecnicoSuperior = new JRadioButton("T�cnico Superior");
        rdbtnTecnicoSuperior.setOpaque(false);
        rdbtnTecnicoSuperior.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnTecnicoSuperior.setBounds(170, 30, 150, 23);
        grupoNivelEstudio.add(rdbtnTecnicoSuperior);
        panelEstudios.add(rdbtnTecnicoSuperior);

        rdbtnObrero = new JRadioButton("Obrero");
        rdbtnObrero.setOpaque(false);
        rdbtnObrero.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdbtnObrero.setBounds(350, 30, 100, 23);
        grupoNivelEstudio.add(rdbtnObrero);
        panelEstudios.add(rdbtnObrero);

        panelTecnicoSuperior = new JPanel();
        panelTecnicoSuperior.setBackground(Color.decode("#ecf0f1"));
        panelTecnicoSuperior.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")), 
            "Detalles T�cnico Superior", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelTecnicoSuperior.setBounds(10, 581, 965, 70);
        contentPanel.add(panelTecnicoSuperior);
        panelTecnicoSuperior.setLayout(null);

        JLabel lblEspecialidad = new JLabel("Especialidad Requerida:");
        lblEspecialidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEspecialidad.setBounds(20, 30, 180, 22);
        panelTecnicoSuperior.add(lblEspecialidad);

        cbxEspecialidadTecnico = new JComboBox<>();
        cbxEspecialidadTecnico.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una opcion",
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
        cbxEspecialidadTecnico.setBounds(210, 30, 300, 22);
        panelTecnicoSuperior.add(cbxEspecialidadTecnico);
        
        JLabel lblAniosExperiencia = new JLabel("A�os de experiencia:");
        lblAniosExperiencia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblAniosExperiencia.setBounds(531, 30, 146, 22);
        panelTecnicoSuperior.add(lblAniosExperiencia);
        
        spnAniosExperienciaTecnico = new JSpinner();
        spnAniosExperienciaTecnico.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        spnAniosExperienciaTecnico.setBounds(698, 31, 80, 22);
        panelTecnicoSuperior.add(spnAniosExperienciaTecnico);

        panelObrero = new JPanel();
        panelObrero.setBackground(Color.decode("#ecf0f1"));
        panelObrero.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")),
            "Habilidades Requeridas", TitledBorder.LEADING, TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelObrero.setBounds(10, 581, 965, 105);
        contentPanel.add(panelObrero);
        panelObrero.setLayout(null);

        chkElectricidad = new JCheckBox("Electricidad b�sica");
        chkElectricidad.setOpaque(false);
        chkElectricidad.setBounds(20, 30, 200, 23);
        panelObrero.add(chkElectricidad);
        
        chkSoldadura = new JCheckBox("Soldadura");
        chkSoldadura.setOpaque(false);
        chkSoldadura.setBounds(20, 60, 200, 23);
        panelObrero.add(chkSoldadura);
        
        chkTecnicaPintura = new JCheckBox("T�cnicas de pintura o alba�iler�a");
        chkTecnicaPintura.setOpaque(false);
        chkTecnicaPintura.setBounds(270, 30, 250, 23);
        panelObrero.add(chkTecnicaPintura);
        
        chkTuberias = new JCheckBox("Instalaci�n de tuber�as");
        chkTuberias.setOpaque(false);
        chkTuberias.setBounds(270, 60, 250, 23);
        panelObrero.add(chkTuberias);
        
        chkMantenimiento = new JCheckBox("Mantenimiento b�sico de equipos");
        chkMantenimiento.setOpaque(false);
        chkMantenimiento.setBounds(530, 30, 250, 23);
        panelObrero.add(chkMantenimiento);
        
        chkMaquinaria = new JCheckBox("Lectura de planos");
        chkMaquinaria.setOpaque(false);
        chkMaquinaria.setBounds(530, 60, 250, 23);
        panelObrero.add(chkMaquinaria);

        // Listeners
        rdbtnUniversitario.addActionListener(e -> mostrarPanelEstudio(panelUniversitario));
        rdbtnTecnicoSuperior.addActionListener(e -> mostrarPanelEstudio(panelTecnicoSuperior));
        rdbtnObrero.addActionListener(e -> mostrarPanelEstudio(panelObrero));

        // Estado Inicial
        rdbtnUniversitario.setSelected(true);
        
        panelUniversitario = new JPanel();
        panelUniversitario.setBackground(Color.decode("#ecf0f1"));
        panelUniversitario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#1A237E")),
            "Detalles Universitario", TitledBorder.LEADING, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.decode("#1A237E")));
        panelUniversitario.setBounds(10, 581, 965, 70);
        contentPanel.add(panelUniversitario);
        panelUniversitario.setLayout(null);
        
        JLabel lblCarrera = new JLabel("Carrera Requerida:");
        lblCarrera.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblCarrera.setBounds(20, 30, 150, 22);
        panelUniversitario.add(lblCarrera);
        
        cbxCarreraUniversitario = new JComboBox<>();
        cbxCarreraUniversitario.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una opcion",
            "Administraci�n de Empresas", "Contabilidad", "Econom�a", "Marketing", "Finanzas",
            "Psicolog�a", "Derecho", "Educaci�n / Pedagog�a", "Trabajo Social",
            "Comunicaci�n Social / Periodismo", "Relaciones Internacionales",
            "Ingenier�a en Sistemas / Inform�tica", "Ingenier�a Civil", "Ingenier�a Industrial",
            "Ingenier�a El�ctrica / Electr�nica", "Medicina", "Enfermer�a", "Odontolog�a",
            "Farmacia", "Nutrici�n", "Fisioterapia", "Veterinaria", "Ciencias de la Computaci�n",
            "Desarrollo de Software", "Ciberseguridad", "Arquitectura", "Dise�o Gr�fico",
            "Dise�o Industrial", "Turismo y Hoteler�a", "Gastronom�a / Artes Culinarias"
        }));
        cbxCarreraUniversitario.setBounds(180, 30, 770, 22);
        panelUniversitario.add(cbxCarreraUniversitario);
        mostrarPanelEstudio(panelUniversitario);
        cargarEmpresas();

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.decode("#ecf0f1"));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnRegistrar = createStyledButton("Registrar", "#2962ff");
        btnRegistrar.addActionListener(e -> registrarVacante());
        buttonPane.add(btnRegistrar);
        
        JButton btnCancelar = createStyledButton("Cancelar", "#95a5a6");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    private void mostrarPanelEstudio(JPanel panel) {
        panelUniversitario.setVisible(false);
        panelTecnicoSuperior.setVisible(false);
        panelObrero.setVisible(false);
        panel.setVisible(true);
    }

    private void inicializarCiudadesPorPais() {
        ciudadesPorPais.put("Argentina", new String[]{"Buenos Aires", "C�rdoba", "Rosario", "Mendoza", "La Plata", "Mar del Plata", "Salta", "San Juan", "San Miguel de Tucum�n"});
        ciudadesPorPais.put("Brasil", new String[]{"Brasilia", "S�o Paulo", "R�o de Janeiro", "Salvador", "Fortaleza", "Belo Horizonte", "Manaus", "Curitiba", "Recife"});
        ciudadesPorPais.put("Chile", new String[]{"Santiago", "Valpara�so", "Concepci�n", "La Serena", "Antofagasta", "Temuco", "Puerto Montt", "Arica", "Iquique"});
        ciudadesPorPais.put("Colombia", new String[]{"Bogot�", "Medell�n", "Cali", "Barranquilla", "Cartagena", "C�cuta", "Bucaramanga", "Pereira", "Santa Marta"});
        ciudadesPorPais.put("M�xico", new String[]{"Ciudad de M�xico", "Guadalajara", "Monterrey", "Puebla", "Toluca", "Tijuana", "Le�n", "Quer�taro", "M�rida"});
        ciudadesPorPais.put("Estados Unidos", new String[]{"Washington D.C.", "Nueva York", "Los �ngeles", "Chicago", "Houston", "Phoenix", "Filadelfia", "San Antonio", "San Diego"});
        ciudadesPorPais.put("Espa�a", new String[]{"Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "M�laga", "Murcia", "Palma de Mallorca", "Las Palmas"});
        ciudadesPorPais.put("Per�", new String[]{"Lima", "Arequipa", "Trujillo", "Chiclayo", "Piura", "Iquitos", "Cusco", "Chimbote", "Huancayo"});
        ciudadesPorPais.put("Ecuador", new String[]{"Quito", "Guayaquil", "Cuenca", "Santo Domingo", "Machala", "Manta", "Portoviejo", "Loja", "Ambato"});
        ciudadesPorPais.put("Venezuela", new String[]{"Caracas", "Maracaibo", "Valencia", "Barquisimeto", "Maracay", "Ciudad Guayana", "Matur�n", "Barcelona", "San Crist�bal"});
        ciudadesPorPais.put("Uruguay", new String[]{"Montevideo", "Salto", "Ciudad de la Costa", "Paysand�", "Las Piedras", "Rivera", "Maldonado", "Tacuaremb�", "Melo"});
        ciudadesPorPais.put("Paraguay", new String[]{"Asunci�n", "Ciudad del Este", "San Lorenzo", "Capiat�", "Lambar�", "Fernando de la Mora", "Encarnaci�n", "Pedro Juan Caballero", "Itaugu�"});
        ciudadesPorPais.put("Bolivia", new String[]{"Sucre", "La Paz", "Santa Cruz de la Sierra", "Cochabamba", "Oruro", "Tarija", "Potos�", "Sacaba", "Montero"});
        ciudadesPorPais.put("Cuba", new String[]{"La Habana", "Santiago de Cuba", "Camag�ey", "Holgu�n", "Guant�namo", "Santa Clara", "Cienfuegos", "Bayamo", "Las Tunas"});
        ciudadesPorPais.put("Rep�blica Dominicana", new String[]{"Santo Domingo", "Santiago de los Caballeros", "Santo Domingo Este", "San Pedro de Macor�s", "La Romana", "San Crist�bal", "San Francisco de Macor�s", "Salvale�n de Hig�ey", "Concepci�n de la Vega"});
        ciudadesPorPais.put("Canad�", new String[]{"Ottawa", "Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Quebec", "Winnipeg", "Hamilton"});
        ciudadesPorPais.put("Italia", new String[]{"Roma", "Mil�n", "N�poles", "Tur�n", "Palermo", "G�nova", "Bolonia", "Florencia", "Venecia"});
        ciudadesPorPais.put("Francia", new String[]{"Par�s", "Marsella", "Lyon", "Toulouse", "Niza", "Nantes", "Estrasburgo", "Montpellier", "Burdeos"});
        ciudadesPorPais.put("Alemania", new String[]{"Berl�n", "Hamburgo", "M�nich", "Colonia", "Fr�ncfort", "Stuttgart", "D�sseldorf", "Dortmund", "Essen"});
        ciudadesPorPais.put("Reino Unido", new String[]{"Londres", "Birmingham", "Glasgow", "Liverpool", "Bristol", "M�nchester", "Sheffield", "Leeds", "Edimburgo"});
        ciudadesPorPais.put("China", new String[]{"Pek�n", "Shangh�i", "Hong Kong", "Cant�n", "Shenzhen", "Tianj�n", "Chongqing", "Dongguan", "Nank�n"});
        ciudadesPorPais.put("Jap�n", new String[]{"Tokio", "Osaka", "Nagoya", "Yokohama", "Kioto", "Kobe", "Fukuoka", "Sapporo", "Hiroshima"});
        
        String[] paisesRestantes = {"Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam�", "Puerto Rico", "Portugal", "Corea del Sur", "India", "Australia", "Sud�frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu�a", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B�lgica", "Pa�ses Bajos", "Nueva Zelanda"};
        for (String pais : paisesRestantes) {
            ciudadesPorPais.put(pais, new String[]{pais});
        }
    }

    private void cargarCiudadesPorPais(String pais) {
        String[] ciudades = ciudadesPorPais.get(pais);
        if (ciudades != null) {
            cbxCiudades.setModel(new DefaultComboBoxModel<>(ciudades));
        } else {
            cbxCiudades.setModel(new DefaultComboBoxModel<>(new String[]{pais}));
        }
    }

    private void cargarEmpresas() {
        cbxEmpresa.removeAllItems();
        cbxEmpresa.addItem("Seleccione una Opci�n");
        for (Empresa empresa : Bolsa.getInstance().getMisEmpresas()) {
            cbxEmpresa.addItem(empresa.getNombre() + " - " + empresa.getIdentificador());
        }
    }
    
    private void cargarDatosEmpresa() {
        int selectedIndex = cbxEmpresa.getSelectedIndex();
        if (selectedIndex <= 0) {
            txtRNC.setText("");
            txtUbicacion.setText("");
            txtSector.setText("");
            return;
        }

        try {
            String seleccion = cbxEmpresa.getSelectedItem().toString();
            String[] partes = seleccion.split(" - ");
            if (partes.length == 2) {
                String rncSeleccionado = partes[1];
                Empresa empresa = Bolsa.getInstance().buscarEmpresaByCod(rncSeleccionado);
                if (empresa != null) {
                    txtRNC.setText(empresa.getIdentificador());
                    txtUbicacion.setText(empresa.getPais());
                    txtSector.setText(empresa.getSector());
                    byte[] imgBytes = empresa.getImagen();
            		if (imgBytes != null) {
            		    ImageIcon icono = new ImageIcon(imgBytes);
            		    Image imagenEscalada = icono.getImage().getScaledInstance(166, 116, Image.SCALE_SMOOTH);
            		    lbLogo.setIcon(new ImageIcon(imagenEscalada));
            		    lbLogo.setText("");
            		} else {
            			lbLogo.setIcon(null);
            			lbLogo.setText("Sin imagen");
            		}
                }
            }
        } catch (Exception e) {
            txtRNC.setText("");
            txtUbicacion.setText("");
            txtSector.setText("");
        }
    }
    
    private JButton createStyledButton(String text, String hexColor) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode(hexColor));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setBorderPainted(false);
        return button;
    }

    private void registrarVacante() {
        try {
            String identificador = txtIdentificador.getText().trim();
            String nombreVacante = txtNombreVacante.getText().trim();
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            String salarioStr = spnPretensionSalarial.getValue().toString();
            
            if (cbxPaisResidencia.getSelectedIndex() == 0) {
                throw new Exception("Seleccione un pa�s");
            }
            
            if (cbxCiudades.getSelectedIndex() == -1) {
                throw new Exception("Seleccione una ciudad");
            }
            if(cbxEmpresa.getSelectedItem() != null) {
            	String selection = cbxEmpresa.getSelectedItem().toString();
            	String[] partes = selection.split(" - ");
            	if(partes.length == 2) {
            		rnc = partes[1];
            	}
            }
            
            String pais = (String) cbxPaisResidencia.getSelectedItem();
            String ciudad = (String) cbxCiudades.getSelectedItem();
            
            if (nombreVacante.isEmpty() || salarioStr.isEmpty()) {
                throw new Exception("Complete todos los campos obligatorios");
            }
            
            if (nombreVacante.length() > 100) {
                throw new Exception("Nombre de vacante no puede exceder 100 caracteres");
            }
            
            float pretensionSalarial = Float.parseFloat(salarioStr);
            if (pretensionSalarial <= 0) {
                throw new Exception("La pretensi�n salarial debe ser mayor que 0");
            }
            
            if (cbxEmpresa.getSelectedItem() == null) {
                throw new Exception("Seleccione una empresa");
            }
            
            if (!rdbtnMudanzaSi.isSelected() && !rdbtnMudanzaNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de mudanza");
            }
            
            if (!rdbtnVehiculoSi.isSelected() && !rdbtnVehiculoNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de veh�culo");
            }
            
            if (!rdbtnLicenciaSi.isSelected() && !rdbtnLicenciaNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de licencia");
            }
            
            boolean mudanza = rdbtnMudanzaSi.isSelected();
            boolean vehiculo = rdbtnVehiculoSi.isSelected();
            boolean licencia = rdbtnLicenciaSi.isSelected();
            
            String nivelEstudio = "";
            String[] infoEstudio = new String[1];
            ArrayList<String> habilidades = new ArrayList<>();
            
            if (rdbtnUniversitario.isSelected()) {
                nivelEstudio = "Universitario";
                String carrera = (String) cbxCarreraUniversitario.getSelectedItem();
                if (carrera.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una carrera universitaria");
                }
                infoEstudio[0] = carrera;
            } 
            else if (rdbtnTecnicoSuperior.isSelected()) {
                nivelEstudio = "T�cnico Superior";
                String especialidad = (String) cbxEspecialidadTecnico.getSelectedItem();
                if (especialidad.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una especialidad t�cnica");
                }
                int aniosExperiencia = (int) spnAniosExperienciaTecnico.getValue();
                infoEstudio = new String[]{especialidad, String.valueOf(aniosExperiencia)};
            } 
            else if (rdbtnObrero.isSelected()) {
                nivelEstudio = "Obrero";
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
            
            Empresa empresa = Bolsa.getInstance().buscarEmpresaByCod(rnc);
            
            Vacante vacante = new Vacante(identificador,
               	rnc,
                nivelEstudio,
                tipoContrato,
                nombreVacante,
                pais,
                ciudad,
                mudanza,
                vehiculo,
                licencia,
                pretensionSalarial, true
            );
            
            Bolsa.getInstance().registrarVacante(vacante);
            
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