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
        setTitle("Contrataciones Activas");
        setBounds(100, 100, 1000, 400); // Aumentamos el ancho para la nueva columna
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

        // Añadimos la nueva columna "Estatus"
        String[] headers = {
            "ID Vacante", 
            "RNC Empresa", 
            "ID Postulación", 
            "Cédula Candidato", 
            "Nombre Candidato",
            "Estatus"  // Nueva columna
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
        
        if (Bolsa.getInstance().getMisContrataciones() == null) {
            return;
        }
        
        for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
            if (hm == null || hm.getVacanteEmpleada() == null || hm.getPostulacionEmpleada() == null) {
                continue;
            }
            
            Object[] fila = new Object[6]; // Ahora 6 columnas
            
            // Obtener datos de la vacante
            fila[0] = hm.getVacanteEmpleada().getIdentificador();
            fila[1] = hm.getVacanteEmpleada().getRncEmpresa();
            
            // Obtener datos de la postulación
            fila[2] = hm.getPostulacionEmpleada().getIdentificador();
            fila[3] = hm.getPostulacionEmpleada().getCedulaCliente();
            
            // Buscar candidato
            Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(
                hm.getPostulacionEmpleada().getCedulaCliente()
            );
            
            if (cand != null) {
                fila[4] = cand.getNombre() + " " + cand.getApellido();
                
                // Determinar el estatus basado en el campo 'status' del candidato
                // status = false -> contratado (activo)
                // status = true -> disponible (contratación inactiva)
                fila[5] = cand.isStatus() ? "Inactiva" : "Activa";
            } else {
                fila[4] = "Candidato no encontrado (" + hm.getPostulacionEmpleada().getCedulaCliente() + ")";
                fila[5] = "N/A";
            }
            
            model.addRow(fila);
        }
    }
}