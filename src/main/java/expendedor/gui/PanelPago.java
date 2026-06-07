package expendedor.gui;

import expendedor.gui.excepcionesgráficas.ProductoNoSeleccionadoException;
import expendedor.logica.*;
import expendedor.logica.excepciones.*;
import expendedor.logica.productos.TipoProducto;

import javax.swing.*;
import java.awt.*;

public class PanelPago extends JPanel {
    private Expendedor expendedor;//Referencia del expendedor
    private TipoProducto tipoProducto;//Guarda un tipo que se ha seleccionado

    private JLabel saldo;//Pantalla donde se muestra el saldo ingresado

    public PanelPago(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setLayout(new GridLayout(8,1));

        //Label saldo
        saldo = new JLabel("$0", SwingConstants.LEFT);
        saldo.setBackground(Color.WHITE);
        saldo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(saldo);

        add(new BotonBuy(this));

        for(TipoProducto tipo : TipoProducto.values()) {
            add(new BotonSelector(this, tipo));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
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
     */
    public void comprar() throws ProductoNoSeleccionadoException {
        if(this.tipoProducto != null) {
            try {
                expendedor.comprarProducto(this.tipoProducto);
            } catch (PagoInsuficienteException | NoHayProductoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            throw new ProductoNoSeleccionadoException("No se ha seleccionado ningún producto.");
        }
    }
}
