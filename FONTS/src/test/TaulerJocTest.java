package test;

import main.domini.classes.TaulerJoc;
import main.domini.classes.RegioJoc;
import main.domini.classes.Casella;

import main.domini.classes.operacions.Suma;
import main.domini.interficies.Operacio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Classe de test per a TaulerJoc.
 *
 * @autor Ermias Valls Mayor
 */
public class TaulerJocTest {

    private TaulerJoc taulerJoc;

    /**
     * Configura l'entorn de test abans de cada test.
     * Crea un nou tauler de joc amb id 1 i grau 3.
     */
    @Before
    public void setUp() throws Exception {
        taulerJoc = new TaulerJoc(1, 3);
        Casella casella = new Casella(0, 0);
        taulerJoc.afegirCasella(casella);
    }

    /**
     * Neteja l'entorn de test després de cada test.
     * Assigna null al tauler de joc.
     */
    @After
    public void tearDown() throws Exception {
        taulerJoc = null;
    }

    /**
     * Prova que verifica si el tauler té solució.
     * S'espera que el tauler no tingui solució.
     */
    @Test
    public void teSolucion() {
        assertFalse(taulerJoc.teSolucion());
    }

    /**
     * Prova que verifica si una casella és modificable.
     * S'espera que la casella sigui modificable inicialment i després no sigui modificable.
     */
    @Test
    public void esModificable() {
        taulerJoc.getCasella(0,0);
        assertTrue(taulerJoc.esModificable(0, 0));
        taulerJoc.getCasella(0,0).setInmodificable();
        assertFalse(taulerJoc.esModificable(0, 0));
    }

    /**
     * Prova que verifica l'addició d'una regió al tauler.
     * S'espera que la mida de les regions del tauler augmenti en 1 després de l'addició.
     */
    @Test
    public void afegirRegioJoc() {
        int sizeBefore = taulerJoc.getRegionsJoc().size();
        ArrayList<Casella> caselles = new ArrayList<>();
        caselles.add(new Casella(1, 0));
        caselles.add(new Casella(1, 1));
        caselles.add(new Casella(1, 2));
        RegioJoc regioJoc = new RegioJoc(caselles, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        int sizeAfter = taulerJoc.getRegionsJoc().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    /**
     * Prova que verifica l'eliminació d'una regió del tauler.
     * S'espera que la regió no estigui al tauler després de l'eliminació.
     */
    @Test
    public void borrarRegioJoc() {
        RegioJoc regioJoc2 = new RegioJoc(1, new Suma(), 2);
        taulerJoc.afegirRegioJoc(regioJoc2);
        taulerJoc.borrarRegioJoc(regioJoc2);
        assertFalse(taulerJoc.getRegionsJoc().contains(regioJoc2));
    }

    /**
     * Prova que verifica l'obtenció de les regions del tauler.
     * Primer, s'espera que el tauler no tingui cap regió.
     * Després, s'afegeix una regió al tauler.
     * Finalment, s'espera que el tauler tingui una regió.
     */
    @Test
    public void getRegionsJoc() {
        assertEquals(0, taulerJoc.getRegionsJoc().size());
        RegioJoc regioJoc = new RegioJoc(1, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        assertEquals(1, taulerJoc.getRegionsJoc().size());
    }

    /**
     * Prova que verifica l'obtenció d'una regió del tauler.
     * S'espera que la regió obtinguda sigui igual a la regió afegida.
     */
    @Test
    public void getRegio() {
        RegioJoc regioJoc = new RegioJoc(1, new Suma(), 6);
        taulerJoc.afegirRegioJoc(regioJoc);
        assertEquals(regioJoc, taulerJoc.getRegio(0, 0));
    }

    /**
     * Prova que verifica si un número és vàlid per a una fila donada.
     * S'espera que el número 1 sigui vàlid i el número 2 no sigui vàlid per a la fila 1.
     */
    @Test
    public void esFilaValida() {
        TaulerJoc tj = new TaulerJoc(1, 2);

        Casella casella1 = new Casella(1, 1);
        tj.afegirCasella(casella1);

        Casella casella2 = new Casella(1, 2);
        casella2.setValor(2);
        tj.afegirCasella(casella2);

        Casella casella3 = new Casella(2, 1);
        casella3.setValor(3);
        tj.afegirCasella(casella3);

        //valors fila 1 = [0,2]
        assertTrue(tj.esFilaValida(1, 1));
        assertFalse(tj.esFilaValida(1, 2));
    }

    /**
     * Prova que verifica si un número és vàlid per a una columna donada.
     * S'espera que el número 1 sigui vàlid i el número 3 no sigui vàlid per a la columna 1.
     */
    @Test
    public void esColumValida() {
        TaulerJoc tj = new TaulerJoc(1, 2);

        Casella casella1 = new Casella(1, 1);
        tj.afegirCasella(casella1);

        Casella casella2 = new Casella(1, 2);
        casella2.setValor(2);
        tj.afegirCasella(casella2);

        Casella casella3 = new Casella(2, 1);
        casella3.setValor(3);
        tj.afegirCasella(casella3);

        //valors columna 1 = [0,3]
        assertTrue(taulerJoc.esColumValida(1, 1));
        assertFalse(taulerJoc.esColumValida(1, 3));

    }

}