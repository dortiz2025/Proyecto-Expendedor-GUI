package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Deposito;
import expendedor.logica.Expendedor;
import expendedor.logica.excepciones.PagoIncorrectoException;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel que representa el monedero del comprador.
 */
public class PanelMonedero extends JPanel {
    private Comprador comprador;
    private Expendedor expendedor;

    //Definimos las coordenadas de cada moneda en la ventana.
    public final int X_M100 = 20,  Y_M100 = 20;
    public final int X_M500 = 20,  Y_M500 = 50;
    public final int X_M1000 = 20, Y_M1000 = 80;
    public final int X_M1500 = 20, Y_M1500 = 110;

    //Definimos el tamaño de las monedas como 25x25 píxeles
    public final int TAMANO_MONEDA = 25;
    public PanelMonedero(Comprador comprador, Expendedor expendedor) {
        this.comprador = comprador;
        this.expendedor = expendedor;
        this.setOpaque(false); //Hacemos el fondo del panel transparente
    }

    public PanelMonedero(Comprador comprador) {
        this.comprador = comprador;//Recibe referencia del comprador
    }
}
