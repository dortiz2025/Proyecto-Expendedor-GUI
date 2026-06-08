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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 99, 99);
        g.drawString("Inventario: ", 10, 20);

        Producto miInventario = comprador.getInventario();

        if (miInventario != null){
            g.setColor(Color.GRAY);
            g.fillRect(25,40,50,76);

            g.setColor(Color.BLACK);
            g.drawString("Producto", 30, 80);
        }


        }
}
