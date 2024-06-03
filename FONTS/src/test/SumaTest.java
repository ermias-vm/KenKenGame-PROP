package test;

import main.domini.classes.operacions.Suma;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
/**
 * La classe {@code SumaTest} és una classe de test unitari per a la classe {@code Suma}.
 */
public class SumaTest {
    /**
     * Comprova que el mètode opera2 funcioni correctament.
     */
    @Test
    public void opera2() {
        Suma suma = new Suma();
        assertEquals(5, suma.opera2(2, 3));
        assertEquals(1, suma.opera2(-2, 3));
        assertEquals(-5, suma.opera2(-2, -3));
        assertEquals(3, suma.opera2(0, 3));
        assertEquals(-3, suma.opera2(0, -3));
    }
    /**
     * Comprova que el mètode operaN funcioni correctament.
     * @throws ExcepcioMoltsValors si no valors.
     */
    @Test
    public void operaN() throws ExcepcioMoltsValors {
        Suma suma = new Suma();
        assertEquals(6, suma.operaN(new int[]{1, 2, 3}));
        assertEquals(0, suma.operaN(new int[]{1, 2, -3}));
        assertEquals(-6, suma.operaN(new int[]{-1, -2, -3}));
        assertEquals(4, suma.operaN(new int[]{1, 0, 3}));
    }

    /**
     * Comprova que el mètode operaN llenci una excepció si no té com a mínim 1 valor.
     * @throws ExcepcioMoltsValors si no valors.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNExcepcioMoltsValorsBuit() throws ExcepcioMoltsValors {
        Suma suma = new Suma();
        suma.operaN(new int[]{});
    }

    /**
     * Comprova que el mètode calculaPossiblesValors funcioni correctament.
     * En aquest cas amb els sumatoris d'1 a n.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
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
    /**
     * Comprova que el mètode calculaPossiblesValors funcioni correctament en el cas maxim de repeticions,
     * quan hi ha una regió en forma d'escala.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
    @Test
    public void calculaPossiblesValorsEscala() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2));
        assertEquals(expected, suma.calculaPossiblesValors(12, 3, 8, new int[]{}));
        assertEquals(expected, suma.calculaPossiblesValors(12, 3, 8, new int[]{1}));
        assertEquals(new HashSet<>(Arrays.asList(2)), suma.calculaPossiblesValors(12, 3, 8, new int[]{1,1,1,1}));
    }

    /**
     * Comprova que el mètode calculaPossiblesValors funcioni correctament amb un valor inicial.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
    @Test
    public void calculaPossiblesValorsValorInicial() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        Set<Integer> expected = new HashSet<>(Arrays.asList(2));
        assertEquals(expected, suma.calculaPossiblesValors(3, 3, 2, new int[]{1}));
    }

    /**
     * Comprova que el mètode calculaPossiblesValors llenci una excepció si té igual o més que midaRegio valors inicials.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsThrowsExceptionForTooManyInitialValues() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{1, 2});
        suma.calculaPossiblesValors(3, 3, 2, new int[]{1, 2,3});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llenci una excepció si algun dels valors és invàlid pel tauler.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorInvalid() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        suma.calculaPossiblesValors(3, 3, 2, new int[]{4});
        suma.calculaPossiblesValors(3, 3, 2, new int[]{0});
        suma.calculaPossiblesValors(3, 3, 2, new int[]{-3});
    }

    /**
     * Comprova que el mètode retorni el resultat, si és vàlid en una regió de mida 1.
     * @throws ExcepcioMoltsValors si té més valors que la mida de la regió.
     * @throws ExcepcioValorInvalid si algun dels valors és invàlid pel tauler.
     */
    @Test
    public void calculaPossiblesValorsMidaRegio1() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Suma suma = new Suma();
        assertEquals(new HashSet<>(Arrays.asList(3)), suma.calculaPossiblesValors(3, 3, 1, new int[]{}));
        assertEquals(new HashSet<>(), suma.calculaPossiblesValors(4, 3, 1, new int[]{}));
    }


}