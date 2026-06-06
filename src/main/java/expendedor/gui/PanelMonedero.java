package expendedor.gui;

import expendedor.logica.Comprador;

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
