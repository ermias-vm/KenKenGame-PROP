package main.domini.classes;

import java.util.*;

public class RankingPersonal extends Ranking {
	private String usuari;
	private int jocsResolts;
	private double avgPistes;
	private Map<String,Double> bestTime;
	
	private void inicialitza() {
		this.avgPistes = 0.0;
		this.jocsResolts = 0;
		this.bestTime = new HashMap<String,Double>();
		for (int i=0; i<Info.size(); ++i) {
			ArrayList<String> s = Info.get(i);
			String user = new String(s.get(0));
			String dif = (Dificultat.esValida(s.get(2))) ? s.get(2) : null;
			double time = Double.parseDouble(s.get(3));
			int pistes = Integer.parseInt(s.get(4));
			if (usuari.equals(user)) {
				++jocsResolts;
				avgPistes += pistes;
				if (dif != null) {
					if (bestTime.containsKey(dif)) {
						if (bestTime.get(dif) > time) bestTime.put(dif,time);
					}
					else bestTime.put(dif,time);
				}
			}
		}
		avgPistes = avgPistes/(double)jocsResolts;
	}
	
	public RankingPersonal(String usuari){
		this.usuari = usuari;
		System.out.println("Carregant informació...");
		CTRLRanking.carregar(this, "Partides");
		System.out.println("Generant rànquing...");
		this.inicialitza();
	}
	
	public String getUsuari() {
		return usuari;
	}
		
	public int getResolts() {
		return jocsResolts;
	}
	
	public double getPistes() {
		return avgPistes;
	}

	public Map<String,Double> getBestTime() {
		return bestTime;
	}
	
	public Double getBestTime(String dif) {
		return bestTime.get(dif);
	}
}

