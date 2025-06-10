package TestsTDD;

import ClasesDAO.UsuarioDAO;
import Modelo.Usuario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class UsuarioDAOTest {

    private UsuarioDAO dao;

    @Before
    public void setup() {
        dao = new UsuarioDAO();
    }

    // Test para insertar un usuario
    @Test
    public void testInsertarUsuario() {
        Usuario usuario = new Usuario(0, "testUser", "password123", "admin", true);
        boolean resultado = dao.insertarUsuario(usuario);
        assertTrue("El usuario debería ser insertado correctamente.", resultado);
    }

    // Test para autenticar un usuario
    @Test
    public void testAutenticarUsuario() {
        Usuario usuario = dao.autenticar("testUser", "password123");
        assertNotNull("El usuario debería ser autenticado correctamente.", usuario);
        assertEquals("El nombre de usuario debe coincidir.", "testUser", usuario.getNombreUsuario());
    }

    // Test para listar todos los usuarios
    @Test
    public void testListarUsuarios() {
        List<Usuario> usuarios = dao.listarUsuarios();
        assertNotNull("La lista de usuarios no debe ser nula.", usuarios);
        assertTrue("Debe haber al menos un usuario.", usuarios.size() > 0);
    }

    // Test para actualizar un usuario
    @Test
    public void testActualizarUsuario() {
        Usuario usuario = new Usuario(1, "updatedUser", "newPassword123", "user", true);
        boolean resultado = dao.actualizarUsuario(usuario);
        assertTrue("El usuario debería ser actualizado correctamente.", resultado);
    }

    // Test para eliminar un usuario por ID
    @Test
    public void testEliminarUsuario() {
        boolean resultado = dao.eliminarUsuario(1); // Asegúrate de que el ID 1 exista en la base de datos
        assertTrue("El usuario debería ser eliminado correctamente.", resultado);
    }
}

