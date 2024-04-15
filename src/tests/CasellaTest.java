package tests;

import main.domini.classes.Casella;
import main.domini.excepcions.ExcepcioCasellaNoModificable;
import main.domini.excepcions.ExcepcioCasellaJaTePosicioAssignada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CasellaTest {

    @Test
    void testConstructor() {
        Casella casella = new Casella(1, 2);
        assertEquals(0, casella.getValor());
        assertEquals(1, casella.getPosX());
        assertEquals(2, casella.getPosY());
        assertTrue(casella.esModificable());
        assertTrue(casella.esBuida());
    }

    @Test
    void testSetValor() {
        Casella casella = new Casella(1, 2);
        casella.setValor(3);
        assertEquals(3, casella.getValor());
    }

    @Test
    void testBorrarValor() {
        Casella casella = new Casella(1, 2);
        casella.setValor(3);
        casella.borrarValor();
        assertEquals(0, casella.getValor());
    }

    @Test
    void testSetPosXY() {
        Casella casella = new Casella();
        casella.setPosXY(3, 4);
        assertEquals(3, casella.getPosX());
        assertEquals(4, casella.getPosY());
    }

    @Test
    void testSetInmodificable() {
        Casella casella = new Casella(1, 2);
        casella.setInmodificable();
        assertFalse(casella.esModificable());
    }

    @Test
    void testEsBuida() {
        Casella casella = new Casella(1, 2);
        assertTrue(casella.esBuida());
        casella.setValor(3);
        assertFalse(casella.esBuida());
    }

    @Test
    void testExcepcioCasellaNoModificable() {
        Casella casella = new Casella(1, 2);
        casella.setInmodificable();
        assertThrows(ExcepcioCasellaNoModificable.class, () -> casella.setValor(3));
        assertThrows(ExcepcioCasellaNoModificable.class, casella::borrarValor);
    }

    @Test
    void testExcepcioCasellaJaTePosicioAssignada() {
        Casella casella = new Casella(1, 2);
        assertThrows(ExcepcioCasellaJaTePosicioAssignada.class, () -> casella.setPosXY(3, 4));
    }
}