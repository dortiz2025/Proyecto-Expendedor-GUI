package expendedor.gui;

import expendedor.logica.Expendedor;
import expendedor.logica.productos.Producto;
import expendedor.logica.Comprador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Sub-panel de PanelExpendedor.
 * Clase que dibuja el producto comprado y
 * lo rota para mayor dinamismo en el programa.
 */
public class PanelProductoComprado extends JPanel {
    private Expendedor expendedor;
    private Comprador comprador;

    /**
     * Guarda la referencia del expendedor y
     * define fondo y borde.
     * @param expendedor Referencia del expendedor.
     */
    public PanelProductoComprado(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.comprador = comprador;

        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Producto productoRecibido = expendedor.getProductoComprado();

                if (productoRecibido != null) {
                    if (comprador.getInventario() == null) {
                        comprador.recibirProducto(productoRecibido);
                        expendedor.retirarProductoComprado();

                        if (SwingUtilities.getWindowAncestor(PanelProductoComprado.this) != null) {
                            SwingUtilities.getWindowAncestor(PanelProductoComprado.this).repaint();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes consumir el producto en tu inventario antes de recoger otro.", "Inventario Lleno", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    System.out.println("No hay producto para retirar.");
                }
            }
        });
    }

    /**
     * Dibuja el producto que ha sido comprado.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Producto producto = this.expendedor.getProductoComprado();

        if (producto != null) {
            String nombre = producto.getClass().getSimpleName();
            Image textura = GestorTexturas.getInstancia().getTextura(nombre);

            int xCentro = (this.getWidth() - 16) / 2;
            int yCentro = (this.getHeight() - 32) / 2;

            Graphics2D g2d = (Graphics2D) g;
            java.awt.geom.AffineTransform estadoOriginal = g2d.getTransform();
            g2d.rotate(Math.toRadians(90), xCentro + 8, yCentro + 16);
            g2d.drawImage(textura, xCentro, yCentro, this);
            g2d.setTransform(estadoOriginal);
        }
    }
}