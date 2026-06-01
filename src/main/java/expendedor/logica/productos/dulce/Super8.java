package expendedor.logica.productos.dulce;

/**
 * Clase abstracta que representa un Producto de tipo Super8.
 * Hereda se su clase padre Dulce
 */
public class Super8 extends Dulce {
    /**
     * Constructor del dulce Super8
     * Asigna identificador del Dulce
     *
     * @param serie Identificador del Dulce
     */
    public Super8(int serie){
        super(serie);
    }

    /**
     * Simula comerse un Super8
     *
     * @return Un String con el nombre del dulce ("Super8")
     */
    @Override
    public String consumir(){
        return "Super8";
    }
}