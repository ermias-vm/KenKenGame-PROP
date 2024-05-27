package main.presentacio.Ranking;

/**
 * Interfície d'un observador del selector de mida.
 */
public interface ObservadorSelectorMida {
    /**
     * Notifica que s'ha seleccionat una mida.
     * @param mida Mida seleccionada.
     */
    void midaSeleccionada(String mida);
}
