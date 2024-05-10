package main.presentacio.Partida;

public interface ObservadorBoto {

    void notificarUndo();

    void notificarRedo();

    void notificarPista();

    void notificarGuardar();

    void notificarTancaIguarda();

    void notificarSortir();

    void introduirTaulerManualment();

    void introduirIdentificadorTauler();

    void partidaAleatoria();

    void ultimaPartidaGuardada();

    void partidaGuardada();

    void tornar();

    void carregaPartida(String buttonText);

    void next10();
}
