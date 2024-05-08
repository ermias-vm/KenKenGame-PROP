package main.domini.controladors;
import java.util.ArrayList;

import java.io.*;
import main.persistencia.*;

public class CTRLRanking {
		
	public static void carregar(Ranking r, String arxiu) {
		ControladorPersistenciaPartida.setSeparator(" ");
		try {
			r.Info = ControladorPersistenciaPartida.carregaPartidesAcabadesMida());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
