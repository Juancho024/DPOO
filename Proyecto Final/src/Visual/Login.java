package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Bolsa;
import Logico.User;
import javax.swing.border.TitledBorder;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JPasswordField txtpasssword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream bolsa;
				FileOutputStream bolsa2;
				ObjectInputStream bolsaRead;
				ObjectOutputStream bolsaWrite;
				try {
					bolsa = new FileInputStream("Bolsa.dat");
					bolsaRead = new ObjectInputStream(bolsa);
					Bolsa temp = (Bolsa) bolsaRead.readObject();
					Bolsa.setControl(temp);
					bolsa.close();
					bolsaRead.close();
				} catch (FileNotFoundException e) {
					try {
						bolsa2 = new FileOutputStream("Bolsa.dat");
						bolsaWrite = new ObjectOutputStream(bolsa2);
						User aux = new User("Administrador", "Admin", "1234");
						Bolsa.getInstance().registrarUser(aux);
						bolsaWrite.writeObject(Bolsa.getInstance());
						bolsa2.close();
						bolsaWrite.close();
					} catch(FileNotFoundException e1) {
						
					} catch (IOException e1) { 
						
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Iniciar Secci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, " Ingresar sus Datos ", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Label label = new Label("Ingresar su Usuario: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(28, 47, 134, 22);
		panel.add(label);
		
		txtUser = new JTextField();
		txtUser.setBounds(164, 47, 180, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		Label label_1 = new Label("Ingresar su Contrase\u00F1a:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(28, 102, 151, 22);
		panel.add(label_1);
		
		btnRegistrar = new JButton("Entrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String clave = new String(txtpasssword.getPassword());
				if (Bolsa.getInstance().confirmUser(user, clave)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			//fuap
		});
		btnRegistrar.setBackground(new Color(0, 120, 215));
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrar.setBounds(91, 173, 100, 30);
		panel.add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(220, 53, 69)); // Rojo claro tipo Bootstrap
		btnCancelar.setForeground(Color.WHITE); // Texto blanco
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setBounds(229, 173, 100, 30);
		panel.add(btnCancelar);
		
		txtpasssword = new JPasswordField();
		txtpasssword.setBounds(185, 102, 159, 20);
		panel.add(txtpasssword);
	}
}