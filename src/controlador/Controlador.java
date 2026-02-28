package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import modelo.ServicioManager;
import vista.Ventana;

/**
 * @version 2.0
 */

public class Controlador implements ActionListener {
	//Atributos
	private Ventana ventana;
	private ServicioManager servicioManager;
	private JButton btnStart;
	private JButton btnStop;
	
	//Constructor
	public Controlador() {
		ventana = new Ventana();
		servicioManager = new ServicioManager();
		
		//Obtencion de elementos en referencias
		btnStart = ventana.getBtnStart();
		btnStop = ventana.getBtnStop();
		
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
		ventana.setEstado(servicioManager.comprobarEstado());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Cuando se pulsa el boton iniciar servicios
		if(e.getSource().equals(btnStart)) {
			servicioManager.iniciarServicios();

			try {
				Thread.sleep(8000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			estado();
		}
		//Cuando se pulsa el boton parar servicios
		if(e.getSource().equals(btnStop)) {
			servicioManager.detenerServicios();
			try {
				Thread.sleep(4000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			estado();
		}
	}
}
