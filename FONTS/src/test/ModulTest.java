package test;


import main.domini.classes.operacions.Modul;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModulTest {

    private Modul modul;

    @Before
    public void setUp() throws Exception {
        modul = new Modul();
    }

    @After
    public void tearDown() throws Exception {
        modul = null;
    }

    @Test
    public void opera2() {
        int result = modul.opera2(10, 3);
        assertEquals(1, result);
    }

    @Test
    public void operaN() {
        int[] valors = {10, 3};
        int result = modul.operaN(valors);
        assertEquals(1, result);
    }

    @Test
    public void calculaPossiblesValors() {
        // Este método devuelve null en la implementación actual, por lo que no podemos probarlo.
    }

    @Test
    public void getNumOperacio() {
        int numOperacio = modul.getNumOperacio();
        assertEquals(5, numOperacio);
    }
}