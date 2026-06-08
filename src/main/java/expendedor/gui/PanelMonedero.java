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

    private void dibujarPilaMonedas(Graphics g, int cantidad, int x, int y, Color color, String texto) {
        if (cantidad > 0) {
            g.setColor(color);
            g.fillOval(x, y, TAMANO_MONEDA, TAMANO_MONEDA);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, TAMANO_MONEDA, TAMANO_MONEDA);
            g.drawString(texto + " (x" + cantidad + ")", x + 30, y + 20);
        }
    }

    /**
     * Este método recibe las coordenadas de un clic y revisa si le atinó a alguna moneda.
     */
    public Moneda revisarClicMonedas(int clicX, int clicY) {

        //Caso click moneda de 100
        if (clicX >= X_M100 && clicX <= X_M100 + TAMANO_MONEDA &&
                clicY >= Y_M100 && clicY <= Y_M100 + TAMANO_MONEDA) {

            // Le sacamos la moneda de 100 al Comprador lógico
            return comprador.getMoneda(100);
        }
        //Caso click moneda de 500
        else if (clicX >= X_M500 && clicX <= X_M500 + TAMANO_MONEDA &&
                clicY >= Y_M500 && clicY <= Y_M500 + TAMANO_MONEDA) {
            return comprador.getMoneda(500);
        }
        //Caso click moneda de 1000
        else if (clicX >= X_M1000 && clicX <= X_M1000 + TAMANO_MONEDA &&
                clicY >= Y_M1000 && clicY <= Y_M1000 + TAMANO_MONEDA) {
            return comprador.getMoneda(1000);
        }

        //Caso click moneda de 1500
        else if (clicX >= X_M1500 && clicX <= X_M1500 + TAMANO_MONEDA &&
                clicY >= Y_M1500 && clicY <= Y_M1500 + TAMANO_MONEDA) {
            return comprador.getMoneda(1500);
        }

        // Si hizo clic en un espacio vacío, no devuelve nada (null)
        return null;
    }
}
