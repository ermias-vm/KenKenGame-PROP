package main.presentacio;

import main.domini.classes.CreeadoraKenkenParametres.CreadorKenkenParam;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearKenKenParametres extends JDialog {
    //private JTextField textGrau;
    private JButton CreaButton;
    private JButton CancelaButton;
    private JPanel panel2;
    private JPanel panelComplet;
    private JLabel labelLogo;
    private JComboBox ComboBoxGrau;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    //private JComboBox comboBoxOperacions;

    public CrearKenKenParametres(boolean jugarDespres) {
        System.out.println("Entrant a la pantalla de generar kenken");

        CreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int grau = Integer.parseInt("" + ComboBoxGrau.getSelectedItem());
                boolean suma = false;
                boolean resta = false;
                boolean multiplicacio = false;
                boolean divisio = false;
                boolean modul = false;
                boolean exponenciacio = false;
                if (radioButton1.isSelected()) {
                    suma = true;
                } else if (radioButton2.isSelected()) {
                    suma = true;
                    resta = true;
                } else if (radioButton3.isSelected()) {
                    multiplicacio = true;
                    divisio = true;
                } else if (radioButton4.isSelected()) {
                    suma = true;
                    multiplicacio = true;
                } else if (radioButton5.isSelected()) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                } else if (radioButton6.isSelected()) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                    modul = true;
                    exponenciacio = true;
                }

                String identificador = crearKenKen(grau, suma, resta, multiplicacio, divisio, modul, exponenciacio);
                if (jugarDespres) CtrlPresentacio.getInstance().jugarIdentificadorTauler(identificador);
            }
        });


        CancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de generar kenken");
                if (jugarDespres) CtrlPresentacio.getInstance().initJugar();
                else CtrlPresentacio.getInstance().showMenuPrincipal();
            }
        });
    }

    private String crearKenKen(int grau, boolean suma, boolean resta, boolean multiplicacio, boolean divisio, boolean modul, boolean exponenciacio) {

        int cont = 0;
        if(suma) cont++;
        if(resta) cont++;
        if(multiplicacio) cont++;
        if(divisio) cont++;
        if(modul) cont++;
        if(exponenciacio) cont++;

        if(!suma && !resta && !multiplicacio && !divisio && !modul && !exponenciacio){
            JOptionPane.showMessageDialog(this,
                    "Has de seleccionar alguna operació",
                    "Torna-ho a provar",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        //Aixo treu error si ha seleccionat massa operacions pel grau seleccionat
        if(cont > grau){
            JOptionPane.showMessageDialog(this,
                    "Has seleccionat massa operacions per un grau tant petit",
                    "Torna-ho a provar",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        System.out.println("Creando KenKen con los siguientes parámetros:");
        System.out.println("Grado: " + grau);
        System.out.println("Operaciones:");
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicación: " + multiplicacio);
        System.out.println("División: " + divisio);
        System.out.println("Módulo: " + modul);
        System.out.println("Exponenciación: " + exponenciacio);
        return CreadorKenkenParam.creadora(grau, suma, resta, multiplicacio, divisio, modul, exponenciacio);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));

        //radioButton1.setIcon(new ImageIcon("resources/imatges/Suma.png"));
        /*
        radioButton1 = new JRadioButton(Utils.carregarImatge("resources/imatges/Suma.png",50,50));
        radioButton2 = new JRadioButton(Utils.carregarImatge("resources/imatges/SumaResta.png",50,50));
        radioButton3 = new JRadioButton(Utils.carregarImatge("resources/imatges/MultDiv.png",50,50));
        radioButton4 = new JRadioButton(Utils.carregarImatge("resources/imatges/SumaMult.png",50,50));
        radioButton5 = new JRadioButton(Utils.carregarImatge("resources/imatges/4Ops.png",50,50));
        radioButton6 = new JRadioButton(Utils.carregarImatge("resources/imatges/TotesOps.png",50,50));

        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));
        */

        // Load icons for selected and unselected states
        ImageIcon sumaIcon = Utils.carregarImatge("resources/imatges/Suma.png", 50, 50);
        ImageIcon sumaSelectedIcon = Utils.carregarImatge("resources/imatges/SumaSel.png", 50, 50);

        ImageIcon sumaRestaIcon = Utils.carregarImatge("resources/imatges/SumaResta.png", 50, 50);
        ImageIcon sumaRestaSelectedIcon = Utils.carregarImatge("resources/imatges/SumaRestaSel.png", 50, 50);

        ImageIcon multDivIcon = Utils.carregarImatge("resources/imatges/MultDiv.png", 50, 50);
        ImageIcon multDivSelectedIcon = Utils.carregarImatge("resources/imatges/MultDivSel.png", 50, 50);

        ImageIcon sumaMultIcon = Utils.carregarImatge("resources/imatges/SumaMult.png", 50, 50);
        ImageIcon sumaMultSelectedIcon = Utils.carregarImatge("resources/imatges/SumaMultSel.png", 50, 50);

        ImageIcon cuatroOpsIcon = Utils.carregarImatge("resources/imatges/4Ops.png", 50, 50);
        ImageIcon cuatroOpsSelectedIcon = Utils.carregarImatge("resources/imatges/4OpsSel.png", 50, 50);

        ImageIcon todasOpsIcon = Utils.carregarImatge("resources/imatges/TotesOps.png", 50, 50);
        ImageIcon todasOpsSelectedIcon = Utils.carregarImatge("resources/imatges/TotesOpsSel.png", 50, 50);

        // Initialize radio buttons with icons
        radioButton1 = new JRadioButton(sumaIcon);
        radioButton1.setSelectedIcon(sumaSelectedIcon);

        radioButton2 = new JRadioButton(sumaRestaIcon);
        radioButton2.setSelectedIcon(sumaRestaSelectedIcon);

        radioButton3 = new JRadioButton(multDivIcon);
        radioButton3.setSelectedIcon(multDivSelectedIcon);

        radioButton4 = new JRadioButton(sumaMultIcon);
        radioButton4.setSelectedIcon(sumaMultSelectedIcon);

        radioButton5 = new JRadioButton(cuatroOpsIcon);
        radioButton5.setSelectedIcon(cuatroOpsSelectedIcon);

        radioButton6 = new JRadioButton(todasOpsIcon);
        radioButton6.setSelectedIcon(todasOpsSelectedIcon);

    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }
}
