package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Logico.Bolsa;
import Logico.Postulacion;

public class ListadoPostulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Object[] fila;
    private Postulacion selectedPostulacion;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVerReporte;
    private JButton btnCerrar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoPostulacion dialog = new ListadoPostulacion();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListadoPostulacion() {
        setTitle("Listado de Postulaciones");
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setResizable(true);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#ecf0f1"));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#bdc3c7")),
                " Postulaciones ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                Color.decode("#333333")));
        panel.setBackground(Color.decode("#ecf0f1"));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#bdc3c7"), 1));
        panel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.white);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedPostulacion = Bolsa.getInstance().buscarPostulacionByCode(table.getValueAt(row, 0).toString());
                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnVerReporte.setEnabled(true);
                } else {
                    clearSelection();
                }
            }
        });
        
        String[] headers = {"Identificador", "Tipo de Contrato", "País Residencia", "Pretensión Salarial", "Nivel de Estudio"};
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int intColumn) {
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        loadPostulacionesTable();
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPane.setBackground(Color.decode("#ecf0f1"));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = createStyledButton("Modificar", "#3498db");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    ModPostulacion modDialog = new ModPostulacion(selectedPostulacion);
                    modDialog.setVisible(true);
                    loadPostulacionesTable();
                    clearSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = createStyledButton("Eliminar", "#e74c3c");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta postulación?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Bolsa.getInstance().eliminarPostulacion(selectedPostulacion);
                        JOptionPane.showMessageDialog(null, "Postulación eliminada con éxito.", "Eliminar Postulación", JOptionPane.INFORMATION_MESSAGE);
                        loadPostulacionesTable();
                        clearSelection();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnEliminar);

        btnVerReporte = createStyledButton("Ver Reporte", "#3498db");
        btnVerReporte.setEnabled(false);
        btnVerReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    ReportePostulacion repPost = new ReportePostulacion(selectedPostulacion);
                    repPost.setModal(true);
                    repPost.setVisible(true);
                    clearSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para ver su reporte.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnVerReporte);

        btnCerrar = createStyledButton("Cerrar", "#95a5a6");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCerrar);
    }

    private void clearSelection() {
        table.clearSelection();
        selectedPostulacion = null;
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnVerReporte.setEnabled(false);
    }
    
    private JButton createStyledButton(String text, String hexColor) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode(hexColor));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setBorderPainted(false);
        return button;
    }

    private void loadPostulacionesTable() {
        model.setRowCount(0);

        for (Postulacion p : Bolsa.getInstance().getMisPostulaciones()) {
            fila = new Object[5];

            fila[0] = p.getIdentificador();
            fila[1] = p.getTipoContrato();
            fila[2] = p.getPaisResidencia();
            fila[3] = String.format("%,.2f", p.getPretensionSalarial());
            fila[4] = p.getNivelEstudio();
            
            model.addRow(fila);
        }
    }
}