package expendedor.gui;

import expendedor.logica.productos.TipoProducto;
import javax.swing.*;
import java.awt.*;

/**
 * Botón Selector para los productos del expendedor.
 * Funciona como un área interactiva transparente sobre el diseño de fondo.
 */
public class BotonSelector extends JButton {
    private PanelPago panelPago;
    private TipoProducto tipo;

    /**
     * Inicializa el botón y configura el evento de selección de producto.
     * @param panelPago Referencia para notificar el evento de selección.
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
     * Dibuja un marco de selección si el botón está activo.
     * @param g Entorno gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.panelPago.getTipoProducto() == this.tipo) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        }
    }
}