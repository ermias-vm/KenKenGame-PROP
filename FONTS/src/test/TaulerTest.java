package test;

import main.domini.classes.Tauler;
import main.domini.classes.Casella;
import main.domini.classes.Regio;
import main.domini.classes.operacions.*;
import main.domini.excepcions.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaulerTest {

    @Test
    public void testGetIdTauler() {
        Tauler tauler = new Tauler("testId", 5);
        assertEquals("testId", tauler.getIdTauler());
    }

    @Test
    public void testGetGrau() {
        Tauler tauler = new Tauler("testId", 5);
        assertEquals(5, tauler.getGrau());
    }

    @Test
    public void testTeSolucio() {
        Tauler tauler = new Tauler("testId", 5);
        assertFalse(tauler.teSolucio());
    }

    @Test
    public void testSetTrobat() {
        Tauler tauler = new Tauler("testId", 5);
        tauler.setTrobat(true);
        assertTrue(tauler.teSolucio());
    }

    @Test
    public void testAfegirCasella() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        assertEquals(casella, tauler.getCasella(1, 1));
    }

    @Test(expected = ExcepcioCasellaNoExisteix.class)
    public void testAfegirCasellaPosicioIncorrecte() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(6, 6, casella);
    }


    @Test
    public void testGetCasella() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        assertEquals(casella, tauler.getCasella(1, 1));
    }

    @Test(expected = ExcepcioCasellaNoExisteix.class)
    public void testGetCasellaPosicioIncorrecta() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        tauler.getCasella(6, 6); // Això hauria de llançar una excepció
    }

    @Test
    public void testGetValor() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(4, 1, 3, true);
        tauler.afegirCasella(1, 1, casella);
        assertEquals(4, tauler.getValor(1, 1));
    }

    @Test
    public void testSetValor() throws ExcepcioCasellaNoExisteix, ExcepcioCasellaNoModificable {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        tauler.setValor(1, 1, 2);
        assertEquals(2, tauler.getValor(1, 1));
    }

    @Test
    public void testSetValorIbloquejar() throws ExcepcioCasellaNoExisteix, ExcepcioCasellaNoModificable {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        tauler.setValorIbloquejar(1, 1, 2);
        assertEquals(2, tauler.getValor(1, 1));
        assertFalse(tauler.esModificable(1, 1));
    }

    @Test
    public void testBorrarValor() throws ExcepcioCasellaNoExisteix, ExcepcioCasellaNoModificable {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        tauler.borrarValor(1, 1);
        assertEquals(0, tauler.getValor(1, 1));
    }


    @Test
    public void testEsModificable() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        Casella casella = new Casella(1, 1, 1, true);
        tauler.afegirCasella(1, 1, casella);
        assertTrue(tauler.esModificable(1, 1));
    }

    @Test
    public void testAfegirRegioJoc() throws ExcepcioMidaIncorrecte {
        Tauler tauler = new Tauler("testId", 5);
        Regio regio = new Regio(1, new Suma(), 2);
        tauler.afegirRegio(regio);
        assertTrue(tauler.getRegions().contains(regio));
    }

    @Test
    public void testGetRegio() throws ExcepcionPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioMidaIncorrecte {
        Tauler tauler = new Tauler("testId", 5);

        Regio regio = new Regio(1, new Suma(), 2);
        Casella casella = new Casella(1, 1, 1, true);
        regio.afegirCasella(casella);
        tauler.afegirCasella(1, 1, casella);
        tauler.afegirRegio(regio);
        assertEquals(regio, tauler.getRegio(1, 1));
    }


    @Test
    public void testEsFilaValida() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        for (int i = 1; i <= 5; ++i) {
            Casella casella = new Casella(i, 1, i, true);
            tauler.afegirCasella(1, i, casella);
        }
        assertFalse(tauler.esFilaValida(1, 1)); // El número 1 ja existeix a la fila
        assertTrue(tauler.esFilaValida(1, 6)); // El número 6 no existeix a la fila
    }

    @Test
    public void testEsColumValida() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 5);
        for (int i = 1; i <= 5; ++i) {
            Casella casella = new Casella(1, i, i, true);
            tauler.afegirCasella(i, 1, casella);
        }
        assertFalse(tauler.esColumValida(1, 1)); // El número 1 ja existeix a la columna
        assertTrue(tauler.esColumValida(1, 6)); // El número 6 no existeix a la columna
    }

    @Test
    public void testCorretgeix()  {
        Tauler tauler = new Tauler("testId", 3);
        int[][] valors = new int[3][3];

        for (int i = 0; i < 3; ++i)  valors[0][i] = i + 1;// valors primera fila = 1, 2, 3
        assertTrue(tauler.corretgeix(valors)); // Tots els valors són únics per fila i columna

        valors[0][2] = 1; // primera fila  = 1, 3, 1 (es repeteix  1)
        assertFalse(tauler.corretgeix(valors)); // La primera fila no és vàlida, 1 es repeteix
    }


    @Test
    public void testGetValorsRegioMatriu() {
        Tauler tauler = new Tauler("testId", 5);
        int[][] valorsTauler = new int[5][5];
        int[][] posicionsRegio = new int[5][2];
        for (int i = 0; i < 5; ++i) {
            valorsTauler[i][i] = i + 1;
            posicionsRegio[i][0] = i + 1;
            posicionsRegio[i][1] = i + 1;
        }
        int[] expectedValorsRegio = {1, 2, 3, 4, 5};
        assertArrayEquals(expectedValorsRegio, tauler.getValorsRegioMatriu(valorsTauler, posicionsRegio));
    }

    @Test
    public void testGetRegionsIncorrectes() throws ExcepcioCasellaNoExisteix, ExcepcioMidaIncorrecte {
        Tauler tauler = new Tauler("testId", 5);
        int[][] valorsTauler = new int[5][5];
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                valorsTauler[i][j] = i + 1;
            }
        }
        Regio regio = new Regio(1, new Suma(), 5);
        for (int i = 1; i <= 5; ++i) {
            Casella casella = new Casella(i, i, i, true);
            regio.afegirCasella(casella);
            tauler.afegirCasella(i, i, casella);
        }
        tauler.afegirRegio(regio);
        ArrayList<Regio> expectedRegionsIncorrectes = new ArrayList<>();
        expectedRegionsIncorrectes.add(regio);
        assertEquals(expectedRegionsIncorrectes, tauler.getRegionsIncorrectes(valorsTauler));
    }


    @Test
    public void testRestaurarModificabilitat() throws ExcepcioCasellaNoExisteix {
        Tauler tauler = new Tauler("testId", 3);
        // Estableixem algunes caselles com a no modificables
        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= 3; ++j) {
                Casella casella = new Casella(i, j, i*j, false); // Caseslles no modificables
                tauler.afegirCasella(i, j, casella);
            }
        }
        // Cridem a restaurarModificabilitat()
        tauler.restaurarModificabilitat();
        // Comprovem que totes les caselles del tauler són ara modificables
        for (int i = 1; i <= tauler.getGrau(); ++i) {
            for (int j = 1; j <= tauler.getGrau(); ++j) {
                assertTrue(tauler.getCasella(i, j).esModificable());
            }
        }
    }

}