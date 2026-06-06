package expendedor.gui;

import expendedor.logica.*;
import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    //Paneles que dividen a nuestra interfaz en 2...
    private PanelComprador panelComprador;
    private PanelExpendedor panelExpendedor;

    private Comprador comprador;
    private Expendedor expendedor;

    public PanelPrincipal() {
        //Inicialización de la lógica
        this.comprador = new Comprador();
        this.expendedor = new Expendedor(5);

        //Inicialización de contenedores gráficos
        this.panelComprador = new PanelComprador(comprador);
        this.panelExpendedor = new PanelExpendedor(expendedor);

        this.setLayout(null);//Para poder diseñar libremente;

        this.panelComprador.setOpaque(false);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Dibujamos la pared
        g.setColor(new Color(113, 146, 190));
        g.fillRect(0, 0, 600, 350);

        //Dibujamos el piso
        g.setColor(Color.GRAY);
        g.fillRect(0, 350, 600, 100);
    }
}
