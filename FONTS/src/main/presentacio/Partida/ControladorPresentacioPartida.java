package main.presentacio.Partida;

import main.domini.controladors.ControladorPartida;
import main.domini.excepcions.*;
import main.presentacio.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPresentacioPartida implements ObservadorCasella, ObservadorBoto{
    public static String COLOR_ERROR = "243, 67, 65";
    public static String COLOR_BE = "40, 250, 85";
    private static ControladorPartida controladorPartida_;
    private VistaPartida vistaPartida_;
    private VistaMenuJugarPartida vistaMenuJugarPartida_;
    private VistaPartidesGuardades vistaPartidesGuardades_;
    private JFrame mainFrame_;
    ControladorPresentacioPartida(JFrame mainFrame) {
        mainFrame_ = mainFrame;
    }
    public void setControladorPartida(ControladorPartida controladorPartida) {
        controladorPartida_ = controladorPartida;
    }
    @Override
    public void notificarCanviValor(String valor, int fila, int columna) {
        try{
            String estatPartida = controladorPartida_.introduirValor(fila, columna, Integer.parseInt(valor));
            vistaPartida_.actualitzaValors(valorsStringToInt(estatPartida));
            mainFrame_.setContentPane(vistaPartida_);
            mainFrame_.revalidate();
            mainFrame_.repaint();
        } catch (ExcepcioValorInvalid | ExcepcioPartidaTancada | ExcepcioPartidaAcabada |
                 ExcepcioPosicioIncorrecta | ExcepcioCarregaPartida e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void notificarUndo() {
        try {
            String valors = controladorPartida_.desferMoviment();
            vistaPartida_.actualitzaValors(valorsStringToInt(valors));
            mainFrame_.setContentPane(vistaPartida_);
            mainFrame_.revalidate();
            mainFrame_.repaint();
        } catch (ExcepcioPartidaTancada | ExcepcioValorInvalid | ExcepcioPartidaAcabada | ExcepcioPosicioIncorrecta e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioDoUndo ignored) {
        }
    }

    @Override
    public void notificarRedo() {
        try {
            String valors = controladorPartida_.referMoviment();
            vistaPartida_.actualitzaValors(valorsStringToInt(valors));
            mainFrame_.setContentPane(vistaPartida_);
            mainFrame_.revalidate();
            mainFrame_.repaint();
        } catch (ExcepcioPartidaTancada | ExcepcioValorInvalid | ExcepcioPartidaAcabada | ExcepcioPosicioIncorrecta e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcioDoUndo ignored) {
        }
    }
    @Override
    public void notificarPista() {
        try {
            ArrayList<int[]> posicionsIncorrectes  = controladorPartida_.donaPista();
            vistaPartida_.actualitzaPosicionsIncorrectes(posicionsIncorrectes);
            mainFrame_.setContentPane(vistaPartida_);
            mainFrame_.revalidate();
            mainFrame_.repaint();
        } catch (ExcepcioCasellaNoExisteix | ExcepcioNoDivisor | ExcepcioCarregaPartida | ExcepcioMoltsValors |
                 ExcepcioDivisio_0 | ExcepcioPosicioIncorrecta | ExcepcioPartidaTancada | ExcepcioValorInvalid |
                 ExcepcioPartidaAcabada e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showOptionDialog(mainFrame_, missatge, null, JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Perfecte!"}, null);
            int delay = 1500;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    CtrlPresentacio.getInstance().retornaMenuPrincipal();
                }
            };
            new Timer(delay, taskPerformer).start();
        } catch (ExcepcioPartidaTancada | ExcepcioPartidaAcabada | ExcepcioCarregaPartida |
                 ExcepcioNoPermisUsuari|ExcepcioCasellaNoExisteix e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        String identificadorTauler = JOptionPane.showInputDialog(mainFrame_, "Introdueix l'identificador del tauler");
        try {
            controladorPartida_.iniciaPartidaIdentificadorTauler(identificadorTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaTauler | ExcepcioPartidaEnCurs | ExcepcioInicialitzacioControladorTauler e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // S'haurà de modificar per posar les operacions.
    @Override
    public void jugarPartidaAleatoria() {
        int midaTauler;
        do{
            midaTauler = Integer.parseInt(JOptionPane.showInputDialog(mainFrame_, "Introdueix la mida del tauler que vols jugar"));
            if (midaTauler > 9 || midaTauler < 3) {
                JOptionPane.showMessageDialog(mainFrame_, "La mida del tauler ha de ser entre 3 i 9", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while (midaTauler > 9 || midaTauler < 3);
        try{
            controladorPartida_.iniciaPartidaAleatoria(midaTauler, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioInicialitzacioControladorTauler | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void jugarUltimaPartidaGuardada() {
        try {
            controladorPartida_.carregarUltimaPartidaGuardada(vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioPartidaEnCurs | ExcepcioCarregaPartida|ExcepcioInicialitzacioPersistenciaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void jugarPartidaGuardada() {
        try {
            ArrayList<String> partidesGuardades = controladorPartida_.carregarPartidesGuardadesUsuari(vistaMenuJugarPartida_.getUsuari());
            vistaPartidesGuardades_ = new VistaPartidesGuardades(partidesGuardades.toArray(new String[0]));
            vistaPartidesGuardades_.addObservadorBoto(this);
            mainFrame_.setContentPane(vistaPartidesGuardades_);
            mainFrame_.revalidate();
            mainFrame_.repaint();
        } catch (ExcepcioCarregaPartida | ExcepcioInicialitzacioPersistenciaPartida e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void tornar() {
        CtrlPresentacio.getInstance().retornaMenuPrincipal();
    }

    @Override
    public void tornarMenu() {
        mainFrame_.setContentPane(vistaMenuJugarPartida_);
        mainFrame_.revalidate();
        mainFrame_.repaint();
    }

    @Override
    public void carregaPartida(String partidaGuardada) {
        String identificadorPartida = partidaGuardada.split("\n")[0];
        try {
            controladorPartida_.iniciarPartidaGuardada(identificadorPartida, vistaMenuJugarPartida_.getUsuari());
            inicialitzaVistaPartida();
        } catch (ExcepcioCarregaPartida | ExcepcioNoPermisUsuari | ExcepcioCreacioPartida | ExcepcioPartidaEnCurs e) {
            JOptionPane.showMessageDialog(mainFrame_, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void inicialitzaVistaPartida() {
        vistaPartida_ = new VistaPartida(controladorPartida_.getMidaPartida(),controladorPartida_.getAdjacentsPartida() ,vistaMenuJugarPartida_.getUsuari());
        for (int i = 0; i < controladorPartida_.getMidaPartida(); i++) {
            for (int j = 0; j < controladorPartida_.getMidaPartida(); j++) {
                vistaPartida_.addObserverCasella(this, i, j);
            }
        }
        vistaPartida_.addObserverMenuPartida(this);
        mainFrame_.setContentPane(vistaPartida_);
        mainFrame_.revalidate();
        mainFrame_.repaint();
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