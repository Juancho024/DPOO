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
        		RegistrarCandidato regCan = new RegistrarCandidato();
        		regCan.setModal(true);
        		regCan.setVisible(true);
        	}
        });
        lbCandidatos.setAlignment(Label.CENTER);
        lbCandidatos.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbCandidatos.setBounds(927, 10, 78, 22);
        panel.add(lbCandidatos);
        
        lbEmpresa = new Label("Empresa");
        lbEmpresa.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegistrarEmpresa regEmp = new RegistrarEmpresa();
        		regEmp.setModal(true);
        		regEmp.setVisible(true);
        	}
        });
        lbEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbEmpresa.setAlignment(Label.CENTER);
        lbEmpresa.setBounds(1025, 10, 78, 22);
        panel.add(lbEmpresa);
        
        lbListado = new Label("Listados y Reportes");
        lbListado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbListado.setAlignment(Label.CENTER);
        lbListado.setBounds(1121, 10, 127, 22);
        panel.add(lbListado);
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