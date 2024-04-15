package tests.operacions;

import main.ErrorConstants;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.domini.classes.operacions.Divisio;
import java.util.Arrays;

public class DivisioTest {

    @Test
    public void opera2ReturnsCorrectQuotient() {
        Divisio divisio = new Divisio();
        assertEquals(5, divisio.opera2(10, 2));
    }

    @Test
    public void opera2ReturnsErrorForNonDivisibleNumbers() {
        Divisio divisio = new Divisio();
        int result = divisio.opera2(10, 3);
        assertEquals(ErrorConstants.ERROR_INT, result);
    }

    @Test
    public void operaNReturnsCorrectQuotientForTwoNumbers() {
        Divisio divisio = new Divisio();
        assertEquals(5, divisio.operaN(new int[]{10, 2}));
    }

    @Test
    public void operaNReturnsErrorForNonDivisibleNumbers() {
        Divisio divisio = new Divisio();
        int result = divisio.operaN(new int[]{10, 3});
        assertEquals(ErrorConstants.ERROR_INT, result);
    }

    @Test
    public void operaNReturnsErrorForMoreThanTwoNumbers() {
        Divisio divisio = new Divisio();
        int result = divisio.operaN(new int[]{10, 2, 3});
        assertEquals(ErrorConstants.ERROR_INT, result);
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForSingleValue() {
        Divisio divisio = new Divisio();
        int[] expected = new int[]{10};
        assertTrue(Arrays.equals(expected, divisio.calculaPossiblesValors(5, 10, 2, new int[]{2})));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForMultipleValues() {
        Divisio divisio = new Divisio();
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, divisio.calculaPossiblesValors(2, 10, 2, new int[]{1, 2, 3, 4, 5})));
    }

    @Test
    public void calculaPossiblesValorsReturnsErrorForMoreThanTwoValuesInRegion() {
        Divisio divisio = new Divisio();
        int[] result = divisio.calculaPossiblesValors(2, 10, 3, new int[]{1, 2, 3});
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, result));
    }
}