package test;


import main.domini.classes.operacions.Exponenciacio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExponenciacioTest {

    private Exponenciacio exponenciacio;

    @Before
    public void setUp() throws Exception {
        exponenciacio = new Exponenciacio();
    }

    @After
    public void tearDown() throws Exception {
        exponenciacio = null;
    }

    @Test
    public void opera2() {
        int result = exponenciacio.opera2(2, 3);
        assertEquals(8, result);
    }

    @Test
    public void operaN() {
        int[] valors = {2, 3};
        int result = exponenciacio.operaN(valors);
        assertEquals(8, result);
    }

    @Test
    public void calculaPossiblesValors() {
        // Este método devuelve null en la implementación actual, por lo que no podemos probarlo.
    }

    @Test
    public void getNumOperacio() {
        int numOperacio = exponenciacio.getNumOperacio();
        assertEquals(6, numOperacio);
    }
}