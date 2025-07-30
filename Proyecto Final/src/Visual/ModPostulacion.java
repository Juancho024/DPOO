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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import Logico.Bolsa;
import Logico.Candidato;
import Logico.Postulacion;
import Logico.Obrero; // Necesario para acceder a las habilidades espec�ficas si las guardas como String[]
import Logico.TecnicoSuperior; // Necesario si usas sus campos directamente para pre-cargar
import Logico.Universitario; // Necesario si usas sus campos directamente para pre-cargar
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ModPostulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Postulacion postulacionActual; // La postulaci�n que se va a modificar

    // --- Componentes de la UI ---
    private JComboBox<String> cbxCandidatos; // Deshabilitado para modificaci�n, pero se usa para mostrar
    private JComboBox<String> cbxTipoContrato;
    private JRadioButton rdbtnMudanzaSi;
    private JRadioButton rdbtnMudanzaNo;
    private JRadioButton rdbtnVehiculoSi;
    private JRadioButton rdbtnVehiculoNo;
    private JRadioButton rdbtnLicenciaSi;
    private JRadioButton rdbtnLicenciaNo;
    private JComboBox<String> cbxPaisResidencia;
    private JComboBox<String> cbxCiudades;
    private Map<String, String[]> ciudadesPorPais = new HashMap<>();
    private JSpinner spnPretensionSalarial;
    private JTextField txtIdentificador;

    // Componentes espec�ficos para Nivel de Estudio
    private JRadioButton rdbtnTecnicoSuperior;
    private JRadioButton rdbtnUniversitario;
    private JRadioButton rdbtnObrero;
    private JPanel panel_Universitario;
    private JPanel panel_TecnicoSuperior;
    private JPanel panel_Obrero;
    private JComboBox<String> cbxTecnicoSuperior;
    private JComboBox<String> cbxUniversitario;
    private JSpinner spnExperiencia;
    private JCheckBox chkElectricidad;
    private JCheckBox chkSoldadura;
    private JCheckBox chkTecnicaPintura;
    private JCheckBox chkTuberias;
    private JCheckBox chkMantenimiento;
    private JCheckBox chkMaquinaria;


    public ModPostulacion(Postulacion post) {
        this.postulacionActual = post; // Guarda la postulaci�n a modificar

        setTitle("Modificar Postulaci�n");
        setBounds(100, 100, 750, 700);
        setLocationRelativeTo(null);
        setModal(true); // Asegura que la ventana sea modal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Inicializar mapa de ciudades por pa�s
        inicializarCiudadesPorPais();

        // --- Resto de la inicializaci�n de la interfaz de usuario ---
        JPanel panelDatos = new JPanel();
        panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Datos de Postulaci�n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelDatos.setBounds(12, 13, 708, 592);
        contentPanel.add(panelDatos);
        panelDatos.setLayout(null);

        // Identificador
        Label lblIdentificador = new Label("Identificador:");
        lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 58, 80, 22);
        panelDatos.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setBounds(10, 80, 331, 22);
        txtIdentificador.setEditable(false); // No editable para el identificador
        panelDatos.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        // Candidato (no editable para una postulaci�n existente)
        Label lblCandidato = new Label("Candidato:");
        lblCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCandidato.setBounds(30, 30, 80, 22);
        panelDatos.add(lblCandidato);

        cbxCandidatos = new JComboBox<>();
        cbxCandidatos.setBounds(112, 30, 569, 22);
        cbxCandidatos.setEnabled(false); // Deshabilitado para modificaci�n
        panelDatos.add(cbxCandidatos);

        // Tipo de Contrato
        Label lblTipoContrato = new Label("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 105, 120, 22);
        panelDatos.add(lblTipoContrato);

        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una Opci�n", "Tiempo Completo", "Medio Tiempo", "Por Proyecto"}));
        cbxTipoContrato.setBounds(10, 133, 331, 22);
        panelDatos.add(cbxTipoContrato);

        // Pa�s y Ciudad de Residencia
        Label lblPais = new Label("Pa�s Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 161, 120, 22);
        panelDatos.add(lblPais);

        cbxPaisResidencia = new JComboBox<>();
        cbxPaisResidencia.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una Opci�n", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per�", "M�xico", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam�", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep�blica Dominicana", "Puerto Rico", "Espa�a", "Estados Unidos", "Canad�", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Jap�n", "Corea del Sur", "China", "India", "Australia", "Sud�frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu�a", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B�lgica", "Pa�ses Bajos", "Nueva Zelanda"}));
        cbxPaisResidencia.setBounds(10, 189, 331, 22);
        panelDatos.add(cbxPaisResidencia);

        Label lblCiudad = new Label("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 217, 120, 22);
        panelDatos.add(lblCiudad);

        cbxCiudades = new JComboBox<>();
        cbxCiudades.setBounds(10, 245, 331, 22);
        panelDatos.add(cbxCiudades);
        cbxCiudades.setEnabled(false); // Inicialmente deshabilitado

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

        // Pretensi�n Salarial
        Label lblSalario = new Label("Pretensi�n Salarial:");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(10, 273, 120, 22);
        panelDatos.add(lblSalario);

        spnPretensionSalarial = new JSpinner();
        spnPretensionSalarial.setModel(new SpinnerNumberModel(0.0f, 0.0f, null, 100.0f)); // Usar float
        spnPretensionSalarial.setBounds(10, 301, 331, 22);
        panelDatos.add(spnPretensionSalarial);

        // Panel Nivel de Estudio
        JPanel panelNivelEstudio = new JPanel();
        panelNivelEstudio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Nivel de Estudio ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelNivelEstudio.setBounds(10, 372, 686, 61);
        panelDatos.add(panelNivelEstudio);
        panelNivelEstudio.setLayout(null);

        rdbtnUniversitario = new JRadioButton("Universitario");
        rdbtnUniversitario.addActionListener(e -> toggleNivelEstudioPanels(true, false, false));
        rdbtnUniversitario.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnUniversitario.setBounds(77, 27, 109, 23);
        panelNivelEstudio.add(rdbtnUniversitario);

        rdbtnTecnicoSuperior = new JRadioButton("T�cnico Superior");
        rdbtnTecnicoSuperior.addActionListener(e -> toggleNivelEstudioPanels(false, true, false));
        rdbtnTecnicoSuperior.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnTecnicoSuperior.setBounds(263, 27, 133, 23);
        panelNivelEstudio.add(rdbtnTecnicoSuperior);

        rdbtnObrero = new JRadioButton("Obrero");
        rdbtnObrero.addActionListener(e -> toggleNivelEstudioPanels(false, false, true));
        rdbtnObrero.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdbtnObrero.setBounds(473, 27, 133, 23);
        panelNivelEstudio.add(rdbtnObrero);

        ButtonGroup grupoNivelEstudio = new ButtonGroup();
        grupoNivelEstudio.add(rdbtnUniversitario);
        grupoNivelEstudio.add(rdbtnTecnicoSuperior);
        grupoNivelEstudio.add(rdbtnObrero);

        setupPanelesNivelEstudio(panelDatos); // Configura los paneles espec�ficos de nivel de estudio

        // Radio Buttons Adicionales
        Label lblMudanza = new Label("Dispuesto a Mudarse:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(10, 336, 134, 22);
        panelDatos.add(lblMudanza);

        JPanel panelMudanza = new JPanel();
        panelMudanza.setBounds(138, 336, 100, 23);
        panelDatos.add(panelMudanza);
        panelMudanza.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        rdbtnMudanzaSi = new JRadioButton("S�");
        rdbtnMudanzaNo = new JRadioButton("No");
        ButtonGroup grupoMudanza = new ButtonGroup();
        grupoMudanza.add(rdbtnMudanzaSi);
        grupoMudanza.add(rdbtnMudanzaNo);
        panelMudanza.add(rdbtnMudanzaSi);
        panelMudanza.add(rdbtnMudanzaNo);

        Label lblVehiculo = new Label("Veh�culo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(258, 336, 100, 22);
        panelDatos.add(lblVehiculo);
        JPanel panelVehiculo = new JPanel();
        panelVehiculo.setBounds(353, 336, 100, 23);
        panelDatos.add(panelVehiculo);
        panelVehiculo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        rdbtnVehiculoSi = new JRadioButton("S�");
        rdbtnVehiculoNo = new JRadioButton("No");
        ButtonGroup grupoVehiculo = new ButtonGroup();
        grupoVehiculo.add(rdbtnVehiculoSi);
        grupoVehiculo.add(rdbtnVehiculoNo);
        panelVehiculo.add(rdbtnVehiculoSi);
        panelVehiculo.add(rdbtnVehiculoNo);

        Label lblLicencia = new Label("Licencia Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(470, 336, 120, 22);
        panelDatos.add(lblLicencia);
        JPanel panelLicencia = new JPanel();
        panelLicencia.setBounds(581, 336, 100, 23);
        panelDatos.add(panelLicencia);
        panelLicencia.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        rdbtnLicenciaSi = new JRadioButton("S�");
        rdbtnLicenciaNo = new JRadioButton("No");
        ButtonGroup grupoLicencia = new ButtonGroup();
        grupoLicencia.add(rdbtnLicenciaSi);
        grupoLicencia.add(rdbtnLicenciaNo);
        panelLicencia.add(rdbtnLicenciaSi);
        panelLicencia.add(rdbtnLicenciaNo);

        JPanel panelFoto = new JPanel();
        panelFoto.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelFoto.setBounds(353, 65, 328, 258);
        panelDatos.add(panelFoto);
        panelFoto.setLayout(null);
        
        Label lbFoto = new Label("Imagen de Candidato: ");
        lbFoto.setBounds(94, 5, 139, 23);
        panelFoto.add(lbFoto);
        lbFoto.setFont(new Font("Tahoma", Font.BOLD, 12));

        // --- Botones ---
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarPostulacion()); // Llama al m�todo de modificaci�n
        buttonPane.add(btnModificar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        // --- Llamada al m�todo para pre-cargar los datos ---
        preCargarDatosPostulacion();
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
        String[] ciudades = ciudadesPorPais.getOrDefault(pais, new String[]{pais});
        cbxCiudades.setModel(new DefaultComboBoxModel<>(ciudades));
    }

    // M�todo para pre-cargar los datos de la postulaci�n
    private void preCargarDatosPostulacion() {
        if (postulacionActual != null) {
            txtIdentificador.setText(postulacionActual.getIdentificador());

            // Candidato: Buscar el candidato y seleccionarlo en el ComboBox
            // Como el cbxCandidatos est� deshabilitado, solo necesitamos seleccionar el correcto
            Candidato candidatoPostulacion = Bolsa.getInstance().buscarCandidatoByCod(postulacionActual.getCedulaCliente());
            if (candidatoPostulacion != null) {
                cbxCandidatos.addItem(candidatoPostulacion.getNombre() + " - " + candidatoPostulacion.getCedula());
                cbxCandidatos.setSelectedItem(candidatoPostulacion.getNombre() + " - " + candidatoPostulacion.getCedula());
            }

            // Tipo de Contrato
            cbxTipoContrato.setSelectedItem(postulacionActual.getTipoContrato());

            // Pa�s y Ciudad
            cbxPaisResidencia.setSelectedItem(postulacionActual.getPaisResidencia());
            cargarCiudadesPorPais(postulacionActual.getPaisResidencia()); // Asegurar que las ciudades se carguen
            cbxCiudades.setSelectedItem(postulacionActual.getCiudadResidencia());

            // Pretensi�n Salarial
            spnPretensionSalarial.setValue(postulacionActual.getPretensionSalarial());

            // Opciones de Radio Buttons
            rdbtnMudanzaSi.setSelected(postulacionActual.isMudanza());
            rdbtnMudanzaNo.setSelected(!postulacionActual.isMudanza());
            rdbtnVehiculoSi.setSelected(postulacionActual.isDisponibilidadVehiculo());
            rdbtnVehiculoNo.setSelected(!postulacionActual.isDisponibilidadVehiculo());
            rdbtnLicenciaSi.setSelected(postulacionActual.isLicencia());
            rdbtnLicenciaNo.setSelected(!postulacionActual.isLicencia());

            // Nivel de Estudio y sus detalles
            String nivelEstudio = postulacionActual.getNivelEstudio();
            String detalleNivelEstudio = postulacionActual.getDetalleNivelEstudio();
            int aniosExperiencia = postulacionActual.getAniosExperiencia();

            if ("Universitario".equals(nivelEstudio)) {
                rdbtnUniversitario.setSelected(true);
                toggleNivelEstudioPanels(true, false, false);
                cbxUniversitario.setSelectedItem(detalleNivelEstudio);
            } else if ("T�cnico Superior".equals(nivelEstudio)) {
                rdbtnTecnicoSuperior.setSelected(true);
                toggleNivelEstudioPanels(false, true, false);
                cbxTecnicoSuperior.setSelectedItem(detalleNivelEstudio);
                spnExperiencia.setValue(aniosExperiencia);
            } else if ("Obrero".equals(nivelEstudio)) {
                rdbtnObrero.setSelected(true);
                toggleNivelEstudioPanels(false, false, true);
                // Para obrero, las habilidades est�n en un String separado por comas
                String[] habilidades = detalleNivelEstudio.split(", ");
                for (String habilidad : habilidades) {
                    if ("Electricidad b�sica".equals(habilidad)) chkElectricidad.setSelected(true);
                    else if ("Soldadura".equals(habilidad)) chkSoldadura.setSelected(true);
                    else if ("T�cnicas de pintura o alba�iler�a".equals(habilidad)) chkTecnicaPintura.setSelected(true);
                    else if ("Instalaci�n de tuber�as".equals(habilidad)) chkTuberias.setSelected(true);
                    else if ("Mantenimiento b�sico de equipos".equals(habilidad)) chkMantenimiento.setSelected(true);
                    else if ("Lectura de planos".equals(habilidad)) chkMaquinaria.setSelected(true);
                }
            }
        }
    }

    private void toggleNivelEstudioPanels(boolean showUni, boolean showTec, boolean showObr) {
        panel_Universitario.setVisible(showUni);
        panel_TecnicoSuperior.setVisible(showTec);
        panel_Obrero.setVisible(showObr);
    }

    private void setupPanelesNivelEstudio(JPanel parentPanel) {
        // Panel Universitario
        panel_Universitario = new JPanel();
        panel_Universitario.setBorder(new TitledBorder(null, " Universitario ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_Universitario.setBounds(10, 430, 686, 140);
        parentPanel.add(panel_Universitario);
        panel_Universitario.setLayout(null);
        Label lblCarrera = new Label("Nombre de la Carrera: ");
        lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCarrera.setBounds(55, 29, 150, 22);
        panel_Universitario.add(lblCarrera);
        cbxUniversitario = new JComboBox<>();
        cbxUniversitario.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una Opci�n", "Administraci�n de Empresas", "Contabilidad", "Econom�a", "Marketing", "Ingenier�a en Sistemas / Inform�tica", "Ingenier�a Civil", "Ingenier�a Industrial", "Medicina", "Derecho", "Psicolog�a"}));
        cbxUniversitario.setBounds(55, 58, 619, 22);
        panel_Universitario.add(cbxUniversitario);

        // Panel T�cnico Superior
        panel_TecnicoSuperior = new JPanel();
        panel_TecnicoSuperior.setBorder(new TitledBorder(null, "T�cnico Superior ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_TecnicoSuperior.setBounds(10, 430, 686, 140);
        parentPanel.add(panel_TecnicoSuperior);
        panel_TecnicoSuperior.setLayout(null);
        Label lblTecnico = new Label("�rea del T�cnico:");
        lblTecnico.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTecnico.setBounds(27, 25, 212, 22);
        panel_TecnicoSuperior.add(lblTecnico);
        cbxTecnicoSuperior = new JComboBox<>();
        cbxTecnicoSuperior.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una Opci�n", "T�cnico en Inform�tica", "T�cnico en Contabilidad", "T�cnico en Electr�nica", "T�cnico en Electricidad", "T�cnico en Desarrollo de Software"}));
        cbxTecnicoSuperior.setBounds(27, 56, 212, 22);
        panel_TecnicoSuperior.add(cbxTecnicoSuperior);
        Label lblExperiencia = new Label("A�os de Experiencia laboral:");
        lblExperiencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblExperiencia.setBounds(310, 25, 212, 22);
        panel_TecnicoSuperior.add(lblExperiencia);
        spnExperiencia = new JSpinner();
        spnExperiencia.setModel(new SpinnerNumberModel(0, 0, 50, 1));
        spnExperiencia.setBounds(310, 56, 212, 22);
        panel_TecnicoSuperior.add(spnExperiencia);

        // Panel Obrero
        panel_Obrero = new JPanel();
        panel_Obrero.setBorder(new TitledBorder(null, " Obrero ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_Obrero.setBounds(10, 430, 686, 140);
        parentPanel.add(panel_Obrero);
        panel_Obrero.setLayout(null);
        
        Label lblHabilidades = new Label("Habilidades Requeridas: ");
        lblHabilidades.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblHabilidades.setBounds(25, 22, 150, 22);
        panel_Obrero.add(lblHabilidades);
        
        chkElectricidad = new JCheckBox("Electricidad b�sica");
        chkElectricidad.setBounds(25, 50, 133, 23);       // Col1, Fila1
        panel_Obrero.add(chkElectricidad);
        
        chkSoldadura = new JCheckBox("Soldadura");
        chkSoldadura.setBounds(25, 76, 94, 23);          // Col1, Fila2
        panel_Obrero.add(chkSoldadura);
        
        chkTecnicaPintura = new JCheckBox("T�cnicas de pintura o alba�iler�a");
        chkTecnicaPintura.setBounds(205, 50, 215, 23);   // Col2, Fila1
        panel_Obrero.add(chkTecnicaPintura);
        
        chkTuberias = new JCheckBox("Instalaci�n de tuber�as");
        chkTuberias.setBounds(205, 76, 164, 23);          // Col2, Fila2
        panel_Obrero.add(chkTuberias);
        
        chkMantenimiento = new JCheckBox("Mantenimiento b�sico de equipos");
        chkMantenimiento.setBounds(425, 50, 247, 23);    // Col3, Fila1
        panel_Obrero.add(chkMantenimiento);
        
        chkMaquinaria = new JCheckBox("Lectura de planos");
        chkMaquinaria.setBounds(425, 76, 247, 23);       // Col3, Fila2
        panel_Obrero.add(chkMaquinaria);

        // Asegurarse de que los paneles est�n ocultos al inicio hasta que se seleccione el tipo de estudio
        panel_Universitario.setVisible(false);
        panel_TecnicoSuperior.setVisible(false);
        panel_Obrero.setVisible(false);
    }
    
    private void modificarPostulacion() {
        try {
            // Validaciones (similares a RegistrarPostulacion, pero adaptadas para modificaci�n)
            // El candidato no se valida porque su cbx est� deshabilitado.

            if (cbxTipoContrato.getSelectedIndex() == 0) throw new Exception("Seleccione un tipo de contrato.");
            if (cbxPaisResidencia.getSelectedIndex() == 0) throw new Exception("Seleccione un pa�s.");
            if (cbxCiudades.getSelectedIndex() == -1 || cbxCiudades.getSelectedItem().toString().isEmpty()) throw new Exception("Seleccione una ciudad.");

            float pretensionSalarial = ((Number) spnPretensionSalarial.getValue()).floatValue();
            if (pretensionSalarial <= 0) {
                throw new Exception("La pretensi�n salarial debe ser mayor que 0.");
            }

            String nivelEstudio = "";
            String detalleNivelEstudio = "";
            int aniosExperiencia = 0; // Solo aplica para T�cnico Superior

            if (rdbtnUniversitario.isSelected()) {
                nivelEstudio = "Universitario";
                if(cbxUniversitario.getSelectedIndex() == 0) throw new Exception("Seleccione una carrera universitaria.");
                detalleNivelEstudio = (String) cbxUniversitario.getSelectedItem();
            } else if (rdbtnTecnicoSuperior.isSelected()) {
                nivelEstudio = "T�cnico Superior";
                if(cbxTecnicoSuperior.getSelectedIndex() == 0) throw new Exception("Seleccione un �rea t�cnica.");
                detalleNivelEstudio = (String) cbxTecnicoSuperior.getSelectedItem();
                aniosExperiencia = (Integer) spnExperiencia.getValue();
            } else if (rdbtnObrero.isSelected()) {
                nivelEstudio = "Obrero";
                if (!chkElectricidad.isSelected() && !chkSoldadura.isSelected() && !chkTecnicaPintura.isSelected() &&
                    !chkTuberias.isSelected() && !chkMantenimiento.isSelected() && !chkMaquinaria.isSelected()) {
                    throw new Exception("Seleccione al menos una habilidad para el obrero.");
                }
                ArrayList<String> oficios = new ArrayList<>();
                if (chkElectricidad.isSelected()) oficios.add("Electricidad b�sica");
                if (chkSoldadura.isSelected()) oficios.add("Soldadura");
                if (chkTecnicaPintura.isSelected()) oficios.add("T�cnicas de pintura o alba�iler�a");
                if (chkTuberias.isSelected()) oficios.add("Instalaci�n de tuber�as");
                if (chkMantenimiento.isSelected()) oficios.add("Mantenimiento b�sico de equipos");
                if (chkMaquinaria.isSelected()) oficios.add("Lectura de planos");
                detalleNivelEstudio = String.join(", ", oficios);
            } else {
                throw new Exception("Seleccione un nivel de estudio.");
            }

            boolean mudanza = rdbtnMudanzaSi.isSelected();
            boolean vehiculo = rdbtnVehiculoSi.isSelected();
            boolean licencia = rdbtnLicenciaSi.isSelected();
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            String pais = (String) cbxPaisResidencia.getSelectedItem();
            String ciudad = (String) cbxCiudades.getSelectedItem();

            // Actualizar la postulaci�n existente (postulacionActual es la referencia al objeto original)
            postulacionActual.setNivelEstudio(nivelEstudio);
            postulacionActual.setDetalleNivelEstudio(detalleNivelEstudio);
            postulacionActual.setAniosExperiencia(aniosExperiencia); // Este campo ahora est� en Postulacion
            postulacionActual.setTipoContrato(tipoContrato);
            postulacionActual.setPaisResidencia(pais);
            postulacionActual.setCiudadResidencia(ciudad);
            postulacionActual.setMudanza(mudanza);
            postulacionActual.setDisponibilidadVehiculo(vehiculo);
            postulacionActual.setLicencia(licencia);
            postulacionActual.setPretensionSalarial(pretensionSalarial);
            // El status no se modifica desde aqu�, asumo que se hace en otro lugar si es necesario.

            JOptionPane.showMessageDialog(this, "�Postulaci�n modificada con �xito!");
            dispose(); // Cierra la ventana despu�s de modificar
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de salario inv�lido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Validaci�n", JOptionPane.ERROR_MESSAGE);
        }
    }
}