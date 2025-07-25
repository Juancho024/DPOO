package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Logico.Bolsa;
import Logico.Vacante;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoVacante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Object[] row;
	private static DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnCancelar;
	private static JTable table;
	private JButton btnModificar;
	private JButton btnEliminar;
	private Vacante selected = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVacante dialog = new ListadoVacante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoVacante() {
		setTitle("Listado de Vacantes");
		setBounds(100, 100, 941, 677);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								selected = Bolsa.getInstance().buscarVacanteByCode(table.getValueAt(index, 0).toString());
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
							}
						}
					});
					String[] header = {"RNC de la Empresa", "Nombre de la Vacante", "Tipo de Contrato", "Pais de Residencia", "Cuidad de Residencia",
							"Pretensión Salarial", "Dispuesto a Mudarse", "Disponibilidad de Licencia", "Disponibilidad de Vehículo", "Nivel de Estudio"};
					modelo.setColumnIdentifiers(header);
					loadVacantes();
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

	public static void loadVacantes() {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		row = new Object[modelo.getColumnCount()];
		for(Vacante aux: Bolsa.getInstance().getMisVacantes()) {
			row[0] = aux.getRncEmpresa();
			row[1] = aux.getNombreVacante();
			row[2] = aux.getTipoContrato();
			row[3] = aux.getPaisResidencia();
			row[4] = aux.getCiudadResidencia();
			row[5] = aux.getPretensionSalarial();
			if(aux.isMudanza() == true) {
				row[6] = "SI";
			} else {
				row[6] = "NO";
			}
			if(aux.isLicencia() == true) {
				row[7] = "SI";
			} else {
				row[7] = "NO";
			}
			if(aux.isDisponibilidadVehiculo() == true) {
				row[8] = "SI";
			} else {
				row[8] = "NO";
			}
			modelo.addRow(row);
		}
		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(130);
		columnModel.getColumn(1).setPreferredWidth(140);
		columnModel.getColumn(2).setPreferredWidth(130);
		columnModel.getColumn(3).setPreferredWidth(130);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(140);	
		columnModel.getColumn(6).setPreferredWidth(140);	
		columnModel.getColumn(7).setPreferredWidth(170);	
		columnModel.getColumn(8).setPreferredWidth(170);
		columnModel.getColumn(9).setPreferredWidth(130);
	}

}
