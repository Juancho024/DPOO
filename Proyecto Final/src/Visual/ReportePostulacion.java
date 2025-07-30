package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import Logico.Postulacion;
import Logico.Candidato; // Podrías necesitarlo para obtener el nombre del candidato si no está en Postulacion
import Logico.Bolsa; // Para buscar el candidato por cédula si es necesario
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox; // Para habilidades de obrero
import javax.swing.JRadioButton; // Para sí/no de mudanza, vehículo, licencia

public class ReportePostulacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtCedulaCandidato;
    private JTextField txtNombreCandidato; // Para mostrar el nombre del candidato
    private JTextField txtTipoContrato;
    private JTextField txtPaisResidencia;
    private JTextField txtCiudadResidencia;
    private JTextField txtPretensionSalarial;
    private JTextField txtNivelEstudio;

    private JRadioButton rdbtnMudanzaSi;
    private JRadioButton rdbtnMudanzaNo;
    private JRadioButton rdbtnVehiculoSi;
    private JRadioButton rdbtnVehiculoNo;
    private JRadioButton rdbtnLicenciaSi;
    private JRadioButton rdbtnLicenciaNo;

    // Componentes para detalles de nivel de estudio
    private JPanel panelDetalleEstudio;
    private JTextField txtDetalleEstudio; // Para Universitario/TécnicoSuperior
    private JTextField txtAniosExperiencia; // Para Técnico Superior

    // Checkboxes para Obrero
    private JCheckBox chkElectricidad;
    private JCheckBox chkSoldadura;
    private JCheckBox chkTecnicaPintura;
    private JCheckBox chkTuberias;
    private JCheckBox chkMantenimiento;
    private JCheckBox chkLecturaPlanos; // Cambiado de chkMaquinaria a chkLecturaPlanos

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            // Ejemplo de uso (necesitarías una Postulacion real)
            // Postulacion dummyPostulacion = new Postulacion("P001", "123-456-7890", "Universitario", "Ingeniería en Sistemas", 0, "Tiempo Completo", "México", "Ciudad de México", true, true, false, 50000.0f, true);
            // ReportePostulacion dialog = new ReportePostulacion(dummyPostulacion);
            // dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            // dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ReportePostulacion(Postulacion selected) {
        setTitle("Reporte de Postulación");
        setBounds(100, 100, 550, 680); // Ajustar tamaño de la ventana
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panelDatosPrincipales = new JPanel();
        panelDatosPrincipales.setBorder(new TitledBorder(null, " Datos de la Postulación ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDatosPrincipales.setBounds(10, 11, 514, 320); // Ajustar tamaño
        contentPanel.add(panelDatosPrincipales);
        panelDatosPrincipales.setLayout(null);

        // Identificador
        Label lblIdentificador = new Label("Identificador: ");
        lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdentificador.setBounds(10, 23, 90, 22);
        panelDatosPrincipales.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setEnabled(false);
        txtIdentificador.setEditable(false);
        txtIdentificador.setText(selected.getIdentificador());
        txtIdentificador.setBounds(10, 48, 240, 20);
        panelDatosPrincipales.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        // Candidato (Cédula y Nombre)
        Label lblCedulaCandidato = new Label("Cédula Candidato: ");
        lblCedulaCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCedulaCandidato.setBounds(260, 23, 130, 22);
        panelDatosPrincipales.add(lblCedulaCandidato);

        txtCedulaCandidato = new JTextField();
        txtCedulaCandidato.setEnabled(false);
        txtCedulaCandidato.setEditable(false);
        txtCedulaCandidato.setText(selected.getCedulaCliente());
        txtCedulaCandidato.setBounds(260, 48, 240, 20);
        panelDatosPrincipales.add(txtCedulaCandidato);
        txtCedulaCandidato.setColumns(10);
        
        Label lblNombreCandidato = new Label("Nombre Candidato: ");
        lblNombreCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombreCandidato.setBounds(10, 76, 130, 22);
        panelDatosPrincipales.add(lblNombreCandidato);

        txtNombreCandidato = new JTextField();
        txtNombreCandidato.setEnabled(false);
        txtNombreCandidato.setEditable(false);
        // Buscar el nombre del candidato a partir de la cédula
        Candidato cand = Bolsa.getInstance().buscarCandidatoByCod(selected.getCedulaCliente());
        if (cand != null) {
            txtNombreCandidato.setText(cand.getNombre() + " " + cand.getApellido());
        } else {
            txtNombreCandidato.setText("No encontrado");
        }
        txtNombreCandidato.setBounds(10, 101, 490, 20);
        panelDatosPrincipales.add(txtNombreCandidato);
        txtNombreCandidato.setColumns(10);

        // Tipo de Contrato
        Label lblTipoContrato = new Label("Tipo de Contrato: ");
        lblTipoContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipoContrato.setBounds(10, 130, 120, 22);
        panelDatosPrincipales.add(lblTipoContrato);

        txtTipoContrato = new JTextField();
        txtTipoContrato.setEnabled(false);
        txtTipoContrato.setEditable(false);
        txtTipoContrato.setText(selected.getTipoContrato());
        txtTipoContrato.setBounds(10, 155, 240, 20);
        panelDatosPrincipales.add(txtTipoContrato);
        txtTipoContrato.setColumns(10);

        // País y Ciudad
        Label lblPais = new Label("País Residencia: ");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(260, 130, 120, 22);
        panelDatosPrincipales.add(lblPais);

        txtPaisResidencia = new JTextField();
        txtPaisResidencia.setEnabled(false);
        txtPaisResidencia.setEditable(false);
        txtPaisResidencia.setText(selected.getPaisResidencia());
        txtPaisResidencia.setBounds(260, 155, 240, 20);
        panelDatosPrincipales.add(txtPaisResidencia);
        txtPaisResidencia.setColumns(10);

        Label lblCiudad = new Label("Ciudad Residencia: ");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 184, 130, 22);
        panelDatosPrincipales.add(lblCiudad);

        txtCiudadResidencia = new JTextField();
        txtCiudadResidencia.setEnabled(false);
        txtCiudadResidencia.setEditable(false);
        txtCiudadResidencia.setText(selected.getCiudadResidencia());
        txtCiudadResidencia.setBounds(10, 209, 240, 20);
        panelDatosPrincipales.add(txtCiudadResidencia);
        txtCiudadResidencia.setColumns(10);

        // Pretensión Salarial
        Label lblSalario = new Label("Pretensión Salarial: ");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(260, 184, 140, 22);
        panelDatosPrincipales.add(lblSalario);

        txtPretensionSalarial = new JTextField();
        txtPretensionSalarial.setEnabled(false);
        txtPretensionSalarial.setEditable(false);
        txtPretensionSalarial.setText(String.format("%,.2f", selected.getPretensionSalarial())); // Formato de moneda
        txtPretensionSalarial.setBounds(260, 209, 240, 20);
        panelDatosPrincipales.add(txtPretensionSalarial);
        txtPretensionSalarial.setColumns(10);
        
        // Opciones de Radio Buttons
        Label lblMudanza = new Label("Dispuesto a Mudarse:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(10, 240, 140, 22);
        panelDatosPrincipales.add(lblMudanza);

        rdbtnMudanzaSi = new JRadioButton("Sí");
        rdbtnMudanzaSi.setEnabled(false);
        rdbtnMudanzaSi.setBounds(155, 240, 50, 23);
        panelDatosPrincipales.add(rdbtnMudanzaSi);

        rdbtnMudanzaNo = new JRadioButton("No");
        rdbtnMudanzaNo.setEnabled(false);
        rdbtnMudanzaNo.setBounds(205, 240, 50, 23);
        panelDatosPrincipales.add(rdbtnMudanzaNo);

        if (selected.isMudanza()) {
            rdbtnMudanzaSi.setSelected(true);
        } else {
            rdbtnMudanzaNo.setSelected(true);
        }

        Label lblVehiculo = new Label("Vehículo Propio:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(260, 240, 110, 22);
        panelDatosPrincipales.add(lblVehiculo);

        rdbtnVehiculoSi = new JRadioButton("Sí");
        rdbtnVehiculoSi.setEnabled(false);
        rdbtnVehiculoSi.setBounds(375, 240, 50, 23);
        panelDatosPrincipales.add(rdbtnVehiculoSi);

        rdbtnVehiculoNo = new JRadioButton("No");
        rdbtnVehiculoNo.setEnabled(false);
        rdbtnVehiculoNo.setBounds(425, 240, 50, 23);
        panelDatosPrincipales.add(rdbtnVehiculoNo);

        if (selected.isDisponibilidadVehiculo()) {
            rdbtnVehiculoSi.setSelected(true);
        } else {
            rdbtnVehiculoNo.setSelected(true);
        }

        Label lblLicencia = new Label("Licencia Conducir:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(10, 275, 120, 22);
        panelDatosPrincipales.add(lblLicencia);

        rdbtnLicenciaSi = new JRadioButton("Sí");
        rdbtnLicenciaSi.setEnabled(false);
        rdbtnLicenciaSi.setBounds(135, 275, 50, 23);
        panelDatosPrincipales.add(rdbtnLicenciaSi);

        rdbtnLicenciaNo = new JRadioButton("No");
        rdbtnLicenciaNo.setEnabled(false);
        rdbtnLicenciaNo.setBounds(185, 275, 50, 23);
        panelDatosPrincipales.add(rdbtnLicenciaNo);

        if (selected.isLicencia()) {
            rdbtnLicenciaSi.setSelected(true);
        } else {
            rdbtnLicenciaNo.setSelected(true);
        }


        // Panel de Datos Académicos / Nivel de Estudio
        JPanel panelNivelEstudio = new JPanel();
        panelNivelEstudio.setBorder(new TitledBorder(null, " Nivel de Estudio ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelNivelEstudio.setBounds(10, 340, 514, 250); // Ajustar tamaño
        contentPanel.add(panelNivelEstudio);
        panelNivelEstudio.setLayout(null);

        Label lblNivel = new Label("Nivel de Estudio: ");
        lblNivel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNivel.setBounds(10, 25, 120, 22);
        panelNivelEstudio.add(lblNivel);

        txtNivelEstudio = new JTextField();
        txtNivelEstudio.setEnabled(false);
        txtNivelEstudio.setEditable(false);
        txtNivelEstudio.setText(selected.getNivelEstudio());
        txtNivelEstudio.setBounds(135, 25, 360, 20);
        panelNivelEstudio.add(txtNivelEstudio);
        txtNivelEstudio.setColumns(10);
        
        // Panel para detalles específicos del nivel de estudio (se hará visible según el tipo)
        panelDetalleEstudio = new JPanel();
        panelDetalleEstudio.setBounds(10, 60, 494, 180); // Posición y tamaño dentro de panelNivelEstudio
        panelNivelEstudio.add(panelDetalleEstudio);
        panelDetalleEstudio.setLayout(null);
        panelDetalleEstudio.setBorder(new TitledBorder(null, " Detalles ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDetalleEstudio.setVisible(false); // Inicialmente oculto

        // Lógica para mostrar los detalles según el nivel de estudio
        String nivelEstudio = selected.getNivelEstudio();
        String detalleNivelEstudio = selected.getDetalleNivelEstudio();
        int aniosExperiencia = selected.getAniosExperiencia();

        if ("Universitario".equals(nivelEstudio)) {
            panelDetalleEstudio.setVisible(true);
            Label lblCarrera = new Label("Carrera Graduada: ");
            lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblCarrera.setBounds(10, 30, 150, 22);
            panelDetalleEstudio.add(lblCarrera);

            txtDetalleEstudio = new JTextField();
            txtDetalleEstudio.setEnabled(false);
            txtDetalleEstudio.setEditable(false);
            txtDetalleEstudio.setText(detalleNivelEstudio);
            txtDetalleEstudio.setBounds(160, 30, 300, 20);
            panelDetalleEstudio.add(txtDetalleEstudio);
            txtDetalleEstudio.setColumns(10);
            
        } else if ("Técnico Superior".equals(nivelEstudio)) {
            panelDetalleEstudio.setVisible(true);
            Label lblTecnico = new Label("Área de Especialidad: ");
            lblTecnico.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblTecnico.setBounds(10, 30, 150, 22);
            panelDetalleEstudio.add(lblTecnico);

            txtDetalleEstudio = new JTextField();
            txtDetalleEstudio.setEnabled(false);
            txtDetalleEstudio.setEditable(false);
            txtDetalleEstudio.setText(detalleNivelEstudio);
            txtDetalleEstudio.setBounds(160, 30, 300, 20);
            panelDetalleEstudio.add(txtDetalleEstudio);
            txtDetalleEstudio.setColumns(10);

            Label lblAnyo = new Label("Años de Experiencia: ");
            lblAnyo.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblAnyo.setBounds(10, 70, 150, 22);
            panelDetalleEstudio.add(lblAnyo);

            txtAniosExperiencia = new JTextField();
            txtAniosExperiencia.setEnabled(false);
            txtAniosExperiencia.setEditable(false);
            txtAniosExperiencia.setText(String.valueOf(aniosExperiencia));
            txtAniosExperiencia.setBounds(160, 70, 100, 20);
            panelDetalleEstudio.add(txtAniosExperiencia);
            txtAniosExperiencia.setColumns(10);

        } else if ("Obrero".equals(nivelEstudio)) {
            panelDetalleEstudio.setVisible(true);
            Label lblHabilidades = new Label("Habilidades: ");
            lblHabilidades.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblHabilidades.setBounds(10, 15, 90, 22);
            panelDetalleEstudio.add(lblHabilidades);

            chkElectricidad = new JCheckBox("Electricidad básica");
            chkElectricidad.setEnabled(false);
            chkElectricidad.setBounds(10, 40, 150, 23);
            panelDetalleEstudio.add(chkElectricidad);

            chkSoldadura = new JCheckBox("Soldadura");
            chkSoldadura.setEnabled(false);
            chkSoldadura.setBounds(10, 65, 150, 23);
            panelDetalleEstudio.add(chkSoldadura);

            chkTecnicaPintura = new JCheckBox("Técnicas de pintura o albañilería");
            chkTecnicaPintura.setEnabled(false);
            chkTecnicaPintura.setBounds(180, 40, 250, 23);
            panelDetalleEstudio.add(chkTecnicaPintura);

            chkTuberias = new JCheckBox("Instalación de tuberías");
            chkTuberias.setEnabled(false);
            chkTuberias.setBounds(180, 65, 250, 23);
            panelDetalleEstudio.add(chkTuberias);

            chkMantenimiento = new JCheckBox("Mantenimiento básico de equipos");
            chkMantenimiento.setEnabled(false);
            chkMantenimiento.setBounds(10, 90, 250, 23);
            panelDetalleEstudio.add(chkMantenimiento);
            
            chkLecturaPlanos = new JCheckBox("Lectura de planos"); // Cambiado el nombre de la variable
            chkLecturaPlanos.setEnabled(false);
            chkLecturaPlanos.setBounds(180, 90, 250, 23);
            panelDetalleEstudio.add(chkLecturaPlanos);

            // Marcar las habilidades según el detalleNivelEstudio
            String[] habilidades = detalleNivelEstudio.split(", ");
            for (String habilidad : habilidades) {
                if ("Electricidad básica".equals(habilidad)) chkElectricidad.setSelected(true);
                else if ("Soldadura".equals(habilidad)) chkSoldadura.setSelected(true);
                else if ("Técnicas de pintura o albañilería".equals(habilidad)) chkTecnicaPintura.setSelected(true);
                else if ("Instalación de tuberías".equals(habilidad)) chkTuberias.setSelected(true);
                else if ("Mantenimiento básico de equipos".equals(habilidad)) chkMantenimiento.setSelected(true);
                else if ("Lectura de planos".equals(habilidad)) chkLecturaPlanos.setSelected(true);
            }
        }
        
        JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Cierra la ventana
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
    }
}