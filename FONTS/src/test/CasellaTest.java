package test;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import main.domini.classes.Casella;
import main.domini.excepcions.ExcepcioCasellaNoModificable;
import main.domini.excepcions.ExcepcioCasellaJaTePosicioAssignada;
import static org.junit.Assert.*;

/**
 * Classe de prova per a la classe Casella.
 * Aquesta classe conté diversos mètodes de prova per a comprovar el correcte funcionament de la classe Casella.
 *
 * @author Ermias Valls Mayor
 */
public class CasellaTest {
    /**
     * Casella que s'utilitzarà en les proves.
     */
    Casella casella;

    /**
     * Configura l'entorn de prova abans de cada prova.
     * Crea una nova casella amb posició (1,1).
     *
     * @throws Exception si es produeix un error durant la configuració de l'entorn de prova.
     */
    @Before
    public void setUp() throws Exception {
        casella = new Casella(1, 1);
    }

    /**
     * Neteja l'entorn de prova després de cada prova.
     * Assigna null a la casella.
     *
     * @throws Exception si es produeix un error durant la neteja de l'entorn de prova.
     */
    @After
    public void tearDown() throws Exception {
        casella = null;
    }

    /**
     * Prova el constructor de la classe Casella.
     * Comprova que la casella creada té les propietats esperades.
     */
    @Test
    public void testConstructor() {
        Casella casella2 = new Casella(2, 2);
        assertEquals(2, casella2.getPosX());
        assertEquals(2, casella2.getPosY());
        assertEquals(0, casella2.getValor());
        assertTrue(casella2.esModificable());
    }

    /**
     * Prova el mètode setValor de la classe Casella.
     * Comprova que el valor de la casella es pot establir correctament.
     *
     * @throws ExcepcioCasellaNoModificable si la casella no és modificable.
     */
    @Test
    public void testSetValor() throws ExcepcioCasellaNoModificable {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    /**
     * Prova el mètode setValor de la classe Casella amb una casella inmodificable.
     * Comprova que es llança una ExcepcioCasellaNoModificable quan s'intenta modificar una casella inmodificable.
     *
     * @throws ExcepcioCasellaNoModificable si la casella no és modificable.
     */
    @Test(expected = ExcepcioCasellaNoModificable.class)
    public void testSetValorCasellaInmodificable() throws ExcepcioCasellaNoModificable {
        casella.setImmodificable();
        casella.setValor(2);  // Això hauria de llançar una ExcepcioCasellaNoModificable
    }

    /**
     * Prova el mètode borrarValor de la classe Casella.
     * Comprova que el valor de la casella es pot esborrar correctament.
     *
     * @throws ExcepcioCasellaNoModificable si la casella no és modificable.
     */
    @Test
    public void testBorrarValor() throws ExcepcioCasellaNoModificable {
        casella.setValor(5);
        casella.borrarValor();
        assertEquals(0, casella.getValor());
    }

    /**
     * Prova el mètode getValor de la classe Casella.
     * Comprova que es pot obtenir correctament el valor de la casella.
     *
     * @throws ExcepcioCasellaNoModificable si la casella no és modificable.
     */
    @Test
    public void testGetValor() throws ExcepcioCasellaNoModificable {
        casella.setValor(5);
        assertEquals(5, casella.getValor());
    }

    /**
     * Prova el mètode getPosX de la classe Casella.
     * Comprova que es pot obtenir correctament la posició X de la casella.
     */
    @Test
    public void testGetPosX() {
        assertEquals(1, casella.getPosX());
    }

    /**
     * Prova el mètode getPosY de la classe Casella.
     * Comprova que es pot obtenir correctament la posició Y de la casella.
     */
    @Test
    public void testGetPosY() {
        assertEquals(1, casella.getPosY());
    }

    /**
     * Prova el mètode setPosXY de la classe Casella.
     * Comprova que es pot establir correctament la posició de la casella.
     *
     * @throws ExcepcioCasellaJaTePosicioAssignada si la casella ja té una posició assignada.
     */
    @Test
    public void testSetPosXY() throws ExcepcioCasellaJaTePosicioAssignada {
        Casella casella2 = new Casella(-1, -1); // Aquesta casella no té una posició assignada
        casella2.setPosXY(2, 2);
        assertEquals(2, casella2.getPosX());
        assertEquals(2, casella2.getPosY());
    }

    /**
     * Prova el mètode setPosXY de la classe Casella amb una casella que ja té una posició assignada.
     * Comprova que es llança una ExcepcioCasellaJaTePosicioAssignada quan s'intenta canviar la posició d'una casella que ja té una posició assignada.
     *
     * @throws ExcepcioCasellaJaTePosicioAssignada si la casella ja té una posició assignada.
     */
    @Test(expected = ExcepcioCasellaJaTePosicioAssignada.class)
    public void testSetPosXYJaAssignada() throws ExcepcioCasellaJaTePosicioAssignada {
        Casella casella2 = new Casella(1, 1); // Aquesta casella ja té una posició assignada
        casella2.setPosXY(3, 3);  // Això hauria de llançar una ExcepcioCasellaJaTePosicioAssignada
    }

    /**
     * Prova el mètode setInmodificable de la classe Casella.
     * Comprova que es pot establir correctament la casella com a inmodificable.
     */
    @Test
    public void testSetInmodificable() {
        casella.setImmodificable();
        assertFalse(casella.esModificable());
    }

    /**
     * Prova el mètode esModificable de la classe Casella.
     * Comprova que es pot obtenir correctament si la casella és modificable o no.
     */
    @Test
    public void testEsModificable() {
        assertTrue(casella.esModificable());
        casella.setImmodificable();
        assertFalse(casella.esModificable());
    }

    /**
     * Prova el mètode esBuida de la classe Casella.
     * Comprova que es pot obtenir correctament si la casella està buida o no.
     *
     * @throws ExcepcioCasellaNoModificable si la casella no és modificable.
     */
    @Test
    public void testEsBuida() throws ExcepcioCasellaNoModificable {
        assertTrue(casella.esBuida());
        casella.setValor(5);
        assertFalse(casella.esBuida());
    }
}