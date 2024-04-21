package main.domini.excepcions;
/**
 * Excepció que es llença quan el nombre de valors introduïts en algun context no és el correcte.
 * Utilitzada en la capa domini en la interacció entre operació i regió.
 */
public class ExcepcioMoltsValors extends Exception {
    /**
     * Creadora de la classe.
     * @param mida Nombre de valors que s'esperaven.
     * @param quantitat Límit quantitatiu del nombre de valors. Pot ser "EQ" (exactament), "MAX" (com a màxim) o "MIN" (com a mínim) el nombre de valors.
     */
    public ExcepcioMoltsValors(int mida, String quantitat) {
        this(creaMissatge(mida, quantitat));
    }

    /**
     * Crea el missatge d'error a partir de la mida i la quantitat esperada.
     * @param mida Nombre de valors que s'esperaven.
     * @param quantitat Indica el límit com a String (EQ, MAX o MIN).
     * @return Missatge d'error.
     */
    private static String creaMissatge(int mida, String quantitat) {
        if ("EQ".equals(quantitat)) return( "Error en el nombre de valors, nomes s'accepten exactament: " + String.valueOf(mida) + " valors");
        else if ("MAX".equals(quantitat)) return( "Error en el nombre de valors, s'accepten com a maxim: " + String.valueOf(mida) + " valors");
        else if ("MIN".equals(quantitat)) return("Error en el nombre de valors, s'accepten com a minim: " + String.valueOf(mida) + " valors");
        return "Error en el nombre de valors";
    }
    /**
     * Creadora de la classe.
     * @param s Missatge d'error.
     */
    public ExcepcioMoltsValors(String s) {
        super(s);
    }
}
