package expendedor.logica.monedas;

/**
 * Clase de Moneda de 100, hereda de Moneda
 */
public class Moneda100 extends Moneda{

    /**
     * Constructor de la clase Moneda100
     * Inicializa la moneda de 100 llamando a la clase padre Moneda
     */
    public Moneda100(){
        super();
    }

    /**
     * Obtiene valor de la moneda
     *
     * @return Valor de la moneda(100)
     */
    @Override
    public int getValor(){
        return 100;
    }
}
