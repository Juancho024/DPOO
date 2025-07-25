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
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

public class ModUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
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
		setTitle("Modificación Usuario");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Datos ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Label label = new Label("Tipo de Usuario:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(30, 35, 105, 22);
		contentPanel.add(label);
		
		cbxTipoUser = new JComboBox();
		cbxTipoUser.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Administrador", "Cliente"}));
		cbxTipoUser.setBounds(154, 37, 231, 20);
		contentPanel.add(cbxTipoUser);
		
		Label label_1 = new Label("Nombre de Usuario: ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(30, 79, 120, 22);
		contentPanel.add(label_1);
		
		txtUser = new JTextField();
		txtUser.setEnabled(false);
		txtUser.setEditable(false);
		txtUser.setBounds(184, 81, 201, 20);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		Label label_2 = new Label("Contrase\u00F1a de Acceso: ");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(30, 123, 149, 22);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("Confirmar Contrase\u00F1a: ");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(30, 167, 149, 22);
		contentPanel.add(label_3);
		
		txtpassword1 = new JPasswordField();
		txtpassword1.setBounds(198, 125, 187, 20);
		contentPanel.add(txtpassword1);
		
		txtpassword2 = new JPasswordField();
		txtpassword2.setBounds(198, 169, 187, 20);
		cbxTipoUser.setSelectedItem(selected.getTipoUser());
		txtUser.setText(selected.getUserName());
		txtpassword1.setText(selected.getPassword());
		txtpassword2.setText(selected.getPassword());
		contentPanel.add(txtpassword2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Modificar");
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
		}
	}
}
