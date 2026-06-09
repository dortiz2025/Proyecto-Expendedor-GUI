package expendedor.gui;

import expendedor.logica.productos.TipoProducto;
import javax.swing.*;
import java.awt.*;

/**
 * Botón Selector para los productos del expendedor.
 * Se comporta como una "hotspot" transparente sobre el arte de fondo.
 */
public class BotonSelector extends JButton {
    private PanelPago panelPago;
    private TipoProducto tipo;

    /**
     * Se crea el botón vacío y transparente.
     * @param panelPago Referencia para poder manejar el evento de presionar el botón.
     * @param tipo Tipo de producto asociado a este botón.
     */
    public BotonSelector(PanelPago panelPago, TipoProducto tipo) {
        super();

        this.panelPago = panelPago;
        this.tipo = tipo;

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        this.addActionListener(e -> {
            panelPago.seleccionarProducto(tipo);
            if (SwingUtilities.getWindowAncestor(this) != null) {
                SwingUtilities.getWindowAncestor(this).repaint();
            }
        });
    }

    /**
     * Dibuja EXCLUSIVAMENTE el marco de selección brillante si está activo.
     * @param g Entorno gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Si este botón es el que está seleccionado en PanelPago, lo resaltamos
        if (this.panelPago.getTipoProducto() == this.tipo) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE); // Marco brillante estilo neón
            g2d.setStroke(new BasicStroke(2)); // Grosor de 2 píxeles

            // Dibujamos el rectángulo justo en el borde del componente
            g2d.drawRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        }
    }
}