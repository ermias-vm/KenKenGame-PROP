package main.domini.classes;
import main.domini.excepcions.*;
 

import java.util.Vector;


public class Regio {

    private int tamany;
    private Vector<Casella> vectorCasellas;
	private boolean escorrecte;

     
    public Regio (int tam) {
    	try {
			if (tam < 1) throw new ExcepcionTamanyIncorrecte();
			tamany = tam;
			vectorCellas = new Vector<Casella>(tam);			
		} catch (ExceptionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
			
			
		}
    }
     
    public Regio (Vector<Casella> vc) {
    	try {
			if (vc.size() < 1) throw new ExcepcionTamanyIncorrecte();
			tamany = v.size();
			vectorCellas = vc;
		} catch (ExcepcionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
		}
    }
     
    public int getNumCasellas() {
        return tamany;
    }
     
    public int getNumCasellasPlenas() { 
        int NumCasellasPlenas = tamany;
        for (int i = 0; i < tamany; ++i) {
            if (vectorCasellas.get(i).esBuida()) NumCasellasPlenas--;
        }
        return NumCasellasPlenas;
    }
     
    public boolean esBuida(int pos) {
    	try {
			if (pos < 0 || pos >= tamany) throw new ExcepcionPosicioIncorrecta();
			return (vectorCellas.get(pos).esBuida());
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
     
    public Casella getCasella(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw new ExcepcionPosicioIncorrecta();
			return (vectorCasellas.get(pos));
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return new Casella();
		}
    }
     
    public int getValor(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw new ExcepcionPosicioIncorrecta();
			return (vectorCasellas.get(pos).getValor());
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return -1;
		}
    }
     
    public void setValor(int pos, int val) {
    	try {
			if (pos < 0 || pos >= tam) throw new ExcepcionPosicioIncorrecta();
			if (val < 1 || val > tam) throw new ExcepcionPosicioIncorrecta();
			vectorCasellas.get(pos).setValor(val);
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		
    }
     
    public void borra(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicioIncorrecta());
			vectorCasellas.get(pos).borra();
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		}
    }

	public bool esCorrecte() {
		escorrecte = true;

		for (int i = 0; i < tamany; ++i) {
			if (vectorCasellas.get(i).esModificable()) escorrecte = false;
		}
		return escorrecte;
	}
     
    
}
