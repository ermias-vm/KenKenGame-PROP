package main.persistencia;

public class ControladorPersistencia {

    private static ControladorPersistencia controladorPersistencia_;
    private static ControladorPersistenciaTauler controladorPersistenciaTauler_;
    private static CtrlUsuariData controladorPersistenciaUsuari_;
    private static ControladorPersistenciaPartida controladorPersistenciaPartida_;

    private ControladorPersistencia() {
        controladorPersistenciaTauler_ = ControladorPersistenciaTauler.getInstance();
        controladorPersistenciaUsuari_ = CtrlUsuariData.getInstance();
        controladorPersistenciaPartida_ = ControladorPersistenciaPartida.getInstance();
    }

    public static ControladorPersistencia getInstance() {
        if (controladorPersistencia_ == null) controladorPersistencia_ = new ControladorPersistencia();
        return controladorPersistencia_;
    }

    //AFEGIR FUNCIONS DE CONTROLADORS DE PERSISTENCIA
}
