package main.presentacio.Partida;

import main.domini.controladors.ControladorPartida;
import main.domini.excepcions.*;
import main.presentacio.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static main.presentacio.CtrlPresentacio.NOMBREPARTIDESLLISTA;

/**
 * Controlador de la presentació de la partida
 * S'encarrega de gestionar les interaccions de l'usuari amb la partida i comunicar-se amb el controlador de domini
 * per a dur a terme les operacions corresponents.
 */
public class ControladorPresentacioPartida implements ObservadorCasella, ObservadorBoto, ObservadorLlista {
    /**
     * Constant que conté el color d'alguna cosa incorrecta
     */
    public static final String COLOR_ERROR = "243, 67, 65";
    /**
     * Constant que conté el color d'alguna cosa correcta
     */
    public static final String COLOR_BE = "40, 250, 85";
    /**
     * Controlador de la presentacio
     */
    private CtrlPresentacio controladorPresentacio_;
    /**
     * Vista de la partida
     */
    private VistaPartida vistaPartida_;
    /**
     * Vista del menú de jugar partida
     */
    private VistaMenuJugarPartida vistaMenuJugarPartida_;
    /**
     * Vista de les partides guardades
     */
    private VistaPartidesGuardades vistaPartidesGuardades_;
    /**
     * Controlador de la presentació de la partida
     */
    private static ControladorPresentacioPartida controladorPresentacioPartida_;
    /**
     * Panell principal
     */
    private JPanel mainPanel_;

    /**
     * Constructora per defecte
     */
    private ControladorPresentacioPartida() {
        controladorPresentacio_ = CtrlPresentacio.getInstance();
        mainPanel_ = new JPanel();
    }

    /**
     * Retorna la instància del controlador de la presentació de la partida
     * @return Instància del controlador de la presentació de la partida
     */
    public static ControladorPresentacioPartida getInstance() {
        if (controladorPresentacioPartida_ == null) controladorPresentacioPartida_ = new ControladorPresentacioPartida();
        return controladorPresentacioPartida_;
    }

