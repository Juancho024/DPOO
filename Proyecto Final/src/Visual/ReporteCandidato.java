package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Candidato;
import Logico.Obrero;
import Logico.TecnicoSuperior;
import Logico.Universitario;

import java.awt.Label;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class ReporteCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtNacionalidad;
	private JTextField txtFecha;
	private JTextField txtGenero;
	private JTextField txtNivel;
	private JCheckBox chkElectricidad;
	private JCheckBox chkSoldadura;
	private JCheckBox chkTuberia;
	private JCheckBox chkPintura;
	private JCheckBox chkMantenimiento;
	private JCheckBox chkPlanos;
	private JTextField txtCarerra;
	private JTextField txtTecnico;
	private JTextField txtAnyo;
	private JLabel lbImagen;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReporteCandidato dialog = new ReporteCandidato(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReporteCandidato(Candidato selected) {
		setTitle("Reporte de Candidato");
		setBounds(100, 100, 530, 580);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, " Datos Personales ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 494, 323);
		contentPanel.add(panel);
		panel.setLayout(null);

		Label label = new Label("Cedula: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 23, 62, 22);
		panel.add(label);

		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setEditable(false);
		txtCedula.setText(selected.getCedula());
		txtCedula.setBounds(10, 48, 145, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		lbImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
		lbImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbImagen.setBounds(254, 23, 220, 162);

		byte[] imgBytes = selected.getImagen();
		if (imgBytes != null) {
		    ImageIcon icono = new ImageIcon(imgBytes);
		    Image imagenEscalada = icono.getImage().getScaledInstance(117, 99, Image.SCALE_SMOOTH);
		    lbImagen.setIcon(new ImageIcon(imagenEscalada));
		    lbImagen.setText("");
		} else {
		    lbImagen.setIcon(null);
		    lbImagen.setText("Sin imagen");
		}
		panel.add(lbImagen);

		Label label_1 = new Label("Nombre Completo: ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 76, 145, 22);
		panel.add(label_1);

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 104, 220, 20);
		txtNombre.setText(selected.getNombre()+" "+selected.getApellido());
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		Label label_2 = new Label("Correo: ");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 137, 145, 22);
		panel.add(label_2);

		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setEnabled(false);
		txtCorreo.setText(selected.getCorreo());
		txtCorreo.setBounds(10, 165, 220, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);

		Label label_3 = new Label("Telefono:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 199, 145, 22);
		panel.add(label_3);

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setEditable(false);
		txtTelefono.setText(selected.getTelefono());
		txtTelefono.setBounds(10, 227, 220, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		Label label_4 = new Label("Nacionalidad: ");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 259, 145, 22);
		panel.add(label_4);

		txtNacionalidad = new JTextField();
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setEnabled(false);
		txtNacionalidad.setText(selected.getNacionalidad());
		txtNacionalidad.setBounds(10, 287, 220, 20);
		panel.add(txtNacionalidad);
		txtNacionalidad.setColumns(10);

		Label label_5 = new Label("Fecha de Nacimiento: ");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(254, 199, 145, 22);
		panel.add(label_5);

		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtFecha.setText(sdf.format(selected.getFechaNacimiento().getTime()));
		txtFecha.setBounds(254, 227, 220, 20);
		panel.add(txtFecha);

		Label label_6 = new Label("Genero: ");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(254, 259, 145, 22);
		panel.add(label_6);

		txtGenero = new JTextField();
		txtGenero.setEnabled(false);
		txtGenero.setEditable(false);
		txtGenero.setColumns(10);
		txtGenero.setText(String.valueOf(selected.getSexo()));
		txtGenero.setBounds(254, 287, 220, 20);
		panel.add(txtGenero);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, " Datos Academicos ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 345, 494, 185);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		Label label_7 = new Label("Nivel:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(112, 27, 44, 22);
		panel_1.add(label_7);

		txtNivel = new JTextField();
		txtNivel.setEnabled(false);
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
		txtNivel.setBounds(162, 27, 220, 20);
		panel_1.add(txtNivel);
		
		JTextField[] campos = { txtCedula, txtNombre, txtCorreo, txtTelefono, txtFecha, txtNacionalidad, txtGenero };
		for (JTextField campo : campos) {
		    campo.setEditable(false);
		    campo.setBackground(Color.WHITE);
		    campo.setForeground(Color.BLACK);
		    campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		    campo.setDisabledTextColor(Color.BLACK); 
		}

		
		if(selected instanceof Universitario) {
			txtNivel.setText("Universitario");
			Label label_8 = new Label("Carrera: ");
			label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_8.setBounds(101, 74, 55, 22);
			panel_1.add(label_8);

			txtCarerra = new JTextField();
			txtCarerra.setEnabled(false);
			txtCarerra.setEditable(false);
			txtCarerra.setColumns(10);
			txtCarerra.setText(((Universitario) selected).getCarreraGraduada());
			txtCarerra.setBounds(162, 74, 220, 20);
			panel_1.add(txtCarerra);
		} 
		if(selected instanceof TecnicoSuperior) {
			txtNivel.setText("Técnico Superior");
			Label label_8 = new Label("T\u00E9cnico Obtenido: ");
			label_8.setAlignment(Label.CENTER);
			label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_8.setBounds(63, 88, 119, 22);
			panel_1.add(label_8);

			Label label_9 = new Label("A\u00F1os de Experiencia:");
			label_9.setAlignment(Label.CENTER);
			label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_9.setBounds(302, 88, 155, 22);
			panel_1.add(label_9);

			txtTecnico = new JTextField();
			txtTecnico.setEnabled(false);
			txtTecnico.setEditable(false);
			txtTecnico.setColumns(10);
			txtTecnico.setText(((TecnicoSuperior) selected).getAreaEspecialidad());
			txtTecnico.setBounds(20, 116, 220, 20);
			panel_1.add(txtTecnico);

			txtAnyo = new JTextField();
			txtAnyo.setEnabled(false);
			txtAnyo.setEditable(false);
			txtAnyo.setColumns(10);
			txtAnyo.setText(String.valueOf(((TecnicoSuperior) selected).getAnyoExperiencia()));
			txtAnyo.setBounds(264, 116, 220, 20);
			panel_1.add(txtAnyo);
		} 
		if(selected instanceof Obrero){
			txtNivel.setText("Obrero");
			Label label_8 = new Label("Habilidades:  ");
			label_8.setAlignment(Label.CENTER);
			label_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			label_8.setBounds(164, 55, 166, 22);
			panel_1.add(label_8);

			chkElectricidad = new JCheckBox("Electricidad B\u00E1sica");
			chkElectricidad.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkElectricidad.setEnabled(false);
			chkElectricidad.setBounds(10, 86, 166, 23);
			panel_1.add(chkElectricidad);

			chkSoldadura = new JCheckBox("Soldadura");
			chkSoldadura.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkSoldadura.setEnabled(false);
			chkSoldadura.setBounds(10, 112, 166, 23);
			panel_1.add(chkSoldadura);

			chkPintura = new JCheckBox("T\u00E9cnica de pintura o Alba\u00F1iler\u00EDa");
			chkPintura.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkPintura.setEnabled(false);
			chkPintura.setBounds(10, 138, 220, 23);
			panel_1.add(chkPintura);

			chkPlanos = new JCheckBox("Lectura de Planos");
			chkPlanos.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkPlanos.setEnabled(false);
			chkPlanos.setBounds(254, 138, 220, 23);
			panel_1.add(chkPlanos);

			chkMantenimiento = new JCheckBox("Mantenimiento B\u00E1sico de Equipos");
			chkMantenimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkMantenimiento.setEnabled(false);
			chkMantenimiento.setBounds(254, 112, 234, 23);
			panel_1.add(chkMantenimiento);

			chkTuberia = new JCheckBox("Instalaci\u00F3n de Tuber\u00EDas");
			chkTuberia.setFont(new Font("Tahoma", Font.BOLD, 12));
			chkTuberia.setEnabled(false);
			chkTuberia.setBounds(254, 86, 166, 23);
			panel_1.add(chkTuberia);
			
			String[] habilidades = ((Obrero) selected).getMisHabilidades();
		    for (String aux : habilidades) {
		        switch (aux) {
		            case "Electricidad Básica":
		                chkElectricidad.setSelected(true);
		                break;
		            case "Soldadura":
		                chkSoldadura.setSelected(true);
		                break;
		            case "Técnica de pintura o Albañilería":
		            	chkPintura.setSelected(true);
		                break;
		            case "Instalación de Tuberías":
		                chkTuberia.setSelected(true);
		                break;
		            case "Mantenimiento Básico de Equipos":
		                chkMantenimiento.setSelected(true);
		                break;
		            case "Lectura de Planos":
		            	chkPlanos.setSelected(true);
		                break;
		        }
		    }
		}
	}
}
