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
import Logico.Empresa;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo = new DefaultTableModel();
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
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					table = new JTable();
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
					
					String[] header = {"RNC", "Nombre", "Pais", "Sector"};
					modelo.setColumnIdentifiers(header);
					loadEmpresas();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Bolsa.getInstance().buscarEmpresaInVacante(selected.getIdentificador()) == true) {
							JOptionPane.showMessageDialog(null, "La empresa " + selected.getIdentificador() + " todavia tiene una vacante abierta.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esa empresa?", "Información", JOptionPane.WARNING_MESSAGE);
							if(opcion == JOptionPane.OK_OPTION) {
								Bolsa.getInstance().eliminarEmpresa(selected);
								loadEmpresas();
								table.clearSelection();
								btnModificar.setEnabled(false);
								btnEliminar.setEnabled(false);
								btnReporte.setEnabled(false);
								selected = null;
							}
						}
					}
				});
				{
					btnReporte = new JButton("Ver Reporte");
					btnReporte.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ReporteEmpresa repEmp = new ReporteEmpresa(selected);
							repEmp.setModal(true);
							repEmp.setVisible(true);
							table.clearSelection();
							btnModificar.setEnabled(false);
							btnEliminar.setEnabled(false);
							btnReporte.setEnabled(false);
							selected = null;
						}
					});
					btnReporte.setEnabled(false);
					btnReporte.setActionCommand("OK");
					buttonPane.add(btnReporte);
				}
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModEmpresa modEmp = new ModEmpresa(selected);
						modEmp.setModal(true);
						modEmp.setVisible(true);
						table.clearSelection();
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnReporte.setEnabled(false);
						selected = null;
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	public static void loadEmpresas() {
		// TODO Auto-generated method stub
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
