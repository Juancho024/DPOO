package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import Logico.Bolsa;
import Logico.Empresa;
import Logico.Vacante;
import java.awt.Label;

public class ReporteVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtNombreVacante;
    private JTextField txtTipoContrato;
    private JTextField txtNivelEstudio;
    private JTextField txtPais;
    private JTextField txtCiudad;
    private JTextField txtMudanza;
    private JTextField txtVehiculo;
    private JTextField txtLicencia;
    private JTextField txtSalario;
    private JTextField txtEstado;
    private JTextField txtRNC;
    private JTextField txtNombreEmpresa;

    public ReporteVacante(Vacante selected) {
        setTitle("Reporte de Vacante");
        setBounds(100, 100, 530, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Obtener datos de la empresa asociada
        Empresa empresa = Bolsa.getInstance().buscarEmpresaByCod(selected.getRncEmpresa());
        String nombreEmpresa = empresa != null ? empresa.getNombre() : "No disponible";

        // Panel 1: Datos Generales
        JPanel panelGeneral = new JPanel();
        panelGeneral.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelGeneral.setBounds(10, 11, 494, 180);
        contentPanel.add(panelGeneral);
        panelGeneral.setLayout(null);

        Label lblId = new Label("Identificador:");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblId.setBounds(10, 23, 100, 22);
        panelGeneral.add(lblId);

        txtIdentificador = new JTextField();
        txtIdentificador.setEnabled(false);
        txtIdentificador.setEditable(false);
        txtIdentificador.setText(selected.getIdentificador());
        txtIdentificador.setBounds(120, 23, 150, 20);
        panelGeneral.add(txtIdentificador);
        txtIdentificador.setColumns(10);

        Label lblNombre = new Label("Nombre Vacante:");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombre.setBounds(10, 53, 100, 22);
        panelGeneral.add(lblNombre);

        txtNombreVacante = new JTextField();
        txtNombreVacante.setEnabled(false);
        txtNombreVacante.setEditable(false);
        txtNombreVacante.setText(selected.getNombreVacante());
        txtNombreVacante.setBounds(120, 53, 360, 20);
        panelGeneral.add(txtNombreVacante);
        txtNombreVacante.setColumns(10);

        Label lblContrato = new Label("Tipo Contrato:");
        lblContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblContrato.setBounds(10, 83, 100, 22);
        panelGeneral.add(lblContrato);

        txtTipoContrato = new JTextField();
        txtTipoContrato.setEnabled(false);
        txtTipoContrato.setEditable(false);
        txtTipoContrato.setText(selected.getTipoContrato());
        txtTipoContrato.setBounds(120, 83, 150, 20);
        panelGeneral.add(txtTipoContrato);
        txtTipoContrato.setColumns(10);

        Label lblNivel = new Label("Nivel Estudio:");
        lblNivel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNivel.setBounds(10, 113, 100, 22);
        panelGeneral.add(lblNivel);

        txtNivelEstudio = new JTextField();
        txtNivelEstudio.setEnabled(false);
        txtNivelEstudio.setEditable(false);
        txtNivelEstudio.setText(selected.getNivelEstudio());
        txtNivelEstudio.setBounds(120, 113, 150, 20);
        panelGeneral.add(txtNivelEstudio);
        txtNivelEstudio.setColumns(10);

        Label lblSalario = new Label("Salario:");
        lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSalario.setBounds(280, 23, 60, 22);
        panelGeneral.add(lblSalario);

        txtSalario = new JTextField();
        txtSalario.setEnabled(false);
        txtSalario.setEditable(false);
        txtSalario.setText(String.valueOf(selected.getPretensionSalarial()));
        txtSalario.setBounds(340, 23, 140, 20);
        panelGeneral.add(txtSalario);
        txtSalario.setColumns(10);

        Label lblEstado = new Label("Estado:");
        lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEstado.setBounds(280, 53, 60, 22);
        panelGeneral.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setEditable(false);
        txtEstado.setText(selected.isStatus() ? "Activa" : "Inactiva");
        txtEstado.setBounds(340, 53, 140, 20);
        panelGeneral.add(txtEstado);
        txtEstado.setColumns(10);

        // Panel 2: Ubicación
        JPanel panelUbicacion = new JPanel();
        panelUbicacion.setBorder(new TitledBorder(null, "Ubicación", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelUbicacion.setBounds(10, 201, 494, 100);
        contentPanel.add(panelUbicacion);
        panelUbicacion.setLayout(null);

        Label lblPais = new Label("País:");
        lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPais.setBounds(10, 30, 100, 22);
        panelUbicacion.add(lblPais);

        txtPais = new JTextField();
        txtPais.setEnabled(false);
        txtPais.setEditable(false);
        txtPais.setText(selected.getPaisResidencia());
        txtPais.setBounds(120, 30, 360, 20);
        panelUbicacion.add(txtPais);
        txtPais.setColumns(10);

        Label lblCiudad = new Label("Ciudad:");
        lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCiudad.setBounds(10, 60, 100, 22);
        panelUbicacion.add(lblCiudad);

        txtCiudad = new JTextField();
        txtCiudad.setEnabled(false);
        txtCiudad.setEditable(false);
        txtCiudad.setText(selected.getCiudadResidencia());
        txtCiudad.setBounds(120, 60, 360, 20);
        panelUbicacion.add(txtCiudad);
        txtCiudad.setColumns(10);

        // Panel 3: Requisitos
        JPanel panelRequisitos = new JPanel();
        panelRequisitos.setBorder(new TitledBorder(null, "Requisitos Adicionales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelRequisitos.setBounds(10, 311, 494, 100);
        contentPanel.add(panelRequisitos);
        panelRequisitos.setLayout(null);

        Label lblMudanza = new Label("Mudanza:");
        lblMudanza.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMudanza.setBounds(10, 30, 100, 22);
        panelRequisitos.add(lblMudanza);

        txtMudanza = new JTextField();
        txtMudanza.setEnabled(false);
        txtMudanza.setEditable(false);
        txtMudanza.setText(selected.isMudanza() ? "Sí" : "No");
        txtMudanza.setBounds(120, 30, 100, 20);
        panelRequisitos.add(txtMudanza);
        txtMudanza.setColumns(10);

        Label lblVehiculo = new Label("Vehículo:");
        lblVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVehiculo.setBounds(230, 30, 100, 22);
        panelRequisitos.add(lblVehiculo);

        txtVehiculo = new JTextField();
        txtVehiculo.setEnabled(false);
        txtVehiculo.setEditable(false);
        txtVehiculo.setText(selected.isDisponibilidadVehiculo() ? "Sí" : "No");
        txtVehiculo.setBounds(330, 30, 100, 20);
        panelRequisitos.add(txtVehiculo);
        txtVehiculo.setColumns(10);

        Label lblLicencia = new Label("Licencia:");
        lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLicencia.setBounds(10, 60, 100, 22);
        panelRequisitos.add(lblLicencia);

        txtLicencia = new JTextField();
        txtLicencia.setEnabled(false);
        txtLicencia.setEditable(false);
        txtLicencia.setText(selected.isLicencia() ? "Sí" : "No");
        txtLicencia.setBounds(120, 60, 100, 20);
        panelRequisitos.add(txtLicencia);
        txtLicencia.setColumns(10);

        // Panel 4: Empresa
        JPanel panelEmpresa = new JPanel();
        panelEmpresa.setBorder(new TitledBorder(null, "Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelEmpresa.setBounds(10, 421, 494, 100);
        contentPanel.add(panelEmpresa);
        panelEmpresa.setLayout(null);

        Label lblRNC = new Label("RNC:");
        lblRNC.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRNC.setBounds(10, 30, 100, 22);
        panelEmpresa.add(lblRNC);

        txtRNC = new JTextField();
        txtRNC.setEnabled(false);
        txtRNC.setEditable(false);
        txtRNC.setText(selected.getRncEmpresa());
        txtRNC.setBounds(120, 30, 150, 20);
        panelEmpresa.add(txtRNC);
        txtRNC.setColumns(10);

        Label lblNombreEmpresa = new Label("Nombre:");
        lblNombreEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombreEmpresa.setBounds(10, 60, 100, 22);
        panelEmpresa.add(lblNombreEmpresa);

        txtNombreEmpresa = new JTextField();
        txtNombreEmpresa.setEnabled(false);
        txtNombreEmpresa.setEditable(false);
        txtNombreEmpresa.setText(nombreEmpresa);
        txtNombreEmpresa.setBounds(120, 60, 360, 20);
        panelEmpresa.add(txtNombreEmpresa);
        txtNombreEmpresa.setColumns(10);

        // Estilizar campos
        JTextField[] campos = {txtIdentificador, txtNombreVacante, txtTipoContrato, txtNivelEstudio, 
                              txtPais, txtCiudad, txtMudanza, txtVehiculo, txtLicencia, 
                              txtSalario, txtEstado, txtRNC, txtNombreEmpresa};
        
        for (JTextField campo : campos) {
            campo.setBackground(Color.WHITE);
            campo.setForeground(Color.BLACK);
            campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            campo.setDisabledTextColor(Color.BLACK);
        }
    }
}