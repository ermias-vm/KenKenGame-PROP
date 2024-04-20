package test.operacions;

import main.domini.classes.operacions.Divisio;
import main.domini.excepcions.ExcepcioDivisio_0;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.excepcions.ExcepcioValorInvalid;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DivisioTest {

    @Test
    public void testOpera2() throws ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        assertEquals(3, divisio.opera2(9, 3));
        assertEquals(-3, divisio.opera2(-9, 3));
        assertEquals(-3, divisio.opera2(9, -3));
        assertEquals(3, divisio.opera2(-9, -3));
    }

    @Test(expected = ExcepcioDivisio_0.class)
    public void testOpera2Divisio_0() throws ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        divisio.opera2(10, 0);
    }

    @Test(expected = ExcepcioNoDivisor.class)
    public void testOpera2NoDivisor() throws ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        divisio.opera2(10, 3);
    }

    @Test
    public void testOperaN() throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        assertEquals(5, divisio.operaN(new int[]{10, 2}));
        assertEquals(3, divisio.operaN(new int[]{9, 3}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void testOperaNMoltsValors() throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        divisio.operaN(new int[]{10, 2, 3});
    }

    @Test(expected = ExcepcioDivisio_0.class)
    public void testOperaNDivisio_0() throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        divisio.operaN(new int[]{10, 0});
    }

    @Test(expected = ExcepcioNoDivisor.class)
    public void testOperaNNoDivisor() throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor {
        Divisio divisio = new Divisio();
        divisio.operaN(new int[]{10, 3});
    }

    @Test
    public void testCalculaPossiblesValorsResultat1() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        for (int midaTauler = 3; midaTauler <= 9; midaTauler++) {
            Set<Integer> expected = new HashSet<>();
            for (int i = 1; i <= midaTauler; i++) {
                expected.add(i);
            }
            assertEquals(expected, divisio.calculaPossiblesValors(1, midaTauler, 2, new int[]{}));
        }
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void testCalculaPossiblesValorsMoltsValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        divisio.calculaPossiblesValors(1, 3, 2, new int[]{1, 2, 3});
    }

    @Test(expected = ExcepcioValorInvalid.class)
    public void testCalculaPossiblesValors0() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        divisio.calculaPossiblesValors(1, 3, 2, new int[]{0});
    }
    @Test
    public void testCalculaPossiblesValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        HashSet<Integer> expected = new HashSet<>(Arrays.asList(1,2,3,6,9));
        assertEquals(expected, divisio.calculaPossiblesValors(3, 9, 2, new int[]{}));
    }
    @Test
    public void testCalculaPossiblesValors1ValorPosat() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        HashSet<Integer> expected = new HashSet<>(Arrays.asList(1,9));
        assertEquals(expected, divisio.calculaPossiblesValors(3, 9, 2, new int[]{3}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void testCalculaPossiblesValorsMidaRegioGran() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        for (int midaTauler = 3; midaTauler <= 9; midaTauler++) {
            Set<Integer> expected = new HashSet<>();
            assertEquals(expected, divisio.calculaPossiblesValors(1, midaTauler, 3, new int[]{}));
        }
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void testCalculaPossiblesValorsNegatiusPosats() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Divisio divisio = new Divisio();
        HashSet<Integer> expected = new HashSet<>();
        assertEquals(expected, divisio.calculaPossiblesValors(3, 9, 2, new int[]{-9}));
    }


}