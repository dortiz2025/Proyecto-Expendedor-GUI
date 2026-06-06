package expendedor.gui;

import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    //Sub-Paneles
    private PanelVitrina panelVitrina;
    private PanelPago panelPago;
    private PanelDepositoGanancias panelDepositoGanancias;
    private PanelProductoComprado panelProductoComprado;
    private PanelVuelto panelVuelto;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;

        this.panelVitrina =  new PanelVitrina(expendedor);
        this.panelPago =  new PanelPago(expendedor);
        this.panelDepositoGanancias = new PanelDepositoGanancias(expendedor);
        this.panelProductoComprado = new PanelProductoComprado(expendedor);
        this.panelVuelto = new PanelVuelto(expendedor);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
