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
    private final Deposito<Bebida> depCoca;
    private final Deposito<Bebida> depSprite;
    private final Deposito<Bebida> depFanta;
    private final Deposito<Dulce> depSuper8;
    private final Deposito<Dulce> depOreo;
    private final Deposito<Dulce> depSnickers;
    private Producto ProductoComprado; //Espacio Único

    //Depósitos de monedas
    private final Deposito<Moneda> depSaldo; //Monedas Ingresadas antes de comprarProducto
    private final List<Deposito<Moneda>> depGanancias; //Depósito de ganancias
    private final Deposito<Moneda> depVuelto; //Depósito del vuelto
    private Moneda depRetiroVuelto; //Depósito de la primera moneda para retirar

    //Variables que almacenan datos importantes...

    /**
     * Constructor de la clase Expendedor
     * Inicializa los depósitos y los llena
     * @param cantidadProductos con la que se llenará cada depósito
     */
    public Expendedor(int cantidadProductos) {
        //Se inicializan los depósitos de productos
        this.depCoca = new Deposito<>();
        this.depFanta = new Deposito<>();
        this.depSprite = new Deposito<>();
        this.depSuper8 = new Deposito<>();
        this.depOreo = new Deposito<>();
        this.depSnickers = new Deposito<>();

        //Se agregan productos a los depósitos
        for (int i = 0; i < cantidadProductos; i++) {
            this.depCoca.add(new CocaCola(100 + i));
            this.depFanta.add(new Fanta(200 + i));
            this.depSprite.add(new Sprite(300 + i));
            this.depSuper8.add(new Super8(400 + i));
            this.depOreo.add(new Oreo(500 + i));
            this.depSnickers.add(new Snickers(600 + i));
        }

        //Se inicializan los depósitos de monedas
        this.depSaldo = new Deposito<>();
        this.depGanancias = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.depGanancias.add(new Deposito<>());
        }
        this.depVuelto = new Deposito<>();
        this.depRetiroVuelto = null;
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
        this.depSaldo.add(moneda);
    }

    /**
     * Permite conocer el saldo ingresado hasta el momento
     * sin procesar la compra ni sacar las monedas.
     * @return Saldo total ingresado y disponible para comprar.
     */
    public int getSaldo() {
        int saldo = 0;
        for (int i = 0; i < this.depSaldo.size(); i++) { //Usamos size para recorrer depSaldo
            saldo += this.depSaldo.getItem(i).getValor(); //Sumamos el valor de cada moneda
        }
        return saldo;
    }

    /**
     * Cancela un intento de compra
     * antes de haber hecho buy.
     * Las monedas caen en el depósito de vuelto.
     */
    public void cancel(){
        if(this.depRetiroVuelto == null){
            this.depRetiroVuelto = this.depSaldo.get();
        }
        Moneda moneda; //Auxiliar para enviar monedas a depVuelto
        while ((moneda = this.depSaldo.get()) != null) {
            this.depVuelto.add(moneda);
        }
    }

    /**
     * Se simula la compra de un producto.
     * En una compra exitosa se calcula el vuelto en diferentes tipos de monedas.
     * @param tipo constante indica el tipo de producto que se quiere comprar
     * @throws PagoInsuficienteException si al Comprador no le alcanza para pagar el producto
     * @throws NoHayProductoException si no hay suficiente stock del producto que se quiere comprar
     */
    public void comprarProducto(TipoProducto tipo) throws PagoInsuficienteException, NoHayProductoException, ProductoSinRetirarException {
        //Variables temporales
        int saldo = 0; //Saldo del cliente
        Deposito<Moneda> depTemp = new Deposito<>();//Deposito temporal de monedas
        Moneda moneda; //Variable auxiliar para ciclos while

        if (this.getProductoComprado() != null){
            while (this.depSaldo.size() > 0) {
                Moneda monedaDevuelta = this.depSaldo.get(); //Sacamos del saldo
                this.depVuelto.add(monedaDevuelta);          //Lo metemos al vuelto
            }
            throw new ProductoSinRetirarException("Por favor, retire el producto del contenedor antes de volver a comprar.");
        }
        //Contamos cuanto dinero entregó el usuario
        while ((moneda = this.depSaldo.get()) != null) {
            saldo += moneda.getValor();
            depTemp.add(moneda);
        }

        //Si no le alcanza...
        if (saldo < tipo.getPrecio()) {
            if(this.depRetiroVuelto == null) {
                this.depRetiroVuelto = depTemp.get(); //Se añade la primera al depósito de retiro (1 slot)
            }
            while((moneda = depTemp.get()) != null) {
                this.depVuelto.add(moneda);
            }
            throw new PagoInsuficienteException("Dinero insuficiente.");
        }

        //Se procesa la compra...
        Producto producto;
        switch (tipo) {
            case COCACOLA -> producto = this.depCoca.get();
            case FANTA -> producto = this.depFanta.get();
            case SPRITE -> producto = this.depSprite.get();
            case SUPER8 -> producto = this.depSuper8.get();
            case OREO -> producto = this.depOreo.get();
            case SNICKERS -> producto = this.depSnickers.get();
            default -> {
                this.depRetiroVuelto = depTemp.get();
                while((moneda = depTemp.get()) != null) {
                    this.depVuelto.add(moneda);
                }
                throw new NoHayProductoException("Tipo de producto no válido");
            }
        }

        //Si no quedaba el producto solicitado...
        if (producto == null){
            if(this.depRetiroVuelto == null) {
                this.depRetiroVuelto = depTemp.get();
            }
            while((moneda = depTemp.get()) != null) {
                this.depVuelto.add(moneda);
            }
            throw new NoHayProductoException("Sin stock");
        }

        //------COMPRA EXITOSA------//
        //las monedas fisicas ingresadas (depTemp) van a las ganancias
        while((moneda = depTemp.get()) != null) {
            addOrdenado(moneda, this.depGanancias);
        }

        //VUELTO EN DIFERENTES TIPOS DE MONEDAS
        int vuelto = saldo - tipo.getPrecio();
        if (vuelto > 0) {
            List<Moneda> monedasVuelto = calcularMonedas(vuelto);
            if (!monedasVuelto.isEmpty()) {
                if(this.depRetiroVuelto == null) {
                    this.depRetiroVuelto = monedasVuelto.removeFirst();
                }
                for(Moneda m : monedasVuelto){
                    this.depVuelto.add(m);
                }
            }
        }
        //Se deja el producto comprado en su respectivo depósito
        this.ProductoComprado = producto;
        //--------------------------//
    }

    /**
     * Método que rellena el expendedor.
     * @param cantidadMaxima cantidad máxima de producto
     */
    public void Restock(int cantidadMaxima){
        while(this.depCoca.size() < cantidadMaxima) {
            this.depCoca.add(new CocaCola(100 + this.depCoca.size()));
        }
        while(this.depFanta.size() < cantidadMaxima) {
            this.depFanta.add(new Fanta(200 + this.depFanta.size()));
        }
        while(this.depSprite.size() < cantidadMaxima) {
            this.depSprite.add(new Sprite(300 + this.depSprite.size()));
        }
        while(this.depSuper8.size() < cantidadMaxima) {
            this.depSuper8.add(new Super8(400 + this.depSuper8.size()));
        }
        while(this.depOreo.size() < cantidadMaxima) {
            this.depOreo.add(new Oreo(500 + this.depOreo.size()));
        }
        while(this.depSnickers.size() < cantidadMaxima) {
            this.depSnickers.add(new Snickers(600 + depSnickers.size()));
        }
    }

    /**
     * Sacar producto comprado.
     * @return producto comprado
     */
    public Producto retirarProductoComprado() {
        Producto aux = this.ProductoComprado;
        this.ProductoComprado = null; //Dejamos el depósito especial vacío
        return aux;
    }

    /**
     * Sacar las monedas del depósito de vuelto.
     * @return moneda que queda en el depósito
     */
    public Moneda retirarMoneda() {
        Moneda moneda = this.depRetiroVuelto;//Moneda retirada
        this.depRetiroVuelto = this.depVuelto.get();//Siguiente que cae (puede ser null)
        return moneda;//Una por una...
    }

    //Getters de depósitos de productos
    public Deposito<Bebida> getDepCoca() { return this.depCoca; }
    public Deposito<Bebida> getDepSprite() { return this.depSprite; }
    public  Deposito<Bebida> getDepFanta() { return this.depFanta; }
    public Deposito<Dulce> getDepOreo() { return this.depOreo; }
    public Deposito<Dulce> getDepSuper8() { return this.depSuper8; }
    public Deposito<Dulce> getDepSnickers() { return this.depSnickers; }
    public Producto getProductoComprado() { return this.ProductoComprado; }

    //Getter de depósitos de monedas
    public List<Deposito<Moneda>> getDepGanancias() { return this.depGanancias; }
    public Deposito<Moneda> getDepVuelto() { return this.depVuelto;}
    public Moneda getDepRetiroVuelto() { return this.depRetiroVuelto;}

    //Metodo interno que calcula eficientemente cuantas monedas (de diferente tipo) suman cierto monto.
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

    //Metodo interno que añade las ganancias ordenadas al arraylist depósito de ganancias.
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