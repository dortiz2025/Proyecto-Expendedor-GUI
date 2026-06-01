package expendedor.logica.productos.dulce;

import expendedor.logica.productos.Producto;

/**
 * Clase abstracta que representa un Producto de tipo Dulce.
 * Es la base para los dulces que hay en la expendedora
 */
public abstract class Dulce extends Producto {
    /**
     * Constructor del dulce.
     * Asigna identificador del Dulce
     *
     * @param serie Identificador del Dulce
     */
    public Dulce(int serie) {
        super(serie);
    }
}