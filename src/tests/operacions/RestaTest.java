package tests.operacions;

import main.ErrorConstants;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import main.domini.classes.operacions.Resta;

public class RestaTest {

    @Test
    public void opera2ReturnsCorrectDifference() {
        Resta resta = new Resta();
        assertEquals(2, resta.opera2(7, 5));
    }

    @Test
    public void opera2ReturnsCorrectDifferenceWhenSubtractingZero() {
        Resta resta = new Resta();
        assertEquals(7, resta.opera2(7, 0));
    }

    @Test
    public void opera2ReturnsCorrectDifferenceWhenSubtractingNegativeNumbers() {
        Resta resta = new Resta();
        assertEquals(11, resta.opera2(7, -4));
    }

    @Test
    public void operaNReturnsCorrectDifferenceForTwoNumbers() {
        Resta resta = new Resta();
        assertEquals(4, resta.operaN(new int[]{8, 4}));
    }

    @Test
    public void operaNReturnsErrorForSingleNumber() {
        Resta resta = new Resta();
        assertEquals(ErrorConstants.ERROR_INT, resta.operaN(new int[]{3}));
    }

    @Test
    public void operaNReturnsErrorForMoreThanTwoNumbers() {
        Resta resta = new Resta();
        assertEquals(ErrorConstants.ERROR_INT, resta.operaN(new int[]{3, 4, 5}));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValues() {
        Resta resta = new Resta();
        int[] expected = new int[]{};
        assertTrue(Arrays.equals(expected, resta.calculaPossiblesValors(6, 3, 2, new int[]{})));
    }

    @Test
    public void calculaPossiblesValorsReturnsErrorForMoreThanTwoValuesInRegion() {
        Resta resta = new Resta();
        int[] result = resta.calculaPossiblesValors(6, 3, 3, new int[]{1, 2, 3});
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, result));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForSingleValueInRegion() {
        Resta resta = new Resta();
        int[] expected = new int[]{7};
        assertTrue(Arrays.equals(expected, resta.calculaPossiblesValors(6, 7, 2, new int[]{1})));
    }
    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForSingleValueInRegionDoubleResult() {
        Resta resta = new Resta();
        int[] expected = new int[]{3,9};
        assertTrue(Arrays.equals(expected, resta.calculaPossiblesValors(3, 9, 2, new int[]{6})));
    }
    @Test
    public void TooMuchRegion() {
        Resta resta = new Resta();
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, resta.calculaPossiblesValors(3, 9, 5, new int[]{})));
    }
}
