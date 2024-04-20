package test;

import main.domini.classes.Casella;
import main.domini.classes.Regio;
import main.domini.classes.Tauler;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestTauler {

    @Test
    public void testConstructor() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(1, tauler.getIdTauler());
        assertEquals(3, tauler.getGrado());
        assertNotNull(tauler.getRegiones());
        assertEquals(0, tauler.getRegiones().size());
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }

    /*
    @Test
    public void testAfegirRegio() {
        Tauler tauler = new Tauler(1, 3);
        Regio regio = new Regio();
        tauler.afegirRegio(regio);
        assertTrue(tauler.getRegiones().contains(regio));
    }*/

    /*
    @Test
    public void testEsborrarRegio() {
        Tauler tauler = new Tauler(1, 3);
        Regio regio = new Regio();
        tauler.afegirRegio(regio);
        tauler.esborrarRegio(regio);
        assertFalse(tauler.getRegiones().contains(regio));
    }*/

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
    public void testGetRegiones() {
        Tauler tauler = new Tauler(1, 3);
        assertNotNull(tauler.getRegiones());
        assertEquals(0, tauler.getRegiones().size());
    }

    @Test
    public void testGetCaselles() {
        Tauler tauler = new Tauler(1, 3);
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }
}
