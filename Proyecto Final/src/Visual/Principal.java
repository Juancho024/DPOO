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

public class Principal extends JFrame {

	private JPanel contentPane;
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
		contentPane = new PanelConFondo("/Recursos/fondov2.png");
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

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
		contentPane.setLayout(null);
		contentPane.add(lbMatch);

		lbCandidatos = new RoundedLabel("Candidato", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbCandidatos.setBounds(805, 11, 100, 26);
		lbCandidatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuCandidatos.show(lbCandidatos, 0, lbCandidatos.getHeight());
			}
		});
		contentPane.add(lbCandidatos);

		menuCandidatos = new JPopupMenu();
		addPopup(lbCandidatos, menuCandidatos);

		mntmNewMenuItem_2 = new JMenuItem("Registro");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCandidato regCan = new RegistrarCandidato();
				regCan.setModal(true);
				regCan.setVisible(true);
			}
		});
		menuCandidatos.add(mntmNewMenuItem_2);

		mntmNewMenuItem_3 = new JMenuItem("Abrir postulaci\u00F3n");
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
		contentPane.add(lbEmpresa);

		menuEmpresa = new JPopupMenu();
		addPopup(lbEmpresa, menuEmpresa);

		mntmNewMenuItem = new JMenuItem("Registro");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpresa regEmp = new RegistrarEmpresa();
				regEmp.setModal(true);
				regEmp.setVisible(true);
			}
		});
		menuEmpresa.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Crear Vacante");
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
		contentPane.add(lbListado);

		MenuListados = new JPopupMenu();
		addPopup(lbListado, MenuListados);

		mntmNewMenuItem_4 = new JMenuItem("Listados de Candidatos");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoCandidatos lisCan = new ListadoCandidatos();
				lisCan.setModal(true);
				lisCan.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_4);

		mntmNewMenuItem_5 = new JMenuItem("Listados de Empresas");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoEmpresa lisEmp = new ListadoEmpresa();
				lisEmp.setModal(true);
				lisEmp.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_5);

		mntmNewMenuItem_6 = new JMenuItem("Listados de Vacantes");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoVacante lisVac = new ListadoVacante();
				lisVac.setModal(true);
				lisVac.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_6);

		mntmNewMenuItem_7 = new JMenuItem("Listados de Postulaci\u00F3nes");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   ListadoPostulacion listPos = new ListadoPostulacion();
			        listPos.setModal(true);
			        listPos.setVisible(true);
			}
		});
		MenuListados.add(mntmNewMenuItem_7);
		
		JMenuItem mntmListadoContrataciones = new JMenuItem("Listado de Contrataciones");
		mntmListadoContrataciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ListadoContratacionesActivas listCont = new ListadoContratacionesActivas();
			     listCont.setModal(true);
			     listCont.setVisible(true);
			}
		});
		MenuListados.add(mntmListadoContrataciones);
		
		lbAdministracion = new RoundedLabel("Administración", new Color(255, 230, 250), new Font("Arial", Font.PLAIN, 14), Color.BLACK);
		lbAdministracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuUsuario.show(lbAdministracion, 0, lbAdministracion.getHeight());
			}
		});
		lbAdministracion.setBounds(671, 11, 113, 26);
		contentPane.add(lbAdministracion);
		
		menuUsuario = new JPopupMenu();
		addPopup(lbAdministracion, menuUsuario);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Usuario");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUser regUser = new RegistrarUser();
				regUser.setModal(true);
				regUser.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listado de Usuarios");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUser lisUser = new ListadoUser();
				lisUser.setModal(true);
				lisUser.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_9);
		
		mntmNewMenuItem_11 = new JMenuItem("Realizar Respaldo");
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
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PorcentajeMatch newPor = new PorcentajeMatch();
				newPor.setModal(true);
				newPor.setVisible(true);
			}
		});
		menuUsuario.add(mntmNewMenuItem_10);
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