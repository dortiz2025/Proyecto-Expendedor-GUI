package expendedor.gui;

import expendedor.logica.Deposito;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelDepositoGanancias y PanelVuelto.
 * Clase que dibuja un depósito de monedas.
 */
public class PanelDepositoMoneda extends JPanel {
    private final Deposito<Moneda> deposito;

    /**
     * Se guarda la referencia del depósito,
     * se activa ToolTip y se define el fondo.
     * @param deposito Referencia del depósito
     */
    public PanelDepositoMoneda(Deposito<Moneda> deposito) {
        this.deposito = deposito;
        this.setBackground(new Color(230,245,255,200));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Activamos la lectura del mouse para ToolTips
        this.setToolTipText("");
    }

    /**
     * Dibuja la pila de monedas del depósito hasta 10 como máximo.
     * @param g Entorno gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (deposito != null) {
            //Se imprimen un máximo de 10 monedas para no salirnos del espacio asignado.
            int cantidad = Math.min(deposito.size(), 10);

            //Diámetro de la moneda (sujeto a modificación según el sprite).
            int size = 16;
            int xCentro = (this.getWidth() - size) / 2;

            for (int i = 0; i < cantidad; i++) {
                Moneda m = deposito.getItem(i);

                if (m != null) {
                    //Traemos la textura cargada que necesitamos.
                    String nombreTextura = m.getClass().getSimpleName();
                    Image textura = GestorTexturas.getInstancia().getTextura(nombreTextura);

                    //Apilamos desde abajo hacia arriba
                    int y = this.getHeight() - size - 2 - i;

                    //Dibujamos la textura si no un círculo ._.
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
}