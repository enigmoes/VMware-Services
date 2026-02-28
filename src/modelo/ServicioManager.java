package modelo;

import com.sun.jna.platform.win32.W32Service;
import com.sun.jna.platform.win32.W32ServiceManager;
import com.sun.jna.platform.win32.Winsvc;

/**
 * Gestión de servicios VMware mediante JNA (Java Native Access).
 *
 * @version 2.0
 */
public class ServicioManager {

    private static final String[] SERVICIOS = {
        "VMAuthdService",
        "VMnetDHCP",
        "VMware NAT Service",
        "VMUSBArbService"
    };

    /**
     * Inicia todos los servicios VMware si están detenidos.
     */
    public void iniciarServicios() {
        W32ServiceManager scm = new W32ServiceManager();
        scm.open(Winsvc.SC_MANAGER_ALL_ACCESS);

        try {
            for (String nombre : SERVICIOS) {
                W32Service servicio = scm.openService(nombre, Winsvc.SERVICE_ALL_ACCESS);

                try {
                    int estado = servicio.queryStatus().dwCurrentState;
                    if (estado == Winsvc.SERVICE_STOPPED || estado == Winsvc.SERVICE_STOP_PENDING) {
                        servicio.startService();
                    }
                } catch (Exception e) {
                    System.err.println("Error al iniciar servicio '" + nombre + "': " + e.getMessage());
                } finally {
                    servicio.close();
                }
            }
        } finally {
            scm.close();
        }
    }

    /**
     * Detiene todos los servicios VMware si están en ejecución.
     */
    public void detenerServicios() {
        W32ServiceManager scm = new W32ServiceManager();
        scm.open(Winsvc.SC_MANAGER_ALL_ACCESS);

        try {
            for (String nombre : SERVICIOS) {
                W32Service servicio = scm.openService(nombre, Winsvc.SERVICE_ALL_ACCESS);
                
                try {
                    int estado = servicio.queryStatus().dwCurrentState;
                    if (estado == Winsvc.SERVICE_RUNNING || estado == Winsvc.SERVICE_START_PENDING) {
                        servicio.stopService();
                    }
                } catch (Exception e) {
                    System.err.println("Error al detener servicio '" + nombre + "': " + e.getMessage());
                } finally {
                    servicio.close();
                }
            }
        } finally {
            scm.close();
        }
    }

    /**
     * Comprueba el estado global de los servicios VMware.
     *
     * @return 1 si todos están en ejecución, 0 si todos están detenidos, -1 si hay estado mixto.
     */
    public int comprobarEstado() {
        int iniciados = 0;
        int detenidos = 0;
        W32ServiceManager scm = new W32ServiceManager();
        scm.open(Winsvc.SC_MANAGER_CONNECT | Winsvc.SC_MANAGER_ENUMERATE_SERVICE);

        try {
            for (String nombre : SERVICIOS) {
                W32Service servicio = scm.openService(nombre, Winsvc.SERVICE_QUERY_STATUS);

                try {
                    int estado = servicio.queryStatus().dwCurrentState;

                    if (estado == Winsvc.SERVICE_RUNNING) {
                        iniciados++;
                    } else if (estado == Winsvc.SERVICE_STOPPED) {
                        detenidos++;
                    }
                } catch (Exception e) {
                    System.err.println("Error al consultar servicio '" + nombre + "': " + e.getMessage());
                } finally {
                    servicio.close();
                }
            }
        } finally {
            scm.close();
        }

        if (iniciados == SERVICIOS.length) return 1;
        if (detenidos == SERVICIOS.length) return 0;

        return -1; // estado mixto (algunos iniciados, otros detenidos)
    }

    /**
     * Comprueba el estado de cada servicio VMware de forma individual.
     *
     * @return array de enteros: 1 = RUNNING, 0 = STOPPED, -1 = transitorio/error
     */
    public int[] comprobarEstados() {
        int[] estados = new int[SERVICIOS.length];
        W32ServiceManager scm = new W32ServiceManager();
        scm.open(Winsvc.SC_MANAGER_CONNECT | Winsvc.SC_MANAGER_ENUMERATE_SERVICE);

        try {
            for (int i = 0; i < SERVICIOS.length; i++) {
                W32Service servicio = scm.openService(SERVICIOS[i], Winsvc.SERVICE_QUERY_STATUS);

                try {
                    int estado = servicio.queryStatus().dwCurrentState;

                    if (estado == Winsvc.SERVICE_RUNNING) {
                        estados[i] = 1;
                    } else if (estado == Winsvc.SERVICE_STOPPED) {
                        estados[i] = 0;
                    } else {
                        estados[i] = -1; // transitorio
                    }
                } catch (Exception e) {
                    estados[i] = -1;
                    System.err.println("Error al consultar '" + SERVICIOS[i] + "': " + e.getMessage());
                } finally {
                    servicio.close();
                }
            }
        } finally {
            scm.close();
        }

        return estados;
    }

    /**
     * Comprueba el estado de un unico servicio por indice.
     *
     * @param index indice en el array SERVICIOS
     * @return 1 = RUNNING, 0 = STOPPED, -1 = transitorio/error
     */
    public int comprobarEstado(int index) {
        W32ServiceManager scm = new W32ServiceManager();
        scm.open(Winsvc.SC_MANAGER_CONNECT | Winsvc.SC_MANAGER_ENUMERATE_SERVICE);

        try {
            W32Service servicio = scm.openService(SERVICIOS[index], Winsvc.SERVICE_QUERY_STATUS);

            try {
                int estado = servicio.queryStatus().dwCurrentState;

                if (estado == Winsvc.SERVICE_RUNNING) return 1;
                if (estado == Winsvc.SERVICE_STOPPED) return 0;

                return -1;
            } catch (Exception e) {
                return -1;
            } finally {
                servicio.close();
            }
        } finally {
            scm.close();
        }
    }

    public int getServiciosCount() {
        return SERVICIOS.length;
    }
}
