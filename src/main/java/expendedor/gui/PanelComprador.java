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

        int yPos = 10;
        int altoBoton = 23;
        int separacion = 6;

        for(TipoProducto tipo : TipoProducto.values()) {
            BotonSelector btnColor = new BotonSelector(panelPago, tipo);

            btnColor.setOpaque(false);
            btnColor.setContentAreaFilled(false);
            btnColor.setBorderPainted(false);
            btnColor.setBounds(500, yPos, 80, altoBoton);

            this.add(btnColor);

            yPos += (altoBoton + separacion);
        }

        this.panelMonedero = new PanelMonedero(comprador, expendedor);
        this.panelMonedero.setBounds(10, 10, 150, 380);
        this.add(this.panelMonedero);

        this.panelInventario = new PanelInventario(comprador);
        this.panelInventario.setBounds(500, 250, 80, 120);
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