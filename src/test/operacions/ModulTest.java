package test.operacions;

import main.domini.classes.operacions.Modul;
import main.domini.excepcions.ExcepcioMoltsValors;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModulTest {

    private Modul modul;

    @Before
    public void setUp() {
        modul = new Modul();
    }

    @After
    public void tearDown() {
        modul = null;
    }

    @Test
    public void testOpera2() {
        assertEquals(1, modul.opera2(5, 2));
    }

    @Test
    public void testOperaN() {
        int[] valors = {5, 2};
        assertEquals(1, modul.operaN(valors));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void testOperaNExcepcio() {
        int[] valors = {5, 2, 3};
        modul.operaN(valors);
    }
}