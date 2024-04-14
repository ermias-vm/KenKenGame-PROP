package test;

import main.domain.classes.Usuari;
import main.domain.classes.Tauler;

//import org.junit.*;
import org.junit.Test;


//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.io.*;

import static org.junit.Assert.*;


public class TestUsuari {

    @Test
    public void testConstructor() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("nomUsuari", usuari.getNom());
        assertEquals("contrasenya123", usuari.getContrasenya());
        assertNotNull(usuari.getTaulers());
        assertEquals(0, usuari.getTaulers().size());
    }

    @Test
    public void testAfegirTauler() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        Tauler tauler = new Tauler(1, 3);
        usuari.afegirTauler(tauler);
        assertTrue(usuari.getTaulers().contains(tauler));
    }

    @Test
    public void testEsborrarTauler() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        Tauler tauler = new Tauler(1, 3);
        usuari.afegirTauler(tauler);
        usuari.esborrarTauler(tauler);
        assertFalse(usuari.getTaulers().contains(tauler));
    }

    @Test
    public void testGetNom() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("nomUsuari", usuari.getNom());
    }

    @Test
    public void testGetContrasenya() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertEquals("contrasenya123", usuari.getContrasenya());
    }

    @Test
    public void testGetTaulers() {
        Usuari usuari = new Usuari("nomUsuari", "contrasenya123");
        assertNotNull(usuari.getTaulers());
        assertEquals(0, usuari.getTaulers().size());
    }
}
