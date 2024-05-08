package main.persistencia;

import com.google.gson.stream.JsonReader;
import java.io.*;

public class CtrlUsuariData {

    private final String  pathUsuaris = "data/usuaris/";
    
    
    public JsonReader getUsuari(String nomUsuari) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(pathUsuaris + nomUsuari + ".json"));
        return reader;
    }

    public boolean existeixUsuari(String nomUsuari) {
        File file = new File(pathUsuaris + nomUsuari + ".json");
        return file.exists();
    }


    public void guardarUsuari(String nomUsuari, String dadesUsuariJson) throws IOException {
        File file = new File(pathUsuaris + nomUsuari + ".json");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        pw.print(dadesUsuariJson);
        pw.close();
    }
}