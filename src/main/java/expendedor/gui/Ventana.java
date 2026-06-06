package expendedor.gui;

import javax.swing.*;

/**
 * Clase que representa a la ventana sobre la cual se monta el PanelPrincipal
 */
public class Ventana extends JFrame {
    public Ventana() {
        this.setTitle("Expendedor Interactivo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 430); //Se suma 30 a nuestro ideal por la barra superior
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        this.setResizable(false);

        // Se agrega el panel principal
        this.add(new PanelPrincipal());

        this.setVisible(true);
    }
}
