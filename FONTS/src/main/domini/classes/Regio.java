package main.domini.classes;

import main.domini.excepcions.*;
import main.domini.interficies.Operacio;
import java.util.ArrayList;


/**
 * Classe Regio que representa una regió d'un tauler Kenken.
 * Una regió està formada per un conjunt de caselles, una operació i un resultat.
 * La mida de la regió és el nombre de caselles que la formen.
 *
 * @author Ermias Valls Mayor
 */

public class Regio {
	/**
	 * Mida de la regió. Indica el nombre de caselles que la formen.
	 */
	private int mida;

	/**
	 * Operació que s'ha de realitzar amb els valors de les caselles de la regió per obtenir el resultat.
	 * La operacio pot der de : Suma, Resta, Multiplicacio, Divisio, Modul, Exponenciacio.
	 * @see main.domini.interficies.Operacio
	 */
	private Operacio operacio;

	/**
	 * Resultat que s'ha d'obtenir en realitzar l'operació especificada amb els valors de les caselles de la regió.
	 */
	private int resultat;

	/**
	 * Llista de caselles que formen la regió.
	 */
	private ArrayList<Casella> caselles;

	/**
	 * Constructor de la classe Regio.
	 * Crea una nova regió amb una mida, operació i resultat especificats.
	 * @param tam  Mida de la regió.
	 * @param operacio  Operació de la regió.
	 * @param resultat  Resultat de la regió.
	 */
	public Regio(int tam, Operacio operacio, int resultat) throws ExcepcioMidaIncorrecte {
			if (tam < 1) throw new ExcepcioMidaIncorrecte();
			mida = tam;
			this.operacio = operacio;
			this.resultat = resultat;
			this.caselles = new ArrayList<Casella>();
	}

	/**
	 * Constructor de la classe Regio.
	 * Crea una nova regió amb un conjunt de caselles, operació i resultat especificats.
	 * @param vCaselles  Conjunt de caselles de la regió.
	 * @param operacio  Operació de la regió.
	 * @param resultat  Resultat de la regió.
	 */
	public Regio(ArrayList<Casella> vCaselles, Operacio operacio, int resultat) throws ExcepcioMidaIncorrecte {
			if (vCaselles.isEmpty()) throw new ExcepcioMidaIncorrecte();
			this.caselles = vCaselles;
			this.mida = vCaselles.size();
			this.operacio = operacio;
			this.resultat = resultat;
	}

	public Regio(Regio r) {
		this.mida = r.getMida();
		this.operacio = r.getOperacio();
		this.resultat = r.getResultat();
		this.caselles = new ArrayList<Casella>();
		for (Casella c : r.getCaselles()) {
			this.caselles.add(new Casella(c));
		}
	}

	/**
	 * Retorna les caselles de la regió.
	 * @return Conjunt de caselles de la regió.
	 */
	public ArrayList<Casella> getCaselles() {
		return this.caselles;
	}

	/**
	 * Retorna la mida de la regió
	 * @return Mida de la regió.
	 */
    public int getMida() {
        return mida;
    }

	/**
	 * Comprova si una casella de la regió està buida.
	 * @param pos  Posició de la casella a comprovar.
	 * @return true si la casella està buida, false en cas contrari.
	 */
    public Casella getCasella(int pos) throws ExcepcionPosicioIncorrecta {
			if (pos < 0 || pos >= mida) throw new ExcepcionPosicioIncorrecta();
			return (caselles.get(pos));
    }

	/**
	 * Retorna el valor d'una casella de la regió.
	 * @param pos  Posició de la casella.
	 * @return Valor de la casella.
	 */
    public int getValor(int pos) throws ExcepcionPosicioIncorrecta {
			if (pos < 0 || pos >= mida) throw new ExcepcionPosicioIncorrecta();
			return (caselles.get(pos).getValor());
    }

	/**
	 * Estableix el valor d'una casella de la regió.
	 * @param pos  Posició de la casella.
	 * @param val  Valor a establir.
	 */
    public void setValor(int pos, int val) throws ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
			if (pos < 0 || pos >= mida) throw new ExcepcionPosicioIncorrecta();
			caselles.get(pos).setValor(val);
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
	public void setResultat(int resultat) {
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
		int[][] posicions = new int[mida][2];
		for (int i = 0; i < mida; i++) {
			posicions[i][0] = caselles.get(i).getPosX();
			posicions[i][1] = caselles.get(i).getPosY();
		}
		return posicions;
	}

