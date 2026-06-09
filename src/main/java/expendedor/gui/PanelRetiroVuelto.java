package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Sub-panel de PanelVuelto.
 * Representa el área interactiva de donde el comprador recoge las monedas de cambio.
 */
public class PanelRetiroVuelto extends JPanel {
    private Expendedor expendedor;

    /**
     * Configura el área de recolección de vuelto y define la acción de retiro.
     * @param expendedor Referencia al expendedor lógico.
     * @param comprador Referencia al comprador lógico receptor del vuelto.
     */
    public PanelRetiroVuelto(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setToolTipText("");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Moneda monedaVuelto = expendedor.retirarMoneda();

                if (monedaVuelto != null) {
                    System.out.println("Retiraste una moneda de: $" + monedaVuelto.getValor());
                    comprador.recibirMoneda(monedaVuelto);

                    if (SwingUtilities.getWindowAncestor(PanelRetiroVuelto.this) != null) {
                        SwingUtilities.getWindowAncestor(PanelRetiroVuelto.this).repaint();
                    }
                } else {
                    System.out.println("No queda vuelto por retirar.");
                }
            }
        });
    }

    /**
     * Dibuja la moneda disponible para retirar en la bandeja de cambio.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Moneda moneda = this.expendedor.getDepRetiroVuelto();

        if (moneda != null) {
            String nombre = moneda.getClass().getSimpleName();
            Image textura = GestorTexturas.getInstancia().getTextura(nombre);

            int size = 16;
            int xCentro = (this.getWidth() - 5) / 2;
            int yCentro = (this.getHeight() + size) / 2;

            if (textura != null) {
                g.drawImage(textura, xCentro, yCentro, size, size, this);
            } else {
                g.setColor(Color.YELLOW);
                g.fillOval(xCentro, yCentro, size, size);
                g.setColor(Color.BLACK);
                g.drawOval(xCentro, yCentro, size, size);
            }
        }
    }

    /**
     * Muestra el número de serie de la moneda que está lista para ser retirada.
     * @param e Evento del ratón.
     * @return El número de serie de la moneda, o null si la bandeja está vacía.
     */
    @Override
    public String getToolTipText(MouseEvent e) {
        Moneda moneda = this.expendedor.getDepRetiroVuelto();

        if (moneda != null) {
            return "Serie: " + moneda.getSerie();
        }

        return null;
    }
}