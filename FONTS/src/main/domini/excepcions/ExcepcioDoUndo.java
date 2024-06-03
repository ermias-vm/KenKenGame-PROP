package main.domini.excepcions;

/**
 * Excepció que es llença quan es vol fer un undo o un redo i no es pot
 */
public class ExcepcioDoUndo extends Exception{
    public ExcepcioDoUndo() {
        super();
    }

    public ExcepcioDoUndo(String message) {
        super(message);
    }
}
