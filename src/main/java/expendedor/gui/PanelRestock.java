package expendedor.gui;

import expendedor.logica.Expendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel de mantenimiento que permite reabastecer el inventario interno de la máquina expendedora.
 */
public class PanelRestock extends JPanel {
    private Expendedor expendedor;
    private JButton botonRestock;

    /**
     * Inicializa el panel y configura el botón de recarga de productos.
     * @param expendedor Referencia al expendedor lógico a reabastecer.
     */
    public PanelRestock(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        botonRestock = new JButton("Restock");

        botonRestock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expendedor.Restock(5);
                SwingUtilities.getWindowAncestor(PanelRestock.this).repaint();
            }
        });

        this.add(botonRestock, BorderLayout.CENTER);
    }
}