package expendedor.gui;

import expendedor.logica.Expendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel que contiene el botón para rellenar los productos de la máquina.
 */
public class PanelRestock extends JPanel {
    private Expendedor expendedor;
    private JButton botonRestock;

    //Recibe el Expendedor completo
    public PanelRestock(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        //Creamos el botón
        botonRestock = new JButton("Restock");

        //Añadimos el listener
        botonRestock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos al método Restock para rellenar el expendedor
                expendedor.Restock(5); // 5 es el máximo de productos

                //Repintamos toda la ventana para que los productos aparezcan visualmente
                SwingUtilities.getWindowAncestor(PanelRestock.this).repaint();
            }
        });

        //Añadimos el botón al panel
        this.add(botonRestock, BorderLayout.CENTER);

    }
}
