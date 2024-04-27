package main.domini.controladors;

import main.domini.interficies.Operacio;
import main.domini.classes.operacions.*;
import main.domini.classes.Casella;
import main.domini.classes.RegioJoc;
import main.domini.classes.TaulerJoc;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class CtrlKenkens {

    private final String  pathTaulers = "Data/taulers/";

    private Operacio getOperacio(int oper) {
        switch (oper) {
            case 1:
                return new Suma();
            case 2:
                return new Resta();
            case 3:
                return new Multiplicacio();
            case 4:
                return new Divisio();
            case 5:
                return new Modul();
            case 6:
                return new Exponenciacio();
            default:
                //System.out.println("SENSE OPERACIO");
                return null;
        }
    }

    public String getTaulerJoc(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        return content.toString();
    }

    public TaulerJoc llegirTaulerJoc(int id , String grau) {
        String path = Paths.get(pathTaulers,grau, id + ".txt").toAbsolutePath().toString();
        try {
            String content = getTaulerJoc(path);
            //System.out.println(content); //imprimir prova
            String[] lines = content.split("\n");
            String[] primeraLinea = lines[0].split(" ");
            System.out.println(Arrays.toString(primeraLinea));
            int N = Integer.parseInt(primeraLinea[0]);
            int R = Integer.parseInt(primeraLinea[1]);


            TaulerJoc TJ = new TaulerJoc(id, N);

            //Llegir cada regio
            for (int i = 0; i < R; i++) {
                String[] regioInfo = lines[i+1].split(" ");
                //System.out.println(Arrays.toString(regioInfo));
                int oper = Integer.parseInt(regioInfo[0]);
                int result = Integer.parseInt(regioInfo[1]);

                ArrayList<Casella> caselles = new ArrayList<>();
                int j = 3;
                //Llegir cada casella de la regio
                while(j < regioInfo.length) {
                    int x = Integer.parseInt(regioInfo[j]);
                    int y = Integer.parseInt(regioInfo[j+1]);
                    Casella casella = new Casella(x, y);
                    j += 2;
                    if (j < regioInfo.length && regioInfo[j].startsWith("[")) {
                        String valorString = regioInfo[j].replaceAll("[\\[\\]]", "");
                        casella.setValor(Integer.parseInt(valorString));
                        casella.setInmodificable();
                        ++j;
                    }
                    caselles.add(casella);
                    TJ.afegirCasella(casella);
                }

                Operacio operacio = getOperacio(oper);
                //System.out.print(operacio.getNumOperacio());
                RegioJoc rj = new RegioJoc(caselles, operacio, result);
                TJ.afegirRegioJoc(rj);
            }

            return TJ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mostrarTaulerJoc(TaulerJoc TJ) throws Exception {
        int grau = TJ.getGrau();
        for (int i = 1; i <= grau; i++) {
            for (int j = 1; j <= grau; j++) {
                int valor = TJ.getCasella(i, j).getValor();
                System.out.print(valor + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}