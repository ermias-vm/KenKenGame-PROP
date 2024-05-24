package main.presentacio.Partida;

public interface ObservadorLlista {
    /**
     * Notifica que s'ha clicat sobre un element de la llista.
     * @param s Informació de l'element clicat.
     */
    void clickatLlista(String s);
    /**
     * Notifica que s'ha clicat sobre el botó de tornar.
     */
    void tornarMenu();
}
