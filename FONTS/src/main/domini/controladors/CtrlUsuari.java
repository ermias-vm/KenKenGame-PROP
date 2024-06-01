package main.domini.controladors;

import main.domini.classes.Usuari;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * La classe CtrlUsuari és el controlador que gestiona les operacions relacionades amb els usuaris.
 * Aquesta classe és responsable de gestionar les tasques com iniciar sessió, registrar-se i canviar la contrasenya.
 * @autor Ermias Valls Mayor
 */
public class CtrlUsuari {
    /**
     * Instància singleton de CtrlUsuari.
     */
    private static CtrlUsuari CU;

    /**
     * Instància de Gson per a la serialització i deserialització de dades d'usuari.
     */
    private Gson gson;

    /**
     * Constructor privat de la classe CtrlUsuari.
     * Inicialitza l'instància de CtrlUsuariData i Gson.
     */
    private CtrlUsuari() {
        this.gson = new Gson();
    }

    /**
     * Retorna la instància singleton de CtrlUsuari.
     * 
     * @return Instància de CtrlUsuari.
     */
    public static CtrlUsuari getInstance() {
        if (CU == null) CU = new CtrlUsuari();
        return CU;
    }

    /**
     * Inicia sessió per a un usuari amb el nom d'usuari i contrasenya proporcionats.
     * 
     * @param nomUsuari  Nom d'usuari.
     * @param contrasenya  Contrasenya de l'usuari.
     * @throws IOException  Si hi ha un error d'entrada/sortida.
     * @throws ExcepcioContrasenyaIncorrecta  Si la contrasenya no és correcta.
     * @throws ExcepcioUsuariNoExisteix  Si l'usuari no existeix.
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) throws IOException, ExcepcioContrasenyaIncorrecta, ExcepcioUsuariNoExisteix {
        //MODificar a CtrlPersistencia
        if (!CtrlDomini.getInstance().existeixUsuariPersistencia(nomUsuari)) {
            throw new ExcepcioUsuariNoExisteix(nomUsuari);
        }
        JsonReader reader = CtrlDomini.getInstance().getUsuariPersistencia(nomUsuari);
        Usuari usuari = gson.fromJson(reader, Usuari.class);
        if (!usuari.esContrasenyaCorrecta(contrasenya)) {
            throw new ExcepcioContrasenyaIncorrecta();
        }
        CtrlDomini.getInstance().setUsuariActual(usuari);
    }

    /**
     * Registra un nou usuari amb el nom d'usuari i contrasenya proporcionats.
     *
     * @param nomUsuari  Nom d'usuari.
     * @param contrasenya  Contrasenya de l'usuari.
     * @throws ExcepcioUsuariJaExisteix  Si l'usuari ja existeix.
     * @throws IOException  Si hi ha un error d'entrada/sortida.
     */
    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        if (CtrlDomini.getInstance().existeixUsuariPersistencia(nomUsuari)) {
            throw new ExcepcioUsuariJaExisteix(nomUsuari);
        }
        Usuari usuari = new Usuari(nomUsuari, contrasenya);
        String dadesUsuariJson = gson.toJson(usuari);
        CtrlDomini.getInstance().guardarUsuariPersistencia(nomUsuari, dadesUsuariJson);
        CtrlDomini.getInstance().setUsuariActual(usuari);
    }

    /**
     * Canvia la contrasenya de l'usuari actual.
     *
     * @param ctrActual  Contrasenya actual de l'usuari.
     * @param ctrNova  Nova contrasenya per a l'usuari.
     * @throws ExcepcioContrasenyaIncorrecta  Si la contrasenya actual no és correcta.
     * @throws IOException  Si hi ha un error d'entrada/sortida.
     */
    public void canviarContrasenya(String ctrActual, String ctrNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        Usuari usuari = CtrlDomini.getInstance().getUsuariActual();
        if (!usuari.esContrasenyaCorrecta(ctrActual)) {
            throw new ExcepcioContrasenyaIncorrecta();
        }
        usuari.setContrasenya(ctrNova);
        String dadesUsuariJson = gson.toJson(usuari);
        CtrlDomini.getInstance().guardarUsuariPersistencia(usuari.getNomUsuari(), dadesUsuariJson);
    }

}