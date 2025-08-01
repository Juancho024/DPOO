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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RegistrarEmpresa extends JDialog {

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
            RegistrarEmpresa dialog = new RegistrarEmpresa();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrarEmpresa() {
        setTitle("Registrar Empresa");
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
        label.setBounds(21, 172, 138, 22);
        panel.add(label);

        txtIdentificador = new JTextField();
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
        txtIdentificador.setBounds(178, 172, 233, 22);
        panel.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        Label label_1 = new Label("Nombre:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_1.setBounds(21, 212, 100, 22);
        panel.add(label_1);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(131, 212, 280, 22);
        panel.add(txtNombre);

        Label label_2 = new Label("País:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_2.setBounds(21, 252, 100, 22);
        panel.add(label_2);

        Label label_3 = new Label("Sector:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_3.setBounds(21, 292, 100, 22);
        panel.add(label_3);
        
        cbxPais = new JComboBox();
        cbxPais.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Argentina", "Brasil", "Chile", "Colombia", "Ecuador", "Per\u00FA", "M\u00E9xico", "Guatemala", "Honduras", "El Salvador", "Nicaragua", "Costa Rica", "Panam\u00E1", "Venezuela", "Paraguay", "Uruguay", "Bolivia", "Cuba", "Rep\u00FAblica Dominicana", "Puerto Rico", "Espa\u00F1a", "Estados Unidos", "Canad\u00E1", "Italia", "Francia", "Alemania", "Reino Unido", "Portugal", "Jap\u00F3n", "Corea del Sur", "China", "India", "Australia", "Sud\u00E1frica", "Egipto", "Nigeria", "Marruecos", "Arabia Saudita", "Turqu\u00EDa", "Rusia", "Noruega", "Suecia", "Finlandia", "Polonia", "Grecia", "Suiza", "Austria", "B\u00E9lgica", "Pa\u00EDses Bajos", "Nueva Zelanda"}));
        cbxPais.setBounds(131, 254, 280, 20);
        panel.add(cbxPais);
        
        cbxSector = new JComboBox();
        cbxSector.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Opci\u00F3n", "Comercio", "Servicios", "Manufactura", "Tecnolog\u00EDa", "Construcci\u00F3n", "Salud", "Educaci\u00F3n", "Transporte", "Turismo", "Agricultura", "Finanzas", "Energ\u00EDa", "Telecomunicaciones", "Alimentos y Bebidas", "Textil", "Inmobiliaria", "Log\u00EDstica", "Publicidad", "Automotriz", "Seguros", "Consultor\u00EDa", "Miner\u00EDa", "Pesca", "Artes Gr\u00E1ficas", "Entretenimiento", "Farmac\u00E9utica", "Qu\u00EDmica", "Ambiental", "Seguridad", "Legal", "Artesan\u00EDa"}));
        cbxSector.setBounds(131, 292, 280, 20);
        panel.add(cbxSector);
        
        lbLogo = new JLabel("Sin imagen", SwingConstants.CENTER);
        lbLogo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lbLogo.setBounds(131, 21, 166, 116);
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
        btnLogo.setBounds(153, 144, 123, 22);
        panel.add(btnLogo);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEmpresa();
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

    private void registrarEmpresa() {
        String identificador = txtIdentificador.getText();
        String nombre = txtNombre.getText();
        String pais = cbxPais.getSelectedItem().toString();
        String sector = cbxSector.getSelectedItem().toString();
        
        if(identificador.isEmpty() || nombre.isEmpty() || pais.isEmpty() || sector.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(Bolsa.getInstance().buscarEmpresaByCod(identificador) != null) {
			JOptionPane.showMessageDialog(null, "El RNC ingresado ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Empresa nuevaEmpresa = new Empresa(identificador, imagenActual, nombre, pais, sector);
	        Bolsa.getInstance().registrarEmpresa(nuevaEmpresa);
	        
	        JOptionPane.showMessageDialog(this, "Empresa registrada con éxito!");
	        dispose();
		}
    }
}