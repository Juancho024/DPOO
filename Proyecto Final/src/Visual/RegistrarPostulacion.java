package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Logico.Bolsa;
import Logico.Candidato;
import Logico.Postulacion;
import Logico.Vacante;

public class RegistrarPostulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<Vacante> cbxVacantes;
    private JComboBox<String> cbxTipoContrato;
    private JTextField txtPaisResidencia;
    private JTextField txtCiudadResidencia;
    private JCheckBox chkMudanza;
    private JCheckBox chkDisponibilidadVehiculo;
    private JCheckBox chkLicencia;
    private JTextField txtPretensionSalarial;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JComboBox<Candidato> cbxCandidatos;

    public static void main(String[] args) {
        try {
            RegistrarPostulacion dialog = new RegistrarPostulacion();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrarPostulacion() {
        setTitle("Registrar Postulación");
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panelDatos = new JPanel();
        panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos de Postulación", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelDatos.setBounds(10, 11, 564, 380);
        contentPanel.add(panelDatos);
        panelDatos.setLayout(null);

        Label lblCandidato = new Label("Candidato:");
        lblCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCandidato.setBounds(10, 30, 100, 22);
        panelDatos.add(lblCandidato);

        cbxCandidatos = new JComboBox<>();
        cbxCandidatos.setBounds(120, 30, 400, 22);
        panelDatos.add(cbxCandidatos);

        Label lblVacante = new Label("Vacante:");
        lblVacante.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVacante.setBounds(10, 70, 100, 22);
        panelDatos.add(lblVacante);

        cbxVacantes = new JComboBox<>();
        cbxVacantes.setBounds(120, 70, 400, 22);
        panelDatos.add(cbxVacantes);

        Label lblTipoContrato = new Label("Tipo de Contrato:");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 110, 120, 22);
        panelDatos.add(lblTipoContrato);

        cbxTipoContrato = new JComboBox<>();
        cbxTipoContrato.setModel(new DefaultComboBoxModel<>(new String[] {
            "Tiempo Completo", "Medio Tiempo", "Por Horas", "Por Proyecto"
        }));
        cbxTipoContrato.setBounds(140, 110, 200, 22);
        panelDatos.add(cbxTipoContrato);

        Label lblPais = new Label("País Residencia:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 150, 120, 22);
        panelDatos.add(lblPais);

        txtPaisResidencia = new JTextField();
        txtPaisResidencia.setBounds(140, 150, 200, 22);
        panelDatos.add(txtPaisResidencia);
        txtPaisResidencia.setColumns(10);

        Label lblCiudad = new Label("Ciudad Residencia:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 190, 120, 22);
        panelDatos.add(lblCiudad);

        txtCiudadResidencia = new JTextField();
        txtCiudadResidencia.setBounds(140, 190, 200, 22);
        panelDatos.add(txtCiudadResidencia);
        txtCiudadResidencia.setColumns(10);

        Label lblMudanza = new Label("Disponibilidad Mudanza:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(10, 230, 170, 22);
        panelDatos.add(lblMudanza);

        chkMudanza = new JCheckBox("");
        chkMudanza.setBounds(190, 230, 97, 23);
        panelDatos.add(chkMudanza);

        Label lblVehiculo = new Label("Vehículo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(10, 270, 120, 22);
        panelDatos.add(lblVehiculo);

        chkDisponibilidadVehiculo = new JCheckBox("");
        chkDisponibilidadVehiculo.setBounds(140, 270, 97, 23);
        panelDatos.add(chkDisponibilidadVehiculo);

        Label lblLicencia = new Label("Licencia Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(10, 310, 120, 22);
        panelDatos.add(lblLicencia);

        chkLicencia = new JCheckBox("");
        chkLicencia.setBounds(140, 310, 97, 23);
        panelDatos.add(chkLicencia);

        Label lblSalario = new Label("Pretensión Salarial:");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(300, 150, 120, 22);
        panelDatos.add(lblSalario);

        txtPretensionSalarial = new JTextField();
        txtPretensionSalarial.setBounds(430, 150, 100, 22);
        panelDatos.add(txtPretensionSalarial);
        txtPretensionSalarial.setColumns(10);

        // Cargar datos iniciales
        cargarCandidatos();
        cargarVacantes();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarPostulacion();
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

    private void cargarCandidatos() {
        cbxCandidatos.removeAllItems();
        for (Candidato candidato : Bolsa.getInstance().getMisCandidatos()) {
            cbxCandidatos.addItem(candidato);
        }
    }

    private void cargarVacantes() {
        cbxVacantes.removeAllItems();
        for (Vacante vacante : Bolsa.getInstance().getMisVacantes()) {
            cbxVacantes.addItem(vacante);
        }
    }

    private void registrarPostulacion() {
        try {
            // Validar selección de candidato y vacante
            if (cbxCandidatos.getSelectedItem() == null) {
                throw new Exception("Seleccione un candidato");
            }
            
            if (cbxVacantes.getSelectedItem() == null) {
                throw new Exception("Seleccione una vacante");
            }
            
            Candidato candidato = (Candidato) cbxCandidatos.getSelectedItem();
            Vacante vacante = (Vacante) cbxVacantes.getSelectedItem();
            
            // Validar campos obligatorios
            String pais = txtPaisResidencia.getText();
            String ciudad = txtCiudadResidencia.getText();
            String salarioStr = txtPretensionSalarial.getText();
            
            if (pais.isEmpty() || ciudad.isEmpty() || salarioStr.isEmpty()) {
                throw new Exception("Complete todos los campos obligatorios");
            }
            
            float pretensionSalarial = Float.parseFloat(salarioStr);
            String tipoContrato = (String) cbxTipoContrato.getSelectedItem();
            boolean mudanza = chkMudanza.isSelected();
            boolean vehiculo = chkDisponibilidadVehiculo.isSelected();
            boolean licencia = chkLicencia.isSelected();
            
            // Crear postulación
            String[] infoEstudio = vacante.getTipoEmpleado()};
            Postulacion postulacion = new Postulacion(
                "PST-" + System.currentTimeMillis(),
                vacante.getTipoEmpleado(),
                infoEstudio,
                tipoContrato,
                pais,
                ciudad,
                mudanza,
                vehiculo,
                licencia,
                pretensionSalarial
            );
            
            // Registrar en bolsa
            Bolsa bolsa = Bolsa.getInstance();
            postulacion.getMisPersonas().add(candidato);
            bolsa.getMisPostulaciones().add(postulacion);
            
            // Agregar a candidato
            candidato.getMisFormulariosPersona().add(postulacion);
            
            JOptionPane.showMessageDialog(this, "Postulación registrada con éxito!");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de salario inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}