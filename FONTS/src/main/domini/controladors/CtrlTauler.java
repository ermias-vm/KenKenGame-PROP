package main.domini.controladors;

import main.domini.excepcions.*;
import main.domini.interficies.Operacio;
import main.domini.classes.operacions.*;
import main.domini.classes.Casella;
import main.domini.classes.RegioJoc;
//import main.domini.classes.Regio;
import main.domini.classes.Tauler;
import main.domini.classes.TaulerJoc;

//import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import java.nio.file.Paths;



public class CtrlTauler {
    private Tauler taulerActual;

    public CtrlTauler() {
        taulerActual = null;
    }

    public Tauler getTaulerActual() {
        return taulerActual;
    }

    public void setTaulerActual(Tauler tauler) {
        taulerActual = tauler;
    }

    public boolean existeixTauler(int idTauler) {
        String nomFitxer = "BDTaulers/" + idTauler + ".txt";
        File fitxer = new File(nomFitxer);
        return fitxer.exists();
    }

    public void registrarTauler(int idTauler) throws IOException {
        try {
            if (existeixTauler(idTauler)) throw new ExcepcioTaulerJaExisteix();
        } catch (ExcepcioTaulerJaExisteix e) {
            System.out.println(e.getMessage());
        }

        String nomFitxer = "BDTaulers/" + idTauler + ".txt";
        PrintWriter fitxer = new PrintWriter(new FileWriter(nomFitxer));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix les dades del tauler (N R oper1 result1 e1 x11 y11 ...):");
        String dadesTauler = scanner.nextLine();

        fitxer.println(dadesTauler);

        fitxer.close();
        scanner.close();

        System.out.println("Tauler registrat amb èxit.");
    }

    public int obtenirGrauTauler(int idTauler) throws IOException, ExcepcioTaulerNoExisteix {
        if (!existeixTauler(idTauler)) {
            throw new ExcepcioTaulerNoExisteix();
        }
    
        String nomFitxer = "BDTaulers/" + idTauler + ".txt";
        File fitxer = new File(nomFitxer);
        
        Scanner scanner = new Scanner(fitxer);
        int grau = 0;
        
        if (scanner.hasNextLine()) {
            String[] primeraLinea = scanner.nextLine().split(" ");
            grau = Integer.parseInt(primeraLinea[0]);
        }
        
        scanner.close();
        return grau;
    }
    

    public void carregarTauler(int idTauler) {
        try {
            int g = obtenirGrauTauler(idTauler);
            Tauler tauler = new Tauler(idTauler, g);
            setTaulerActual(tauler);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del tablero: " + e.getMessage());
        } catch (ExcepcioTaulerNoExisteix e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static int[][] llegirTauler(int idTauler) throws IOException {
        String nomFitxer = "Data/" + idTauler + ".txt";
        BufferedReader reader = new BufferedReader(new FileReader(nomFitxer));
        String linia = reader.readLine();
        String[] dimensions = linia.split(" ");
        int dimensio = Integer.parseInt(dimensions[0]);
        int numRegions = Integer.parseInt(dimensions[1]);
        int[][] tauler = new int[dimensio][dimensio];
        
        for (int i = 0; i < numRegions; i++) {
            linia = reader.readLine();
            String[] valors = linia.split(" ");
            int numElements = Integer.parseInt(valors[2]);
            int k = 3;
            for (int j = 0; j < numElements; j++) {
                int fila = Integer.parseInt(valors[k]) - 1; // Ajustar a l'indexació de Java (que comença per 0)
                int columna = Integer.parseInt(valors[k + 1]) - 1; // Ajustar a l'indexació de Java
                if (/*j + 1 < valors.length &&*/ valors[k + 2].startsWith("[")) {
                    int valor = Integer.parseInt(valors[j + 1].substring(1, valors[k + 2].length() - 1));
                    tauler[fila][columna] = valor;
                    k = k + 3;
                } else {
                    tauler[fila][columna] = 0;
                    k = k + 2;
                }
                
            }
        }
        
        reader.close();
        return tauler;
    }

    public void mostrarTauler(int idTauler) {
        try {
            // Leer el tablero desde el archivo
            int[][] tauler = llegirTauler(idTauler);
    
            // Mostrar el tablero
            int n = tauler.length; // Obtener el tamaño del tablero (grado)
    
            // Imprimir filas
            for (int i = 0; i < n; i++) {
                // Imprimir borde superior de la fila
                for (int j = 0; j < n; j++) {
                    System.out.print("+---");
                }
                System.out.println("+");
    
                // Imprimir valores de la fila
                for (int j = 0; j < n; j++) {
                    int valor = tauler[i][j];
                    String valorStr = (valor == 0) ? " " : Integer.toString(valor);
                    System.out.print("| " + valorStr + " ");
                }
                System.out.println("|");
            }
    
            // Imprimir borde inferior del tablero
            for (int j = 0; j < n; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del tablero: " + e.getMessage());
        }
    }

    public Tauler llegirTaulerJoc(int idTauler) {
        String path = Paths.get("Data/", idTauler + ".txt").toString();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            return construirTaulerKenKen(idTauler,lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TaulerJoc construirTaulerKenKen(int idTauler, List<String> lines) {
        int n = Integer.parseInt(lines.get(0));
        TaulerJoc K = new TaulerJoc(idTauler,n);
        int nr = Integer.parseInt(lines.get(1));
        for (int i = 2; i < nr + 2; ++i) {
            ArrayList<Casella> VC = new ArrayList<>();
            String[] parts = lines.get(i).split(" ");
            int nc = Integer.parseInt(parts[2]);
            Operacio op;
            if(parts[0].equals("1")){
                op = new Suma();
            }else if(parts[0].equals("2")){
                op = new Resta();
            }else if(parts[0].equals("3")){
                op = new Multiplicacio();
            }else if(parts[0].equals("4")){
                op = new Divisio();
            }else if(parts[0].equals("5")){
                op = new Modul();
            }else{
                op = new Exponenciacio();
            }
            //Operacio op = new Operacio(parts[0]);
            //Operacio op = new Operacio()
            int res = Integer.parseInt(parts[1]);
            int j = 1;
            for (int k = 0; k < nc; ++k) {
                int x = Integer.parseInt(parts[j]);
                int y = Integer.parseInt(parts[j + 1]);
                Casella c = K.getCasella(x, y);
                VC.add(c);
                j += 2;
            }
            RegioJoc r = new RegioJoc(VC, op, res);
            K.afegirRegioJoc(r);
        }
        return K;
    }
    

}