	/**
	 * Afegeix una casella a la regió.
	 * @param c  Casella a afegir.
	 */
	public void afegirCasella(Casella c) {
		caselles.add(c);
	}

	/**
	 * Afegeix una llista de caselles a la regió.
	 * @param c  Llista de caselles a afegir.
	 */
	public void afegirCaselles(ArrayList<Casella> c) {
		caselles.addAll(c);
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

	/**
	 * Comprova si la regió és vàlida segons els valors proporcionats.
	 *
	 * @param valors  Array d'enters que representen els valors de les caselles de la regió.
	 * @return Retorna true si la regió és vàlida, false en cas contrari.
	 * @throws ExcepcioNoDivisor  Si es produeix un error de divisió per zero.
	 * @throws ExcepcioMoltsValors  Si es proporcionen més valors dels que pot contenir la regió.
	 * @throws ExcepcioDivisio_0  Si es produeix una divisió per zero.
	 * @throws ExcepcioValorInvalid  Si es proporciona un valor invàlid.
	 *
	 * Aquesta funció primer comprova si els valors proporcionats són nuls, en aquest cas, obté els valors de les caselles de la regió.
	 * Després, comprova si la regió està completa, si no ho està, retorna false.
	 * Si la mida de la regió és 1, comprova si el resultat és igual al valor de la casella.
	 * Si l'operació és commutativa (suma o multiplicació), es realitza l'operació amb tots els valors i es compara amb el resultat esperat.
	 * Si l'operació no és commutativa (resta, divisió, mòdul, exponenciació), es realitza l'operació amb els dos primers valors en ambdues direccions i es compara amb el resultat esperat.
	 */
	public boolean esValida(int[] valors ) throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid {
		if (valors == null) {
			valors = getValorsCaselles();
		}

		if (!esCompleta()) return false;
		if (!esCompletaValors(valors)) return false;
		//no operacio
		if (getMida() == 1) return this.resultat == valors[0];

		//operacions on:  2 >= midaRegio <= N : suma , multiplicacio
		if (operacio.esCommutativa()) {
			int result = operacio.operaN(valors);
			return result == this.resultat;
		}
		//operacions on: midaRegio = 2 : resta, divisió, mòdul, exponenciació
		int result1 = this.operacio.opera2(valors[0], valors[1]);
		int result2 = this.operacio.opera2(valors[1], valors[0]);
		return resultat == result1 || resultat == result2;
	}

	public boolean esCompletaValors(int[] valors) {
		for (int i = 0; i < mida; i++) {
			if (valors[i] == 0) return false;
		}
		return true;
	}
	/**
	 * Comprova si la regió és vàlida segons els valors proporcionats.
	 *
	 * @param valors  Array d'enters que representen els valors de les caselles de la regió.
	 * @return Retorna true si la regió és vàlida, false en cas contrari.
	 * @throws ExcepcioNoDivisor  Si es produeix un error de divisió per zero.
	 * @throws ExcepcioMoltsValors  Si es proporcionen més valors dels que pot contenir la regió.
	 * @throws ExcepcioDivisio_0  Si es produeix una divisió per zero.
	 * @throws ExcepcioValorInvalid  Si es proporciona un valor invàlid.
	 *
	 * Aquesta funció primer comprova si els valors proporcionats són nuls, en aquest cas, obté els valors de les caselles de la regió.
	 * Després, comprova si la regió està completa, si no ho està, retorna false.
	 * Si la mida de la regió és 1, comprova si el resultat és igual al valor de la casella.
	 * Si l'operació és commutativa (suma o multiplicació), es realitza l'operació amb tots els valors i es compara amb el resultat esperat.
	 * Si l'operació no és commutativa (resta, divisió, mòdul, exponenciació), es realitza l'operació amb els dos primers valors en ambdues direccions i es compara amb el resultat esperat.
	 */
	public boolean esValidaValors(int[] valors ) throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioValorInvalid {
		if (!esCompletaValors(valors)) return false;
		//no operacio
		if (getMida() == 1) return this.resultat == valors[0];

		//operacions on:  2 >= midaRegio <= N : suma , multiplicacio
		if (operacio.esCommutativa()) {
			int result = operacio.operaN(valors);
			return result == this.resultat;
		}
		//operacions on: midaRegio = 2 : resta, divisió, mòdul, exponenciació
		int result1 = this.operacio.opera2(valors[0], valors[1]);
		int result2 = this.operacio.opera2(valors[1], valors[0]);
		return resultat == result1 || resultat == result2;
	}

}
