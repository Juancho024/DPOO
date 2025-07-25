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
import Logico.Vacante;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RegistrarPostulacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<Vacante> cbxVacantes;
	private JComboBox<String> cbxTipoContrato;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JComboBox cbxCandidatos;
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
	private String cedula;

	public static void main(String[] args) {
		try {
			RegistrarPostulacion dialog = new RegistrarPostulacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarPostulacion() {
		setTitle("Registrar Postulación");
		setBounds(100, 100, 601, 528);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Inicializar mapa de ciudades por país
		inicializarCiudadesPorPais();

		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
				"Datos de Postulación", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatos.setBounds(12, 13, 564, 420);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		Label lblCandidato = new Label("Candidato:");
		lblCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCandidato.setBounds(10, 30, 100, 22);
		panelDatos.add(lblCandidato);

		cbxCandidatos = new JComboBox<>();
		cbxCandidatos.setBounds(152, 30, 400, 22);
		panelDatos.add(cbxCandidatos);

		Label lblVacante = new Label("Vacante:");
		lblVacante.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVacante.setBounds(10, 70, 100, 22);
		panelDatos.add(lblVacante);

		cbxVacantes = new JComboBox<>();
		cbxVacantes.setBounds(152, 70, 400, 22);
		panelDatos.add(cbxVacantes);

		Label lblTipoContrato = new Label("Tipo de Contrato:");
		lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoContrato.setBounds(10, 110, 120, 22);
		panelDatos.add(lblTipoContrato);

		cbxTipoContrato = new JComboBox<>();
		cbxTipoContrato.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opción", "Tiempo Completo", "Medio Tiempo", "Por Proyecto"}));
		cbxTipoContrato.setBounds(152, 110, 200, 22);
		panelDatos.add(cbxTipoContrato);

		Label lblPais = new Label("País Residencia:");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPais.setBounds(10, 150, 120, 22);
		panelDatos.add(lblPais);

		cbxPaisResidencia = new JComboBox<>();
		cbxPaisResidencia.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opción", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Perú", "México", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panamá", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "República Dominicana", "Puerto Rico", "España", "Estados Unidos", "Canadá", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Japón", "Corea del Sur", "China", "India", "Australia", "Sudáfrica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turquía", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "Bélgica", "Países Bajos", "Nueva Zelanda"}));
		cbxPaisResidencia.setBounds(152, 150, 200, 22);
		panelDatos.add(cbxPaisResidencia);

		Label lblCiudad = new Label("Ciudad Residencia:");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCiudad.setBounds(10, 190, 120, 22);
		panelDatos.add(lblCiudad);

		cbxCiudades = new JComboBox<>();
		cbxCiudades.setBounds(152, 190, 200, 22);
		panelDatos.add(cbxCiudades);
		cbxCiudades.setEnabled(false); // Inicialmente deshabilitado

		// Listener para cambiar las ciudades cuando se selecciona un país
		cbxPaisResidencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String paisSeleccionado = (String) cbxPaisResidencia.getSelectedItem();
				if (!"Seleccione una Opción".equals(paisSeleccionado)) {
					cargarCiudadesPorPais(paisSeleccionado);
					cbxCiudades.setEnabled(true);
				} else {
					cbxCiudades.setModel(new DefaultComboBoxModel<>());
					cbxCiudades.setEnabled(false);
				}
			}
		});

		Label lblMudanza = new Label("Dispuesto a Mudarse");
		lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMudanza.setBounds(10, 280, 134, 22);
		panelDatos.add(lblMudanza);

		JPanel panelMudanza = new JPanel();
		panelMudanza.setBounds(152, 279, 100, 23);
		panelDatos.add(panelMudanza);
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
		lblVehiculo.setBounds(10, 317, 120, 22);
		panelDatos.add(lblVehiculo);

		JPanel panelVehiculo = new JPanel();
		panelVehiculo.setBounds(152, 316, 100, 23);
		panelDatos.add(panelVehiculo);
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
		lblLicencia.setBounds(10, 356, 120, 22);
		panelDatos.add(lblLicencia);

		JPanel panelLicencia = new JPanel();
		panelLicencia.setBounds(152, 355, 100, 23);
		panelDatos.add(panelLicencia);
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
		lblSalario.setBounds(10, 231, 120, 22);
		panelDatos.add(lblSalario);

		spnPretensionSalarial = new JSpinner();
		spnPretensionSalarial.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10)));
		spnPretensionSalarial.setBounds(152, 231, 200, 22);
		panelDatos.add(spnPretensionSalarial);

		// Cargar datos iniciales
		cargarCandidatos();
		cargarVacantes();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPostulacion();
			}
		});
		buttonPane.add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);
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

	private void cargarCandidatos() {
		cbxCandidatos.removeAllItems();
		cbxCandidatos.addItem("Seleccione una Opción");
		for (Candidato candidato : Bolsa.getInstance().getMisCandidatos()) {
			cbxCandidatos.addItem(candidato.getNombre() +" - "+ candidato.getCedula());
		}
	}

	private void cargarVacantes() {
		cbxVacantes.removeAllItems();
		for (Vacante vacante : Bolsa.getInstance().getMisVacantes()) {
			cbxVacantes.addItem(vacante);
		}
	}

	private void registrarPostulacion() {
		try {
			// Validar selección de candidato y vacante
			if (cbxCandidatos.getSelectedItem() == null) {
				throw new Exception("Seleccione un candidato");
			}

			if (cbxVacantes.getSelectedItem() == null) {
				throw new Exception("Seleccione una vacante");
			}
			if (cbxCandidatos.getSelectedItem() != null) {
				String seleccion = cbxCandidatos.getSelectedItem().toString();
				String[] partes = seleccion.split(" - ");
				if (partes.length == 2) {
					cedula = partes[1];
					// Usa la cédula aquí
				}
			}

			Candidato candidato = Bolsa.getInstance().buscarCandidatoByCod(cedula);
			Vacante vacante = (Vacante) cbxVacantes.getSelectedItem();

			// Validar país seleccionado
			if (cbxPaisResidencia.getSelectedIndex() == 0) {
				throw new Exception("Seleccione un país");
			}

			// Validar ciudad seleccionada
			if (cbxCiudades.getSelectedIndex() == -1) {
				throw new Exception("Seleccione una ciudad");
			}

			// Validar campos obligatorios
			String pais = cbxPaisResidencia.getSelectedItem().toString();
			String ciudad = cbxCiudades.getSelectedItem().toString();
			String salarioStr = spnPretensionSalarial.getValue().toString();

			if (pais.isEmpty() || ciudad.isEmpty() || salarioStr.isEmpty()) {
				throw new Exception("Complete todos los campos obligatorios");
			}

			// Validar formato de país y ciudad
			if (!pais.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
				throw new Exception("País contiene caracteres inválidos");
			}

			if (!ciudad.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
				throw new Exception("Ciudad contiene caracteres inválidos");
			}

			// Validar longitud de campos
			if (pais.length() > 50) {
				throw new Exception("País no puede exceder 50 caracteres");
			}

			if (ciudad.length() > 50) {
				throw new Exception("Ciudad no puede exceder 50 caracteres");
			}

			float pretensionSalarial = Float.parseFloat(salarioStr);
			if (pretensionSalarial <= 0) {
				throw new Exception("La pretensión salarial debe ser mayor que 0");
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

			String tipoContrato = (String) cbxTipoContrato.getSelectedItem();

			// Determinar nivel de estudio del candidato
			String nivelEstudio = "";
			if (candidato instanceof Logico.Universitario) {
				nivelEstudio = "Universitario";
			} else if (candidato instanceof Logico.TecnicoSuperior) {
				nivelEstudio = "Técnico Superior";
			} else if (candidato instanceof Logico.Obrero) {
				nivelEstudio = "Obrero";
			}

			// Crear postulación
			Postulacion postulacion = new Postulacion(
					"", candidato.getCedula(),   // Usar cédula como identificador
					nivelEstudio,
					tipoContrato,
					pais,
					ciudad,
					mudanza,
					vehiculo,
					licencia,
					pretensionSalarial, true
					);

			// Registrar en bolsa
			Bolsa bolsa = Bolsa.getInstance();
			bolsa.getMisPostulaciones().add(postulacion);

			// Agregar postulación al candidato
			candidato.getMisPostulaciones().add(postulacion);

			JOptionPane.showMessageDialog(this, "Postulación registrada con éxito!");
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Formato de salario inválido", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}