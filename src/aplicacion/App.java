package aplicacion;

import javax.swing.UIManager;

import controlador.Controlador;

/**
 * @version 2.0
 */

public class App {
	//Metodo main inicio ejecucion
	public static void main(String[] args) {
		//Cambio de apariencia
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Creamos objeto anonimo controlador invocando la ejecucion de dicha clase.
		new Controlador();
	}

}
