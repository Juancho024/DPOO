package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Logico.Bolsa;
import Logico.User;

public class ListadoUser extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnCancelar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JPanel panel;
    private JScrollPane scrollPane;
    private static JTable table;
    private static Object[] row;
    private static DefaultTableModel modelo = new DefaultTableModel();
    private User selected = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoUser dialog = new ListadoUser();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoUser() {
        setTitle("Listado de Usuarios");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
        setBounds(100, 100, 941, 697);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#ecf0f1"));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(10, 10));
        {
            panel = new JPanel();
            panel.setBackground(Color.decode("#ecf0f1"));
            panel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.decode("#6e9fe2")),
                    " Usuarios ",
                    TitledBorder.LEFT,
                    TitledBorder.TOP,
                    new Font("Segoe UI", Font.BOLD, 16),
                    Color.decode("#333333")));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    table = new JTable();
                    table.setRowHeight(25);
                    table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    table.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int index = table.getSelectedRow();
                            if(index >= 0) {
                                selected = Bolsa.getInstance().buscarUserByUser(table.getValueAt(index, 1).toString());
                                btnEliminar.setEnabled(true);
                                btnModificar.setEnabled(true);
                            }
                        }
                    });
                    
                    JTableHeader header = table.getTableHeader();
                    header.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    header.setBackground(Color.decode("#2c3e50"));
                    header.setForeground(Color.white);
                    header.setReorderingAllowed(false);
                    header.setResizingAllowed(true);
                    
                    modelo = new DefaultTableModel(){
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    String[] headerTable = {"Tipo de Usuario", "Nombre de Usuario", "Contraseña"};
                    modelo.setColumnIdentifiers(headerTable);
                    loadUser();
                    scrollPane.setViewportView(table);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPane.setBackground(Color.decode("#ecf0f1"));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
            btnEliminar = createStyledButton("Eliminar", "#e74c3c");
            btnEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(Bolsa.getInstance().validarEliminarUser(selected.getUserName())) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar todos los usuarios.\nDebe permanecer al menos un usuario administrador para garantizar el acceso al sistema.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar ese Usuario?", "Información", JOptionPane.WARNING_MESSAGE);
                        if(opcion == JOptionPane.OK_OPTION) {
                            Bolsa.getInstance().eliminarUser(selected);
                            loadUser();
                            table.clearSelection();
                            btnModificar.setEnabled(false);
                            btnEliminar.setEnabled(false);
                            selected = null;
                        }
                    }
                }
            });
            btnEliminar.setEnabled(false);
            buttonPane.add(btnEliminar);
            
            btnModificar = createStyledButton("Modificar", "#6e9fe2");
            btnModificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ModUser modUser = new ModUser(selected);
                    modUser.setModal(true);
                    modUser.setVisible(true);
                    loadUser();
                    table.clearSelection();
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    selected = null;
                }
            });
            btnModificar.setEnabled(false);
            buttonPane.add(btnModificar);
            getRootPane().setDefaultButton(btnModificar);
            
            btnCancelar = createStyledButton("Cerrar", "#95a5a6");
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPane.add(btnCancelar);
        }
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

    public static void loadUser() {
        modelo.setRowCount(0);
        row = new Object[modelo.getColumnCount()];
        for(User aux: Bolsa.getInstance().getMisUsers()) {
            row[0] = aux.getTipoUser();
            row[1] = aux.getUserName();
            row[2] = aux.getPassword();
            modelo.addRow(row);
        }
        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(392);
        columnModel.getColumn(2).setPreferredWidth(220);
    }
}