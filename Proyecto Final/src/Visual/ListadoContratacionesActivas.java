package Visual;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.DefaultComboBoxModel;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.HistorialMatch;

public class ListadoContratacionesActivas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> cbxEstadoContratacion;
    private HistorialMatch selected = null;
    
    public static void main(String[] args) {
        try {
            ListadoContratacionesActivas dialog = new ListadoContratacionesActivas();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListadoContratacionesActivas() {
        setTitle("Contrataciones Activas");
        setBounds(100, 100, 1000, 600); // Aumentado el tamaño
        setResizable(true);
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        
        // Estilo del contentPanel
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Más espacio
        contentPanel.setBackground(Color.decode("#ecf0f1")); // Fondo gris claro
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(10, 10)); // Espacio entre componentes

        // --- Panel de filtro en la parte superior ---
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5)); // Más espaciado
        panelFiltro.setBackground(Color.decode("#bdc3c7")); // Un gris más oscuro para el fondo del filtro
        panelFiltro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblFiltro = new JLabel("Filtrar por estado:");
        lblFiltro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblFiltro.setForeground(Color.decode("#333333")); // Color del texto
        panelFiltro.add(lblFiltro);

        cbxEstadoContratacion = new JComboBox<>();
        DefaultComboBoxModel<String> modelCbx = new DefaultComboBoxModel<>();
        modelCbx.addElement("Todas");
        modelCbx.addElement("Activas");
        modelCbx.addElement("Inactivas");
        cbxEstadoContratacion.setModel(modelCbx);
        panelFiltro.add(cbxEstadoContratacion);
        contentPanel.add(panelFiltro, BorderLayout.NORTH);

        // --- Tabla con scroll pane ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#bdc3c7"), 1)); // Borde sutil
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25); // Aumentamos la altura de la fila
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Fuente para las celdas
        
        // Estilo del encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente en negrita para el encabezado
        header.setBackground(Color.decode("#2c3e50")); // Fondo azul oscuro
        header.setForeground(Color.white); // Texto blanco
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        
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
                    // Lógica para seleccionar la fila
                    String idVacante = (String) model.getValueAt(row, 0);
                    String idPostulacion = (String) model.getValueAt(row, 2);
                    
                    for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
                        if (hm != null && hm.getVacanteEmpleada() != null &&
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

        // --- Panel de botones en la parte inferior ---
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Margen entre botones
        buttonPane.setBackground(Color.decode("#ecf0f1"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        // Botones con estilo moderno
        JButton btnVerRepPost = createStyledButton("Ver Reporte Postulacion", "#3498db");
        btnVerRepPost.addActionListener(e -> {
            if (selected != null && selected.getPostulacionEmpleada() != null) {
            	ReportePostulacion repPost = new ReportePostulacion(selected.getPostulacionEmpleada());
                repPost.setModal(true); // Importante para mantener el foco
                repPost.setLocationRelativeTo(ListadoContratacionesActivas.this); // Centrar respecto a esta ventana
                repPost.toFront(); // Traer al frente
                repPost.setVisible(true);
            }
        });
        buttonPane.add(btnVerRepPost);

        JButton btnVerRepVac = createStyledButton("Ver Reporte Vacante", "#3498db");
        btnVerRepVac.addActionListener(e -> {
            if (selected != null && selected.getVacanteEmpleada() != null) {
            	ReporteVacante repVac = new ReporteVacante(selected.getVacanteEmpleada());
                repVac.setModal(true);
                repVac.setLocationRelativeTo(ListadoContratacionesActivas.this);
                repVac.toFront();
                repVac.setVisible(true);
            }
        });
        buttonPane.add(btnVerRepVac);

        JButton btnDesemplear = createStyledButton("Desemplear", "#e74c3c"); // Rojo para acción destructiva
        btnDesemplear.addActionListener(e -> {
            if (selected != null) {
                Bolsa.getInstance().desemplearCandidato(selected);
                loadContrataciones();
                selected = null;
            }
        });
        buttonPane.add(btnDesemplear);

        JButton btnCerrar = createStyledButton("Cerrar", "#95a5a6"); // Gris para cerrar
        btnCerrar.addActionListener(e -> dispose());
        buttonPane.add(btnCerrar);
    }

    // Método auxiliar para crear botones con estilo
    private JButton createStyledButton(String text, String hexColor) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode(hexColor));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        button.setBorderPainted(false); // Eliminar el borde feo
        return button;
    }

    private void loadContrataciones() {
        // ... (Tu lógica existente para cargar datos)
        model.setRowCount(0);
        
        if (Bolsa.getInstance().getMisContrataciones() == null) {
            return;
        }
        
        int filtro = cbxEstadoContratacion.getSelectedIndex();
        
        for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
            if (hm == null || hm.getVacanteEmpleada() == null || hm.getPostulacionEmpleada() == null) {
                continue;
            }
            
            boolean activa = Bolsa.getInstance().buscarCandidatoByCod(hm.getPostulacionEmpleada().getCedulaCliente()).isStatus();
            if (filtro == 1 && !activa) continue;
            if (filtro == 2 && activa) continue;
            
            Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(hm.getPostulacionEmpleada().getCedulaCliente());
            
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