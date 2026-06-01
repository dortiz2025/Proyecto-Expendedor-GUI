package expendedor.logica.productos.bebida;

/**
 * Clase que representa una Sprite
 * Hereda de su clase padre Bebida
 */
public class Sprite extends Bebida {
    /**
     * Constructor de la bebida Sprite
     * Asigna identificador de la bebida
     *
     * @param serie Identificador de la Bebida
     */
    public Sprite(int serie) {
        super(serie);
    };

    /**
     * Simula tomarse la Sprite
     *
     * @return Un String con el sabor de la bebida ("Sprite")
     */
    @Override
    public String consumir(){
        return "Sprite";
    }
}