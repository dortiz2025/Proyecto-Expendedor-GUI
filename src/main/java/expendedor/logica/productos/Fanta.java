package expendedor.logica.productos;

/**
 * Clase que representa una Fanta
 * Hereda de su clase padre Bebida
 */
public class Fanta extends Bebida {
    /**
     * Constructor de la bebida Fanta
     * Asigna identificador de la bebida
     *
     * @param serie Identificador de la Bebida
     */
    public Fanta(int serie) {
        super(serie);
    }

    /**
     * Simula tomarse la Fanta
     *
     * @return Un String con el sabor de la bebida ("Fanta")
     */
    @Override
    public String consumir() {
        return "Fanta";
    }
}