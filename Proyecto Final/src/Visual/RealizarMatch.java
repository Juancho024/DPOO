package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Vacante;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RealizarMatch extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarMatch dialog = new RealizarMatch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarMatch() {
		setTitle("Realizar Match");
		setBounds(100, 100, 1200, 677);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Nombre de la vacante y RNC ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		for(Vacante aux: Bolsa.getInstance().getMisVacantes()) {
			JPanel panel_Info = new JPanel();
			panel_Info.setBorder(null);
			panel_Info.setBounds(10, 21, 1154, 596);
			
			Label lbInfo = new Label(aux.getNombreVacante() + "-" + aux.getIdentificador());
			lbInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			Button btnConsultar = new Button("Consultar");
			btnConsultar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 12));
            
            panel_Info.add(lbInfo, BorderLayout.CENTER);
            panel_Info.add(btnConsultar, BorderLayout.EAST);
            panel.add(panel_Info);
			
		}
	}
}
