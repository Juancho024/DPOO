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

        String[] headers = {"Identificador", "Cédula Candidato", "Nivel Estudio", "Detalle Estudio", "Años Exp.", "Tipo Contrato", "País", "Ciudad", "Mudanza", "Vehículo", "Licencia", "Salario", "Status"};
        model = new DefaultTableModel();
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
                    // Aquí podrías habilitar/deshabilitar botones de "Modificar" o "Eliminar"
                } else {
                    selectedPostulacion = null; // No hay nada seleccionado
                }
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    ModPostulacion modDialog = new ModPostulacion(selectedPostulacion);
                    modDialog.setVisible(true);
                    // Cuando la ventana de modificación se cierra, refrescamos la tabla
                    loadPostulacionesTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedPostulacion != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta postulación?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Bolsa.getInstance().eliminarPostulacion(selectedPostulacion);
                        JOptionPane.showMessageDialog(null, "Postulación eliminada con éxito.", "Eliminar Postulación", JOptionPane.INFORMATION_MESSAGE);
                        loadPostulacionesTable(); // Refrescar la tabla
                        selectedPostulacion = null; // Limpiar selección
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una postulación para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnEliminar);

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
            fila = new Object[13]; // Ajustar el tamaño del array a 13 (nuevos campos)
            fila[0] = p.getIdentificador();
            fila[1] = p.getCedulaCliente();
            fila[2] = p.getNivelEstudio();
            fila[3] = p.getDetalleNivelEstudio(); // Nuevo campo
            fila[4] = p.getAniosExperiencia();   // Nuevo campo
            fila[5] = p.getTipoContrato();
            fila[6] = p.getPaisResidencia();
            fila[7] = p.getCiudadResidencia();
            fila[8] = p.isMudanza() ? "Sí" : "No";
            fila[9] = p.isDisponibilidadVehiculo() ? "Sí" : "No";
            fila[10] = p.isLicencia() ? "Sí" : "No";
            fila[11] = p.getPretensionSalarial();
            fila[12] = p.isStatus() ? "Activa" : "Inactiva"; // Mostrar el estado
            model.addRow(fila);
        }
    }
}