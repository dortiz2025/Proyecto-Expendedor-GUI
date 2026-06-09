package expendedor.gui;

import expendedor.gui.excepcionesgraficas.ProductoNoSeleccionadoException;
import expendedor.logica.*;
import expendedor.logica.excepciones.*;
import expendedor.logica.productos.TipoProducto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        int anchoCajaVerde = 100; // 50*2

        int anchoSaldo = 80;      // 40*2
        int xSaldo = (anchoCajaVerde - anchoSaldo) / 2;

        saldo = new JLabel("$0", SwingConstants.CENTER); // Cambiado a CENTER para que el texto esté centrado adentro
        saldo.setBackground(new Color(152, 193, 149));
        saldo.setOpaque(true);
        saldo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        saldo.setBounds(xSaldo, 140, anchoSaldo, 50); // 70*2, 25*2
        this.add(saldo);

        //Centrar buy
        int anchoBuy = 84; // 42*2
        int xBuy = (anchoCajaVerde - anchoBuy) / 2;

        BotonBuy btnBuy = new BotonBuy(this);
        btnBuy.setBorderPainted(false);
        btnBuy.setFocusPainted(false);
        btnBuy.setOpaque(false);
        btnBuy.setContentAreaFilled(false);
        btnBuy.setBounds(xBuy, 30, anchoBuy, 32); // 15*2, 16*2

        btnBuy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, btnBuy);
                if (principal != null) {
                    principal.setFondoActivo("maquina_select");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, btnBuy);
                if (principal != null) {
                    principal.setFondoActivo("maquina");
                }
            }
        });

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
            } catch (PagoInsuficienteException | NoHayProductoException | ProductoSinRetirarException e) {
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