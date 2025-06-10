/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import Modelo.Producto;
import ClasesDAO.ProductoDAO;
import ClasesDAO.VentaDAO;
import Modelo.ItemCarrito;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class ProductosClienteVista extends javax.swing.JFrame {
    private JPanel panelCatalogo;
    private JScrollPane scrollPane;
    private JButton btnVerCarrito;
    private JButton btnComprar;

    // Representación del carrito
   private List<ItemCarrito> carrito = new ArrayList<>();
    /**
     * Creates new form ProductosClienteVista
     */
    public ProductosClienteVista() {
        setTitle("Catálogo de Productos");
        setSize(1175, 850);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columnas por fila

        scrollPane = new JScrollPane(panelCatalogo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        btnVerCarrito = new JButton("🛒 Ver Carrito");
        btnVerCarrito.addActionListener(e -> mostrarCarrito());

        btnComprar = new JButton("✅ Confirmar Compra");
        btnComprar.addActionListener(e -> realizarCompra());
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnVerCarrito);
        panelBotones.add(btnComprar);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        cargarProductos();
    }

    private void cargarProductos() {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.obtenerTodos();

        for (Producto producto : productos) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(Color.WHITE);

            JLabel nombreLabel = new JLabel("📦 " + producto.getNombre());
            JLabel precioLabel = new JLabel("💵 S/ " + producto.getPrecio());
            JLabel stockLabel = new JLabel("Stock: " + producto.getCantidad());

            JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, producto.getCantidad(), 1));
            JButton btnAgregar = new JButton("Agregar al carrito");

            btnAgregar.addActionListener(e -> {
            int cantidad = (int) spinnerCantidad.getValue();
            carrito.add(new ItemCarrito(producto, cantidad));
            JOptionPane.showMessageDialog(this, "✅ Producto agregado al carrito.");
});
            
            card.add(Box.createVerticalStrut(10));
            card.add(nombreLabel);
            card.add(precioLabel);
            card.add(stockLabel);
            card.add(spinnerCantidad);
            card.add(btnAgregar);
            card.add(Box.createVerticalStrut(10));

            panelCatalogo.add(card);
            }

        revalidate();
        repaint();
    }
    
    private void mostrarCarrito() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "🛒 El carrito está vacío.", "Carrito", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder resumen = new StringBuilder("🧾 Carrito de compras:\n\n");
        float total = 0;

        for (ItemCarrito item : carrito) {
            float subtotal = item.getSubtotal();
            resumen.append("- ").append(item.getCantidad())
                   .append(" x ").append(item.getProducto().getNombre())
                   .append(" = S/ ").append(subtotal).append("\n");
            total += subtotal;
        }

        resumen.append("\nTotal: S/ ").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(this, resumen.toString(), "🛒 Carrito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void realizarCompra() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "🛒 El carrito está vacío.");
            return;
        }

        float total = 0;
        for (ItemCarrito item : carrito) {
            total += item.getSubtotal();
        }

        VentaDAO ventaDAO = new VentaDAO();
        int idVenta = ventaDAO.registrarVenta(total);

        if (idVenta != -1) {
            ventaDAO.registrarDetalleVenta(idVenta, carrito);
            JOptionPane.showMessageDialog(this, "✅ Compra realizada. N° Venta: " + idVenta);
            carrito.clear();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar la venta.");
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 884, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductosClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductosClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductosClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductosClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
            SwingUtilities.invokeLater(() -> {
                new ProductosClienteVista().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
