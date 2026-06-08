package expendedor.gui;

import expendedor.logica.Expendedor;
import expendedor.logica.productos.Producto;
import expendedor.logica.Comprador;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelExpendedor.
 * Clase que dibuja el producto comprado y
 * lo rota para mayor dinamismo en el programa.
 */
public class PanelProductoComprado extends JPanel {
    private Expendedor expendedor;

    /**
     * Guarda la referencia del expendedor y
     * define fondo y borde.
     * @param expendedor Referencia del expendedor.
     */
    public PanelProductoComprado(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
            //Se recibe la textura
            String nombre = producto.getClass().getSimpleName();
            Image textura = GestorTexturas.getInstancia().getTextura(nombre);

            //---------Dibujo del producto rotado---------//
            //Ambos parámetros 16 y 32 se pueden cambiar según los sprites.
            //Aquí se usan para definir el centro y dibujar bien.
            int xCentro = (this.getWidth() - 16) / 2;
            int yCentro = (this.getHeight() - 32) / 2;

            //Usamos un entorno gráfico avanzado de java para rotar imágenes
            Graphics2D g2d = (Graphics2D) g;
            //X e Y son el eje de rotación (en este caso el centro de la imágen)
            java.awt.geom.AffineTransform estadoOriginal = g2d.getTransform();
            g2d.rotate(Math.toRadians(90), xCentro + 8, yCentro + 16);
            g2d.drawImage(textura, xCentro, yCentro, this);
            g2d.setTransform(estadoOriginal);;
        }
    }

}