    /**
     * Inicialitza la vista del menú de jugar partida
     * @param usuari Usuari que juga la partida i està loggejat
     */
    public void inicialitzaMenuJugarPartida(String usuari) {
        vistaMenuJugarPartida_ = new VistaMenuJugarPartida();
        vistaMenuJugarPartida_.setUsuari(usuari);
        vistaMenuJugarPartida_.addObservadorBoto(this);
        mainPanel_.removeAll();
        mainPanel_.add(vistaMenuJugarPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    /**
     * Reacciona quan es notifica que s'ha fet un click a una casella del joc.
     * Canvia el valor de la casella a domini i actualitza la vista.
     * @param valor Valor que s'ha posat a la casella.
     * @param fila Fila de la casella.
     * @param columna Columna de la casella.
     */
    @Override
    public void notificarCanviValor(String valor, int fila, int columna) {
        try{
            String estatPartida = controladorPresentacio_.introduirValor(fila, columna, Integer.parseInt(valor));
            vistaPartida_.actualitzaValors(valorsStringToInt(estatPartida));
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartida_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioValorInvalid | ExcepcioPartidaTancada | ExcepcioPartidaAcabada |
                 ExcepcioPosicioIncorrecta | ExcepcioCarregaPartida e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de undo de la partida.
     * Desfà l'últim moviment i actualitza la vista.
     */
    @Override
    public void notificarUndo() {
        try {
            String valors = controladorPresentacio_.desferMoviment();
            vistaPartida_.actualitzaValors(valorsStringToInt(valors));
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartida_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioPartidaTancada | ExcepcioValorInvalid | ExcepcioPartidaAcabada | ExcepcioPosicioIncorrecta e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioDoUndo ignored) {
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de redo de la partida.
     * Refà l'últim moviment i actualitza la vista.
     */
    @Override
    public void notificarRedo() {
        try {
            String valors = controladorPresentacio_.referMoviment();
            vistaPartida_.actualitzaValors(valorsStringToInt(valors));
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartida_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioPartidaTancada | ExcepcioValorInvalid | ExcepcioPartidaAcabada | ExcepcioPosicioIncorrecta e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioDoUndo ignored) {
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de pista de la partida.
     * Demana una pista al controlador de la partida i actualitza la vista.
     * La pista pot ser una regió incorrecta o els valors repetits, o bé un valor nou ja colocat.
     */
    @Override
    public void notificarPista() {
        try {
            ArrayList<int[]> posicionsIncorrectes  = controladorPresentacio_.donaPista();
            if (!posicionsIncorrectes.isEmpty()) vistaPartida_.actualitzaPosicionsIncorrectes(posicionsIncorrectes);
            else vistaPartida_.actualitzaValors(controladorPresentacio_.getValorsPartida());
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartida_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioCasellaNoExisteix | ExcepcioNoDivisor | ExcepcioCarregaPartida | ExcepcioMoltsValors |
                 ExcepcioDivisio_0 | ExcepcioPosicioIncorrecta | ExcepcioPartidaTancada | ExcepcioValorInvalid |
                 ExcepcioPartidaAcabada|ExcepcioCasellaNoModificable e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó d'acabar de la partida.
     * Acaba la partida si és correcta i indica si podrà participar en els rankings, tanca la partida i retorna al menú principal.
     */
    @Override
    public void notificarAcabar() {
        try {
            String[] partidaAcabada  = controladorPresentacio_.acabarPartida(vistaPartida_.getIdentificadorUsuari());
            if (partidaAcabada[0].equals("true")) {
                vistaPartida_.mostrarMissatgeMenu("Partida guardada correctament", true);
            } else {
                vistaPartida_.mostrarMissatgeMenu("La partida no ha estat guardada correctament", false);
            }
            StringBuilder missatge = new StringBuilder("Partida acabada amb temps: " + partidaAcabada[2] + "\n");
            if (partidaAcabada[1].equals("true")) {
                missatge.append("La partida no podrà participar als rankings");
            } else {
                missatge.append("La partida no podrà participar als rankings!");
            }
            JOptionPane.showOptionDialog(mainPanel_, missatge, null, JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Perfecte!"}, null);
            int delay = 1500;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    CtrlPresentacio.getInstance().showMenuPrincipal();
                }
            };
            new Timer(delay, taskPerformer).start();
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari|ExcepcioCasellaNoExisteix e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioPartidaMalament e) {
            vistaPartida_.mostrarMissatgeMenu("La partida és incorrecta", false);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de guardar de la partida.
     * Guarda la partida i mostra un missatge indicant si s'ha guardat correctament o no.
     */
    @Override
    public void notificarGuardar() {
        try {
            boolean guardada = controladorPresentacio_.guardarPartida(vistaPartida_.getIdentificadorUsuari());
            if (guardada) vistaPartida_.mostrarMissatgeMenu("Partida guardada correctament", true);
            else vistaPartida_.mostrarMissatgeMenu("La partida no ha estat guardada correctament", false);
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de tancar i guardar de la partida.
     * Tanca i guarda la partida i mostra un missatge indicant si s'ha guardat correctament o no.
     */
    @Override
    public void notificarTancaIguarda() {
        try {
            boolean guardada = controladorPresentacio_.tancarIguardarPartida(vistaPartida_.getIdentificadorUsuari());
            if (guardada) vistaPartida_.mostrarMissatgeMenu("Partida guardada correctament", true);
            else vistaPartida_.mostrarMissatgeMenu("La partida no ha estat guardada correctament", false);
            int delay = 1500;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    CtrlPresentacio.getInstance().showMenuPrincipal();
                }
            };
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de sortir de la partida.
     * Tanca la partida i retorna al menú principal.
     */
    @Override
    public void notificarSortir() {
        CtrlPresentacio.getInstance().showMenuPrincipal();
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de jugar partida introduint el tauler manualment.
     */
    @Override
    public void jugarIntroduirTaulerManualment() {

    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de jugar partida introduint l'identificador del tauler.
     * Demana a l'usuari l'identificador del tauler i inicia la partida amb aquest identificador.
     */
    @Override
    public void jugarIntroduirIdentificadorTauler() {
        String identificadorTauler = JOptionPane.showInputDialog(mainPanel_, "Introdueix l'identificador del tauler");
        try {
            controladorPresentacio_.iniciaPartidaIdentificadorTauler(identificadorTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaTauler | ExcepcioPartidaEnCurs | ExcepcioInicialitzacioControladorTauler e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de jugar partida aleatòria.
     * Porta a la creació d'una partida aleatòria.
     */
    // S'haurà de modificar per posar les operacions.
    @Override
    public void jugarPartidaAleatoria() {
        int midaTauler;
        do{
            String midaTaulerDeciso = JOptionPane.showInputDialog(mainPanel_, "Introdueix la mida del tauler que vols jugar");
            if (midaTaulerDeciso == null) return;
            try {
                midaTauler = Integer.parseInt(midaTaulerDeciso);
            }
            catch (NumberFormatException e) {
                return;
            }
            if (midaTauler > 9 || midaTauler < 3) {
                JOptionPane.showMessageDialog(mainPanel_, "La mida del tauler ha de ser entre 3 i 9", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while (midaTauler > 9 || midaTauler < 3);
        try{
            controladorPresentacio_.iniciaPartidaAleatoria(midaTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioInicialitzacioControladorTauler | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de jugar última partida guardada.
     * Carrega l'última partida guardada i la mostra.
     */
    @Override
    public void jugarUltimaPartidaGuardada() {
        try {
            controladorPresentacio_.carregarUltimaPartidaGuardada(vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioPartidaEnCurs | ExcepcioCarregaPartida|ExcepcioInicialitzacioPersistenciaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de jugar partida guardada.
     * Carrega les partides guardades de l'usuari i mostra la llista de partides guardades.
     */
    @Override
    public void jugarPartidaGuardada() {
        try {
            ArrayList<String> partidesGuardades = controladorPresentacio_.carregarPartidesGuardadesUsuari(vistaMenuJugarPartida_.getUsuari());
            vistaPartidesGuardades_ = new VistaPartidesGuardades(partidesGuardades.toArray(new String[0]),NOMBREPARTIDESLLISTA);
            vistaPartidesGuardades_.addObservadorLlista(this);
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartidesGuardades_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioCarregaPartida | ExcepcioInicialitzacioPersistenciaPartida e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click al botó de tornar per tornar al menú de JugarPartida.
     */
    @Override
    public void tornarMenu() {
        mainPanel_.removeAll();
        mainPanel_.add(vistaMenuJugarPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    /**
     * Mètode que s'executa quan es notifica que s'ha fet click a una partida guardada de la llista.
     * Inicia la partida guardada seleccionada.
     * @param partidaGuardada Partida guardada a la que s'ha fet click.
     */
    @Override
    public void clickatLlista(String partidaGuardada) {
        String identificadorPartida = partidaGuardada.split("\n")[0];
        try {
            controladorPresentacio_.iniciarPartidaGuardada(identificadorPartida, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Inicialitza la vista de la partida amb les dades de la partida actual.
     */
    private void inicialitzaVistaPartida() {
        vistaPartida_ = new VistaPartida(controladorPresentacio_.getMidaPartida(),controladorPresentacio_.getAdjacentsPartida() ,vistaMenuJugarPartida_.getUsuari(), controladorPresentacio_.getValorsPartida());
        for (int i = 0; i < controladorPresentacio_.getMidaPartida(); i++) {
            for (int j = 0; j < controladorPresentacio_.getMidaPartida(); j++) {
                vistaPartida_.addObserverCasella(this, i, j);
            }
        }
        vistaPartida_.addObserverMenuPartida(this);
        mainPanel_.removeAll();
        mainPanel_.add(vistaPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    /**
     * Converteix la string que representa l'estat d'una partida a una matriu d'ints.
     * @param solucioTotalText String que representa l'estat de la partida.
     * @return Matriu d'ints que representa l'estat de la partida.
     */
    private int[][] valorsStringToInt(String solucioTotalText) {
        String[] linies = solucioTotalText.split("\n");
        int mida = linies.length;
        int[][] solucioTotal = new int[mida][mida];
        for (int i = 0; i < mida; i++) {
            String[] valors = linies[i].split(" ");
            for (int j = 0; j < mida; j++) {
                solucioTotal[i][j] = Integer.parseInt(valors[j]);
            }
        }
        return solucioTotal;
    }
    /**
     * Retorna el panell principal
     * @return Panell principal
     */
    public JPanel getDefaultPanel() {
        return mainPanel_;
    }

}