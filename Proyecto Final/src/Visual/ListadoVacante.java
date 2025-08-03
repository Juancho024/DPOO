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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import Logico.Bolsa;
import Logico.Vacante;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ListadoVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static Object[] row;
    private static DefaultTableModel modelo = new DefaultTableModel();
    private JButton btnCerrar;
    private static JTable table;
    private JButton btnModificar;
    private JButton btnEliminar;
    private Vacante selected = null;
    private JButton btnReportes;
    private JComboBox<String> cbxEstadoVacante;

    public static void main(String[] args) {
        try {
            ListadoVacante dialog = new ListadoVacante();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListadoVacante() {
        setTitle("Listado de Vacantes");
        setBounds(100, 100, 1200, 677);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#ecf0f1"));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(10, 10));
        {
            JPanel panel = new JPanel();
            panel.setBackground(Color.decode("#ecf0f1"));
            panel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.decode("#bdc3c7")),
                    " Vacantes ",
                    TitledBorder.LEFT,
                    TitledBorder.TOP,
                    new Font("Segoe UI", Font.BOLD, 16),
                    Color.decode("#333333")));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 10));

            // Panel de filtros
            JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelFiltro.setBackground(Color.decode("#ecf0f1"));
            panelFiltro.add(new JLabel("Filtrar por estado de vacante:"));

            cbxEstadoVacante = new JComboBox<>();
            DefaultComboBoxModel<String> modelCbx = new DefaultComboBoxModel<>();
            modelCbx.addElement("Todas");
            modelCbx.addElement("Activas");
            modelCbx.addElement("Inactivas");
            cbxEstadoVacante.setModel(modelCbx);
            cbxEstadoVacante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadVacantes();
                }
            });
            panelFiltro.add(cbxEstadoVacante);
            panel.add(panelFiltro, BorderLayout.NORTH);
            
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
                    int index = table.getSelectedRow();
                    if(index >= 0) {
                        selected = Bolsa.getInstance().buscarVacanteByCode(table.getValueAt(index, 0).toString());
                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                        btnReportes.setEnabled(true);
                    } else {
                        clearSelection();
                    }
                }
            });
            
            modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            String[] headerTable = {"Identificador", "RNC Empresa", "Nombre Vacante", "Tipo Contrato", 
                               "País Residencia", "Ciudad Residencia", "Pretensión Salarial", 
                               "Mudanza", "Vehículo", "Licencia", "Nivel Estudio", "Estado"};
            modelo.setColumnIdentifiers(headerTable);
            table.setModel(modelo);
            scrollPane.setViewportView(table);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPane.setBackground(Color.decode("#ecf0f1"));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            btnReportes = createStyledButton("Ver Reporte", "#3498db");
            btnReportes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        ReporteVacante reporte = new ReporteVacante(selected);
                        reporte.setModal(true);
                        reporte.setVisible(true);
                        clearSelection();
                    }
                }
            });
            btnReportes.setEnabled(false);
            buttonPane.add(btnReportes);
            
            btnModificar = createStyledButton("Modificar", "#3498db");
            btnModificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        ModVacante modVac = new ModVacante(selected);
                        modVac.setModal(true);
                        modVac.setVisible(true);
                        loadVacantes();
                        clearSelection();
                    }
                }
            });
            btnModificar.setEnabled(false);
            buttonPane.add(btnModificar);
            
            btnEliminar = createStyledButton("Eliminar", "#e74c3c");
            btnEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        int opcion = JOptionPane.showConfirmDialog(null, 
                                "¿Seguro que desea eliminar esta vacante?", 
                                "Confirmar Eliminación", 
                                JOptionPane.YES_NO_OPTION);
                        
                        if(opcion == JOptionPane.YES_OPTION) {
                            Bolsa.getInstance().eliminarVacante(selected);
                            loadVacantes();
                            clearSelection();
                        }
                    }
                }
            });
            btnEliminar.setEnabled(false);
            buttonPane.add(btnEliminar);
            
            btnCerrar = createStyledButton("Cerrar", "#95a5a6");
            btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            btnCerrar.setActionCommand("Cancel");
            buttonPane.add(btnCerrar);
        }
        
        loadVacantes();
    }
    
    private void clearSelection() {
        table.clearSelection();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnReportes.setEnabled(false);
        selected = null;
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

    public void loadVacantes() {
        modelo.setRowCount(0);
        row = new Object[modelo.getColumnCount()];
        
        int estadoFilter = cbxEstadoVacante.getSelectedIndex();
        
        for(Vacante aux: Bolsa.getInstance().getMisVacantes()) {
            if(estadoFilter == 1 && !aux.isStatus()) continue;
            if(estadoFilter == 2 && aux.isStatus()) continue;
            
            row[0] = aux.getIdentificador();
            row[1] = aux.getRncEmpresa();
            row[2] = aux.getNombreVacante();
            row[3] = aux.getTipoContrato();
            row[4] = aux.getPaisResidencia();
            row[5] = aux.getCiudadResidencia();
            row[6] = String.format("%,.2f", aux.getPretensionSalarial());
            row[7] = aux.isMudanza() ? "Sí" : "No";
            row[8] = aux.isDisponibilidadVehiculo() ? "Sí" : "No";
            row[9] = aux.isLicencia() ? "Sí" : "No";
            row[10] = aux.getNivelEstudio();
            row[11] = aux.isStatus() ? "Activa" : "Inactiva";
            
            modelo.addRow(row);
        }
        
        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(120);
        columnModel.getColumn(5).setPreferredWidth(120);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        columnModel.getColumn(9).setPreferredWidth(80);
        columnModel.getColumn(10).setPreferredWidth(120);
        columnModel.getColumn(11).setPreferredWidth(80);
    }
}