package test.operacions;

import main.domini.classes.operacions.Multiplicacio;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class MultiplicacioTest {
    @Test
    public void opera2ReturnsCorrectProduct() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(6, multiplicacio.opera2(2, 3));
    }

    @Test
    public void operaNReturnsCorrectProduct() throws ExcepcioMoltsValors {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(24, multiplicacio.operaN(new int[]{2, 3, 4}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNThrowsExceptionForEmptyArray() throws ExcepcioMoltsValors {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.operaN(new int[]{});
    }

    @Test
    public void calculaPossiblesValorsReturnsCorrectSet() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3));
        assertEquals(expected, multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{}));
    }

    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsThrowsExceptionForTooManyInitialValues() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{1, 2});
    }

    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsThrowsExceptionForInvalidInitialValue() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{4});
    }

    @Test(expected = ExcepcioNoDivisor.class)
    public void calculaPossiblesValorsThrowsExceptionForNonDivisorInitialValue() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 9, 2, new int[]{5});
    }
}