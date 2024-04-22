package drivers;

import main.domini.controladors.CtrlDomini;

import java.util.Scanner;

public class DriverKenken {
    private CtrlDomini CD;

    public DriverKenken(CtrlDomini CD) {
        this.CD = CD;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int idTauler = 0;

        do {
            System.out.println("Introdueixi un el Tauler a solucionar  [1-10]:");
            System.out.println("Introdueixi 0 per sortir.");
            idTauler = scanner.nextInt();

            if (idTauler == 0) {
                break;
            }

            if (idTauler < 1 || idTauler > 10) {
                System.out.println("Número invàlid. Si us plau, introdueixi un número entre 1 i 10.");
                continue;
            }

            try {
                CD.importarKenken(idTauler);
                CD.resoldreKenken();
            } catch (Exception e) {
                System.out.println("S'ha produït un error: " + e.getMessage());
            }
        } while (idTauler != 0);

        scanner.close();
    }
}