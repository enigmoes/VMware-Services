package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Panel para centrar ventana en pantalla
 * 
 * @version 2.1.0
 */
public class Swing {
	
	/**
	 * Metodo para centrar ventana en pantalla, pasamos un objeto JFrame como
	 * argumento que recibira el parametro ventana.
	 * @param ventana
	 */
	public static void centrar(JFrame ventana) {
		// Obtener las medidas de la pantalla
		Dimension medidaPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		// Obtener las medidas de la ventana
		Dimension medidaVentana = ventana.getSize();
		// Tomamos como medidas de Pantalla la mayor de las medidas
		if (medidaPantalla.height < medidaVentana.height) {
			medidaPantalla.height = medidaVentana.height;
		}
		if (medidaPantalla.width < medidaVentana.width) {
			medidaPantalla.width = medidaVentana.width;
		}
		// Ponemos la localización de la esquina superior izquierda de la ventana
		ventana.setLocation((medidaPantalla.width - medidaVentana.width) / 2,
				(medidaPantalla.height - medidaVentana.height) / 2);
	}
	
}
