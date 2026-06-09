package expendedor.gui;

import expendedor.logica.productos.TipoProducto;
import javax.swing.*;
import java.awt.*;

/**
 * Botón Selector para los productos del expendedor.
 */
public class BotonSelector extends JButton {
    private PanelPago panelPago;
    private TipoProducto tipo;

    /**
     * Se crea el botón.
     * @param panelPago Referencia para poder manejar el evento de presionar el botón.
     * @param tipo Para el nombre del botón.
     */
    public BotonSelector(PanelPago panelPago, TipoProducto tipo) {
        super(tipo.toString());
        this.panelPago = panelPago;
        this.tipo = tipo;

        this.addActionListener(e -> {
            panelPago.seleccionarProducto(tipo);
            if (SwingUtilities.getWindowAncestor(this) != null) {
                SwingUtilities.getWindowAncestor(this).repaint();
            }
        });
    }

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