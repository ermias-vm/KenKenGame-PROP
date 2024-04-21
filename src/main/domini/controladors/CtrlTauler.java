package main.domini.controladors;

import main.domini.excepcions.*;

//import main.domini.classes.Casella;
//import main.domini.classes.Regio;
import main.domini.classes.Tauler;

//import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

//import java.util.ArrayList;
//import java.util.List;



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
        String nomFitxer = "BDTaulers/" + idTauler + ".txt";
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
    

}
