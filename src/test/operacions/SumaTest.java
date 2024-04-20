package test.operacions;

import main.domini.classes.operacions.Suma;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class SumaTest {
    @Test
    public void opera2() {
        Suma suma = new Suma();
        assertEquals(5, suma.opera2(2, 3));
        assertEquals(1, suma.opera2(-2, 3));
        assertEquals(-5, suma.opera2(-2, -3));
        assertEquals(3, suma.opera2(0, 3));
        assertEquals(-3, suma.opera2(0, -3));
    }

    @Test
    public void operaN() throws ExcepcioMoltsValors {
        Suma suma = new Suma();
        assertEquals(6, suma.operaN(new int[]{1, 2, 3}));
        assertEquals(0, suma.operaN(new int[]{1, 2, -3}));
        assertEquals(-6, suma.operaN(new int[]{-1, -2, -3}));
        assertEquals(4, suma.operaN(new int[]{1, 0, 3}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNExcepcioMoltsValorsBuit() throws ExcepcioMoltsValors {
        Suma suma = new Suma();
        suma.operaN(new int[]{});
    }

    @Test
    public void calculaPossiblesValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2));

        assertEquals(expected, suma.calculaPossiblesValors(3, 3, 2, new int[]{}));

        assertEquals(new HashSet<>(), suma.calculaPossiblesValors(0, 3, 2, new int[]{}));

        int n = 9;
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2));
        for(int i = 1; i <= n; i++){
           set.add(i);
        }
        int sumaN = n*(n+1)/2;
        assertEquals(set, suma.calculaPossiblesValors(sumaN, n, n, new int[]{}));

    }
    @Test
    public void calculaPossiblesValorsEscala() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2));
        assertEquals(expected, suma.calculaPossiblesValors(12, 3, 8, new int[]{}));
        assertEquals(expected, suma.calculaPossiblesValors(12, 3, 8, new int[]{1}));
        assertEquals(new HashSet<>(Arrays.asList(2)), suma.calculaPossiblesValors(12, 3, 8, new int[]{1,1,1,1}));
    }
    @Test
    public void calculaPossiblesValorsValorInicial() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        Set<Integer> expected = new HashSet<>(Arrays.asList(2));
        assertEquals(expected, suma.calculaPossiblesValors(3, 3, 2, new int[]{1}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsThrowsExceptionForTooManyInitialValues() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{1, 2});
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorInvalid() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{4});
        suma.calculaPossiblesValors(3, 3, 2, new int[]{0});
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValor0() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{0});
    }
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorNegatiu() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{-3});
    }
    @Test
    public void calculaPossiblesValorsMidaRegio1() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        assertEquals(new HashSet<>(Arrays.asList(3)), suma.calculaPossiblesValors(3, 3, 1, new int[]{}));
        assertEquals(new HashSet<>(), suma.calculaPossiblesValors(4, 3, 1, new int[]{}));
    }


}