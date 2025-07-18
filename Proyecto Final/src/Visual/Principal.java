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

public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;

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
        setBounds(100, 100, 450, 300);
        dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height-40);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnRegistros = new JMenu("Registros");
        menuBar.add(mnRegistros);
        
        JMenu mnEmpresa = new JMenu("Empresa");
        mnRegistros.add(mnEmpresa);
        
        JMenuItem mntmRegistrarEmpresa = new JMenuItem("Registrar Empresa");
        mntmRegistrarEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirRegistroEmpresa();
            }
        });
        mnEmpresa.add(mntmRegistrarEmpresa);
        
        JMenu mnCandidato = new JMenu("Candidato");
        mnRegistros.add(mnCandidato);
        
        JMenuItem mntmRegistrarCandidato = new JMenuItem("Registrar Candidato");
        mntmRegistrarCandidato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirRegistroCandidato();
            }
        });
        mnCandidato.add(mntmRegistrarCandidato);
        
        JMenu mnBuscarOfertas = new JMenu("Buscar Ofertas");
        menuBar.add(mnBuscarOfertas);
        
        JMenuItem mntmBuscarVacante = new JMenuItem("Buscar Vacante");
        mnBuscarOfertas.add(mntmBuscarVacante);
        
        JMenuItem mntmBuscarCandidato = new JMenuItem("Buscar Candidato");
        mnBuscarOfertas.add(mntmBuscarCandidato);
        
        JMenu mnListados = new JMenu("Listados y Reportes");
        menuBar.add(mnListados);
        
        JMenuItem mntmReporteEmpresas = new JMenuItem("Reporte de Empresas");
        mnListados.add(mntmReporteEmpresas);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
    }
    
    private void abrirRegistroEmpresa() {
        RegistrarEmpresa dialog = new RegistrarEmpresa();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    
    private void abrirRegistroCandidato() {
        RegistrarCandidato dialog = new RegistrarCandidato();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}