package main.domini.controladors;


import main.persistencia.ControladorPersistencia;


/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private static CtrlDomini ctrlDomini;

    /*
    private static CtrlUsuari ctrlUsuari;
    private static ControladorPartida ctrlPartida;
    private static CtrlKenkens ctrlKenkens;
    private static CtrlRanking ctrlRanking;
    private static ControladorPersistencia ctrlPersistencia;
    */


    private CtrlDomini() {

        //AC = new CreadorKenkenManual();
    }

    public static CtrlDomini getInstance() {
        if (ctrlDomini == null) ctrlDomini = new CtrlDomini();
        return ctrlDomini;
    }


}