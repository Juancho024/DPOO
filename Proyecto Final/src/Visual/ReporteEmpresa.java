package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Logico.Bolsa;
import Logico.Empresa;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class ReporteEmpresa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtNombre;
    private JLabel lbLogo;
    private byte[] imagenActual;
    private Label label_4;
    private JTextField txtPais;
    private JTextField txtSector;

    public static void main(String[] args) {
        try {
            ReporteEmpresa dialog = new ReporteEmpresa(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReporteEmpresa(Empresa selected) {
        setTitle("Reporte de Empresa");
        setBounds(100, 100, 450, 391);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Empresa", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        contentPanel.add(panel);
        panel.setLayout(null);

        Label label = new Label("RNC de la Empresa:");
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(21, 185, 138, 22);
        panel.add(label);
        lbLogo = new JLabel("Sin imagen", SwingConstants.CENTER);
        lbLogo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lbLogo.setBounds(131, 21, 179, 123);
        byte[] imgBytes = selected.getImagen();
		if (imgBytes != null) {
		    ImageIcon icono = new ImageIcon(imgBytes);
		    Image imagenEscalada = icono.getImage().getScaledInstance(166, 116, Image.SCALE_SMOOTH);
		    lbLogo.setIcon(new ImageIcon(imagenEscalada));
		    lbLogo.setText("");
		} else {
			lbLogo.setIcon(null);
			lbLogo.setText("Sin imagen");
		}
        panel.add(lbLogo);


        txtIdentificador = new JTextField();
        txtIdentificador.setEditable(false);
        txtIdentificador.setEnabled(false);
        txtIdentificador.setText(selected.getIdentificador());
        txtIdentificador.setBounds(178, 185, 233, 22);
        panel.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        Label label_1 = new Label("Nombre:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_1.setBounds(21, 225, 100, 22);
        panel.add(label_1);

        txtNombre = new JTextField();
        txtNombre.setEnabled(false);
        txtNombre.setEditable(false);
        txtNombre.setColumns(10);
        txtNombre.setText(selected.getNombre());
        txtNombre.setBounds(131, 225, 280, 22);
        panel.add(txtNombre);

        Label label_2 = new Label("País:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_2.setBounds(21, 265, 100, 22);
        panel.add(label_2);

        Label label_3 = new Label("Sector:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_3.setBounds(21, 305, 100, 22);
        panel.add(label_3);
        
        label_4 = new Label("Logo de la Empresa");
        label_4.setAlignment(Label.CENTER);
        label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_4.setBounds(154, 150, 138, 22);
        panel.add(label_4);
        
        txtPais = new JTextField();
        txtPais.setEnabled(false);
        txtPais.setEditable(false);
        txtPais.setColumns(10);
        txtPais.setText(selected.getPais());
        txtPais.setBounds(131, 265, 280, 22);
        panel.add(txtPais);
        
        txtSector = new JTextField();
        txtSector.setEnabled(false);
        txtSector.setEditable(false);
        txtSector.setColumns(10);
        txtSector.setText(selected.getSector());
        txtSector.setBounds(131, 305, 280, 22);
        panel.add(txtSector);
        
        JTextField[] campos = { txtNombre, txtSector, txtPais, txtIdentificador};
		for (JTextField campo : campos) {
		    if(campo != null) {
		    	campo.setEditable(false);
			    campo.setBackground(Color.WHITE);
			    campo.setForeground(Color.BLACK);
			    campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			    campo.setDisabledTextColor(Color.BLACK); 
		    }
		}
    }
}