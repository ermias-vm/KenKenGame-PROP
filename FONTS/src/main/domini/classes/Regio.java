package main.domini.classes;

import main.domini.excepcions.*;
import main.domini.interficies.Operacio;
import java.util.ArrayList;


public class Regio {

    private int tamany;
	private Operacio operacio;
	private int resultat;
	private ArrayList<Casella> caselles;

	public Regio(int tam, Operacio operacio, int resultat) {
		try {
			if (tam < 1) throw new ExcepcionTamanyIncorrecte();

			tamany = tam;
			this.operacio = operacio;
			this.resultat = resultat;
			this.caselles = new ArrayList<Casella>();

		} catch (ExcepcionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
		}
	}


	public Regio(ArrayList<Casella> vCaselles, Operacio operacio, int resultat) {
		try {
			if (vCaselles.isEmpty()) throw new ExcepcionTamanyIncorrecte();

			this.caselles = vCaselles;
			this.tamany = vCaselles.size();
			this.operacio = operacio;
			this.resultat = resultat;

		} catch (ExcepcionTamanyIncorrecte e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Casella> getCaselles() {
		return this.caselles;
	}

    public int getTamany() {
        return caselles.size();
    }
     
    public int getNumcasellesPlenas() { 
        int numCasellesPlenas = tamany;
        for (int i = 0; i < tamany; ++i) {
            if (caselles.get(i).esBuida()) numCasellesPlenas--;
        }
        return numCasellesPlenas;
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

	/**
	 * Retorna l'operació de la regió.
	 *
	 * @return Operació de la regió.
	 */
	public Operacio getOperacio() {
		return this.operacio;
	}

	/**
	 * Estableix l'operació de la regió.
	 *
	 * @param operacio  Operació a establir.
	 */
	public void setOperacio(Operacio operacio) {
		this.operacio = operacio;
	}

	/**
	 * Retorna el resultat de la regió.
	 *
	 * @return Resultat de la regió.
	 */
	public int getResultat() {
		return this.resultat;
	}

	/**
	 * Estableix el resultat de la regió.
	 *
	 * @param resultat  Resultat a establir.
	 */
	public void seResultat(int resultat) {
		this.resultat = resultat;
	}

	/**
	 * Retorna els valors de les caselles de la regió.
	 *
	 * @return Array d'enters amb els valors de les caselles.
	 */
	public int[] getValorsCaselles() {
		ArrayList<Casella> caselles = getCaselles();
		int[] valors = new int[caselles.size()];

		for (int i = 0; i < caselles.size(); i++) {
			valors[i] = caselles.get(i).getValor();
		}
		return valors;
	}

	/**
	 * Retorna les posicions de les caselles de la regió.
	 *
	 * @return Array d'enters amb les posicions de les caselles.
	 */
	public int[][] getPosicionsCaselles() {
		int[][] posicions = new int[tamany][2];
		for (int i = 0; i < tamany; i++) {
			posicions[i][0] = caselles.get(i).getPosX();
			posicions[i][1] = caselles.get(i).getPosY();
		}
		return posicions;
	}

	/**
	 * Comprova si la regió està completa.
	 *
	 * @return true si la regió està completa, false en cas contrari.
	 */
	public boolean esCompleta() {
		ArrayList<Casella> caselles = getCaselles();
		for (Casella casella : caselles) {
			if (casella.esBuida()) {
				return false;
			}
		}
		return true;
	}


	public boolean esValida(int[] valors ) throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid {
		if (valors == null) {
			valors = getValorsCaselles();
		}
		if (!esCompleta()) return false;
		//no operacio
		if (getTamany() == 1) return this.resultat == valors[0];

		//operacions on:  2 >= tamanyRegio <= N : suma , multiplicacio
		if (operacio.esCommutativa()) {
			int result = operacio.operaN(valors);
			return result == this.resultat;
		}
		//operacions on: tamanyRegio = 2 : resta, divisió, mòdul, exponenciació
		int result1 = this.operacio.opera2(valors[0], valors[1]);
		int result2 = this.operacio.opera2(valors[1], valors[0]);
		return resultat == result1 || resultat == result2;
	}
	
}
