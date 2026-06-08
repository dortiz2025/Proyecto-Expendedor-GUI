package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Deposito;
import expendedor.logica.Expendedor;
import expendedor.logica.excepciones.PagoIncorrectoException;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;

/**
 * Panel que representa el monedero del comprador.
 */
public class PanelMonedero extends JPanel {
    private Comprador comprador;

    public PanelMonedero(Comprador comprador) {
        this.comprador = comprador;//Recibe referencia del comprador
    }
}
