package test;

import main.domini.classes.Usuari;
import main.domini.classes.Tauler;

//import org.junit.*;
import org.junit.Test;


//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.io.*;

import static org.junit.Assert.*;

/**
 * Classe de prova per a Usuari.
 *
 * @author David Giribet
 */
public class TestUsuari {

    /**
     * Prueba del constructor de la clase Usuari.
     * Verifica que el constructor inicializa correctamente el objeto Usuari
     * con el nombre de usuario y la contraseña dados, y que inicializa la lista de tableros.
     */
    @Test
    public void testConstructor() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("nomUsuari", usuari.getNom());
        assertEquals("contrasenya123", usuari.getContrasenya());
        assertNotNull(usuari.getTaulers());
        assertEquals(0, usuari.getTaulers().size());
    }

    /**
     * Prueba del método afegirTauler() de la clase Usuari.
     * Verifica que se agrega correctamente un tablero a la lista de tableros del usuario.
     */
    @Test
    public void testAfegirTauler() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        Tauler tauler = new Tauler(1, 3);
        usuari.afegirTauler(tauler);
        assertTrue(usuari.getTaulers().contains(tauler));
    }

    /**
     * Prueba del método esborrarTauler() de la clase Usuari.
     * Verifica que se elimina correctamente un tablero de la lista de tableros del usuario.
     */
    @Test
    public void testEsborrarTauler() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        Tauler tauler = new Tauler(1, 3);
        usuari.afegirTauler(tauler);
        usuari.esborrarTauler(tauler);
        assertFalse(usuari.getTaulers().contains(tauler));
    }

    /**
     * Prueba del método getNom() de la clase Usuari.
     * Verifica que se devuelve correctamente el nombre de usuario.
     */
    @Test
    public void testGetNom() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("nomUsuari", usuari.getNom());
    }

    /**
     * Prueba del método getContrasenya() de la clase Usuari.
     * Verifica que se devuelve correctamente la contraseña del usuario.
     */
    @Test
    public void testGetContrasenya() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("contrasenya123", usuari.getContrasenya());
    }

    /**
     * Prueba del método getTaulers() de la clase Usuari.
     * Verifica que se devuelve correctamente la lista de tableros del usuario.
     */
    @Test
    public void testGetTaulers() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertNotNull(usuari.getTaulers());
        assertEquals(0, usuari.getTaulers().size());
    }
}
