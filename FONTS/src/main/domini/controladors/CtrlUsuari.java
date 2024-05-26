package main.domini.controladors;

import main.domini.classes.Usuari;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;
import main.persistencia.CtrlUsuariData;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class CtrlUsuari {

    private Usuari usuariActual;
    private static CtrlUsuariData CUD;
    private static CtrlUsuari CU;
    private Gson gson;

    /**
     * Constructor privat de la classe CtrlUsuari.
     * Inicialitza l'instància de CtrlUsuariData i Gson.
     */
    private CtrlUsuari() {
        CUD = CtrlUsuariData.getInstance();
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
        if (!CUD.existeixUsuari(nomUsuari)) {
            throw new ExcepcioUsuariNoExisteix(nomUsuari);
        }
        JsonReader reader = CUD.getUsuari(nomUsuari);
        usuariActual = gson.fromJson(reader, Usuari.class);
        if (!usuariActual.esContrasenyaCorrecta(contrasenya)) {
            usuariActual = null;
            throw new ExcepcioContrasenyaIncorrecta();
        }
    }

    /**
     * Registra un nou usuari amb el nom d'usuari i contrasenya proporcionats.
     * 
     * @param nomUsuari  Nom d'usuari.
     * @param contrasenya  Contrasenya de l'usuari.
     * @throws ExcepcioUsuariJaExisteix  Si l'usuari ja existeix.
     * @throws IOException  Si hi ha un error d'entrada/sortida.
     */
    public void registrarse(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        if (CUD.existeixUsuari(nomUsuari)) {
            throw new ExcepcioUsuariJaExisteix(nomUsuari);
        }
        usuariActual = new Usuari(nomUsuari, contrasenya);
        String dadesUsuariJson = gson.toJson(usuariActual);
        CUD.guardarUsuari(nomUsuari, dadesUsuariJson);
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
        if (!usuariActual.esContrasenyaCorrecta(ctrActual)) {
            throw new ExcepcioContrasenyaIncorrecta();
        }
        usuariActual.setContrasenya(ctrNova);
        String dadesUsuariJson = gson.toJson(usuariActual);
        CUD.guardarUsuari(usuariActual.getNomUsuari(), dadesUsuariJson);
    }

    /**
     * Tanca la sessió de l'usuari actual.
     */
    public void tancarSessio() {
        usuariActual = null;
    }

    /**
     * Retorna l'usuari actual.
     * 
     * @return Usuari actual.
     */
    public Usuari getUsuariActual() {
        return usuariActual;
    }

}