package expendedor.logica.excepciones;

/**
 * Excepción al intentar pagar sin dinero o dinero inválido (Moneda Null)
 */
public class PagoIncorrectoException extends Exception {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error
     *
     * @param mensaje Entrega razón de la excepción
     */
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}

