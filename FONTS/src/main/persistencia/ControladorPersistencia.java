package main.persistencia;

public class ControladorPersistencia {
    private static ControladorPersistenciaTauler controladorPersistenciaTauler_;
    private static CtrlUsuariData controladorPersistenciaUsuari_;
    private static ControladorPersistenciaPartida controladorPersistenciaPartida_;
    public ControladorPersistencia() {
        controladorPersistenciaTauler_ = new ControladorPersistenciaTauler();
        controladorPersistenciaUsuari_ = new CtrlUsuariData();
        controladorPersistenciaPartida_ = new ControladorPersistenciaPartida();
    }
    public static ControladorPersistenciaTauler getControladorPersistenciaTauler() {
        return controladorPersistenciaTauler_;
    }
    public static CtrlUsuariData getControladorPersistenciaUsuari() {
        return controladorPersistenciaUsuari_;
    }
    public static ControladorPersistenciaPartida getControladorPersistenciaPartida() {
        return controladorPersistenciaPartida_;
    }
}
