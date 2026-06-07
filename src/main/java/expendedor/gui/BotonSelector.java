package expendedor.gui;

import expendedor.logica.productos.TipoProducto;
import javax.swing.*;

/**
 * Botón Selector para los productos del expendedor.
 */
public class BotonSelector extends JButton {
    public BotonSelector(PanelPago panelPago, TipoProducto tipo) {
        super(tipo.toString());
        this.addActionListener(_ -> panelPago.seleccionarProducto(tipo));
    }
}