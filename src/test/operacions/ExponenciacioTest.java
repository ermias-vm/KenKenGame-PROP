package test.operacions;

import main.domini.classes.operacions.Exponenciacio;
import main.domini.excepcions.ExcepcioMoltsValors;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExponenciacioTest {

    private Exponenciacio exponenciacio;

    @Before
    public void setUp() {
        exponenciacio = new Exponenciacio();
    }

    @After
    public void tearDown() {
        exponenciacio = null;
    }

    @Test
    public void testOpera2() {
        assertEquals(8, exponenciacio.opera2(2, 3));
    }

    @Test
    public void testOperaN() {
        int[] valors = {2, 3};
        assertEquals(8, exponenciacio.operaN(valors));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void testOperaNExcepcio() {
        int[] valors = {2, 3, 4};
        exponenciacio.operaN(valors);
    }

}