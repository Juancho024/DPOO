package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Empresa;

public class RegistrarEmpresa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtNombre;
    private JTextField txtPais;
    private JTextField txtSector;

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
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de la Empresa", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        contentPanel.add(panel);
        panel.setLayout(null);

        Label label = new Label("Identificador:");
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(10, 30, 100, 22);
        panel.add(label);

        txtIdentificador = new JTextField();
        txtIdentificador.setBounds(120, 30, 280, 22);
        panel.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        Label label_1 = new Label("Nombre:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_1.setBounds(10, 70, 100, 22);
        panel.add(label_1);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(120, 70, 280, 22);
        panel.add(txtNombre);

        Label label_2 = new Label("País:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_2.setBounds(10, 110, 100, 22);
        panel.add(label_2);

        txtPais = new JTextField();
        txtPais.setColumns(10);
        txtPais.setBounds(120, 110, 280, 22);
        panel.add(txtPais);

        Label label_3 = new Label("Sector:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_3.setBounds(10, 150, 100, 22);
        panel.add(label_3);

        txtSector = new JTextField();
        txtSector.setColumns(10);
        txtSector.setBounds(120, 150, 280, 22);
        panel.add(txtSector);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEmpresa();
            }
        });
        buttonPane.add(btnRegistrar);
        getRootPane().setDefaultButton(btnRegistrar);
        
        JButton btnCancelar = new JButton("Cancelar");
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
        String pais = txtPais.getText();
        String sector = txtSector.getText();
        
        if(identificador.isEmpty() || nombre.isEmpty() || pais.isEmpty() || sector.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Empresa nuevaEmpresa = new Empresa(identificador, nombre, pais, sector);
        Bolsa.getInstance().registrarEmpresa(nuevaEmpresa);
        
        JOptionPane.showMessageDialog(this, "Empresa registrada con éxito!");
        dispose();
    }
}