package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelExpendedor encargado de agrupar visualmente la lista de espera del vuelto
 * y el área de recolección final de las monedas.
 */
public class PanelVuelto extends JPanel {
    private Expendedor expendedor;

    /**
     * Inicializa los componentes relacionados con la devolución de dinero.
     * @param expendedor Referencia al expendedor lógico.
     * @param comprador Referencia al comprador lógico receptor.
     */
    public PanelVuelto(Expendedor expendedor, Comprador comprador) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 2));

        this.add(new PanelDepositoMoneda(expendedor.getDepVuelto()));
        this.add(new PanelRetiroVuelto(expendedor, comprador));
    }

    /**
     * Gestiona el pintado del componente contenedor de vuelto.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}