package expendedor.gui;

import expendedor.logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panel principal que contiene la interfaz gráfica completa.
 * Gestiona el fondo de la escena y agrupa los subsistemas del expendedor y el comprador.
 */
public class PanelPrincipal extends JPanel {
    private PanelComprador panelComprador;
    private PanelExpendedor panelExpendedor;

    private Comprador comprador;
    private Expendedor expendedor;
    private String fondoActual = "maquina";

    /**
     * Constructor que inicializa los componentes lógicos y posiciona los elementos gráficos base.
     */
    public PanelPrincipal() {
        this.setLayout(null);

        this.comprador = new Comprador();
        this.expendedor = new Expendedor(5);

        this.panelExpendedor = new PanelExpendedor(expendedor, comprador);
        this.panelExpendedor.setBounds(396, 70, 390, 700);

        this.panelComprador = new PanelComprador(comprador, expendedor, this.panelExpendedor.getPanelPago());
        this.panelComprador.setBounds(0, 0, 1200, 800);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);
    }

    /**
     * Cambia la textura de fondo activa del panel y solicita su repintado.
     * @param textura Nombre de la textura a cargar.
     */
    public void setFondoActivo(String textura) {
        this.fondoActual = textura;
        this.repaint();
    }

    /**
     * Dibuja la imagen de fondo de la aplicación.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image fondoEscena = GestorTexturas.getInstancia().getTextura(this.fondoActual);

        if (fondoEscena != null) {
            g.drawImage(fondoEscena, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}