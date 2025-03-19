package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @version 1.0 08/09/2017
 */

public class PanelImagen extends JPanel {
	//Atributos
	private static final long serialVersionUID = 1L;
	private ImageIcon imagen;
	private Image fondo;
	
	//Constructor
	public PanelImagen() {
		this.setLayout(new BorderLayout());
		imagen = new ImageIcon(getClass().getResource("/images/vm100.png"));
		fondo = imagen.getImage();
		this.setPreferredSize(new Dimension(100, 100));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Pintamos la imagen centrada, es decir, (cogemos el tamaño del panel y el tamaño de la imagen y dividimos entre 2)
		g.drawImage(fondo, (this.getWidth() - imagen.getIconWidth()) / 2, (this.getHeight() - imagen.getIconHeight()) / 2, 
				imagen.getIconWidth(), imagen.getIconHeight(), this);
	}
}
