package test;

import main.domini.classes.RegioJoc;
import main.domini.classes.Casella;
import main.domini.classes.operacions.Suma;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


/**
 * Classe de prova per a RegioJoc.
 *
 * @author Ermias Valls Mayor
 */
public class RegioJocTest {

    private RegioJoc regioJoc;
    private ArrayList<Casella> caselles;

    /**
     * Configura l'entorn de prova abans de cada prova.
     * Crea una llista de caselles i una regióJoc completa amb aquestes caselles.
     */
    @Before
    public void setUp() throws Exception {
        caselles = new ArrayList<>();
        Casella casella1 = new Casella(1, 1);
        casella1.setValor(1);
        Casella casella2 = new Casella(1, 2);
        casella2.setValor(2);
        Casella casella3 = new Casella(1, 2);
        casella3.setValor(3);

        caselles.add(casella1);
        caselles.add(casella2);
        caselles.add(casella1);
        regioJoc = new RegioJoc(caselles, new Suma(), 6);
    }

    /**
     * Neteja l'entorn de prova després de cada prova.
     * Assigna null a la regió de joc.
     */
    @After
    public void tearDown() throws Exception {
        regioJoc = null;
    }

    /**
     * Prova el constructor de RegioJoc amb mida.
     * S'espera que la regió creada no sigui nul·la.
     */
    @Test
    public void testConstructorAmbMida() {
        RegioJoc regio2 = new RegioJoc(3, new Suma(), 6);
        assertNotNull(regio2);
    }

    /**
     * Prova el constructor de RegioJoc amb caselles.
     * S'espera que la regió creada no sigui nul·la.
     */
    @Test
    public void testConstructorAmbCaselles() {
        assertNotNull(regioJoc);
    }

    /**
     * Prova l'obtenció de l'operació de la regió.
     * S'espera que l'operació no sigui nul·la.
     */
    @Test
    public void getOperacio() {
        assertNotNull(regioJoc.getOperacio());
    }

    /**
     * Prova l'establiment de l'operació de la regió.
     * S'espera que l'operació establerta sigui igual a l'operació obtinguda.
     */
    @Test
    public void setOperacio() {
        Suma novaOperacio = new Suma();
        regioJoc.setOperacio(novaOperacio);
        assertEquals(novaOperacio, regioJoc.getOperacio());
    }

    /**
     * Prova l'obtenció del resultat de la regió.
     * S'espera que el resultat obtingut sigui igual al resultat establert.
     */
    @Test
    public void getResultat() {
        assertEquals(6, regioJoc.getResultat());
    }

    /**
     * Prova l'obtenció dels valors de les caselles de la regió.
     * S'espera que els valors obtinguts siguin iguals als valors establerts.
     */
    @Test
    public void getValorsCaselles() {
        int[] esperat = {1, 2, 3};
        assertArrayEquals(esperat, regioJoc.getValorsCaselles());
    }

    /**
     * Prova si la regió està completa.
     * S'espera que la regió estigui completa inicialment i després eliminarem el valor
     * de la primera casella establint-lo a 0 i s'espera que la regio no estigui completa.
     */
    @Test
    public void esCompleta() {
        assertTrue(regioJoc.esCompleta());
        regioJoc.borra(1);
        assertFalse(regioJoc.esCompleta());
    }
    /**
     * Prova si la regió és vàlida.
     * S'espera que la regió sigui vàlida inicialment després eliminarem el valor
     * de la primera casella establint-lo a 0 i s'espera que la regio no sigui valida.
     */
    @Test
    public void esValida() throws Exception {
        //resultat = 6, operacio = suma
        //valors regio : 1, 2, 3
        assertTrue(regioJoc.esValida());
        regioJoc.setValor(0, 0);
        //valors regio : 0, 2, 3
        assertFalse(regioJoc.esValida());

    }
}