package test;

import main.domini.classes.TaulerJoc;
import main.domini.classes.RegioJoc;
import main.domini.classes.Casella;

import main.domini.classes.operacions.Suma;
import main.domini.interficies.Operacio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaulerJocTest {

    private TaulerJoc taulerJoc;

    @Before
    public void setUp() throws Exception {
        taulerJoc = new TaulerJoc(1, 3);
    }

    @After
    public void tearDown() throws Exception {
        taulerJoc = null;
    }

    @Test
    public void getIdTauler() {
        assertEquals(1, taulerJoc.getIdTauler());
    }

    @Test
    public void getGrau() {
        assertEquals(3, taulerJoc.getGrau());
    }

    @Test
    public void getCasella() {
        assertNotNull(taulerJoc.getCasella(0, 0));
    }

    @Test
    public void getCaselles() {
        assertNotNull(taulerJoc.getCaselles());
    }

    @Test
    public void afegirCasella() {
        int sizeBefore = taulerJoc.getCaselles().size();
        taulerJoc.afegirCasella(new Casella(0, 1));
        int sizeAfter = taulerJoc.getCaselles().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void borrarCasella() {
        Casella casella = new Casella(0, 1);
        taulerJoc.afegirCasella(casella);
        taulerJoc.borrarCasella(casella);
        assertFalse(taulerJoc.getCaselles().contains(casella));
    }

    @Test
    public void getValor() {
        Casella casella = new Casella(0, 1);
        taulerJoc.afegirCasella(casella);
        assertEquals(1, taulerJoc.getValor(0, 0));
    }

    @Test
    public void setValor() {
        taulerJoc.setValor(0, 0, 2);
        assertEquals(2, taulerJoc.getValor(0, 0));
    }

    @Test
    public void borrarValor() {
        taulerJoc.setValor(0, 0, 2);
        taulerJoc.borrarValor(0, 0);
        assertEquals(0, taulerJoc.getValor(0, 0));
    }

    @Test
    public void esModificable() {
        assertTrue(taulerJoc.esModificable(0, 0));
    }

    @Test
    public void esBuida() {
        assertTrue(taulerJoc.esBuida(0, 0));
    }

    @Test
    public void afegirRegioJoc() {
        int sizeBefore = taulerJoc.getRegionsJoc().size();
        ArrayList<Casella> caselles = new ArrayList<>();
        caselles.add(new Casella(1, 0));
        caselles.add(new Casella(1, 1));
        caselles.add(new Casella(1, 2));
        RegioJoc regioJoc = new RegioJoc(caselles, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        int sizeAfter = taulerJoc.getRegionsJoc().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void borrarRegioJoc() {
        RegioJoc regioJoc = new RegioJoc(1, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        taulerJoc.borrarRegioJoc(regioJoc);
        assertFalse(taulerJoc.getRegionsJoc().contains(regioJoc));
    }

    @Test
    public void getRegionsJoc() {
        assertNotNull(taulerJoc.getRegionsJoc());
    }

    @Test
    public void getRegio() {
        RegioJoc regioJoc = new RegioJoc(1, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        assertEquals(regioJoc, taulerJoc.getRegio(0, 0));
    }

    @Test
    public void esFilaValida() {
        assertTrue(taulerJoc.esFilaValida(0, 1));
    }

    @Test
    public void esColumValida() {
        assertTrue(taulerJoc.esColumValida(0, 1));
    }

    @Test
    public void solucionarKenken() {
        try {
            taulerJoc.solucionarKenken(taulerJoc);
            assertTrue(taulerJoc.teSolucion());
        } catch (Exception e) {
            fail("Exception thrown during test: " + e.toString());
        }
    }
}