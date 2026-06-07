package expendedor.gui;

import expendedor.gui.excepcionesgráficas.ProductoNoSeleccionadoException;

import javax.swing.*;

/**
 * Botón buy del expendedor
 */
public class BotonBuy extends JButton {
    public BotonBuy(PanelPago panelPago){
        super("Buy");
        this.addActionListener(_ -> {
            try{
                panelPago.comprar();
            } catch(ProductoNoSeleccionadoException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}