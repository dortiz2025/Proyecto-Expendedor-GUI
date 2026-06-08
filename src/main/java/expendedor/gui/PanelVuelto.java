package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelVuelto extends JPanel {
    private Expendedor expendedor;

    public PanelVuelto(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new GridLayout(1,2));

        //Añadimos en un lado el depósito de vuelto
        this.add(new PanelDepositoMoneda(expendedor.getDepVuelto()));
        this.add(new PanelRetiroVuelto(expendedor));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Moneda monedaVuelto = expendedor.getDepVuelto().get();

                if (monedaVuelto != null) {
                    System.out.println("Retiraste una moneda de: $" + monedaVuelto.getValor());

                    // Devolvemos el valor correspondiente al monedero del comprador
                    comprador.recibirMoneda(monedaVuelto);

                    // Redibujamos la ventana para reflejar el cambio en ambos paneles inmediatamente
                    if (SwingUtilities.getWindowAncestor(PanelVuelto.this) != null) {
                        SwingUtilities.getWindowAncestor(PanelVuelto.this).repaint();
                    }
                } else {
                    System.out.println("No queda vuelto por retirar.");
                }
            }
        });
    }



  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
