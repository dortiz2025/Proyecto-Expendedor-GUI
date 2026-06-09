package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Sub-Panel de PanelVuelto
 * Clase que dibuja un mini-deposito para retirar el vuelto.
 */
public class PanelRetiroVuelto extends JPanel {
    private Expendedor expendedor;

    /**
     * Se guarda la referencia del expendedor
     * y se definen transparencia y fondo.
     * @param expendedor Referencia del expendedor.
     * @param comprador Referencia del comprador.
     */
    public PanelRetiroVuelto(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.setOpaque(false);

        // El clic pertenece a este cuadrito específico
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Usamos el método oficial de la lógica para respetar la cascada
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Dibujamos el depósito RetiroVuelto
        g.setColor(new Color(141, 174, 198));
        g.fillRect((getWidth()-18)/2, 51, 18, 18);
        g.setColor(Color.BLACK);
        g.drawRect((getWidth()-18)/2, 51, 18, 18);

        //Dibujamos la moneda si es que hay una atascada en la salida
        Moneda moneda = this.expendedor.getDepRetiroVuelto();

        if (moneda != null) {
            String nombre = moneda.getClass().getSimpleName();
            Image textura = GestorTexturas.getInstancia().getTextura(nombre);

            int size = 16; //Tamaño de la moneda
            int xCentro = (this.getWidth() - size) / 2;
            int yCentro = (this.getHeight() - size) / 2;

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
}