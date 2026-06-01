package expendedor.logica.productos.bebida;

/**
 * Clase que representa un producto de tipo CocaCola
 * Hereda de la clase abstracta Bebida
 */
public class CocaCola extends Bebida {
    /**
     * Constructor de la bebida CocaCola
     * Asigna identificador de la bebida
     *
     * @param serie Identificador de la Bebida
     */
    public CocaCola(int serie) {
        super(serie);
    };

    /**
     * Simula el tomarse la bebida
     *
     * @return Un String con el sabor ("CocaCola")
     */
    @Override
    public String consumir(){
        return "CocaCola";
    }
}