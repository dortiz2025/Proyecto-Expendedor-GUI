package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private Comprador comprador;

    private PanelMonedero panelMonedero;
    private PanelInventario panelInventario;

    public PanelComprador(Comprador comprador, Expendedor expendedor) {
        this.comprador = comprador;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
