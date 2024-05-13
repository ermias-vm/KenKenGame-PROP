package main.domini.controladors;

import java.io.*;
import main.persistencia.*;
import main.domini.classes.*;

public class CtrlRanking {
		
	public static void carregartipus(Ranking r, int tipus) {
		
		
			r.Informacio = ControladorPersistenciaPartida.carregaPartidesAcabadesGrau(tipus);
		
	}
    public static void carregarpersonal(Ranking r, String nom) {
		
			r.Informacio = ControladorPersistenciaPartida.carregarPartidesAcabadesUsuari(nom);
		
	}
    public RankingPerMida generarRankingPerTipus (int d, int nEntrades) {
		return new RankingPerMida(d, nEntrades);
	}
	
	public RankingUsuari generarRankingPersonal(String username) {
		return new RankingUsuari(username);
	}

}
