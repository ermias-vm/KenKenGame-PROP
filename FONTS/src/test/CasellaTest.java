package  test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.domini.classes.Casella;

import static org.junit.Assert.*;

/**
 * Classe de prova per a Casella.
 *
 * @author Ermias Valls Mayor
 */
public class CasellaTest {
    Casella casella;

    /**
     * Configura l'entorn de prova abans de cada prova.
     * Crea una nova casella amb posició (1,1).
     */
    @Before
    public void setUp() throws Exception {
        casella = new Casella(1, 1);
    }

    /**
     * Neteja l'entorn de prova després de cada prova.
     * Assigna null a la casella.
     */
    @After
    public void tearDown() throws Exception {
        casella = null;
    }

    /**
     * Prova l'establiment del valor de la casella.
     * S'espera que el valor establert sigui igual al valor obtingut.
     */
    @Test
    public void setValor() {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    /**
     * Prova l'eliminació del valor de la casella.
     * S'espera que el valor obtingut sigui 0 després de l'eliminació.
     */
    @Test
    public void borrarValor() {
        casella.setValor(5);
        casella.borrarValor();
        assertEquals(0, casella.getValor());
    }


    /**
     * Prova l'obtenció del valor de la casella.
     * S'espera que el valor obtingut sigui igual al valor establert.
     */
    @Test
    public void getValor() {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    /**
     * Prova l'obtenció de la posició X de la casella.
     * S'espera que la posició X obtinguda sigui igual a la posició X establerta.
     */
    @Test
    public void getPosX() {
        assertEquals(1, casella.getPosX());
    }

    /**
     * Prova l'obtenció de la posició Y de la casella.
     * S'espera que la posició Y obtinguda sigui igual a la posició Y establerta.
     */
    @Test
    public void getPosY() {
        assertEquals(1, casella.getPosY());
    }

    @Test
    /**
     * Prova l'obtenció de la posició Y de la casella.
     * S'espera que la posició Y obtinguda sigui igual a la posició Y establerta.
     */
    public void setPosXY() {
        casella.setPosXY(2, 2);
        assertEquals(2, casella.getPosX());
        assertEquals(2, casella.getPosY());
    }

    /**
     * Prova l'establiment de la casella com a inmodificable.
     * S'espera que la casella no sigui modificable després de l'establiment.
     */
    @Test
    public void setInmodificable() {
        casella.setInmodificable();
        assertFalse(casella.esModificable());
    }

    /**
     * Prova si la casella és modificable.
     * S'espera que la casella sigui modificable inicialment i despres de ferla inmodificable s
     * sespera que la casella no sigui modificable.
     */
    @Test
    public void esModificable() {
        assertTrue(casella.esModificable());
        casella.setInmodificable();
        assertFalse(casella.esModificable());
    }

    /**
     * Prova si la casella està buida.
     * S'espera que la casella estigui buida inicialment i després no estigui buida després d'establir un valor.
     */
    @Test
    public void esBuida() {
        assertTrue(casella.esBuida());
        casella.setValor(5);
        assertFalse(casella.esBuida());
    }
}