package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Bolsa;
import Logico.Postulacion;

public class ListadoPostulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Object[] fila;
    private Postulacion selectedPostulacion; // Para almacenar la postulación seleccionada
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVerReporte; // Nuevo botón para el reporte

    public ListadoPostulacion() {
        setTitle("Listado de Postulaciones");
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setModal(true); // Asegura que la ventana sea modal
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        // SOLO se muestran las columnas solicitadas
        String[] headers = {"Identificador", "Tipo de Contrato", "País Residencia", "Pretensión Salarial", "Nivel de Estudio"};
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int intColumn) {
                // Nunca permitir edición de celdas
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        loadPostulacionesTable(); // Cargar datos iniciales

        // Listener para seleccionar una fila en la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String id = (String) table.getValueAt(row, 0); // Obtener el ID de la primera columna
                    selectedPostulacion = Bolsa.getInstance().buscarPostulacionById(id);
                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnVerReporte.setEnabled(true); // Habilitar el botón de reporte
                } else {
                    selectedPostulacion = null; // No hay nada seleccionado
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnVerReporte.setEnabled(false); // Deshabilitar el botón de reporte
                }
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false); // Deshabilitado inicialmente
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    ModPostulacion modDialog = new ModPostulacion(selectedPostulacion);
                    modDialog.setVisible(true);
                    // Cuando la ventana de modificación se cierra, refrescamos la tabla
                    loadPostulacionesTable();
                    // Después de modificar y refrescar, limpiar la selección y deshabilitar botones
                    table.clearSelection();
                    selectedPostulacion = null;
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnVerReporte.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false); // Deshabilitado inicialmente
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta postulación?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Bolsa.getInstance().eliminarPostulacion(selectedPostulacion);
                        JOptionPane.showMessageDialog(null, "Postulación eliminada con éxito.", "Eliminar Postulación", JOptionPane.INFORMATION_MESSAGE);
                        loadPostulacionesTable(); // Refrescar la tabla
                        selectedPostulacion = null; // Limpiar selección
                        table.clearSelection();
                        btnModificar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnVerReporte.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnEliminar);

        // Nuevo botón "Ver Reporte"
        btnVerReporte = new JButton("Ver Reporte");
        btnVerReporte.setEnabled(false); // Deshabilitado inicialmente
        btnVerReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    ReportePostulacion repPost = new ReportePostulacion(selectedPostulacion);
                    repPost.setModal(true);
                    repPost.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para ver su reporte.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnVerReporte);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana
            }
        });
        buttonPane.add(btnCancelar);
    }

    private void loadPostulacionesTable() {
        model.setRowCount(0); // Limpiar todas las filas existentes

        for (Postulacion p : Bolsa.getInstance().getMisPostulaciones()) {
            fila = new Object[5]; // Ahora son solo 5 columnas

            fila[0] = p.getIdentificador();
            fila[1] = p.getTipoContrato();
            fila[2] = p.getPaisResidencia();
            fila[3] = String.format("%,.2f", p.getPretensionSalarial()); // Formato de moneda
            fila[4] = p.getNivelEstudio();
            
            model.addRow(fila);
        }
    }
}