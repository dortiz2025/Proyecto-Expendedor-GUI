package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.productos.Bebida;
import expendedor.logica.productos.Dulce;
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
     * Reproduce el efecto de sonido adecuado según el tipo de producto.
     * - Bebida  → sonido de trago/líquido ("bebida")
     * - Dulce   → sonido de mordisco/crujido ("dulce")
     * Si el producto no es ninguno de los dos tipos conocidos, no reproduce nada.
     *
     * @param producto Producto que está a punto de consumirse.
     */
    private void reproducirSonidoConsumo(Producto producto) {
        GestorSonidos gestor = GestorSonidos.getInstancia();

        if (producto instanceof Bebida) {
            gestor.reproducir("beber");
        } else if (producto instanceof Dulce) {
            gestor.reproducir("masticar");
        }
    }

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
                Producto productoEnInventario = comprador.getInventario();
                if (comprador.getInventario() != null) {
                    reproducirSonidoConsumo(productoEnInventario);
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