package expendedor.gui;

import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa a la vitrina visual del expendedor.
 * Se encarga de dibujar ordenadamente los productos.
 */
public class PanelVitrina extends JPanel {

    /**
     * Se cargan las texturas de los productos y se añaden los depósitos.
     * @param expendedor Referencia del expendedor para poder dibujar
     * la cantidad exacta de productos.
     */
    public PanelVitrina(Expendedor expendedor) {
        this.setOpaque(false);

        // Cambiado a 6 filas y 1 columna
        int separacionVertical = 0;
        this.setLayout(new GridLayout(6, 1, 0, separacionVertical));

        int margenSuperior = 0;
        int margenIzquierdo = 5;
        int margenInferior = 5;
        int margenDerecho = 5;
        this.setBorder(BorderFactory.createEmptyBorder(margenSuperior, margenIzquierdo, margenInferior, margenDerecho));

        this.add(new PanelDepositoProducto<>(expendedor.getDepCoca(), this.getTextura("CocaCola")));
        this.add(new PanelDepositoProducto<>(expendedor.getDepFanta(), this.getTextura("Fanta")));
        this.add(new PanelDepositoProducto<>(expendedor.getDepSprite(), this.getTextura("Sprite")));
        this.add(new PanelDepositoProducto<>(expendedor.getDepSuper8(), this.getTextura("Super8")));
        this.add(new PanelDepositoProducto<>(expendedor.getDepOreo(), this.getTextura("Oreo")));
        this.add(new PanelDepositoProducto<>(expendedor.getDepSnickers(), this.getTextura("Snickers")));
    }

    /**
     * Dibuja los componentes del panel.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private Image getTextura(String nombre){
        return GestorTexturas.getInstancia().getTextura(nombre);
    }
}