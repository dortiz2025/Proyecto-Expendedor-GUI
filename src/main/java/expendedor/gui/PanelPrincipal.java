package expendedor.gui;

import expendedor.logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panel principal que contiene la interfaz gráfica completa.
 * Gestiona el fondo maestro y agrupa al expendedor y al comprador.
 */
public class PanelPrincipal extends JPanel {
    private PanelComprador panelComprador;
    private PanelExpendedor panelExpendedor;

    private Comprador comprador;
    private Expendedor expendedor;

    /**
     * Constructor que inicializa los componentes lógicos y gráficos.
     */
    public PanelPrincipal() {
        this.setLayout(null);

        this.comprador = new Comprador();
        this.expendedor = new Expendedor(5);

        this.panelExpendedor = new PanelExpendedor(expendedor, comprador);
        this.panelExpendedor.setBounds(198, 35, 195, 350);

        this.panelComprador = new PanelComprador(comprador, expendedor, this.panelExpendedor.getPanelPago());
        this.panelComprador.setBounds(0, 0, 600, 400);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);
    }

    /**
     * Dibuja la imagen de fondo de la aplicación.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image fondoEscena = GestorTexturas.getInstancia().getTextura("maquina");

        if (fondoEscena != null) {
            g.drawImage(fondoEscena, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}