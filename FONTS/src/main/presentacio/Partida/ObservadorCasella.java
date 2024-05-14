package main.presentacio.Partida;

public interface ObservadorCasella {
    void notificarCanviValor(String valor, int fila, int columna);
}
