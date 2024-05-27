package main.presentacio.Ranking;

/**
 * Interfície d'un observador del buscador d'usuari, es podria estendre a qualsevol altre tipus de cerca.
 */
public interface ObservadorBuscador {
    /**
     * Notifica que s'ha realitzat una cerca d'usuari.
     * @param nom Nom de l'usuari a cercar.
     */
    void cercaUsuari(String nom);
}
