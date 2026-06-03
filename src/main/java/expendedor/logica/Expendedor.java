package expendedor.logica;

import expendedor.logica.excepciones.*;
import expendedor.logica.monedas.*;
import expendedor.logica.productos.*;

import java.sql.SQLOutput;
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
            depGanancias.add(new Deposito<>());
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

        //------COMPRA EXITOSA------//
        int precioProducto = tipo.getPrecio();

        //Se añaden todas las monedas ingresadas a las ganancias.
        if (precioProducto == saldo) {
            while((moneda = depTemp.get()) != null) {
                addOrdenado(moneda, depGanancias);
            }
        }
        //Se añade
        else {
            List<Moneda> ganancias = calcularMonedas(precioProducto);
            for(Moneda m : ganancias) {
                addOrdenado(m, depGanancias);
            }
        }
        //--------------------------//

        //VUELTO EN DIFERENTES TIPOS DE MONEDAS
        int vuelto = saldo - tipo.getPrecio();
        if (vuelto > 0) {
            List<Moneda> monedasVuelto = calcularMonedas(vuelto);
            for(Moneda m : monedasVuelto){
               depVuelto.add(m);
            }
        }

        //Se deja el producto comprado en su respectivo depósito
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
     * Sacar las monedas del depósito de vuelto.
     * @return moneda que queda en el depósito
     */
    public Moneda getVuelto() {
        return depVuelto.get();//Una por una
    }

    /**
     * Entrega una lista con monedas de acuerdo a un monto.
     * Se entrega la menor cantidad de monedas posibles.
     * @param monto monto que deberían sumar las monedas
     * @return arraylist con las monedas
     */
    private List<Moneda> calcularMonedas(int monto) {
        int[] valores = {1500, 1000, 500, 100}; //Lista auxiliar (Monedas mayor a menor)
        List<Moneda> monedas = new ArrayList<>(); //ArrayList con las monedas

        for (int valor : valores) {
            int cantidadMonedas = monto / valor; //Se entrega la menor cantidad de monedas posibles.

            if (cantidadMonedas > 0) {
                monto %= valor; // Actualizamos el monto restante

                for (int i = 0; i < cantidadMonedas; i++) {
                    //Añadimos monedas según el tipo
                    switch (valor) {
                        case 1500 -> monedas.add(new Moneda1500());
                        case 1000 -> monedas.add(new Moneda1000());
                        case 500  -> monedas.add(new Moneda500());
                        case 100  -> monedas.add(new Moneda100());
                    }
                }
            }
        }
        return monedas;
    }

    /**
     * Añade las ganancias ordenadas al arraylist depósito de ganancias.
     * @param moneda moneda que se ingresará
     * @param depGanancias referencia del arraylist
     */
    private void addOrdenado(Moneda moneda, List<Deposito<Moneda>> depGanancias) {
        switch (moneda.getValor()) {
            case 1500 -> depGanancias.get(0).add(moneda);
            case 1000 -> depGanancias.get(1).add(moneda);
            case 500 -> depGanancias.get(2).add(moneda);
            case 100 -> depGanancias.get(3).add(moneda);
            default -> {}
        }
    }
}