package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;

import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PorcentajeMatch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextField txtPorcentaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PorcentajeMatch dialog = new PorcentajeMatch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PorcentajeMatch() {
		setTitle("Registrar el Porcentaje de Matcheo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
		setBounds(100, 100, 404, 197);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 102, 153));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanelRedondeado(60);
			panel.setBackground(SystemColor.activeCaption);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			Label label = new Label("Ingrese el Porcentaje M\u00EDnimo de Match permitido:");
			label.setAlignment(Label.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBounds(42, 22, 294, 22);
			panel.add(label);
			
			txtPorcentaje = new JTextField();
			txtPorcentaje.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					String text = txtPorcentaje.getText();

					if (!Character.isDigit(c) && c != '.' || 
					    (c == '.' && text.contains("."))) {
						e.consume();
					}
				}
			});
			txtPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPorcentaje.setBounds(87, 62, 203, 20);
			panel.add(txtPorcentaje);
			txtPorcentaje.setColumns(10);
			{
				okButton = new JButton("Registrar");
				okButton.setForeground(Color.WHITE);
				okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				okButton.setBackground(SystemColor.textHighlight);
				okButton.setBounds(132, 102, 114, 23);
				panel.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String texto = txtPorcentaje.getText();
							if (texto.isEmpty()) {
								throw new NumberFormatException();
							}
							float porcentaje = Float.parseFloat(texto);
							if (porcentaje <= 0 || porcentaje > 100) {
								JOptionPane.showMessageDialog(null, "Ingrese un valor entre 1 y 100.", "Valor fuera de rango", JOptionPane.WARNING_MESSAGE);
								return;
							}

							Bolsa.setPorcentajeMinMatcheo(porcentaje);
							JOptionPane.showMessageDialog(null, "Registro exitoso.", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Ingrese un número válido para el porcentaje (ej. 50 o 75.5).", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
