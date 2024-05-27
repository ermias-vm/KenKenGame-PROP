package main.persistencia;

import com.google.gson.stream.JsonReader;

import java.io.*;

/**
 * Controlador de dades d'usuari que gestiona les operacions de persistència relacionades amb els usuaris.
 * @autor Ermias Valls Mayor
 */
public class CtrlUsuariData {

    /**
     * Instància singleton de CtrlUsuariData.
     */
    private static CtrlUsuariData CUsuariData;

    /**
     * Ruta del directori on es guarden les dades dels usuaris.
     */
    private static final String  PATH_USUARIS = "data/usuaris/";

    /**
     * Constructor privat de la classe CtrlUsuariData.
     */
    private CtrlUsuariData() {

    }

    /**
     * Retorna la instància singleton de CtrlUsuariData.
     *
     * @return Instància de CtrlUsuariData.
     */
    public static CtrlUsuariData getInstance() {
        if (CUsuariData== null) CUsuariData= new CtrlUsuariData();
        return CUsuariData;
    }

    /**
     * Retorna un JsonReader per a les dades de l'usuari amb el nom d'usuari proporcionat.
     *
     * @param nomUsuari  Nom d'usuari.
     * @return JsonReader per a les dades de l'usuari.
     * @throws FileNotFoundException  Si no es troba el fitxer de dades de l'usuari.
     */
    public JsonReader getUsuari(String nomUsuari) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(PATH_USUARIS + nomUsuari + ".json"));
        return reader;
    }

    /**
     * Comprova si existeix un usuari amb el nom d'usuari proporcionat.
     *
     * @param nomUsuari  Nom d'usuari.
     * @return Cert si l'usuari existeix, fals altrament.
     */
    public boolean existeixUsuari(String nomUsuari) {
        File file = new File(PATH_USUARIS + nomUsuari + ".json");
        return file.exists();
    }

    /**
     * Guarda les dades de l'usuari en format JSON en un fitxer.
     *
     * @param nomUsuari  Nom d'usuari.
     * @param dadesUsuariJson  Dades de l'usuari en format JSON.
     * @throws IOException  Si hi ha un error d'entrada/sortida.
     */
    public void guardarUsuari(String nomUsuari, String dadesUsuariJson) throws IOException {
        File file = new File(PATH_USUARIS + nomUsuari + ".json");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        pw.print(dadesUsuariJson);
        pw.close();
    }
}