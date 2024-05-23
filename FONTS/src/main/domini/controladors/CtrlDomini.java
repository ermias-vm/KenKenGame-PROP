package main.domini.controladors;

import main.persistencia.ControladorPersistencia;


/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private static CtrlDomini CDomini;
    private static CtrlUsuari CUsuari;
    private static ControladorPartida CPartida;
    private static CtrlKenkens CKenkens;
    //private static CtrlRanking CRanking;
    private static ControladorPersistencia CPersistencia;

    private CtrlDomini() {
        CPersistencia = ControladorPersistencia.getInstance();
        CUsuari = CtrlUsuari.getInstance();
        CPartida = ControladorPartida.getInstance();
        CKenkens = CtrlKenkens.getInstance();
        //CRanking = CtrlRanking.getInstance();  ----Falta afegir

    }


    public static CtrlDomini getInstance() {
        if (CDomini == null) CDomini = new CtrlDomini();
        return CDomini;
    }

    //AFEGIR FUNCIONS DE CONTROLADORS DEL DOMINI
}