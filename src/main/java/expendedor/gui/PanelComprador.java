package expendedor.gui;

import expendedor.logica.Comprador;
import expendedor.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private Comprador comprador;

    private PanelMonedero panelMonedero;
    private PanelInventario panelInventario;

    public PanelComprador(Comprador comprador, Expendedor expendedor) {
        this.comprador = comprador;

        this.setLayout(null);

        this.setBackground(new Color(210, 235, 210));

        //Inicializamos los sub-paneles pasándoles la lógica del comprador
        this.panelMonedero = new PanelMonedero(comprador, expendedor);
        this.panelInventario = new PanelInventario(comprador);

        //Se deja un espacio vertical para las monedas
        this.panelMonedero.setBounds(10, 10, 100, 300);
        //Cuadro para el inventario
        this.panelInventario.setBounds(460, 50, 99, 99);

        //Añadimos ambos sub-paneles a mostrar
        this.add(panelMonedero);
        this.add(panelInventario);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
