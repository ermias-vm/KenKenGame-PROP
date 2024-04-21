package main.domini.controladors;
import java.util.ArrayList;

import main.domini.excepcions.*;
import main.domini.classes.Partida;
import main.domini.classes.Ranking;
import java.io.*;


import main.domini.classes.RankinperMida;


import main.persistencia.ControladorPersistenciaPartida;



public class CtrlRanking {
    ControladorPersistenciaPartida controladorPersistenciaPartida = new ControladorPersistenciaPartida();

    public void carregarAcabadesGrau(Ranking a, int grau) {
        ArrayList<ArrayList<String>> informacio = controladorPersistenciaPartida.carregaPartidesAcabadesGrau(grau);
        if (informacio != null) {
            a.informacio = informacio;
        }
    }

    public void carregarPartidesAcabadesUsuari(Ranking a, String Usuari) {
        ArrayList<ArrayList<String>> informacio = controladorPersistenciaPartida.carregarPartidesAcabadesUsuari(Usuari);
        if (informacio != null) {
            a.informacio = informacio;
        }
    }
}
