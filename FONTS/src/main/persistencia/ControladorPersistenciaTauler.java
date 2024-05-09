package main.persistencia;


import java.io.*;
import java.util.HashMap;

public class ControladorPersistenciaTauler {
    /**
     * Llegeix un tauler del disc.
     * @param identificadorTauler Identificador del tauler.
     * @return Dades del tauler.
     */
    public String llegirTauler(String identificadorTauler) {
        int mida = Integer.parseInt(identificadorTauler.split("-")[1]);
        StringBuilder dadesTauler = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new FileReader("data/taulers/mida"+mida+"/"+identificadorTauler));
            String linia;
            while((linia = lector.readLine()) != null) {
                dadesTauler.append(linia).append("\n");
            }
            lector.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/taulers/diccionariTaulers"));
            Object result = ois.readObject();
            if (result instanceof HashMap) {
                taulersPerSufix = (HashMap<String, String[]>) result;
            }
        }
        catch (IOException|ClassNotFoundException e) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/taulers/diccionariTaulers"));
                oos.writeObject(taulersPerSufix);
                oos.close();
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        }
        String sufix = generaSufixFitxer(dadesTauler);
        String[] identificadors = taulersPerSufix.get(sufix);
        for (String sufixTaulers:taulersPerSufix.keySet()){
            System.out.println(sufixTaulers);
            for (String identificador:taulersPerSufix.get(sufixTaulers)){
                System.out.println(identificador);
            }
        }
        System.out.println(taulersPerSufix.isEmpty());
        if (identificadors != null) {
            for (String identificador : identificadors) {
                String dadesTaulerGuardat = llegirTauler(identificador);
                System.out.println("Sha trobat\n" + dadesTaulerGuardat);
                if (dadesTaulerGuardat.equals(dadesTauler)) {
                    return identificador;
                }
            }
        }
        int mida = Integer.parseInt(sufix.split("-")[1]);
        String carpeta = "data/taulers/mida"+mida;
        String fitxer;
        try{
            BufferedReader lector = new BufferedReader(new FileReader("data/taulers/ultimidentificador.txt"));
            String identificador = lector.readLine();
            lector.close();
            int identificadorNou = Integer.parseInt(identificador) + 1;
            PrintWriter escriptor = new PrintWriter("data/taulers/ultimidentificador.txt");
            escriptor.print(identificadorNou);
            escriptor.close();
            fitxer = identificador+"-"+mida;
            File directori = new File (carpeta);
            File actualFile = new File (directori, fitxer);
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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/taulers/diccionariTaulers"));
            oos.writeObject(taulersPerSufix);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fitxer;
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
            return llistaFitxers[index].getName();
        }
        return "";
    }
}
