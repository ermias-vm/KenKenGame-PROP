package tests.operacions;

import main.domini.classes.operacions.Suma;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;

public class SumaTest {

    @Test
    public void opera2ReturnsCorrectSum() {
        Suma suma = new Suma();
        assertEquals(12, suma.opera2(7, 5));
    }

    @Test
    public void operaNReturnsCorrectSumForMultipleNumbers() {
        Suma suma = new Suma();
        assertEquals(15, suma.operaN(new int[]{3, 4, 8}));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValues() {
        Suma suma = new Suma();
        int[] expected = new int[]{1, 2, 3};
        assertTrue(Arrays.equals(expected, suma.calculaPossiblesValors(6, 3, 3, new int[]{})));
    }

    @Test
    public void calculaPossiblesValorsReturnsErrorForMoreThanTwoValuesInRegion() {
        Suma suma = new Suma();
        int[] result = suma.calculaPossiblesValors(6, 3, 3, new int[]{1, 2, 3});
        assertTrue(Arrays.equals(new int[0], result));
    }

    @Test
    public void opera2ReturnsCorrectSumWhenAddingZero() {
        Suma suma = new Suma();
        assertEquals(7, suma.opera2(7, 0));
    }

    @Test
    public void opera2ReturnsCorrectSumWhenAddingNegativeNumbers() {
        Suma suma = new Suma();
        assertEquals(-3, suma.opera2(-7, 4));
    }

    @Test
    public void operaNReturnsCorrectSumForSingleNumber() {
        Suma suma = new Suma();
        assertEquals(3, suma.operaN(new int[]{3}));
    }

    @Test
    public void operaNReturnsZeroForEmptyArray() {
        Suma suma = new Suma();
        assertEquals(0, suma.operaN(new int[]{}));
    }

    @Test
    public void operaNReturnsCorrectSumWhenAddingZero() {
        Suma suma = new Suma();
        assertEquals(7, suma.operaN(new int[]{3, 4, 0}));
    }

    @Test
    public void operaNReturnsCorrectSumWhenAddingNegativeNumbers() {
        Suma suma = new Suma();
        assertEquals(-3, suma.operaN(new int[]{-7, 4}));
    }
}