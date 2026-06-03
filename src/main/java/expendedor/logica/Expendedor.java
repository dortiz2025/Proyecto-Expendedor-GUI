package expendedor.logica;

import expendedor.logica.excepciones.*;
import expendedor.logica.monedas.*;
import expendedor.logica.productos.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Expendedora que almacena depósitos de productos y de monedas para el vuelto
 * Procesa las compras, valida el pago y da el vuelto correspondiente
 */
public class Expendedor {
    //Depósitos de productos
    private Deposito<Bebida> depCoca;
    private Deposito<Bebida> depSprite;
    private Deposito<Bebida> depFanta;
    private Deposito<Dulce> depSuper8;
    private Deposito<Dulce> depOreo;
    private Deposito<Dulce> depSnickers;
    private Producto ProductoComprado; //Espacio Único

    //Depósitos de monedas
    private Deposito<Moneda> depMoneda;//INUTILIZABLE - ELIMINAR
    private Deposito<Moneda> depSaldo;//Monedas Ingresadas antes de comprarProducto
    private List<Deposito<Moneda>> depGanancias;
    private Deposito<Moneda> depVuelto;

    //Variables que almacenan datos importantes...

    /**
     * Constructor de la clase Expendedor
     * Inicializa los depósitos y los llena
     * @param cantidadProductos con la que se llenará cada depósito
     */
    public Expendedor(int cantidadProductos) {
        //Se inicializan los depósitos de productos
        this.depCoca = new Deposito<>();
        this.depSprite = new Deposito<>();
        this.depFanta = new Deposito<>();
        this.depSuper8 = new Deposito<>();
        this.depOreo = new Deposito<>();
        this.depSnickers = new Deposito<>();
        this.depMoneda = new Deposito<>();

        //Se agregan productos a los depósitos
        for (int i = 0; i < cantidadProductos; i++) {
            this.depCoca.add(new CocaCola(100 + i));
            this.depSprite.add(new Sprite(200 + i));
            this.depFanta.add(new Fanta(300 + i));
            this.depSuper8.add(new Super8(400 + i));
            this.depOreo.add(new Oreo(500 + i));
            this.depSnickers.add(new Snickers(600 + i));
        }

        //Se inicializan los depósitos de monedas
        this.depSaldo = new Deposito<>();
        this.depGanancias = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            depGanancias.add(new Deposito<Moneda>());
        }
        this.depVuelto = new Deposito<>();
    }

    /**
     * Se simula la compra de un producto.
     * En una compra exitosa se calcula el vuelto en diferentes tipos de monedas.
     * @param tipo constante indica el tipo de producto que se quiere comprar
     * @throws PagoInsuficienteException si al Comprador no le alcanza para pagar el producto
     * @throws NoHayProductoException si no hay suficiente stock del producto que se quiere comprar
     */
    public void comprarProducto(TipoProducto tipo) throws PagoInsuficienteException, NoHayProductoException {
        //Variables temporales
        int saldo = 0; //Saldo del cliente
        Deposito<Moneda> depTemp = new Deposito<>();//Deposito temporal de monedas
        Moneda moneda; //Variable auxiliar para ciclos while

        //Contamos cuanto dinero entregó el usuario
        while ((moneda = depSaldo.get()) != null) {
            saldo += moneda.getValor();
            depTemp.add(moneda);
        }

        //Si no le alcanza...
        if (saldo < tipo.getPrecio()) {
            while((moneda = depTemp.get()) != null) {
                depVuelto.add(moneda);
            }
            throw new PagoInsuficienteException("Dinero insuficiente.");
        }

        //Se procesa la compra...
        Producto producto;
        switch (tipo) {
            case COCACOLA -> producto = depCoca.get();
            case FANTA -> producto = depFanta.get();
            case SPRITE -> producto = depSprite.get();
            case SUPER8 -> producto = depSuper8.get();
            case OREO -> producto = depOreo.get();
            case SNICKERS -> producto = depSnickers.get();
            default -> {
                while((moneda = depTemp.get()) != null) {
                    depVuelto.add(moneda);
                }
                throw new NoHayProductoException("Tipo de producto no válido");
            }
        }

        //Si no quedaba el producto solicitado...
        if (producto == null){
            while((moneda = depTemp.get()) != null) {
                depVuelto.add(moneda);
            }
            throw new NoHayProductoException("Sin stock");
        }

        //VUELTO EN DIFERENTES TIPOS DE MONEDAS
        int vuelto = saldo - tipo.getPrecio();
        if (vuelto > 0) {
            entregarVuelto(vuelto, depVuelto); //Metodo interno
        }

        this.ProductoComprado = producto;
    }

    /**
     * Insertar monedas al saldo de la expendedora.
     * @param moneda moneda para sumar al saldo
     * @throws PagoIncorrectoException si se ingresa una moneda falsa (nula)
     */
    public void insertarMoneda(Moneda moneda) throws PagoIncorrectoException {
        if (moneda == null) {
            throw new PagoIncorrectoException("No puede ingresar una moneda falsa (nula)");//Excepción Inútil
        }
        depSaldo.add(moneda);
    }

    /**
     * Sacar producto comprado.
     * @return producto comprado
     */
    public Producto getProductoComprado() {
        Producto aux = this.ProductoComprado;
        this.ProductoComprado = null; //Dejamos el depósito especial vacío
        return aux;
    }

    /**
     * Sacar las monedas de vuelto
     * @return una Moneda de 100 pesos
     */
    public Moneda getVuelto() {
        return depMoneda.get();
    }

    /**
     * Calcula el vuelto en distintos tipos de monedas.
     * Añade monedas en el depósito correspondiente.
     * @param vuelto vuelto que deberían sumar las monedas
     * @param depVuelto referencia del depósito de vuelto
     */
    private void entregarVuelto(int vuelto, Deposito<Moneda> depVuelto) {
        //--- PROCESO ENTREGA VUELTO ---//
        int[] valores = {1500, 1000, 500, 100}; //Lista auxiliar (Monedas mayor a menor)

        for (int valor : valores) {
            int cantidadMonedas = vuelto / valor; //Se calcula cuantas monedas de cada tipo podemos entregar

            if (cantidadMonedas > 0) {
                vuelto %= valor; // Actualizamos el vuelto restante

                for (int i = 0; i < cantidadMonedas; i++) {
                    //Añadimos monedas según el tipo
                    switch (valor) {
                        case 1500 -> depVuelto.add(new Moneda1500());
                        case 1000 -> depVuelto.add(new Moneda1000());
                        case 500  -> depVuelto.add(new Moneda500());
                        case 100  -> depVuelto.add(new Moneda100());
                    }
                }
            }
        }
        //---FIN ENTREGA DE VUELTO---//
    }
}