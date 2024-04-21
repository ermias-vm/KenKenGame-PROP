package test.operacions;

import main.domini.classes.operacions.Resta;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class RestaTest {
    @Test
    public void opera2() {
        Resta resta = new Resta();
        assertEquals(1, resta.opera2(2, 3));
    }

    @Test
    public void operaN() throws ExcepcioMoltsValors {
        Resta resta = new Resta();
        assertEquals(1, resta.operaN(new int[]{2, 3}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNMoltsValors() throws ExcepcioMoltsValors {
        Resta resta = new Resta();
        resta.operaN(new int[]{1, 2, 3});
    }

    @Test
    public void calculaPossiblesValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 3));
        assertEquals(expected, resta.calculaPossiblesValors(2, 3, 2, new int[]{}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsMoltsValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(2, 3, 2, new int[]{1, 2});
    }

    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorInvalid0() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(0, 3, 2, new int[]{0});
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorInvalidNegatiu() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(0, 3, 2, new int[]{-3});
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorForaMida() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(0, 3, 2, new int[]{5});
    }
}