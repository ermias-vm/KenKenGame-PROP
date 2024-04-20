import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.domini.classes.Casella;

import static org.junit.Assert.*;

public class CasellaTest {
    Casella casella;

    @Before
    public void setUp() throws Exception {
        casella = new Casella(1, 1);
    }

    @After
    public void tearDown() throws Exception {
        casella = null;
    }

    @Test
    public void setValor() {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    @Test
    public void borrarValor() {
        casella.setValor(5);
        casella.borrarValor();
        assertEquals(0, casella.getValor());
    }

    @Test
    public void getValor() {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    @Test
    public void getPosX() {
        assertEquals(1, casella.getPosX());
    }

    @Test
    public void getPosY() {
        assertEquals(1, casella.getPosY());
    }

    @Test
    public void setPosXY() {
        casella.setPosXY(2, 2);
        assertEquals(2, casella.getPosX());
        assertEquals(2, casella.getPosY());
    }

    @Test
    public void setInmodificable() {
        casella.setInmodificable();
        assertFalse(casella.esModificable());
    }

    @Test
    public void esModificable() {
        assertTrue(casella.esModificable());
    }

    @Test
    public void esBuida() {
        assertTrue(casella.esBuida());
        casella.setValor(5);
        assertFalse(casella.esBuida());
    }
}