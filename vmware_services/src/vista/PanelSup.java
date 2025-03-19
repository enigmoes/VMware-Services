package vista;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.SwingConstants;

/**
 * @version 1.0 08/09/2017
 */

public class PanelSup extends JPanel {
	//Atributos
	private static final long serialVersionUID = 1L;
	private JLabel etiqueta;
	
	//Constructor
	public PanelSup() {
		this.setLayout(null);
		
		crearPanelImagen();
		
		crearEtiqueta();
		
		this.setSize(new Dimension(567, 177));
		this.setPreferredSize(new Dimension(567, 177));
	}
	
	//Metodo para crear el panel con la imagen
	private void crearPanelImagen() {
		PanelImagen imagen = new PanelImagen();
		imagen.setBounds(233, 11, 100, 100);
		this.add(imagen);
	}
	
	//Metodo para crear la etiqueta
	private void crearEtiqueta() {
		etiqueta = new JLabel();
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
		etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
		etiqueta.setBounds(187, 139, 192, 27);
		this.add(etiqueta);
	}
	
	//Metodos GET
	public JLabel getEtiqueta() {
		return etiqueta;
	}
	
	//Metodos SET
	public void setEtiqueta(String texto, String icono) {
		etiqueta.setText(texto);
		etiqueta.setIcon(new ImageIcon(PanelSup.class.getResource(icono)));
	}
	
}
