package test;

import main.domini.classes.RegioJoc;
import main.domini.classes.Casella;
import main.domini.classes.operacions.Suma;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegioJocTest {

    private RegioJoc regioJoc;
    private ArrayList<Casella> caselles;

    @Before
    public void setUp() throws Exception {
        caselles = new ArrayList<>();
        caselles.add(new Casella(0, 0));
        caselles.add(new Casella(0, 1));
        caselles.add(new Casella(0, 2));
        regioJoc = new RegioJoc(caselles, new Suma(), 6);
    }

    @After
    public void tearDown() throws Exception {
        regioJoc = null;
    }

    @Test
    public void testConstructorAmbMida() {
        RegioJoc regio = new RegioJoc(3, new Suma(), 6);
        assertNotNull(regio);
    }

    @Test
    public void testConstructorAmbCaselles() {
        assertNotNull(regioJoc);
    }

    @Test
    public void getOperacio() {
        assertNotNull(regioJoc.getOperacio());
    }

    @Test
    public void setOperacio() {
        Suma novaOperacio = new Suma();
        regioJoc.setOperacio(novaOperacio);
        assertEquals(novaOperacio, regioJoc.getOperacio());
    }

    @Test
    public void getResultat() {
        assertEquals(6, regioJoc.getResultat());
    }

    @Test
    public void getValorsCaselles() {
        int[] esperat = {0, 0, 0};
        assertArrayEquals(esperat, regioJoc.getValorsCaselles());
    }

    @Test
    public void esCompleta() {
        assertFalse(regioJoc.esCompleta());
    }

    @Test
    public void esValida() {
        try {
            assertFalse(regioJoc.esValida());
        } catch (Exception e) {
            fail("S'ha llançat una excepció durant el test: " + e.toString());
        }
    }
}