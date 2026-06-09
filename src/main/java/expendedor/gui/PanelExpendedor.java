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
        this.panelRestock = new PanelRestock(expendedor);

        // Hacerlos transparentes
        this.panelVitrina.setOpaque(false);
        this.panelPago.setOpaque(false);
        this.panelDepositoGanancias.setOpaque(false);
        this.panelProductoComprado.setOpaque(false);
        this.panelVuelto.setOpaque(false);
        this.panelRestock.setOPaque(false);

        // Bordes de colores neón para calibrar
        //this.panelVitrina.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Vidrio de productos
        //this.panelPago.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Zona de teclado y monedas
       // this.panelDepositoGanancias.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // Recolector oculto
        //this.panelProductoComprado.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2)); // Cajón donde cae la bebida
        //this.panelVuelto.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2)); // Cajita del vuelto

        //Se definen los tamaños de cada panel de modo que estén ordenados
        //Las coordenadas son relativas a las del expendedor en sí
        this.panelVitrina.setBounds(54, 20, 210, 640);        // 27*2, 10*2, 105*2, 320*2
        this.panelPago.setBounds(290, 20, 90, 180);          // 145*2, 10*2, 45*2, 90*2
        this.panelDepositoGanancias.setBounds(296, 244, 80, 110); // 148*2, 122*2, 40*2, 55*2
        this.panelProductoComprado.setBounds(296, 540, 80, 100);  // 148*2, 270*2, 40*2, 50*2
        this.panelVuelto.setBounds(296, 430, 80, 84);        // 148*2, 215*2, 40*2, 42*2

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
