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
public class PanelDepositoProducto<T> extends JPanel {
    private final Deposito<T> deposito; //Deposito genérico
    private final Image textura;//Guarda la textura para no cargarla de nuevo

    /**
     * Se guardan datos necesarios para poder dibujar el depósito.
     * @param deposito Referencia del depósito.
     * @param textura Textura cargada del producto.
     */
    public PanelDepositoProducto(Deposito<T> deposito, Image textura) {
        this.deposito = deposito;
        this.textura = textura;
        this.setBackground(new Color(230,245,255,200));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Actualiza la cantidad de productos que hay disponibles en el expendedor.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Se dibujan los productos
        if (this.textura != null && this.deposito != null) {
            int cantidad = this.deposito.size();
            for (int i = 0; i < cantidad; i++) {
                //Se dibuja cada producto encima de otro con posiciones diferentes.
                g.drawImage(this.textura, 5 + (i * 10), 5, this);
            }
        }
    }
}