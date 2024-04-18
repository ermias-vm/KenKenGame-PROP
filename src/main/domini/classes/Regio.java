package main.domini.classes;
import main.domini.excepcions.*;
 

import java.util.ArrayList;


public class Regio {

    private int tamany;
    protected ArrayList<Casella> casellas;
	

     
    public Regio (int tam) {
    	try {
			if (tam < 1) throw new ExcepcionTamanyIncorrecte();
			tamany = tam;
			casellas = new ArrayList<Casella>();			
		} catch (ExceptionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
			
			
		}
    }
     
    public Regio (int tam, ArrayList<Casella> vc) {
    	try {
			if (vc.size() < 1) throw new ExcepcionTamanyIncorrecte();
			if (tam != vc.size()) throw new ExcepcionTamanyIncorrecte();

			tamany = vc.size();
			casellas = vc;
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
            if (casellas.get(i).esBuida()) NumCasellasPlenas--;
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
			return (casellas.get(pos));
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return new Casella();
		}
    }
     
    public int getValor(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw new ExcepcionPosicioIncorrecta();
			return (casellas.get(pos).getValor());
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
			return -1;
		}
    }
     
    public void setValor(int pos, int val) {
    	try {
			if (pos < 0 || pos >= tam) throw new ExcepcionPosicioIncorrecta();
			if (val < 1 || val > tam) throw new ExcepcionPosicioIncorrecta();
			casellas.get(pos).setValor(val);
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		
    }
     
    public void borra(int pos) {
    	try {
			if (pos < 0 || pos >= casellas.size) throw (new ExcepcionPosicioIncorrecta());
			casellas.get(pos).borra();
		} catch (ExcepcionPosicioIncorrecta e) {
			System.out.println(e.getMessage());
		}
    }

	

	
    
}
