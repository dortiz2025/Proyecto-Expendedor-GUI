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
        this.setOpaque(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (comprador.getInventario() != null){
                    System.out.println("Hiciste click en el inventario");
                    comprador.consumirProducto();
                    repaint();
                }
            }
        });

        this.setBackground(Color.LIGHT_GRAY);
    }
}
