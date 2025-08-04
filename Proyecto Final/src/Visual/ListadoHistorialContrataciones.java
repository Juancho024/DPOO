package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList; // CAMBIO REALIZADO: Importación necesaria
import java.util.List;      // CAMBIO REALIZADO: Importación necesaria

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.HistorialMatch;
import Logico.Postulacion;
import Logico.Vacante;

public class ListadoHistorialContrataciones extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<Candidato> cbxCandidatos;
    private HistorialMatch selected = null;
    
    // CAMBIO REALIZADO: Lista para un acceso eficiente a los datos de la tabla.
    private List<HistorialMatch> contratacionesMostradas;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoHistorialContrataciones dialog = new ListadoHistorialContrataciones();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoHistorialContrataciones() {
        // CAMBIO REALIZADO: Inicializar la lista.
        this.contratacionesMostradas = new ArrayList<>();

        setTitle("Contrataciones por Candidato");
        setBounds(100, 100, 1000, 500);
        setLocationRelativeTo(null);
        setResizable(true);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        // Panel de filtro superior
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelFiltro.setBackground(new Color(240, 240, 240));
        panelFiltro.setBorder(new LineBorder(new Color(200, 200, 200)));
        
        JLabel lblFiltro = new JLabel("Filtrar por candidato:");
        lblFiltro.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelFiltro.add(lblFiltro);
        
        // ComboBox para selección de candidatos
        cbxCandidatos = new JComboBox<>();
        cbxCandidatos.addItem(null); // Opción "Todos los candidatos"
        
        for (Candidato candidato : Bolsa.getInstance().getMisCandidatos()) {
            cbxCandidatos.addItem(candidato);
        }
        
        cbxCandidatos.setRenderer(new ListCellRenderer<Candidato>() {
            private final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
            
            @Override
            public Component getListCellRendererComponent(JList<? extends Candidato> list, Candidato value, 
                                                         int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                if (value == null) {
                    renderer.setText("Todos los candidatos");
                } else {
                    renderer.setText(value.getNombre() + " " + value.getApellido() + " - " + value.getCedula());
                }
                renderer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                return renderer;
            }
        });
        
        cbxCandidatos.addActionListener(e -> loadContrataciones());
        
        panelFiltro.add(cbxCandidatos);
        contentPanel.add(panelFiltro, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        
        // CAMBIO REALIZADO: El MouseListener ahora es mucho más eficiente.
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                // Se verifica que la fila seleccionada sea válida y exista en nuestra lista.
                if (row >= 0 && row < contratacionesMostradas.size()) {
                    // Acceso instantáneo al objeto, sin necesidad de buscar en un bucle.
                    selected = contratacionesMostradas.get(row);
                    // System.out.println("Seleccionado: " + selected.getVacanteEmpleada().getIdentificador()); // Descomentar para probar
                }
            }
        });
        
        scrollPane.setViewportView(table);

        String[] headers = {
            "ID Vacante", "RNC Empresa", "ID Postulación", "Cédula Candidato", 
            "Nombre Candidato", "Estatus"
        };
        
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        
        customizeTable();
        loadContrataciones();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBackground(new Color(250, 250, 250));
        buttonPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        customizeButton(btnCerrar);
        buttonPane.add(btnCerrar);
    }

    private void loadContrataciones() {
        model.setRowCount(0);
        contratacionesMostradas.clear(); // CAMBIO REALIZADO: Limpiar también la lista de respaldo.
        
        if (Bolsa.getInstance().getMisContrataciones() == null) {
            return;
        }
        
        Candidato selectedCandidato = (Candidato) cbxCandidatos.getSelectedItem();
        
        for (HistorialMatch hm : Bolsa.getInstance().getMisContrataciones()) {
            try {
                if (hm == null || hm.getVacanteEmpleada() == null || hm.getPostulacionEmpleada() == null) {
                    continue;
                }
                
                if (selectedCandidato != null && 
                    !selectedCandidato.getCedula().equalsIgnoreCase(hm.getPostulacionEmpleada().getCedulaCliente())) {
                    continue;
                }
                
                Vacante vacante = hm.getVacanteEmpleada();
                Postulacion postulacion = hm.getPostulacionEmpleada();
                String cedulaCliente = postulacion.getCedulaCliente();
                
                // CAMBIO REALIZADO: Búsqueda del candidato UNA SOLA VEZ.
                Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(cedulaCliente);
                
                // CAMBIO REALIZADO: Se usa el objeto 'cand' para obtener el nombre y el estatus.
                // Se previene un NullPointerException si el candidato no se encuentra.
                String nombreCompleto = "Candidato no encontrado";
                boolean activa = false;
                
                if (cand != null) {
                    nombreCompleto = cand.getNombre() + " " + cand.getApellido();
                    activa = cand.isStatus();
                }
                
                Object[] fila = new Object[6];
                fila[0] = vacante.getIdentificador();
                fila[1] = vacante.getRncEmpresa();
                fila[2] = postulacion.getIdentificador();
                fila[3] = cedulaCliente;
                fila[4] = nombreCompleto;
                fila[5] = activa ? "Activa" : "Inactiva";
                
                model.addRow(fila);
                contratacionesMostradas.add(hm); // CAMBIO REALIZADO: Se añade el objeto a la lista de respaldo.

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    
    private void customizeTable() {
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <= 3; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 248, 255) : new Color(224, 255, 255));
                }
                return c;
            }
        });
        
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }
    
    private void customizeButton(JButton btn) {
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 100, 150)), 
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(100, 149, 237));
            }
            
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(70, 130, 180));
            }
        });
    }
}