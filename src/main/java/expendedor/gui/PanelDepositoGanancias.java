package expendedor.gui;

import expendedor.logica.*;
import expendedor.logica.monedas.Moneda;

import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelExpendedor.
 * Clase que dibuja el depósito de ganancias del expendedor.
 */
public class PanelDepositoGanancias extends JPanel {
    private Expendedor expendedor;

    /**
     * Añade los depósitos a la clase.
     * @param expendedor Referencia del expendedor.
     */
    public PanelDepositoGanancias(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 4, 0, 0));

        for (Deposito<Moneda> dep : expendedor.getDepGanancias()){
            this.add(new PanelDepositoMoneda(dep));
        }
    }

    /**
     * Dibuja los componentes de la clase.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}