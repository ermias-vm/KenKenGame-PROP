/*
package test;

import main.stubs.TaulerStub;
import main.domini.excepcions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

*/
/**
 * La classe {@code PartidaTest} és una classe de proves per a la classe {@code Partida}.
 * Utilitza una variant de la classe {@code Partida} anomenada {@code Partida_per_a_test},
 * que està configurada per a utilitzar un TaulerStub en comptes d'un Tauler.
 * Utilitza la classe {@code TaulerStub} per a simular un tauler.
 *//*

public class PartidaTest {
    */
/**
     * La partida a provar.
     *//*

    private Partida_per_a_test partida;
    */
/**
     * El tauler de la partida.
     *//*

    private TaulerStub taulerStub;

    */
/**
     * Inicialitza la partida i el tauler a grau 9, el màxim que es veurà, tot i que no passaria res si fos major.
     *//*

    @Before
    public void setUp() {
        taulerStub = new TaulerStub("tauler", 9);
        partida = new Partida_per_a_test("Usuari", taulerStub);
    }

    */
/**
     * Comprova que la partida s'ha inicialitzat correctament.
     *//*

    @Test
    public void partidaInicialitzacio() {
        assertEquals("Usuari", partida.getUsuariPartida());
        assertEquals(taulerStub, partida.getTaulerPartida());
        assertFalse(partida.getGuardadaPartida());
        assertFalse(partida.getAcabadaPartida());
        assertFalse(partida.getTancadaPartida());
    }

    */
/**
     * Comprova que el valor d'una casella és el correcte després de canviar el valor.
     * @throws Exception si la posició fos incorrecta o el valor invàlid.
     *//*

    @Test
    public void setValorCorrecte() throws Exception {
        assertTrue(partida.setValorPartida(0, 0, 1));
        assertEquals(1, partida.getValorPartida(0, 0));
    }

    */
/**
     * Comprova que llença {@code ExcepcionPosicioIncorrecta}
     * quan s'intenta accedir a una posició fora de la matriu de les caselles.
     * @throws Exception diversos casos, veure javadoc de la implementació.
     *//*

    @Test(expected = ExcepcionPosicioIncorrecta.class)
    public void setValorPosicioIncorrecta() throws Exception {
        partida.setValorPartida(-1, 0, 1);
        partida.setValorPartida(0, -3, 1);
        partida.setValorPartida(9,0,2);
        partida.setValorPartida(8,10,2);
    }

    */
/**
     * Comprova que llença {@code ExcepcioValorInvalid} quan s'intenta posar un valor fora del rang [1..grau].
     * @throws Exception diversos casos, veure javadoc de la implementació.
     *//*

    @Test(expected = ExcepcioValorInvalid.class)
    public void setValorInvalid() throws Exception {
        partida.setValorPartida(0, 0, -1);
        partida.setValorPartida(0, 0, 10);
    }

    */
/**
     * Comprova que posi la partida a guardada
     *//*

    @Test
    public void partidaEsGuarda() {
        assertTrue(partida.setGuardadaPartida());
        assertTrue(partida.getGuardadaPartida());
    }

    */
/**
     * Comprova que la partida prohibeix la modificació si està tancada.
     * Comprova que la partida posa correctament les flags de tancada i guardada.
     * @throws Exception si hi ha un error en la tancada de la partida.
     *//*

    @Test(expected = ExcepcioPartidaTancada.class)
    public void partidaGuardarITancar() throws Exception {
        partida.tancaIGuardaPartida();
        assertTrue(partida.getTancadaPartida());
        assertTrue(partida.getGuardadaPartida());
        partida.setValorPartida(0, 0, 1);
    }

    */
/**
     * Comprova que la partida retorna la informació com s'espera al acabar-la.
     * Primer de tot s'espera 4s per comprovar que la durada de la partida sigui correcta.
     * @throws Exception si hi ha un error en la durada de la partida.
     *//*

    @Test
    public void partidaAcabadaFormatCorrecte() throws Exception {
        Thread.sleep(4000);
        String result = partida.acabaPartida();
        String[] lines = result.split("\n");
        assertEquals(6, lines.length);
        assertEquals(partida.getIdentificadorPartida(), lines[0]);
        assertEquals(partida.getUsuariPartida(), lines[1]);
        assertEquals(partida.getIdentificadorTaulerPartida(), lines[2]);
        assertTrue(Float.parseFloat(lines[3])  > 4);
        assertEquals(String.valueOf(partida.getGrauPartida()), lines[4]);
        assertEquals(String.valueOf(partida.getGuardadaPartida()), lines[5]);
    }

    */
