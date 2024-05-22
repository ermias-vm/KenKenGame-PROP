package main.presentacio;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class CasellaConstructora extends JPanel {

    int posX;
    int posY;
    private boolean seleccionada = false;
    private boolean pertanyRegio = false;
    private int indexRegio = -1;

    public CasellaConstructora(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        //System.out.println("Soc a la casella " + posX + " " + posY);
        addMouseListener();
    }

    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasellaConstructora casella = (CasellaConstructora) e.getSource();
                if (SwingUtilities.isLeftMouseButton(e) && !casella.teRegioAssignada()) {
                    casella.setSeleccionada();
                }
                else if (SwingUtilities.isRightMouseButton(e) && !casella.teRegioAssignada() && casella.esSeleccionada()) {

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

                    Object[] message = {
                            "Operació:", operacioPanel,
                            filler,
                            resultatPanel
                    };

                    JOptionPane optionPane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog("Introdueix la operació i el resultat");

                    dialog.setSize(new Dimension(280, 240));
                    dialog.setVisible(true);

                    int opcio = JOptionPane.OK_OPTION;
                    if (optionPane.getValue() != null) {
                        opcio = (int) optionPane.getValue();
                    }

                    if (opcio == JOptionPane.OK_OPTION) {
                        String operacio = "SUMA";
                        for (Enumeration<AbstractButton> buttons = operacioGroup.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();
                            if (button.isSelected()) {
                                operacio = button.getText();
                                break;
                            }
                        }
                        String resultat = resultatField.getText();
                        CrearKenkenManual.getInstance().assignarCasellesRegio(operacio, resultat);
                    }
                }
            }
        });
    }

    public void setSeleccionada() {
            if (this.seleccionada) {
                this.seleccionada = false;
                this.setBackground(new Color(255, 255, 255));
                CrearKenkenManual.getInstance().eliminarPosCasella(posX, posY);

            } else {
                this.seleccionada = true;
                this.setBackground(new Color(145, 252, 246));
                CrearKenkenManual.getInstance().afegirPosCasella(posX, posY);
            }
    }

    public void marcaComRegio(int index) {
        this.pertanyRegio = true;
        this.indexRegio = index;
        //System.out.println("Casella " + posX + " " + posY + " pertany a la regio " + index);
        this.setBackground(new Color(104, 253, 104));
    }

    public void desmarcaComRegio() {
        this.pertanyRegio = false;
        this.setBackground(new Color(255, 255, 255));
    }

    public boolean esSeleccionada() {
        return seleccionada;
    }

    public boolean teRegioAssignada() {
        return pertanyRegio;
    }

    public void setIndexRegio(int index) {
        this.indexRegio = index;
    }

    public int getIndexRegio() {
        return this.indexRegio;
    }
}
