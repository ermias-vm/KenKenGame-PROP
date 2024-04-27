package test;

import main.domini.classes.Casella;
//import main.domini.classes.Regio;
import main.domini.classes.Tauler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de prova per a Tauler.
 *
 * @author David Giribet
 *
public class TestTauler {

    /**
     * Prueba del constructor de la clase Tauler.
     * Verifica que el constructor inicializa correctamente el objeto Tauler
     * con el identificador y el grado dados, y que inicializa la lista de casillas.
     *
    @Test
    public void testConstructor() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(1, tauler.getIdTauler());
        assertEquals(3, tauler.getGrau());
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }

    /**
     * Prueba del método afegirCasella() de la clase Tauler.
     * Verifica que se agrega correctamente una casilla a la lista de casillas del tablero.
     *
    @Test
    public void testAfegirCasella() {
        Tauler tauler = new Tauler(1, 3);
        Casella casella = new Casella();
        tauler.afegirCasella(casella);
        assertTrue(tauler.getCaselles().contains(casella));
    }

    /**
     * Prueba del método borrarCasella() de la clase Tauler.
     * Verifica que se elimina correctamente una casilla de la lista de casillas del tablero.
     *
    @Test
    public void testEsborrarCasella() {
        Tauler tauler = new Tauler(1, 3);
        Casella casella = new Casella();
        tauler.afegirCasella(casella);
        tauler.borrarCasella(casella);
        assertFalse(tauler.getCaselles().contains(casella));
    }

    /**
     * Prueba del método getIdTauler() de la clase Tauler.
     * Verifica que se devuelve correctamente el identificador del tablero.
     *
    @Test
    public void testGetIdTauler() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(1, tauler.getIdTauler());
    }

    /**
     * Prueba del método getGrau() de la clase Tauler.
     * Verifica que se devuelve correctamente el grado del tablero.
     *
    @Test
    public void testGetGrado() {
        Tauler tauler = new Tauler(1, 3);
        assertEquals(3, tauler.getGrau());
    }

    /**
     * Prueba del método getCaselles() de la clase Tauler.
     * Verifica que se devuelve correctamente la lista de casillas del tablero.
     *
    @Test
    public void testGetCaselles() {
        Tauler tauler = new Tauler(1, 3);
        assertNotNull(tauler.getCaselles());
        assertEquals(0, tauler.getCaselles().size());
    }
}*/
