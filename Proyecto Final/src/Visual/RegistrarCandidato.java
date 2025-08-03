package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.Obrero;
import Logico.TecnicoSuperior;
import Logico.Universitario;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Canvas;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RegistrarCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JRadioButton rdbtnTecnicoSuperior;
	private JRadioButton rdbtnUniversitario;
	private JRadioButton rdbtnObrero;
	private JPanel panel_Universitario;
	private JPanel panel_TecnicoSuperior;
	private JPanel panel_Obrero;
	private char genero;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private Label label_6;
	private JCheckBox chkElectricidad;
	private JCheckBox chkSoldadura;
	private JCheckBox chkTecnicaPintura;
	private JCheckBox chkTuberias;
	private JCheckBox chkMantenimiento;
	private JCheckBox chkMaquinaria;
	private Label label_7;
	private JComboBox cbxTecnicoSuperior;
	private JComboBox cbxUniversitario;
	private JSpinner spnFecha;
	private Label label_10;
	private JSpinner spnExperiencia;
	private Button btnFoto;
	private Label label_11;
	private JComboBox cbxNacionalidad;
	private JLabel lbImagen;
	private byte[] imagenActual;
	private JPanel panel_2;
	private Label label_12;
	private Label label_13;
	private Label label_14;
	private Label label_15;
	private Label label_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCandidato dialog = new RegistrarCandidato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCandidato() {
		setTitle("Registrar Candidato");
		setBounds(100, 100, 600, 668);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);

		JPanel panel = new JPanelRedondeado(60);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 94, 564, 255);
		contentPanel.add(panel);
		panel.setLayout(null);

		Label label_2 = new Label("Apellido: ");
		label_2.setBounds(10, 113, 62, 22);
		panel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		Label label_5 = new Label("Correo: ");
		label_5.setBounds(256, 154, 62, 22);
		panel.add(label_5);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCorreo = new JTextField();
		txtCorreo.setBackground(SystemColor.inactiveCaptionBorder);
		txtCorreo.setBounds(324, 154, 228, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBackground(SystemColor.inactiveCaptionBorder);
		txtApellido.setBounds(76, 113, 170, 22);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		Label label = new Label("Cedula: ");
		label.setBounds(10, 31, 49, 22);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula = new JTextField();
		txtCedula.setBackground(SystemColor.inactiveCaptionBorder);
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letras = e.getKeyChar();
				String text = txtCedula.getText();
				if(text.length() >= 13) {
					e.consume();
				}
				if(letras == '\b') {
					return;
				}
				if(text.length() == 3 || text.length() == 11) {
					txtCedula.setText(text + "-");
				}
				if(!Character.isDigit(letras)) {
					e.consume();
				}
			}
		});
		txtCedula.setBounds(76, 31, 170, 22);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		Label label_1 = new Label("Nombre:");
		label_1.setBounds(10, 70, 62, 22);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		Label label_4 = new Label("Telefono: ");
		label_4.setBounds(10, 154, 62, 22);
		panel.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBackground(SystemColor.inactiveCaption);
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(true);
				rdbtnFemenino.setSelected(false);
				genero = 'M';
			}
		});
		rdbtnMasculino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnMasculino.setBounds(324, 197, 86, 23);
		panel.add(rdbtnMasculino);

		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBackground(SystemColor.inactiveCaption);
		rdbtnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(true);
				genero = 'F';
			}
		});
		rdbtnFemenino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFemenino.setBounds(435, 197, 109, 23);
		panel.add(rdbtnFemenino);

		Label label_3 = new Label("Genero: ");
		label_3.setBounds(257, 197, 61, 22);
		panel.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));

		Label label_9 = new Label("Fecha de Nacimiento: ");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(10, 197, 132, 22);
		panel.add(label_9);

		spnFecha = new JSpinner();
		spnFecha.setBackground(SystemColor.inactiveCaptionBorder);
		spnFecha.setModel(new SpinnerDateModel(new Date(1167638400000L), new Date(-788889600000L), new Date(1199174400000L), Calendar.DAY_OF_YEAR));
		spnFecha.setBounds(148, 197, 101, 20);
		panel.add(spnFecha);

		JSpinner.DateEditor editorfecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
		spnFecha.setEditor(editorfecha);

		txtTelefono = new JTextField();
		txtTelefono.setBackground(SystemColor.inactiveCaptionBorder);
		txtTelefono.setBounds(76, 154, 170, 22);
		panel.add(txtTelefono);
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letras = e.getKeyChar();
				String text = txtTelefono.getText();
				if(text.length() >= 12) {
					e.consume();
				}
				if(letras == '\b') {
					return;
				}
				if(text.length() == 3 || text.length() == 7) {
					txtTelefono.setText(text + "-");
				}
				if(!Character.isDigit(letras)) {
					e.consume();
				}
			}
		});
		txtTelefono.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBackground(SystemColor.inactiveCaptionBorder);
		txtNombre.setBounds(76, 70, 170, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		btnFoto = new Button("Colocar Foto");
		btnFoto.setForeground(Color.WHITE);
		btnFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFoto.setBackground(Color.LIGHT_GRAY);
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (.png, .jpg, .jpeg)", "png", "jpg", "jpeg");
				chooser.setFileFilter(filtro);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File archivo = chooser.getSelectedFile();
					String nombreArchivo = archivo.getName().toLowerCase();

					if (!(nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg"))) {
						JOptionPane.showMessageDialog(null, "Solo se permiten imágenes JPG o PNG", "Formato de Imagen no Valido", JOptionPane.ERROR_MESSAGE);
						return;
					}

					try (FileInputStream fis = new FileInputStream(archivo);
							ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

						byte[] buffer = new byte[1024];
						int bytesRead;
						while ((bytesRead = fis.read(buffer)) != -1) {
							bos.write(buffer, 0, bytesRead);
						}

						imagenActual = bos.toByteArray();

						ImageIcon iconoOriginal = new ImageIcon(imagenActual);
						Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						lbImagen.setIcon(new ImageIcon(imagenEscalada));
						lbImagen.setText("");

					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Error al cargar la imagen");
					}
				}
			}
		});
		btnFoto.setBounds(392, 128, 90, 22);
		panel.add(btnFoto);

		label_11 = new Label("Nacionalidad:");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(108, 226, 101, 22);
		panel.add(label_11);

		cbxNacionalidad = new JComboBox();
		cbxNacionalidad.setBackground(SystemColor.inactiveCaptionBorder);
		cbxNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per\u00FA", "M\u00E9xico", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam\u00E1", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep\u00FAblica Dominicana", "Puerto Rico", "Espa\u00F1a", "Estados Unidos", "Canad\u00E1", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Jap\u00F3n", "Corea del Sur", "China", "India", "Australia", "Sud\u00E1frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu\u00EDa", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B\u00E9lgica", "Pa\u00EDses Bajos", "Nueva Zelanda"}));
		cbxNacionalidad.setBounds(215, 224, 170, 20);
		panel.add(cbxNacionalidad);

		lbImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
		lbImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbImagen.setBounds(378, 23, 117, 99);
		panel.add(lbImagen);

		label_13 = new Label("Datos Personales");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_13.setAlignment(Label.CENTER);
		label_13.setBounds(216, 0, 132, 21);
		panel.add(label_13);


		JPanel panel_1 = new JPanelRedondeado(60);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 360, 564, 61);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		rdbtnUniversitario = new JRadioButton("Universitario");
		rdbtnUniversitario.setBackground(SystemColor.inactiveCaption);
		rdbtnUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnicoSuperior.setSelected(false);
				rdbtnUniversitario.setSelected(true);
				rdbtnObrero.setSelected(false);
				panel_Obrero.setVisible(false);
				panel_Universitario.setVisible(true);
				panel_TecnicoSuperior.setVisible(false);

			}
		});
		rdbtnUniversitario.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnUniversitario.setBounds(32, 27, 109, 23);
		panel_1.add(rdbtnUniversitario);

		rdbtnTecnicoSuperior = new JRadioButton("T\u00E9cnico Superior");
		rdbtnTecnicoSuperior.setBackground(SystemColor.inactiveCaption);
		rdbtnTecnicoSuperior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnicoSuperior.setSelected(true);
				rdbtnUniversitario.setSelected(false);
				rdbtnObrero.setSelected(false);
				panel_Obrero.setVisible(false);
				panel_Universitario.setVisible(false);
				panel_TecnicoSuperior.setVisible(true);
			}
		});
		rdbtnTecnicoSuperior.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTecnicoSuperior.setBounds(186, 27, 133, 23);
		panel_1.add(rdbtnTecnicoSuperior);

		rdbtnObrero = new JRadioButton("Obrero");
		rdbtnObrero.setBackground(SystemColor.inactiveCaption);
		rdbtnObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnicoSuperior.setSelected(false);
				rdbtnUniversitario.setSelected(false);
				rdbtnObrero.setSelected(true);
				panel_Obrero.setVisible(true);
				panel_Universitario.setVisible(false);
				panel_TecnicoSuperior.setVisible(false);

			}
		});
		rdbtnObrero.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnObrero.setBounds(370, 27, 133, 23);
		panel_1.add(rdbtnObrero);

		label_12 = new Label("Nivel de Estudio");
		label_12.setAlignment(Label.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_12.setBounds(216, 0, 132, 21);
		panel_1.add(label_12);

		panel_Universitario = new JPanelRedondeado(60);
		panel_Universitario.setBackground(SystemColor.inactiveCaption);
		panel_Universitario.setBounds(10, 432, 564, 138);
		contentPanel.add(panel_Universitario);
		panel_Universitario.setLayout(null);

		label_7 = new Label("Nombre de la Carrera: ");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(55, 29, 139, 22);
		panel_Universitario.add(label_7);

		cbxUniversitario = new JComboBox();
		cbxUniversitario.setBackground(SystemColor.inactiveCaptionBorder);
		cbxUniversitario.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Administraci\u00F3n de Empresas", "Contabilidad", "Econom\u00EDa", "Marketing", "Finanzas", "Psicolog\u00EDa", "Derecho", "Educaci\u00F3n / Pedagog\u00EDa", "Trabajo Social", "Comunicaci\u00F3n Social / Periodismo", "Relaciones Internacionales", "Ingenier\u00EDa en Sistemas / Inform\u00E1tica", "Ingenier\u00EDa Civil", "Ingenier\u00EDa Industrial", "Ingenier\u00EDa El\u00E9ctrica / Electr\u00F3nica", "Medicina", "Enfermer\u00EDa", "Odontolog\u00EDa", "Farmacia", "Nutrici\u00F3n", "Fisioterapia", "Veterinaria", "Ciencias de la Computaci\u00F3n", "Desarrollo de Software", "Ciberseguridad", "Arquitectura", "Dise\u00F1o Gr\u00E1fico", "Dise\u00F1o Industrial", "Turismo y Hoteler\u00EDa", "Gastronom\u00EDa / Artes Culinarias"}));
		cbxUniversitario.setBounds(55, 58, 219, 20);
		panel_Universitario.add(cbxUniversitario);

		label_16 = new Label("Universitario");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_16.setAlignment(Label.CENTER);
		label_16.setBounds(216, 0, 132, 21);
		panel_Universitario.add(label_16);
		{
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.setForeground(Color.WHITE);
			btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnRegistrar.setBackground(SystemColor.textHighlight);
			btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnRegistrar.setBounds(237, 581, 119, 35);
			contentPanel.add(btnRegistrar);
			btnRegistrar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Candidato aux = null;
					String cedula = txtCedula.getText();
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String telefono = txtTelefono.getText();
					String correo = txtCorreo.getText();
					Date fechaNacimiento = (Date) spnFecha.getValue();
					String nacionalidad = cbxNacionalidad.getSelectedItem().toString();

					if(rdbtnUniversitario.isSelected()) {
						if(genero != 'M' && genero != 'F'|| cedula.isEmpty() || 
								nombre.isEmpty() || 
								apellido.isEmpty() || 
								telefono.isEmpty() || 
								correo.isEmpty() || 
								cbxUniversitario.getSelectedIndex() == 0 || 
								nacionalidad.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						aux = new Universitario(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, imagenActual, true, cbxUniversitario.getSelectedItem().toString());
					}
					if(rdbtnTecnicoSuperior.isSelected()) {
						if(genero != 'M' && genero != 'F' || cedula.isEmpty() || 
								nombre.isEmpty() || 
								apellido.isEmpty() || 
								telefono.isEmpty() || 
								correo.isEmpty() || 
								nacionalidad.isEmpty() ||
								cbxTecnicoSuperior.getSelectedIndex() == 0 ||
								spnExperiencia.getValue().toString().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						int anyoExperiencia = (int) spnExperiencia.getValue();
						aux = new TecnicoSuperior(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, imagenActual, true, cbxTecnicoSuperior.getSelectedItem().toString(), anyoExperiencia);
					}
					if(rdbtnObrero.isSelected()) {
						if(genero != 'M' && genero != 'F' || cedula.isEmpty() || 
								nombre.isEmpty() || 
								apellido.isEmpty() || 
								telefono.isEmpty() || 
								correo.isEmpty() || 
								nacionalidad.isEmpty() ||
								(!chkElectricidad.isSelected() &&
										!chkSoldadura.isSelected() &&
										!chkTecnicaPintura.isSelected() &&
										!chkTuberias.isSelected() &&
										!chkMantenimiento.isSelected() &&
										!chkMaquinaria.isSelected())) {
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						ArrayList<String> habilidadesList = new ArrayList<>();
						if (chkElectricidad.isSelected()) {
							habilidadesList.add("Electricidad Básica");
						}
						if (chkSoldadura.isSelected()) {
							habilidadesList.add("Soldadura");
						}
						if (chkTecnicaPintura.isSelected()) {
							habilidadesList.add("Técnica de pintura o Albañilería");
						}
						if (chkTuberias.isSelected()) {
							habilidadesList.add("Instalación de Tuberías");
						}
						if (chkMantenimiento.isSelected()) {
							habilidadesList.add("Mantenimiento Básico de Equipos");
						}
						if (chkMaquinaria.isSelected()) {
							habilidadesList.add("Lectura de Planos");
						}

						String[] habilidades = habilidadesList.toArray(new String[0]);
						aux = new Obrero(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, imagenActual, true, habilidades);
					}
					if (!Bolsa.getInstance().validarCorreo(correo) && !txtCorreo.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Correo inválido", "Error", JOptionPane.INFORMATION_MESSAGE);
						txtCorreo.requestFocus(); 
					}  else if(Bolsa.getInstance().buscarCandidatoByCod(cedula) != null) {
						JOptionPane.showMessageDialog(null, "La Cedula ingresada ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
						txtCedula.requestFocus();
					} else {
						Bolsa.getInstance().registrarCandidato(aux);
						JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				}
			});
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 102, 153));
		panel_2.setBounds(0, 0, 594, 83);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("DATOS DE CANDIDATO");
		lblNewJgoodiesTitle.setForeground(SystemColor.window);
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewJgoodiesTitle.setBounds(192, 24, 209, 33);
		panel_2.add(lblNewJgoodiesTitle);

		panel_TecnicoSuperior = new JPanelRedondeado(60);
		panel_TecnicoSuperior.setBackground(SystemColor.inactiveCaption);
		panel_TecnicoSuperior.setBounds(10, 432, 564, 138);
		contentPanel.add(panel_TecnicoSuperior);
		panel_TecnicoSuperior.setLayout(null);

		Label label_8 = new Label("Nombre del T\u00E9cnico Superior:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(27, 25, 212, 22);
		panel_TecnicoSuperior.add(label_8);

		cbxTecnicoSuperior = new JComboBox();
		cbxTecnicoSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		cbxTecnicoSuperior.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "T\u00E9cnico en Inform\u00E1tica", "T\u00E9cnico en Contabilidad", "T\u00E9cnico en Electr\u00F3nica", "T\u00E9cnico en Electricidad", "T\u00E9cnico en Mec\u00E1nica Industrial", "T\u00E9cnico en Refrigeraci\u00F3n y Climatizaci\u00F3n", "T\u00E9cnico en Enfermer\u00EDa", "T\u00E9cnico en Farmacia", "T\u00E9cnico en An\u00E1lisis de Sistemas", "T\u00E9cnico en Gesti\u00F3n Administrativa", "T\u00E9cnico en Desarrollo de Software", "T\u00E9cnico en Redes", "T\u00E9cnico en Seguridad Industrial", "T\u00E9cnico en Construcci\u00F3n Civil", "T\u00E9cnico en Dise\u00F1o Gr\u00E1fico", "T\u00E9cnico en Producci\u00F3n Audiovisual", "T\u00E9cnico en Turismo", "T\u00E9cnico en Gastronom\u00EDa", "T\u00E9cnico en Log\u00EDstica", "T\u00E9cnico en Recursos Humanos", "T\u00E9cnico en Mercadeo", "T\u00E9cnico en Agronom\u00EDa", "T\u00E9cnico en Educaci\u00F3n Inicial", "T\u00E9cnico en Gesti\u00F3n Ambiental", "T\u00E9cnico en Mantenimiento Industrial"}));
		cbxTecnicoSuperior.setBounds(27, 56, 212, 20);
		panel_TecnicoSuperior.add(cbxTecnicoSuperior);

		label_10 = new Label("A\u00F1os de Experiencia laboral:");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_10.setBounds(293, 25, 212, 22);
		panel_TecnicoSuperior.add(label_10);

		spnExperiencia = new JSpinner();
		spnExperiencia.setBackground(SystemColor.inactiveCaptionBorder);
		spnExperiencia.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		spnExperiencia.setBounds(359, 56, 103, 20);
		panel_TecnicoSuperior.add(spnExperiencia);

		label_14 = new Label("T\u00E9cnico Superior");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_14.setAlignment(Label.CENTER);
		label_14.setBounds(216, 0, 132, 21);
		panel_TecnicoSuperior.add(label_14);

		panel_Obrero = new JPanelRedondeado(60);
		panel_Obrero.setBackground(SystemColor.inactiveCaption);
		panel_Obrero.setBounds(10, 432, 564, 138);
		contentPanel.add(panel_Obrero);
		panel_Obrero.setLayout(null);

		label_6 = new Label("Habilidades: ");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(25, 22, 97, 22);
		panel_Obrero.add(label_6);

		chkElectricidad = new JCheckBox("Electricidad b\u00E1sica");
		chkElectricidad.setBackground(SystemColor.inactiveCaption);
		chkElectricidad.setBounds(25, 50, 205, 23);
		panel_Obrero.add(chkElectricidad);

		chkSoldadura = new JCheckBox("Soldadura");
		chkSoldadura.setBackground(SystemColor.inactiveCaption);
		chkSoldadura.setBounds(25, 76, 234, 23);
		panel_Obrero.add(chkSoldadura);

		chkTecnicaPintura = new JCheckBox("T\u00E9cnicas de pintura o alba\u00F1iler\u00EDa");
		chkTecnicaPintura.setBackground(SystemColor.inactiveCaption);
		chkTecnicaPintura.setBounds(25, 102, 234, 23);
		panel_Obrero.add(chkTecnicaPintura);

		chkTuberias = new JCheckBox("Instalaci\u00F3n de tuber\u00EDas ");
		chkTuberias.setBackground(SystemColor.inactiveCaption);
		chkTuberias.setBounds(261, 50, 247, 23);
		panel_Obrero.add(chkTuberias);

		chkMantenimiento = new JCheckBox("Mantenimiento b\u00E1sico de equipos");
		chkMantenimiento.setBackground(SystemColor.inactiveCaption);
		chkMantenimiento.setBounds(261, 76, 247, 23);
		panel_Obrero.add(chkMantenimiento);

		chkMaquinaria = new JCheckBox("Lectura de planos");
		chkMaquinaria.setBackground(SystemColor.inactiveCaption);
		chkMaquinaria.setBounds(261, 102, 247, 23);
		panel_Obrero.add(chkMaquinaria);

		label_15 = new Label("Obrero");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_15.setAlignment(Label.CENTER);
		label_15.setBounds(216, 0, 132, 21);
		panel_Obrero.add(label_15);
		{
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					clean();
				}
			});
		}

	}
	private void clean() {
		// TODO Auto-generated method stub
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date fechaInicial = sdf.parse("2007/01/01");
			spnFecha.setValue(fechaInicial);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cbxNacionalidad.setSelectedIndex(0);
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
		rdbtnUniversitario.setSelected(true);
		rdbtnTecnicoSuperior.setSelected(false);
		rdbtnObrero.setSelected(false);
		panel_Universitario.setVisible(true);
		panel_TecnicoSuperior.setVisible(false);
		panel_Obrero.setVisible(false);
		cbxUniversitario.setSelectedIndex(0);
		cbxTecnicoSuperior.setSelectedIndex(0);
		spnExperiencia.setValue(0);
		chkElectricidad.setSelected(false);
		chkSoldadura.setSelected(false);
		chkTecnicaPintura.setSelected(false);
		chkTuberias.setSelected(false);
		chkMantenimiento.setSelected(false);
		chkMaquinaria.setSelected(false);

		txtCedula.requestFocus();
	}
}