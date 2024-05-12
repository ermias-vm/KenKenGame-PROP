package main.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MissatgePopUp {
    private JPanel panel;
    private JButton comfirmButton;
    private JLabel missatgeComfirm;
    private JPanel panelComplet;
    private JPanel panel2;
    private JButton cancelarButton;
    private JButton acceptarButton;
    private JLabel msgConfirmLabel;
    private JDialog dialog;

    private boolean esCancelat;

    public MissatgePopUp(String msgComfirmacio, JDialog dialog) {
        this.dialog = dialog;
        msgConfirmLabel.setText(msgComfirmacio);
        acceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esCancelat = false;
                dialog.dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esCancelat = true;
                dialog.dispose();

            }
        });
    }

    public boolean esCancelat() {
        return esCancelat;
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }
}
