package expendedor.logica;

import expendedor.logica.excepciones.*;
import expendedor.logica.monedas.*;
import expendedor.logica.productos.*;
import java.util.ArrayList;

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
    private Producto productoComprado; //Espacio Único

    //Depósitos de monedas
    private Deposito<Moneda> depMoneda;//INUTILIZABLE - ELIMINAR
    private Deposito<Moneda> depSaldo;//Monedas Ingresadas antes de comprarProducto
    private ArrayList<Deposito<Moneda>> depGanancias;
    private Deposito<Moneda> depVuelto;

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
     * Se simula la compra de un producto validando la moneda ingresada y el stock
     * Si la compra es exitosa, guarda el vuelto en monedas de 100 en su depósito
     * De lo contrario, devuelve la moneda original al depósito de vuelto
     * @param moneda que ocupa el Comprador
     * @param tipo constante indica el tipo de producto que se quiere comprar
     * @return el producto que se compró
     * @throws PagoIncorrectoException si la moneda es nula
     * @throws PagoInsuficienteException si al Comprador no le alcanza para pagar el producto
     * @throws NoHayProductoException si no hay suficiente stock del producto que se quiere comprar
     */
    public void comprarProducto(Moneda moneda, TipoProducto tipo) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {

        if (moneda == null) {
             throw new PagoIncorrectoException("Pago incorrecto");
        }

        if (moneda.getValor() < tipo.getPrecio()) {
            depVuelto.add(moneda);
            throw new PagoInsuficienteException("Dinero insuficiente.");
        }

        switch (tipo) {
            case COCACOLA -> productoComprado = depCoca.get();
            case FANTA -> productoComprado = depFanta.get();
            case SPRITE -> productoComprado = depSprite.get();
            case SUPER8 -> productoComprado = depSuper8.get();
            case OREO -> productoComprado = depOreo.get();
            case SNICKERS -> productoComprado = depSnickers.get();
            default -> {
                depMoneda.add(moneda);
                throw new NoHayProductoException("Tipo de producto no válido");
            }
        }

        if (productoComprado == null){
            depVuelto.add(moneda);
            throw new NoHayProductoException("Sin stock");
        }

        int vuelto = moneda.getValor() - tipo.getPrecio();
        int cantidadMonedas100 = (vuelto/100);

        for (int i = 0; i < cantidadMonedas100; i++) {
            depMoneda.add(new Moneda100());
        }

        this.productoComprado = productoComprado;
    }

    public void insertarMoneda(Moneda moneda) {
        depSaldo.add(moneda);
    }

    public Producto getProductoComprado() {
        return productoComprado;
    }

    /**
     * Sacar las monedas de vuelto
     * @return una Moneda de 100 pesos
     */
    public Moneda getVuelto() {
        return depMoneda.get();
    }
}
