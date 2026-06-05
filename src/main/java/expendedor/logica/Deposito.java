package expendedor.logica;

import java.util.ArrayList;

/**
 * Depósito Genérico.
 * @param <T> Clase que será elemento del depósito.
 */
public class Deposito <T> {

    /**
     * Arreglo que almacena elementos de clase T
     * simulando un depósito.
     */
    private ArrayList<T> deposito;

    /**
     * Constructor que inicializa el ArrayList.
     */
    public Deposito() {
        this.deposito = new ArrayList<>();
    }

    /**
     * Permite saber cuántos elementos hay en el depósito.
     * @return Cantidad de elementos en el depósito.
     */
    public int size(){
        return this.deposito.size();
    }

    /**
     * Metodo que añade elementos al depósito.
     * @param elemento Elemento que se quiere añadir al depósito.
     */
    public void add(T elemento) {
        this.deposito.add(elemento);
    }

    /**
     * Retira elementos del depósito.
     * Mecánica FIFO.
     * @return Entrega el elemento retirado del depósito.
     */
    public T get() {
        if (deposito.isEmpty()) {
            return null;
        }
        return deposito.removeFirst();
    }

    /**
     * Permite obtener la referencia de un objeto
     * sin sacarlo del depósito.
     * @param index Índice del elemento.
     * @return El elemento, o null si el índice es inválido.
     */
    public T getItem(int index) {
        if (index >= 0 && index < deposito.size()) {
            return deposito.get(index);
        }
        return null;
    }
}