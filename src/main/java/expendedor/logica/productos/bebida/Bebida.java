package expendedor.logica.productos.bebida;

import expendedor.logica.productos.Producto;

/**
 * Clase abstracta que representa una Bebida
 * Sirve como base para las bebidas específicas que se venderán en la expendedora
 */
public abstract class Bebida extends Producto {
    /**
     * Constructor de Bebida
     * Asigna identificador de la bebida
     *
     * @param serie Identificador de la Bebida
     */
    public Bebida(int serie){
        super(serie);
    }
}