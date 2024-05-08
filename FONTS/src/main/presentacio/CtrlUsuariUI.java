package main.presentacio;

import main.domini.controladors.CtrlUsuari;
import main.domini.classes.Usuari;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import java.io.IOException;

public class CtrlUsuariUI {
    private static CtrlUsuariUI CUui;
    private CtrlUsuari CU;

    public static CtrlUsuariUI getInstance() {
        if (CUui == null) CUui = new CtrlUsuariUI();
        return CUui;
    }

    public CtrlUsuariUI() {
        CU = new CtrlUsuari();
    }

    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CU.iniciarSessio(nomUsuari, contrasenya);
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CU.registrarse(nomUsuari, contrasenya);
    }

    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CU.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    public Usuari getUsuariActual() {
        return CU.getUsuariActual();
    }

    ///poser cridar desde CtrlPresentacio
    public void tancarSessio() {
        CU.tancarSessio();
    }
}