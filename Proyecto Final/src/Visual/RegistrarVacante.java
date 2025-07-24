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
        
        Label lblPais = new Label("Pa�s Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 150, 120, 22);
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

        // Listener para cambiar las ciudades cuando se selecciona un pa�s
        cbxPaisResidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paisSeleccionado = (String) cbxPaisResidencia.getSelectedItem();
                if (paisSeleccionado != null && !"Seleccione una Opci�n".equals(paisSeleccionado)) {
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
        
        rdbtnMudanzaSi = new JRadioButton("S�");
        rdbtnMudanzaNo = new JRadioButton("No");
        ButtonGroup grupoMudanza = new ButtonGroup();
        grupoMudanza.add(rdbtnMudanzaSi);
        grupoMudanza.add(rdbtnMudanzaNo);
        panelMudanza.add(rdbtnMudanzaSi);
        panelMudanza.add(rdbtnMudanzaNo);
        rdbtnMudanzaNo.setSelected(true);
        
        Label lblVehiculo = new Label("Veh�culo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(350, 70, 120, 22);
        panelVacante.add(lblVehiculo);
        
        JPanel panelVehiculo = new JPanel();
        panelVehiculo.setBounds(486, 70, 120, 22);
        panelVacante.add(panelVehiculo);
        panelVehiculo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        rdbtnVehiculoSi = new JRadioButton("S�");
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
        
        rdbtnLicenciaSi = new JRadioButton("S�");
        rdbtnLicenciaNo = new JRadioButton("No");
        ButtonGroup grupoLicencia = new ButtonGroup();
        grupoLicencia.add(rdbtnLicenciaSi);
        grupoLicencia.add(rdbtnLicenciaNo);
        panelLicencia.add(rdbtnLicenciaSi);
        panelLicencia.add(rdbtnLicenciaNo);
        rdbtnLicenciaNo.setSelected(true);
        
        Label lblSalario = new Label("Pretensi�n Salarial:");
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
        
        // Grupo para los botones de nivel de estudio (SOLO UNA SELECCI�N)
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
        panelObrero.setBounds(10, 414, 626, 130);
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
        
        // Resto de pa�ses (ejemplos)
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
        
        // Pa�ses sin definir (usar capital como �nica opci�n)
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
            // Por si acaso no est� definido el pa�s
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
    
    private void registrarVacante() {
        try {
            // Validar campos b�sicos
            String identificador = txtIdentificador.getText().trim();
            String nombreVacante = txtNombreVacante.getText().trim();
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            String salarioStr = spnPretensionSalarial.getValue().toString();
            
            // Validar selecci�n de pa�s y ciudad
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
            
            // Validar campos vac�os
            if (identificador.isEmpty() || nombreVacante.isEmpty() || salarioStr.isEmpty()) {
                throw new Exception("Complete todos los campos obligatorios");
            }
            
            // Validar formato del identificador
            if (!identificador.matches("[A-Za-z0-9-]+")) {
                throw new Exception("Identificador solo puede contener letras, n�meros y guiones");
            }
            
            // Validar identificador �nico
            for (Vacante v : Bolsa.getInstance().getMisVacantes()) {
                if (v.getIdentificador().equals(identificador)) {
                    throw new Exception("Ya existe una vacante con este identificador");
                }
            }
            
            // Validar longitud de campos
            if (nombreVacante.length() > 100) {
                throw new Exception("Nombre de vacante no puede exceder 100 caracteres");
            }
            
            // Validar pretensi�n salarial
            float pretensionSalarial = Float.parseFloat(salarioStr);
            if (pretensionSalarial <= 0) {
                throw new Exception("La pretensi�n salarial debe ser mayor que 0");
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
                throw new Exception("Seleccione disponibilidad de veh�culo");
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
                nivelEstudio = "T�cnico Superior";
                String especialidad = (String) cbxEspecialidadTecnico.getSelectedItem();
                if (especialidad.equals("Seleccione una opcion")) {
                    throw new Exception("Seleccione una especialidad t�cnica");
                }
                infoEstudio[0] = especialidad;
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