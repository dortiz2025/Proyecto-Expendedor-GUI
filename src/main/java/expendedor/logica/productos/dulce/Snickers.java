package expendedor.logica.productos.dulce;

/**
 * Clase de un Producto de tipo Snickers
 * Hereda de su padre Dulce
 */
public class Snickers extends Dulce {
    /**
     * Constructor del Snickers
     * Asigna identificador del Dulce
     *
     * @param serie Identificador del Dulce
     */
    public Snickers(int serie){
        super(serie);
    }

    /**
     * Simula comerse un Snickers
     *
     * @return Un String con el nombre del dulce ("Snickers")
     */
    @Override
    public String consumir(){
        return "Snickers";
    }
}