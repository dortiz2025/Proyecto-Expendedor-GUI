package expendedor.gui;

import expendedor.logica.Deposito;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Sub-panel de PanelDepositoGanancias y PanelVuelto.
 * Clase que dibuja un depósito de monedas.
 */
public class PanelDepositoMoneda extends JPanel {
    private final Deposito<Moneda> deposito;

    /**
     * Inicializa el panel, asocia el depósito y configura el ToolTip.
     * @param deposito Referencia del depósito.
     */
    public PanelDepositoMoneda(Deposito<Moneda> deposito) {
        this.deposito = deposito;
        this.setOpaque(false);
        this.setToolTipText("");
    }

    /**
     * Dibuja la pila de monedas del depósito hasta un máximo de 10 unidades.
     * @param g Entorno gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (deposito != null) {
            int cantidad = Math.min(deposito.size(), 10);
            int size = 16;
            int xCentro = this.getWidth() / 2;

            for (int i = 0; i < cantidad; i++) {
                Moneda m = deposito.getItem(i);

                if (m != null) {
                    String nombreTextura = m.getClass().getSimpleName();
                    Image textura = GestorTexturas.getInstancia().getTextura(nombreTextura);

                    int y = this.getHeight() - size - 2 - i;

                    if (textura != null) {
                        g.drawImage(textura, xCentro, y, size, size, this);
                    } else {
                        g.setColor(Color.GRAY);
                        g.fillOval(xCentro, y, size, size);
                        g.setColor(Color.BLACK);
                        g.drawOval(xCentro, y, size, size);
                    }
                }
            }
        }
    }

    /**
     * Devuelve la serie de la moneda en la posición indicada por el evento del ratón.
     * @param e Evento del ratón.
     * @return Serie de la moneda seleccionada, o null si no se encuentra.
     */
    @Override
    public String getToolTipText(MouseEvent e) {
        if (deposito == null || deposito.size() == 0) return null;

        int cantidad = Math.min(deposito.size(), 10);
        int size = 16;
        int xCentro = (this.getWidth() - size) / 2;

        if (e.getX() >= xCentro && e.getX() <= xCentro + size) {
            for (int i = 0; i < cantidad; i++) {
                int y = this.getHeight() - size - 2 - i;
                if (e.getY() >= y && e.getY() <= y + size) {
                    Moneda m = deposito.getItem(i);
                    if (m != null) return "Serie: " + m.getSerie();
                }
            }
        }
        return null;
    }
}