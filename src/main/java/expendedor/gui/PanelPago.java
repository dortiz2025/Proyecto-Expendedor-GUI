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
 * Administra la interfaz de transacciones, mostrando el saldo actual y permitiendo ejecutar compras.
 */
public class PanelPago extends JPanel {
    private Expendedor expendedor;
    private TipoProducto tipoProducto;
    private JLabel saldo;

    /**
     * Configura el panel de pago, inicializando la pantalla de saldo y el botón de compra.
     * @param expendedor Referencia al modelo lógico del expendedor.
     */
    public PanelPago(Expendedor expendedor) {
        this.expendedor = expendedor;
        this.setOpaque(false);
        this.setLayout(null);

        int anchoCajaVerde = 100;
        int anchoSaldo = 80;
        int xSaldo = (anchoCajaVerde - anchoSaldo) / 2;

        saldo = new JLabel("$0", SwingConstants.CENTER);
        saldo.setBackground(new Color(152, 193, 149));
        saldo.setOpaque(true);
        saldo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        saldo.setBounds(xSaldo, 140, anchoSaldo, 50);
        this.add(saldo);

        int anchoBuy = 84;
        int xBuy = (anchoCajaVerde - anchoBuy) / 2;

        BotonBuy btnBuy = new BotonBuy(this);
        btnBuy.setBorderPainted(false);
        btnBuy.setFocusPainted(false);
        btnBuy.setOpaque(false);
        btnBuy.setContentAreaFilled(false);
        btnBuy.setBounds(xBuy, 30, anchoBuy, 32);

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
     * Actualiza visualmente el saldo en pantalla según los ingresos en la máquina.
     * @param g Entorno gráfico.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        saldo.setText("$" + expendedor.getSaldo());
    }

    /**
     * Establece el producto que se intentará comprar.
     * @param tipo Tipo de producto seleccionado.
     */
    public void seleccionarProducto(TipoProducto tipo){
        this.tipoProducto = tipo;
    }

    /**
     * Procesa la compra del producto seleccionado.
     * @throws ProductoNoSeleccionadoException Si se intenta comprar sin elegir un producto previamente.
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
     * Devuelve el producto seleccionado en el momento.
     * @return TipoProducto actualmente seleccionado.
     */
    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }
}