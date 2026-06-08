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

        //Agregamos el listener del mouse para poder clickear sobre las monedas
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Vemos donde ocurrió el click
                Moneda monedaSacada = revisarClicMonedas(e.getX(), e.getY());

                if (monedaSacada != null) {
                    System.out.println("Sacaste una moneda de $" + monedaSacada.getValor() + " e ingresó al expendedor.");

                    //Se la entregamos a la máquina expendedora
                    try {
                        expendedor.insertarMoneda(monedaSacada);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    }

                    //Redibujamos todo el panel
                    if (SwingUtilities.getWindowAncestor(PanelMonedero.this) != null) {
                        SwingUtilities.getWindowAncestor(PanelMonedero.this).repaint();
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Deposito<Moneda>> monedero = comprador.getMonedero();

        //Dibujamos las monedas si es que hay al menos 1 en su depósito respectivo
        dibujarPilaMonedas(g, monedero.get(3).size(), X_M100, Y_M100, Color.LIGHT_GRAY, "100");
        dibujarPilaMonedas(g, monedero.get(2).size(), X_M500, Y_M500, Color.PINK, "500");
        dibujarPilaMonedas(g, monedero.get(1).size(), X_M1000, Y_M1000, Color.GREEN, "1000");
        dibujarPilaMonedas(g, monedero.get(0).size(), X_M1500, Y_M1500, Color.BLUE, "1500");
    }

    public PanelMonedero(Comprador comprador) {
        this.comprador = comprador;//Recibe referencia del comprador
    }
}
