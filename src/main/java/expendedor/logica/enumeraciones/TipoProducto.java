package expendedor.logica.enumeraciones;

/**
 * Enum de tipo de producto
 */
public enum TipoProducto {
    /**
     * Bebida CocaCola, precio: 1300
     */
    COCACOLA(1300),

    /**
     * Bebida Fanta, precio: 1300
     */
    FANTA(1300),

    /**
     * Bebida Sprite, precio: 1300
     */
    SPRITE(1300),

    /**
     * Dulce Super8, precio: 500
     */
    SUPER8(500),

    /**
     * Dulce Oreo, precio: 800
     */
    OREO(800),

    /**
     * Dulce SNICKERS, precio:800
     */
    SNICKERS(1000);

    /**
     * Precio Constante
     * No se modifica
     */
    private final int precio;

    /**
     * Asigna precio a cada producto
     *
     * @param precio Precio del producto
     */
    TipoProducto(int precio){
        this.precio = precio;
    }

    /**
     * getter de Precio del producto
     *
     * @return Devuelve precio del producto
     */
    public int getPrecio() {
        return precio;
    }

}