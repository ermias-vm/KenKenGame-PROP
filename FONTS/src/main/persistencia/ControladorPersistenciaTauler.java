package main.persistencia;


import main.domini.excepcions.ExcepcioTaulerNoExisteix;

import java.io.*;
import java.util.HashMap;

import static main.presentacio.CtrlPresentacio.MIDAMAX;
import static main.presentacio.CtrlPresentacio.MIDAMIN;

/**
 * Controlador de persistència de taulers.
 * S'encarrega de llegir i guardar taulers al disc. Opera sobre la carpeta "data/taulers".
 */
public class ControladorPersistenciaTauler {
    /**
     * Instància del controlador de persistència de taulers.
     */
    private static ControladorPersistenciaTauler ctrTaulerData;
    /**
     * Constructora de la classe.
     */
    public ControladorPersistenciaTauler() {
        inicialitzaDiccionariTaulers();
    }
    /**
     * Retorna la instància del controlador de persistència de taulers.
     * @return Instància del controlador de persistència de taulers.
     */
    public static ControladorPersistenciaTauler getInstance() {
        if (ctrTaulerData== null) ctrTaulerData= new ControladorPersistenciaTauler();
        return ctrTaulerData;
    }

    /**
     * Llegeix un tauler del disc.
     * @param identificadorTauler Identificador del tauler.
     * @return Dades del tauler.
     * @throws ExcepcioTaulerNoExisteix Si el tauler no existeix.
     */
    public String llegirTauler(String identificadorTauler) throws ExcepcioTaulerNoExisteix {
        String[] identificador = identificadorTauler.split("-");
        if (identificador.length != 2) {
            throw new ExcepcioTaulerNoExisteix(identificadorTauler);
        }
        int mida = Integer.parseInt(identificador[1]);
        String carpeta = "data/taulers/mida"+mida;
        StringBuilder dadesTauler = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(carpeta+"/"+identificadorTauler + ".txt"));
            String linia;
            while((linia = lector.readLine()) != null) {
                dadesTauler.append(linia).append("\n");
            }
            lector.close();
        } catch (IOException e) {
            throw new ExcepcioTaulerNoExisteix(identificadorTauler);
        }
        return dadesTauler.toString();
    }

    /**
     * Genera un identificador per al tauler a partir de les dades del tauler i el guarda al disc, comprova primer que no existeixi ja,
     * si existeix retorna l'identificador del tauler que existeix ja.
     * @param dadesTauler Dades del tauler.
     * @return Identificador del tauler.
     */
    public String generaIdentificadorIGuardaTauler(String dadesTauler) {
        HashMap<String, String[]> taulersPerSufix = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/taulers/.diccionariTaulers"));
            Object result = ois.readObject();
            if (result instanceof HashMap) {
                taulersPerSufix = (HashMap<String, String[]>) result;
            }
        }
        catch (IOException|ClassNotFoundException e) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/taulers/.diccionariTaulers"));
                oos.writeObject(taulersPerSufix);
                oos.close();
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        }
        String sufix = generaSufixFitxer(dadesTauler);
        int mida = Integer.parseInt(sufix.split("-")[1]);
        String[] identificadors = taulersPerSufix.get(sufix);
        if (identificadors != null) {
            for (String identificador : identificadors) {
                try {
                    String dadesTaulerGuardat = llegirTauler(identificador);
                    if (dadesTaulerGuardat.equals(dadesTauler)) {
                        return identificador;
                    }
                } catch (ExcepcioTaulerNoExisteix e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String carpeta = "data/taulers/mida"+mida;
        String fitxer;
        try{
            BufferedReader lector = new BufferedReader(new FileReader("data/taulers/.ultimidentificador.txt"));
            String identificador = lector.readLine();
            lector.close();
            int identificadorNou = Integer.parseInt(identificador) + 1;
            PrintWriter escriptor = new PrintWriter("data/taulers/.ultimidentificador.txt");
            escriptor.print(identificadorNou);
            escriptor.close();
            fitxer = identificadorNou + "-" + mida;
            String actualFitxer = fitxer + ".txt";
            File directori = new File (carpeta);
            File actualFile = new File (directori, actualFitxer);
            PrintWriter escriptorTauler = new PrintWriter(actualFile);
            escriptorTauler.print(dadesTauler);
            escriptorTauler.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (identificadors == null) {
            identificadors = new String[1];
            identificadors[0] = fitxer;
        }
        else {
            String[] identificadorsNou = new String[identificadors.length + 1];
            System.arraycopy(identificadors, 0, identificadorsNou, 0, identificadors.length);
            identificadorsNou[identificadors.length] = fitxer;
            identificadors = identificadorsNou;
        }
        taulersPerSufix.put(sufix, identificadors);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/taulers/.diccionariTaulers"));
            oos.writeObject(taulersPerSufix);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fitxer;
    }
    /**
     * Selecciona un tauler aleatori de la mida especificada.
     * @param mida Mida del tauler.
     * @return Nom del fitxer del tauler seleccionat o string buida si no hi ha.
     */
    public String seleccionaTaulerAleatori(int mida) {
        String carpeta = "data/taulers/mida"+mida;
        File fitxerCarpeta = new File(carpeta);
        File[] llistaFitxers = fitxerCarpeta.listFiles();
        if(llistaFitxers != null) {
            int nombreFitxers = llistaFitxers.length;
            int index = (int) (Math.random() * nombreFitxers);
            return llistaFitxers[index].getName().replace(".txt", "");
        }
        return "";
    }
    /**
     * Genera un sufix per a l'identificador del fitxer del tauler a partir de les dades del tauler.
     * Format:
     * "-mida-operacio1_operacio2_..._operacioN"
     * @param dadesTauler Dades del tauler.
     * @return Sufix per a l'identificador del fitxer.
     */
    private String generaSufixFitxer(String dadesTauler) {
        StringBuilder sufix = new StringBuilder();
        String[] dades = dadesTauler.split("\n");
        String[] midaRegions = dades[0].split(" ");
        //R
        int nombreRegions = Integer.parseInt(midaRegions[1]);
        //N
        int mida = Integer.parseInt(midaRegions[0]);
        sufix.append("-").append(mida).append("-");
        for (int i = 0; i < nombreRegions; i++) {
            String[] dadesRegio = dades[i + 1].split(" ");
            int operacio = Integer.parseInt(dadesRegio[0]);
            sufix.append(operacio).append("_");
        }
        sufix.deleteCharAt(sufix.length() - 1);
        return sufix.toString();
    }
    /**
     * Esborra un tauler del disc.
     * @param identificadorTauler Identificador del tauler.
     */
    private void esborraTauler(String identificadorTauler) {
        int mida = Integer.parseInt(identificadorTauler.split("-")[1]);
        String carpeta = "data/taulers/mida"+mida;
        File fitxer = new File(carpeta + "/" + identificadorTauler + ".txt");
        if (!fitxer.delete()) {
            throw new RuntimeException("No s'ha pogut esborrar el tauler.");
        }
    }
    /**
     * Inicialitza el diccionari de taulers a partir dels fitxers del disc.
     * També actualitza l'últim identificador dels taulers.
     */
    private void inicialitzaDiccionariTaulers() {
        int identificadorMax = 0;
        HashMap<String, String[]> taulersPerSufix = new HashMap<>();
        for (int i= MIDAMIN; i <= MIDAMAX; i++) {
            String carpeta = "data/taulers/mida"+i;
            File fitxerCarpeta = new File(carpeta);
            File[] llistaFitxers = fitxerCarpeta.listFiles();
            if (llistaFitxers == null) continue;
            for (File fitxer : llistaFitxers) {
                try {
                    String identificador = fitxer.getName().replace(".txt", "");
                    int identificadorInt = Integer.parseInt(identificador.split("-")[0]);
                    if (identificadorInt > identificadorMax) {
                        identificadorMax = identificadorInt;
                    }
                    String contingut = llegirTauler(fitxer.getName().replace(".txt", ""));
                    String sufix = generaSufixFitxer(contingut);
                    String[] identificadors = taulersPerSufix.get(sufix);
                    if (identificadors == null) {
                        identificadors = new String[1];
                        identificadors[0] = fitxer.getName().replace(".txt", "");
                    } else {
                        String[] identificadorsNou = new String[identificadors.length + 1];
                        System.arraycopy(identificadors, 0, identificadorsNou, 0, identificadors.length);
                        identificadorsNou[identificadors.length] = fitxer.getName().replace(".txt", "");
                        identificadors = identificadorsNou;
                    }
                    taulersPerSufix.put(sufix, identificadors);
                } catch (ExcepcioTaulerNoExisteix e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            PrintWriter escriptor = new PrintWriter("data/taulers/.ultimidentificador.txt");
            escriptor.print(identificadorMax);
            System.out.println(identificadorMax);
            escriptor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/taulers/.diccionariTaulers"));
            oos.writeObject(taulersPerSufix);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