/**
     * Comprova que la partida retorna la informació com s'espera al guardar-la.
     * Primer de tot s'espera 4s per comprovar que la durada de la partida sigui correcta.
     * @throws Exception si hi ha un error en la durada de la partida.
     *//*

    @Test
    public void partidaGuardadaFormatCorrecte() throws Exception {
        Thread.sleep(4000);
        String result = partida.guardaPartida();
        String[] lines = result.split("\n");
        assertEquals(partida.getGrauPartida() + 4, lines.length);
        assertEquals(partida.getIdentificadorPartida(), lines[0]);
        assertEquals(partida.getIdentificadorTaulerPartida(), lines[1]);
        assertTrue( Float.parseFloat(lines[2])  > 4);
        assertEquals(String.valueOf(partida.getGrauPartida()), lines[3]);
        for (int i = 4; i < lines.length; i++) {
            String[] values = lines[i].split(" ");
            for (String value : values) {
                assertTrue(value.matches("\\d+")); //del 0 al 9
            }
        }
    }

    */
/**
     * Comprova que la partida retorna l'estat correcte després de posar valors correctes.
     * @throws ExcepcioPartidaTancada si la partida està tancada.
     * @throws ExcepcioValorInvalid si el valor és invàlid.
     * @throws ExcepcioPartidaAcabada si la partida ja està acabada.
     * @throws ExcepcionPosicioIncorrecta si la posició és incorrecta.
     *//*

    @Test
    public void partidaRetornaEstatCorrecte() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcionPosicioIncorrecta {
        TaulerStub t = new TaulerStub("tauler", 3);
        Partida_per_a_test p = new Partida_per_a_test("Usuari", t);
        p.setValorPartida(0,0,2);
        p.setValorPartida(0,1,1);
        p.setValorPartida(0,2,3);
        p.setValorPartida(1,2,3);
        p.setValorPartida(2,2,2);
        String estat = "2 1 3\n0 0 3\n0 0 2\n";
        assertEquals(estat, p.generaPartidaText());
    }

    */
/**
     * Comprova que la creadora d'una partida amb dades guardades funciona correctament.
     * @throws ExcepcioCreacioPartida si el nom d'usuari no coincideix o si la mida del tauler i la mida dels valors de la partida no coincideixen..
     *//*

    @Test
    public void partidaCreadoraGuardada() throws ExcepcioCreacioPartida {
        String identificador = "Usuari:data";
        String usuari = "Usuari";
        TaulerStub t = new TaulerStub("tauler", 3);
        float temps = 3.6f;
        int[][] valorsPartida = {{2,1,3},{0,0,3},{0,0,2}};
        Partida_per_a_test p = new Partida_per_a_test(identificador, usuari, t, temps, valorsPartida);
        assertEquals(p.getUsuariPartida(), usuari);
        assertEquals(p.getIdentificadorPartida(), identificador);
        assertEquals(p.getTaulerPartida(), t);
        assertEquals(p.getGrauPartida(), 3);
        assertEquals(p.getTempsPartida(), temps, 0.01);
    }

    */
/**
     * Comprova que la creadora d'una partida amb dades guardades llença {@code ExcepcioCreacioPartida}
     * quan s'intenta crear una partida amb un usuari diferent del de les dades guardades.
     * @throws ExcepcioCreacioPartida si el nom d'usuari no coincideix.
     *//*

    @Test(expected = ExcepcioCreacioPartida.class)
    public void partidaCreadoraNoPermisUser() throws ExcepcioCreacioPartida {
        String identificador = "Pepito:data";
        String usuari = "Usuari";
        TaulerStub t = new TaulerStub("tauler", 3);
        float temps = 3.6f;
        int[][] valorsPartida = {{2,1,3},{0,0,3},{0,0,2}};
        Partida_per_a_test p = new Partida_per_a_test(identificador, usuari, t, temps, valorsPartida);
        assertEquals(p.getUsuariPartida(), usuari);
        assertEquals(p.getIdentificadorPartida(), identificador);
        assertEquals(p.getTaulerPartida(), t);
        assertEquals(p.getGrauPartida(), 3);
        assertEquals(p.getTempsPartida(), temps, 0.01);
    }

    */
/**
     * Comprova que la creadora d'una partida amb dades guardades llença {@code ExcepcioCreacioPartida}
     * quan la mida del tauler i la mida dels valors de la partida no coincideixen.
     * @throws ExcepcioCreacioPartida si la mida del tauler i la mida dels valors de la partida no coincideixen.
     *//*

    @Test(expected = ExcepcioCreacioPartida.class)
    public void partidaCreadoraNoTaulerMida() throws ExcepcioCreacioPartida {
        String identificador = "User:data";
        String usuari = "Usuari";
        TaulerStub t = new TaulerStub("tauler", 3);
        float temps = 3.6f;
        int[][] valorsPartida = {{2,1},{0,0},{0,2}};
        Partida_per_a_test p = new Partida_per_a_test(identificador, usuari, t, temps, valorsPartida);
        assertEquals(p.getUsuariPartida(), usuari);
        assertEquals(p.getIdentificadorPartida(), identificador);
        assertEquals(p.getTaulerPartida(), t);
        assertEquals(p.getGrauPartida(), 3);
        assertEquals(p.getTempsPartida(), temps, 0.01);
    }
}*/
