package main.domini.classes;

/**
 * Classe que representa un usuari.
 * Un usuari té un nom d'usuari i una contrasenya.
 *
 *   @author Ermias Valls Mayor
 */
public class Usuari {

    /**
     * Nom d'usuari de l'usuari.
     */
    private String nomUsuari;

    /**
     * Contrasenya de l'usuari.
     */
    private String contrasenya;

    /**
     * Constructora dela classe per defecte.
     * Crea un usuari sense nom d'usuari ni contrasenya.
     */
    public Usuari() {
        this.nomUsuari = null;
        this.contrasenya = null;
    }

    /**
     * Constructora de la classe amb paràmetres.
     * Crea un usuari amb un nom d'usuari i una contrasenya donats.
     * @param nomUsuari Nom d'usuari de l'usuari.
     * @param contrasenya Contrasenya de l'usuari.
     */
    public Usuari(String nomUsuari, String contrasenya) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    /**
     * Retorna el nom d'usuari de l'usuari.
     * @return Nom d'usuari de l'usuari.
     */
    public String getNomUsuari() {
        return this.nomUsuari;
    }

    /**
     * Retorna la contrasenya de l'usuari.
     * @return Contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return this.contrasenya;
    }

    /**
     * Estableix el nom d'usuari de l'usuari.
     * @param nomUsuari Nou nom d'usuari de l'usuari.
     */
    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    /**
     * Estableix la contrasenya de l'usuari.
     * @param contrasenya Nova contrasenya de l'usuari.
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    /**
     * Comprova si la contrasenya donada és igual a la contrasenya de l'usuari.
     * @param contrasenya Contrasenya a comprovar.
     * @return Cert si la contrasenya és correcta, fals altrament.
     */
    public boolean esContrasenyaCorrecta(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }

    /**
     * Comprova si l'usuari té una contrasenya.
     * @return Cert si l'usuari té una contrasenya, fals altrament.
     */
    public  boolean teContrasenya() {
        return this.contrasenya != null;
    }

}