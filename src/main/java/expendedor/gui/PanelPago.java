package expendedor.gui;

import expendedor.gui.excepcionesgraficas.ProductoNoSeleccionadoException;
import expendedor.logica.*;
import expendedor.logica.excepciones.*;
import expendedor.logica.productos.TipoProducto;
import javax.swing.*;
import java.awt.*;

/**
 * Sub-panel de PanelExpendedor.
 * Clase que permite organizar el código para el dibujo
 * y el manejo de eventos en la compra y selección de productos.
 */
public class PanelPago extends JPanel {
    private Expendedor expendedor;//Referencia del expendedor
    private TipoProducto tipoProducto;//Guarda un tipo que se ha seleccionado

    private JLabel saldo;//Pantalla donde se muestra el saldo ingresado

    /**
     * Se agregan los botones ordenadamente.
     * @param expendedor Referencia del expendedor para poder manejar una compra.
     */
    public PanelPago(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);

        this.setLayout(null);
        int anchoCajaVerde = 50;

        int anchoSaldo = 40; //
        int xSaldo = (anchoCajaVerde - anchoSaldo) / 2;

        saldo = new JLabel("$0", SwingConstants.CENTER); // Cambiado a CENTER para que el texto esté centrado adentro
        saldo.setBackground(new Color(152, 193, 149));
        saldo.setOpaque(true);
        saldo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        saldo.setBounds(xSaldo-3, 70, anchoSaldo, 25);
        this.add(saldo);

        //Centrar buy
        int anchoBuy = 42;
        int xBuy = (anchoCajaVerde - anchoBuy) / 2;

        BotonBuy btnBuy = new BotonBuy(this);
        btnBuy.setOpaque(false);
        btnBuy.setContentAreaFilled(false);
        btnBuy.setBorderPainted(true);
        btnBuy.setBounds(xBuy, 15, anchoBuy, 16);
        this.add(btnBuy);
    }

    /**
     * Actualiza el saldo en pantalla.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        saldo.setText("$" + expendedor.getSaldo());
    }

    /**
     * Permite seleccionar un producto.
     * @param tipo tipo de producto seleccionado
     */
    public void seleccionarProducto(TipoProducto tipo){
        this.tipoProducto = tipo;
    }

    /**
     * Comprar un producto.
     * @throws ProductoNoSeleccionadoException Excepción inicial al presionar buy.
     */
    public void comprar() throws ProductoNoSeleccionadoException {
        if(this.tipoProducto != null) {
            try {
                expendedor.comprarProducto(this.tipoProducto);

                this.tipoProducto = null;

                SwingUtilities.getWindowAncestor(this).repaint();
            } catch (PagoInsuficienteException | NoHayProductoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            throw new ProductoNoSeleccionadoException("No se ha seleccionado ningún producto.");
        }
    }
    /**
     * Permite saber qué producto está seleccionado actualmente.
     * @return Tipo de producto seleccionado.
     */
    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }
}