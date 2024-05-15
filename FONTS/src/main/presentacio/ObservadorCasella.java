package main.presentacio;

public interface ObservadorCasella {
    void notificarCanviValor(String valor, int fila, int columna);
}
