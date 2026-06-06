package expendedor.gui;

import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
