package main.domini.excepcions;

public class ExcepcioMoltsValors extends Exception {
    public ExcepcioMoltsValors(int mida, String quantitat) {
        this(creaMissatge(mida, quantitat));
    }
    private static String creaMissatge(int mida, String quantitat) {
        if ("EQ".equals(quantitat)) return( "Error en el nombre de valors, nomes s'accepten exactament: " + String.valueOf(mida) + " valors");
        else if ("MAX".equals(quantitat)) return( "Error en el nombre de valors, s'accepten com a maxim: " + String.valueOf(mida) + " valors");
        else if ("MIN".equals(quantitat)) return("Error en el nombre de valors, s'accepten com a minim: " + String.valueOf(mida) + " valors");
        return "Error en el nombre de valors";
    }
    public ExcepcioMoltsValors(String s) {
        super(s);
    }
}
