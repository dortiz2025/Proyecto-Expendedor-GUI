package expendedor.gui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;

/**
 * Clase que permite gestionar las texturas.
 * De esta manera no se cargan más de una vez
 * durante la ejecución del programa.
 */
public class GestorTexturas {
    private static GestorTexturas instancia; //Única instancia
    private HashMap<String, Image> texturas; //Texturas cargadas

    //Se cargan las texturas y se guardan con un nombre asociado.
    private GestorTexturas() {
        texturas = new HashMap<>();

        //Cargamos las texturas...
        cargarTextura("CocaCola");
        cargarTextura("Fanta");
        cargarTextura("Sprite");
        cargarTextura("Super8");
        cargarTextura("Oreo");
        cargarTextura("Snickers");
        //Aquí se pueden añadir más luego (sprites de monedas, etc.)
    }

    /**
     * Metodo global para poder tener acceso a las texturas.
     * @return Referencia de la única instancia.
     */
    public static GestorTexturas getInstancia() {
        if (instancia == null) {
            instancia = new GestorTexturas();
        }
        return instancia;
    }

    // Metodo interno que carga cada imagen y la añade al hashmap.
    private void cargarTextura(String nombre) {
        try {
            Image img = new ImageIcon(getClass().getResource("/texturas/" + nombre + ".png")).getImage();
            texturas.put(nombre, img);
        } catch (Exception e) {
            System.err.println("No se encontró la textura /texturas/" + nombre + ".png");
        }
    }

    /**
     * Permite acceder a una textura pre-cargada en la ram con su nombre.
     * @param nombre nombre de la textura.
     * @return textura de tipo Image solicitada.
     */
    public Image getTextura(String nombre) {
        return texturas.get(nombre);
    }
}