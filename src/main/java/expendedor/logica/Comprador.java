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

    private String productoConsumido;

    //Monedero
    private int cantMonedas1500;
    private int cantMonedas1000;
    private int cantMonedas500;
    private int cantMonedas100;

    //Inventario (1 slot)
    private Producto inventario;

    /**
     * Constructor de la clase que simula la compra de un producto
     *
     * @param moneda Medio de pago para la compra (Puede ser null)
     * @param producto Producto que se ha elegido comprar
     * @param expendedor Máquina con la que se interactúa
     *
     * @throws NoHayProductoException Si no hay stock del producto o no existe
     * @throws PagoInsuficienteException Si el dinero no alcanza
     * @throws PagoIncorrectoException Si se compra sin dinero (Moneda null)
     */
    public Comprador(Moneda moneda, TipoProducto producto, Expendedor expendedor) throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {

        // Variable que almacena el producto comprado.
        // Puede ser null si hubo una excepción.
        Producto productoComprado = null;

        try {
            // Se hace la compra del producto seleccionado.
            // Se guarda en la variable.
            productoComprado = expendedor.comprarProducto(moneda, producto);
        } finally {

            // Variable que almacena el vuelto.
            // Moneda a moneda.
            Moneda vueltoMoneda;

            // Se pide vuelto
            // Mientras no sea null -> Se sigue pidiendo
            vueltoMoneda = expendedor.getVuelto();
            this.vueltoCompra = 0; //Se comienza en 0 de vuelto
            while (vueltoMoneda != null) {
                //Se suma al vuelto total de la compra
                this.vueltoCompra += vueltoMoneda.getValor();
                vueltoMoneda = expendedor.getVuelto();
            }

            // Si existe un producto comprado, se guarda su nombre
            // Sino, queda null
            if (productoComprado != null) this.productoConsumido = productoComprado.consumir();
            else this.productoConsumido = null;
        }
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