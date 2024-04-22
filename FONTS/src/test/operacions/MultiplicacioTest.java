package test.operacions;

import main.domini.classes.operacions.Multiplicacio;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

/**
 *  Test de la classe {@code Multiplicacio} per a comprovar que els seus mètodes funcionen correctament.
 */
public class MultiplicacioTest {
    /**
     * Comprova que el mètode opera2 retorna el producte correcte de dos enters.
     */
    @Test
    public void opera2Correcte() {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(6, multiplicacio.opera2(2, 3));
    }
    /**
     * Comprova que el mètode operaN retorna el producte correcte d'un conjunt d'enters.
     */
    @Test
    public void operaNCorrecte() throws ExcepcioMoltsValors {
        Multiplicacio multiplicacio = new Multiplicacio();
        assertEquals(24, multiplicacio.operaN(new int[]{2, 3, 4}));
    }
    /**
     * Comprova que el mètode operaN llença una excepció quan s'intenta operar un conjunt buit d'enters.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNExcepcioMoltsValors() throws ExcepcioMoltsValors {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.operaN(new int[]{});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors retorna el conjunt correcte de valors possibles.
     */
    @Test
    public void calculaPossiblesCorrecte() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3));
        assertEquals(expected, multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{}));
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llença una excepció quan hi ha massa valors inicials.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsExcepcioMoltsValors() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{1, 2});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llença una excepció quan s'introdueix un valor inicial invàlid donada la mida del tauler.
     */
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsExcepcioValorInvalid() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 3, 2, new int[]{4});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llença una excepció quan s'introdueix un valor inicial que no és divisor del resultat.
     */
    @Test(expected = ExcepcioNoDivisor.class)
    public void calculaPossiblesValorsExcepcioNoDivisor() throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        Multiplicacio multiplicacio = new Multiplicacio();
        multiplicacio.calculaPossiblesValors(6, 9, 2, new int[]{5});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors assumint que la regió fos una escala correctament.
     * En aquest cas seria de la forma 1*1*1**2*2*2
     *
     */
    @Test
    public void calculaPossiblesValorsEscala() throws ExcepcioMoltsValors, ExcepcioValorInvalid, ExcepcioNoDivisor {
        Multiplicacio multiplicacio = new Multiplicacio();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1,2));
        assertEquals(expected, multiplicacio.calculaPossiblesValors(8, 3, 6, new int[]{}));
    }
}