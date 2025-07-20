package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    private Label lbCandidatos;
    private Label lbEmpresa;
    private Label lbListado;
    private Label lbMatch;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height-40);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        lbMatch = new Label("Match");
        lbMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbMatch.setAlignment(Label.CENTER);
        lbMatch.setBounds(1254, 10, 62, 22);
        panel.add(lbMatch);
        
        lbCandidatos = new Label("Candidatos");
        lbCandidatos.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		menuCandidatos.show(lbCandidatos, 0, lbCandidatos.getHeight());
        	}
        });
        lbCandidatos.setAlignment(Label.CENTER);
        lbCandidatos.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbCandidatos.setBounds(819, 10, 78, 22);
        panel.add(lbCandidatos);
        
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
        menuCandidatos.add(mntmNewMenuItem_3);
        
        lbEmpresa = new Label("Empresa");
        lbEmpresa.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		menuEmpresa.show(lbEmpresa, 0, lbEmpresa.getHeight());
        	}
        });
        lbEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbEmpresa.setAlignment(Label.CENTER);
        lbEmpresa.setBounds(958, 10, 78, 22);
        panel.add(lbEmpresa);
        
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
        menuEmpresa.add(mntmNewMenuItem_1);
        
        lbListado = new Label("Listados y Reportes");
        lbListado.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		MenuListados.show(lbListado, 0, lbListado.getHeight());
        	}
        });
        lbListado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbListado.setAlignment(Label.CENTER);
        lbListado.setBounds(1091, 10, 127, 22);
        panel.add(lbListado);
        
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
        MenuListados.add(mntmNewMenuItem_5);
        
        mntmNewMenuItem_6 = new JMenuItem("Listados de Vacantes");
        MenuListados.add(mntmNewMenuItem_6);
        
        mntmNewMenuItem_7 = new JMenuItem("Listados de Postulaci\u00F3nes");
        MenuListados.add(mntmNewMenuItem_7);
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