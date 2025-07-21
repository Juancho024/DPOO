package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Logico.Bolsa;
import Logico.Postulacion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoPostulacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTable table;
	private static Object[] row;
	private DefaultTableModel modelo = new DefaultTableModel();
	private Postulacion selected = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPostulacion dialog = new ListadoPostulacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPostulacion() {
		setTitle("Listado de Postulaci\u00F3n");
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
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					if(index >= 0) {
						selected = Bolsa.getInstance().buscarPostulacionByCode(table.getValueAt(index, 0).toString());
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
			String[] header = {"Cedula del Candidato", "Tipo de Contrato", "Pais de Residencia", "Cuidad de Residencia",
					"Pretensión Salarial", "Dispuesto a Mudarse", "Disponibilidad de Licencia", "Disponibilidad de Vehículo"};
			modelo.setColumnIdentifiers(header);
			loadPostulacion();
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("Modificar");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	private void loadPostulacion() {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		row = new Object[modelo.getColumnCount()];
		for(Postulacion aux: Bolsa.getInstance().getMisPostulaciones()) {
			row[0] = aux.getIdentificador();
			row[1] = aux.getTipoContrato();
			row[2] = aux.getPaisResidencia();
			row[3] = aux.getCiudadResidencia();
			row[4] = aux.getPretensionSalarial();
			if(aux.isMudanza() == true) {
				row[5] = "SI";
			} else {
				row[5] = "NO";
			}
			if(aux.isLicencia() == true) {
				row[6] = "SI";
			} else {
				row[6] = "NO";
			}
			if(aux.isDisponibilidadVehiculo() == true) {
				row[7] = "SI";
			} else {
				row[7] = "NO";
			}
			modelo.addRow(row);
		}
		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(140);
		columnModel.getColumn(1).setPreferredWidth(130);
		columnModel.getColumn(2).setPreferredWidth(130);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(140);	
		columnModel.getColumn(5).setPreferredWidth(140);	
		columnModel.getColumn(6).setPreferredWidth(170);	
		columnModel.getColumn(7).setPreferredWidth(170);	
	}
}
