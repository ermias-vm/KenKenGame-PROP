import main.domini.classes.ErrorConstantsPartida;
import main.domini.classes.Partida;
import main.stubs.TaulerStub;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PartidaTest {

    @Test
    public void partidaReturnsCorrectUserAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals("testUser", partida.getUsuariPartida());
    }

    @Test
    public void partidaReturnsCorrectTaulerAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(taulerStub, partida.getTaulerPartida());
    }

    @Test
    public void partidaReturnsErrorWhenSettingValueOutOfBounds() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(ErrorConstantsPartida.ERROR_INT_FILA_COLUMNA, partida.setValorPartida(1, 4, 4));
    }

    @Test
    public void partidaReturnsErrorWhenSavingIncorrectly() {
        TaulerStub taulerStub = new TaulerStub("testTauler") {
            @Override
            public boolean corretgeix(int[][] valors) {
                return false;
            }
        };
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(ErrorConstantsPartida.ERROR_STRING_PARTIDA_INCORRECTA, partida.acabaPartida());
    }
    @Test
    public void partidaReturnsCorrectSizeAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(3, partida.getMidaPartida());
    }
    @Test
    public void partidaReturnsCorrectValuesAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        int[][] values = partida.getValorsPartida();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, values[i][j]);
            }
        }
    }

    @Test
    public void partidaCorrectlySetsValue() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(1, partida.setValorPartida(1, 1, 1));
        assertEquals(1, partida.getValorsPartida()[1][1]);
    }

    @Test
    public void partidaReturnsErrorWhenSettingInvalidValue() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(ErrorConstantsPartida.ERROR_INT_VALOR_INVALID, partida.setValorPartida(4, 1, 1));
    }
    @Test
    public void partidaCorrectlyEndsGame() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertNotNull(partida.acabaPartida());
        assertTrue(partida.getAcabadaPartida());
    }

    @Test
    public void partidaReturnsCorrectStartTimeAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        assertEquals(LocalDateTime.of(2022, 1, 1, 0, 0), partida.getiniciPartida());
    }
    @Test
    public void partidaCorrectlyClosesAndSavesGame() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        String expectedOutput = "testUser2022-01-01T00:00:00\ntestUser\ntestTauler\n480\n3\n0 0 0\n0 0 0\n0 0 0\n";
        assertEquals(expectedOutput, partida.tancaIGuardaPartida());
        assertTrue(partida.getGuardadaPartida());
        assertEquals(480, partida.getTempsPartida());
    }
    public void partidaCorrectlySavesGame() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.setValorPartida(1, 1, 1);
        String expectedOutput = "testUser2022-01-01T00:00:00\ntestUser\ntestTauler\n480\n3\n0 0 0\n0 1 0\n0 0 0\n";
        assertEquals(expectedOutput, partida.tancaIGuardaPartida());
        assertTrue(partida.getGuardadaPartida());
        assertEquals(480, partida.getTempsPartida());
    }
    @Test
    public void partidaReturnsCorrectIdentifierAfterCreation() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        String expectedIdentifier = "testUser2022-01-01T00:00:00";
        assertEquals(expectedIdentifier, partida.getIdentificadorPartida());
    }

    @Test
    public void partidaReturnsErrorWhenSettingValueAfterGameEnded() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.acabaPartida();
        assertEquals(ErrorConstantsPartida.ERROR_INT_PARTIDA_ACABADA, partida.setValorPartida(1, 1, 1));
    }
    @Test
    public void partidaReturnsErrorWhenEndingGameTwice() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.acabaPartida();
        assertEquals(ErrorConstantsPartida.ERROR_STRING_PARTIDA_ACABADA, partida.acabaPartida());
    }

    @Test
    public void partidaReturnsErrorWhenClosingAndSavingGameTwice() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.tancaIGuardaPartida();
        assertEquals(ErrorConstantsPartida.ERROR_STRING_PARTIDA_TANCADA, partida.tancaIGuardaPartida());
    }
    @Test
    public void partidaReturnsErrorWhenSettingValueAfterGameClosed() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.tancaIGuardaPartida();
        assertEquals(ErrorConstantsPartida.ERROR_INT_PARTIDA_TANCADA, partida.setValorPartida(1, 1, 1));
    }
    @Test
    public void partidaReturnsErrorWhenEndingGameAfterGameClosed() {
        TaulerStub taulerStub = new TaulerStub("testTauler");
        Partida partida = new Partida(3, "testUser", taulerStub);
        partida.tancaIGuardaPartida();
        assertEquals(ErrorConstantsPartida.ERROR_STRING_PARTIDA_TANCADA, partida.acabaPartida());
    }
}