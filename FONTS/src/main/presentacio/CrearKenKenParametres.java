package main.presentacio;

import main.domini.classes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CrearKenKenParametres extends JDialog {
    //private JTextField textGrau;
    private JButton Crea;
    private JButton Cancela;
    private JPanel PanelCreacio;
    private JLabel ImatgeKenKen;
    private JComboBox ComboBoxGrau;
    private JComboBox comboBoxOperacions;

    public CrearKenKenParametres() {
        System.out.println("Entrant a la pantalla de generar kenken");
        Crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int grau = Integer.parseInt("" + ComboBoxGrau.getSelectedItem());
                String operacions = Objects.requireNonNull(comboBoxOperacions.getSelectedItem()).toString();
                boolean suma = false;
                boolean resta = false;
                boolean multiplicacio = false;
                boolean divisio = false;
                boolean modul = false;
                boolean exponenciacio = false;
                if(operacions.equals("1) Suma")) {
                    suma = true;
                }else if(operacions.equals("2) Suma + Resta")) {
                    suma = true;
                    resta = true;
                }else if(operacions.equals("3) Multiplicació + Divisió")) {
                    multiplicacio = true;
                    divisio = true;
                }else if(operacions.equals("4) Opcions 2 i 3 juntes")) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                }else if(operacions.equals("5) La opció 4 + Modul")) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                    modul = true;
                }else if(operacions.equals("6) La opció 4 + Exponenciació")) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                    exponenciacio = true;
                }else if(operacions.equals("7) La opció 4 + Modul+ Exponenciació")) {
                    suma = true;
                    resta = true;
                    multiplicacio = true;
                    divisio = true;
                    modul = true;
                    exponenciacio = true;
                }

                crearKenKen(grau, suma, resta, multiplicacio, divisio, modul, exponenciacio);
            }
        });

        Cancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de generar kenken");
                CtrlPresentacio.getInstance().showMenuPrincipal();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Crear KenKen por Parámetros");
        frame.setContentPane(new CrearKenKenParametres().PanelCreacio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void crearKenKen(int grau, boolean suma, boolean resta, boolean multiplicacio, boolean divisio, boolean modul, boolean exponenciacio) {

        int cont = 0;
        if(suma) cont++;
        if(resta) cont++;
        if(multiplicacio) cont++;
        if(divisio) cont++;
        if(modul) cont++;
        if(exponenciacio) cont++;

        //Aixo treu error si ha seleccionat massa operacions pel grau seleccionat
        if(cont > grau){
            JOptionPane.showMessageDialog(this,
                    "Has seleccionat massa operacions per un grau tant petit",
                    "Torna-ho a provar",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        CreadorKenkenParam.creadora(grau, suma, resta, multiplicacio, divisio, modul, exponenciacio);
        System.out.println("Creando KenKen con los siguientes parámetros:");
        System.out.println("Grado: " + grau);
        System.out.println("Operaciones:");
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicación: " + multiplicacio);
        System.out.println("División: " + divisio);
        System.out.println("Módulo: " + modul);
        System.out.println("Exponenciación: " + exponenciacio);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImatgeKenKen = new JLabel(new ImageIcon("resources/imatges/cuadricula 2.png"));
    }
    public JPanel getDefaultPanel() {
        return PanelCreacio;
    }
}
