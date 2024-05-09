package main.domini.controladors;

import main.domini.classes.*;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistencia;

import java.io.IOException;
//import main.persistencia.CtrlPersistencia;

/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private static CtrlUsuari controladorUsuari_;
    private static ControladorPartida controladorPartida_;
    private static CtrlKenkens controladorKenkens_;
    private static CtrlRanking controladorRanking_;
    private static ControladorPersistencia controladorPersistencia_;
    /**
     * Constructor del controlador de domini.
     */
    public CtrlDomini() {
        new ControladorPersistencia();
        controladorPartida_ = new ControladorPartida();
        controladorRanking_ = new CtrlRanking();
        controladorKenkens_ = new CtrlKenkens(ControladorPersistencia.getControladorPersistenciaTauler());
        controladorUsuari_ = new CtrlUsuari();
        controladorPartida_.setControladorTauler(controladorKenkens_);
        controladorPartida_.setControladorPersistenciaPartida(ControladorPersistencia.getControladorPersistenciaPartida());
        //AC = new CreadorKenkenManual();
    }

    }