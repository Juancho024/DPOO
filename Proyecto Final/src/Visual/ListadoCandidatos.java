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
import Logico.Candidato;
import Logico.Obrero;
import Logico.TecnicoSuperior;
import Logico.Universitario;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoCandidatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JComboBox cbxTiposCandidatos;
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
		setBounds(100, 100, 1200, 677);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Candidatos ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				cbxTiposCandidatos = new JComboBox();
				cbxTiposCandidatos.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Universitario", "T\u00E9cnico Superior ", "Obrero"}));
				cbxTiposCandidatos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selection = cbxTiposCandidatos.getSelectedIndex();
						loadCandidatos(selection);
						loadHeaderTable();
					}

				});
				cbxTiposCandidatos.setBounds(293, 23, 260, 20);
				panel.add(cbxTiposCandidatos);
			}
			{
				Label label = new Label("Seg\u00FAn el nivel de estudios de los candidatos:");
				label.setFont(new Font("Tahoma", Font.BOLD, 12));
				label.setBounds(10, 21, 260, 22);
				panel.add(label);
			}

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
						selected = Bolsa.getInstance().buscarCandidatoByCod(table.getValueAt(index, 0).toString());
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						btnReportes.setEnabled(true);
					}
				}
			});
			
			
			modelo = new DefaultTableModel(){
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                // Nunca permitir edici�n
	                return false;
	            }
	        };
			loadHeaderTable();
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Bolsa.getInstance().buscarCandidatoInPostulacion(selected.getCedula()) == true) {
						JOptionPane.showMessageDialog(null, "El candidato " + selected.getCedula()+ " todavia tiene una postulaci�n abierta.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						int opcion = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar ese Candidato?", "Informaci�n", JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION) {
							Bolsa.getInstance().eliminarCandidato(selected);
							loadCandidatos(0);
							table.clearSelection();
							btnModificar.setEnabled(false);
							btnEliminar.setEnabled(false);
							btnReportes.setEnabled(true);
							selected = null;
						}
					}
				}
			});
			
			btnReportes = new JButton("Ver Reporte");
			btnReportes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReporteCandidato repCan = new ReporteCandidato(selected);
					repCan.setModal(true);
					repCan.setVisible(true);
					table.clearSelection();
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnReportes.setEnabled(false);
					selected = null;
				}
			});
			btnReportes.setEnabled(false);
			btnReportes.setActionCommand("OK");
			buttonPane.add(btnReportes);
			btnEliminar.setEnabled(false);
			btnEliminar.setActionCommand("OK");
			buttonPane.add(btnEliminar);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModCandidato modCan = new ModCandidato(selected);
						modCan.setModal(true);
						modCan.setVisible(true);
						table.clearSelection();
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnReportes.setEnabled(false);
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
	private void loadHeaderTable() {
		// TODO Auto-generated method stub
		String [] header = null;
		switch(selection) {
		case 0: 
			header  = new String[] {"Cedula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Telefono", "Nivel Acad�mico"};
			break;
		case 1:
			header  = new String[] {"Cedula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Telefono", "Carrera graduada"};
			break;
		case 2: 
			header  = new String[] {"Cedula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Telefono", "Tecnico graduado"};
			break;
		case 3:
			header  = new String[] {"Cedula", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Sexo", "Telefono", "Habilidades"};
			break;
		}
		modelo.setColumnIdentifiers(header);
		loadCandidatos(selection);
	}

	public static void loadCandidatos(int selection) {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		row = new Object[modelo.getColumnCount()];
		switch(selection) {
		case 0: 
			for(Candidato aux: Bolsa.getInstance().getMisCandidatos()) {
				row [0] = aux.getCedula();
				row [1] = aux.getNombre();
				row [2] = aux.getApellido();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				row [3] = sdf.format(aux.getFechaNacimiento());
				row [4] = aux.getNacionalidad();
				row [5] = aux.getSexo();
				row [6] = aux.getTelefono();
				if(aux instanceof Universitario) {
					row [7] = "Universitario";
				}
				if(aux instanceof TecnicoSuperior) {
					row [7] = "Tecnico Superior";
				}
				if(aux instanceof Obrero) {
					row [7] = "Obrero";
				}
				modelo.addRow(row);
				table.setModel(modelo);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumnModel columnModel = table.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(100);
				columnModel.getColumn(1).setPreferredWidth(150);
				columnModel.getColumn(2).setPreferredWidth(150);
				columnModel.getColumn(3).setPreferredWidth(150);
				columnModel.getColumn(4).setPreferredWidth(150);
				columnModel.getColumn(5).setPreferredWidth(50);
				columnModel.getColumn(6).setPreferredWidth(100);
				columnModel.getColumn(7).setPreferredWidth(302);
			}
			break;
		case 1:
			for(Candidato aux: Bolsa.getInstance().getMisCandidatos()) {
				if(aux instanceof Universitario) {
					row [0] = aux.getCedula();
					row [1] = aux.getNombre();
					row [2] = aux.getApellido();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					row [3] = sdf.format(aux.getFechaNacimiento());
					row [4] = aux.getNacionalidad();
					row [5] = aux.getSexo();
					row [6] = aux.getTelefono();
					row [7] = ((Universitario) aux).getCarreraGraduada();
					modelo.addRow(row);
					table.setModel(modelo);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					TableColumnModel columnModel = table.getColumnModel();
					columnModel.getColumn(0).setPreferredWidth(100);
					columnModel.getColumn(1).setPreferredWidth(150);
					columnModel.getColumn(2).setPreferredWidth(150);
					columnModel.getColumn(3).setPreferredWidth(150);
					columnModel.getColumn(4).setPreferredWidth(150);
					columnModel.getColumn(5).setPreferredWidth(50);
					columnModel.getColumn(6).setPreferredWidth(100);
					columnModel.getColumn(7).setPreferredWidth(302);
				}
			}
			break;
		case 2:
			for(Candidato aux: Bolsa.getInstance().getMisCandidatos()) {
				if(aux instanceof TecnicoSuperior) {
					row [0] = aux.getCedula();
					row [1] = aux.getNombre();
					row [2] = aux.getApellido();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					row [3] = sdf.format(aux.getFechaNacimiento());
					row [4] = aux.getNacionalidad();
					row [5] = aux.getSexo();
					row [6] = aux.getTelefono();
					row [7] = ((TecnicoSuperior) aux).getAreaEspecialidad();
					modelo.addRow(row);
					table.setModel(modelo);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					TableColumnModel columnModel = table.getColumnModel();
					columnModel.getColumn(0).setPreferredWidth(100);
					columnModel.getColumn(1).setPreferredWidth(150);
					columnModel.getColumn(2).setPreferredWidth(150);
					columnModel.getColumn(3).setPreferredWidth(150);
					columnModel.getColumn(4).setPreferredWidth(150);
					columnModel.getColumn(5).setPreferredWidth(50);
					columnModel.getColumn(6).setPreferredWidth(100);
					columnModel.getColumn(7).setPreferredWidth(302);
				}
			}
			break;
		case 3: 
			for(Candidato aux: Bolsa.getInstance().getMisCandidatos()) {
				if(aux instanceof Obrero) {
					row [0] = aux.getCedula();
					row [1] = aux.getNombre();
					row [2] = aux.getApellido();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					row [3] = sdf.format(aux.getFechaNacimiento());
					row [4] = aux.getNacionalidad();
					row [5] = aux.getSexo();
					row [6] = aux.getTelefono();
					String[] habilidades = ((Obrero) aux).getMisHabilidades();
			        row[7] = String.join(", ", habilidades);
					modelo.addRow(row);
					table.setModel(modelo);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					TableColumnModel columnModel = table.getColumnModel();
					columnModel.getColumn(0).setPreferredWidth(100);
					columnModel.getColumn(1).setPreferredWidth(150);
					columnModel.getColumn(2).setPreferredWidth(150);
					columnModel.getColumn(3).setPreferredWidth(150);
					columnModel.getColumn(4).setPreferredWidth(150);
					columnModel.getColumn(5).setPreferredWidth(50);
					columnModel.getColumn(6).setPreferredWidth(100);
					columnModel.getColumn(7).setPreferredWidth(550);
				}
			}
			break;
		}
	}
}
