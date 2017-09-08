package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelo.Comando;
import modelo.Fichero;
import vista.Ventana;

/**
 * @author Joel Cubero
 * @version 1.0 08/09/2017
 */

public class Controlador implements ActionListener {
	//Atributos
	private Ventana ventana;
	private Comando cmd;
	private JButton btnStart;
	private JButton btnStop;
	
	//Constructor
	public Controlador() {
		ventana = new Ventana();
		cmd = new Comando();
		
		//Obtencion de elementos en refencias
		btnStart = ventana.getBtnStart();
		btnStop = ventana.getBtnStop();
		
		//Invocacion a metodos
		crearBat();
		
		//Metodo para comprobar el estado
		estado();
		
		addActionListener();
		//Fin invocacion
	}
	
	//Metodo para asociar evento a los botones
	private void addActionListener() {
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
	}
	
	//Metodo para comprobar el estado de los servicios
	private void estado() {
		//Comprobamos el estado de los servicios y actializamos aviso
		ventana.setEstado(cmd.comprobarEstado());
	}
	
	//Metodo para crear los ficheros .bat
	private void crearBat() {
		Fichero.escribir("start.bat");
		Fichero.escribir("stop.bat");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Cuando se pulsa el boton iniciar servicios
		if(e.getSource().equals(btnStart)) {
			cmd.exec(System.getProperty("java.io.tmpdir") + "/start.bat");
			try {
				Thread.sleep(8000);
			} catch (Exception  ex) {
				ex.printStackTrace();
			}
			estado();
		}
		//Cuando se pulsa el boton parar servicios
		if(e.getSource().equals(btnStop)) {
			cmd.exec(System.getProperty("java.io.tmpdir") + "/stop.bat");
			try {
				Thread.sleep(4000);
			} catch (Exception  ex) {
				ex.printStackTrace();
			}
			estado();
		}
		
	}
	
}
