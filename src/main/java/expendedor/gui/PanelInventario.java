package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.productos.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelInventario extends JPanel {
    private Comprador comprador;

    public PanelInventario(Comprador comprador) {
        this.comprador = comprador;
    }
}
