package main.persistencia;


import java.io.*;

public class ControladorPersistenciaTauler {
    /**
     * Llegeix un tauler a partir del seu identificador.
     * @param identificadorTauler Identificador del tauler.
     * @return Dades del tauler en el format d'entrada descrit a l'enunciat.
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
     * Genera un identificador per a un tauler a partir de les dades del tauler i el guarda a la carpeta corresponent.
     * Si ja existeix un tauler amb les mateixes dades, retorna l'identificador d'aquest.
     * @param dadesTauler Dades del tauler en el format d'entrada descrit a l'enunciat.
     * @return Identificador del tauler.
     */
    public String generaIdentificadorIGuardaTauler(String dadesTauler) {
        String sufix = generaSufixFitxer(dadesTauler);
        int mida = Integer.parseInt(sufix.split("-")[1]);
        String carpeta = "data/taulers/mida"+mida;
        String fitxer = "";
        File folder = new File(carpeta);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    if (file.getName().contains(sufix)) {
                        try{
                            StringBuilder text = new StringBuilder();
                            BufferedReader lector = new BufferedReader(new FileReader(file.getName()));
                            String linia;
                            while((linia = lector.readLine()) != null) {
                                text.append(linia).append("\n");
                            }
                            lector.close();
                            if (text.toString().equals(dadesTauler)) {
                                fitxer = file.getName();
                                break;
                            }
                        }
                        catch (IOException e) {
                        }
                    }
                }
            }
        }
        if (fitxer.equals("")) {
            try{
                BufferedReader lector = new BufferedReader(new FileReader("data/taulers/ultimidentificador.txt"));
                String identificador = lector.readLine();
                lector.close();
                int identificadorNou = Integer.parseInt(identificador) + 1;
                PrintWriter escriptor = new PrintWriter("data/taulers/ultimidentificador.txt");
                escriptor.print(identificadorNou);
                escriptor.close();
                fitxer = identificador+sufix;
                File directori = new File (carpeta);
                File actualFile = new File (directori, fitxer);
                PrintWriter escriptorTauler = new PrintWriter(actualFile);
                escriptorTauler.print(dadesTauler);
                escriptorTauler.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fitxer;
    }

    /**
     * Genera un sufix per a l'identificador del fitxer del tauler a partir de les dades del tauler.
     * Format:
     * "-mida-operacio1_operacio2_..._operacioN"
     * @param dadesTauler Dades del tauler en el format d'entrada descrit a l'enunciat.
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
     * Selecciona un tauler aleatori d'una mida concreta.
     * @param mida Mida del tauler.
     * @return Identificador del tauler seleccionat o string buida si no hi ha taulers d'aquella mida.
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
