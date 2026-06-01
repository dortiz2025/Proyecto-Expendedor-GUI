package expendedor.logica.monedas;

/**
 * Clase de Moneda de 500, hereda de Moneda
 */
public class Moneda500 extends Moneda{

    /**
     * Constructor de la clase Moneda500
     * Inicializa la moneda de 500 llamando a la clase padre Moneda
     */
    public Moneda500(){
        super();
    }

    /**
     * Obtiene valor de la moneda
     *
     * @return Valor de la moneda(500)
     */
    @Override
    public int getValor(){
        return 500;
    }
}
