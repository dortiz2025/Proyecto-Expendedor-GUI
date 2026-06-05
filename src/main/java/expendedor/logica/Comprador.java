package expendedor.logica;

import expendedor.logica.monedas.*;
import expendedor.logica.productos.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que simula ser una persona
 * que compra un producto y lo consume.
 * "Recuerda" que ha consumido
 * y cuanto vuelto le dieron
 */
public class Comprador {
    //Monedero
    private List<Deposito<Moneda>> monedero;

    //Inventario (1 slot)
    private Producto inventario;

    /**
     * Constructor de la clase comprador.
     */
    public Comprador() {
        //Inicializa el monedero
        this.monedero = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            monedero.add(new Deposito<>());
        }
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
        switch (moneda.getValor()) {
            case 1500 -> monedero.get(0).add(moneda);
            case 1000 -> monedero.get(1).add(moneda);
            case 500 -> monedero.get(2).add(moneda);
            case 100 -> monedero.get(3).add(moneda);
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