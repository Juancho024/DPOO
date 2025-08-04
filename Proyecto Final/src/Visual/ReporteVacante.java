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
import java.awt.SystemColor;
import java.awt.Toolkit;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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
		setBounds(100, 100, 530, 595);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Recursos/logo.jpg")));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Obtener datos de la empresa asociada
		Empresa empresa = Bolsa.getInstance().buscarEmpresaByCod(selected.getRncEmpresa());
		String nombreEmpresa = empresa != null ? empresa.getNombre() : "No disponible";
		contentPanel.setLayout(null);

		// Panel 1: Datos Generales
		JPanel panelGeneral = new JPanelRedondeado(60);
		panelGeneral.setBackground(SystemColor.inactiveCaption);
		panelGeneral.setBounds(10, 59, 494, 152);
		contentPanel.add(panelGeneral);
		panelGeneral.setLayout(null);

		Label lblId = new Label("Identificador:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 30, 100, 22);
		panelGeneral.add(lblId);

		txtIdentificador = new JTextField();
		txtIdentificador.setBackground(SystemColor.inactiveCaptionBorder);
		txtIdentificador.setEnabled(false);
		txtIdentificador.setEditable(false);
		txtIdentificador.setText(selected.getIdentificador());
		txtIdentificador.setBounds(120, 30, 150, 20);
		panelGeneral.add(txtIdentificador);
		txtIdentificador.setColumns(10);

		Label lblNombre = new Label("Nombre Vacante:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 60, 100, 22);
		panelGeneral.add(lblNombre);

		Label lblContrato = new Label("Tipo Contrato:");
		lblContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrato.setBounds(10, 90, 100, 22);
		panelGeneral.add(lblContrato);

		txtTipoContrato = new JTextField();
		txtTipoContrato.setEnabled(false);
		txtTipoContrato.setEditable(false);
		txtTipoContrato.setText(selected.getTipoContrato());
		txtTipoContrato.setBounds(120, 90, 150, 20);
		panelGeneral.add(txtTipoContrato);
		txtTipoContrato.setColumns(10);

		Label lblNivel = new Label("Nivel Estudio:");
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNivel.setBounds(10, 120, 100, 22);
		panelGeneral.add(lblNivel);

		txtNivelEstudio = new JTextField();
		txtNivelEstudio.setEnabled(false);
		txtNivelEstudio.setEditable(false);
		txtNivelEstudio.setText(selected.getNivelEstudio());
		txtNivelEstudio.setBounds(120, 120, 150, 20);
		panelGeneral.add(txtNivelEstudio);
		txtNivelEstudio.setColumns(10);

		Label lblSalario = new Label("Salario:");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalario.setBounds(280, 30, 60, 22);
		panelGeneral.add(lblSalario);

		txtSalario = new JTextField();
		txtSalario.setEnabled(false);
		txtSalario.setEditable(false);
		txtSalario.setText(String.valueOf(selected.getPretensionSalarial()));
		txtSalario.setBounds(340, 30, 140, 20);
		panelGeneral.add(txtSalario);
		txtSalario.setColumns(10);

		Label lblEstado = new Label("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(276, 90, 60, 22);
		panelGeneral.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBackground(SystemColor.inactiveCaptionBorder);
		txtEstado.setText(selected.isStatus() ? "Activa" : "Inactiva");
		txtEstado.setBounds(340, 90, 140, 20);
		panelGeneral.add(txtEstado);
		txtEstado.setColumns(10);

		txtNombreVacante = new JTextField();
		txtNombreVacante.setEnabled(false);
		txtNombreVacante.setEditable(false);
		txtNombreVacante.setText(selected.getNombreVacante());
		txtNombreVacante.setBounds(120, 60, 360, 20);
		panelGeneral.add(txtNombreVacante);
		txtNombreVacante.setColumns(10);

		Label label_3 = new Label("Datos Generales");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setAlignment(Label.CENTER);
		label_3.setBounds(174, 2, 146, 22);
		panelGeneral.add(label_3);

		// Panel 2: Ubicación
		JPanel panelUbicacion = new JPanelRedondeado(60);
		panelUbicacion.setBackground(SystemColor.inactiveCaption);
		panelUbicacion.setBounds(10, 222, 494, 100);
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

		Label label_2 = new Label("Ubicaci\u00F3n");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(174, 0, 146, 22);
		panelUbicacion.add(label_2);

		// Panel 3: Requisitos
		JPanel panelRequisitos = new JPanelRedondeado(60);
		panelRequisitos.setBackground(SystemColor.inactiveCaption);
		panelRequisitos.setBounds(10, 333, 494, 100);
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

		Label label_1 = new Label("Requisitos Adicionales");
		label_1.setAlignment(Label.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(174, 0, 146, 22);
		panelRequisitos.add(label_1);

		// Panel 4: Empresa
		JPanel panelEmpresa = new JPanelRedondeado(60);
		panelEmpresa.setBackground(SystemColor.inactiveCaption);
		panelEmpresa.setBounds(10, 444, 494, 100);
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

		Label label = new Label("Datos de Empresa");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(186, 0, 121, 22);
		panelEmpresa.add(label);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 524, 48);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("DATOS DE REPORTE");
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewJgoodiesTitle.setBounds(171, 11, 182, 26);
		panel.add(lblNewJgoodiesTitle);

		// Estilizar campos
		JTextField[] campos = {txtIdentificador, txtNombreVacante, txtTipoContrato, txtNivelEstudio, 
				txtPais, txtCiudad, txtMudanza, txtVehiculo, txtLicencia, 
				txtSalario, txtEstado, txtRNC, txtNombreEmpresa};

		for (JTextField campo : campos) {
			campo.setBackground(SystemColor.inactiveCaptionBorder);
			campo.setForeground(Color.BLACK);
			campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			campo.setDisabledTextColor(Color.BLACK);
		}
	}
}