package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class CrearKenkenManual {

    private JPanel taulerKenken;
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

    private boolean taulerModificat = false;

    private ArrayList<String> constructorKenken = new ArrayList<>();
    private ArrayList<int[]> posCaselles = new ArrayList<>();
    private ArrayList<int[]> dadesRegio = new ArrayList<>();

    public CrearKenkenManual() {
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
                int size = Integer.parseInt((String) Objects.requireNonNull(grauComboBox.getSelectedItem()));
                System.out.println("Creant tauler de mida " + size);
                createKenkenBoard(size);
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

    private void createKenkenBoard(int size) {
        panelEsq.removeAll();
        panelEsq.setBackground(Color.BLACK);
        System.out.println("DinsCreateKenkenBoard");

        ArrayList<Boolean>[][] mapaAdjacents = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapaAdjacents[i][j] = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    mapaAdjacents[i][j].add(false);
                }
            }
        }


        taulerKenken = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CasellaConstructora casella = new CasellaConstructora(i,j);
                taulerKenken.add(casella);
            }
        }
        taulerKenken.setBackground(Color.BLACK);

        panelEsq.add(taulerKenken, BorderLayout.CENTER);
        panelEsq.validate();
    }


    public void afegirPosCasella (int x, int y) {
        posCaselles.add(new int[]{x, y});
    }

    public void eliminarPosCasella (int x, int y) {
        for (int i = 0; i < posCaselles.size(); i++) {
            if (posCaselles.get(i)[0] == x && posCaselles.get(i)[1] == y) {
                posCaselles.remove(i);
                break;
            }
        }
    }

    public void previewTauler (int size) {
        System.out.println("Vista previa del tauler de mida " + size);

        taulerKenken = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JPanel cuadrat = new JPanel();
                cuadrat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cuadrat.setBackground(Color.GRAY);
                taulerKenken.add(cuadrat);
            }
        }
        panelEsq.add(taulerKenken, BorderLayout.CENTER);
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
