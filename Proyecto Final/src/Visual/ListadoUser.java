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
import Logico.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 941, 677);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
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
					modelo = new DefaultTableModel(){
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                // Nunca permitir edición
			                return false;
			            }
			        };
					String[] header = {"Tipo de Usuario", "Nombre de Usuario", "Contraseña"};
					modelo.setColumnIdentifiers(header);
					loadUser();
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
						if(Bolsa.getInstance().validarEliminarUser(selected.getUserName()) == true) {
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
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
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

	public static void loadUser() {
		// TODO Auto-generated method stub
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
