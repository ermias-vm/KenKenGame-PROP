package tests.operacions;

import main.ErrorConstants;
import main.domini.classes.operacions.Multiplicacio;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;

public class MultiplicacioTest {

    @Test
    public void opera2ReturnsCorrectProduct() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(20, multiplicacio.opera2(10, 2));
    }

    @Test
    public void operaNReturnsCorrectProductForMultipleNumbers() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(60, multiplicacio.operaN(new int[]{10, 2, 3}));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForSingleValue() {
        Multiplicacio multiplicacio = new Multiplicacio();
        int[] expected = new int[]{5};
        assertTrue(Arrays.equals(expected, multiplicacio.calculaPossiblesValors(10, 10, 2, new int[]{2})));
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectValuesForMultipleValues() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, multiplicacio.calculaPossiblesValors(10, 10, 2, new int[]{1, 2, 3, 4, 5})));
    }

    @Test
    public void calculaPossiblesValorsReturnsErrorForMoreThanTwoValuesInRegion() {
        Multiplicacio multiplicacio = new Multiplicacio();
        int[] result = multiplicacio.calculaPossiblesValors(10, 10, 3, new int[]{1, 2, 3});
        assertTrue(Arrays.equals(ErrorConstants.ERROR_ARRAY, result));
    }
}