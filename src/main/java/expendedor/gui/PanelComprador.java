package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;
import expendedor.logica.productos.TipoProducto;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelPrincipal.
 * Clase que representa la interfaz del comprador, agrupando sus acciones
 * y los botones de selección.
 */
public class PanelComprador extends JPanel {
    private Comprador comprador;
    private PanelMonedero panelMonedero;
    private PanelInventario panelInventario;

    /**
     * Inicializa el panel del comprador y posiciona sus componentes.
     * @param comprador Referencia al comprador lógico.
     * @param expendedor Referencia al expendedor lógico.
     * @param panelPago Referencia al panel de pago.
     */
    public PanelComprador(Comprador comprador, Expendedor expendedor, PanelPago panelPago) {
        this.comprador = comprador;

        this.setLayout(null);
        this.setOpaque(false);

        int yPos = 20;
        int altoBoton = 46;
        int separacion = 13;

        for(TipoProducto tipo : TipoProducto.values()) {
            BotonSelector btnColor = new BotonSelector(panelPago, tipo);

            btnColor.setOpaque(false);
            btnColor.setContentAreaFilled(false);
            btnColor.setBorderPainted(false);
            btnColor.setBounds(1010, yPos, 160, altoBoton);

            this.add(btnColor);

            yPos += (altoBoton + separacion);
        }

        this.panelMonedero = new PanelMonedero(comprador, expendedor);
        this.panelMonedero.setBounds(20, 20, 300, 760);
        this.add(this.panelMonedero);

        this.panelInventario = new PanelInventario(comprador);
        this.panelInventario.setBounds(1000, 500, 160, 240);
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