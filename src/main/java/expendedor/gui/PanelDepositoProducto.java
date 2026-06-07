package expendedor.gui;

import expendedor.logica.Deposito;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelVitrina.
 * Clase que permite reutilizar código al dibujar
 * los depósitos de productos.
 * @param <T> Tipo de depósito genérico.
 */
public class PanelDeposito<T> extends JPanel {
    private Deposito<T> deposito; //Deposito genérico
    private Image textura;//Guarda la textura para no cargarla de nuevo

    /**
     * Se guardan datos necesarios para poder dibujar el depósito.
     * @param deposito Referencia del depósito.
     * @param textura Textura cargada del producto.
     */
    public PanelDeposito(Deposito<T> deposito, Image textura) {
        this.deposito = deposito;
        this.textura = textura;
    }

    /**
     * Actualiza la cantidad de productos que hay disponibles en el expendedor.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Se dibuja el fondo
        g.setColor(new Color(230, 245, 255, 200));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Se dibuja un borde
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1); // Borde negro

        //Se dibujan los productos
        if (textura != null && deposito != null) {
            int cantidad = deposito.size();
            for (int i = 0; i < cantidad; i++) {
                //Se dibuja cada producto encima de otro con posiciones diferentes.
                g.drawImage(textura, 5 + (i * 10), 5, this);
            }
        }
    }
}