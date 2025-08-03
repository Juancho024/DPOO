package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
    private JComboBox<String> cbxEstadoContratacion;
    private HistorialMatch selected = null;

    public ListadoContratacionesActivas() {
        setTitle("Contrataciones Activas");
        setBounds(100, 100, 1000, 500); // Aumentamos la altura para el filtro
        setLocationRelativeTo(null);
        setResizable(true);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        // Panel de filtro en la parte superior
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtrar por estado:"));
        cbxEstadoContratacion = new JComboBox<>(new String[]{"Todas", "Activas", "Inactivas"});
        cbxEstadoContratacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadContrataciones();
            }
        });
        panelFiltro.add(cbxEstadoContratacion);
        contentPanel.add(panelFiltro, BorderLayout.NORTH);

        // Tabla única con scroll pane
        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Configuración del modelo de la tabla
        String[] headers = {
            "ID Vacante", 
            "RNC Empresa", 
            "ID Postulación", 
            "Cédula Candidato", 
            "Nombre Candidato",
            "Estatus"
        };
        
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String idVacante = (String) model.getValueAt(row, 0);
                    String idPostulacion = (String) model.getValueAt(row, 2);
                    
                    // Buscar la contratación correspondiente
                    for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
                        if (hm != null && 
                            hm.getVacanteEmpleada() != null &&
                            hm.getVacanteEmpleada().getIdentificador().equals(idVacante) &&
                            hm.getPostulacionEmpleada() != null &&
                            hm.getPostulacionEmpleada().getIdentificador().equals(idPostulacion)) {
                            
                            selected = hm;
                            break;
                        }
                    }
                }
            }
        });

        // Cargar datos iniciales
        loadContrataciones();

        // Panel de botones en la parte inferior
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        
        JButton btnVerRepPost = new JButton("Ver Reporte Postulacion");
        btnVerRepPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null && selected.getPostulacionEmpleada() != null) {
                    ReportePostulacion repPost = new ReportePostulacion(selected.getPostulacionEmpleada());
                    repPost.setModal(true); // Importante para mantener el foco
                    repPost.setLocationRelativeTo(ListadoContratacionesActivas.this); // Centrar respecto a esta ventana
                    repPost.toFront(); // Traer al frente
                    repPost.setVisible(true);
                }
            }
        });
        buttonPane.add(btnVerRepPost);

        JButton btnVerRepVac = new JButton("Ver Reporte Vacante");
        btnVerRepVac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null && selected.getVacanteEmpleada() != null) {
                    ReporteVacante repVac = new ReporteVacante(selected.getVacanteEmpleada());
                    repVac.setModal(true);
                    repVac.setLocationRelativeTo(ListadoContratacionesActivas.this);
                    repVac.toFront();
                    repVac.setVisible(true);
                }
            }
        });
        buttonPane.add(btnVerRepVac);

        JButton btnDesemplear = new JButton("Desemplear");
        btnDesemplear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    // Tu implementación de desempleo aquí
                    Bolsa.getInstance().desemplearCandidato(selected);
                    loadContrataciones(); // Recargar datos
                    selected = null; // Resetear selección
                }
            }
        });
        buttonPane.add(btnDesemplear);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCerrar);
        
    }

    private void loadContrataciones() {
        model.setRowCount(0); // Limpiar tabla
        
        if (Bolsa.getInstance().getMisContrataciones() == null) {
            return;
        }
        
        int filtro = cbxEstadoContratacion.getSelectedIndex();
        
        for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
            if (hm == null || hm.getVacanteEmpleada() == null || hm.getPostulacionEmpleada() == null) {
                continue;
            }
            
            // Aplicar filtro por estado de la contratación
            
            boolean activa = Bolsa.getInstance().buscarCandidatoByCod(hm.getPostulacionEmpleada().getCedulaCliente()).isStatus();
            if (filtro == 1 && !activa) continue; // Solo activas
            if (filtro == 2 && activa) continue;   // Solo inactivas
            
            // Buscar candidato
            Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(
                hm.getPostulacionEmpleada().getCedulaCliente()
            );
            
            // Crear fila con datos unificados
            Object[] fila = new Object[6];
            fila[0] = hm.getVacanteEmpleada().getIdentificador();
            fila[1] = hm.getVacanteEmpleada().getRncEmpresa();
            fila[2] = hm.getPostulacionEmpleada().getIdentificador();
            fila[3] = hm.getPostulacionEmpleada().getCedulaCliente();
            fila[4] = (cand != null) ? cand.getNombre() + " " + cand.getApellido() : "Candidato no encontrado";
            fila[5] = activa ? "Activa" : "Inactiva";
            
            model.addRow(fila);
        }
    }
}