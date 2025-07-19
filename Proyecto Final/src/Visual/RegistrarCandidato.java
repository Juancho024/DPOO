package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.Obrero;
import Logico.TecnicoSuperior;
import Logico.Universitario;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.SystemColor;

public class RegistrarCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
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
	private String [] misHabilidades;
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
		setBounds(100, 100, 600, 575);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Datos Personales: ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 564, 255);
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
		txtCorreo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String correo = txtCorreo.getText().trim();
				if (!Bolsa.getInstance().validarCorreo(correo)) {
					JOptionPane.showMessageDialog(null, "Correo inválido" );
					txtCorreo.requestFocus(); 
				}
			}
		});
		txtCorreo.setBounds(324, 154, 228, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(76, 113, 170, 22);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		Label label = new Label("Cedula: ");
		label.setBounds(10, 31, 49, 22);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula = new JTextField();
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicial = null;
		try {
			fechaInicial = sdf.parse("01/01/2007");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SpinnerDateModel modeloFecha = new SpinnerDateModel();
		modeloFecha.setValue(fechaInicial);
		spnFecha.setModel(modeloFecha);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spnFecha, "yyyy/MM/dd");
		spnFecha.setEditor(editor);
		spnFecha.setBounds(148, 197, 101, 20);
		panel.add(spnFecha);

		txtTelefono = new JTextField();
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
		txtNombre.setBounds(76, 70, 170, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		Canvas canvas = new Canvas();
		canvas.setBounds(377, 22, 117, 100);
		panel.add(canvas);

		btnFoto = new Button("Colocar Foto");
		btnFoto.setBackground(Color.LIGHT_GRAY);
		btnFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFoto.setBounds(392, 128, 90, 22);
		panel.add(btnFoto);
		
		label_11 = new Label("Nacionalidad:");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(108, 226, 101, 22);
		panel.add(label_11);
		
		cbxNacionalidad = new JComboBox();
		cbxNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per\u00FA", "M\u00E9xico", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam\u00E1", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep\u00FAblica Dominicana", "Puerto Rico", "Espa\u00F1a", "Estados Unidos", "Canad\u00E1", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Jap\u00F3n", "Corea del Sur", "China", "India", "Australia", "Sud\u00E1frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu\u00EDa", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B\u00E9lgica", "Pa\u00EDses Bajos", "Nueva Zelanda"}));
		cbxNacionalidad.setBounds(215, 224, 170, 20);
		panel.add(cbxNacionalidad);


		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, " Nivel de Estudios: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 277, 564, 61);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		rdbtnUniversitario = new JRadioButton("Universitario");
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

		panel_TecnicoSuperior = new JPanel();
		panel_TecnicoSuperior.setBorder(new TitledBorder(null, "T\u00E9cnico Superior ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TecnicoSuperior.setBounds(10, 349, 564, 138);
		contentPanel.add(panel_TecnicoSuperior);
		panel_TecnicoSuperior.setLayout(null);

		Label label_8 = new Label("Nombre del T\u00E9cnico Superior:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(27, 25, 212, 22);
		panel_TecnicoSuperior.add(label_8);

		cbxTecnicoSuperior = new JComboBox();
		cbxTecnicoSuperior.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "T\u00E9cnico en Inform\u00E1tica", "T\u00E9cnico en Contabilidad", "T\u00E9cnico en Electr\u00F3nica", "T\u00E9cnico en Electricidad", "T\u00E9cnico en Mec\u00E1nica Industrial", "T\u00E9cnico en Refrigeraci\u00F3n y Climatizaci\u00F3n", "T\u00E9cnico en Enfermer\u00EDa", "T\u00E9cnico en Farmacia", "T\u00E9cnico en An\u00E1lisis de Sistemas", "T\u00E9cnico en Gesti\u00F3n Administrativa", "T\u00E9cnico en Desarrollo de Software", "T\u00E9cnico en Redes", "T\u00E9cnico en Seguridad Industrial", "T\u00E9cnico en Construcci\u00F3n Civil", "T\u00E9cnico en Dise\u00F1o Gr\u00E1fico", "T\u00E9cnico en Producci\u00F3n Audiovisual", "T\u00E9cnico en Turismo", "T\u00E9cnico en Gastronom\u00EDa", "T\u00E9cnico en Log\u00EDstica", "T\u00E9cnico en Recursos Humanos", "T\u00E9cnico en Mercadeo", "T\u00E9cnico en Agronom\u00EDa", "T\u00E9cnico en Educaci\u00F3n Inicial", "T\u00E9cnico en Gesti\u00F3n Ambiental", "T\u00E9cnico en Mantenimiento Industrial"}));
		cbxTecnicoSuperior.setBounds(27, 56, 212, 20);
		panel_TecnicoSuperior.add(cbxTecnicoSuperior);

		label_10 = new Label("A\u00F1os de Experiencia laboral:");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_10.setBounds(293, 25, 212, 22);
		panel_TecnicoSuperior.add(label_10);

		spnExperiencia = new JSpinner();
		spnExperiencia.setModel(new SpinnerNumberModel(1, 0, 50, 1));
		spnExperiencia.setBounds(359, 56, 103, 20);
		panel_TecnicoSuperior.add(spnExperiencia);

		panel_Obrero = new JPanel();
		panel_Obrero.setBorder(new TitledBorder(null, " Obrero ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Obrero.setBounds(10, 349, 564, 138);
		contentPanel.add(panel_Obrero);
		panel_Obrero.setLayout(null);

		label_6 = new Label("Habilidades: ");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(25, 22, 97, 22);
		panel_Obrero.add(label_6);

		chkElectricidad = new JCheckBox("Electricidad b\u00E1sica");
		chkElectricidad.setBounds(25, 50, 205, 23);
		panel_Obrero.add(chkElectricidad);

		chkSoldadura = new JCheckBox("Soldadura");
		chkSoldadura.setBounds(25, 76, 234, 23);
		panel_Obrero.add(chkSoldadura);

		chkTecnicaPintura = new JCheckBox("T\u00E9cnicas de pintura o alba\u00F1iler\u00EDa");
		chkTecnicaPintura.setBounds(25, 102, 234, 23);
		panel_Obrero.add(chkTecnicaPintura);

		chkTuberias = new JCheckBox("Instalaci\u00F3n de tuber\u00EDas ");
		chkTuberias.setBounds(261, 50, 247, 23);
		panel_Obrero.add(chkTuberias);

		chkMantenimiento = new JCheckBox("Mantenimiento b\u00E1sico de equipos");
		chkMantenimiento.setBounds(261, 76, 247, 23);
		panel_Obrero.add(chkMantenimiento);

		chkMaquinaria = new JCheckBox("Lectura de planos");
		chkMaquinaria.setBounds(261, 102, 247, 23);
		panel_Obrero.add(chkMaquinaria);

		panel_Universitario = new JPanel();
		panel_Universitario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Universitario ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Universitario.setBounds(10, 349, 564, 138);
		contentPanel.add(panel_Universitario);
		panel_Universitario.setLayout(null);

		label_7 = new Label("Nombre de la Carrera: ");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(55, 29, 139, 22);
		panel_Universitario.add(label_7);

		cbxUniversitario = new JComboBox();
		cbxUniversitario.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Administraci\u00F3n de Empresas", "Contabilidad", "Econom\u00EDa", "Marketing", "Finanzas", "Psicolog\u00EDa", "Derecho", "Educaci\u00F3n / Pedagog\u00EDa", "Trabajo Social", "Comunicaci\u00F3n Social / Periodismo", "Relaciones Internacionales", "Ingenier\u00EDa en Sistemas / Inform\u00E1tica", "Ingenier\u00EDa Civil", "Ingenier\u00EDa Industrial", "Ingenier\u00EDa El\u00E9ctrica / Electr\u00F3nica", "Medicina", "Enfermer\u00EDa", "Odontolog\u00EDa", "Farmacia", "Nutrici\u00F3n", "Fisioterapia", "Veterinaria", "Ciencias de la Computaci\u00F3n", "Desarrollo de Software", "Ciberseguridad", "Arquitectura", "Dise\u00F1o Gr\u00E1fico", "Dise\u00F1o Industrial", "Turismo y Hoteler\u00EDa", "Gastronom\u00EDa / Artes Culinarias"}));
		cbxUniversitario.setBounds(55, 58, 219, 20);
		panel_Universitario.add(cbxUniversitario);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
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
							if(txtCedula.getText().trim().isEmpty() || 
									txtNombre.getText().trim().isEmpty() || 
									txtApellido.getText().trim().isEmpty() || 
									txtTelefono.getText().trim().isEmpty() || 
									txtCorreo.getText().trim().isEmpty() || 
									cbxUniversitario.getSelectedIndex() == 0 || 
									cbxNacionalidad.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);;
								return;
							}
							aux = new Universitario(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, cbxUniversitario.getSelectedItem().toString());
						}
						if(rdbtnTecnicoSuperior.isSelected()) {
							if(txtCedula.getText().trim().isEmpty() || 
									txtNombre.getText().trim().isEmpty() || 
									txtApellido.getText().trim().isEmpty() || 
									txtTelefono.getText().trim().isEmpty() || 
									txtCorreo.getText().trim().isEmpty() || 
									cbxNacionalidad.getSelectedIndex() == 0 ||
									cbxTecnicoSuperior.getSelectedIndex() == 0 ||
									spnExperiencia.getValue().toString().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);;
								return;
							}
							int anyoExperiencia = (int) spnExperiencia.getValue();
							aux = new TecnicoSuperior(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, cbxTecnicoSuperior.getSelectedItem().toString(), anyoExperiencia);
						}
						if(rdbtnObrero.isSelected()) {
							if(txtCedula.getText().trim().isEmpty() || 
									txtNombre.getText().trim().isEmpty() || 
									txtApellido.getText().trim().isEmpty() || 
									txtTelefono.getText().trim().isEmpty() || 
									txtCorreo.getText().trim().isEmpty() || 
									cbxNacionalidad.getSelectedIndex() == 0||
									(!chkElectricidad.isSelected() &&
										    !chkSoldadura.isSelected() &&
										    !chkTecnicaPintura.isSelected() &&
										    !chkTuberias.isSelected() &&
										    !chkMantenimiento.isSelected() &&
										    !chkMaquinaria.isSelected())) {
								JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);;
								return;
							}
							if(chkElectricidad.isSelected()) {
								misHabilidades [0] = "Electricidad Básica"; 
							}
							if(chkSoldadura.isSelected()) {
								misHabilidades [1] = "Soldadura";
							}
							if(chkTecnicaPintura.isSelected()) {
								misHabilidades [2] = "Técnica de pintura o Albañilería";
							}
							if(chkMantenimiento.isSelected()) {
								misHabilidades [3] = "Mantenimiento Básico de Equipos";
							}
							if(chkTuberias.isSelected()) {
								misHabilidades [4] = "Instalación de Tuberías";
							}
							if(chkMaquinaria.isSelected()) {
								misHabilidades [5] = "Lectura de Planos";
							}
							aux = new Obrero(cedula, nombre, apellido, genero, fechaNacimiento, telefono, correo, nacionalidad, misHabilidades);
						}
						Bolsa.getInstance().registrarCandidato(aux);
						JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
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
		spnExperiencia.setValue(1);
		chkElectricidad.setSelected(false);
		chkSoldadura.setSelected(false);
		chkTecnicaPintura.setSelected(false);
		chkTuberias.setSelected(false);
		chkMantenimiento.setSelected(false);
		chkMaquinaria.setSelected(false);

		txtCedula.requestFocus();
	}
}