package TestsTDD;

import ClasesDAO.DetallePedidoDAO;
import Modelo.DetallePedido;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class DetallePedidoDAOTest {

    private DetallePedidoDAO dao;

    @Before
    public void setup() {
        dao = new DetallePedidoDAO();
    }

    // Test para insertar un detalle de pedido
    @Test
    public void testInsertarDetallePedido() {
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(2);
        detalle.setSubTotal(100.0f);
        detalle.setIdProducto(1);  // Asegúrate de tener un producto con este ID
        detalle.setIdPedido(1);    // Asegúrate de tener un pedido con este ID
        
        boolean resultado = dao.insertarDetallePedido(detalle);
        assertTrue("El detalle de pedido debería ser insertado correctamente.", resultado);
    }

    // Test para obtener detalles de pedido por ID de pedido
    @Test
    public void testObtenerDetallesPorPedido() {
        int idPedido = 1;  // Asegúrate de tener un pedido con este ID en la base de datos
        List<DetallePedido> detalles = dao.obtenerDetallesPorPedido(idPedido);
        assertNotNull("La lista de detalles no debe ser nula.", detalles);
        assertTrue("Debe haber al menos un detalle para el pedido.", detalles.size() > 0);
    }
}

