package main.domini.classes;

import java.util.*;

import main.domini.controladors.CtrlRanking;
import main.domini.classes.Ranking;

public class RankingIndividual extends Ranking{
    
  
	
	public RankingIndividual (String d) {
        tempstotals = new ArrayList<ArrayList<Pack>>();
        CtrlRanking ctrlRanking = new CtrlRanking();
		ctrlRanking.carregarPartidesAcabadesUsuari(this, d);
        for (int i = 3; i <= 9; ++i) { //Per cada grau
		tempsgrau = new ArrayList<Pack>();
            for (int j=0; j<informacio.size(); j++){ //Per cada linia d'informacio
                if (informacio.get(4).equals(i)){
                    ArrayList<String> s = informacio.get(j);
                    String idusuari = new String(s.get(1)); //Agafa l'idusuari
                    double time = Double.parseDouble(s.get(3));
                    String idtauler = new String(s.get(0));
                    Pack a = new Pack();
                    a.setTemps(time);
                    a.setIduser(idusuari); //Ho coloquem a la Pack	
                    a.setidtauler(idtauler);
                    tempsgrau.add(a); //Ho afegim a ArrayList
                    Collections.sort(tempsgrau, new CustomComparator()); //Ordenem ArrayList
                }
            }    
            tempstotals.add(tempsgrau);
        }
	}

    	
}
