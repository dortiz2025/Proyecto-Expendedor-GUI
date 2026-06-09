package expendedor.gui;

import expendedor.logica.Deposito;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelVitrina.
 * Clase que dibuja los depósitos de productos de forma genérica.
 * @param <T> Tipo de depósito genérico.
 */
public class PanelDepositoProducto<T> extends JPanel {
    private final Deposito<T> deposito;
    private final Image textura;

    /**
     * Inicializa los datos necesarios para renderizar el depósito.
     * @param deposito Referencia del depósito.
     * @param textura Textura cargada del producto.
     */
    public PanelDepositoProducto(Deposito<T> deposito, Image textura) {
        this.deposito = deposito;
        this.textura = textura;
        this.setOpaque(false);
    }

    /**
     * Dibuja los productos disponibles en el expendedor.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.textura != null && this.deposito != null) {
            int cantidad = this.deposito.size();
            for (int i = 0; i < cantidad; i++) {
                g.drawImage(this.textura, 5 + (i * 10), 5, this);
            }
        }
    }
}