package domini.classes;

import main.domini.classes.Casella;
import main.domini.classes.Regio;
import main.domini.classes.operacions.*;
import main.domini.excepcions.*;
import main.domini.interficies.Operacio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Classe de prova per a la classe Regio.
 * Aquesta classe conté diversos mètodes de prova per a comprovar el correcte funcionament de la classe Regio.
 *
 * @author Ermias Valls Mayor
 */
public class RegioTest {
    private Regio regio;
    private ArrayList<Casella> caselles;

    /**
     * Configura l'entorn de prova abans de cada prova.
     * Crea una nova regió amb una mida, operació i resultat especificats.
     *
     * @throws Exception si es produeix un error durant la configuració de l'entorn de prova.
     */
    @Before
    public void setUp() throws Exception {
        caselles = new ArrayList<>();
        caselles.add(new Casella(0, 0));
        caselles.add(new Casella(0, 1));
        Operacio operacio = null; // Replace with an actual operation
        regio = new Regio(caselles, operacio, 3);
    }

    /**
     * Prova el constructor de la classe Regio.
     * Comprova que la regió creada té les propietats esperades.
     */
    @Test
    public void testConstructor() {
        assertEquals(2, regio.getMida());
        assertEquals(3, regio.getResultat());
        // Add more assertions for other properties
    }

    /**
     * Prova el mètode getCaselles de la classe Regio.
     * Comprova que es pot obtenir correctament les caselles de la regió.
     */
    @Test
    public void testGetCaselles() {
        assertEquals(caselles, regio.getCaselles());
    }

    /**
     * Prova el mètode getCasella de la classe Regio.
     * Comprova que es pot obtenir correctament una casella de la regió.
     */
    @Test
    public void testGetCasella() throws ExcepcionPosicioIncorrecta {
        assertEquals(caselles.get(0), regio.getCasella(0));
    }

