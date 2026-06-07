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
        this.setLayout(new GridLayout(8,1));

        //Label saldo
        saldo = new JLabel("$0", SwingConstants.LEFT);
        saldo.setBackground(new Color(152, 193, 149));
        saldo.setOpaque(true);
        saldo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(saldo);

        add(new BotonBuy(this));

        for(TipoProducto tipo : TipoProducto.values()) {
            add(new BotonSelector(this, tipo));
        }
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
                SwingUtilities.getWindowAncestor(this).repaint();
            } catch (PagoInsuficienteException | NoHayProductoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            throw new ProductoNoSeleccionadoException("No se ha seleccionado ningún producto.");
        }
    }
}
