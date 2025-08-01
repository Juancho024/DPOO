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
import javax.swing.table.TableColumnModel;
import Logico.Bolsa;
import Logico.Vacante;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Label;
import java.awt.Font;

public class ListadoVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static Object[] row;
    private static DefaultTableModel modelo = new DefaultTableModel();
    private JButton btnCancelar;
    private static JTable table;
    private JButton btnModificar;
    private JButton btnEliminar;
    private Vacante selected = null;
    private JButton btnReportes;
    private JComboBox cbxEstadoVacante;

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
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "Vacantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            
            cbxEstadoVacante = new JComboBox();
            cbxEstadoVacante.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Activas", "Inactivas"}));
            cbxEstadoVacante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadVacantes();
                }
            });
            cbxEstadoVacante.setBounds(293, 23, 260, 20);
            panel.add(cbxEstadoVacante);
            
            Label label = new Label("Filtrar por estado de vacante:");
            label.setFont(new Font("Tahoma", Font.BOLD, 12));
            label.setBounds(10, 21, 260, 22);
            panel.add(label);
            
            JPanel panel_1 = new JPanel();
            panel_1.setBounds(10, 56, 1155, 523);
            panel.add(panel_1);
            panel_1.setLayout(new BorderLayout(0, 0));
            
            JScrollPane scrollPane = new JScrollPane();
            panel_1.add(scrollPane, BorderLayout.CENTER);
            
            table = new JTable();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = table.getSelectedRow();
                    if(index >= 0) {
                        selected = Bolsa.getInstance().buscarVacanteByCode(table.getValueAt(index, 0).toString());
                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                        btnReportes.setEnabled(true);
                    }
                }
            });
            
            modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            String[] header = {"Identificador", "RNC Empresa", "Nombre Vacante", "Tipo Contrato", 
                               "País Residencia", "Ciudad Residencia", "Pretensión Salarial", 
                               "Mudanza", "Vehículo", "Licencia", "Nivel Estudio", "Estado"};
            modelo.setColumnIdentifiers(header);
            table.setModel(modelo);
            scrollPane.setViewportView(table);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            btnReportes = new JButton("Ver Reporte");
            btnReportes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        ReporteVacante reporte = new ReporteVacante(selected);
                        reporte.setModal(true);
                        reporte.setVisible(true);
                    }
                }
            });
            btnReportes.setEnabled(false);
            buttonPane.add(btnReportes);
            
            btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        // Eliminamos la verificación de postulaciones activas
                        int opcion = JOptionPane.showConfirmDialog(null, 
                                "¿Seguro que desea eliminar esta vacante?", 
                                "Confirmar Eliminación", 
                                JOptionPane.YES_NO_OPTION);
                        
                        if(opcion == JOptionPane.YES_OPTION) {
                            Bolsa.getInstance().eliminarVacante(selected);
                            loadVacantes();
                            btnModificar.setEnabled(false);
                            btnEliminar.setEnabled(false);
                            btnReportes.setEnabled(false);
                            selected = null;
                        }
                    }
                }
            });
            btnEliminar.setEnabled(false);
            buttonPane.add(btnEliminar);
            
            btnModificar = new JButton("Modificar");
            btnModificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selected != null) {
                        ModVacante modVac = new ModVacante(selected);
                        modVac.setModal(true);
                        modVac.setVisible(true);
                        loadVacantes();
                    }
                }
            });
            btnModificar.setEnabled(false);
            buttonPane.add(btnModificar);
            
            btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            btnCancelar.setActionCommand("Cancel");
            buttonPane.add(btnCancelar);
        }
        
        loadVacantes();
    }

    public void loadVacantes() {
        modelo.setRowCount(0);
        row = new Object[modelo.getColumnCount()];
        
        int estadoFilter = cbxEstadoVacante.getSelectedIndex();
        
        for(Vacante aux: Bolsa.getInstance().getMisVacantes()) {
            // Aplicar filtro de estado
            if(estadoFilter == 1 && !aux.isStatus()) continue; // Solo activas
            if(estadoFilter == 2 && aux.isStatus()) continue;  // Solo inactivas
            
            row[0] = aux.getIdentificador();
            row[1] = aux.getRncEmpresa();
            row[2] = aux.getNombreVacante();
            row[3] = aux.getTipoContrato();
            row[4] = aux.getPaisResidencia();
            row[5] = aux.getCiudadResidencia();
            row[6] = aux.getPretensionSalarial();
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
        columnModel.getColumn(0).setPreferredWidth(100);  // Identificador
        columnModel.getColumn(1).setPreferredWidth(100);  // RNC Empresa
        columnModel.getColumn(2).setPreferredWidth(150);  // Nombre Vacante
        columnModel.getColumn(3).setPreferredWidth(120);  // Tipo Contrato
        columnModel.getColumn(4).setPreferredWidth(120);  // País
        columnModel.getColumn(5).setPreferredWidth(120);  // Ciudad
        columnModel.getColumn(6).setPreferredWidth(100);  // Salario
        columnModel.getColumn(7).setPreferredWidth(80);   // Mudanza
        columnModel.getColumn(8).setPreferredWidth(80);   // Vehículo
        columnModel.getColumn(9).setPreferredWidth(80);   // Licencia
        columnModel.getColumn(10).setPreferredWidth(120); // Nivel Estudio
        columnModel.getColumn(11).setPreferredWidth(80);  // Estado
    }
}