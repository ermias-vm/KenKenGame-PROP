package main.presentacio;

import main.domini.controladors.CtrlKenkens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class CrearKenkenManual {
    private static CrearKenkenManual creardoraKenken;

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
    private int numCasellesAssignades = 0;
    private CasellaConstructora[][] caselles;
    private ArrayList<String> dadesRegions = new ArrayList<>();
    private ArrayList<int[]> posCaselles = new ArrayList<>();



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
                int size = Integer.parseInt((String) Objects.requireNonNull(grauComboBox.getSelectedItem()));
                System.out.println("Creant tauler de mida " + size);
                createKenkenBoard(size);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = caselles.length; // Asumiendo que 'caselles' es una matriz cuadrada
                if (numCasellesAssignades != size * size) {
                    JOptionPane.showMessageDialog(panelComplet, "Hi ha caselles sense regio assignada", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    StringBuilder sb = new StringBuilder();
                    sb.append(caselles.length).append(" ").append(dadesRegions.size()).append("\n");
                    for (String s : dadesRegions) {
                        sb.append(s);
                        sb.append("\n");
                    }
                    //System.out.println(sb.toString());
                    if (CtrlKenkens.getInstance().comprovarKenkenCreat(sb.toString())) {
                        System.out.println("Kenken valid");
                        //CtrlKenkens.getInstance().guardarKenken(sb.toString());
                        //CtrlPresentacio.getInstance().showMenuPrincipal();
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

    private void createKenkenBoard(int size) {
        panelEsq.removeAll();
        
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
        caselles = new CasellaConstructora[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CasellaConstructora casella = new CasellaConstructora(i,j);
                taulerKenken.add(casella);
                caselles[i][j] = casella;
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

    public void assignarCasellesRegio(String operacio, String resultat) {
        StringBuilder infoRegio = new StringBuilder();
        infoRegio.append(traduirOperacio(operacio)).append(" ");
        infoRegio.append(resultat).append(" ");
        infoRegio.append(posCaselles.size());

        for (int[] pos : posCaselles) {
            CasellaConstructora casella = caselles[pos[0]][pos[1]];
            casella.marcaComRegio(dadesRegions.size());
            infoRegio.append(" ").append(pos[0]).append(" ").append(pos[1]);
        }

        dadesRegions.add(infoRegio.toString().trim());
        imprimirDadesRegio();
        numCasellesAssignades += posCaselles.size();
        System.out.println("Caselles assignades: " + numCasellesAssignades);
        posCaselles.clear();

    }

    private String traduirOperacio(String operacio) {
        switch (operacio) {
            case "SUMA":
                if(posCaselles.size() == 1) return "0";
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
                return "-1";
        }
    }
    public void imprimirDadesRegio() {
        for (String infoRegio : dadesRegions) {
            System.out.println(infoRegio);
        }
    }

    public void previewTauler (int size) {

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
