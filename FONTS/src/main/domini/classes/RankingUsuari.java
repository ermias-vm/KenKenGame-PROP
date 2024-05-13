package main.domini.classes;
import main.domini.excepcions.*;
import main.domini.controladors.CtrlRanking;
import java.util.*;

public class RankingUsuari extends Ranking {
	private String usuari;
	private int jocsResolts;
	private Map<String,Double> bestTime;
	
	private void inicialitza() {
		this.jocsResolts = 0;
		this.bestTime = new HashMap<String,Double>();
		for (int i=0; i<Informacio.size(); ++i) {
			ArrayList<String> s = Informacio.get(i);
			String user = new String(s.get(1));
			String dif = new String(s.get(4));
			double time = Double.parseDouble(s.get(3));
			if (usuari.equals(user)) {
				++jocsResolts;
				
				if (dif != null) {
					if (bestTime.containsKey(dif)) {
						if (bestTime.get(dif) > time) bestTime.put(dif,time);
					}
					else bestTime.put(dif,time);
				}
			}
		}
	}
	
	public RankingUsuari(String usuari){
		this.usuari = usuari;
		System.out.println("Carregant informació...");
		CtrlRanking.carregarpersonal(this,usuari);
		System.out.println("Generant rànquing...");
		this.inicialitza();
	}
	
	public String getUsuari() {
		return usuari;
	}
		
	public int getResolts() {
		return jocsResolts;
	}
	

	public Map<String,Double> getBestTime() {
		return bestTime;
	}
	
	public Double getBestTime(String dif) {
		return bestTime.get(dif);
	}
}
