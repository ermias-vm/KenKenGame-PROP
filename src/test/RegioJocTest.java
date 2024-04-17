package test;

import main.domini.classes.RegioJoc;
import main.domini.classes.Casella;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegioJocTest {
    private RegioJoc regioJoc;

    @BeforeEach
    public void setUp() {
        regioJoc = new RegioJoc(3, 1, 6);
    }

    @Test
    public void testGetOperacio() {
        assertEquals(1, regioJoc.getOperacio());
    }

    @Test
    public void testSetOperacio() {
        regioJoc.setOperacio(2);
        assertEquals(2, regioJoc.getOperacio());
    }

    @Test
    public void testGetResultat() {
        assertEquals(6, regioJoc.getResultat());
    }

    @Test
    public void testSetResultat() {
        regioJoc.setResultat(9);
        assertEquals(9, regioJoc.getResultat());
    }

    @Test
    public void testEsCompleta() {
        assertFalse(regioJoc.esCompleta());
        regioJoc.getCaselles().add(new Casella(1, 2));
        regioJoc.getCaselles().add(new Casella(2, 3));
        regioJoc.getCaselles().add(new Casella(3, 4));
        assertTrue(regioJoc.esCompleta());
    }

    @Test
    public void testEsValida() {
        assertTrue(regioJoc.esValida());
    }
}