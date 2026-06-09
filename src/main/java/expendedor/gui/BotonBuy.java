package expendedor.gui;

import expendedor.gui.excepcionesgraficas.ProductoNoSeleccionadoException;
import expendedor.logica.excepciones.ProductoSinRetirarException;

import javax.swing.*;

/**
 * Botón buy del expendedor
 */
public class BotonBuy extends JButton {
    /**
     * Se crea el botón.
     * @param panelPago Referencia del panel para enviar eventos.
     */
    public BotonBuy(PanelPago panelPago){
        super("");
        this.addActionListener(_ -> {
            try{
                panelPago.comprar();
            } catch(ProductoNoSeleccionadoException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch(ProductoSinRetirarException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Retirar producto del contenedor antes de volver a comprar.", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}