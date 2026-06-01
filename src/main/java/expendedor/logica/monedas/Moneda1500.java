package expendedor.logica.monedas;

/**
 * Clase de moneda de 1500, hereda de Moneda
 */
public class Moneda1500 extends Moneda{

    /**
     * Constructor de la clase Moneda1500
     * Inicializa la moneda de 1500 llamando a la clase padre Moneda
     */
    public Moneda1500(){
        super();
    }

    /**
     * Obtiene valor de la moneda
     *
     * @return Valor de la moneda(1500)
     */
    @Override
    public int getValor(){
        return 1500;
    }
}