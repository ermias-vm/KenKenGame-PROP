package main.presentacio.Partida;

/**
 * Interfície que defineix els mètodes que hauran d'implementar els observadors dels botons de les caselles.
 */
public interface ObservadorCasella {
    /**
     * Notifica als observadors que s'ha fet un canvi de valor a la casella.
     * @param valor Valor que s'ha posat a la casella.
     * @param fila Fila de la casella.
     * @param columna Columna de la casella.
     */
    void notificarCanviValor(String valor, int fila, int columna);

    void notificarRequestFocus(int fila, int columna);
}
