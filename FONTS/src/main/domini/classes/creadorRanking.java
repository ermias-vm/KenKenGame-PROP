package main.domini.classes;

import main.domini.controladors.*;
import main.persistencia.*;

public class creadorRanking {
	
	public creadorRanking() {
		CTRLRanking CR = new CTRLRanking();
	}
	
	
	public RankingPerTipus generarRankingPerTipus (String d, int nEntrades) {
		return new RankingPerTipus(d, nEntrades);
	}
	
	public RankingPersonal generarRankingPersonal(String username) {
		return new RankingPersonal(username);
	}
}
