package TestsTDD;

import ClasesDAO.ProveedorDAO;
import Modelo.Proveedor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ProvedorDAOTest {

    private ProveedorDAO dao;

    @Before
    public void setup() {
        dao = new ProveedorDAO();
    }

    // Test para insertar un proveedor
    @Test
    public void testInsertarProveedor() {
        Proveedor proveedor = new Proveedor(0, "Proveedor Test", "Contacto Test");
        boolean resultado = dao.insertarProveedor(proveedor);
        assertTrue("El proveedor debería ser insertado correctamente.", resultado);
    }

    // Test para obtener todos los proveedores
    @Test
    public void testObtenerTodosProveedores() {
        List<Proveedor> proveedores = dao.obtenerTodosProveedores();
        assertNotNull("La lista de proveedores no debe ser nula.", proveedores);
        assertTrue("Debe haber al menos un proveedor.", proveedores.size() > 0);
    }

    // Test para obtener un proveedor por ID
    @Test
    public void testObtenerProveedorPorId() {
        Proveedor proveedor = dao.obtenerProveedorPorId(1); // Asegúrate de tener un ID válido en la base de datos
        assertNotNull("El proveedor debería existir.", proveedor);
        assertEquals("El ID del proveedor debe coincidir.", 1, proveedor.getId());
    }

    // Test para actualizar un proveedor
    @Test
    public void testActualizarProveedor() {
        Proveedor proveedor = new Proveedor(1, "Proveedor Actualizado", "Contacto actualizado");
        boolean resultado = dao.actualizarProveedor(proveedor);
        assertTrue("El proveedor debería ser actualizado correctamente.", resultado);
    }

    // Test para eliminar un proveedor
    @Test
    public void testEliminarProveedor() {
        boolean resultado = dao.eliminarProveedor(1); // Asegúrate de tener un proveedor con este ID en la base de datos
        assertTrue("El proveedor debería ser eliminado correctamente.", resultado);
    }

    // Test para buscar proveedores por nombre
    @Test
    public void testBuscarProveedoresPorNombre() {
        List<Proveedor> proveedores = dao.buscarProveedoresPorNombre("Test");
        assertNotNull("La lista de proveedores no debe ser nula.", proveedores);
        assertTrue("Debe haber al menos un proveedor con el nombre 'Test'.", proveedores.size() > 0);
    }
}
