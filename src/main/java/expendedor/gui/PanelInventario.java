package expendedor.gui;

import expendedor.logica.Comprador;

import javax.swing.*;

public class PanelInventario extends JPanel {
    private Comprador comprador;

    public PanelInventario(Comprador comprador) {
        this.comprador = comprador;
    }
}
