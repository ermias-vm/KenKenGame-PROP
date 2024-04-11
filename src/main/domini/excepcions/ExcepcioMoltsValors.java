package main.domini.excepcions;

public class ExcepcioMoltsValors extends Exception {
    private String missatge;

    public ExcepcioMoltsValors(int mida, String quantitat) {
        super();
        if ("EQ".equals(quantitat)) this.missatge = "Error en el nombre de valors, nomes s'accepten exactament: " + String.valueOf(mida) + " valors";
        else if ("MAX".equals(quantitat)) this.missatge = "Error en el nombre de valors, s'accepten com a maxim: " + String.valueOf(mida) + " valors";
        else if ("MIN".equals(quantitat)) this.missatge = "Error en el nombre de valors, s'accepten com a minim: " + String.valueOf(mida) + " valors";
    }

    public ExcepcioMoltsValors(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Operacio: " + missatge;
    }
}
