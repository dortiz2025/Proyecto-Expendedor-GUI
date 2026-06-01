package expendedor.logica.monedas;

/**
 * Clase abstracta "Moneda" para poder comprar en la expendedora
 * Implementa la interfaz Comparable para poder ordenar las monedas según
 * su valor
 */
public abstract class Moneda implements Comparable<Moneda>{

    /**
     * Constructor de clase Moneda
     */
    public Moneda() {}

    /**
     * Se ocupa el hashcode de la moneda como su numero de serie
     *
     * @return hashcode de la moneda (numero de serie)
     */
    public int getSerie() {
        return this.hashCode(); }

    /**
     * Metodo abstracto para que cada moneda retorne su respectivo valor
     *
     * @return valor de moneda
     */
    public abstract int getValor();

    /**
     * Metodo para poder comparar las monedas según su valor, permitiendo que sean
     * ordenadas
     *
     * @param otraMoneda Moneda distinta para comparar
     * @return (-1) si la moneda tiene menor valor, (1) si es mayor, (0) si son iguales
     */
    @Override
    public int compareTo(Moneda otraMoneda) {
        if (this.getValor() < otraMoneda.getValor()) {
            return -1;
        } else if (this.getValor() > otraMoneda.getValor()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * String que una "moneda" representa
     *
     * @return string con el valor y el numero de serie (hashcode) de la moneda
     */
    @Override
    public String toString() {
        return "Moneda de: " + this.getValor() + ", Serie: " + this.hashCode();
    }
}
