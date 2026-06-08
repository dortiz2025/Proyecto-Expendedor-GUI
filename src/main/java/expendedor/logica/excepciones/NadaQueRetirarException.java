package expendedor.logica.excepciones;

/**
 * Excepción al intentar sacar una moneda o un producto cuando no hay nada.
 */
public class NadaQueRetirarException extends RuntimeException {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error.
     *
     * @param mensaje Entrega razón de la excepción.
     */
    public NadaQueRetirarException(String mensaje) {
        super(mensaje);
    }
}
