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
        this.setLayout(null); // Diseño libre
        this.setBackground(new Color(0, 75, 115));//Fondo Expendedor

        //Se inicializan los sub-paneles
        this.panelVitrina =  new PanelVitrina(expendedor);
        this.panelPago =  new PanelPago(expendedor);
        this.panelDepositoGanancias = new PanelDepositoGanancias(expendedor);
        this.panelProductoComprado = new PanelProductoComprado(expendedor);
        this.panelVuelto = new PanelVuelto(expendedor);

        //Se definen los tamaños de cada panel de modo que estén ordenados
        //Las coordenadas son relativas a las del expendedor en sí
        this.panelVitrina.setBounds(10, 10, 160, 180);
        this.panelPago.setBounds(180, 10, 60, 180);
        this.panelDepositoGanancias.setBounds(10, 200, 80, 70);
        this.panelProductoComprado.setBounds(105, 240, 60, 40);
        this.panelVuelto.setBounds(180, 200, 60, 70);

        //Se añaden al expendedor
        this.add(panelVitrina);
        this.add(panelPago);
        this.add(panelDepositoGanancias);
        this.add(panelProductoComprado);
        this.add(panelVuelto);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
