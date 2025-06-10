package TestsTDD;

import ClasesDAO.InventarioDAO;
import Modelo.Inventario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class InventarioDAOTest {

    private InventarioDAO dao;

    @Before
    public void setup() {
        dao = new InventarioDAO();
    }

    // Test para insertar un inventario
    @Test
    public void testInsertarInventario() {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1);  // Asegúrate de que el producto existe
        inventario.setCantidadEnStock(100);
        inventario.setStockMinimo(10);
        
        boolean resultado = dao.insertarInventario(inventario);
        assertTrue("El inventario debería ser insertado correctamente.", resultado);
    }

    // Test para obtener todo el inventario
    @Test
    public void testObtenerTodoInventario() {
        List<Inventario> inventarios = dao.obtenerTodoInventario();
        assertNotNull("La lista de inventarios no debe ser nula.", inventarios);
        assertTrue("Debe haber al menos un inventario.", inventarios.size() > 0);
    }

    // Test para obtener un inventario por idProducto
    @Test
    public void testObtenerInventarioPorProducto() {
        int idProducto = 1;  // Asegúrate de que este producto exista en la base de datos
        Inventario inventario = dao.obtenerInventarioPorProducto(idProducto);
        assertNotNull("El inventario no debe ser nulo.", inventario);
        assertEquals("El ID del producto debe coincidir.", idProducto, inventario.getIdProducto());
    }

    // Test para actualizar inventario
    @Test
    public void testActualizarInventario() {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1);  // Asegúrate de que este producto exista en la base de datos
        inventario.setCantidadEnStock(120);
        inventario.setStockMinimo(15);
        
        boolean resultado = dao.actualizarInventario(inventario);
        assertTrue("El inventario debería ser actualizado correctamente.", resultado);
    }

    // Test para eliminar un inventario
    @Test
    public void testEliminarInventario() {
        int idProducto = 1;  // Asegúrate de que este producto exista en la base de datos
        boolean resultado = dao.eliminarInventario(idProducto);
        assertTrue("El inventario debería ser eliminado correctamente.", resultado);
    }

    // Test para obtener productos con stock bajo
    @Test
    public void testObtenerProductosStockBajo() {
        List<Inventario> inventarios = dao.obtenerProductosStockBajo();
        assertNotNull("La lista de inventarios con stock bajo no debe ser nula.", inventarios);
        assertTrue("Debe haber al menos un producto con stock bajo.", inventarios.size() > 0);
    }

    // Test para actualizar cantidad en stock
    @Test
    public void testActualizarCantidadStock() {
        int idProducto = 1;  // Asegúrate de que este producto exista en la base de datos
        int nuevaCantidad = 150;  // Nueva cantidad en stock
        
        boolean resultado = dao.actualizarCantidadStock(idProducto, nuevaCantidad);
        assertTrue("La cantidad en stock debería ser actualizada correctamente.", resultado);
    }
}
