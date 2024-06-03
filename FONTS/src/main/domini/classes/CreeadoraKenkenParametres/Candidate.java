package main.domini.classes.CreeadoraKenkenParametres;

/**
 * La classe Candidate es la que s'encarrega, dins de CreadorKenkenParam, de buscar, d'un casella en especific, quienes son les caselles candidates a fusionar-se amb ella en una regio.
 * @autor David Giribet Casado
 */
public class Candidate {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    /**
     * Constructor de la classe Candidate.
     *
     * @param x1 Coordenada x de la primera cel·la.
     * @param y1 Coordenada y de la primera cel·la.
     * @param x2 Coordenada x de la segona cel·la.
     * @param y2 Coordenada y de la segona cel·la.
     */
    public Candidate(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
