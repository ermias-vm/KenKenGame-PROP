package test;

import main.domini.classes.Usuari;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class UsuariTest {
    private Usuari usuari;

    @Before
    public void setUp() {
        usuari = new Usuari("testUser", "testPassword");
    }

    @Test
    public void testGetNomUsuari() {
        assertEquals("testUser", usuari.getNomUsuari());
    }

    @Test
    public void testGetContrasenya() {
        assertEquals("testPassword", usuari.getContrasenya());
    }

    @Test
    public void testSetNomUsuari() {
        usuari.setNomUsuari("newUser");
        assertEquals("newUser", usuari.getNomUsuari());
    }

    @Test
    public void testSetContrasenya() {
        usuari.setContrasenya("newPassword");
        assertEquals("newPassword", usuari.getContrasenya());
    }

    @Test
    public void testEsContrasenyaCorrecta() {
        assertTrue(usuari.esContrasenyaCorrecta("testPassword"));
        assertFalse(usuari.esContrasenyaCorrecta("wrongPassword"));
    }

    @Test
    public void testTeContrasenya() {
        assertTrue(usuari.teContrasenya());
        usuari.setContrasenya(null);
        assertFalse(usuari.teContrasenya());
    }
}