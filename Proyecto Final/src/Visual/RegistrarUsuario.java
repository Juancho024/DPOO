package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarUsuario extends JDialog {

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
	private JCheckBox chkMasculino;
	private JCheckBox chkFemenino;
	private JPanel panel_Universitario;
	private JPanel panel_TecnicoSuperior;
	private JPanel panel_Obrero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(86, 69, 170, 22);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		Label label_3 = new Label("Genero: ");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(158, 147, 91, 22);
		contentPanel.add(label_3);
		
		chkMasculino = new JCheckBox("Masculino");
		chkMasculino.setBounds(255, 147, 97, 22);
		contentPanel.add(chkMasculino);
		
		chkFemenino = new JCheckBox("Femenino");
		chkFemenino.setBounds(371, 147, 97, 22);
		contentPanel.add(chkFemenino);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(86, 109, 170, 22);
		contentPanel.add(txtTelefono);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Datos Personales: ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 564, 170);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		Label label_2 = new Label("Apellido: ");
		label_2.setBounds(256, 56, 62, 22);
		panel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		Label label_5 = new Label("Correo: ");
		label_5.setBounds(256, 98, 62, 22);
		panel.add(label_5);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(324, 98, 228, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(324, 56, 228, 22);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		Label label = new Label("Cedula: ");
		label.setBounds(10, 20, 49, 22);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtCedula = new JTextField();
		txtCedula.setBounds(76, 20, 170, 22);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		Label label_1 = new Label("Nombre:");
		label_1.setBounds(10, 56, 62, 22);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		Label label_4 = new Label("Telefono: ");
		label_4.setBounds(10, 98, 62, 22);
		panel.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, " Nivel de Estudios: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 192, 564, 71);
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
		rdbtnTecnicoSuperior.setBounds(186, 28, 133, 23);
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
		rdbtnObrero.setBounds(370, 28, 133, 23);
		panel_1.add(rdbtnObrero);
		
		panel_Obrero = new JPanel();
		panel_Obrero.setBorder(new TitledBorder(null, " Obrero ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Obrero.setBounds(10, 274, 564, 138);
		contentPanel.add(panel_Obrero);
		
		panel_Universitario = new JPanel();
		panel_Universitario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Universitario ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Universitario.setBounds(10, 274, 564, 138);
		contentPanel.add(panel_Universitario);
		panel_Universitario.setLayout(null);
		
		panel_TecnicoSuperior = new JPanel();
		panel_TecnicoSuperior.setBorder(new TitledBorder(null, "T\u00E9cnico Superior ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TecnicoSuperior.setBounds(10, 274, 564, 138);
		contentPanel.add(panel_TecnicoSuperior);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
