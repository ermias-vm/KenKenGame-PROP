package main.domini.classes;

import main.domini.controladors.*;
import main.persistencia.CtrlPersistencia;

public class RankingFactory {
	
	public RankingFactory() {
		CtrlPersistencia.setSeparator(" ");
		CtrlUser CU = new CtrlUser();
	}
	
	
	public RankingPerTipus generarRankingPerTipus (String d, int nEntrades) {
		return new RankingPerTipus(d, nEntrades);
	}
	
	public RankingPersonal generarRankingPersonal(String username) {
		return new RankingPersonal(username);
	}
}
