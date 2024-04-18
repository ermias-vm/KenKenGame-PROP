package main.domini.excepcions;

public class ExcepcioNoDivisor extends Exception {
    private String missatge_;
    public ExcepcioNoDivisor(int valor, int valor2) {
    this ("Error: El valor " + valor + " no es divisor de " + valor2); ;
    }
    public ExcepcioNoDivisor(String s) {
        super(s);
        this.missatge_ = s;
    }

}
