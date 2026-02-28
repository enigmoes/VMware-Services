package aplicacion;

import com.formdev.flatlaf.FlatDarkLaf;
import controlador.Controlador;

/**
 * Main App File
 * 
 * @version 2.0
 */
public class App {
	// Metodo main inicio ejecucion
	public static void main(String[] args) {
		// Aplicar Look & Feel moderno FlatDarkLaf
		FlatDarkLaf.setup();
		// Creamos objeto anonimo controlador invocando la ejecucion de dicha clase.
		new Controlador();
	}

}
