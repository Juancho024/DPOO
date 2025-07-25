package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Vacante;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
	private JTextField txtContrato2;
	private JTextField txtNombre2;
	private JTextField txtMudarse3;
	private JTextField txtLicencia3;
	private JTextField txtVehiculo3;
	private JTextField txtCiudad3;
	private JTextField txtPais3;
	private JTextField txtSalario3;
	private JTextField txtContrato3;
	private JTextField txtNombre3;
	private Button btnContratar2;
	private Button btnContratar3;
	private Button btnContratar1;

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
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Label label = new Label("Elegir una Vacante:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(23, 23, 123, 22);
		panel.add(label);
		
		JComboBox cbxVacantes = new JComboBox();
		cbxVacantes.setBounds(152, 25, 499, 20);
		panel.add(cbxVacantes);
		
		JPanel panel_Postulacion1 = new JPanel();
		panel_Postulacion1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Informaci\u00F3n Postulaci\u00F3n 1 ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Postulacion1.setBounds(15, 68, 368, 549);
		panel.add(panel_Postulacion1);
		panel_Postulacion1.setLayout(null);
		
		btnContratar1 = new Button("Contratar");
		btnContratar1.setBackground(new Color(220, 53, 69)); 
		btnContratar1.setForeground(Color.WHITE);
		btnContratar1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar1.setBounds(140, 492, 70, 22);
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
		
		JPanel panel_Postulacion2 = new JPanel();
		panel_Postulacion2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Informaci\u00F3n Postulaci\u00F3n 2 ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Postulacion2.setBounds(398, 68, 376, 549);
		panel.add(panel_Postulacion2);
		panel_Postulacion2.setLayout(null);
		
		btnContratar2 = new Button("Contratar");
		btnContratar2.setBackground(new Color(220, 53, 69)); 
		btnContratar2.setForeground(Color.WHITE);
		btnContratar2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar2.setBounds(140, 492, 70, 22);
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
		
		txtContrato2 = new JTextField();
		txtContrato2.setEnabled(false);
		txtContrato2.setEditable(false);
		txtContrato2.setColumns(10);
		txtContrato2.setBounds(18, 237, 162, 20);
		panel_Postulacion2.add(txtContrato2);
		
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
		
		JPanel panel_Postulacion3 = new JPanel();
		panel_Postulacion3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n Postulaci\u00F3n 3 ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Postulacion3.setBounds(789, 68, 368, 549);
		panel.add(panel_Postulacion3);
		panel_Postulacion3.setLayout(null);
		
		btnContratar3 = new Button("Contratar");
		btnContratar3.setBackground(new Color(220, 53, 69)); 
		btnContratar3.setForeground(Color.WHITE);
		btnContratar3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnContratar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContratar3.setBounds(140, 492, 70, 22);
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
		
		txtContrato3 = new JTextField();
		txtContrato3.setEnabled(false);
		txtContrato3.setEditable(false);
		txtContrato3.setColumns(10);
		txtContrato3.setBounds(10, 236, 162, 20);
		panel_Postulacion3.add(txtContrato3);
		
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
		
		for(Vacante aux: Bolsa.getInstance().getMisVacantes()) {
			JPanel panel_Info = new JPanel();
			panel_Info.setBorder(null);
			panel_Info.setBounds(10, 21, 1154, 596);
			
			Label lbInfo = new Label(aux.getNombreVacante() + "-" + aux.getIdentificador());
			lbInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			Button btnConsultar = new Button("Consultar");
			btnConsultar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 12));
            
            panel_Info.add(lbInfo, BorderLayout.CENTER);
            panel_Info.add(btnConsultar, BorderLayout.EAST);
            panel.add(panel_Info);
			
		}
	}
}
