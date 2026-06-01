package expendedor.logica.monedas;

/**
 * Clase de moneda de 1000, hereda de Moneda
 */
public class Moneda1000 extends Moneda{

    /**
     * Constructor de la clase Moneda1000
     * Inicializa la moneda de 1000 llamando a la clase padre Moneda
     */
    public Moneda1000(){
        super();
    }

    /**
     * Obtiene valor de la moneda
     *
     * @return Valor de la moneda(1000)
     */
    @Override
    public int getValor(){
        return 1000;
    }
}