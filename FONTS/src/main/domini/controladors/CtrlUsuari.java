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


    private  CtrlUsuari() {
        CUD = CtrlUsuariData.getInstance();
        this.gson = new Gson();
    }
    public static CtrlUsuari getInstance() {
        if (CU == null) CU = new CtrlUsuari();
        return CU;
    }

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

    public void registrarse(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        if (CUD.existeixUsuari(nomUsuari)) {
            throw new ExcepcioUsuariJaExisteix(nomUsuari);
        }
        usuariActual= new Usuari(nomUsuari, contrasenya);
        String dadesUsuariJson = gson.toJson(usuariActual);
        CUD.guardarUsuari(nomUsuari, dadesUsuariJson);
    }


    public void canviarContrasenya(String ctrActual, String ctrNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        if (!usuariActual.esContrasenyaCorrecta(ctrActual)) {
            throw new ExcepcioContrasenyaIncorrecta();
        }
        usuariActual.setContrasenya(ctrNova);
        String dadesUsuariJson = gson.toJson(usuariActual);
        CUD.guardarUsuari(usuariActual.getNomUsuari(), dadesUsuariJson);
    }

    public void tancarSessio() {
        usuariActual = null;
    }

    public Usuari getUsuariActual() {
        return usuariActual;
    }


}