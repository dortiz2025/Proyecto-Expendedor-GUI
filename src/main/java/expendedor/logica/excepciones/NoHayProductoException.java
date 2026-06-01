package expendedor.logica.excepciones;

/**
 * Excepción al intentar comprar un producto que no existe o no tiene stock
 */
public class NoHayProductoException extends Exception {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error
     *
     * @param mensaje Entrega razón de la excepción
     */
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}
