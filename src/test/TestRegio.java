package test;

import main.domini.classes.Casella;
import main.domini.classes.Regio;


import org.junit.Test;



import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestRegio {
    @Test
    public void testNumCasellas() {
        Regio regio = new Regio(5);
        assertEquals(5, regio.getNumCasellas());
    }

    @Test
    public void testNumCasellasPlenas() {
        ArrayList<Casella> casellas = new ArrayList<>();
        // Añade casillas a 'casellas', algunas de las cuales están llenas
        Regio regio = new Regio(casellas);
        int expectedNumCasellasPlenas = // número de casillas llenas en 'casellas';
        assertEquals(expectedNumCasellasPlenas, regio.getNumCasellasPlenas());
    }

    @Test
    public void testEsBuida() {
        ArrayList<Casella> casellas = new ArrayList<>();
        // Añade casillas a 'casellas', algunas de las cuales están vacías
        Regio regio = new Regio(casellas);
        int pos = // posición a comprobar;
        boolean expected = // valor esperado;
        assertEquals(expected, regio.esBuida(pos));
    }

    @Test
    public void testConstructor() {
        Regio regio = new Regio(3);
        assertEquals(3, regio.getIdTauler());
        assertEquals(3, regio.getGrado());
        assertNotNull(regio.getPeçes());
        assertEquals(0, regio.getPeçes().size());
        assertNotNull(regio.getCaselles());
        assertEquals(0, regio.getCaselles().size());
    }

    @Test
    public void testConstructorambvector() {
        Regio regio = new Regio(1, 3);
        Peça regio = new Peça();
        regio.afegirPeça(regio);
        assertTrue(regio.getPeçes().contains(regio));
    }

    @Test
    public void testGetnumcaselles() {
        Regio regio = new Regio(1, 3);
        Peça regio = new Peça();
        regio.afegirPeça(regio);
        regio.esborrarPeça(regio);
        assertFalse(regio.getPeçes().contains(regio));
    }

    @Test
    public void testAfegirCasella() {
        Regio regio = new Regio(1, 3);
        Casella casella = new Casella();
        regio.afegirCasella(casella);
        assertTrue(regio.getCaselles().contains(casella));
    }

    @Test
    public void testEsborrarCasella() {
        Regio regio = new Regio(1, 3);
        Casella casella = new Casella();
        regio.afegirCasella(casella);
        regio.esborrarCasella(casella);
        assertFalse(regio.getCaselles().contains(casella));
    }

    @Test
    public void testGetIdTauler() {
        Regio regio = new Regio(1, 3);
        assertEquals(1, regio.getIdTauler());
    }

    @Test
    public void testGetGrado() {
        Regio regio = new Regio(1, 3);
        assertEquals(3, regio.getGrado());
    }

    @Test
    public void testGetPeçes() {
        Regio regio = new Regio(1, 3);
        assertNotNull(regio.getPeçes());
        assertEquals(0, regio.getPeçes().size());
    }

    @Test
    public void testGetCaselles() {
        Regio regio = new Regio(1, 3);
        assertNotNull(regio.getCaselles());
        assertEquals(0, regio.getCaselles().size());
    }

    @Test
    public void testConstructorTamanyValid() {
        Regio regio = new Regio(5);
        assertEquals(5, regio.getNumCasellas());
    }

    @Test
    public void testConstructorTamanyInvalid() {
        Regio regio = new Regio(0);
    }

    @Test
    public void testConstructorArrayListValid() {
        ArrayList<Casella> casellas = new ArrayList<>();
        casellas.add(new Casella());
        Regio regio = new Regio(casellas);
        assertEquals(casellas.size(), regio.getNumCasellas());
    }

    @Test
    public void testConstructorArrayListInvalid() {
        ArrayList<Casella> casellas = new ArrayList<>();
        Regio regio = new Regio(casellas);
    }

    @Test
    public void testEsBuida() {
        ArrayList<Casella> casellas = new ArrayList<>();
        casellas.add(new Casella());
        casellas.add(new Casella("contenido"));
        Regio regio = new Regio(casellas);
        assertTrue(regio.esBuida(0));
        assertFalse(regio.esBuida(1));
    }

    @Test
    public void testGetCasella() {
        ArrayList<Casella> casellas = new ArrayList<>();
        Casella casella = new Casella("contenido");
        casellas.add(casella);
        Regio regio = new Regio(casellas);
        assertEquals(casella, regio.getCasella(0));
    }

    @Test
    public void testGetValor() {
        ArrayList<Casella> casellas = new ArrayList<>();
        Casella casella = new Casella();
        casella.setValor(5);
        casellas.add(casella);
        Regio regio = new Regio(casellas);
        assertEquals(5, regio.getValor(0));
    }

    // Puedes continuar agregando más pruebas para los otros métodos de Regio aquí
}
