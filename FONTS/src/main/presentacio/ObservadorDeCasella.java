package main.presentacio;

public class ObservadorDeCasella implements ObservadorCasella {
    @Override
    public void notificarCanviValor(String valor, int fila, int columna) {
        // Actualizacions de la casella
        System.out.println("Valor cambiado a " + valor + " en fila " + fila + ", columna " + columna);
    }
}