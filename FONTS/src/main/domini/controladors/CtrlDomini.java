package main.domini.controladors;
import main.domini.classes.Usuari;
import main.persistencia.ControladorPersistencia;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import java.io.IOException;


/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private static CtrlDomini CDomini;
    private static CtrlUsuari CUsuari;
    private static ControladorPartida CPartida;
    private static CtrlKenkens CKenkens;
    private static ControladorRanking CRanking;
    private static ControladorPersistencia CPersistencia;

    private Usuari usuariActual;

    private CtrlDomini() {
        CPersistencia = ControladorPersistencia.getInstance();
        CUsuari = CtrlUsuari.getInstance();
        CPartida = ControladorPartida.getInstance();
        CKenkens = CtrlKenkens.getInstance();
        //CRanking = ControladorRanking.getInstance();

    }

    public static CtrlDomini getInstance() {
        if (CDomini == null) CDomini = new CtrlDomini();
        return CDomini;
    }

    //////////////// FUNCIONS DE USUARI ////////////////

    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CUsuari.iniciarSessio(nomUsuari, contrasenya);
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CUsuari.registrarse(nomUsuari, contrasenya);
    }

    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CUsuari.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    public void setUsuariActual(Usuari usuari) {
        usuariActual = usuari;
    }

    public Usuari getUsuariActual() {
        return usuariActual;
    }

    public void tancarSessio() {
        usuariActual = null;
    }

}