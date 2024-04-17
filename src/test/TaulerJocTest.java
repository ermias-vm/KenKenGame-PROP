package test;

import main.domini.classes.TaulerJoc;
import main.domini.classes.RegioJoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaulerJocTest {
    private TaulerJoc taulerJoc;

    @BeforeEach
    public void setUp() {
        taulerJoc = new TaulerJoc(1, 3);
    }

    @Test
    public void testGetValor() {
        taulerJoc.setValor(1, 1, 2);
        assertEquals(2, taulerJoc.getValor(1, 1));
    }

    @Test
    public void testSetValor() {
        taulerJoc.setValor(1, 1, 2);
        assertEquals(2, taulerJoc.getValor(1, 1));
    }

    @Test
    public void testBorrarValor() {
        taulerJoc.setValor(1, 1, 2);
        taulerJoc.borrarValor(1, 1);
        assertEquals(0, taulerJoc.getValor(1, 1));
    }

    @Test
    public void testEsModificable() {
        assertTrue(taulerJoc.esModificable(1, 1));
    }

    @Test
    public void testEsBuida() {
        assertTrue(taulerJoc.esBuida(1, 1));
    }

    @Test
    public void testAfegirRegioJoc() {
        RegioJoc regioJoc = new RegioJoc();
        taulerJoc.afegirRegioJoc(regioJoc);
        assertTrue(taulerJoc.getRegionsJoc().contains(regioJoc));
    }

    @Test
    public void testBorrarRegioJoc() {
        RegioJoc regioJoc = new RegioJoc();
        taulerJoc.afegirRegioJoc(regioJoc);
        taulerJoc.borrarRegioJoc(regioJoc);
        assertFalse(taulerJoc.getRegionsJoc().contains(regioJoc));
    }

    @Test
    public void testEsFilaValida() {
        taulerJoc.setValor(1, 1, 2);
        assertFalse(taulerJoc.esFilaValida(1, 2));
        assertTrue(taulerJoc.esFilaValida(1, 3));
    }

    @Test
    public void testEsColumValida() {
        taulerJoc.setValor(1, 1, 2);
        assertFalse(taulerJoc.esColumValida(1, 2));
        assertTrue(taulerJoc.esColumValida(1, 3));
    }
}