package expendedor.logica;

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
     * Constructor de la clase comprador.
     */
    public Comprador() {
        this.cantMonedas1500 = 5;
        this.cantMonedas1000 = 5;
        this.cantMonedas500 = 4;
        this.cantMonedas100 = 10;
    }

    /**
     * Permite añadir un producto al inventario.
     * @param producto producto adquirido.
     */
    public void recibirProducto(Producto producto){
        //Aquí se podría generar un sonido como "¡Wow!",
        // para añadirle dinamismo a la interacción.
        inventario = producto;
    }

    /**
     * Añade monedas al monedero
     * @param moneda Moneda que se añadirá
     */
    public void addMoneda(Moneda moneda){
        switch (moneda){
            case Moneda1500 m -> cantMonedas1500++;
            case Moneda1000 m -> cantMonedas1000++;
            case Moneda500 m -> cantMonedas500++;
            case Moneda100 m -> cantMonedas100++;
            default -> {}
        }
    }

    /**
     * Consume el producto del inventario.
     */
    public void consumirProducto(){
        //Aquí se puede generar un sonido dependiendo del producto.
        //Se tendría que modificar consumir() en cada producto.
        if(inventario != null) {
            System.out.println("Se ha consumido un producto: " + this.inventario.consumir());
            this.inventario = null;
        }
    }
}