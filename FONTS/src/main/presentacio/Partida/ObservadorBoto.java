package main.presentacio.Partida;

/**
 * Interfície que defineix els mètodes que hauran d'implementar els observadors dels botons de la vista de jugar partida.
 */
public interface ObservadorBoto {

    /**
     * Notifica als observadors que s'ha premut el botó d'undo.
     */
    void notificarUndo();

    /**
     * Notifica als observadors que s'ha premut el botó de redo.
     */
    void notificarRedo();

    /**
     * Notifica als observadors que s'ha premut el botó de pista.
     */
    void notificarPista();

    /**
     * Notifica als observadors que s'ha premut el botó de guardar.
     */
    void notificarGuardar();

    /**
     * Notifica als observadors que s'ha premut el botó de tancar i guardar.
     */
    void notificarTancaIguarda();

    /**
     * Notifica als observadors que s'ha premut el botó de sortir.
     */
    void notificarSortir();

    /**
     * Notifica als observadors que l'usuari vol jugar introduint el tauler manualment.
     */
    void jugarIntroduirTaulerManualment();

    /**
     * Notifica als observadors que l'usuari vol jugar introduint l'identificador del tauler.
     */
    void jugarIntroduirIdentificadorTauler();

    /**
     * Notifica als observadors que l'usuari vol jugar una partida aleatòria.
     */
    void jugarPartidaAleatoria();

    /**
     * Notifica als observadors que l'usuari vol jugar l'última partida guardada.
     */
    void jugarUltimaPartidaGuardada();

    /**
     * Notifica als observadors que l'usuari vol jugar una partida guardada.
     */
    void jugarPartidaGuardada();

    /**
     * Notifica als observadors que l'usuari vol tornar.
     */
    void tornar();

    /**
     * Notifica als observadors que l'usuari vol tornar al menú.
     */
    void tornarMenu();

    /**
     * Notifica als observadors que l'usuari vol carregar una partida.
     * @param buttonText El text del botó que s'ha premut.
     */
    void carregaPartida(String buttonText);

    /**
     * Notifica als observadors que l'usuari vol acabar.
     */
    void notificarAcabar();
}
