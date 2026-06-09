package expendedor.gui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

/**
 * Singleton encargado de gestionar la carga y almacenamiento de texturas.
 * Previene la recarga múltiple de recursos en tiempo de ejecución.
 */
public class GestorTexturas {
    private static GestorTexturas instancia;
    private final HashMap<String, Image> texturas;

    private GestorTexturas() {
        texturas = new HashMap<>();

        cargarTextura("maquina");
        cargarTextura("maquina_select");
        cargarTextura("CocaCola");
        cargarTextura("Fanta");
        cargarTextura("Sprite");
        cargarTextura("Super8");
        cargarTextura("Oreo");
        cargarTextura("Snickers");
        cargarTextura("Moneda1500");
        cargarTextura("Moneda1000");
        cargarTextura("Moneda500");
        cargarTextura("Moneda100");
    }

    /**
     * Obtiene la única instancia del gestor.
     * @return Referencia a GestorTexturas.
     */
    public static GestorTexturas getInstancia() {
        if (instancia == null) {
            instancia = new GestorTexturas();
        }
        return instancia;
    }

    /**
     * Carga una textura desde los recursos y la asocia a un identificador.
     * @param nombre Nombre de la textura a cargar.
     */
    private void cargarTextura(String nombre) {
        URL url = getClass().getResource("/texturas/" + nombre + ".png");

        if (url != null) {
            Image img = new ImageIcon(url).getImage();
            texturas.put(nombre, img);
        } else {
            System.err.println("No se encontró la textura /texturas/" + nombre + ".png");
        }
    }

    /**
     * Devuelve una textura previamente almacenada.
     * @param nombre Clave de la textura.
     * @return Objeto Image correspondiente, o null si no se encuentra.
     */
    public Image getTextura(String nombre) {
        return texturas.get(nombre);
    }
}