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
    private final List<Deposito<Moneda>> monedero;

    //Inventario (1 slot)
    private Producto inventario;

    /**
     * Constructor de la clase comprador.
     */
    public Comprador() {
        //Inicializa el monedero
        this.monedero = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.monedero.add(new Deposito<>());
        }
        //Añadimos monedas iniciales...
        int[] cantidadMonedas = {5, 4, 5, 10};
        int[] valorMonedas = {1500, 1000, 500, 100};
        // Recorremos los 4 tipos de monedas
        for (int i = 0; i < cantidadMonedas.length; i++) {
            // Por cada tipo, generamos la cantidad respectiva
            for (int j = 0; j < cantidadMonedas[i]; j++) {
                generarMoneda(valorMonedas[i]);
            }
        }
    }

    /**
     * Recibe monedas y las guarda en el monedero.
     * @param moneda Moneda recibida.
     */
    public void recibirMoneda(Moneda moneda){
        switch (moneda.getValor()) {
            case 1500 -> monedero.get(0).add(moneda);
            case 1000 -> monedero.get(1).add(moneda);
            case 500 -> monedero.get(2).add(moneda);
            case 100 -> monedero.get(3).add(moneda);
            default -> {}
        }
    }

    /**
     * Entrega una moneda según el valor solicitado.
     * @param valor Valor de la moneda.
     * @return Moneda solicitada.
     */
    public Moneda getMoneda(int valor){
        return switch (valor) {
            case 1500 -> this.monedero.get(0).get();
            case 1000 -> this.monedero.get(1).get();
            case 500 -> this.monedero.get(2).get();
            case 100 -> this.monedero.get(3).get();
            default -> null;
        };
    }

    /**
     * Añade una nueva moneda al monedero
     * dependiendo del valor solicitado.
     * @param valor Valor de la nueva moneda.
     */
    public void generarMoneda(int valor){
        switch (valor) {
            case 1500 -> this.monedero.get(0).add(new Moneda1500());
            case 1000 -> this.monedero.get(1).add(new Moneda1000());
            case 500 -> this.monedero.get(2).add(new Moneda500());
            case 100 -> this.monedero.get(3).add(new Moneda100());
        }
    }

    /**
     * Permite añadir un producto al inventario.
     * @param producto producto adquirido.
     */
    public void recibirProducto(Producto producto){
        //Aquí se podría generar un sonido como "¡Wow!",
        // para añadirle dinamismo a la interacción.
        this.inventario = producto;
    }

    /**
     * Getter del inventario del comprador
     * @return inventario del comprador
     */
    public Producto getInventario() {
        return this.inventario;
    }

    /**
     * Consume el producto del inventario.
     */
    public void consumirProducto(){
        //Aquí se puede generar un sonido dependiendo del producto.
        //Se tendría que modificar consumir() en cada producto.
        if(this.inventario != null) {
            System.out.println("Se ha consumido un producto: " + this.inventario.consumir());
            this.inventario = null;
        }
    }

    /**
     * Getter de monedero.
     * @return Referencia del monedero.
     */
    public List<Deposito<Moneda>> getMonedero(){
        return this.monedero;
    }
}