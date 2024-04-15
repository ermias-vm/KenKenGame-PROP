package test;

import main.domain.classes.Casella;
import main.domain.classes.Peça;
import main.domain.classes.Tauler;

import org.junit.Test;

//import java.until.ArrayList;
//import java.until.List;

import static org.junit.Assert.*;


public class TestTauler {

    @Test
    public void testConstructor() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(1, tauler.getIdTauler());
        assertEquals(3, tauler.getGrado());
        assertNotNull(tauler.getPeçes());
        assertEquals(0, tauler.getPeçes().size());
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }

    @Test
    public void testAfegirPeça() {
        Tauler tauler = new Tauler(1, 3);
        Peça peça = new Peça();
        tauler.afegirPeça(peça);
        assertTrue(tauler.getPeçes().contains(peça));
    }

    @Test
    public void testEsborrarPeça() {
        Tauler tauler = new Tauler(1, 3);
        Peça peça = new Peça();
        tauler.afegirPeça(peça);
        tauler.esborrarPeça(peça);
        assertFalse(tauler.getPeçes().contains(peça));
    }

    @Test
    public void testAfegirCasella() {
        Tauler tauler = new Tauler(1, 3);
        Casella casella = new Casella();
        tauler.afegirCasella(casella);
        assertTrue(tauler.getCaselles().contains(casella));
    }

    @Test
    public void testEsborrarCasella() {
        Tauler tauler = new Tauler(1, 3);
        Casella casella = new Casella();
        tauler.afegirCasella(casella);
        tauler.esborrarCasella(casella);
        assertFalse(tauler.getCaselles().contains(casella));
    }

    @Test
    public void testGetIdTauler() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(1, tauler.getIdTauler());
    }

    @Test
    public void testGetGrado() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(3, tauler.getGrado());
    }

    @Test
    public void testGetPeçes() {
        Tauler tauler = new Tauler(1, 3);
        assertNotNull(tauler.getPeçes());
        assertEquals(0, tauler.getPeçes().size());
    }

    @Test
    public void testGetCaselles() {
        Tauler tauler = new Tauler(1, 3);
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }
}
