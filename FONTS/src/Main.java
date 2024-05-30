import main.presentacio.CtrlPresentacio;

/**
 * Classe Main que inicialitza el programa.
 * Crea una instància de CtrlPresentacio i crida al mètode run.
 * @author Ermias Valls Mayor
 */
public class Main {
    public static void main(String[] args) {
        CtrlPresentacio.getInstance().run();
    }
}