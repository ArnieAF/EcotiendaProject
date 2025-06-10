package TestsTDD;

import ClasesDAO.CategoriaDAO;
import Modelo.Categoria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CategoriaDAOTest {

    private CategoriaDAO dao;
    private int categoriaInsertadaId;

    @Before
    public void setUp() {
        dao = new CategoriaDAO();

        // Insertamos una categoría de prueba antes de cada test
        Categoria categoria = new Categoria();
        categoria.setNombre("TestCategoria");
        dao.insertarCategoria(categoria);

        // Recuperamos la ID insertada para pruebas posteriores
        List<Categoria> lista = dao.buscarCategoriasPorNombre("TestCategoria");
        if (!lista.isEmpty()) {
            categoriaInsertadaId = lista.get(0).getId();
        }
    }

    @After
    public void tearDown() {
        // Eliminamos la categoría de prueba después de cada test
        dao.eliminarCategoria(categoriaInsertadaId);
    }

    @Test
    public void testInsertarCategoria() {
        Categoria nueva = new Categoria();
        nueva.setNombre("CategoriaNueva");

        boolean resultado = dao.insertarCategoria(nueva);
        assertTrue(resultado);

        // Limpieza manual de la categoría agregada
        List<Categoria> lista = dao.buscarCategoriasPorNombre("CategoriaNueva");
        if (!lista.isEmpty()) {
            dao.eliminarCategoria(lista.get(0).getId());
        }
    }

    @Test
    public void testObtenerTodasCategorias() {
        List<Categoria> categorias = dao.obtenerTodasCategorias();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
    }

    @Test
    public void testObtenerCategoriaPorId() {
        Categoria categoria = dao.obtenerCategoriaPorId(categoriaInsertadaId);
        assertNotNull(categoria);
        assertEquals("TestCategoria", categoria.getNombre());
    }

    @Test
    public void testActualizarCategoria() {
        Categoria categoria = dao.obtenerCategoriaPorId(categoriaInsertadaId);
        categoria.setNombre("TestCategoriaActualizada");

        boolean actualizado = dao.actualizarCategoria(categoria);
        assertTrue(actualizado);

        Categoria actualizada = dao.obtenerCategoriaPorId(categoriaInsertadaId);
        assertEquals("TestCategoriaActualizada", actualizada.getNombre());

        // Volver al nombre original
        categoria.setNombre("TestCategoria");
        dao.actualizarCategoria(categoria);
    }

    @Test
    public void testEliminarCategoria() {
        Categoria temp = new Categoria();
        temp.setNombre("CategoriaTemporal");
        dao.insertarCategoria(temp);

        List<Categoria> lista = dao.buscarCategoriasPorNombre("CategoriaTemporal");
        assertFalse(lista.isEmpty());

        int idTemp = lista.get(0).getId();
        boolean eliminado = dao.eliminarCategoria(idTemp);
        assertTrue(eliminado);

        Categoria eliminada = dao.obtenerCategoriaPorId(idTemp);
        assertNull(eliminada);
    }

    @Test
    public void testBuscarCategoriasPorNombre() {
        List<Categoria> resultados = dao.buscarCategoriasPorNombre("TestCategoria");
        assertNotNull(resultados);
        assertTrue(resultados.size() > 0);
        assertEquals("TestCategoria", resultados.get(0).getNombre());
    }
}
