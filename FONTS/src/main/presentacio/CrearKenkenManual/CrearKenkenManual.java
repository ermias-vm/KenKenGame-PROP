package main.presentacio.CrearKenkenManual;

import main.domini.controladors.CtrlKenkens;
import main.presentacio.CtrlPresentacio;
import main.presentacio.MissatgePopUp;
import main.presentacio.Utils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
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



    public JPanel crearOperacioPanel() {
        JPanel operacioPanel = new JPanel();
        operacioPanel.setLayout(new GridLayout(3, 2));
        String[] operacions = {"SUMA", "RESTA", "MULT", "DIV", "EXP", "MOD"};
        ButtonGroup operacioGroup = new ButtonGroup();
        boolean primerBoto = true;
        for (String operacio : operacions) {
            JRadioButton operacioButton = new JRadioButton(operacio);
            if (primerBoto) {
                operacioButton.setSelected(true);
                primerBoto = false;
            }
            operacioGroup.add(operacioButton);
            operacioPanel.add(operacioButton);
        }
        return operacioPanel;
    }

    public void processarEntradaUsuari() {
        JPanel operacioPanel = this.crearOperacioPanel();
        JPanel resultatPanel = this.crearResultatPanel();
        int opcio;
        String error = null;

        do {
            opcio = this.mostrarDialog(operacioPanel, resultatPanel, error);

            if (opcio == JOptionPane.OK_OPTION) {
                String operacio = this.obtenerOperacioSeleccionada(operacioPanel);
                String resultat = ((JTextField)resultatPanel.getComponent(1)).getText();
                error = TaulerConstrutor.getInstance().validarEntrada(operacio, resultat);
                if (error == null) {
                    TaulerConstrutor.getInstance().assignarCasellesRegio(operacio, resultat);
                }
            }
        } while (opcio == JOptionPane.OK_OPTION && error != null);
    }

    public JPanel crearResultatPanel() {
        Box.Filler filler = new Box.Filler(new Dimension(0, 15), new Dimension(0, 15), new Dimension(0, 15));
        JTextField resultatField = new JTextField();
        JPanel resultatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultatPanel.add(new JLabel("Resultat:"));

        resultatField.setPreferredSize(new Dimension(90, 20));

        ((AbstractDocument) resultatField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException, BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 9 && string.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        resultatPanel.add(resultatField);
        return resultatPanel;
    }

    public int mostrarDialog(JPanel operacioPanel, JPanel resultatPanel, String errorMessage) {
        Object[] message = {
                "Operació:", operacioPanel,
                new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10)),
                resultatPanel
        };

        JOptionPane optionPane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog("Introdueix la operació i el resultat");
        dialog.setSize(new Dimension(280, 240));

        if (errorMessage != null) {
            JOptionPane.showMessageDialog(dialog, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }

        dialog.setVisible(true);
        return optionPane.getValue() == null ? JOptionPane.CANCEL_OPTION : (int) optionPane.getValue();
    }

    public String obtenerOperacioSeleccionada(JPanel operacioPanel) {
        String operacio = "SUMA";
        for (Component comp : operacioPanel.getComponents()) {
            if (comp instanceof JRadioButton) {
                JRadioButton button = (JRadioButton) comp;
                if (button.isSelected()) {
                    operacio = button.getText();
                    break;
                }
            }
        }
        return operacio;
    }


    public void previewTauler (int size) {
        JPanel taulerPreview = new JPanel(new GridLayout(size, size));
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
