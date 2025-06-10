package TestsTDD;

import ClasesDAO.DetalleVentaDAO;
import Modelo.DetalleVenta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class DetalleVentaDAOTest {

    private DetalleVentaDAO dao;

    @Before
    public void setup() {
        dao = new DetalleVentaDAO();
    }

    // Test para insertar detalle de venta
    @Test
    public void testInsertarDetalleVenta() {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdVenta(1);   // Asegúrate de que existe una venta con este ID
        detalle.setIdProducto(1); // Asegúrate de que existe un producto con este ID
        detalle.setCantidad(2);   // Cantidad de productos
        detalle.setSubTotal(100.0f);  // Total de este detalle

        boolean resultado = dao.insertarDetalleVenta(detalle);
        assertTrue("El detalle de venta debería ser insertado correctamente.", resultado);
    }

    // Test para obtener detalles por idVenta
    @Test
    public void testObtenerDetallesPorVenta() {
        int idVenta = 1;  // Asegúrate de que existe una venta con este ID
        List<DetalleVenta> detalles = dao.obtenerDetallesPorVenta(idVenta);
        assertNotNull("La lista de detalles no debe ser nula.", detalles);
        assertTrue("Debe haber al menos un detalle para la venta.", detalles.size() > 0);
    }
}
