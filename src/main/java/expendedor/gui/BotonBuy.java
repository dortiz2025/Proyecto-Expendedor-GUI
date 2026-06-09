package expendedor.gui;

import expendedor.gui.excepcionesgraficas.ProductoNoSeleccionadoException;
import expendedor.logica.excepciones.ProductoSinRetirarException;

import javax.swing.*;

/**
 * Botón de compra del expendedor.
 */
public class BotonBuy extends JButton {
    /**
     * Inicializa el botón y maneja los eventos de compra.
     * @param panelPago Referencia del panel de pago para procesar transacciones.
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