package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * @author Joel Cubero
 * @version 1.0 08/09/2017
 */

public class Ventana extends JFrame {
	//Atributos
	private static final long serialVersionUID = 1L;
	private Container contenedor;
	private JButton btnStart, btnStop;
	private PanelSup sup;
	
	//Constructor
	public Ventana() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Llamada a metodos
		crearPanelTop();
		
		crearPanelBottom();
		
		crearPanelAutor();
		//Fin llamada
		
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/images/vm100.png")));
		this.setSize(new Dimension(573, 383));
		this.setTitle("VMware services");
		this.setVisible(true);
		Swing.centrar(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Metodo para crear el panel superior
	private void crearPanelTop() {
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		
		sup = new PanelSup();
		sup.getEtiqueta().setLocation(187, 139);
		sup.setBounds(0, 0, 567, 177);
		panelTop.add(sup);
		
		contenedor.add(panelTop);
	}
	
	//Metodo para crear el panel inferior
	private void crearPanelBottom() {
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(null);
		
		btnStart = new JButton("Start Services");
		btnStart.setFont(new Font("Arial", Font.BOLD, 17));
		btnStart.setBounds(164, 33, 238, 38);
		panelBottom.add(btnStart);
		
		btnStop = new JButton("Stop Services");
		btnStop.setFont(new Font("Arial", Font.BOLD, 17));
		btnStop.setBounds(164, 104, 238, 38);
		panelBottom.add(btnStop);
		
		contenedor.add(panelBottom);
	}
	
	//Metodo para crear el panel con la etiqueta autor
	private void crearPanelAutor() {
		JPanel panelAutor = new JPanel();
		panelAutor.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		FlowLayout flowLayout = (FlowLayout) panelAutor.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelAutor.setMaximumSize(new Dimension(32767, 1000));
		
		JLabel autor = new JLabel("Autor: Joel Cubero");
		panelAutor.add(autor);
		
		contenedor.add(panelAutor);		
	}
		
	//Metodos GET
	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnStop() {
		return btnStop;
	}
	
	public PanelSup getPanelSup() {
		return sup;
	}
	
	//Metodos SET
	public void setEstado(int estado) {
		if(estado == 1) {
			sup.setEtiqueta(" ESTADO: INICIADO", "/images/verde16.png");
		} else if (estado == 0) {
			sup.setEtiqueta(" ESTADO: PARADO", "/images/rojo16.png");
		}
	}
}
