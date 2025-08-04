package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import Logico.Bolsa;
import Logico.Empresa;
import Logico.Vacante;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ModVacante extends JDialog {

    // ... (El resto de las declaraciones de variables no cambia)
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
    private JSpinner spnAniosExperienciaTecnico;
    private JLabel lbLogoEmpresa;
    private JTextField txtRNC;
    private JTextField txtUbicacion;
    private JTextField txtSector;
    private Vacante vacanteOriginal;
    private JLabel lbLogo;


    public ModVacante(Vacante selected) {
        this.vacanteOriginal = selected;
        // ... (El constructor y la inicializaci�n de componentes no cambian)
        setTitle("Modificar Vacante");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
        setBounds(100, 100, 1001, 719);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPanel.setBackground(new Color(70, 130, 180));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Inicializar mapa de ciudades
        inicializarCiudadesPorPais();

        // --- Panel de Selecci�n de Empresa (Izquierda) ---
        JPanel panelSeleccionEmpresa = new JPanel();
        panelSeleccionEmpresa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccionar Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelSeleccionEmpresa.setBounds(10, 11, 470, 63);
        contentPanel.add(panelSeleccionEmpresa);
        panelSeleccionEmpresa.setLayout(null);

        JLabel lblEmpresa = new JLabel("Empresa:");
        lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmpresa.setBounds(10, 30, 60, 22);
        panelSeleccionEmpresa.add(lblEmpresa);

        cbxEmpresa = new JComboBox<>();
        cbxEmpresa.setBounds(85, 30, 370, 22);
        panelSeleccionEmpresa.add(cbxEmpresa);
        cbxEmpresa.addActionListener(e -> cargarDatosEmpresa());

        // --- Panel de Informaci�n de Empresa (Derecha) ---
        JPanel panelInfoEmpresa = new JPanel();
        panelInfoEmpresa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de la Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelInfoEmpresa.setBounds(490, 11, 485, 298);
        contentPanel.add(panelInfoEmpresa);
        panelInfoEmpresa.setLayout(null);

        JLabel lblRNC = new JLabel("RNC:");
        lblRNC.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRNC.setBounds(21, 181, 80, 22);
        panelInfoEmpresa.add(lblRNC);

        txtRNC = new JTextField();
        txtRNC.setEditable(false);
        txtRNC.setBounds(107, 181, 348, 22);
        panelInfoEmpresa.add(txtRNC);
        txtRNC.setColumns(10);

        JLabel lblUbicacion = new JLabel("Ubicaci�n:");
        lblUbicacion.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblUbicacion.setBounds(21, 224, 80, 22);
        panelInfoEmpresa.add(lblUbicacion);

        txtUbicacion = new JTextField();
        txtUbicacion.setEditable(false);
        txtUbicacion.setBounds(107, 224, 348, 22);
        panelInfoEmpresa.add(txtUbicacion);
        txtUbicacion.setColumns(10);

        JLabel lblArea = new JLabel("�rea:");
        lblArea.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblArea.setBounds(21, 267, 80, 22);
        panelInfoEmpresa.add(lblArea);

        txtSector = new JTextField();
        txtSector.setEditable(false);
        txtSector.setBounds(107, 267, 348, 22);
        panelInfoEmpresa.add(txtSector);
        txtSector.setColumns(10);
        
        lbLogoEmpresa = new JLabel("Logo de la empresa");
        lbLogoEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbLogoEmpresa.setBounds(184, 148, 123, 22);
        panelInfoEmpresa.add(lbLogoEmpresa);
        
        lbLogo = new JLabel("Sin imagen", SwingConstants.CENTER);
        lbLogo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lbLogo.setBounds(159, 36, 166, 116);
        panelInfoEmpresa.add(lbLogo);
        
        

        // --- Panel Principal de Vacante (Izquierda) ---
        JPanel panelVacante = new JPanel();
        panelVacante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la Vacante", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelVacante.setBounds(10, 83, 470, 376);
        contentPanel.add(panelVacante);
        panelVacante.setLayout(null);

        JLabel lblIdentificador = new JLabel("Identificador:");
        lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 22, 100, 22);
        panelVacante.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setText(selected.getIdentificador());
        txtIdentificador.setEditable(false);
        txtIdentificador.setBounds(10, 50, 440, 22);
        panelVacante.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        JLabel lblNombreVacante = new JLabel("Nombre Vacante:");
        lblNombreVacante.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombreVacante.setBounds(10, 88, 120, 22);
        panelVacante.add(lblNombreVacante);

        txtNombreVacante = new JTextField();
        txtNombreVacante.setBounds(10, 123, 440, 22);
        panelVacante.add(txtNombreVacante);
        txtNombreVacante.setColumns(10);

        JLabel lblTipoContrato = new JLabel("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 151, 120, 22);
        panelVacante.add(lblTipoContrato);

        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tiempo Completo", "Medio Tiempo", "Por Horas", "Por Proyecto"
        }));
        cbxTipoContrato.setBounds(10, 179, 440, 22);
        panelVacante.add(cbxTipoContrato);

        JLabel lblPais = new JLabel("Pa�s Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 207, 120, 22);
        panelVacante.add(lblPais);

        cbxPaisResidencia = new JComboBox<>();
        cbxPaisResidencia.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una Opci�n",
            "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per�", "M�xico",
            "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam�",
            "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep�blica Dominicana",
            "Puerto Rico", "Espa�a", "Estados Unidos", "Canad�", "Italia", "Francia",
            "Alemania", "Reino Unido", "Portugal", "Jap�n", "Corea del Sur", "China",
            "India", "Australia", "Sud�frica", "Egipto", "Nigeria", "Marruecos",
            "Arabia Saudita", "Turqu�a", "Rusia", "Noruega", "Suecia", "Finlandia",
            "Polonia", "Grecia", "Suiza", "Austria", "B�lgica", "Pa�ses Bajos", "Nueva Zelanda"}));
        cbxPaisResidencia.setBounds(10, 235, 440, 22);
        panelVacante.add(cbxPaisResidencia);

        JLabel lblCiudad = new JLabel("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 263, 120, 22);
        panelVacante.add(lblCiudad);

        cbxCiudades = new JComboBox<>();
        cbxCiudades.setBounds(10, 291, 440, 22);
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
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(10, 319, 120, 22);
        panelVacante.add(lblSalario);

        spnPretensionSalarial = new JSpinner();
        spnPretensionSalarial.setModel(new SpinnerNumberModel(0, 0, null, 10));
        spnPretensionSalarial.setBounds(10, 347, 440, 22);
        panelVacante.add(spnPretensionSalarial);

        // --- Panel de Requisitos Adicionales (Derecha) ---
        JPanel panelRequisitos = new JPanel();
        panelRequisitos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Requisitos Adicionales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelRequisitos.setBounds(490, 319, 485, 140);
        contentPanel.add(panelRequisitos);
        panelRequisitos.setLayout(null);

        JLabel lblMudanza = new JLabel("Dispuesto a Mudarse:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(20, 30, 140, 22);
        panelRequisitos.add(lblMudanza);

        JPanel panelMudanza = new JPanel();
        panelMudanza.setBounds(170, 30, 120, 33);
        panelRequisitos.add(panelMudanza);
        panelMudanza.setLayout(new FlowLayout());
        rdbtnMudanzaSi = new JRadioButton("S�");
        rdbtnMudanzaNo = new JRadioButton("No");
        ButtonGroup grupoMudanza = new ButtonGroup();
        grupoMudanza.add(rdbtnMudanzaSi);
        grupoMudanza.add(rdbtnMudanzaNo);
        panelMudanza.add(rdbtnMudanzaSi);
        panelMudanza.add(rdbtnMudanzaNo);

        JLabel lblVehiculo = new JLabel("Veh�culo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(20, 65, 140, 22);
        panelRequisitos.add(lblVehiculo);

        JPanel panelVehiculo = new JPanel();
        panelVehiculo.setBounds(170, 65, 120, 33);
        panelRequisitos.add(panelVehiculo);
        panelVehiculo.setLayout(new FlowLayout());
        rdbtnVehiculoSi = new JRadioButton("S�");
        rdbtnVehiculoNo = new JRadioButton("No");
        ButtonGroup grupoVehiculo = new ButtonGroup();
        grupoVehiculo.add(rdbtnVehiculoSi);
        grupoVehiculo.add(rdbtnVehiculoNo);
        panelVehiculo.add(rdbtnVehiculoSi);
        panelVehiculo.add(rdbtnVehiculoNo);

        JLabel lblLicencia = new JLabel("Licencia de Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(20, 100, 140, 22);
        panelRequisitos.add(lblLicencia);

        JPanel panelLicencia = new JPanel();
        panelLicencia.setBounds(170, 100, 120, 29);
        panelRequisitos.add(panelLicencia);
        panelLicencia.setLayout(new FlowLayout());
        rdbtnLicenciaSi = new JRadioButton("S�");
        rdbtnLicenciaNo = new JRadioButton("No");
        ButtonGroup grupoLicencia = new ButtonGroup();
        grupoLicencia.add(rdbtnLicenciaSi);
        grupoLicencia.add(rdbtnLicenciaNo);
        panelLicencia.add(rdbtnLicenciaSi);
        panelLicencia.add(rdbtnLicenciaNo);

        // --- Paneles de Nivel de Estudio ---
        JPanel panelEstudios = new JPanel();
        panelEstudios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nivel de Estudios Requerido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelEstudios.setBounds(10, 470, 965, 70);
        contentPanel.add(panelEstudios);
        panelEstudios.setLayout(null);

        ButtonGroup grupoNivelEstudio = new ButtonGroup();
        rdbtnUniversitario = new JRadioButton("Universitario");
        rdbtnUniversitario.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnUniversitario.setBounds(20, 30, 120, 23);
        grupoNivelEstudio.add(rdbtnUniversitario);
        panelEstudios.add(rdbtnUniversitario);

        rdbtnTecnicoSuperior = new JRadioButton("T�cnico Superior");
        rdbtnTecnicoSuperior.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnTecnicoSuperior.setBounds(170, 30, 150, 23);
        grupoNivelEstudio.add(rdbtnTecnicoSuperior);
        panelEstudios.add(rdbtnTecnicoSuperior);

        rdbtnObrero = new JRadioButton("Obrero");
        rdbtnObrero.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnObrero.setBounds(350, 30, 100, 23);
        grupoNivelEstudio.add(rdbtnObrero);
        panelEstudios.add(rdbtnObrero);

        panelUniversitario = new JPanel();
        panelUniversitario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalles Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelUniversitario.setBounds(10, 551, 965, 70);
        contentPanel.add(panelUniversitario);
        panelUniversitario.setLayout(null);

        JLabel lblCarrera = new JLabel("Carrera Requerida:");
        lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 12));
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

        panelTecnicoSuperior = new JPanel();
        panelTecnicoSuperior.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalles T\u00E9cnico Superior", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelTecnicoSuperior.setBounds(10, 551, 965, 70);
        contentPanel.add(panelTecnicoSuperior);
        panelTecnicoSuperior.setLayout(null);

        JLabel lblEspecialidad = new JLabel("Especialidad Requerida:");
        lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 12));
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
        lblAniosExperiencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAniosExperiencia.setBounds(520, 30, 200, 22);
        panelTecnicoSuperior.add(lblAniosExperiencia);
        
        spnAniosExperienciaTecnico = new JSpinner();
        spnAniosExperienciaTecnico.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        spnAniosExperienciaTecnico.setBounds(720, 30, 80, 22);
        panelTecnicoSuperior.add(spnAniosExperienciaTecnico);

        panelObrero = new JPanel();
        panelObrero.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Habilidades Requeridas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
        panelObrero.setBounds(10, 551, 965, 85);
        contentPanel.add(panelObrero);
        panelObrero.setLayout(null);

        chkElectricidad = new JCheckBox("Electricidad b�sica");
        chkElectricidad.setBounds(20, 25, 200, 23);
        panelObrero.add(chkElectricidad);
        
        chkSoldadura = new JCheckBox("Soldadura");
        chkSoldadura.setBounds(20, 55, 200, 23);
        panelObrero.add(chkSoldadura);
        
        chkTecnicaPintura = new JCheckBox("T�cnicas de pintura o alba�iler�a");
        chkTecnicaPintura.setBounds(270, 25, 250, 23);
        panelObrero.add(chkTecnicaPintura);
        
        chkTuberias = new JCheckBox("Instalaci�n de tuber�as");
        chkTuberias.setBounds(270, 55, 250, 23);
        panelObrero.add(chkTuberias);
        
        chkMantenimiento = new JCheckBox("Mantenimiento b�sico de equipos");
        chkMantenimiento.setBounds(530, 25, 250, 23);
        panelObrero.add(chkMantenimiento);
        
        chkMaquinaria = new JCheckBox("Lectura de planos");
        chkMaquinaria.setBounds(530, 55, 250, 23);
        panelObrero.add(chkMaquinaria);

        // Listeners para mostrar los paneles correspondientes
        rdbtnUniversitario.addActionListener(e -> mostrarPanelEstudio(panelUniversitario));
        rdbtnTecnicoSuperior.addActionListener(e -> mostrarPanelEstudio(panelTecnicoSuperior));
        rdbtnObrero.addActionListener(e -> mostrarPanelEstudio(panelObrero));

        // Cargar datos iniciales
        cargarEmpresas();
        cargarDatosVacante(selected);
        
        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarVacante());
        buttonPane.add(btnModificar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    // ... (El resto de los m�todos como mostrarPanelEstudio, inicializarCiudades, etc., no cambian)
    private void mostrarPanelEstudio(JPanel panel) {
        panelUniversitario.setVisible(false);
        panelTecnicoSuperior.setVisible(false);
        panelObrero.setVisible(false);
        panel.setVisible(true);
    }

    private void inicializarCiudadesPorPais() {
        // Argentina
        ciudadesPorPais.put("Argentina", new String[]{"Buenos Aires", "C�rdoba", "Rosario", "Mendoza", "La Plata", "Mar del Plata", "Salta", "San Juan", "San Miguel de Tucum�n"});
        
        // Brasil
        ciudadesPorPais.put("Brasil", new String[]{"Brasilia", "S�o Paulo", "R�o de Janeiro", "Salvador", "Fortaleza", "Belo Horizonte", "Manaus", "Curitiba", "Recife"});
        
        // Chile
        ciudadesPorPais.put("Chile", new String[]{"Santiago", "Valpara�so", "Concepci�n", "La Serena", "Antofagasta", "Temuco", "Puerto Montt", "Arica", "Iquique"});
        
        // Colombia
        ciudadesPorPais.put("Colombia", new String[]{"Bogot�", "Medell�n", "Cali", "Barranquilla", "Cartagena", "C�cuta", "Bucaramanga", "Pereira", "Santa Marta"});
        
        // M�xico
        ciudadesPorPais.put("M�xico", new String[]{"Ciudad de M�xico", "Guadalajara", "Monterrey", "Puebla", "Toluca", "Tijuana", "Le�n", "Quer�taro", "M�rida"});
        
        // Estados Unidos
        ciudadesPorPais.put("Estados Unidos", new String[]{"Washington D.C.", "Nueva York", "Los �ngeles", "Chicago", "Houston", "Phoenix", "Filadelfia", "San Antonio", "San Diego"});
        
        // Espa�a
        ciudadesPorPais.put("Espa�a", new String[]{"Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "M�laga", "Murcia", "Palma de Mallorca", "Las Palmas"});
        
        // Per�
        ciudadesPorPais.put("Per�", new String[]{"Lima", "Arequipa", "Trujillo", "Chiclayo", "Piura", "Iquitos", "Cusco", "Chimbote", "Huancayo"});
        
        // Resto de pa�ses
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
        
        // Pa�ses sin definir
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
    
    /**
     * Carga los datos de la vacante en los componentes de la ventana.
     * @param vacante La vacante a modificar.
     */
    private void cargarDatosVacante(Vacante vacante) {
        // Datos b�sicos
        txtIdentificador.setText(vacante.getIdentificador());
        txtNombreVacante.setText(vacante.getNombreVacante());
        cbxTipoContrato.setSelectedItem(vacante.getTipoContrato());
        spnPretensionSalarial.setValue(vacante.getPretensionSalarial());
        
        // Ubicaci�n
        cbxPaisResidencia.setSelectedItem(vacante.getPaisResidencia());
        cargarCiudadesPorPais(vacante.getPaisResidencia());
        cbxCiudades.setSelectedItem(vacante.getCiudadResidencia());
        
        // Requisitos adicionales
        rdbtnMudanzaSi.setSelected(vacante.isMudanza());
        rdbtnMudanzaNo.setSelected(!vacante.isMudanza());
        rdbtnVehiculoSi.setSelected(vacante.isDisponibilidadVehiculo());
        rdbtnVehiculoNo.setSelected(!vacante.isDisponibilidadVehiculo());
        rdbtnLicenciaSi.setSelected(vacante.isLicencia());
        rdbtnLicenciaNo.setSelected(!vacante.isLicencia());
        
        // --- INICIO DE LA L�GICA CORREGIDA ---
        // Nivel de estudios: Carga el nivel de estudio y muestra el panel correspondiente.
        String nivel = vacante.getNivelEstudio();
        if ("Universitario".equals(nivel)) {
            rdbtnUniversitario.setSelected(true);
            mostrarPanelEstudio(panelUniversitario);
            // Aqu� se podr�a cargar la carrera si estuviera guardada en la vacante
            // cbxCarreraUniversitario.setSelectedItem(vacante.getCarrera());
        } else if ("T�cnico Superior".equals(nivel)) {
            rdbtnTecnicoSuperior.setSelected(true);
            mostrarPanelEstudio(panelTecnicoSuperior);
             // Aqu� se podr�a cargar la especialidad y a�os si estuvieran guardados
            // cbxEspecialidadTecnico.setSelectedItem(vacante.getEspecialidad());
            // spnAniosExperienciaTecnico.setValue(vacante.getAniosExperiencia());
        } else if ("Obrero".equals(nivel)) {
            rdbtnObrero.setSelected(true);
            mostrarPanelEstudio(panelObrero);
            // Ya no se intenta cargar ni deshabilitar las habilidades,
            // porque no se guardan en la clase Vacante.
        }
        // --- FIN DE LA L�GICA CORREGIDA ---
        
        // Empresa
        for (int i = 0; i < cbxEmpresa.getItemCount(); i++) {
            if (cbxEmpresa.getItemAt(i).contains(vacante.getRncEmpresa())) {
                cbxEmpresa.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Procesa la modificaci�n de la vacante con los datos del formulario.
     */
    private void modificarVacante() {
        try {
            // Obtener datos de la empresa
            String rnc = "";
            if (cbxEmpresa.getSelectedItem() != null && cbxEmpresa.getSelectedIndex() > 0) {
                String selection = cbxEmpresa.getSelectedItem().toString();
                String[] partes = selection.split(" - ");
                if (partes.length == 2) {
                    rnc = partes[1];
                }
            } else {
                 throw new Exception("Seleccione una empresa");
            }
            
            // Validar campos
            if (cbxPaisResidencia.getSelectedIndex() == 0) {
                throw new Exception("Seleccione un pa�s");
            }
            
            if (cbxCiudades.getSelectedIndex() == -1) {
                throw new Exception("Seleccione una ciudad");
            }
            
            String pais = (String) cbxPaisResidencia.getSelectedItem();
            String ciudad = (String) cbxCiudades.getSelectedItem();
            
            // Validar nivel de estudio
            String nivelEstudio = "";
            ArrayList<String> habilidades = new ArrayList<>();
            
            if (rdbtnUniversitario.isSelected()) {
                nivelEstudio = "Universitario";
                String carrera = (String) cbxCarreraUniversitario.getSelectedItem();
                if (carrera.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una carrera universitaria");
                }
            } 
            else if (rdbtnTecnicoSuperior.isSelected()) {
                nivelEstudio = "T�cnico Superior";
                String especialidad = (String) cbxEspecialidadTecnico.getSelectedItem();
                if (especialidad.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una especialidad t�cnica");
                }
            } 
            else if (rdbtnObrero.isSelected()) {
                nivelEstudio = "Obrero";
                if (chkElectricidad.isSelected()) habilidades.add("Electricidad b�sica");
                if (chkSoldadura.isSelected()) habilidades.add("Soldadura");
                if (chkTecnicaPintura.isSelected()) habilidades.add("T�cnicas de pintura o alba�iler�a");
                if (chkTuberias.isSelected()) habilidades.add("Instalaci�n de tuber�as");
                if (chkMantenimiento.isSelected()) habilidades.add("Mantenimiento b�sico de equipos");
                if (chkMaquinaria.isSelected()) habilidades.add("Lectura de planos");
                
                if (habilidades.isEmpty()) {
                    throw new Exception("Seleccione al menos una habilidad para el obrero");
                }
            } 
            else {
                throw new Exception("Seleccione un nivel de estudio requerido");
            }
            
            // Actualizar la vacante
            vacanteOriginal.setRncEmpresa(rnc);
            vacanteOriginal.setNivelEstudio(nivelEstudio);
            vacanteOriginal.setTipoContrato((String) cbxTipoContrato.getSelectedItem());
            vacanteOriginal.setNombreVacante(txtNombreVacante.getText().trim());
            vacanteOriginal.setPaisResidencia(pais);
            vacanteOriginal.setCiudadResidencia(ciudad);
            vacanteOriginal.setMudanza(rdbtnMudanzaSi.isSelected());
            vacanteOriginal.setDisponibilidadVehiculo(rdbtnVehiculoSi.isSelected());
            vacanteOriginal.setLicencia(rdbtnLicenciaSi.isSelected());
            
            Number pretensionSalarialNumber = (Number) spnPretensionSalarial.getValue();
            float pretensionSalarial = pretensionSalarialNumber.floatValue();
            vacanteOriginal.setPretensionSalarial(pretensionSalarial);
            
            // --- CAMBIO IMPORTANTE ---
            // Se elimina la asignaci�n de habilidades, ya que no se guardan en Vacante.
            // if ("Obrero".equals(nivelEstudio)) {
            //     vacanteOriginal.setHabilidades(habilidades); // Esta l�nea se elimina
            // }
            
            // Guardar cambios
            Bolsa.getInstance().modificarVacante(vacanteOriginal);
            JOptionPane.showMessageDialog(this, "Vacante modificada con �xito!");
            dispose();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}