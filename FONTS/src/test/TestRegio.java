package test;

import main.domini.classes.Casella;
import main.domini.classes.Regio;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestRegio {

    /**
     * Test para verificar si el tamaño de la región se establece correctamente.
     */
    @Test
    public void testNumCasellas() {
        Regio regio = new Regio(5);
        assertEquals(5, regio.getTamany());
    }

    /**
     * Test para verificar si el número de casillas llenas es correcto cuando se crea la región con un ArrayList de casillas vacías.
     */
    @Test
    public void testNumCasellasPlenas_() {
        ArrayList<Casella> casellas = new ArrayList<>(5);
        Regio regio = new Regio(casellas);
        assertEquals(0, regio.getNumcasellesPlenas());
        assertEquals(5, regio.getTamany());
    }

    /**
     * Test para verificar si la función esBuida() devuelve nulo para una casilla que se supone que está vacía.
     */
    @Test
    public void testEsBuida_() {
        ArrayList<Casella> casellas = new ArrayList<>(6);
        // Añade casillas a 'casellas', algunas de las cuales están vacías
        Regio regio = new Regio(casellas);
        assertNull(regio.esBuida(2));
    }

    /**
     * Test para verificar si se crea correctamente una región con un tamaño especificado.
     */
    @Test
    public void testConstructor() {
        Regio regio = new Regio(3);
        assertNotNull(regio.getCaselles());
        assertEquals(3, regio.getCaselles().size());
    }
    
    /**
     * Test para verificar si se puede agregar un valor a una casilla de la región.
     */
    @Test
    public void testAddValor() {
        // Setup
        ArrayList<Casella> casellas = new ArrayList<>(6);

        Regio regio = new Regio(casellas);
        regio.setValor(2, 3);
        // Exercise

        // Verify
        assertEquals(3, regio.getValor(2));  
    }

    /**
     * Test para verificar si se borra correctamente el valor de una casilla.
     */
    @Test
    public void Borravalor() {
        // Setup
        ArrayList<Casella> casellas = new ArrayList<>(6);

        Regio regio = new Regio(casellas);
        regio.setValor(2, 3);
        regio.borra(2);
    
        // Verify
        assertNotEquals(3, regio.getValor(2));  
    }
}
