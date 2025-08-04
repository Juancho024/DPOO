package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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

public class ModUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtUser;
	private JPasswordField txtpassword1;
	private JPasswordField txtpassword2;
	private JComboBox cbxTipoUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModUser dialog = new ModUser(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al abrir la ventana: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModUser(User selected) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setTitle("Modificación Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
		setBounds(100, 100, 450, 329);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBounds(10, 53, 424, 236);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		Label label = new Label("Tipo de Usuario:");
		label.setBounds(30, 10, 105, 22);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(label);
		
		cbxTipoUser = new JComboBox();
		cbxTipoUser.setBounds(154, 12, 231, 20);
		cbxTipoUser.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Administrador", "Cliente"}));
		contentPanel.add(cbxTipoUser);
		
		Label label_1 = new Label("Nombre de Usuario: ");
		label_1.setBounds(30, 54, 120, 22);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(label_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(184, 56, 201, 20);
		txtUser.setEnabled(false);
		txtUser.setEditable(false);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		Label label_2 = new Label("Contrase\u00F1a de Acceso: ");
		label_2.setBounds(30, 98, 149, 22);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(label_2);
		
		Label label_3 = new Label("Confirmar Contrase\u00F1a: ");
		label_3.setBounds(30, 142, 149, 22);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(label_3);
		
		txtpassword1 = new JPasswordField();
		txtpassword1.setBounds(198, 100, 187, 20);
		contentPanel.add(txtpassword1);
		
		txtpassword2 = new JPasswordField();
		txtpassword2.setBounds(198, 144, 187, 20);
		cbxTipoUser.setSelectedItem(selected.getTipoUser());
		txtUser.setText(selected.getUserName());
		txtpassword1.setText(selected.getPassword());
		txtpassword2.setText(selected.getPassword());
		contentPanel.add(txtpassword2);
		{
			btnRegistrar = new JButton("Modificar");
			btnRegistrar.setForeground(Color.WHITE);
	        btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
	        btnRegistrar.setBackground(SystemColor.textHighlight);
			btnRegistrar.setBounds(159, 188, 105, 23);
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
					if(Bolsa.getInstance().validarEliminarUser(user) == true) {
						JOptionPane.showMessageDialog(null, "No se puede modificar todos los usuarios a Cliente.\nDebe permanecer al menos un usuario administrador para garantizar el acceso al sistema.", "Advertencia", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!password1.equals(password2)) {
						JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden.\nPor favor, verifique e intente nuevamente.", "Validación de Contraseña", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						selected.setTipoUser(tipoUser);
						selected.setUserName(user);
						selected.setPassword(password1);
						Bolsa.getInstance().modificarUser(selected);
						JOptionPane.showMessageDialog(null, "Modificación Satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
			});
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 444, 46);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("DATOS DE USUARIO");
		lblNewJgoodiesTitle.setForeground(SystemColor.window);
        lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(139, 13, 166, 20);
		panel.add(lblNewJgoodiesTitle);
	}
}
