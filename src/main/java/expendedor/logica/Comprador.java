package expendedor.logica;

import expendedor.logica.excepciones.*;
import expendedor.logica.monedas.*;
import expendedor.logica.productos.*;

/**
 * Clase que simula ser una persona
 * que compra un producto y lo consume.
 * "Recuerda" que ha consumido
 * y cuanto vuelto le dieron
 */
public class Comprador {
    //Monedero
    private int cantMonedas1500;
    private int cantMonedas1000;
    private int cantMonedas500;
    private int cantMonedas100;

    //Inventario (1 slot)
    private Producto inventario;

    /**
     * Constructor de la clase comprador
     */
    public Comprador(Moneda moneda, TipoProducto producto, Expendedor expendedor) {
        this.cantMonedas1500 = 5;
        this.cantMonedas1000 = 5;
        this.cantMonedas500 = 4;
        this.cantMonedas100 = 10;
    }

    /**
     * Permite añadir un producto al inventario
     * @param producto producto adquirido.
     */
    public void recibirProducto(Producto producto){
        inventario = producto;
    }

    /**
     * Metodo que simula ver el vuelto recibido
     *
     * @return Devuelve el vuelto recibido
     */
    public int cuantoVuelto() {
        return vueltoCompra; }

    /**
     * Metodo que simula preguntar que producto se ha consumido
     *
     * @return Entrega el nombre del producto consumido
     */
    public String queConsumiste() {
        if (productoConsumido != null) return productoConsumido;
        else return "No hay producto consumido.";
    }
}