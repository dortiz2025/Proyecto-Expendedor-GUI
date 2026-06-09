package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelVuelto extends JPanel {
    private Expendedor expendedor;

    public PanelVuelto(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new GridLayout(1,2));

        // Añadimos la lista de espera visual
        this.add(new PanelDepositoMoneda(expendedor.getDepVuelto()));

        // Añadimos el cuadrito de retiro (Le pasamos el comprador para que pueda darle el dinero)
        this.add(new PanelRetiroVuelto(expendedor, comprador));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}