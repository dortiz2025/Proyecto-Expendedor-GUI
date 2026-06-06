package expendedor.gui;

import expendedor.logica.Comprador;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private Comprador comprador;

    public PanelComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
