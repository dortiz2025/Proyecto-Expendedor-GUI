package expendedor.gui;

import expendedor.logica.productos.TipoProducto;
import javax.swing.*;

/**
 * Botón Selector para los productos del expendedor.
 */
public class BotonSelector extends JButton {
    /**
     * Se crea el botón.
     * @param panelPago Referencia para poder manejar el evento de presionar el botón.
     * @param tipo Para el nombre del botón.
     */
    public BotonSelector(PanelPago panelPago, TipoProducto tipo) {
        super(tipo.toString());
        this.addActionListener(_ -> panelPago.seleccionarProducto(tipo));
    }
}