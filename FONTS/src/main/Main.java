package main;

import main.domini.controladors.CtrlDomini;

public class Main {
    private static CtrlDomini CD;

    public static void main (String[] args) throws Exception {
        System.out.println("Inicia controlador de dominio...");
        CD = new CtrlDomini();

        importarKenken();
        pintarSolucioKenken();
    }

    public static void importarKenken() {

        CD.importarKenken();
    }

    public static void pintarSolucioKenken() throws Exception {
        CD.resoldreKenken();
    }

}