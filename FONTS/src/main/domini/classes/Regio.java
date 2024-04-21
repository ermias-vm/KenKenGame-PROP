package main.domini.classes;
import main.domini.excepcions.*;
 

import java.util.ArrayList;


public class Regio {

    private int tamany;
	private ArrayList<Casella> caselles;
	

     
    public Regio (int tam) {
    	try {
			if (tam < 1) throw new ExcepcionTamanyIncorrecte();
			tamany = tam;
			caselles = new ArrayList<Casella>();			
		} catch (ExcepcionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
			
			
		}
    }
     
    public Regio (ArrayList<Casella> vc) {
    	try {
			if (vc.size() < 1) throw new ExcepcionTamanyIncorrecte();

			tamany = vc.size();
			caselles = vc;
		} catch (ExcepcionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
		}
    }

	public ArrayList<Casella> getCaselles() {
		return this.caselles;
	}

    public int getTamany() {
        return tamany;
    }
     
    public int getNumcasellesPlenas() { 
        int NumcasellesPlenas = tamany;
        for (int i = 0; i < tamany; ++i) {
            if (caselles.get(i).esBuida()) NumcasellesPlenas--;
        }
        return NumcasellesPlenas;
    }
     
    public boolean esBuida(int pos) {
    	try {
			if (pos < 0 || pos >= tamany) throw new ExcepcionPosicioIncorrecta();
			return (caselles.get(pos).esBuida());
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
     
    public Casella getCasella(int pos) {
    	try {
			if (pos < 0 || pos >= tamany) throw new ExcepcionPosicioIncorrecta();
			return (caselles.get(pos));
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
     
    public int getValor(int pos) {
    	try {
			if (pos < 0 || pos >= tamany) throw new ExcepcionPosicioIncorrecta();
			return (caselles.get(pos).getValor());
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return -1;
		}
    }
     
    public void setValor(int pos, int val) {
    	try {
			if (pos < 0 || pos >= tamany) throw new ExcepcionPosicioIncorrecta();
			if (val < 1 || val > tamany) throw new ExcepcionPosicioIncorrecta();
			caselles.get(pos).setValor(val);
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		}
		
    }
     
    public void borra(int pos) {
    	try {
			if (pos < 0 || pos >= caselles.size()) throw (new ExcepcionPosicioIncorrecta());
			caselles.get(pos).borrarValor();
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		}
    }
    
}
