package aplicacion;

import javax.swing.UIManager;

import controlador.Controlador;

/**
 * @version 1.0 08/09/2017
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