    /**
     * Prova el mètode getValor de la classe Regio.
     * Comprova que es pot obtenir correctament el valor d'una casella de la regió.
     */
    @Test
    public void testGetValor() throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        regio.setValor(1, 5);
        assertEquals(5, regio.getValor(1));
    }

    /**
     * Prova el mètode getValor de la classe Regio.
     * Comprova que es llança una ExcepcionPosicioIncorrecta quan es demana el valor d'una casella amb una posició incorrecta.
     */
    @Test(expected = ExcepcionPosicioIncorrecta.class)
    public void testGetValorPosicioIncorrecta() throws ExcepcionPosicioIncorrecta {
        regio.getValor(10);  // Això hauria de llançar una ExcepcionPosicioIncorrecta
    }

    /**
     * Prova el mètode setValor de la classe Regio.
     * Comprova que es pot establir correctament el valor d'una casella de la regió.
     */
    @Test
    public void testSetValor() throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        regio.setValor(0, 5);
        assertEquals(5, regio.getValor(0));
    }

    /**
     * Prova el mètode setValor de la classe Regio.
     * Comprova que es llança una ExcepcionPosicioIncorrecta quan es demana establir el valor d'una casella amb una posició incorrecta.
     */
    @Test(expected = ExcepcionPosicioIncorrecta.class)
    public void testSetValorPosicioIncorrecta() throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        regio.setValor(regio.getMida(), 5);  // Això hauria de llançar una ExcepcionPosicioIncorrecta
    }

    /**
     * Prova el mètode getOperacio de la classe Regio.
     * Comprova que es pot obtenir correctament l'operació de la regió.
     */
    @Test
    public void testGetOperacio() {
        Operacio operacio = new Suma();
        regio.setOperacio(operacio);
        assertEquals(operacio, regio.getOperacio());
    }

    /**
     * Prova el mètode setOperacio de la classe Regio.
     * Comprova que es pot establir correctament l'operació de la regió.
     */
    @Test
    public void testSetOperacio() {
        Operacio operacio = new Resta();
        regio.setOperacio(operacio);
        assertEquals(operacio, regio.getOperacio());
    }

    /**
     * Prova el mètode getResultat de la classe Regio.
     * Comprova que es pot obtenir correctament el resultat de la regió.
     */
    @Test
    public void testGetResultat() {
        regio.setResultat(5);
        assertEquals(5, regio.getResultat());
    }

    /**
     * Prova el mètode seResultat de la classe Regio.
     * Comprova que es pot establir correctament el resultat de la regió.
     */
    @Test
    public void testSeResultat() {
        regio.setResultat(5);
        assertEquals(5, regio.getResultat());
    }

    /**
     * Prova el mètode getValorsCaselles de la classe Regio.
     * Comprova que es poden obtenir correctament els valors de les caselles de la regió.
     */
    @Test
    public void testGetValorsCaselles() throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        regio.setValor(0, 5);
        int[] expected = new int[regio.getMida()];
        expected[0] = 5;
        assertArrayEquals(expected, regio.getValorsCaselles());
    }

    /**
     * Prova el mètode getPosicionsCaselles de la classe Regio.
     * Comprova que es poden obtenir correctament les posicions de les caselles de la regió.
     */
    @Test
    public void testGetPosicionsCaselles() throws ExcepcionPosicioIncorrecta {
        int[][] expected = new int[regio.getMida()][2];
        for (int i = 0; i < regio.getMida(); i++) {
            expected[i][0] = regio.getCasella(i).getPosX();
            expected[i][1] = regio.getCasella(i).getPosY();
        }
        assertArrayEquals(expected, regio.getPosicionsCaselles());
    }

    /**
     * Prova el mètode afegirCasella de la classe Regio.
     * Comprova que es pot afegir correctament una casella a la regió.
     */
    @Test
    public void testAfegirCasella() throws ExcepcioMidaIncorrecte {
        Regio regio = new Regio(1, null, 1);
        Casella c = new Casella(1, 1, 1); // casella amb valor 1
        regio.afegirCasella(c);
        assertEquals(1, regio.getMida());
        assertTrue(regio.getCaselles().contains(c));
    }

    /**
     * Prova el mètode afegirCaselles de la classe Regio.
     * Comprova que es poden afegir correctament diverses caselles a la regió.
     */
    @Test
    public void testAfegirCaselles() throws ExcepcioMidaIncorrecte {
        Regio regio = new Regio(2, null, 1);
        ArrayList<Casella> caselles = new ArrayList<>();
        Casella c1 = new Casella(1, 1, 1); // casella amb valor 1
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        caselles.add(c1);
        caselles.add(c2);
        regio.afegirCaselles(caselles);
        assertEquals(2, regio.getMida());
        assertTrue(regio.getCaselles().containsAll(caselles));
    }

    /**
     * Prova el mètode esCompleta de la classe Regio.
     * Comprova que es pot determinar correctament si la regió està completa.
     */
    @Test
    public void testEsCompleta() throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        assertFalse(regio.esCompleta());
        for (int i = 0; i < regio.getMida(); i++) {
            regio.setValor(i, i + 1);
        }
        assertTrue(regio.esCompleta());
    }


    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quan la regió no està completa.
     * Crea una regió amb una sola casella sense valor i verifica que esValida() retorna false.
     */
    @Test
    public void testEsValida_RegioNoCompleta() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioNoCompleta  = new Regio(1, null,1);
        Casella c =  new Casella(); // casella sense valor
        regioNoCompleta.afegirCasella(c);
        //Es tornarà fals perquè la regió no està completa
        assertFalse(regioNoCompleta.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quan la mida de la regió és 1.
     * Crea una regió amb una sola casella amb valor i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_MidaRegio1() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioMida1 = new Regio(1, null, 1);
        Casella c = new Casella(1, 1, 1); // casella amb valor 1
        regioMida1.afegirCasella(c);
        assertTrue(regioMida1.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quan  operacio es suma i la mida de la regió és 2.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Suma2Valors() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioSuma = new Regio(2, new Suma(), 3);
        Casella c1 = new Casella(1, 1, 1); // casella amb valor 1
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        regioSuma.afegirCasella(c1);
        regioSuma.afegirCasella(c2);
        assertTrue(regioSuma.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és suma i la mida de la regió és 4.
     * Crea una regió amb quatre caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Suma4Valors() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioSuma = new Regio(4, new Suma(), 10);
        Casella c1 = new Casella(1, 1, 1); // casella amb valor 1
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        Casella c3 = new Casella(2, 1, 3); // casella amb valor 3
        Casella c4 = new Casella(2, 2, 4); // casella amb valor 4
        regioSuma.afegirCasella(c1);
        regioSuma.afegirCasella(c2);
        regioSuma.afegirCasella(c3);
        regioSuma.afegirCasella(c4);
        assertTrue(regioSuma.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és multiplicació i la mida de la regió és 2.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Multiplicacio2Valors() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioMultiplicacio = new Regio(2, new Multiplicacio(), 2);
        Casella c1 = new Casella(1, 1, 1); // casella amb valor 1
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        regioMultiplicacio.afegirCasella(c1);
        regioMultiplicacio.afegirCasella(c2);
        assertTrue(regioMultiplicacio.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és multiplicació i la mida de la regió és 4.
     * Crea una regió amb quatre caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Multiplicacio4Valors() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioMultiplicacio = new Regio(4, new Multiplicacio(), 24);
        Casella c1 = new Casella(1, 1, 1); // casella amb valor 1
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        Casella c3 = new Casella(2, 1, 3); // casella amb valor 3
        Casella c4 = new Casella(2, 2, 4); // casella amb valor 4
        regioMultiplicacio.afegirCasella(c1);
        regioMultiplicacio.afegirCasella(c2);
        regioMultiplicacio.afegirCasella(c3);
        regioMultiplicacio.afegirCasella(c4);
        assertTrue(regioMultiplicacio.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és resta.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_OperacioNoCommutativa() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioNoCommutativa = new Regio(2, new Resta(), 1);
        Casella c1 = new Casella(1, 1, 2); // casella amb valor 2
        Casella c2 = new Casella(1, 2, 1); // casella amb valor 1
        regioNoCommutativa.afegirCasella(c1);
        regioNoCommutativa.afegirCasella(c2);
        assertTrue(regioNoCommutativa.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és Divisió.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Divisio() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioDivisio = new Regio(2, new Divisio(), 2);
        Casella c1 = new Casella(1, 1, 4); // casella amb valor 4
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        regioDivisio.afegirCasella(c1);
        regioDivisio.afegirCasella(c2);
        assertTrue(regioDivisio.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és Mòdul.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Modul() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioModul = new Regio(2, new Modul(), 1);
        Casella c1 = new Casella(1, 1, 3); // casella amb valor 3
        Casella c2 = new Casella(1, 2, 2); // casella amb valor 2
        regioModul.afegirCasella(c1);
        regioModul.afegirCasella(c2);
        assertTrue(regioModul.esValida(null));
    }

    /**
     * Aquest mètode de prova verifica el comportament de la funció esValida() quando l'operació és Exponenciació.
     * Crea una regió amb dues caselles amb valors i verifica que esValida() retorna true.
     */
    @Test
    public void testEsValida_Exponenciacio() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta, ExcepcioMidaIncorrecte {
        Regio regioExponenciacio = new Regio(2, new Exponenciacio(), 8);
        Casella c1 = new Casella(1, 1, 2); // casella amb valor 2
        Casella c2 = new Casella(1, 2, 3); // casella amb valor 3
        regioExponenciacio.afegirCasella(c1);
        regioExponenciacio.afegirCasella(c2);
        assertTrue(regioExponenciacio.esValida(null));
    }
}