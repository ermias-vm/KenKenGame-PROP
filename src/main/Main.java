package main;

import main.domini.controladors.CtrlDomini;

public class Main {
    private static CtrlDomini CD;

    public static void main (String[] args) throws Exception {
        System.out.println("Inicia controlador de dominio...");
        CD = new CtrlDomini();

        // Asumiendo que tienes los parámetros necesarios para estas funciones
        importarKenken();
        resoldreKenken();
        mostrarKenken();
    }

    public static void importarKenken() {

        CD.importarKenken();
    }

    public static void resoldreKenken() {

        CD.resoldreKenken();
    }

    public static void mostrarKenken() {

        CD.mostrarKenken();
    }
}