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

public class ModEmpresa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtNombre;
    private JComboBox cbxPais;
    private JComboBox cbxSector;
    private JButton btnCancelar;
    private JButton btnRegistrar;
    private JLabel lbLogo;
    private JButton btnLogo;
    private byte[] imagenActual;

    public static void main(String[] args) {
        try {
            ModEmpresa dialog = new ModEmpresa(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModEmpresa(Empresa selected) {
        setTitle("Modificar Empresa");
        setBounds(100, 100, 450, 414);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
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

        btnLogo = new JButton("Insertar Logo");
        btnLogo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (.png, .jpg, .jpeg)", "png", "jpg", "jpeg");
		        chooser.setFileFilter(filtro);

		        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            File archivo = chooser.getSelectedFile();
		            String nombreArchivo = archivo.getName().toLowerCase();

		            if (!(nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg"))) {
		                JOptionPane.showMessageDialog(null, "Solo se permiten imágenes JPG o PNG", "Formato de Imagen no Valido", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            try (FileInputStream fis = new FileInputStream(archivo);
		                 ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

		                byte[] buffer = new byte[1024];
		                int bytesRead;
		                while ((bytesRead = fis.read(buffer)) != -1) {
		                    bos.write(buffer, 0, bytesRead);
		                }

		                imagenActual = bos.toByteArray();

		                ImageIcon iconoOriginal = new ImageIcon(imagenActual);
		                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(166, 116, Image.SCALE_SMOOTH);
		                lbLogo.setIcon(new ImageIcon(imagenEscalada));
		                lbLogo.setText("");
		              

		            } catch (IOException ex) {
		                JOptionPane.showMessageDialog(null, "Error al cargar la imagen");
		            }
		        }
        	}
        });
        btnLogo.setBounds(159, 144, 123, 22);
        panel.add(btnLogo);


        txtIdentificador = new JTextField();
        txtIdentificador.setEditable(false);
        txtIdentificador.setEnabled(false);
        txtIdentificador.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char letras = e.getKeyChar();
        		String text = txtIdentificador.getText();
        		if(text.length() >= 9) {
        			e.consume();
        		}
        		if(!Character.isDigit(letras)) {
					e.consume();
				}
        	}
        });
        txtIdentificador.setText(selected.getIdentificador());
        txtIdentificador.setBounds(178, 185, 233, 22);
        panel.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        Label label_1 = new Label("Nombre:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_1.setBounds(21, 225, 100, 22);
        panel.add(label_1);

        txtNombre = new JTextField();
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
        
        cbxPais = new JComboBox();
        cbxPais.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per\u00FA", "M\u00E9xico", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam\u00E1", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep\u00FAblica Dominicana", "Puerto Rico", "Espa\u00F1a", "Estados Unidos", "Canad\u00E1", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Jap\u00F3n", "Corea del Sur", "China", "India", "Australia", "Sud\u00E1frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu\u00EDa", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B\u00E9lgica", "Pa\u00EDses Bajos", "Nueva Zelanda"}));
        cbxPais.setSelectedItem(selected.getPais());
        cbxPais.setBounds(131, 267, 280, 20);
        panel.add(cbxPais);
        
        cbxSector = new JComboBox();
        cbxSector.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Comercio", "Servicios", "Manufactura", "Tecnolog\u00EDa", "Construcci\u00F3n", "Salud", "Educaci\u00F3n", "Transporte", "Turismo", "Agricultura", "Finanzas", "Energ\u00EDa", "Telecomunicaciones", "Alimentos y Bebidas", "Textil", "Inmobiliaria", "Log\u00EDstica", "Publicidad", "Automotriz", "Seguros", "Consultor\u00EDa", "Miner\u00EDa", "Pesca", "Artes Gr\u00E1ficas", "Entretenimiento", "Farmac\u00E9utica", "Qu\u00EDmica", "Ambiental", "Seguridad", "Legal", "Artesan\u00EDa"}));
        cbxSector.setSelectedItem(selected.getSector());
        cbxSector.setBounds(131, 305, 280, 20);
        panel.add(cbxSector);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnRegistrar = new JButton("Modificar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(txtIdentificador.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() || cbxSector.getSelectedIndex() == 0 || cbxPais.getSelectedIndex() == 0) {
                	JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
                }
            	
            	selected.setIdentificador(txtIdentificador.getText());
                selected.setNombre(txtNombre.getText());
                selected.setPais(cbxPais.getSelectedItem().toString());
                selected.setSector(cbxSector.getSelectedItem().toString());
                if(imagenActual != null) {
					selected.setImagen(imagenActual);
				}
                Bolsa.getInstance().modificarEmpresa(selected);
                JOptionPane.showMessageDialog(null, "Modificación Satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
				ListadoEmpresa.loadEmpresas();
                dispose();
                
            }
        });
        buttonPane.add(btnRegistrar);
        getRootPane().setDefaultButton(btnRegistrar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);
    }

}