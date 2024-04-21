package test;

import main.stubs.TaulerStub;
import main.domini.excepcions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartidaTest {
    private Partida_per_a_test partida;
    private TaulerStub taulerStub;

    @Before
    public void setUp() {
        taulerStub = new TaulerStub("tauler", 9);
        partida = new Partida_per_a_test("Usuari", taulerStub);
    }

    @Test
    public void partidaInicialitzacio() {
        assertEquals("Usuari", partida.getUsuariPartida());
        assertEquals(taulerStub, partida.getTaulerPartida());
        assertFalse(partida.getGuardadaPartida());
        assertFalse(partida.getAcabadaPartida());
        assertFalse(partida.getTancadaPartida());
    }

    @Test
    public void setValorCorrecte() throws Exception {
        assertTrue(partida.setValorPartida(0, 0, 1));
        assertEquals(1, partida.getValorPartida(0, 0));
    }

    @Test(expected = ExcepcionPosicioIncorrecta.class)
    public void setValorPosicioIncorrecta() throws Exception {
        partida.setValorPartida(-1, 0, 1);
    }

    @Test(expected = ExcepcioValorInvalid.class)
    public void setValorInvalid() throws Exception {
        partida.setValorPartida(0, 0, -1);
    }

    @Test
    public void partidaIsSaved() {
        assertTrue(partida.setGuardadaPartida());
        assertTrue(partida.getGuardadaPartida());
    }

    @Test(expected = ExcepcioPartidaTancada.class)
    public void partidaIsClosed() throws Exception {
        partida.tancaIGuardaPartida();
        assertTrue(partida.getTancadaPartida());
        partida.setValorPartida(0, 0, 1);
    }

    @Test
    public void partidaIsClosedAndSaved() throws Exception {
        partida.tancaIGuardaPartida();
        assertTrue(partida.getTancadaPartida());
        assertTrue(partida.getGuardadaPartida());
    }

    @Test
    public void partidaAcabadaReturnsCorrectFormat() throws Exception {
        partida.setValorPartida(0, 0, 1);
        String result = partida.acabaPartida();
        String[] lines = result.split("\n");
        assertEquals(6, lines.length);
        assertEquals(partida.getIdentificadorPartida(), lines[0]);
        assertEquals(partida.getUsuariPartida(), lines[1]);
        assertEquals(partida.getIdentificadorTaulerPartida(), lines[2]);
        assertEquals("480", lines[3]);
        assertEquals(String.valueOf(partida.getGrauPartida()), lines[4]);
        assertEquals(String.valueOf(partida.getGuardadaPartida()), lines[5]);
    }

    @Test
    public void partidaGuardadaReturnsCorrectFormat() throws Exception {
        partida.guardaPartida();
        String result = partida.guardaPartida();
        String[] lines = result.split("\n");
        assertEquals(partida.getGrauPartida() + 4, lines.length);
        assertEquals(partida.getIdentificadorPartida(), lines[0]);
        assertEquals(partida.getIdentificadorTaulerPartida(), lines[1]);
        assertEquals("480", lines[2]);
        assertEquals(String.valueOf(partida.getGrauPartida()), lines[3]);
        for (int i = 4; i < lines.length; i++) {
            String[] values = lines[i].split(" ");
            for (String value : values) {
                assertTrue(value.matches("\\d+"));
            }
        }
    }
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

}