package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Classe TaulerConstrutor que estén JPanel.
 * Representa un tauler de joc per a la construcció manual de Kenken.
 * Implementa els metodes necesaris per a la construcio manual i validació del Tauler de Kenken.
 * Aquesta classe utilitza la classe CasellaConstructora per a la creació del tauler.
 *
 * @author Ermias Valls Mayor
 */
public class TaulerConstrutor extends JPanel {

    /**
     * Constants per a les operacions i modes.
     * SUM representa l'operació de suma.
     * MULT representa l'operació de multiplicació.
     * CREIXENTMENT representa mode obtenció valors creixentment.
     * DECREIXENTMENT representa mode obtenció decreixentment.
     */
    private static final int SUM = 0;
    private static final int MULT = 1;
    private static final int CREIXENTMENT = 0;
    private static final int DECREIXENTMENT = 1;

    /**
     * Instància única de TaulerConstrutor (Singleton).
     * Aquesta instància es crea una sola vegada i es reutilitza en tot el programa.
     */
    private static TaulerConstrutor taulerConstrutor;

    /**
     * Mida del tauler.
     * Aquest valor representa el nombre de files i columnes del tauler.
     */
    private final int mida;

    /**
     * Número de caselles assignades.
     * Aquest valor representa el nombre total de caselles que han estat assignades a una regió.
     */
    private int numCasellesAssignades;

    /**
     * Matriu de caselles.
     * Aquesta matriu conté les caselles que formen el tauler.
     */
    private CasellaConstructora[][] caselles;

    /**
     * Llista de dades de les regions.
     * Aquesta llista conté les dades de cada regió del tauler, incloent l'operació i el resultat.
     */
    private ArrayList<String> dadesRegions = new ArrayList<>();

    /**
     * Llista de posicions de les caselles seleccionades.
     * Aquesta llista conté les posicions de les caselles que l'usuari ha seleccionat per a formar una regió.
     */
    private ArrayList<int[]> posCasellesSeleccionades = new ArrayList<>();

    /**
     * Matriu de booleans que indica si un valor ha estat usat en una fila.
     * Aquesta matriu es fa servir per a comprovar si un valor ja ha estat usat en una fila específica quan es calcula el resultat mínim o màxim d'una operació.
     */
    private boolean[][] valorsUsatsFila;

    /**
     * Constructor privat de TaulerConstrutor.
     * Inicialitza el tauler amb una mida específica.
     *
     * @param mida La mida del tauler.
     */
    private  TaulerConstrutor(int mida) {
        super(new GridLayout(mida,mida));
        this.mida = mida;
        this.numCasellesAssignades = 0;
        inicialitzarCaselles(mida);

    }

    /**
     * Retorna la instància única de TaulerConstrutor.
     *
     * @return La instància de TaulerConstrutor.
     */
    public static TaulerConstrutor getInstance() {
        return taulerConstrutor;
    }

    /**
     * Crea una nova instància de TaulerConstrutor amb una mida específica.
     *
     * @param mida La mida del nou tauler.
     */
    public static void newInstance(int mida) {
            taulerConstrutor = new TaulerConstrutor(mida);
    }

