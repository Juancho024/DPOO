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
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtUser;
	private JPasswordField txtpassword;
	private JLabel logo;
	private boolean passwordVisible = false;
	private JLabel ojoCerrado;
	private JLabel ojoAbierto;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Recursos/logo.jpg")));
		setTitle("Iniciar Secci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 415);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		logo = DefaultComponentFactory.getInstance().createLabel("");
		logo.setIcon(RoundedImageIcon.fromResource("/Recursos/perfilv2.jpg", 120)); 
		logo.setBounds(88, 11, 120, 120);
		panel.add(logo);

		JPanel panel_1 = new JPanelRedondeado(60);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setLayout(null);
		panel_1.setBounds(10, 101, 279, 251);
		panel.add(panel_1);

		Label label = new Label("Ingresar su Usuario: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(28, 47, 134, 22);
		panel_1.add(label);

		txtUser = new JTextField();
		txtUser.setBackground(SystemColor.inactiveCaptionBorder);
		txtUser.setColumns(10);
		txtUser.setBounds(28, 76, 233, 20);
		panel_1.add(txtUser);

		Label label_1 = new Label("Ingresar su Contrase\u00F1a:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(28, 102, 151, 22);
		panel_1.add(label_1);

		JButton button = new JButton("Entrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String clave = new String(txtpassword.getPassword());
				if (Bolsa.getInstance().confirmUser(user, clave)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(SystemColor.textHighlight);
		button.setBounds(89, 173, 100, 30);
		panel_1.add(button);

		txtpassword = new JPasswordField();
		txtpassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String clave = new String(txtpassword.getPassword());
				if (Bolsa.getInstance().confirmUser(user, clave)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		txtpassword.setBackground(SystemColor.inactiveCaptionBorder);
		txtpassword.setBounds(28, 130, 188, 20);
		panel_1.add(txtpassword);

		ojoCerrado = DefaultComponentFactory.getInstance().createLabel("");
		ojoCerrado.setVisible(false);
		ojoCerrado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtpassword.setEchoChar('•'); 
				ojoCerrado.setVisible(false);
				ojoAbierto.setVisible(true);
			}
		});
		ojoCerrado.setHorizontalAlignment(SwingConstants.CENTER);
		ojoCerrado.setIcon(new ImageIcon(Login.class.getResource("/Recursos/ojoCerrado.png")));
		ojoCerrado.setBounds(226, 130, 35, 20);
		panel_1.add(ojoCerrado);

		ojoAbierto = DefaultComponentFactory.getInstance().createLabel("");
		ojoAbierto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtpassword.setEchoChar((char) 0);
				ojoCerrado.setVisible(true);
				ojoAbierto.setVisible(false);
			}
		});
		ojoAbierto.setHorizontalAlignment(SwingConstants.CENTER);
		ojoAbierto.setIcon(new ImageIcon(Login.class.getResource("/Recursos/ojoAbierto.png")));
		ojoAbierto.setBounds(226, 130, 35, 20);
		panel_1.add(ojoAbierto);

	}
}