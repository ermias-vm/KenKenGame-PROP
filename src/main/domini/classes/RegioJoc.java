package main.domini.classes;

import java.util.List;
import java.util.ArrayList;

public class RegioJoc extends Regio {
    private int operacio;
    private int resultat;


    public RegioJoc(int tam, int operacio, int resultat) {
        super(tam);
        this.operacio = operacio;
        this.resultat = resultat;
    }

    public RegioJoc(List<Casella> caselles, int operacio, int resultat) {
        super(caselles);
        this.operacio = operacio;
        this.resultat = resultat;
    }


    public int getOperacio() {
        return this.operacio;
    }

    public void setOperacio(int operacio) {
        this.operacio = operacio;
    }

    public int getResultat() {
        return this.resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public boolean esCompleta() {
        for (Casella casella : caselles) {
            if (casella.esBuida()) {
                return false;
            }
        }
        return true;
    }

    public boolean esValida() {
        if (!esCompleta()) {
            return false;
        }

        //Falta implementar
        return true;
    }


}