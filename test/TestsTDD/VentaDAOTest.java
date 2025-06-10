package TestsTDD;

import ClasesDAO.VentaDAO;
import Modelo.Venta;
import Modelo.ItemCarrito;
import Modelo.Producto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VentaDAOTest {

    private VentaDAO dao;

    @Before
    public void setup() {
        dao = new VentaDAO();
    }

    // Test para insertar una venta
    @Test
    public void testInsertarVenta() {
        Venta venta = new Venta();
        venta.setFechaVenta(new java.util.Date());  // Set current date
        venta.setTotal(200.0f);
        venta.setIdUsuario(1);
        
        boolean resultado = dao.insertarVenta(venta);
        assertTrue("La venta debería ser insertada correctamente.", resultado);
    }

    // Test para obtener todas las ventas
    @Test
    public void testObtenerVentas() {
        List<Venta> ventas = dao.obtenerVentas();
        assertNotNull("La lista de ventas no debe ser nula.", ventas);
        assertTrue("Debe haber al menos una venta.", ventas.size() > 0);
    }

    // Test para registrar una venta (sin detalles)
    @Test
    public void testRegistrarVenta() {
        float total = 150.0f;
        int idVenta = dao.registrarVenta(total);
        assertTrue("El ID de la venta registrada debería ser mayor que 0.", idVenta > 0);
    }


}
