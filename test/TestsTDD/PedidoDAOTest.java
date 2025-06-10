package TestsTDD;

import ClasesDAO.PedidoDAO;
import Modelo.Pedido;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Date;

public class PedidoDAOTest {

    private PedidoDAO dao;

    @Before
    public void setup() {
        dao = new PedidoDAO();
    }

    // Test para insertar un pedido
    @Test
    public void testInsertarPedido() {
        Pedido pedido = new Pedido();
        pedido.setFecha(new Date());  // Usamos la fecha actual
        pedido.setTotal(200.0f);
        
        int idPedido = dao.insertarPedido(pedido);
        assertTrue("El ID del pedido debería ser mayor que 0.", idPedido > 0);
    }

    // Test para obtener todos los pedidos
    @Test
    public void testObtenerTodosPedidos() {
        List<Pedido> pedidos = dao.obtenerTodosPedidos();
        assertNotNull("La lista de pedidos no debe ser nula.", pedidos);
        assertTrue("Debe haber al menos un pedido.", pedidos.size() > 0);
    }

    // Test para obtener un pedido por ID
    @Test
    public void testObtenerPedidoPorId() {
        int idPedido = 1;  // Asegúrate de que exista un pedido con este ID en la base de datos
        Pedido pedido = dao.obtenerPedidoPorId(idPedido);
        assertNotNull("El pedido no debe ser nulo.", pedido);
        assertEquals("El ID del pedido debe coincidir.", idPedido, pedido.getId());
    }

    // Test para actualizar un pedido
    @Test
    public void testActualizarPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1);  // Asegúrate de que este pedido exista en la base de datos
        pedido.setFecha(new Date());  // Usamos la fecha actual
        pedido.setTotal(250.0f);
        
        boolean resultado = dao.actualizarPedido(pedido);
        assertTrue("El pedido debería ser actualizado correctamente.", resultado);
    }

    // Test para eliminar un pedido
    @Test
    public void testEliminarPedido() {
        int idPedido = 1;  // Asegúrate de que este pedido exista en la base de datos
        boolean resultado = dao.eliminarPedido(idPedido);
        assertTrue("El pedido debería ser eliminado correctamente.", resultado);
    }

    // Test para obtener pedidos por rango de fechas
    @Test
    public void testObtenerPedidosPorFecha() {
        Date fechaInicio = new Date();  // Fecha de inicio
        Date fechaFin = new Date();     // Fecha de fin (puedes ajustar las fechas según sea necesario)
        
        List<Pedido> pedidos = dao.obtenerPedidosPorFecha(fechaInicio, fechaFin);
        assertNotNull("La lista de pedidos no debe ser nula.", pedidos);
        assertTrue("Debe haber al menos un pedido en el rango de fechas.", pedidos.size() > 0);
    }
}
