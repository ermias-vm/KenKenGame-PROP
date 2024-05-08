package main.domini.classes;


public class Usuari {

    private String nomUsuari;
    private String contrasenya;

    public Usuari() {
        this.nomUsuari = null;
        this.contrasenya = null;
    }

    public Usuari(String nomUsuari, String contrasenya) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    public String getNomUsuari() {
        return this.nomUsuari;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    public boolean esContrasenyaCorrecta(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }

    public  boolean teContrasenya() {
        return this.contrasenya != null;
    }

}
