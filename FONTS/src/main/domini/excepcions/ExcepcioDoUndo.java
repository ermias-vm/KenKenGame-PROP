package main.domini.excepcions;

public class ExcepcioDoUndo extends Exception{
    public ExcepcioDoUndo() {
        super();
    }

    public ExcepcioDoUndo(String message) {
        super(message);
    }
}
