package expendedor.logica.productos;

/**
 * Clase abstracta que representa un Producto de tipo Oreo.
 * Hereda se su clase padre Dulce
 */
public class Oreo extends Dulce {
    /**
     * Constructor del dulce Oreo
     * Asigna identificador del Dulce
     *
     * @param serie Identificador del Dulce
     */
    public Oreo(int serie){
        super(serie);
    }

    /**
     * Simula comerse una Oreo
     *
     * @return Un String con el nombre del dulce ("Oreo")
     */
    @Override
    public String consumir(){
        return "Oreo";
    }
}