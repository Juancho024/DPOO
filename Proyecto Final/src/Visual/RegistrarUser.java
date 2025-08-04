package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.User;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RegistrarUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtUser;
	private JPasswordField txtpassword1;
	private JPasswordField txtpassword2;
	private JComboBox cbxTipoUser;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUser dialog = new RegistrarUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUser() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 450, 330);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Label label = new Label("Tipo de Usuario:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(39, 66, 105, 22);
		contentPanel.add(label);
		
		cbxTipoUser = new JComboBox();
		cbxTipoUser.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Administrador", "Cliente"}));
		cbxTipoUser.setBounds(163, 68, 231, 20);
		contentPanel.add(cbxTipoUser);
		
		Label label_1 = new Label("Nombre de Usuario: ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(39, 110, 120, 22);
		contentPanel.add(label_1);
		
		txtUser = new JTextField();
		txtUser.setBackground(SystemColor.inactiveCaptionBorder);
		txtUser.setBounds(193, 112, 201, 20);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		Label label_2 = new Label("Contrase\u00F1a de Acceso: ");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(39, 154, 149, 22);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("Confirmar Contrase\u00F1a: ");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(39, 198, 149, 22);
		contentPanel.add(label_3);
		
		txtpassword1 = new JPasswordField();
		txtpassword1.setBackground(SystemColor.inactiveCaptionBorder);
		txtpassword1.setBounds(207, 156, 187, 20);
		contentPanel.add(txtpassword1);
		
		txtpassword2 = new JPasswordField();
		txtpassword2.setBackground(SystemColor.inactiveCaptionBorder);
		txtpassword2.setBounds(207, 200, 187, 20);
		contentPanel.add(txtpassword2);
		{
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.setForeground(Color.WHITE);
	        btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
	        btnRegistrar.setBackground(SystemColor.textHighlight);
			btnRegistrar.setBounds(169, 244, 105, 22);
			contentPanel.add(btnRegistrar);
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String user = txtUser.getText();
					String tipoUser = cbxTipoUser.getSelectedItem().toString();
					String password1 = new String(txtpassword1.getPassword());
					String password2 = new String(txtpassword2.getPassword());
					if(user.isEmpty() || cbxTipoUser.getSelectedIndex() == 0 || password1.isEmpty() || password2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(Bolsa.getInstance().buscarUserByUser(user) != null) {
						JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
						txtUser.setText("");
						txtUser.requestFocus();
						return;
					}
					if(!password1.equals(password2)) {
						JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden.\nPor favor, verifique e intente nuevamente.", "Validación de Contraseña", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						User aux = new User(tipoUser, user, password1);
						Bolsa.getInstance().registrarUser(aux);
						JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				}
			});
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 444, 51);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("DATOS DE USUARIO");
		lblNewJgoodiesTitle.setForeground(SystemColor.window);
        lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(139, 11, 166, 29);
		panel.add(lblNewJgoodiesTitle);
	}
	private void clean() {
		// TODO Auto-generated method stub
		cbxTipoUser.setSelectedIndex(0);
		txtUser.setText("");
		txtpassword1.setText("");
		txtpassword2.setText("");
	}
}
