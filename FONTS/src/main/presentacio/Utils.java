package main.presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * La classe Utils proporciona funcions d'utilitat per a la personalització de la interfície d'usuari.
 *
 * @autor Ermias Valls Mayor
 */
public class Utils {

    /**
     * Carrega una imatge des de la ruta especificada i la redimensiona a l'amplada i l'altura donades.
     *
     * @param ruta La ruta de l'imatge a carregar.
     * @param amplada L'amplada a la qual redimensionar l'imatge.
     * @param altura L'altura a la qual redimensionar l'imatge.
     * @return Una ImageIcon que conté l'imatge redimensionada, o null si hi ha hagut un error en carregar l'imatge.
     */
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