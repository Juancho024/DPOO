package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.HistorialMatch;
import Logico.PorcentajeMatch;
import Logico.Postulacion;
import Logico.Vacante;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RealizarMatch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre1;
	private JTextField txtTipoContrato1;
	private JTextField txtSalario1;
	private JTextField txtPais1;
	private JTextField txtCuidad1;
	private JTextField txtVehiculo1;
	private JTextField txtLicencia1;
	private JTextField txtMudarse1;
	private JTextField txtMudarse2;
	private JTextField txtLicencia2;
	private JTextField txtVehiculo2;
	private JTextField txtCuidad2;
	private JTextField txtPais2;
	private JTextField txtSalario2;
	private JTextField txtTipoContrato2;
	private JTextField txtNombre2;
	private JTextField txtMudarse3;
	private JTextField txtLicencia3;
	private JTextField txtVehiculo3;
	private JTextField txtCiudad3;
	private JTextField txtPais3;
	private JTextField txtSalario3;
	private JTextField txtTipoContrato3;
	private JTextField txtNombre3;
	private Button btnContratar2;
	private Button btnContratar3;
	private Button btnContratar1;
	private JComboBox<String> cbxVacantes;
	private JLabel lbImagen1;
	private JLabel lbImagen2;
	private JLabel lbImagen3;
	private JLabel lbPorcentaje1;
	private JLabel lbPorcentaje3;
	private JLabel lbPorcentaje2;
	private Label label_28;
	private Label label_29;
	private Label label_30;
	private String cedula1;
	private String cedula2;
	private String cedula3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarMatch dialog = new RealizarMatch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarMatch() {
		setTitle("Realizar Match");
		setBounds(100, 100, 1200, 677);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Label label = new Label("Elegir una Vacante:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(23, 23, 123, 22);
		panel.add(label);
		
		cbxVacantes = new JComboBox<String>();
		cbxVacantes.removeAllItems();
		cbxVacantes.addItem("Seleccione una Opción");
		for(PorcentajeMatch aux: Bolsa.getInstance().getMisPorcentajesMatches()) {
			cbxVacantes.addItem(aux.getMisVacantes().getNombreVacante()+ " - " + aux.getMisVacantes().getRncEmpresa());
		}
		cbxVacantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) cbxVacantes.getSelectedItem();
				System.out.println("Seleccionado: " + seleccion);
				if (seleccion != null && !seleccion.equals("Seleccione una Opción")) {
					PorcentajeMatch match = buscarAuxByInfo(seleccion);
					if (match != null) {
						llenarPostulacionesDesdeCedulas(match);
					}
				}
			}
		});
		cbxVacantes.setBounds(152, 25, 499, 20);
		panel.add(cbxVacantes);
		
		JPanel panel_Postulacion1 = new JPanelRedondeado(60);
		panel_Postulacion1.setBackground(SystemColor.inactiveCaption);
		panel_Postulacion1.setBounds(15, 68, 368, 549);
		panel.add(panel_Postulacion1);
		panel_Postulacion1.setLayout(null);
		
		btnContratar1 = new Button("Contratar");
		btnContratar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vacante auxVa = buscarAuxVacanteByInfo(cbxVacantes.getSelectedItem().toString());
				Postulacion auxPos = Bolsa.getInstance().buscarPostulacionByCode(cedula1);
				Bolsa.getInstance().registrarHistorialMatch(auxVa, auxPos);
			}
		});
		btnContratar1.setBackground(new Color(220, 53, 69)); 
		btnContratar1.setForeground(Color.WHITE);
		btnContratar1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar1.setBounds(139, 492, 90, 30);
		panel_Postulacion1.add(btnContratar1);
		
		Label label_1 = new Label("Nombre del Postulante:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 151, 146, 22);
		panel_Postulacion1.add(label_1);
		
		txtNombre1 = new JTextField();
		txtNombre1.setEnabled(false);
		txtNombre1.setEditable(false);
		txtNombre1.setBounds(10, 179, 348, 20);
		panel_Postulacion1.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		Label label_2 = new Label("Tipo de Contrato: ");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 205, 162, 22);
		panel_Postulacion1.add(label_2);
		
		txtTipoContrato1 = new JTextField();
		txtTipoContrato1.setEnabled(false);
		txtTipoContrato1.setEditable(false);
		txtTipoContrato1.setColumns(10);
		txtTipoContrato1.setBounds(10, 233, 162, 20);
		panel_Postulacion1.add(txtTipoContrato1);
		
		Label label_3 = new Label("Pretensi\u00F3n salarial:  ");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(193, 205, 165, 22);
		panel_Postulacion1.add(label_3);
		
		txtSalario1 = new JTextField();
		txtSalario1.setEnabled(false);
		txtSalario1.setEditable(false);
		txtSalario1.setColumns(10);
		txtSalario1.setBounds(193, 233, 165, 20);
		panel_Postulacion1.add(txtSalario1);
		
		Label label_4 = new Label("Aspectos de Disponibilidad Personal:");
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_4.setBounds(10, 263, 348, 22);
		panel_Postulacion1.add(label_4);
		
		Label label_5 = new Label("Pa\u00EDs de Residencia:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(10, 303, 130, 22);
		panel_Postulacion1.add(label_5);
		
		txtPais1 = new JTextField();
		txtPais1.setEnabled(false);
		txtPais1.setEditable(false);
		txtPais1.setColumns(10);
		txtPais1.setBounds(146, 305, 212, 20);
		panel_Postulacion1.add(txtPais1);
		
		Label label_6 = new Label("Ciudad de Residencia:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setBounds(10, 340, 130, 22);
		panel_Postulacion1.add(label_6);
		
		txtCuidad1 = new JTextField();
		txtCuidad1.setEnabled(false);
		txtCuidad1.setEditable(false);
		txtCuidad1.setColumns(10);
		txtCuidad1.setBounds(156, 342, 202, 20);
		panel_Postulacion1.add(txtCuidad1);
		
		Label label_7 = new Label("Disponibilidad Vehicular: ");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(10, 380, 146, 22);
		panel_Postulacion1.add(label_7);
		
		Label label_8 = new Label("Disponibilidad de Licencia:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(10, 415, 162, 22);
		panel_Postulacion1.add(label_8);
		
		Label label_9 = new Label("Dispuesto a Mudarse: ");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(10, 451, 162, 22);
		panel_Postulacion1.add(label_9);
		
		txtVehiculo1 = new JTextField();
		txtVehiculo1.setEnabled(false);
		txtVehiculo1.setEditable(false);
		txtVehiculo1.setColumns(10);
		txtVehiculo1.setBounds(171, 382, 187, 20);
		panel_Postulacion1.add(txtVehiculo1);
		
		txtLicencia1 = new JTextField();
		txtLicencia1.setEnabled(false);
		txtLicencia1.setEditable(false);
		txtLicencia1.setColumns(10);
		txtLicencia1.setBounds(181, 417, 177, 20);
		panel_Postulacion1.add(txtLicencia1);
		
		txtMudarse1 = new JTextField();
		txtMudarse1.setEnabled(false);
		txtMudarse1.setEditable(false);
		txtMudarse1.setColumns(10);
		txtMudarse1.setBounds(181, 453, 177, 20);
		panel_Postulacion1.add(txtMudarse1);
		
		lbImagen1 = new JLabel("Sin imagen", SwingConstants.CENTER);
		lbImagen1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbImagen1.setBounds(200, 34, 123, 117);
		panel_Postulacion1.add(lbImagen1);
		
		lbPorcentaje1 = new JLabel("", SwingConstants.CENTER);
		lbPorcentaje1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbPorcentaje1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbPorcentaje1.setBounds(35, 53, 90, 40);
		panel_Postulacion1.add(lbPorcentaje1);
		
		label_30 = new Label("Informaci\u00F3n Postulaci\u00F3n 1");
		label_30.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_30.setBounds(10, 10, 162, 22);
		panel_Postulacion1.add(label_30);
		
		JPanel panel_Postulacion2 = new JPanelRedondeado(60);
		panel_Postulacion2.setBackground(SystemColor.inactiveCaption);
		panel_Postulacion2.setBounds(398, 68, 376, 549);
		panel.add(panel_Postulacion2);
		panel_Postulacion2.setLayout(null);
		
		btnContratar2 = new Button("Contratar");
		btnContratar2.setBackground(new Color(220, 53, 69)); 
		btnContratar2.setForeground(Color.WHITE);
		btnContratar2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar2.setBounds(143, 492, 90, 30);
		panel_Postulacion2.add(btnContratar2);
		
		txtMudarse2 = new JTextField();
		txtMudarse2.setEnabled(false);
		txtMudarse2.setEditable(false);
		txtMudarse2.setColumns(10);
		txtMudarse2.setBounds(189, 457, 177, 20);
		panel_Postulacion2.add(txtMudarse2);
		
		Label label_10 = new Label("Dispuesto a Mudarse: ");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_10.setBounds(18, 455, 162, 22);
		panel_Postulacion2.add(label_10);
		
		txtLicencia2 = new JTextField();
		txtLicencia2.setEnabled(false);
		txtLicencia2.setEditable(false);
		txtLicencia2.setColumns(10);
		txtLicencia2.setBounds(189, 421, 177, 20);
		panel_Postulacion2.add(txtLicencia2);
		
		Label label_11 = new Label("Disponibilidad de Licencia:");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(18, 419, 162, 22);
		panel_Postulacion2.add(label_11);
		
		txtVehiculo2 = new JTextField();
		txtVehiculo2.setEnabled(false);
		txtVehiculo2.setEditable(false);
		txtVehiculo2.setColumns(10);
		txtVehiculo2.setBounds(179, 386, 187, 20);
		panel_Postulacion2.add(txtVehiculo2);
		
		Label label_12 = new Label("Disponibilidad Vehicular: ");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_12.setBounds(18, 384, 146, 22);
		panel_Postulacion2.add(label_12);
		
		txtCuidad2 = new JTextField();
		txtCuidad2.setEnabled(false);
		txtCuidad2.setEditable(false);
		txtCuidad2.setColumns(10);
		txtCuidad2.setBounds(164, 346, 202, 20);
		panel_Postulacion2.add(txtCuidad2);
		
		Label label_13 = new Label("Ciudad de Residencia:");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_13.setBounds(18, 344, 130, 22);
		panel_Postulacion2.add(label_13);
		
		txtPais2 = new JTextField();
		txtPais2.setEnabled(false);
		txtPais2.setEditable(false);
		txtPais2.setColumns(10);
		txtPais2.setBounds(154, 309, 212, 20);
		panel_Postulacion2.add(txtPais2);
		
		Label label_14 = new Label("Pa\u00EDs de Residencia:");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_14.setBounds(18, 307, 130, 22);
		panel_Postulacion2.add(label_14);
		
		Label label_15 = new Label("Aspectos de Disponibilidad Personal:");
		label_15.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_15.setBounds(18, 267, 348, 22);
		panel_Postulacion2.add(label_15);
		
		txtSalario2 = new JTextField();
		txtSalario2.setEnabled(false);
		txtSalario2.setEditable(false);
		txtSalario2.setColumns(10);
		txtSalario2.setBounds(201, 237, 165, 20);
		panel_Postulacion2.add(txtSalario2);
		
		txtTipoContrato2 = new JTextField();
		txtTipoContrato2.setEnabled(false);
		txtTipoContrato2.setEditable(false);
		txtTipoContrato2.setColumns(10);
		txtTipoContrato2.setBounds(18, 237, 162, 20);
		panel_Postulacion2.add(txtTipoContrato2);
		
		txtNombre2 = new JTextField();
		txtNombre2.setEnabled(false);
		txtNombre2.setEditable(false);
		txtNombre2.setColumns(10);
		txtNombre2.setBounds(18, 183, 348, 20);
		panel_Postulacion2.add(txtNombre2);
		
		Label label_16 = new Label("Pretensi\u00F3n salarial:  ");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_16.setBounds(201, 209, 165, 22);
		panel_Postulacion2.add(label_16);
		
		Label label_17 = new Label("Tipo de Contrato: ");
		label_17.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_17.setBounds(18, 209, 162, 22);
		panel_Postulacion2.add(label_17);
		
		Label label_18 = new Label("Nombre del Postulante:");
		label_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_18.setBounds(18, 155, 146, 22);
		panel_Postulacion2.add(label_18);
		
		lbImagen2 = new JLabel("Sin imagen", SwingConstants.CENTER);
		lbImagen2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbImagen2.setBounds(218, 34, 123, 117);
		panel_Postulacion2.add(lbImagen2);
		
		lbPorcentaje2 = new JLabel("", SwingConstants.CENTER);
		lbPorcentaje2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbPorcentaje2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbPorcentaje2.setBounds(37, 53, 90, 40);
		panel_Postulacion2.add(lbPorcentaje2);
		
		label_29 = new Label("Informaci\u00F3n Postulaci\u00F3n 2");
		label_29.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_29.setBounds(10, 10, 162, 22);
		panel_Postulacion2.add(label_29);
		
		JPanel panel_Postulacion3 = new JPanelRedondeado(60);
		panel_Postulacion3.setBackground(SystemColor.inactiveCaption);
		panel_Postulacion3.setBounds(789, 68, 368, 549);
		panel.add(panel_Postulacion3);
		panel_Postulacion3.setLayout(null);
		
		btnContratar3 = new Button("Contratar");
		btnContratar3.setBackground(new Color(220, 53, 69)); 
		btnContratar3.setForeground(Color.WHITE);
		btnContratar3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar3.setBounds(139, 492, 90, 30);
		panel_Postulacion3.add(btnContratar3);
		
		txtMudarse3 = new JTextField();
		txtMudarse3.setEnabled(false);
		txtMudarse3.setEditable(false);
		txtMudarse3.setColumns(10);
		txtMudarse3.setBounds(181, 456, 177, 20);
		panel_Postulacion3.add(txtMudarse3);
		
		Label label_19 = new Label("Dispuesto a Mudarse: ");
		label_19.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_19.setBounds(10, 454, 162, 22);
		panel_Postulacion3.add(label_19);
		
		txtLicencia3 = new JTextField();
		txtLicencia3.setEnabled(false);
		txtLicencia3.setEditable(false);
		txtLicencia3.setColumns(10);
		txtLicencia3.setBounds(181, 420, 177, 20);
		panel_Postulacion3.add(txtLicencia3);
		
		Label label_20 = new Label("Disponibilidad de Licencia:");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_20.setBounds(10, 418, 162, 22);
		panel_Postulacion3.add(label_20);
		
		txtVehiculo3 = new JTextField();
		txtVehiculo3.setEnabled(false);
		txtVehiculo3.setEditable(false);
		txtVehiculo3.setColumns(10);
		txtVehiculo3.setBounds(171, 385, 187, 20);
		panel_Postulacion3.add(txtVehiculo3);
		
		Label label_21 = new Label("Disponibilidad Vehicular: ");
		label_21.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_21.setBounds(10, 383, 146, 22);
		panel_Postulacion3.add(label_21);
		
		txtCiudad3 = new JTextField();
		txtCiudad3.setEnabled(false);
		txtCiudad3.setEditable(false);
		txtCiudad3.setColumns(10);
		txtCiudad3.setBounds(156, 345, 202, 20);
		panel_Postulacion3.add(txtCiudad3);
		
		Label label_22 = new Label("Ciudad de Residencia:");
		label_22.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_22.setBounds(10, 343, 130, 22);
		panel_Postulacion3.add(label_22);
		
		txtPais3 = new JTextField();
		txtPais3.setEnabled(false);
		txtPais3.setEditable(false);
		txtPais3.setColumns(10);
		txtPais3.setBounds(146, 308, 212, 20);
		panel_Postulacion3.add(txtPais3);
		
		Label label_23 = new Label("Pa\u00EDs de Residencia:");
		label_23.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_23.setBounds(10, 306, 130, 22);
		panel_Postulacion3.add(label_23);
		
		Label label_24 = new Label("Aspectos de Disponibilidad Personal:");
		label_24.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_24.setBounds(10, 266, 348, 22);
		panel_Postulacion3.add(label_24);
		
		txtSalario3 = new JTextField();
		txtSalario3.setEnabled(false);
		txtSalario3.setEditable(false);
		txtSalario3.setColumns(10);
		txtSalario3.setBounds(193, 236, 165, 20);
		panel_Postulacion3.add(txtSalario3);
		
		txtTipoContrato3 = new JTextField();
		txtTipoContrato3.setEnabled(false);
		txtTipoContrato3.setEditable(false);
		txtTipoContrato3.setColumns(10);
		txtTipoContrato3.setBounds(10, 236, 162, 20);
		panel_Postulacion3.add(txtTipoContrato3);
		
		txtNombre3 = new JTextField();
		txtNombre3.setEnabled(false);
		txtNombre3.setEditable(false);
		txtNombre3.setColumns(10);
		txtNombre3.setBounds(10, 182, 348, 20);
		panel_Postulacion3.add(txtNombre3);
		
		Label label_25 = new Label("Pretensi\u00F3n salarial:  ");
		label_25.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_25.setBounds(193, 208, 165, 22);
		panel_Postulacion3.add(label_25);
		
		Label label_26 = new Label("Tipo de Contrato: ");
		label_26.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_26.setBounds(10, 208, 162, 22);
		panel_Postulacion3.add(label_26);
		
		Label label_27 = new Label("Nombre del Postulante:");
		label_27.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_27.setBounds(10, 154, 146, 22);
		panel_Postulacion3.add(label_27);	
		
		lbImagen3 = new JLabel("Sin imagen", SwingConstants.CENTER);
		lbImagen3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbImagen3.setBounds(214, 33, 123, 117);
		panel_Postulacion3.add(lbImagen3);
		
		lbPorcentaje3 = new JLabel("", SwingConstants.CENTER);
		lbPorcentaje3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbPorcentaje3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbPorcentaje3.setBounds(35, 53, 90, 40);
		panel_Postulacion3.add(lbPorcentaje3);
		
		label_28 = new Label("Informaci\u00F3n Postulaci\u00F3n 3 ");
		label_28.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_28.setBounds(10, 10, 162, 22);
		panel_Postulacion3.add(label_28);
		
		JTextField[] campos = { txtNombre1, txtTipoContrato1, txtSalario1, txtPais1, txtCuidad1, txtVehiculo1, txtLicencia1, txtMudarse1, txtNombre2, txtTipoContrato2, txtSalario2, txtPais2, txtCuidad2, txtVehiculo2, txtLicencia2, txtMudarse2, txtNombre3, txtTipoContrato3, txtSalario3, txtPais3, txtCiudad3, txtVehiculo3, txtLicencia3, txtMudarse3};
		for (JTextField campo : campos) {
		    if(campo != null) {
		    	campo.setEditable(false);
			    campo.setBackground(SystemColor.inactiveCaptionBorder);
			    campo.setForeground(Color.BLACK);
			    campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			    campo.setDisabledTextColor(Color.BLACK); 
		    }
		}
		
	}
	private void llenarPostulacionesDesdeCedulas(PorcentajeMatch match) {
		// TODO Auto-generated method stub
		String[] cedulas = match.getMis3Postulaciones();
		ArrayList<Postulacion> misPostulaciones = Bolsa.getInstance().getMisPostulaciones();
		System.out.println("Cantidad de cédulas: " + (cedulas != null ? cedulas.length : "null"));
		for (int i = 0; i < cedulas.length; i++) { //Borrar
		    System.out.println("Cédula [" + i + "]: " + cedulas[i]);
		}
		
		for(int i = 0; i < cedulas.length; i++) {
			String cedula = cedulas[i];
			Postulacion encontrada = null;
			
			for(Postulacion auxP: misPostulaciones) {
				if(auxP.getCedulaCliente().equals(cedula)) {
					encontrada = auxP;
					break;
				}
			}
			if(encontrada != null) {
				switch(i) {
				case 0:
					lbPorcentaje1.setText(String.format("%.2f", match.getPorcentaje()[i])+"%");
                    cedula1 = encontrada.getCedulaCliente();
					txtNombre1.setText(encontrada.getCedulaCliente());
                    txtTipoContrato1.setText(encontrada.getTipoContrato());
                    txtSalario1.setText(String.valueOf(encontrada.getPretensionSalarial()));
                    txtPais1.setText(encontrada.getPaisResidencia());
                    txtCuidad1.setText(encontrada.getCiudadResidencia());
                    if(encontrada.isDisponibilidadVehiculo() == true) {
                    	txtVehiculo1.setText("SI");
                    } else {
                    	txtVehiculo1.setText("NO");
                    }
                    if(encontrada.isLicencia() == true) {
                    	txtLicencia1.setText("SI");
                    } else {
                    	txtLicencia1.setText("NO");
                    }
                    if(encontrada.isMudanza() == true) {
                    	txtMudarse1.setText("SI");
                    } else {
                    	txtMudarse1.setText("NO");
                    }
                    Candidato aux = Bolsa.getInstance().buscarCandidatoByCod(encontrada.getCedulaCliente());
                    byte[] imgBytes =  aux.getImagen();
            		if (imgBytes != null) {
            		    ImageIcon icono = new ImageIcon(imgBytes);
            		    Image imagenEscalada = icono.getImage().getScaledInstance(117, 99, Image.SCALE_SMOOTH);
            		    lbImagen1.setIcon(new ImageIcon(imagenEscalada));
            		    lbImagen1.setText("");
            		} else {
            		    lbImagen1.setIcon(null);
            		    lbImagen1.setText("Sin imagen");
            		}
                    break;
                case 1:
                	lbPorcentaje2.setText(String.format("%.2f", match.getPorcentaje()[i])+"%");
                	cedula2 = encontrada.getCedulaCliente();
                	txtNombre2.setText(encontrada.getCedulaCliente());
                    txtTipoContrato2.setText(encontrada.getTipoContrato());
                    txtSalario2.setText(String.valueOf(encontrada.getPretensionSalarial()));
                    txtPais2.setText(encontrada.getPaisResidencia());
                    txtCuidad2.setText(encontrada.getCiudadResidencia());
                    if(encontrada.isDisponibilidadVehiculo() == true) {
                    	txtVehiculo2.setText("SI");
                    } else {
                    	txtVehiculo2.setText("NO");
                    }
                    if(encontrada.isLicencia() == true) {
                    	txtLicencia2.setText("SI");
                    } else {
                    	txtLicencia2.setText("NO");
                    }
                    if(encontrada.isMudanza() == true) {
                    	txtMudarse2.setText("SI");
                    } else {
                    	txtMudarse2.setText("NO");
                    }
                    Candidato aux2 = Bolsa.getInstance().buscarCandidatoByCod(encontrada.getCedulaCliente());
                    byte[] imgBytes2 =  aux2.getImagen();
            		if (imgBytes2 != null) {
            		    ImageIcon icono = new ImageIcon(imgBytes2);
            		    Image imagenEscalada = icono.getImage().getScaledInstance(117, 99, Image.SCALE_SMOOTH);
            		    lbImagen2.setIcon(new ImageIcon(imagenEscalada));
            		    lbImagen2.setText("");
            		} else {
            		    lbImagen2.setIcon(null);
            		    lbImagen2.setText("Sin imagen");
            		}
                    break;
                case 2:
                	lbPorcentaje3.setText(String.format("%.2f", match.getPorcentaje()[i])+"%");
                	cedula3 = encontrada.getCedulaCliente();
                	txtNombre3.setText(encontrada.getCedulaCliente());
                    txtTipoContrato3.setText(encontrada.getTipoContrato());
                    txtSalario3.setText(String.valueOf(encontrada.getPretensionSalarial()));
                    txtPais3.setText(encontrada.getPaisResidencia());
                    txtCiudad3.setText(encontrada.getCiudadResidencia());
                    if(encontrada.isDisponibilidadVehiculo() == true) {
                    	txtVehiculo3.setText("SI");
                    } else {
                    	txtVehiculo3.setText("NO");
                    }
                    if(encontrada.isLicencia() == true) {
                    	txtLicencia3.setText("SI");
                    } else {
                    	txtLicencia3.setText("NO");
                    }
                    if(encontrada.isMudanza() == true) {
                    	txtMudarse3.setText("SI");
                    } else {
                    	txtMudarse3.setText("NO");
                    }
                    Candidato aux3 = Bolsa.getInstance().buscarCandidatoByCod(encontrada.getCedulaCliente());
                    byte[] imgBytes3 =  aux3.getImagen();
            		if (imgBytes3 != null) {
            		    ImageIcon icono = new ImageIcon(imgBytes3);
            		    Image imagenEscalada = icono.getImage().getScaledInstance(117, 99, Image.SCALE_SMOOTH);
            		    lbImagen3.setIcon(new ImageIcon(imagenEscalada));
            		    lbImagen3.setText("");
            		} else {
            		    lbImagen3.setIcon(null);
            		    lbImagen3.setText("Sin imagen");
            		}
                    break;
				}
			}
			System.out.println(cedula1 + " " + cedula2 + " "+ cedula3); //Borrar
		}
	}

	private PorcentajeMatch buscarAuxByInfo(String seleccion) {
		// TODO Auto-generated method stub
		PorcentajeMatch aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < Bolsa.getInstance().getMisPorcentajesMatches().size()) {
			String auxP = Bolsa.getInstance().getMisPorcentajesMatches().get(i).getMisVacantes().getNombreVacante() + " - " + Bolsa.getInstance().getMisPorcentajesMatches().get(i).getMisVacantes().getRncEmpresa();
			if(auxP.equals(seleccion)) {
				aux = Bolsa.getInstance().getMisPorcentajesMatches().get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	private Vacante buscarAuxVacanteByInfo(String seleccion) {
		// TODO Auto-generated method stub
		Vacante aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < Bolsa.getInstance().getMisVacantes().size()) {
			String auxV = Bolsa.getInstance().getMisVacantes().get(i).getNombreVacante() + " - " + Bolsa.getInstance().getMisVacantes().get(i).getRncEmpresa();
			if(auxV.equals(seleccion)) {
				aux = Bolsa.getInstance().getMisVacantes().get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
}
