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
    

}
