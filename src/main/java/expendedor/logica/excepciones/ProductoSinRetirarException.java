package expendedor.logica.excepciones;

/**
 * Excepción al intentar comprar sin haber retirado un producto ya comprado anteriormente.
 */
public class ProductoSinRetirarException extends RuntimeException {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error.
     *
     * @param message Entrega razón de la excepción.
     */
    public ProductoSinRetirarException(String message) {
        super(message);
    }
}
