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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Logico.Bolsa;
import Logico.Empresa;

public class ListadoEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private Empresa selected = null;
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEmpresa dialog = new ListadoEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEmpresa() {
		setTitle("Listados de Empresas");
		setBounds(100, 100, 941, 677);
		setLocationRelativeTo(null);
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Más espacio
		contentPanel.setBackground(Color.decode("#ecf0f1")); // Fondo gris claro
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(10, 10));
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#bdc3c7")),
                " Empresas ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                Color.decode("#333333")));
        panel.setBackground(Color.decode("#ecf0f1"));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#bdc3c7"), 1));
			panel.add(scrollPane);
			{
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
							selected = Bolsa.getInstance().buscarEmpresaByCod(table.getValueAt(index, 0).toString());
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
							btnReporte.setEnabled(true);
							
						}
					}
				});
				modelo = new DefaultTableModel(){
		            @Override
		            public boolean isCellEditable(int row, int column) {
		                // Nunca permitir edición
		                return false;
		            }
		        };
				
				String[] headerTable = {"RNC", "Nombre", "Pais", "Sector"};
				modelo.setColumnIdentifiers(headerTable);
				table.setModel(modelo);
				loadEmpresas();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			buttonPane.setBackground(Color.decode("#ecf0f1"));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnReporte = createStyledButton("Ver Reporte", "#3498db");
				btnReporte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ReporteEmpresa repEmp = new ReporteEmpresa(selected);
						repEmp.setModal(true);
						repEmp.setVisible(true);
						clearSelection();
					}
				});
				btnReporte.setEnabled(false);
				buttonPane.add(btnReporte);
			}
			{
				btnModificar = createStyledButton("Modificar", "#3498db");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModEmpresa modEmp = new ModEmpresa(selected);
						modEmp.setModal(true);
						modEmp.setVisible(true);
						clearSelection();
					}
				});
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = createStyledButton("Eliminar", "#e74c3c");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Bolsa.getInstance().buscarEmpresaInVacante(selected.getIdentificador())) {
							JOptionPane.showMessageDialog(null, "La empresa " + selected.getIdentificador() + " todavia tiene una vacante.\nDebe eliminar dicha vacante para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esa empresa?", "Información", JOptionPane.WARNING_MESSAGE);
							if(opcion == JOptionPane.OK_OPTION) {
								Bolsa.getInstance().eliminarEmpresa(selected);
								loadEmpresas();
								clearSelection();
							}
						}
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnCancelar = createStyledButton("Cerrar", "#95a5a6");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void clearSelection() {
	    table.clearSelection();
	    btnModificar.setEnabled(false);
	    btnEliminar.setEnabled(false);
	    btnReporte.setEnabled(false);
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

	public static void loadEmpresas() {
		modelo.setRowCount(0);
		row = new Object[modelo.getColumnCount()];
		for(Empresa aux: Bolsa.getInstance().getMisEmpresas()) {
			row [0] = aux.getIdentificador();
			row [1] = aux.getNombre();
			row [2] = aux.getPais();
			row [3] = aux.getSector();
			modelo.addRow(row);
		}
		
		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(250);
		columnModel.getColumn(3).setPreferredWidth(218);	
	}
}