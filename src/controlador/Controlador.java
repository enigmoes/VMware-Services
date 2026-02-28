package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.util.List;

import modelo.ServicioManager;
import vista.Ventana;

/**
 * Controlador principal (ventana, eventos, botones y procesos)
 *
 * @version 2.0
 */
public class Controlador implements ActionListener {
	// Atributos
	private Ventana ventana;
	private JButton btn;

	private ServicioManager servicioManager;
	private SwingWorker<int[], int[]> workerActual;

	// Constructor
	public Controlador() {
		ventana = new Ventana();
		servicioManager = new ServicioManager();
		btn = ventana.getBtn();

		// Comprobar estado inicial y ajustar texto del boton
		actualizar();
		btn.addActionListener(this);
	}

	// Actualiza indicadores y texto del boton segun el estado global
	private void actualizar() {
		int[] estados = servicioManager.comprobarEstados();
		ventana.setEstados(estados);

		int global = servicioManager.comprobarEstado();
		ventana.setTextoBoton(global == 1 ? "Stop Services" : "Start Services");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Determina la accion segun el estado actual de los servicios
		boolean iniciando = servicioManager.comprobarEstado() != 1;
		ejecutarConFeedback(iniciando);
	}

	// Lanza la operacion en segundo plano con efecto escalonado en los indicadores
	private void ejecutarConFeedback(boolean iniciando) {
		if (workerActual != null && !workerActual.isDone()) {
			workerActual.cancel(true);
		}

		ventana.setBotonHabilitado(false);
		ventana.setTextoBoton(iniciando ? "Starting..." : "Stopping...");

		workerActual = new SwingWorker<int[], int[]>() {
			@Override
			protected int[] doInBackground() throws Exception {
				if (iniciando) {
					servicioManager.iniciarServicios();
				} else {
					servicioManager.detenerServicios();
				}

				int estadoEsperado = iniciando ? 1 : 0;
				long timeout = System.currentTimeMillis() + 15000;
				int[] estadosActuales = servicioManager.comprobarEstados();

				// Polling escalonado: consulta cada servicio por separado con 200ms de pausa
				while (!isCancelled() && System.currentTimeMillis() < timeout) {
					for (int i = 0; i < servicioManager.getServiciosCount() && !isCancelled(); i++) {
						estadosActuales[i] = servicioManager.comprobarEstado(i);
						publish(estadosActuales.clone());

						Thread.sleep(200);
					}

					if (servicioManager.comprobarEstado() == estadoEsperado) return estadosActuales;
				}

				return estadosActuales;
			}

			@Override
			protected void process(List<int[]> chunks) {
				// Pinta el ultimo snapshot publicado en el EDT
				ventana.setEstados(chunks.get(chunks.size() - 1));
			}

			@Override
			protected void done() {
				if (isCancelled()) return;
				try {
					ventana.setEstados(get());
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					ventana.setBotonHabilitado(true);

					int global = servicioManager.comprobarEstado();
					ventana.setTextoBoton(global == 1 ? "Stop Services" : "Start Services");
				}
			}
		};
		workerActual.execute();
	}
}
