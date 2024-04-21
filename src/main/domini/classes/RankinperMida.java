package main.domini.classes;

import main.domini.controladors.CtrlRanking;

import main.persistencia.ControladorPersistenciaPartida;
import java.util.*;

public class Ranking {
	public ArrayList<ArrayList<String>> informacio;
	protected  ArrayList<Pack> tempsgrau; //ArrayList de Pack<Jugador,Temps>
    protected  ArrayList<ArrayList<Pack>> tempstotals;
	
	public class Pack { //Pack d'Usuari + Temps per a RankingPerTipus
	private String idusuari;
	private Double temps;
	private String idtauler;
	
	public Pack(){
    
    }   
	
	public Pack(String idusuari, Double temps, String idtauler){ 
		this.idusuari=idusuari;
		this.temps=temps;
		this.idtauler=idtauler;
	}
	
	public String getIdusuari() {
		return idusuari;
	}
	public void setIduser(String idusuari) {
		this.idusuari = idusuari;
	}
	public Double getTemps() {
		return temps;
	}
	public void setTemps(Double temps) {
		this.temps = temps;
	}
	public String getidtauler(){
		return idtauler;
	}
	public void setidtauler(String idtauler){
		this.idtauler = idtauler;
	}
	
}

	protected Ranking() {
		informacio = new ArrayList<ArrayList<String>>();
	}
	public void setInfo(ArrayList<ArrayList<String>> informacio) {
        this.informacio = informacio;
    }

	public class CustomComparator implements Comparator<Pack>{ //Comparador per ordenar de major a menor (per invertir l'ordre invertir el compare)
		public int compare(Pack t1,Pack t2){
			return t1.getTemps().compareTo(t2.getTemps());
		}
	}
	
	

	public ArrayList<ArrayList<Pack>> getTempsTotals() {
		return tempstotals;
	}


}
