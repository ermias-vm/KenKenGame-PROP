package test.operacions;

import main.domini.classes.operacions.Resta;
import main.domini.excepcions.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

/**
 * Test de la classe {@code Resta}
 */
public class RestaTest {
    /**
     * Comprova que el mètode opera2 funcioni correctament.
     */
    @Test
    public void opera2() {
        Resta resta = new Resta();
        assertEquals(1, resta.opera2(2, 3));
    }
    /**
     * Comprova que el mètode operaN funcioni correctament amb dos valors.
     * @throws ExcepcioMoltsValors si hi ha més o menys de dos valors.
     */
    @Test
    public void operaN() throws ExcepcioMoltsValors {
        Resta resta = new Resta();
        assertEquals(1, resta.operaN(new int[]{2, 3}));
    }

    /**
     * Comprova que el mètode operaN llenci una excepció si es passa més o menys de 2 valors.
     * @throws ExcepcioMoltsValors si hi ha més o menys de dos valors.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void operaNMoltsValors() throws ExcepcioMoltsValors {
        Resta resta = new Resta();
        resta.operaN(new int[]{1, 2, 3});
        resta.operaN(new int[]{1});
    }

    /**
     * Comprova que el mètode calculaPossiblesValors funcioni correctament.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test
    public void calculaPossiblesValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 3));
        assertEquals(expected, resta.calculaPossiblesValors(2, 3, 2, new int[]{}));
    }
    /**
     * Comprova que el mètode calculaPossiblesValors funcioni amb un valor inicial.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test
    public void calculaPossiblesValorsInicial() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        Set<Integer> expected = new HashSet<>(Arrays.asList(3, 9));
        assertEquals(expected, resta.calculaPossiblesValors(3, 9, 2, new int[]{6}));
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llenci una excepció si es passa dos o més valors.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsMoltsValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(2, 3, 2, new int[]{1, 2});
        resta.calculaPossiblesValors(2, 3, 2, new int[]{1, 2, 3});
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llenci una excepció si la regió no és de mida 2.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test(expected = ExcepcioMoltsValors.class)
    public void calculaPossiblesValorsRegioMoltsValors() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(2, 3, 1, new int[]{});
        resta.calculaPossiblesValors(2, 3, 3, new int[]{});
    }

    /**
     * Comprova que el mètode calculaPossiblesValors retorni el set buit si el resultat és 0.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test
    public void calculaPossiblesValorsValorInvalid0() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        assertEquals(new HashSet<>(),resta.calculaPossiblesValors(0, 3, 2, new int[]{1}));
    }
    /**
     * Comprova que el mètode calculaPossiblesValors llenci excepció si un dels valors inicials és invàlid.
     * Ja sigui perquè és més gran que la mida del tauler o perquè és inferior a 1.
     * @throws ExcepcioMoltsValors si no hi ha 0 o 1 valor inicial o la mida de la regió no és 2.
     * @throws ExcepcioValorInvalid si el valor inicial no és vàlid.
     */
    @Test(expected = ExcepcioValorInvalid.class)
    public void calculaPossiblesValorsValorInvalid() throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        Resta resta = new Resta();
        resta.calculaPossiblesValors(1, 3, 2, new int[]{-3});
        resta.calculaPossiblesValors(1, 3, 2, new int[]{0});
        resta.calculaPossiblesValors(0, 3, 2, new int[]{5});
    }
}