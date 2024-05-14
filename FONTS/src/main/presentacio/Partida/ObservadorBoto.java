package main.presentacio.Partida;

public interface ObservadorBoto {

    void notificarUndo();

    void notificarRedo();

    void notificarPista();

    void notificarGuardar();

    void notificarTancaIguarda();

    void notificarSortir();

    void jugarIntroduirTaulerManualment();

    void jugarIntroduirIdentificadorTauler();

    void jugarPartidaAleatoria();

    void jugarUltimaPartidaGuardada();

    void jugarPartidaGuardada();

    void tornar();

    void tornarMenu();

    void carregaPartida(String buttonText);

    void notificarAcabar();
}