    /**
     * Inicialitza les caselles del tauler.
     *
     * @param mida La mida del tauler.
     */
    private void inicialitzarCaselles(int mida) {
        caselles = new CasellaConstructora[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                CasellaConstructora casella = new CasellaConstructora(i,j);
                caselles[i][j] = casella;
                this.add(casella);
            }
        }
    }

    /**
     * Retorna la mida del tauler.
     *
     * @return La mida del tauler.
     */
    public int getMida() {
        return mida;
    }

    /**
     * Retorna el número de caselles seleccionades.
     *
     * @return El número de caselles seleccionades.
     */
    public int getNumCasellesAssignades() {
        return numCasellesAssignades;
    }

    /**
     * Retorna el número de caselles seleccionades.
     *
     * @return El número de caselles seleccionades.
     */
    public int getNumCasellesSelecionades() {
        return posCasellesSeleccionades.size();
    }

    /**
     * Retorna el nombre de files selecionades.
     *
     * @return El nombre de files que contenen caselles seleccionades.
     */
    public int getNumFilesSelecionades() {
        ordenarPosicionsCaselles();
        int numFilesSelecionades = 0;
        int comparador = -1;

        for (int[] pos : posCasellesSeleccionades) {
            if (pos[0] > comparador) {
                ++numFilesSelecionades;
                comparador = pos[0];
            }
        }
        return numFilesSelecionades;
    }

    /**
     * Indica si el tauler ha estat modificat.
     *
     * @return True si el tauler ha estat modificat, false en cas contrari.
     */
    public boolean esModificat() {
        return (numCasellesAssignades > 0 || getNumCasellesSelecionades() > 0);
    }

    /**
     * Afegeix la posició d'una casella seleccionada a la llista de posicions de caselles selecionades.
     *
     * @param x La coordenada x de la casella.
     * @param y La coordenada y de la casella.
     */
    public void afegirPosCasellaSelecionada (int x, int y) {
        posCasellesSeleccionades.add(new int[]{x, y});
    }

    /**
     * Elimina la posició d'una casella selecionada de la llista de posicions de caselles selecionades.
     *
     * @param x La coordenada x de la casella.
     * @param y La coordenada y de la casella.
     */
    public void eliminarPosCasellaSelecionada (int x, int y) {
        for (int i = 0; i < posCasellesSeleccionades.size(); i++) {
            if (posCasellesSeleccionades.get(i)[0] == x && posCasellesSeleccionades.get(i)[1] == y) {
                posCasellesSeleccionades.remove(i);
                break;
            }
        }
    }

    /**
     * Assigna les caselles seleccionades a una regió.
     * Crea una cadena amb la informació de la regió (operació, resultat, mida de la regió i posicions de les caselles) i l'afegeix a la llista de dades de les regions.
     * Marca cada casella seleccionada com a part de la regió.
     * Afegeix la informació de la regió a la primera casella de la regió (casella mes amunt i a l'esquerra).
     * Marca les vores de la regió.
     * Incrementa el número de caselles assignades amb la mida de la regió.
     * Finalment, esborra la llista de caselles seleccionades.
     *
     * @param operacio L'operació de la regió.
     * @param resultat El resultat de la regió.
     */
    public void assignarCasellesRegio(String operacio, String resultat) {
        StringBuilder infoRegio = new StringBuilder();
        infoRegio.append(traduirOperacioAnum(operacio)).append(" ");
        infoRegio.append(resultat).append(" ");
        infoRegio.append(posCasellesSeleccionades.size());

        ordenarPosicionsCaselles();

        int[] primeraPos = posCasellesSeleccionades.get(0);

        for (int[] pos : posCasellesSeleccionades) {
            CasellaConstructora casella = caselles[pos[0]][pos[1]];
            casella.marcaComRegio(dadesRegions.size());
            infoRegio.append(" ").append(pos[0]+1).append(" ").append(pos[1]+1);
        }

        CasellaConstructora primeraCasella = caselles[primeraPos[0]][primeraPos[1]];
        primeraCasella.addInfoRegio(resultat ,traduirOperacioAsimbol(operacio), mida);
        marcarBordesRegio();
        dadesRegions.add(infoRegio.toString().trim());
        numCasellesAssignades += posCasellesSeleccionades.size();
        posCasellesSeleccionades.clear();
    }

    /**
     * Elimina una regió assignada.
     * Imprimeix les dades de les regions, obté la informació de la regió a eliminar i la divideix en parts.
     * Actualitza l'índex de les regions i desmarca les caselles de la regió eliminada.
     * Elimina la informació de la regió de la primera casella de la regió.
     * Decrementa el número de caselles assignades amb la mida de la regió.
     * Elimina la informació de la regió de la llista de dades de les regions.
     * Finalment, imprimeix les dades de les regions.
     *
     * @param index L'índex de la regió a eliminar.
     */
    public void eliminarRegioAssignada(int index) {
        String infoRegio = dadesRegions.get(index);
        String[] parts = infoRegio.split(" ");

        //Actualitzar index de les regions i desmarca les caselles de la regio  eliminada
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                if (caselles[i][j].getIndexRegio() == index) {
                    caselles[i][j].desmarcaComRegio();
                }
                else if (caselles[i][j].getIndexRegio() > index) {
                    caselles[i][j].decrementaIndexRegio();
                }
            }
        }
        //Eliminar label de la informacio de la regio (resultat,operacio) que te la  primer casella
        int x = Integer.parseInt(parts[3]) - 1;
        int y = Integer.parseInt(parts[4]) - 1;
        caselles[x][y].eliminarInfoRegio();

        int numCaselles = Integer.parseInt(parts[2]);
        numCasellesAssignades -= numCaselles;
        dadesRegions.remove(index);
    }

    /**
     * Comprova si una posició és adjacent a alguna de les caselles seleccionades.
     * Si la llista de caselles seleccionades està buida, retorna true.
     * En cas contrari, comprova si la posició està a la vora de alguna de les caselles seleccionades.
     * Retorna true si la posició és adjacent a alguna casella seleccionada, altrament retorna false.
     *
     * @param posX La coordenada x de la posició.
     * @param posY La coordenada y de la posició.
     * @return True si la posició és adjacent a alguna casella seleccionada, false en cas contrari.
     */
    public boolean esAdjacent(int posX, int posY) {
        if (posCasellesSeleccionades.isEmpty()) return true;
        for (int[] pos : posCasellesSeleccionades) {
            if ((pos[0] == posX && Math.abs(pos[1] - posY) == 1) || (pos[1] == posY && Math.abs(pos[0] - posX) == 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprova si una posició és una "vora regió".
     * Calcula les coordenades del veí de la posició en la direcció especificada per dx  dy.
     * Si el veí està dins del tauler i no està en la regió, retorna true.
     * Retorna false si el veí està en la regió o fora del tauler.
     *
     * @param dx La direcció x per a calcular el veí.
     * @param dy La direcció y per a calcular el veí.
     * @param posX La coordenada x de la posició.
     * @param posY La coordenada y de la posició.
     * @return True si la posició és una "vora regió", false en cas contrari.
     */
    public boolean esVoraRegio(int dx, int dy, int posX, int posY) {
        int veiX = posX + dx;
        int veiY = posY + dy;

        // Verificar que el vei no está en la región
        for (int[] pos : posCasellesSeleccionades) {
            if (pos[0] == veiX && pos[1] == veiY) { //No es vora regio
                return false;
            }
        }
        return true; //Es vora regio o està fora del tauler
    }

    /**
     * Marca les vores de la regió.
     * Per a cada casella seleccionada, calcula si cadadascuna de les seves 4 vores es de la regió i la pinta.
     */
    public void marcarBordesRegio() {
        for (int[] pos : posCasellesSeleccionades) {
            CasellaConstructora casella = caselles[pos[0]][pos[1]];
            boolean[] voresRegio = new boolean[4];
            voresRegio[0] = esVoraRegio(-1, 0, pos[0], pos[1]); // amunt
            voresRegio[1] = esVoraRegio(0, -1, pos[0], pos[1]); // esquerra
            voresRegio[2] = esVoraRegio(+1, 0, pos[0], pos[1]); // abaix
            voresRegio[3] = esVoraRegio(0, +1, pos[0], pos[1]); // dreta
            casella.pintarVores(voresRegio);
        }
    }
    
    /**
     * Retorna el contingut del tauler com a cadena String.
     * Crea una cadena amb el format especific per a crear taulers kenken.
     *
     * @return El contingut del tauler com a cadena String.
     */
    public String getContigutTauler() {
        StringBuilder contingutTauler = new StringBuilder();
        contingutTauler.append(caselles.length).append(" ").append(dadesRegions.size()).append("\n");
        for (String s : dadesRegions) {
            contingutTauler.append(s);
            contingutTauler.append("\n");
        }
        return contingutTauler.toString();
    }

    /**
     * Ordena creixentment les posicions de les caselles seleccionades.
     * Ordena la llista de caselles seleccionades primer per la coordenada x i després per la coordenada y.
     */
    void ordenarPosicionsCaselles() {
        posCasellesSeleccionades.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
    }

    /**
     * Si la llista de caselles seleccionades no està buida, intercanvia la primera i l'última posició.
     */
    void intercambiarPrimeraUltimaPosicion() {
        if (!posCasellesSeleccionades.isEmpty()) {
            int[] primeraPosicion = posCasellesSeleccionades.get(0);
            int[] ultimaPosicion = posCasellesSeleccionades.get(posCasellesSeleccionades.size() - 1);
            posCasellesSeleccionades.set(0, ultimaPosicion);
            posCasellesSeleccionades.set(posCasellesSeleccionades.size() - 1, primeraPosicion);
        }
    }
    
    /**
     * Valida l'entrada de l'operació i el resultat.
     *
     * @param operacio L'operació a validar.
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si l'entrada no és vàlida, altrament retorna null.
     */
    public String validarEntrada(String operacio, String resultat) {

        if (resultat.isEmpty()) {
            return "<html><div style='text-align: center;'>El camp resultat es buit.<br>Si us plau, porporcioni un resultat valid.</div></html>";
        }
        else if (this.getNumCasellesSelecionades() == 1 && !operacio.equals("SUMA")) {
            return "<html><div style='text-align: center;'>Per regions d'una casella, l'unica operacio possible es SUMA." +
                    "<br>Si us plau, selecioni mes caselles o canvia la operacio a SUMA</div></html>";
        }
        else if ((operacio.equals("EXP") || operacio.equals("MOD") || operacio.equals("RESTA") || operacio.equals("DIV")) && this.getNumCasellesSelecionades()!= 2) {
            return "<html><div style='text-align: center;'>Per las operaciones \"RESTA\",\"DIV\", \"EXP\" i \"MOD\" has de selecionar " +
                    "exactament 2 caselles.<br>Si us plau, selecioni nomes dos caselles o canvia la operacio a SUMA o MULT</div></html>";
        }
        else if (!esRegioConectada()) {
            return "<html><div style='text-align: center;'>Les caselles selecionades no formen una regió connectada." +
                    "<br>Si us plau, connecteu les caselles aïllades.</div></html>";
        }
        return validarValorMinMaxOperacio(operacio, Integer.parseInt(resultat));
    }
    
    /**
     * Valida si el resultat esta dins el valor mínim i màxim que es pot obtenir amb l'operació indicada.
     *
     * @param operacio L'operació a validar.
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
     private String validarValorMinMaxOperacio(String operacio, int resultat) {
         ordenarPosicionsCaselles();
         switch (operacio) {
            case "SUMA":
                return validarSuma(resultat);
            case "RESTA":
                return validarResta(resultat);
            case "MULT":
                return validarMultiplicacio(resultat);
            case "DIV":
                return validarDivisio(resultat);
            case "EXP":
                return validarExponenciacio(resultat);
            case "MOD":
                return validarModul(resultat);
        }
        return null;
    }

    /**
     * Comprova si un valor està dins un interval.
     *
     * @param valor El valor a comprovar.
     * @param min El valor mínim de l'interval.
     * @param max El valor màxim de l'interval.
     * @return True si el valor no està dins l'interval, false en cas contrari.
     */
    private boolean resultatEsForaInterval(int valor, int min, int max) {
        return valor < min || valor > max;
    }

    /**
     * Valida el resultat de l'operació de resta.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarResta(int resultat) {
        int min = 1;
        int max = mida-1;

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }
        return null;    //Cas on el resultat es vàlid
    }
    /**
     * Valida el resultat de l'operació de divisió.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarDivisio(int resultat) {
        int min = 2;
        int max = mida;

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }
        return null;    //Cas on el resultat es vàlid
    }

    /**
     * Valida el resultat de l'operació d'exponenciació.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarExponenciacio(int resultat) {
        int min = 2;
        int max;
        if (mida == 3) max = 9;
        else max = (int) Math.pow(mida-1,mida);

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }
        return null;    //Cas on el resultat es vàlid
    }

    /**
     * Valida el resultat de l'operació de mòdul.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarModul(int resultat) {
        int min = 0;        // equivalent,  N%1 = 0;
        int max = mida -1; // equivalent, (mida-1) % mida = mida-1

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }
        return null;    //Cas on el resultat es vàlid
    }

    /**
     * Valida el resultat de l'operació de suma.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarSuma(int resultat) {
        //Cas on hi ha nomes s'ha selecionat  una casella
        if (getNumCasellesSelecionades() == 1 && resultatEsForaInterval(resultat,1,mida)) {
            return "<html><div style='text-align: center;'>El resultat de la suma de 1 " +
                    "casella ha de pertànyer<br>al interval: [1, " + mida + "]</div></html>";
        }

        //Cas especial
        if (mida * getNumFilesSelecionades() == getNumCasellesSelecionades()) {
            intercambiarPrimeraUltimaPosicion();
        }

        int min = calcularResultatMinim(SUM);
        int max = calcularResultatMaxim(SUM);

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }

        return null;    //Cas on el resultat es vàlid
    }

    /**
     * Valida el resultat de l'operació de multiplicació.
     *
     * @param resultat El resultat a validar.
     * @return Un missatge d'error si el resultat no està dins l'interval, altrament retorna null.
     */
    private String validarMultiplicacio(int resultat) {
        if (getNumCasellesSelecionades() > 9) {
            return "<html><div style='text-align: center;'>Per a regions de mida superior a 9 caselles, " +
                    "l'operacio de MULTIPLICACIO no es vàlida.<br>Si us plau, seleccioni menys caselles o canvia  l'operacio a SUMA.</div></html>";
        }
        //Cas especial
        if (mida * getNumFilesSelecionades() == getNumCasellesSelecionades()) {
            intercambiarPrimeraUltimaPosicion();
        }
        int min = calcularResultatMinim(MULT);
        int max = calcularResultatMaxim(MULT);

        if (resultatEsForaInterval(resultat,min,max)) {  //Cas on el resultat es invàlid
            return "<html><div style='text-align: center;'>Resultat invàlid. Per a aquesta regió " +
                    "el resultat ha de pertànyer<br>al interval: [" + min + ", " + max + "]</div></html>";
        }
        return null;    //Cas on el resultat es vàlid
    }

    /**
     * Calcula el resultat mínim que es pot obtenir amb l'operació especificada.
     * Primer, obté una matriu de valors per a les columnes ordenats de manera creixent.
     * Després, utilitza aquesta matriu per calcular el resultat de l'operació.
     *
     * @param operacio L'operació a calcular. Aquesta operació es pot referir a SUMA o MULTIPLICACIO.
     * @return El resultat mínim que es pot obtenir amb l'operació especificada.
     */
    private int calcularResultatMinim(int operacio) {
        ArrayList<ArrayList<Integer>> valorsColumnes = obtenirMatriuValorColumnes(CREIXENTMENT);
        return calcularResultat(valorsColumnes, operacio);
    }

    /**
     * Calcula el resultat màxim que es pot obtenir amb l'operació especificada.
     * Primer, obté una matriu de valors per a les columnes ordenats de manera decreixent.
     * Després, utilitza aquesta matriu per calcular el resultat de l'operació.
     *
     * @param operacio L'operació a calcular. Aquesta operació es pot referir a SUMA o MULTIPLICACIO.
     * @return El resultat mínim que es pot obtenir amb l'operació especificada.
     */
    private int calcularResultatMaxim(int operacio) {
        ArrayList<ArrayList<Integer>> valorsColumnes = obtenirMatriuValorColumnes(DECREIXENTMENT);
        return calcularResultat(valorsColumnes, operacio);
    }

    /**
     * Calcula el resultat de l'operació donada utilitzant els valors de les columnes proporcionades.
     * Per a cada casella seleccionada, obté un valor de la columna corresponent.
     * Aquest valor s'afegeix al resultat si l'operació és SUMA, o es multiplica pel resultat si l'operació és MULTIPLICACIO.
     * Els valors utilitzats es marquen com a utilitzats per a la fila corresponent per evitar la seva reutilització.
     * Els valors usats per cada columna es retiren de la llista de valors de la columna.
     *
     * @param valorsColumnes Una llista de llistes d'enters que representen els valors de cada columna.
     * @param operacio L'operació a calcular. Aquesta operació es pot referir a SUMA o MULTIPLICACIO.
     * @return El resultat de l'operació.
     */
    private int calcularResultat(ArrayList<ArrayList<Integer>> valorsColumnes, int  operacio) {
        valorsUsatsFila = new boolean[mida][mida+1];
        int resultat = 0;
        if (operacio == MULT) resultat = 1;

        for (int[] posCasellesSeleccionade : posCasellesSeleccionades) {
            int fila = posCasellesSeleccionade[0];
            int colum = posCasellesSeleccionade[1];
            int valor = 0;
            int j = 0;
            boolean trobat = false;
            while (j < valorsColumnes.get(colum).size() && !trobat) {
                valor = valorsColumnes.get(colum).get(j);
                if (!valorsUsatsFila[fila][valor]) {
                    valorsUsatsFila[fila][valor] = true;
                    valorsColumnes.get(colum).remove(j);
                    trobat = true;
                }
                j++;
            }

            if (operacio == SUM) resultat += valor;
            else resultat *= valor; //Cas on es calcula per multiplicacio, (operacio == MULT)
        }

        return resultat;
    }

    /**
     * Obté una matriu de valors per a les columnes.
     * Crea una llista de possibles valors en ordre creixent o decreixent segons el mode especificat.
     * Després, crea una matriu on cada columna conté una còpia de la llista de possibles valors.
     *
     * @param MODE El mode d'ordenació dels valors. Pot ser CREIXENTMENT o DECREIXENTMENT.
     * @return Una matriu on cada columna conté una llista de possibles valors.
     */
    private ArrayList<ArrayList<Integer>> obtenirMatriuValorColumnes(int MODE){
        ArrayList<Integer> possiblesValors = new ArrayList<>();

        if (MODE == CREIXENTMENT) {
            for (int valor = 1; valor <= mida; valor++) possiblesValors.add(valor);
        }
        else {
            for (int valor = mida; valor > 0; valor--) possiblesValors.add(valor);
        }

        ArrayList<ArrayList<Integer>> valorsColumna = new ArrayList<>();
        for (int i = 0; i < mida; i++) {
            valorsColumna .add(new ArrayList<>(possiblesValors));
        }
        return valorsColumna ;
    }

    /**
     * Comprova si la regió formada per les caselles seleccionades està connectada.
     * Primer, ordena les posicions de les caselles seleccionades.
     * Després, realitza una cerca en profunditat (DFS) per comprovar si totes les caselles seleccionades estan connectades.
     *
     * @return true si la regió està connectada, false en cas contrari.
     */
    private boolean esRegioConectada() {
        ordenarPosicionsCaselles();

        boolean[] visitades = new boolean[posCasellesSeleccionades.size()];
        int numVisitades = dfs(0, visitades);
        return numVisitades == posCasellesSeleccionades.size();
    }

    /**
     * Realitza una cerca en profunditat (DFS) a partir de la casella especificada.
     * Visita les caselles adjacents a la casella actual que encara no han estat visitades.
     * La cerca es realitza en les quatre direccions: amunt, abaix, esquerra i dreta.
     *
     * @param index L'índex de la casella actual en la llista de caselles seleccionades.
     * @param visitades Un array de booleans que indica quines caselles han estat visitades.
     * @return El nombre de caselles visitades a partir de la casella actual.
     */
    private int dfs(int index, boolean[] visitades) {
        if (visitades[index]) return 0;

        visitades[index] = true;
        int numVisitades = 1;

        // Visitar les caselles adjacents (amunt, abaix, esquerra, dreta)
        int[][] direccions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : direccions) {
            int x = posCasellesSeleccionades.get(index)[0] + dir[0];
            int y = posCasellesSeleccionades.get(index)[1] + dir[1];
            for (int i = 0; i < posCasellesSeleccionades.size(); i++) {
                if (!visitades[i] && posCasellesSeleccionades.get(i)[0] == x && posCasellesSeleccionades.get(i)[1] == y) {
                    numVisitades += dfs(i, visitades);
                }
            }
        }

        return numVisitades;
    }

    /**
     * Tradueix l'operació especificada a un número.
     * Cada operació es correspon a un número específic.
     * En el cas de la suma si només s'ha seleccionat una casella, l'operació es tradueix a 0.
     *
     * @param operacio L'operació a traduir. Pot ser "SUMA", "RESTA", "MULT", "DIV", "EXP" o "MOD".
     * @return Un string que representa el número corresponent a l'operació [1,6], o null si l'operació no és reconeguda.
     */
    private String traduirOperacioAnum(String operacio) {
        switch (operacio) {
            case "SUMA":
                if(posCasellesSeleccionades.size() == 1) return "0";
                return "1";
            case "RESTA":
                return "2";
            case "MULT":
                return "3";
            case "DIV":
                return "4";
            case "EXP":
                return "5";
            case "MOD":
                return "6";
            default:
                return null;
        }
    }

    /**
     * Tradueix l'operació especificada a un símbol.
     * Cada operació es correspon a un símbol específic.
     * En el cas de ser una suma amb una sola casella seleccionada, l'operació es tradueix a una cadena buida.
     *
     * @param operacio L'operació a traduir. Pot ser "SUMA", "RESTA", "MULT", "DIV", "EXP" o "MOD".
     * @return Un string que representa el símbol corresponent a l'operació, o null si l'operació no és reconeguda.
     */
    private String traduirOperacioAsimbol(String operacio) {
        switch (operacio) {
            case "SUMA":
                if(posCasellesSeleccionades.size() == 1) return "";
                return "+";
            case "RESTA":
                return "-";
            case "MULT":
                return "x";
            case "DIV":
                return "÷";
            case "EXP":
                return "^";
            case "MOD":
                return "%";
            default:
                return null;
        }
    }

    /**
     * Restableix l'estat del tauler.
     * Configura cada casella a l'estat inicial i elimina la informació de la regió.
     * També restableix el nombre de caselles assignades, les dades de les regions i les posicions de les caselles seleccionades.
     */
    public void resetTauler() {
        CasellaConstructora casella;
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                casella = caselles[i][j];
                casella.configInicial();
                casella.eliminarInfoRegio();
            }
        }
        numCasellesAssignades = 0;
        dadesRegions.clear();
        posCasellesSeleccionades.clear();
    }

}
