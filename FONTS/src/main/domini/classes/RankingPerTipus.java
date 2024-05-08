package main.domini.classes;

import java.util.*;

public class RankingPerTipus extends Ranking{
	private int nEntrades; //Nombre d'entrades
	private static ArrayList<pack> tempsJugador; //ArrayList de pack<Jugador,Temps>

	
	public class CustomComparator implements Comparator<pack>{ //Comparador per ordenar de major a menor (per invertir l'ordre invertir el compare)
		public int compare(pack t1,pack t2){
			return t1.getTemps().compareTo(t2.getTemps());
		}
	}
	public RankingPerTipus (String d, int nEntrades) {
		RankingPerTipus.tempsJugador = new ArrayList<pack>();
		this.nEntrades=nEntrades;
		CTRLRanking.carregar(this, "Partides");
		for (int j=0; j<Info.size(); j++){ //Per cada linia d'info
			ArrayList<String> s = Info.get(j);
			if (s.get(2).equals(d)){ //Selecciona la linia de la dificultat seleccionada per l'usuari
				String user = new String(s.get(0)); //Agafa l'user
				double time = Double.parseDouble(s.get(3));
				String id = new String(s.get(1));
				pack aux = new pack();
				aux.setTemps(time);
				aux.setUser(user); //Ho coloquem a la pack
				aux.setId(id);
				RankingPerTipus.tempsJugador.add(aux); //Ho afegim a ArrayList
			}
		Collections.sort(tempsJugador, new CustomComparator()); //Ordenem ArrayList
		}
	}
	
	public void setnEntrades(int nEntrades) {
		this.nEntrades = nEntrades;
	}
	
	public int getnEntrades() {
		return nEntrades;
	}

	public ArrayList<pack> getTempsJugador() {
		return tempsJugador;
	}	
}