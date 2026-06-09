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
 * Panel que representa gráficamente el monedero del comprador y permite la interacción
 * para ingresar monedas al expendedor.
 */
public class PanelMonedero extends JPanel {
    private Comprador comprador;
    private Expendedor expendedor;

    public final int X_M100 = 30,  Y_M100 = 30;
    public final int X_M500 = 30,  Y_M500 = 220;
    public final int X_M1000 = 30, Y_M1000 = 420;
    public final int X_M1500 = 30, Y_M1500 = 620;

    public final int TAMANO_MONEDA = 80;

    /**
     * Inicializa el panel del monedero y configura los eventos de entrada de monedas.
     * @param comprador Referencia al comprador lógico.
     * @param expendedor Referencia al expendedor lógico.
     */
    public PanelMonedero(Comprador comprador, Expendedor expendedor) {
        this.comprador = comprador;
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setToolTipText("");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Moneda monedaSacada = revisarClicMonedas(e.getX(), e.getY());

                if (monedaSacada != null) {
                    System.out.println("Sacaste una moneda de $" + monedaSacada.getValor() + " e ingresó al expendedor.");

                    try {
                        expendedor.insertarMoneda(monedaSacada);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (SwingUtilities.getWindowAncestor(PanelMonedero.this) != null) {
                        SwingUtilities.getWindowAncestor(PanelMonedero.this).repaint();
                    }
                }
            }
        });
    }

    /**
     * Dibuja las monedas disponibles en el monedero del comprador.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Deposito<Moneda>> monedero = comprador.getMonedero();

        dibujarPilaMonedas(g, monedero.get(3).size(), X_M100, Y_M100, Color.WHITE, "100");
        dibujarPilaMonedas(g, monedero.get(2).size(), X_M500, Y_M500, Color.WHITE, "500");
        dibujarPilaMonedas(g, monedero.get(1).size(), X_M1000, Y_M1000, Color.WHITE, "1000");
        dibujarPilaMonedas(g, monedero.get(0).size(), X_M1500, Y_M1500, Color.WHITE, "1500");
    }

    private void dibujarPilaMonedas(Graphics g, int cantidad, int x, int y, Color color, String texto) {
        if (cantidad > 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("x" + cantidad, x + 10 + TAMANO_MONEDA + 10, y + (TAMANO_MONEDA / 2));
        }
    }

    /**
     * Determina si las coordenadas del ratón coinciden con la ubicación de una moneda.
     * @param clicX Coordenada X del clic.
     * @param clicY Coordenada Y del clic.
     * @return La moneda seleccionada, o null si el área está vacía.
     */
    public Moneda revisarClicMonedas(int clicX, int clicY) {
        if (clicX >= X_M100 && clicX <= X_M100 + TAMANO_MONEDA &&
                clicY >= Y_M100 && clicY <= Y_M100 + TAMANO_MONEDA) {
            return comprador.getMoneda(100);
        } else if (clicX >= X_M500 && clicX <= X_M500 + TAMANO_MONEDA &&
                clicY >= Y_M500 && clicY <= Y_M500 + TAMANO_MONEDA) {
            return comprador.getMoneda(500);
        } else if (clicX >= X_M1000 && clicX <= X_M1000 + TAMANO_MONEDA &&
                clicY >= Y_M1000 && clicY <= Y_M1000 + TAMANO_MONEDA) {
            return comprador.getMoneda(1000);
        } else if (clicX >= X_M1500 && clicX <= X_M1500 + TAMANO_MONEDA &&
                clicY >= Y_M1500 && clicY <= Y_M1500 + TAMANO_MONEDA) {
            return comprador.getMoneda(1500);
        }

        return null;
    }

    /**
     * Muestra el número de serie de la moneda sobre la cual se posiciona el ratón.
     * @param e Evento del ratón.
     * @return El número de serie en formato String o null.
     */
    @Override
    public String getToolTipText(MouseEvent e) {
        int clicX = e.getX();
        int clicY = e.getY();
        List<Deposito<Moneda>> monedero = comprador.getMonedero();

        if (clicX >= X_M100 && clicX <= X_M100 + TAMANO_MONEDA && clicY >= Y_M100 && clicY <= Y_M100 + TAMANO_MONEDA) {
            if (monedero.get(3).size() > 0) return "Serie: " + monedero.get(3).getItem(0).getSerie();
        } else if (clicX >= X_M500 && clicX <= X_M500 + TAMANO_MONEDA && clicY >= Y_M500 && clicY <= Y_M500 + TAMANO_MONEDA) {
            if (monedero.get(2).size() > 0) return "Serie: " + monedero.get(2).getItem(0).getSerie();
        } else if (clicX >= X_M1000 && clicX <= X_M1000 + TAMANO_MONEDA && clicY >= Y_M1000 && clicY <= Y_M1000 + TAMANO_MONEDA) {
            if (monedero.get(1).size() > 0) return "Serie: " + monedero.get(1).getItem(0).getSerie();
        } else if (clicX >= X_M1500 && clicX <= X_M1500 + TAMANO_MONEDA && clicY >= Y_M1500 && clicY <= Y_M1500 + TAMANO_MONEDA) {
            if (monedero.get(0).size() > 0) return "Serie: " + monedero.get(0).getItem(0).getSerie();
        }
        return null;
    }
}