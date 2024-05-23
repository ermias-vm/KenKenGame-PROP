package main.presentacio.Partida;

import main.domini.controladors.ControladorPartida;
import main.domini.excepcions.*;
import main.presentacio.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPresentacioPartida implements ObservadorCasella, ObservadorBoto {
    public static String COLOR_ERROR = "243, 67, 65";
    public static String COLOR_BE = "40, 250, 85";
    private static ControladorPartida controladorPartida_;
    private VistaPartida vistaPartida_;
    private VistaMenuJugarPartida vistaMenuJugarPartida_;
    private VistaPartidesGuardades vistaPartidesGuardades_;
    private static ControladorPresentacioPartida controladorPresentacioPartida_;
    private JPanel mainPanel_;

    private ControladorPresentacioPartida() {
        controladorPartida_ = ControladorPartida.getInstance();
        mainPanel_ = new JPanel();
    }
    public static ControladorPresentacioPartida getInstance() {
        if (controladorPresentacioPartida_ == null) controladorPresentacioPartida_ = new ControladorPresentacioPartida();
        return controladorPresentacioPartida_;
    }
    public void inicialitzaMenuJugarPartida(String usuari) {
        vistaMenuJugarPartida_ = new VistaMenuJugarPartida();
        vistaMenuJugarPartida_.setUsuari(usuari);
        vistaMenuJugarPartida_.addObservadorBoto(this);
        mainPanel_.removeAll();
        mainPanel_.add(vistaMenuJugarPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }
    @Override
    public void notificarCanviValor(String valor, int fila, int columna) {
        try{
            String estatPartida = controladorPartida_.introduirValor(fila, columna, Integer.parseInt(valor));
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
    @Override
    public void notificarUndo() {
        try {
            String valors = controladorPartida_.desferMoviment();
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

    @Override
    public void notificarRedo() {
        try {
            String valors = controladorPartida_.referMoviment();
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
    @Override
    public void notificarPista() {
        try {
            ArrayList<int[]> posicionsIncorrectes  = controladorPartida_.donaPista();
            vistaPartida_.actualitzaPosicionsIncorrectes(posicionsIncorrectes);
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartida_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioCasellaNoExisteix | ExcepcioNoDivisor | ExcepcioCarregaPartida | ExcepcioMoltsValors |
                 ExcepcioDivisio_0 | ExcepcioPosicioIncorrecta | ExcepcioPartidaTancada | ExcepcioValorInvalid |
                 ExcepcioPartidaAcabada e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioCasellaNoModificable e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void notificarAcabar() {
        try {
            String[] partidaAcabada  = controladorPartida_.acabarPartida(vistaPartida_.getIdentificadorUsuari());
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
                    CtrlPresentacio.getInstance().retornaMenuPrincipal();
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
    @Override
    public void notificarGuardar() {
        try {
            boolean guardada = controladorPartida_.guardarPartida(vistaPartida_.getIdentificadorUsuari());
            if (guardada) vistaPartida_.mostrarMissatgeMenu("Partida guardada correctament", true);
            else vistaPartida_.mostrarMissatgeMenu("La partida no ha estat guardada correctament", false);
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void notificarTancaIguarda() {
        try {
            boolean guardada = controladorPartida_.tancarIguardarPartida(vistaPartida_.getIdentificadorUsuari());
            if (guardada) vistaPartida_.mostrarMissatgeMenu("Partida guardada correctament", true);
            else vistaPartida_.mostrarMissatgeMenu("La partida no ha estat guardada correctament", false);
            int delay = 1500;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    CtrlPresentacio.getInstance().retornaMenuPrincipal();
                }
            };
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void notificarSortir() {
        CtrlPresentacio.getInstance().retornaMenuPrincipal();
    }

    @Override
    public void jugarIntroduirTaulerManualment() {

    }

    @Override
    public void jugarIntroduirIdentificadorTauler() {
        String identificadorTauler = JOptionPane.showInputDialog(mainPanel_, "Introdueix l'identificador del tauler");
        try {
            controladorPartida_.iniciaPartidaIdentificadorTauler(identificadorTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaTauler | ExcepcioPartidaEnCurs | ExcepcioInicialitzacioControladorTauler e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            controladorPartida_.iniciaPartidaAleatoria(midaTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioInicialitzacioControladorTauler | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void jugarUltimaPartidaGuardada() {
        try {
            controladorPartida_.carregarUltimaPartidaGuardada(vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioPartidaEnCurs | ExcepcioCarregaPartida|ExcepcioInicialitzacioPersistenciaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void jugarPartidaGuardada() {
        try {
            ArrayList<String> partidesGuardades = controladorPartida_.carregarPartidesGuardadesUsuari(vistaMenuJugarPartida_.getUsuari());
            vistaPartidesGuardades_ = new VistaPartidesGuardades(partidesGuardades.toArray(new String[0]));
            vistaPartidesGuardades_.addObservadorBoto(this);
            mainPanel_.removeAll();
            mainPanel_.add(vistaPartidesGuardades_);
            mainPanel_.revalidate();
            mainPanel_.repaint();
        } catch (ExcepcioCarregaPartida | ExcepcioInicialitzacioPersistenciaPartida e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void tornar() {
        CtrlPresentacio.getInstance().retornaMenuPrincipal();
    }

    @Override
    public void tornarMenu() {
        mainPanel_.removeAll();
        mainPanel_.add(vistaMenuJugarPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    @Override
    public void carregaPartida(String partidaGuardada) {
        String identificadorPartida = partidaGuardada.split("\n")[0];
        try {
            controladorPartida_.iniciarPartidaGuardada(identificadorPartida, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainPanel_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void inicialitzaVistaPartida() {
        vistaPartida_ = new VistaPartida(controladorPartida_.getMidaPartida(),controladorPartida_.getAdjacentsPartida() ,vistaMenuJugarPartida_.getUsuari(), controladorPartida_.getValorsPartida());
        for (int i = 0; i < controladorPartida_.getMidaPartida(); i++) {
            for (int j = 0; j < controladorPartida_.getMidaPartida(); j++) {
                vistaPartida_.addObserverCasella(this, i, j);
            }
        }
        vistaPartida_.addObserverMenuPartida(this);
        mainPanel_.removeAll();
        mainPanel_.add(vistaPartida_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }
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
}