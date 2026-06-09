package expendedor.gui;

import expendedor.logica.Expendedor;
import expendedor.logica.Comprador;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelPrincipal
 * Clase que dibuja al expendedor, contiene sub-paneles
 * que permiten organizar el código gráfico.
 */
public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private Comprador comprador;

    //Sub-Paneles
    private PanelVitrina panelVitrina;
    private PanelPago panelPago;
    private PanelDepositoGanancias panelDepositoGanancias;
    private PanelProductoComprado panelProductoComprado;
    private PanelVuelto panelVuelto;

    /**
     * Se inicializan los sub-paneles y se añaden a la clase.
     * @param expendedor Referencia del expendedor.
     */
    public PanelExpendedor(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.comprador = comprador;

        this.setLayout(null); // Diseño libre
        //this.setBackground(new Color(0, 75, 115));//Fondo Expendedor

        this.setOpaque(false); //Lo hace invisible

        //Se inicializan los sub-paneles
        this.panelVitrina =  new PanelVitrina(expendedor);
        this.panelPago =  new PanelPago(expendedor);
        this.panelDepositoGanancias = new PanelDepositoGanancias(expendedor);
        this.panelProductoComprado = new PanelProductoComprado(expendedor, comprador);
        this.panelVuelto = new PanelVuelto(expendedor, comprador);

        // Hacerlos transparentes
        this.panelVitrina.setOpaque(false);
        this.panelPago.setOpaque(false);
        this.panelDepositoGanancias.setOpaque(false);
        this.panelProductoComprado.setOpaque(false);
        this.panelVuelto.setOpaque(false);

        // Bordes de colores neón para calibrar
        //this.panelVitrina.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Vidrio de productos
        //this.panelPago.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Zona de teclado y monedas
       // this.panelDepositoGanancias.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // Recolector oculto
        //this.panelProductoComprado.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2)); // Cajón donde cae la bebida
        //this.panelVuelto.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2)); // Cajita del vuelto

        //Se definen los tamaños de cada panel de modo que estén ordenados
        //Las coordenadas son relativas a las del expendedor en sí
        this.panelVitrina.setBounds(27, 10, 105, 320);
        this.panelPago.setBounds(145, 10, 45, 90);
        this.panelDepositoGanancias.setBounds(148, 122, 40, 55);
        this.panelProductoComprado.setBounds(148, 270, 40, 50);
        this.panelVuelto.setBounds(148, 215, 40, 42);

        //Se añaden al expendedor
        this.add(panelVitrina);
        this.add(panelPago);
        this.add(panelDepositoGanancias);
        this.add(panelProductoComprado);
        this.add(panelVuelto);
    }

    /**
     * Dibuja los componentes del panel.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public PanelPago getPanelPago() {
        return this.panelPago;
    }
}
