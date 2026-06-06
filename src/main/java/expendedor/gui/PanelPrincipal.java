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
        this.panelComprador = new PanelComprador();
        this.panelExpendedor = new PanelExpendedor();

        //Fondo gris
        this.setBackground(Color.GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.panelComprador.paintComponent(g);
        this.panelExpendedor.paintComponent(g);
    }
}
