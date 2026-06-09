package expendedor.gui;

import javax.swing.*;

/**
 * Clase contenedora que representa la ventana principal de la aplicación
 * sobre la cual se monta el entorno gráfico.
 */
public class Ventana extends JFrame {

    /**
     * Inicializa y configura las dimensiones y propiedades de la ventana de la aplicación.
     */
    public Ventana() {
        this.setTitle("Expendedor Interactivo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 830);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(new PanelPrincipal());

        this.setVisible(true);
    }
}