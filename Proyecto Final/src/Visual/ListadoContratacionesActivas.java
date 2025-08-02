package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.HistorialMatch;

public class ListadoContratacionesActivas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

    public ListadoContratacionesActivas() {
        setTitle("Historial de Contrataciones");
        setBounds(100, 100, 900, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        String[] headers = {
            "ID Vacante", 
            "RNC Empresa", 
            "ID Postulación", 
            "Cédula Candidato", 
            "Nombre Candidato"
        };
        
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        loadHistorialTable();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCerrar);
    }

    private void loadHistorialTable() {
        model.setRowCount(0); // Limpiar tabla
        
        // Verificar que la lista de contrataciones existe
        if (Bolsa.getInstance().getMisContrataciones() == null) {
            return;
        }
        
        for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
            // Verificar que el objeto HistorialMatch y sus componentes no son nulos
            if (hm == null || hm.getVacanteEmpleada() == null || hm.getPostulacionEmpleada() == null) {
                continue;
            }
            
            Object[] fila = new Object[5];
            
            // Obtener datos de la vacante
            fila[0] = hm.getVacanteEmpleada().getIdentificador();
            fila[1] = hm.getVacanteEmpleada().getRncEmpresa();
            
            // Obtener datos de la postulación
            fila[2] = hm.getPostulacionEmpleada().getIdentificador();
            fila[3] = hm.getPostulacionEmpleada().getCedulaCliente();
            
            // Buscar candidato de forma segura
            Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(
                hm.getPostulacionEmpleada().getCedulaCliente()
            );
            
            if (cand != null) {
                fila[4] = cand.getNombre() + " " + cand.getApellido();
            } else {
                fila[4] = "Candidato no encontrado (" + hm.getPostulacionEmpleada().getCedulaCliente() + ")";
            }
            
            model.addRow(fila);
        }
    }
}