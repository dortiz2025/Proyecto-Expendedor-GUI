package expendedor.gui;

import expendedor.logica.Expendedor;
import expendedor.logica.Comprador;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelPrincipal.
 * Clase que dibuja al expendedor y organiza sus sub-paneles internos.
 */
public class PanelExpendedor extends JPanel {

    private Expendedor expendedor;
    private Comprador comprador;

    private PanelVitrina panelVitrina;
    private PanelPago panelPago;
    private PanelDepositoGanancias panelDepositoGanancias;
    private PanelProductoComprado panelProductoComprado;
    private PanelVuelto panelVuelto;
    private PanelRestock panelRestock;

    /**
     * Inicializa los sub-paneles y los añade al contenedor.
     * @param expendedor Referencia del expendedor.
     * @param comprador Referencia del comprador.
     */
    public PanelExpendedor(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.comprador = comprador;

        this.setLayout(null);
        this.setOpaque(false);

        this.panelVitrina = new PanelVitrina(expendedor);
        this.panelPago = new PanelPago(expendedor);
        this.panelDepositoGanancias = new PanelDepositoGanancias(expendedor);
        this.panelProductoComprado = new PanelProductoComprado(expendedor, comprador);
        this.panelVuelto = new PanelVuelto(expendedor, comprador);
        this.panelRestock = new PanelRestock(expendedor);

        this.panelVitrina.setOpaque(false);
        this.panelPago.setOpaque(false);
        this.panelDepositoGanancias.setOpaque(false);
        this.panelProductoComprado.setOpaque(false);
        this.panelVuelto.setOpaque(false);
        this.panelRestock.setOpaque(false);

        this.panelVitrina.setBounds(54, 20, 210, 640);
        this.panelPago.setBounds(290, 20, 90, 180);
        this.panelDepositoGanancias.setBounds(296, 244, 80, 110);
        this.panelProductoComprado.setBounds(296, 540, 80, 100);
        this.panelVuelto.setBounds(296, 430, 80, 84);
        this.panelRestock.setBounds(20, 665, 100, 30);

        this.add(panelVitrina);
        this.add(panelPago);
        this.add(panelDepositoGanancias);
        this.add(panelProductoComprado);
        this.add(panelVuelto);
        this.add(panelRestock);
        this.setComponentZOrder(panelRestock, 0);
    }

    /**
     * Dibuja los componentes del panel.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    /**
     * Obtiene el panel de pago.
     * @return Referencia al PanelPago instanciado.
     */
    public PanelPago getPanelPago() {
        return this.panelPago;
    }
}