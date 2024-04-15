import main.domini.classes.ErrorConstantsOperacions;
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
    public void calculaPossiblesValorsReturnsErrorForMoreThanTwoValuesInRegion() {
        Multiplicacio multiplicacio = new Multiplicacio();
        int[] result = multiplicacio.calculaPossiblesValors(10, 10, 2, new int[]{1, 2, 3});
        assertTrue(Arrays.equals(ErrorConstantsOperacions.ERROR_ARRAY_MIDA, result));
    }
    @Test
    public void opera2ReturnsZeroWhenMultiplyingByZero() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(0, multiplicacio.opera2(10, 0));
    }

    @Test
    public void opera2ReturnsNegativeProductForNegativeNumbers() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(20, multiplicacio.opera2(-10, -2));
    }

    @Test
    public void operaNReturnsZeroWhenMultiplyingByZero() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(0, multiplicacio.operaN(new int[]{10, 2, 0}));
    }

    @Test
    public void operaNReturnsNegativeProductForOddNumberOfNegativeNumbers() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(-60, multiplicacio.operaN(new int[]{-10, 2, 3}));
    }

    @Test
    public void calculaPossiblesValorsReturnsEmptyForAllValuesSet() {
        Multiplicacio multiplicacio = new Multiplicacio();
        int[] expected = new int[0];
        int[] actual = multiplicacio.calculaPossiblesValors(10, 10, 2, new int[]{2, 5});
        System.out.println(Arrays.toString(actual)); // This will print the actual result
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void calculaPossiblesValorsReturnsErrorForZeroInValues() {
        Multiplicacio multiplicacio = new Multiplicacio();
        int[] result = multiplicacio.calculaPossiblesValors(10, 10, 2, new int[]{0, 2});
        assertTrue(Arrays.equals(ErrorConstantsOperacions.ERROR_ARRAY_DIV_0, result));
    }
}