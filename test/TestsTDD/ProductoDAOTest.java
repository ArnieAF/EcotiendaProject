package TestsTDD;

import ClasesDAO.ProductoDAO;
import Modelo.Producto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ProductoDAOTest {

    private ProductoDAO dao;

    @Before
    public void setup() {
        dao = new ProductoDAO();
    }

    // Test para insertar un producto
    @Test
    public void testInsertarProducto() {
        Producto producto = new Producto(0, "Test Producto", "Descripción de prueba", 10.0f, 100, 1);
        boolean resultado = dao.insertarProducto(producto);
        assertTrue("El producto debería ser insertado correctamente.", resultado);
    }

    // Test para obtener todos los productos
    @Test
    public void testListarProductos() {
        List<Producto> productos = dao.listarProductos();
        assertNotNull("La lista de productos no debe ser nula.", productos);
        assertTrue("Debe haber al menos un producto.", productos.size() > 0);
    }

    // Test para buscar un producto por ID
    @Test
    public void testBuscarProductoPorId() {
        Producto producto = dao.buscarProductoPorId(1);
        assertNotNull("El producto debería existir.", producto);
        assertEquals("El ID del producto debe coincidir.", 1, producto.getId());
    }

    // Test para actualizar un producto
    @Test
    public void testActualizarProducto() {
        Producto producto = new Producto(1, "Producto Actualizado", "Descripción actualizada", 15.0f, 200, 2);
        boolean resultado = dao.actualizarProducto(producto);
        assertTrue("El producto debería ser actualizado correctamente.", resultado);
    }

    // Test para eliminar un producto por ID
    @Test
    public void testEliminarProducto() {
        boolean resultado = dao.eliminarProducto(1);
        assertTrue("El producto debería ser eliminado correctamente.", resultado);
    }

    // Test para obtener todos los productos (usando el método obtenerTodos)
    @Test
    public void testObtenerTodos() {
        List<Producto> productos = dao.obtenerTodos();
        assertNotNull("La lista de productos no debe ser nula.", productos);
        assertTrue("Debe haber al menos un producto.", productos.size() > 0);
    }
}

