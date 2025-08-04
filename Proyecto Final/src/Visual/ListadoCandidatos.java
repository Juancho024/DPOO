package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.Obrero;
import Logico.TecnicoSuperior;
import Logico.Universitario;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;

public class ListadoCandidatos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTable table;
    private static Object[] row;
    private static DefaultTableModel modelo;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JComboBox<String> cbxTiposCandidatos;
    private int selection;
    private Candidato selected = null;
    private JButton btnReportes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoCandidatos dialog = new ListadoCandidatos();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoCandidatos() {
        setTitle("Listado de Candidatos");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
        setBounds(100, 100, 1200, 677);
        setLocationRelativeTo(null);
        setResizable(true); // Se permite el redimensionamiento
        getContentPane().setLayout(new BorderLayout());
        
        // Estilo del contentPanel
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Más espacio
        contentPanel.setBackground(Color.decode("#ecf0f1")); // Fondo gris claro
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(10, 10));

        // Panel de filtro y título
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#bdc3c7")),
                " Candidatos ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                Color.decode("#333333")));
        panel.setBackground(Color.decode("#ecf0f1"));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 10)); // Espacio vertical

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFiltro.setBackground(Color.decode("#bdc3c7"));
        panelFiltro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Label label = new Label("Según el nivel de estudios de los candidatos:");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.decode("#333333"));
        panelFiltro.add(label);

        cbxTiposCandidatos = new JComboBox<>(new String[]{"Todos", "Universitario", "Técnico Superior", "Obrero"});
        cbxTiposCandidatos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbxTiposCandidatos.addActionListener(e -> {
            selection = cbxTiposCandidatos.getSelectedIndex();
            loadHeaderTable();
            loadCandidatos(selection);
        });
        panelFiltro.add(cbxTiposCandidatos);

        panel.add(panelFiltro, BorderLayout.NORTH);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.decode("#ecf0f1"));
        panel.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#bdc3c7"), 1));
        panel_1.add(scrollPane, BorderLayout.CENTER);

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
                int index = table.getSelectedRow();
                if (index >= 0) {
                    selected = Bolsa.getInstance().buscarCandidatoByCod(table.getValueAt(index, 0).toString());
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                    btnReportes.setEnabled(true);
                }
            }
        });

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        loadHeaderTable();
        loadCandidatos(selection);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPane.setBackground(Color.decode("#ecf0f1"));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnReportes = createStyledButton("Ver Reporte", "#3498db");
        btnReportes.addActionListener(e -> {
            if (selected != null) {
                ReporteCandidato repCan = new ReporteCandidato(selected);
                repCan.setModal(true);
                repCan.setLocationRelativeTo(ListadoCandidatos.this);
                repCan.setVisible(true);
                clearSelection();
            }
        });
        btnReportes.setEnabled(false);
        buttonPane.add(btnReportes);

        btnModificar = createStyledButton("Modificar", "#3498db");
        btnModificar.addActionListener(e -> {
            if (selected != null) {
                ModCandidato modCan = new ModCandidato(selected);
                modCan.setModal(true);
                modCan.setLocationRelativeTo(ListadoCandidatos.this);
                modCan.setVisible(true);
                clearSelection();
            }
        });
        btnModificar.setEnabled(false);
        buttonPane.add(btnModificar);
        
        btnEliminar = createStyledButton("Eliminar", "#e74c3c");
        btnEliminar.addActionListener(e -> {
            if (selected != null) {
                if (Bolsa.getInstance().buscarCandidatoInPostulacion(selected.getCedula())) {
                    JOptionPane.showMessageDialog(null, "El candidato " + selected.getCedula() + " todavía tiene una postulación.\nDebe eliminar dicha postulación primero.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este Candidato?", "Información", JOptionPane.WARNING_MESSAGE);
                    if (opcion == JOptionPane.OK_OPTION) {
                        Bolsa.getInstance().eliminarCandidato(selected);
                        loadCandidatos(selection);
                        clearSelection();
                    }
                }
            }
        });
        btnEliminar.setEnabled(false);
        buttonPane.add(btnEliminar);

        btnCancelar = createStyledButton("Cerrar", "#95a5a6");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    // Método auxiliar para limpiar la selección y deshabilitar botones
    private void clearSelection() {
        table.clearSelection();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnReportes.setEnabled(false);
        selected = null;
    }

    // Método auxiliar para crear botones con estilo
    private JButton createStyledButton(String text, String hexColor) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode(hexColor));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        button.setBorderPainted(false);
        return button;
    }

    private void loadHeaderTable() {
        String[] header = null;
        switch (selection) {
            case 0:
                header = new String[]{"Cédula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Teléfono", "Nivel Académico", "Estado"};
                break;
            case 1:
                header = new String[]{"Cédula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Teléfono", "Carrera graduada"};
                break;
            case 2:
                header = new String[]{"Cédula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Teléfono", "Técnico graduado"};
                break;
            case 3:
                header = new String[]{"Cédula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Teléfono", "Habilidades"};
                break;
        }
        modelo.setColumnIdentifiers(header);
        table.setModel(modelo);
    }

    public static void loadCandidatos(int selection) {
        modelo.setRowCount(0);
        row = new Object[modelo.getColumnCount()];

        switch (selection) {
            case 0:
                for (Candidato aux : Bolsa.getInstance().getMisCandidatos()) {
                    row[0] = aux.getCedula();
                    row[1] = aux.getNombre();
                    row[2] = aux.getApellido();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    row[3] = sdf.format(aux.getFechaNacimiento());
                    row[4] = aux.getNacionalidad();
                    row[5] = aux.getSexo();
                    row[6] = aux.getTelefono();
                    if (aux instanceof Universitario) {
                        row[7] = "Universitario";
                    } else if (aux instanceof TecnicoSuperior) {
                        row[7] = "Tecnico Superior";
                    } else if (aux instanceof Obrero) {
                        row[7] = "Obrero";
                    }
                    row[8] = aux.isStatus() ? "Desempleado" : "Empleado";
                    modelo.addRow(row);
                }
                break;
            case 1:
                for (Candidato aux : Bolsa.getInstance().getMisCandidatos()) {
                    if (aux instanceof Universitario) {
                        row[0] = aux.getCedula();
                        row[1] = aux.getNombre();
                        row[2] = aux.getApellido();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        row[3] = sdf.format(aux.getFechaNacimiento());
                        row[4] = aux.getNacionalidad();
                        row[5] = aux.getSexo();
                        row[6] = aux.getTelefono();
                        row[7] = ((Universitario) aux).getCarreraGraduada();
                        modelo.addRow(row);
                    }
                }
                break;
            case 2:
                for (Candidato aux : Bolsa.getInstance().getMisCandidatos()) {
                    if (aux instanceof TecnicoSuperior) {
                        row[0] = aux.getCedula();
                        row[1] = aux.getNombre();
                        row[2] = aux.getApellido();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        row[3] = sdf.format(aux.getFechaNacimiento());
                        row[4] = aux.getNacionalidad();
                        row[5] = aux.getSexo();
                        row[6] = aux.getTelefono();
                        row[7] = ((TecnicoSuperior) aux).getAreaEspecialidad();
                        modelo.addRow(row);
                    }
                }
                break;
            case 3:
                for (Candidato aux : Bolsa.getInstance().getMisCandidatos()) {
                    if (aux instanceof Obrero) {
                        row[0] = aux.getCedula();
                        row[1] = aux.getNombre();
                        row[2] = aux.getApellido();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        row[3] = sdf.format(aux.getFechaNacimiento());
                        row[4] = aux.getNacionalidad();
                        row[5] = aux.getSexo();
                        row[6] = aux.getTelefono();
                        String[] habilidades = ((Obrero) aux).getMisHabilidades();
                        row[7] = String.join(", ", habilidades);
                        modelo.addRow(row);
                    }
                }
                break;
        }
        
        // Ajuste de columnas después de cargar los datos
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = table.getColumnModel();
        switch (selection) {
            case 0:
                columnModel.getColumn(0).setPreferredWidth(100);
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(2).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                columnModel.getColumn(4).setPreferredWidth(150);
                columnModel.getColumn(5).setPreferredWidth(50);
                columnModel.getColumn(6).setPreferredWidth(100);
                columnModel.getColumn(7).setPreferredWidth(151);
                columnModel.getColumn(8).setPreferredWidth(151);
                break;
            case 1:
            case 2:
                columnModel.getColumn(0).setPreferredWidth(100);
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(2).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                columnModel.getColumn(4).setPreferredWidth(150);
                columnModel.getColumn(5).setPreferredWidth(50);
                columnModel.getColumn(6).setPreferredWidth(100);
                columnModel.getColumn(7).setPreferredWidth(302);
                break;
            case 3:
                columnModel.getColumn(0).setPreferredWidth(100);
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(2).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                columnModel.getColumn(4).setPreferredWidth(150);
                columnModel.getColumn(5).setPreferredWidth(50);
                columnModel.getColumn(6).setPreferredWidth(100);
                columnModel.getColumn(7).setPreferredWidth(550);
                break;
        }
    }
}