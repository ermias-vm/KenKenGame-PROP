package main.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearKenKenParametres extends JDialog {
    //private JTextField textGrau;
    private JButton Crea;
    private JButton Cancela;
    private JPanel PanelCreacio;
    private JCheckBox Suma;
    private JCheckBox Resta;
    private JCheckBox Multiplicacio;
    private JCheckBox Divisio;
    private JCheckBox Modul;
    private JCheckBox Exponenciacio;
    private JLabel ImatgeKenKen;
    private JComboBox ComboBoxGrau;

    public CrearKenKenParametres() {
        System.out.println("Entrant a la pantalla de generar kenken");
        Crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int grau = Integer.parseInt("" + ComboBoxGrau.getSelectedItem());
                boolean suma = Suma.isSelected();
                boolean resta = Resta.isSelected();
                boolean multiplicacio = Multiplicacio.isSelected();
                boolean divisio = Divisio.isSelected();
                boolean modul = Modul.isSelected();
                boolean exponenciacio = Exponenciacio.isSelected();

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

        //Aixo treu error si no ha seleccionat cap operacio
        if(!suma && !resta && !multiplicacio && !divisio && !modul && !exponenciacio){
            JOptionPane.showMessageDialog(this,
                    "Siusplau seleccionar minim una operacio",
                    "Torna-ho a provar",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aqui, tocara cridar a CreadorKenkenParam.java quan el codi estigui acabat
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
        ImatgeKenKen = new JLabel(new ImageIcon("resources/imatges/cuadricula.png"));
    }
    public JPanel getDefaultPanel() {
        return PanelCreacio;
    }
}
