package main.domini.classes;


import java.util.ArrayList;
import main.domini.interficies.Operacio;
import main.domini.classes.operacions.*;

/**
 * Classe RegioJoc que estén la classe Regio.
 * Representa una regió de joc amb una operació i un resultat associats.
 *
 * @author Ermias Valls Mayor
 */
public class RegioJoc extends Regio {
    private Operacio operacio;
    private int resultat;

    /**
     * Constructor de RegioJoc amb tamany, operació i resultat.
     *
     * @param tam       Tamany de la regió.
     * @param operacio  Operació de la regió.
     * @param resultat  Resultat de la regió.
     */
    public RegioJoc(int tam, Operacio operacio, int resultat) {
        super(tam);
        this.operacio = operacio;
        this.resultat = resultat;
    }

    /**
     * Constructor de RegioJoc amb una llista de caselles, operació i resultat.
     *
     * @param caselles  Llista de caselles de la regió.
     * @param operacio  Operació de la regió.
     * @param resultat  Resultat de la regió.
     */
    public RegioJoc(ArrayList<Casella> caselles, Operacio operacio, int resultat) {
        super(caselles);
        this.operacio = operacio;
        this.resultat = resultat;
    }

    /**
     * Retorna l'operació de la regió.
     *
     * @return Operació de la regió.
     */
    public Operacio getOperacio() {
        return this.operacio;
    }

    /**
     * Estableix l'operació de la regió.
     *
     * @param operacio  Operació a establir.
     */
    public void setOperacio(Operacio operacio) {
        this.operacio = operacio;
    }

    /**
     * Retorna el resultat de la regió.
     *
     * @return Resultat de la regió.
     */
    public int getResultat() {
        return this.resultat;
    }

    /**
     * Estableix el resultat de la regió.
     *
     * @param resultat  Resultat a establir.
     */
    public void seResultat(int resultat) {
        this.resultat = resultat;
    }

    /**
     * Retorna els valors de les caselles de la regió.
     *
     * @return Array d'enters amb els valors de les caselles.
     */
    public int[] getValorsCaselles() {
        ArrayList<Casella> caselles = getCaselles();
        int[] valors = new int[caselles.size()];

        for (int i = 0; i < caselles.size(); i++) {
            valors[i] = caselles.get(i).getValor();
        }
        return valors;
    }

    /**
     * Comprova si la regió està completa.
     *
     * @return true si la regió està completa, false en cas contrari.
     */
    public boolean esCompleta() {
        ArrayList<Casella> caselles = getCaselles();
        for (Casella casella : caselles) {
            if (casella.esBuida()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprova si la regió és vàlida.
     *
     * @return true si la regió és vàlida, false en cas contrari.
     * @throws Exception si hi ha un error en l'operació.
     */
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