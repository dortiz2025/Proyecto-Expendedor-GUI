package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;
import expendedor.logica.productos.TipoProducto;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelPrincipal.
 * Clase que representa la interfaz del comprador, incluyendo sus acciones
 * y los botones de selección de productos.
 */
public class PanelComprador extends JPanel {
    private Comprador comprador;
    private PanelMonedero panelMonedero;
    private PanelInventario panelInventario;

    /**
     * Inicializa el panel del comprador con sus respectivos componentes gráficos.
     * @param comprador Referencia al comprador lógico.
     * @param expendedor Referencia al expendedor lógico.
     * @param panelPago Referencia al panel de pago para enviar la selección.
     */
    public PanelComprador(Comprador comprador, Expendedor expendedor, PanelPago panelPago) {
        this.comprador = comprador;

        this.setLayout(null);
        this.setOpaque(false);

        int yPos = 20; // 10*2
        int altoBoton = 46; // 23*2
        int separacion = 13; // 6*2

        for(TipoProducto tipo : TipoProducto.values()) {
            BotonSelector btnColor = new BotonSelector(panelPago, tipo);

            btnColor.setOpaque(false);
            btnColor.setContentAreaFilled(false);
            btnColor.setBorderPainted(false);
            btnColor.setBounds(1010, yPos, 160, altoBoton); // 500*2, 80*2

            this.add(btnColor);

            yPos += (altoBoton + separacion);
        }

        this.panelMonedero = new PanelMonedero(comprador, expendedor);
        this.panelMonedero.setBounds(20, 20, 300, 760); // 10*2, 150*2, 380*2
        this.add(this.panelMonedero);

        this.panelInventario = new PanelInventario(comprador);
        this.panelInventario.setBounds(1000, 500, 160, 240); // 500*2, 250*2, 80*2, 120*2
        this.add(this.panelInventario);
    }

    /**
     * Dibuja los componentes del panel.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}