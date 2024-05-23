package main.presentacio.CrearKenkenManual;

import main.domini.controladors.CtrlKenkens;
import main.presentacio.CtrlPresentacio;
import main.presentacio.MissatgePopUp;
import main.presentacio.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class CrearKenkenManual {

    private static CrearKenkenManual creardoraKenken;

    private JPanel panelComplet;
    private JPanel panelEsq;
    private JPanel grauPanel;
    private JPanel configPanel;
    private JLabel logoCreateLabel;
    private JLabel grauLabel;
    private JButton guardarButton;
    private JButton sortirButton;
    private JButton aceptarButton;
    private JComboBox grauComboBox;

    private TaulerConstrutor TaulerKenken;
    private boolean taulerModificat = false;


    public static CrearKenkenManual getInstance() {
        if (creardoraKenken == null) creardoraKenken = new CrearKenkenManual();
        return creardoraKenken;
    }

    public static void newInstance() {
        creardoraKenken = new CrearKenkenManual();
    }

    private CrearKenkenManual() {
        configuracioInicial();

        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taulerModificat) {
                    MissatgePopUp missatgePopUp = CtrlPresentacio.getInstance().showPopUp("<html>Estas segur que vols sortir?<br>Si surts sense guardar es perdran els canvis</html>");
                    if (!missatgePopUp.esCancelat()) {
                        System.out.println("Sortint de crear kenken");
                        CtrlPresentacio.getInstance().showMenuPrincipal();
                    }
                    else {
                        System.out.println("Sortida cancelada");
                    }
                }
                else {
                    System.out.println("Sortint de crear kenken");
                    CtrlPresentacio.getInstance().showMenuPrincipal();
                }
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taulerModificat = true;
                aceptarButton.setVisible(false);
                grauLabel.setVisible(false);
                grauComboBox.setVisible(false);
                guardarButton.setVisible(true);
                int mida = Integer.parseInt((String) grauComboBox.getSelectedItem());
                TaulerConstrutor.newInstance(mida);
                TaulerKenken = TaulerConstrutor.getInstance();
                panelEsq.removeAll();
                panelEsq.add(TaulerKenken, BorderLayout.CENTER);
                System.out.println("Creant tauler de mida " + mida);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mida = TaulerKenken.getMida();
                if (TaulerKenken.getNumCasellesAssignades() != mida*mida) {
                    JOptionPane.showMessageDialog(panelComplet, "Hi ha caselles sense regio assignada", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String contingutTauler = TaulerKenken.getContigutTauler();
                    System.out.println(contingutTauler);
                    if (CtrlKenkens.getInstance().comprovarKenkenCreat(contingutTauler.toString())) {
                        System.out.println("Kenken valid");
                        String idTauler = CtrlKenkens.getInstance().guardarTaulerBD(contingutTauler);
                        String missatge = "Tauler guardat amb id: " + idTauler  + " en la ubicacio data/taulers/mida" + mida + "/" + idTauler + ".txt";
                        JOptionPane.showMessageDialog(panelComplet, missatge, "Informació", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println(missatge);
                        CtrlPresentacio.getInstance().showMenuPrincipal();
                    } else {
                        JOptionPane.showMessageDialog(panelComplet, "El Kenken no es valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        grauComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt((String) Objects.requireNonNull(grauComboBox.getSelectedItem()));
                panelEsq.removeAll();
                previewTauler(size);
            }
        });
    }


    public void previewTauler (int size) {

         JPanel taulerPreview= new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JPanel cuadrat = new JPanel();
                cuadrat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cuadrat.setBackground(Color.GRAY);
                taulerPreview.add(cuadrat);
            }
        }
        panelEsq.add(taulerPreview, BorderLayout.CENTER);
        panelEsq.validate();
    }

    public void configuracioInicial () {
        System.out.println("Entrant a la pantalla de crear kenken");
        guardarButton.setVisible(false);
        previewTauler(3);
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    private void createUIComponents() {
        logoCreateLabel = new JLabel(Utils.carregarImatge("resources/imatges/logoKenkenCreador.png", 320, 320));
    }

}
