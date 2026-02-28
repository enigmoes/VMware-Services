package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra el estado individual de cada servicio VMware
 * mediante un indicador circular (●) coloreado.
 *
 * @version 2.0
 */
public class PanelServicios extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String[] NOMBRES = {
        "VMAuthdService",
        "VMnetDHCP",
        "VMware NAT Service",
        "VMUSBArbService"
    };

    private static final Color COLOR_RUNNING  = new Color(76, 175, 80);   // verde
    private static final Color COLOR_STOPPED  = new Color(244, 67, 54);   // rojo
    private static final Color COLOR_PENDING  = new Color(255, 193, 7);   // amarillo

    private final JLabel[] indicadores;

    public PanelServicios() {
        setLayout(new GridLayout(NOMBRES.length, 1, 0, 6));
        setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        indicadores = new JLabel[NOMBRES.length];

        for (int i = 0; i < NOMBRES.length; i++) {
            JPanel fila = new JPanel(new BorderLayout(10, 0));
            fila.setOpaque(false);

            JLabel nombre = new JLabel(NOMBRES[i]);
            nombre.setFont(new Font("Arial", Font.PLAIN, 13));

            indicadores[i] = new JLabel("●");
            indicadores[i].setFont(new Font("Arial", Font.BOLD, 18));
            indicadores[i].setForeground(COLOR_STOPPED);
            indicadores[i].setHorizontalAlignment(SwingConstants.RIGHT);

            fila.add(nombre, BorderLayout.WEST);
            fila.add(indicadores[i], BorderLayout.EAST);

            add(fila);
        }
    }

    /**
     * Actualiza el color de cada indicador según su estado.
     * @param estados array: 1 = RUNNING, 0 = STOPPED, -1 = transitorio
     */
    public void setEstados(int[] estados) {
        for (int i = 0; i < indicadores.length; i++) {
            indicadores[i].setForeground(switch (estados[i]) {
                case 1  -> COLOR_RUNNING;
                case 0  -> COLOR_STOPPED;
                default -> COLOR_PENDING;
            });
        }
    }
}
