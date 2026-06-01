package expendedor.logica.excepciones;

/**
 * Excepción al intentar comprar con menos dinero del necesario
 */
public class PagoInsuficienteException extends Exception {
    /**
     * Constructor de la excepción
     * Crea una instancia con una advertencia del error
     *
     * @param mensaje Entrega razón de la excepción
     */
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
