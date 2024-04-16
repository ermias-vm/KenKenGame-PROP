package main.domini.classes;
import main.domini.excepcions.*;
 
public class peca {

    private int tamany;
    private Vector<Casella> vectorCasellas;
     
    Regio (int tamano) {
    	try {
			if (tamano < 1) throw (new );
			tamany = tamano;
			vCellas = new Vector<Cella>();
			Cella cellaAux = null;
			int b;
			for (int i = 0; i < tam; ++i) {
			    cellaAux = new Cella();
			    vCellas.addElement(cellaAux);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			
		}
    }
     
    Regio (int tamano, Vector<Cella> vc) {
    	try {
			if (tamano < 1) throw (new );
			tam = tamano;
			if (vc.size() != tam) throw (new );
			vCellas = (Vector<Cella>) vc;
		} catch (ExcepcionTamanoIncorrecto e) {
			System.out.println(e.getMessage());
		} catch (ExcepcionNumCeldasDiferenteTamano e) {
			System.out.println(e.getMessage());
		}
    }
     
    public int getNumCeldas() {
        return tam;
    }
     
    public int getNumCeldasRellenas() { //lo calcula cada vez
        int numCeldasRellenas = tam;
        for (int i = 0; i < tam; ++i) {
            if (vCellas.get(i).estaVacia()) numCeldasRellenas--;
        }
        return numCeldasRellenas;
    }
     
    public boolean estaVacia(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicionFueraRango());
			return (vCellas.get(pos).estaVacia());
		} catch (ExcepcionPosicionFueraRango e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
     
    public Cella getCella(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicionFueraRango());
			return (vCellas.get(pos));
		} catch (ExcepcionPosicionFueraRango e) {
			System.out.println(e.getMessage());
			return new Cella();
		}
    }
     
    public int getNumero(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicionFueraRango());
			return (vCellas.get(pos).getNumero());
		} catch (ExcepcionPosicionFueraRango e) {
			System.out.println(e.getMessage());
			return -1;
		}
    }
     
    public void setNumero(int pos, int val) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicionFueraRango());
			if (val < 1 || val > tam) throw (new ExcepcionValorFueraRango());
			vCellas.get(pos).setNumero(val);
		} catch (ExcepcionPosicionFueraRango e) {
			System.out.println(e.getMessage());
		} catch (ExcepcionValorFueraRango e) {
			System.out.println(e.getMessage());
		}
    }
     
    public void borra(int pos) {
    	try {
			if (pos < 0 || pos >= tam) throw (new ExcepcionPosicionFueraRango());
			vCellas.get(pos).borra();
		} catch (ExcepcionPosicionFueraRango e) {
			System.out.println(e.getMessage());
		}
    }
     
    
}
