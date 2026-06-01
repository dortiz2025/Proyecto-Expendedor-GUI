package expendedor.logica.productos;

/**
 * Clase abstracta que representa un producto genérico
 * Sirve como base para lo que se venderá en el expendedor
 */
public abstract class Producto {
    /**
     * Identificador del Producto
     */
    private int serie;

    /**
     * Asigna identificador
     *
     * @param serie Identificador del Producto
     */
    public Producto(int serie){
        this.serie = serie;
    }

    /**
     * Simula lectura de identificador
     *
     * @return Identificador del Producto
     */
    public int getSerie(){
        return serie;
    }

    /**
     * Simula consumo del producto
     *
     * @return Nombre del producto consumido
     */
    public abstract String consumir();
}