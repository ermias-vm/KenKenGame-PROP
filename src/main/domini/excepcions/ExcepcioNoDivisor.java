package main.domini.excepcions;

public class ExcepcioNoDivisor extends Exception {
    private String missatge;
    public ExcepcioNoDivisor(int valor, int resultat) {
    this ("Error: El valor " + valor + " no es divisor de " + resultat); ;
    }
    public ExcepcioNoDivisor(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Operacio: " + missatge;
    }
}
