package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.productos.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel que representa el inventario del comprador.
 * Muestra el producto que ha sido retirado de la máquina.
 */
public class PanelInventario extends JPanel {
    private Comprador comprador;

    /**
     * Inicializa el panel del inventario.
     * @param comprador Referencia al comprador lógico.
     */
    public PanelInventario(Comprador comprador) {
        this.comprador = comprador;
        this.setOpaque(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (comprador.getInventario() != null) {
                    comprador.consumirProducto();
                    repaint();
                }
            }
        });
    }

    /**
     * Dibuja el inventario y el producto si existe.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Producto inventario = comprador.getInventario();

        if (inventario != null) {
            String nombre = inventario.getClass().getSimpleName();
            Image textura = GestorTexturas.getInstancia().getTextura(nombre);

            if (textura != null) {
                int xCentro = (this.getWidth() - 16) / 2;
                int yCentro = (this.getHeight() - 32) / 2;
                g.drawImage(textura, xCentro, yCentro + 10, this);
            }
        }
    }
}