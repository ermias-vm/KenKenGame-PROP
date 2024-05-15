/*
package main.domini.classes;
import main.domini.controladors.CtrlRanking;

import java.util.*;

public class RankingPerMidaMarti extends RankingMarti {
	private int nEntrades; //Nombre d'entrades
	private static ArrayList<PackInfo> tempsJugador; //ArrayList de PackInfo<Jugador,Temps>

	
        public class CustomComparator implements Comparator<PackInfo>{ //Comparador per ordenar de major a menor (per invertir l'ordre invertir el compare)
            public int compare(PackInfo t1,PackInfo t2){
                return t1.getTemps().compareTo(t2.getTemps());
            }
        }

        public RankingPerMidaMarti(int d, int nEntrades) {
            RankingPerMidaMarti.tempsJugador = new ArrayList<PackInfo>();
            this.nEntrades=nEntrades;
            CtrlRanking.carregartipus(this,d);
            for (int j=0; j<Informacio.size(); j++){ //Per cada linia d'info
                ArrayList<String> s = Informacio.get(j);
                String user = new String(s.get(1)); //Agafa l'user
                double time = Double.parseDouble(s.get(3));
                String id = new String(s.get(2));
                PackInfo aux = new PackInfo();
                aux.setTemps(time);
                aux.setUser(user); //Ho coloquem a la PackInfo
                aux.setId(id);
                RankingPerMidaMarti.tempsJugador.add(aux); //Ho afegim a ArrayList
            }
            Collections.sort(tempsJugador, new CustomComparator()); //Ordenem ArrayList
        }

        public void setnEntrades(int nEntrades) {
            this.nEntrades = nEntrades;
        }

        public int getnEntrades() {
            return nEntrades;
        }

        public ArrayList<PackInfo> getTempsJugador() {
            return tempsJugador;
        }
}

*/
