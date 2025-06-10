package ClasesDAO;

import Modelo.Venta;
import Conexion.DatabaseConnection;
import Modelo.ItemCarrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // Insertar una venta
    public boolean insertarVenta(Venta venta) {
        String sql = "INSERT INTO Venta (fechaVenta, total, idUsuario) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Convertir java.util.Date a java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(venta.getFechaVenta().getTime());

            stmt.setDate(1, sqlDate);
            stmt.setFloat(2, venta.getTotal());
            stmt.setInt(3, venta.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        venta.setIdVenta(generatedKeys.getInt(1)); // asignar id generado
                    }
                }
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    // Obtener todas las ventas (opcional)
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT idVenta, fechaVenta, total, idUsuario FROM Venta";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("idVenta"));
                v.setFechaVenta(rs.getDate("fechaVenta"));
                v.setTotal(rs.getFloat("total"));
                v.setIdUsuario(rs.getInt("idUsuario"));
                ventas.add(v);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener ventas: " + e.getMessage());
        }

        return ventas;
    }
    
    public int registrarVenta(float total) {
        int idVentaGenerado = -1;
        String sql = "INSERT INTO Venta (fechaVenta, total) OUTPUT INSERTED.idVenta VALUES (GETDATE(), ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setFloat(1, total);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idVentaGenerado = rs.getInt("idVenta");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar venta: " + e.getMessage());
        }

        return idVentaGenerado;
    }
    
    public void registrarDetalleVenta(int idVenta, List<ItemCarrito> carrito) {
        String sql = "INSERT INTO DetalleVenta (idVenta, idProducto, cantidad, subTotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (ItemCarrito item : carrito) {
                pstmt.setInt(1, idVenta);
                pstmt.setInt(2, item.getProducto().getId());
                pstmt.setInt(3, item.getCantidad());
                pstmt.setFloat(4, item.getSubtotal());
                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar detalle de venta: " + e.getMessage());
        }
    }
}
