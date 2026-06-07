package expendedor.gui;

import expendedor.logica.Expendedor;
import expendedor.logica.productos.Bebida;

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
     *                  la cantidad exacta de productos.
     */
    public PanelVitrina(Expendedor expendedor) {
        this.setOpaque(false);
        this.setLayout(new GridLayout(3, 2, 5, 5));

        //Cargamos las texturas
        Image imgCoca = cargarImagen("/texturas/CocaCola.png");
        Image imgFanta = cargarImagen("/texturas/Fanta.png");
        Image imgSprite = cargarImagen("/texturas/Sprite.png");
        Image imgSuper8 = cargarImagen("/texturas/Super8.png");
        Image imgOreo = cargarImagen("/texturas/Oreo.png");
        Image imgSnickers = cargarImagen("/texturas/Snickers.png");

        //Añadimos depósitos al panel
        this.add(new PanelDeposito<>(expendedor.getDepCoca(), imgCoca));
        this.add(new PanelDeposito<>(expendedor.getDepFanta(), imgFanta));
        this.add(new PanelDeposito<>(expendedor.getDepSprite(), imgSprite));
        this.add(new PanelDeposito<>(expendedor.getDepSuper8(), imgSuper8));
        this.add(new PanelDeposito<>(expendedor.getDepOreo(), imgOreo));
        this.add(new PanelDeposito<>(expendedor.getDepSnickers(), imgSnickers));
    }

    /**
     * Metodo auxiliar para cargar imágenes.
     */
    private Image cargarImagen(String ruta) {
        try {
            return new ImageIcon(getClass().getResource(ruta)).getImage();
        } catch (Exception e) {
            System.out.println("No se encontró la imagen: " + ruta);
            return null;
        }
    }
}
