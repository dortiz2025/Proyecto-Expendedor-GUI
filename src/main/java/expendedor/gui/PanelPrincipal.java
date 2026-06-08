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
        this.panelComprador.setBounds(0, 0, 600, 400);

        //El expendedor lo ubicamos al centro chocando con el piso...
        this.panelExpendedor.setBounds(198, 33, 192, 330);

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

        // Carga la imagen de toda la escena
        Image fondoEscena = GestorTexturas.getInstancia().getTextura("maquina");

        if (fondoEscena != null) {
            // Dibuja la imagen cubriendo toda la ventana (600x400)
            g.drawImage(fondoEscena, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0,0, this.getWidth(), this.getHeight());
        }
    }
}
