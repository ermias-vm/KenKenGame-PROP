package main.domini.classes;

import java.util.List;
import java.util.ArrayList;

import main.domini.interficies.Operacio;

public class RegioJoc extends Regio {
    private Operacio operacio;
    private int resultat;


    public RegioJoc(int tam, Operacio operacio, int resultat) {
        super(tam);
        this.operacio = operacio;
        this.resultat = resultat;
    }

    public RegioJoc(ArrayList<Casella> caselles, Operacio operacio, int resultat) {
        super(caselles);
        this.operacio = operacio;
        this.resultat = resultat;
    }

    public Operacio getOperacio() {
        return this.operacio;
    }

    public void setOperacio(Operacio operacio) {
        this.operacio = operacio;
    }

    public int getResultat() {
        return this.resultat;
    }

    public void seResultat(int resultat) {
        this.resultat = resultat;
    }

    public int[] getValorsCaselles() {
        ArrayList<Casella> caselles = getCaselles();
        int[] valors = new int[caselles.size()];

        for (int i = 0; i < caselles.size(); i++) {
            valors[i] = caselles.get(i).getValor();
        }
        return valors;
    }


    public boolean esCompleta() {
        ArrayList<Casella> caselles = getCaselles();
        for (Casella casella : caselles) {
            if (casella.esBuida()) {
                return false;
            }
        }
        return true;
    }

    public boolean esValida() throws Exception {

        if (!esCompleta()) {
            return false;
        }
        int valors[] = getValorsCaselles();
        //no operacio
        if (getTamany() == 1) return this.resultat == valors[0];

        int numOp  = this.operacio.getNumOperacio();

        //operacions on:  2 >= tamanyRegio <= N : suma , multiplicacio
        if (numOp == 1 || numOp == 3) {
            int result = operacio.operaN(valors);
            return result == this.resultat;
        }

        //operacions on: tamanyRegio = 2 : resta, divisió, mòdul, exponenciació
        int result1 = this.operacio.opera2(valors[0], valors[1]);
        int result2 = this.operacio.opera2(valors[1], valors[0]);
        return resultat == result1 || resultat == result2;
    }

}