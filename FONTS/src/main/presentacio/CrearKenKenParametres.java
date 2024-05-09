package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearKenKenParametres extends JDialog {
    private JTextField textGrau;
    private JTextField textNOp;
    private JTextField textOperacions;
    private JTextPane Operacions;
    private JButton Crea;
    private JButton Cancela;
    private JPanel PanelCreacio;

    public CrearKenKenParametres(JFrame parent){
        super(parent);
        setTitle("Create new KenKen");
        setContentPane(PanelCreacio);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createParametres();
            }
        });
        Cancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void createParametres() {
        String Grau = textGrau.getText();
        String NumOp = textNOp.getText();
        String Operacions = textOperacions.getText();

        if(Grau.isEmpty() || NumOp.isEmpty() || Operacions.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Plena tots els espais siusplau",
                    "Torna a provar",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int o = Operacions.length()*2 -1;
        if(!NumOp.equals(o)){
            JOptionPane.showMessageDialog(this,
                    "Les operacions entrades no coincideixen amb el nombre d'operacions que has acordat",
                    "Torna a provar",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    public static void main(String[] args) {
        CrearKenKenParametres myForm = new CrearKenKenParametres(null);
    }
}
