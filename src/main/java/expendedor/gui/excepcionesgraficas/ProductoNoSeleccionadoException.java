package expendedor.gui.excepcionesgraficas;

/**
 * Excepción al intentar comprar sin haber seleccionado un producto.
 */
public class ProductoNoSeleccionadoException extends RuntimeException {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error
     * @param mensaje Entrega razón de la excepción
     */
    public ProductoNoSeleccionadoException(String mensaje) {
        super(mensaje);
    }
}
