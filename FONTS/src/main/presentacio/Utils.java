package main.presentacio;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static ImageIcon carregarImatge(String ruta, int amplada, int altura) {
        try {
            ImageIcon imageIcon = new ImageIcon(ruta);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(amplada, altura, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.out.println("Error al carregar la imatge: " + e.getMessage());
            e.printStackTrace();
            return null;
        }


    }



}