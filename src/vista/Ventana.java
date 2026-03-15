package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * Ventana principal
 * 
 * @version 2.1.0
 */
public class Ventana extends JFrame {
	// Atributos
	private static final long serialVersionUID = 1L;
	
	private Container contenedor;
	private JButton btn;
	private PanelServicios panelServicios;

	// Constructor
	public Ventana() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// Instanciar paneles y botones
		crearPanelLogo();
		crearPanelServicios();
		crearPanelBoton();
		crearPanelFooter();

		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/images/vms_100.png")));
		this.pack();
		this.setMinimumSize(new Dimension(460, 0));
		this.setTitle("VMware Services v2.1.0");
		this.setVisible(true);
		Swing.centrar(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Panel superior con el logo
	private void crearPanelLogo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		panel.add(new PanelImagen());
		contenedor.add(panel);
	}

	// Panel con el estado individual de cada servicio
	private void crearPanelServicios() {
		panelServicios = new PanelServicios();
		contenedor.add(panelServicios);
	}

	// Panel con el boton de accion unico
	private void crearPanelBoton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));

		btn = new JButton("Start Services");
		btn.setFont(new Font("Arial", Font.BOLD, 14));
		btn.setPreferredSize(new Dimension(180, 36));
		panel.add(btn);

		contenedor.add(panel);
	}

	// Panel footer
	private void crearPanelFooter() {
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		FlowLayout fl = (FlowLayout) panel.getLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		panel.setMaximumSize(new Dimension(32767, 1000));
		panel.add(new JLabel("\u00a9 Enigmo \u00b7 v2.1.0"));
		contenedor.add(panel);
	}

	// GET
	public JButton getBtn() {
		return btn;
	}

	public PanelServicios getPanelServicios() {
		return panelServicios;
	}

	// SET
	public void setEstados(int[] estados) {
		panelServicios.setEstados(estados);
	}

	public void setTextoBoton(String texto) {
		btn.setText(texto);
	}

	public void setBotonHabilitado(boolean habilitado) {
		btn.setEnabled(habilitado);
	}
}
