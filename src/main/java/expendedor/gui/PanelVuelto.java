package expendedor.gui;

import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelVuelto extends JPanel {
    private Expendedor expendedor;

    public PanelVuelto(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new GridLayout(1,2));

        //Añadimos en un lado el depósito de vuelto
        this.add(new PanelDepositoMoneda(expendedor.getDepVuelto()));
        this.add(new PanelRetiroVuelto(expendedor));
    }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
