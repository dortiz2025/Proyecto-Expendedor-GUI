package expendedor.gui;

import expendedor.logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panel principal que se añade a la ventana.
 * Dibuja toda la parte visual.
 * Contiene a sub-paneles que organizan la lógica gráfica.
 */
public class PanelPrincipal extends JPanel {
    //Paneles que dividen a nuestra interfaz en 2...
    private PanelComprador panelComprador;
    private PanelExpendedor panelExpendedor;

    private Comprador comprador;
    private Expendedor expendedor;

    /**
     * Se inicializa el comprador y el expendedor
     * con su cantidad de productos predefinida.
     * Se añaden sub-paneles a la clase.
     */
    public PanelPrincipal() {
        //Inicialización de la lógica
        this.comprador = new Comprador();
        this.expendedor = new Expendedor(5);

        //Inicialización de contenedores gráficos
        this.panelComprador = new PanelComprador(comprador, expendedor);
        this.panelExpendedor = new PanelExpendedor(expendedor, comprador);

        this.setLayout(null);//Para poder diseñar libremente;

        this.panelComprador.setOpaque(false); //Panel comprador transparente

        //Dejamos al panel comprador en toda la ventana...
        this.panelComprador.setBounds(0, 0, 600, 430);

        //El expendedor lo ubicamos al centro chocando con el piso...
        this.panelExpendedor.setBounds(175, 20, 250, 300);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);
    }

    /**
     * Dibuja los componentes de la clase.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Dibujamos la pared
        g.setColor(new Color(113, 146, 190));
        g.fillRect(0, 0, 600, 350);

        //Dibujamos el piso
        g.setColor(Color.GRAY);
        g.fillRect(0, 320, 600, 100);//Justo debajo de la expendedora
    }
}
