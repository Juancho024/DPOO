package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;

import java.awt.Label;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private Dimension dim;
	private JLabel lbCandidatos;
	private JLabel lbEmpresa;
	private JLabel lbListado;
	private JPopupMenu menuCandidatos;
	private JPopupMenu menuEmpresa;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JPopupMenu MenuListados;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenuItem mntmNewMenuItem_7;
	private JLabel lbMatch;
	private JLabel lbAdministracion;
	private JPopupMenu menuUsuario;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	private JMenuItem mntmNewMenuItem_11;
	private JMenuItem mntmNewMenuItem_10;
	private JPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setTitle("JJ² PROJECT");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream bolsa2;
				ObjectOutputStream bolsaWrite;
				try {
					bolsa2 = new FileOutputStream("Bolsa.dat");
					bolsaWrite = new ObjectOutputStream(bolsa2);
					bolsaWrite.writeObject(Bolsa.getInstance());
					System.out.println("Datos guardados correctamente.");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane_1 = new PanelConFondo("/Recursos/fondov2.png");
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);

		lbMatch = new RoundedLabel("MATCH", new Color(0, 102, 153), new Font("Arial", Font.BOLD, 14), Color.WHITE);
		lbMatch.setBounds(1230, 11, 100, 26); // Tamaño y posición más amplios
		lbMatch.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(!Bolsa.getLoginUser().getTipoUser().equalsIgnoreCase("Administrador")) {
		            JOptionPane.showMessageDialog(
		                null,
		                "Solo los Administradores tienen acceso a esa opción.",
		                "Acceso denegado",
		                JOptionPane.WARNING_MESSAGE
		            );
		        } else {
		            RealizarMatch reaMat = new RealizarMatch();
		            reaMat.setModal(true);
		            reaMat.setVisible(true);
		        }
		    }
		});
		contentPane_1.setLayout(null);
		contentPane_1.add(lbMatch);

		lbCandidatos = new RoundedLabel("Candidato", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbCandidatos.setBounds(805, 11, 100, 26);
		lbCandidatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCandidatos.show(lbCandidatos, 0, lbCandidatos.getHeight());
			}
		});
		contentPane_1.add(lbCandidatos);

		menuCandidatos = new JPopupMenu();
		addPopup(lbCandidatos, menuCandidatos);

		mntmNewMenuItem_2 = new JMenuItem("Registro");
		mntmNewMenuItem_2.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/candidato.png")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCandidato regCan = new RegistrarCandidato();
				regCan.setModal(true);
				regCan.setVisible(true);
			}
		});
		menuCandidatos.add(mntmNewMenuItem_2);

		mntmNewMenuItem_3 = new JMenuItem("Abrir postulaci\u00F3n");
		mntmNewMenuItem_3.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/postulacion.png")));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPostulacion regPos = new RegistrarPostulacion();
				regPos.setModal(true);
				regPos.setVisible(true);
			}
		});
		menuCandidatos.add(mntmNewMenuItem_3);

		lbEmpresa = new RoundedLabel("Empresa", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuEmpresa.show(lbEmpresa, 0, lbEmpresa.getHeight());
			}
		});
		lbEmpresa.setBounds(929, 11, 100, 26);
		contentPane_1.add(lbEmpresa);

		menuEmpresa = new JPopupMenu();
		addPopup(lbEmpresa, menuEmpresa);

		mntmNewMenuItem = new JMenuItem("Registro");
		mntmNewMenuItem.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listEmpresa.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpresa regEmp = new RegistrarEmpresa();
				regEmp.setModal(true);
				regEmp.setVisible(true);
			}
		});
		menuEmpresa.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Crear Vacante");
		mntmNewMenuItem_1.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/vacante.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacante regVac = new RegistrarVacante();
				regVac.setModal(true);
				regVac.setVisible(true);
			}
		});
		menuEmpresa.add(mntmNewMenuItem_1);
		// FIN DE MENÚS AÑADIDOS

		lbListado = new RoundedLabel("Listados y Reportes", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbListado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuListados.show(lbListado, 0, lbListado.getHeight());
			}
		});
		lbListado.setBounds(1051, 11, 154, 26); // Posición ajustada
		contentPane_1.add(lbListado);

		MenuListados = new JPopupMenu();
		addPopup(lbListado, MenuListados);

		mntmNewMenuItem_4 = new JMenuItem("Listados de Candidatos");
		mntmNewMenuItem_4.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listperson.png")));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoCandidatos lisCan = new ListadoCandidatos();
				lisCan.setModal(true);
				lisCan.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_4);

		mntmNewMenuItem_5 = new JMenuItem("Listados de Empresas");
		mntmNewMenuItem_5.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listUSer.png")));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoEmpresa lisEmp = new ListadoEmpresa();
				lisEmp.setModal(true);
				lisEmp.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_5);

		mntmNewMenuItem_6 = new JMenuItem("Listados de Vacantes");
		mntmNewMenuItem_6.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/vacante.png")));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoVacante lisVac = new ListadoVacante();
				lisVac.setModal(true);
				lisVac.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_6);

		mntmNewMenuItem_7 = new JMenuItem("Listados de Postulaci\u00F3nes");
		mntmNewMenuItem_7.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listperson.png")));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   ListadoPostulacion listPos = new ListadoPostulacion();
			        listPos.setModal(true);
			        listPos.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_7);
		
		JMenuItem mntmListadoContrataciones = new JMenuItem("Listado de Contrataciones");
		mntmListadoContrataciones.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listUSer.png")));
		mntmListadoContrataciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ListadoContratacionesActivas listCont = new ListadoContratacionesActivas();
			     listCont.setModal(true);
			     listCont.setVisible(true);
			}
		});
		MenuListados.add(mntmListadoContrataciones);
		
		JMenuItem mntmListadoHistorialContrataciones = new JMenuItem("Historial de Contrataciones\r\n");
		mntmListadoHistorialContrataciones.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/contrataciones.png")));
		mntmListadoHistorialContrataciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				ListadoHistorialContrataciones hm = new ListadoHistorialContrataciones();
				hm.setVisible(true);
				hm.setModal(true);
			}
		});
		MenuListados.add(mntmListadoHistorialContrataciones);
		
		lbAdministracion = new RoundedLabel("Administración", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbAdministracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuUsuario.show(lbAdministracion, 0, lbAdministracion.getHeight());
			}
		});
		lbAdministracion.setBounds(671, 11, 113, 26);
		contentPane_1.add(lbAdministracion);
		
		menuUsuario = new JPopupMenu();
		addPopup(lbAdministracion, menuUsuario);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Usuario");
		mntmNewMenuItem_8.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/persona.png")));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUser regUser = new RegistrarUser();
				regUser.setModal(true);
				regUser.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listado de Usuarios");
		mntmNewMenuItem_9.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/listUSer.png")));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUser lisUser = new ListadoUser();
				lisUser.setModal(true);
				lisUser.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_9);
		
		mntmNewMenuItem_11 = new JMenuItem("Realizar Respaldo");
		mntmNewMenuItem_11.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/respaldo.png")));
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
			    {
			      sfd = new Socket("127.0.0.1",7000);
			      DataInputStream aux = new DataInputStream(new FileInputStream(new File("Bolsa.dat")));
			      SalidaSocket = new DataOutputStream((sfd.getOutputStream()));
			      int unByte;
			      try
			      {
			    	while ((unByte = aux.read()) != -1){
			    		SalidaSocket.write(unByte);
						SalidaSocket.flush();
			    	}
			      }
			      catch (IOException ioe)
			      {
			        System.out.println("Error: "+ioe);
			      }
			    }
			    catch (UnknownHostException uhe)
			    {
			      System.out.println("No se puede acceder al servidor.");
			      System.exit(1);
			    }
			    catch (IOException ioe)
			    {
			      System.out.println("Comunicación rechazada.");
			      System.exit(1);
			    }
			}
		});
		menuUsuario.add(mntmNewMenuItem_11);
		
		mntmNewMenuItem_10 = new JMenuItem("Porcentaje Match");
		mntmNewMenuItem_10.setIcon(new ImageIcon(Principal.class.getResource("/Recursos/%.png")));
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PorcentajeMatch newPor = new PorcentajeMatch();
				newPor.setModal(true);
				newPor.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_10);
		
		panel = new RoundedImagePanel("/Recursos/logo.jpg", 550);
		panel.setBounds(683, 186, 587, 391);
		contentPane_1.add(panel);
		
		JLabel lblNewJgoodiesTitle = new JLabel();
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewJgoodiesTitle.setText("<html><div style='text-align: center; font-size:32px; color:#003366;'>"
		    + "¡Impulsa tu futuro hoy mismo!<br>"
		    + "Bienvenido a la Bolsa Laboral que te conecta con<br>"
		    + "las mejores oportunidades."
		    + "</div></html>");

		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setVerticalAlignment(SwingConstants.TOP);
		lblNewJgoodiesTitle.setOpaque(true);
		lblNewJgoodiesTitle.setBackground(Color.WHITE); // Fondo blanco
		lblNewJgoodiesTitle.setBounds(63, 218, 571, 261); // Ajusta según el espacio disponible

		contentPane_1.add(lblNewJgoodiesTitle);
		if(!Bolsa.getLoginUser().getTipoUser().equalsIgnoreCase("Administrador")) {
			lbAdministracion.setVisible(false);
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}