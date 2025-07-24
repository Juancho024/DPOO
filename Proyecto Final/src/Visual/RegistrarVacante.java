package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
    private JComboBox cbxEmpresa;
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
        setBounds(100, 100, 668, 650);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        // Inicializar mapa de ciudades
        inicializarCiudadesPorPais();
        
        JPanel panelEmpresa = new JPanel();
        panelEmpresa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Empresa", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelEmpresa.setBounds(10, 11, 626, 70);
        contentPanel.add(panelEmpresa);
        panelEmpresa.setLayout(null);
        
        Label lblEmpresa = new Label("Empresa:");
        lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmpresa.setBounds(10, 30, 80, 22);
        panelEmpresa.add(lblEmpresa);
        
        cbxEmpresa = new JComboBox<>();
        cbxEmpresa.setBounds(100, 30, 500, 22);
        panelEmpresa.add(cbxEmpresa);
        
        JPanel panelVacante = new JPanel();
        panelVacante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Vacante", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelVacante.setBounds(10, 92, 626, 230);
        contentPanel.add(panelVacante);
        panelVacante.setLayout(null);
        
        Label lblIdentificador = new Label("Identificador:");
        lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 30, 100, 22);
        panelVacante.add(lblIdentificador);
        
        txtIdentificador = new JTextField();
        txtIdentificador.setBounds(140, 30, 200, 22);
        panelVacante.add(txtIdentificador);
        txtIdentificador.setColumns(10);
        
        Label lblNombreVacante = new Label("Nombre Vacante:");
        lblNombreVacante.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombreVacante.setBounds(10, 70, 120, 22);
        panelVacante.add(lblNombreVacante);
        
        txtNombreVacante = new JTextField();
        txtNombreVacante.setBounds(140, 70, 200, 22);
        panelVacante.add(txtNombreVacante);
        txtNombreVacante.setColumns(10);
        
        Label lblTipoContrato = new Label("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 110, 120, 22);
        panelVacante.add(lblTipoContrato);
        
        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tiempo Completo", "Medio Tiempo", "Por Horas", "Por Proyecto"
        }));
        cbxTipoContrato.setBounds(140, 110, 200, 22);
        panelVacante.add(cbxTipoContrato);
        
        Label lblPais = new Label("País Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 150, 120, 22);
        panelVacante.add(lblPais);
        
        cbxPaisResidencia = new JComboBox<>();
        cbxPaisResidencia.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una Opción", 
            "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Perú", "México", 
            "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panamá", 
            "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "República Dominicana", 
            "Puerto Rico", "España", "Estados Unidos", "Canadá", "Italia", "Francia", 
            "Alemania", "Reino Unido", "Portugal", "Japón", "Corea del Sur", "China", 
            "India", "Australia", "Sudáfrica", "Egipto", "Nigeria", "Marruecos", 
            "Arabia Saudita", "Turquía", "Rusia", "Noruega", "Suecia", "Finlandia", 
            "Polonia", "Grecia", "Suiza", "Austria", "Bélgica", "Países Bajos", "Nueva Zelanda"}));
        cbxPaisResidencia.setBounds(140, 150, 200, 22);
        panelVacante.add(cbxPaisResidencia);
        
        Label lblCiudad = new Label("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 190, 120, 22);
        panelVacante.add(lblCiudad);
        
        cbxCiudades = new JComboBox<>();
        cbxCiudades.setBounds(140, 190, 200, 22);
        panelVacante.add(cbxCiudades);
        cbxCiudades.setEnabled(false); // Inicialmente deshabilitado

        // Listener para cambiar las ciudades cuando se selecciona un país
        cbxPaisResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paisSeleccionado = (String) cbxPaisResidencia.getSelectedItem();
                if (paisSeleccionado != null && !"Seleccione una Opción".equals(paisSeleccionado)) {
                    cargarCiudadesPorPais(paisSeleccionado);
                    cbxCiudades.setEnabled(true);
                } else {
                    cbxCiudades.setModel(new DefaultComboBoxModel<>());
                    cbxCiudades.setEnabled(false);
                }
            }
        });
        
        Label lblMudanza = new Label("Dipuesto a Mudarse:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(350, 30, 130, 22);
        panelVacante.add(lblMudanza);
        
        JPanel panelMudanza = new JPanel();
        panelMudanza.setBounds(486, 30, 120, 22);
        panelVacante.add(panelMudanza);
        panelMudanza.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        rdbtnMudanzaSi = new JRadioButton("Sí");
        rdbtnMudanzaNo = new JRadioButton("No");
        ButtonGroup grupoMudanza = new ButtonGroup();
        grupoMudanza.add(rdbtnMudanzaSi);
        grupoMudanza.add(rdbtnMudanzaNo);
        panelMudanza.add(rdbtnMudanzaSi);
        panelMudanza.add(rdbtnMudanzaNo);
        rdbtnMudanzaNo.setSelected(true);
        
        Label lblVehiculo = new Label("Vehículo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(350, 70, 120, 22);
        panelVacante.add(lblVehiculo);
        
        JPanel panelVehiculo = new JPanel();
        panelVehiculo.setBounds(486, 70, 120, 22);
        panelVacante.add(panelVehiculo);
        panelVehiculo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        rdbtnVehiculoSi = new JRadioButton("Sí");
        rdbtnVehiculoNo = new JRadioButton("No");
        ButtonGroup grupoVehiculo = new ButtonGroup();
        grupoVehiculo.add(rdbtnVehiculoSi);
        grupoVehiculo.add(rdbtnVehiculoNo);
        panelVehiculo.add(rdbtnVehiculoSi);
        panelVehiculo.add(rdbtnVehiculoNo);
        rdbtnVehiculoNo.setSelected(true);
        
        Label lblLicencia = new Label("Licencia Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(350, 110, 120, 22);
        panelVacante.add(lblLicencia);
        
        JPanel panelLicencia = new JPanel();
        panelLicencia.setBounds(486, 110, 120, 22);
        panelVacante.add(panelLicencia);
        panelLicencia.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        rdbtnLicenciaSi = new JRadioButton("Sí");
        rdbtnLicenciaNo = new JRadioButton("No");
        ButtonGroup grupoLicencia = new ButtonGroup();
        grupoLicencia.add(rdbtnLicenciaSi);
        grupoLicencia.add(rdbtnLicenciaNo);
        panelLicencia.add(rdbtnLicenciaSi);
        panelLicencia.add(rdbtnLicenciaNo);
        rdbtnLicenciaNo.setSelected(true);
        
        Label lblSalario = new Label("Pretensión Salarial:");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(350, 150, 120, 22);
        panelVacante.add(lblSalario);
        
        spnPretensionSalarial = new JSpinner();
        spnPretensionSalarial.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10)));
        spnPretensionSalarial.setBounds(486, 150, 120, 22);
        panelVacante.add(spnPretensionSalarial);
        
        JPanel panelEstudios = new JPanel();
        panelEstudios.setBorder(new TitledBorder(null, "Nivel de Estudios Requerido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelEstudios.setBounds(10, 333, 626, 70);
        contentPanel.add(panelEstudios);
        panelEstudios.setLayout(null);
        
        // Grupo para los botones de nivel de estudio (SOLO UNA SELECCIÓN)
        ButtonGroup grupoNivelEstudio = new ButtonGroup();
        
        rdbtnUniversitario = new JRadioButton("Universitario");
        rdbtnUniversitario.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnUniversitario.setBounds(20, 30, 120, 23);
        grupoNivelEstudio.add(rdbtnUniversitario);
        panelEstudios.add(rdbtnUniversitario);
        
        rdbtnTecnicoSuperior = new JRadioButton("Técnico Superior");
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
        panelUniversitario.setBorder(new TitledBorder(null, "Detalles Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelUniversitario.setBounds(10, 414, 614, 70);
        contentPanel.add(panelUniversitario);
        panelUniversitario.setLayout(null);
        panelUniversitario.setVisible(false);
        
        Label lblCarrera = new Label("Carrera Requerida:");
        lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCarrera.setBounds(20, 30, 150, 22);
        panelUniversitario.add(lblCarrera);
        
        cbxCarreraUniversitario = new JComboBox<>();
        cbxCarreraUniversitario.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una opcion",					
            "Administración de Empresas", "Contabilidad", "Economía", "Marketing", "Finanzas", 
            "Psicología", "Derecho", "Educación / Pedagogía", "Trabajo Social", 
            "Comunicación Social / Periodismo", "Relaciones Internacionales", 
            "Ingeniería en Sistemas / Informática", "Ingeniería Civil", "Ingeniería Industrial", 
            "Ingeniería Eléctrica / Electrónica", "Medicina", "Enfermería", "Odontología", 
            "Farmacia", "Nutrición", "Fisioterapia", "Veterinaria", "Ciencias de la Computación", 
            "Desarrollo de Software", "Ciberseguridad", "Arquitectura", "Diseño Gráfico", 
            "Diseño Industrial", "Turismo y Hotelería", "Gastronomía / Artes Culinarias"
        }));
        cbxCarreraUniversitario.setBounds(180, 30, 350, 22);
        panelUniversitario.add(cbxCarreraUniversitario);
        
        panelTecnicoSuperior = new JPanel();
        panelTecnicoSuperior.setBorder(new TitledBorder(null, "Detalles Técnico Superior", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTecnicoSuperior.setBounds(10, 414, 614, 70);
        contentPanel.add(panelTecnicoSuperior);
        panelTecnicoSuperior.setLayout(null);
        panelTecnicoSuperior.setVisible(false);
        
        Label lblEspecialidad = new Label("Especialidad Requerida:");
        lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEspecialidad.setBounds(20, 30, 180, 22);
        panelTecnicoSuperior.add(lblEspecialidad);
        
        cbxEspecialidadTecnico = new JComboBox<>();
        cbxEspecialidadTecnico.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione una opcion",
            "Técnico en Informática", "Técnico en Contabilidad", "Técnico en Electrónica", 
            "Técnico en Electricidad", "Técnico en Mecánica Industrial", 
            "Técnico en Refrigeración y Climatización", "Técnico en Enfermería", 
            "Técnico en Farmacia", "Técnico en Análisis de Sistemas", 
            "Técnico en Gestión Administrativa", "Técnico en Desarrollo de Software", 
            "Técnico en Redes", "Técnico en Seguridad Industrial", 
            "Técnico en Construcción Civil", "Técnico en Diseño Gráfico", 
            "Técnico en Producción Audiovisual", "Técnico en Turismo", 
            "Técnico en Gastronomía", "Técnico en Logística", 
            "Técnico en Recursos Humanos", "Técnico en Mercadeo", 
            "Técnico en Agronomía", "Técnico en Educación Inicial", 
            "Técnico en Gestión Ambiental", "Técnico en Mantenimiento Industrial"
        }));
        cbxEspecialidadTecnico.setBounds(210, 30, 320, 22);
        panelTecnicoSuperior.add(cbxEspecialidadTecnico);
        
        panelObrero = new JPanel();
        panelObrero.setBorder(new TitledBorder(null, "Habilidades Requeridas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelObrero.setBounds(10, 414, 626, 130);
        contentPanel.add(panelObrero);
        panelObrero.setLayout(null);
        panelObrero.setVisible(false);
        
        chkElectricidad = new JCheckBox("Electricidad básica");
        chkElectricidad.setBounds(20, 30, 200, 23);
        panelObrero.add(chkElectricidad);
        
        chkSoldadura = new JCheckBox("Soldadura");
        chkSoldadura.setBounds(20, 60, 200, 23);
        panelObrero.add(chkSoldadura);
        
        chkTecnicaPintura = new JCheckBox("Técnicas de pintura o albañilería");
        chkTecnicaPintura.setBounds(20, 90, 250, 23);
        panelObrero.add(chkTecnicaPintura);
        
        chkTuberias = new JCheckBox("Instalación de tuberías");
        chkTuberias.setBounds(300, 30, 250, 23);
        panelObrero.add(chkTuberias);
        
        chkMantenimiento = new JCheckBox("Mantenimiento básico de equipos");
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
    
    private void inicializarCiudadesPorPais() {
        // Argentina
        ciudadesPorPais.put("Argentina", new String[]{"Buenos Aires", "Córdoba", "Rosario", "Mendoza", "La Plata", "Mar del Plata", "Salta", "San Juan", "San Miguel de Tucumán"});
        
        // Brasil
        ciudadesPorPais.put("Brasil", new String[]{"Brasilia", "São Paulo", "Río de Janeiro", "Salvador", "Fortaleza", "Belo Horizonte", "Manaus", "Curitiba", "Recife"});
        
        // Chile
        ciudadesPorPais.put("Chile", new String[]{"Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta", "Temuco", "Puerto Montt", "Arica", "Iquique"});
        
        // Colombia
        ciudadesPorPais.put("Colombia", new String[]{"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta", "Bucaramanga", "Pereira", "Santa Marta"});
        
        // México
        ciudadesPorPais.put("México", new String[]{"Ciudad de México", "Guadalajara", "Monterrey", "Puebla", "Toluca", "Tijuana", "León", "Querétaro", "Mérida"});
        
        // Estados Unidos
        ciudadesPorPais.put("Estados Unidos", new String[]{"Washington D.C.", "Nueva York", "Los Ángeles", "Chicago", "Houston", "Phoenix", "Filadelfia", "San Antonio", "San Diego"});
        
        // España
        ciudadesPorPais.put("España", new String[]{"Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma de Mallorca", "Las Palmas"});
        
        // Perú
        ciudadesPorPais.put("Perú", new String[]{"Lima", "Arequipa", "Trujillo", "Chiclayo", "Piura", "Iquitos", "Cusco", "Chimbote", "Huancayo"});
        
        // Resto de países (ejemplos)
        ciudadesPorPais.put("Ecuador", new String[]{"Quito", "Guayaquil", "Cuenca", "Santo Domingo", "Machala", "Manta", "Portoviejo", "Loja", "Ambato"});
        ciudadesPorPais.put("Venezuela", new String[]{"Caracas", "Maracaibo", "Valencia", "Barquisimeto", "Maracay", "Ciudad Guayana", "Maturín", "Barcelona", "San Cristóbal"});
        ciudadesPorPais.put("Uruguay", new String[]{"Montevideo", "Salto", "Ciudad de la Costa", "Paysandú", "Las Piedras", "Rivera", "Maldonado", "Tacuarembó", "Melo"});
        ciudadesPorPais.put("Paraguay", new String[]{"Asunción", "Ciudad del Este", "San Lorenzo", "Capiatá", "Lambaré", "Fernando de la Mora", "Encarnación", "Pedro Juan Caballero", "Itauguá"});
        ciudadesPorPais.put("Bolivia", new String[]{"Sucre", "La Paz", "Santa Cruz de la Sierra", "Cochabamba", "Oruro", "Tarija", "Potosí", "Sacaba", "Montero"});
        ciudadesPorPais.put("Cuba", new String[]{"La Habana", "Santiago de Cuba", "Camagüey", "Holguín", "Guantánamo", "Santa Clara", "Cienfuegos", "Bayamo", "Las Tunas"});
        ciudadesPorPais.put("República Dominicana", new String[]{"Santo Domingo", "Santiago de los Caballeros", "Santo Domingo Este", "San Pedro de Macorís", "La Romana", "San Cristóbal", "San Francisco de Macorís", "Salvaleón de Higüey", "Concepción de la Vega"});
        ciudadesPorPais.put("Canadá", new String[]{"Ottawa", "Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Quebec", "Winnipeg", "Hamilton"});
        ciudadesPorPais.put("Italia", new String[]{"Roma", "Milán", "Nápoles", "Turín", "Palermo", "Génova", "Bolonia", "Florencia", "Venecia"});
        ciudadesPorPais.put("Francia", new String[]{"París", "Marsella", "Lyon", "Toulouse", "Niza", "Nantes", "Estrasburgo", "Montpellier", "Burdeos"});
        ciudadesPorPais.put("Alemania", new String[]{"Berlín", "Hamburgo", "Múnich", "Colonia", "Fráncfort", "Stuttgart", "Düsseldorf", "Dortmund", "Essen"});
        ciudadesPorPais.put("Reino Unido", new String[]{"Londres", "Birmingham", "Glasgow", "Liverpool", "Bristol", "Mánchester", "Sheffield", "Leeds", "Edimburgo"});
        ciudadesPorPais.put("China", new String[]{"Pekín", "Shanghái", "Hong Kong", "Cantón", "Shenzhen", "Tianjín", "Chongqing", "Dongguan", "Nankín"});
        ciudadesPorPais.put("Japón", new String[]{"Tokio", "Osaka", "Nagoya", "Yokohama", "Kioto", "Kobe", "Fukuoka", "Sapporo", "Hiroshima"});
        
        // Países sin definir (usar capital como única opción)
        String[] paisesRestantes = {"Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panamá", "Puerto Rico", "Portugal", "Corea del Sur", "India", "Australia", "Sudáfrica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turquía", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "Bélgica", "Países Bajos", "Nueva Zelanda"};
        for (String pais : paisesRestantes) {
            ciudadesPorPais.put(pais, new String[]{pais});
        }
    }
    
    private void cargarCiudadesPorPais(String pais) {
        String[] ciudades = ciudadesPorPais.get(pais);
        if (ciudades != null) {
            cbxCiudades.setModel(new DefaultComboBoxModel<>(ciudades));
        } else {
            // Por si acaso no está definido el país
            cbxCiudades.setModel(new DefaultComboBoxModel<>(new String[]{pais}));
        }
    }
    
    private void cargarEmpresas() {
        cbxEmpresa.removeAllItems();
        cbxEmpresa.addItem("Seleccione una Opción");
        for (Empresa empresa : Bolsa.getInstance().getMisEmpresas()) {
            cbxEmpresa.addItem(empresa.getNombre() + " - " + empresa.getIdentificador());
        }
    }
    
    private void registrarVacante() {
        try {
            // Validar campos básicos
            String identificador = txtIdentificador.getText().trim();
            String nombreVacante = txtNombreVacante.getText().trim();
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            String salarioStr = spnPretensionSalarial.getValue().toString();
            
            // Validar selección de país y ciudad
            if (cbxPaisResidencia.getSelectedIndex() == 0) {
                throw new Exception("Seleccione un país");
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
            
            // Validar campos vacíos
            if (identificador.isEmpty() || nombreVacante.isEmpty() || salarioStr.isEmpty()) {
                throw new Exception("Complete todos los campos obligatorios");
            }
            
            // Validar formato del identificador
            if (!identificador.matches("[A-Za-z0-9-]+")) {
                throw new Exception("Identificador solo puede contener letras, números y guiones");
            }
            
            // Validar identificador único
            for (Vacante v : Bolsa.getInstance().getMisVacantes()) {
                if (v.getIdentificador().equals(identificador)) {
                    throw new Exception("Ya existe una vacante con este identificador");
                }
            }
            
            // Validar longitud de campos
            if (nombreVacante.length() > 100) {
                throw new Exception("Nombre de vacante no puede exceder 100 caracteres");
            }
            
            // Validar pretensión salarial
            float pretensionSalarial = Float.parseFloat(salarioStr);
            if (pretensionSalarial <= 0) {
                throw new Exception("La pretensión salarial debe ser mayor que 0");
            }
            
            // Validar empresa
            if (cbxEmpresa.getSelectedItem() == null) {
                throw new Exception("Seleccione una empresa");
            }
            
            // Validar radio buttons
            if (!rdbtnMudanzaSi.isSelected() && !rdbtnMudanzaNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de mudanza");
            }
            
            if (!rdbtnVehiculoSi.isSelected() && !rdbtnVehiculoNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de vehículo");
            }
            
            if (!rdbtnLicenciaSi.isSelected() && !rdbtnLicenciaNo.isSelected()) {
                throw new Exception("Seleccione disponibilidad de licencia");
            }
            
            // Obtener valores de los radio buttons
            boolean mudanza = rdbtnMudanzaSi.isSelected();
            boolean vehiculo = rdbtnVehiculoSi.isSelected();
            boolean licencia = rdbtnLicenciaSi.isSelected();
            
            // Validar nivel de estudio
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
                nivelEstudio = "Técnico Superior";
                String especialidad = (String) cbxEspecialidadTecnico.getSelectedItem();
                if (especialidad.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una especialidad técnica");
                }
                infoEstudio[0] = especialidad;
            } 
            else if (rdbtnObrero.isSelected()) {
                nivelEstudio = "Obrero";
                if (chkElectricidad.isSelected()) habilidades.add("Electricidad básica");
                if (chkSoldadura.isSelected()) habilidades.add("Soldadura");
                if (chkTecnicaPintura.isSelected()) habilidades.add("Técnicas de pintura");
                if (chkTuberias.isSelected()) habilidades.add("Instalación de tuberías");
                if (chkMantenimiento.isSelected()) habilidades.add("Mantenimiento básico");
                if (chkMaquinaria.isSelected()) habilidades.add("Lectura de planos");
                
                if (habilidades.isEmpty()) {
                    throw new Exception("Seleccione al menos una habilidad");
                }
                infoEstudio = habilidades.toArray(new String[0]);
            } 
            else {
                throw new Exception("Seleccione un nivel de estudio requerido");
            }
            
            // Obtener empresa
            Empresa empresa = Bolsa.getInstance().buscarEmpresaByCod(rnc);
            
            // Crear vacante
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
            
            // Registrar vacante
            Bolsa bolsa = Bolsa.getInstance();
            bolsa.getMisVacantes().add(vacante);
            
            // Asociar vacante a empresa
            empresa.getMisFormulariosEmpresa().add(vacante);
            
            JOptionPane.showMessageDialog(this, "Vacante registrada con éxito!");
            dispose();
        } 
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de salario inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}