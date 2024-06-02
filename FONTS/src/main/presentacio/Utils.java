package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe Utils proporciona funcions d'utilitat per a la personalització de la interfície d'usuari.
 *
 * @autor Ermias Valls Mayor
 */
public class Utils {

    public static final Color COLOR_DE_FONS= new Color(11, 3, 138, 255);

    // COLORS BOTONS
    public static final Color COLOR_BOTO_VERMELL = new Color(211, 88, 88, 255);
    public static final Color COLOR_BOTO_VERD = new Color(97, 186, 77, 255);
    public static final Color COLOR_BOTO_BLAU = new Color(78, 152, 193, 255);

    // MIDES BOTONS
    private static final int AMPLADA_BOTO_PETIT = 95;
    private static final int ALTURA_BOTO_PETIT = 20;

    private static final int AMPLADA_BOTO_MITJA = 125;
    private static final int ALTURA_BOTO_MITJA = 27;

    private static final int AMPLADA_BOTO_GRAN = 250;
    private static final int ALTURA_BOTO_GRAN = 50;


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

    /**
     * Assigna un color de fons a un panell.
     *
     * @param panel El panell al qual s'assigna el color de fons.
     */
    public static void assignarColorDeFons(JPanel panel) {
        panel.setBackground(COLOR_DE_FONS);
    }



            /// BOTONS PERSONALITZATS ///

    /**
     * Crea un botó amb vores arrodonides.
     *
     * @return Un JButton amb vores arrodonides.
     */
    private static JButton crearBotoArrodonit() {
        JButton boto = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(getBackground());
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                    g2d.dispose();
                }
                super.paintComponent(g);
            }
        };

        return boto;
    }

    /**
     * Configura un botó amb les especificacions donades.
     *
     * @param boto El botó a configurar.
     * @param amplada L'amplada del botó.
     * @param altura L'altura del botó.
     * @param text El text del botó.
     * @param colorText El color del text del botó.
     * @param colorBoto El color de fons del botó.
     * @return Un JButton configurat segons les especificacions donades.
     */
    private static JButton configurarBoto(JButton boto, int amplada, int altura, String text, Color colorText, Color colorBoto) {
        addListenersButton(boto);
        boto.setText(text);
        boto.setBorderPainted(false);
        Dimension mida = new Dimension(amplada, altura);
        boto.setPreferredSize(mida);
        boto.setMaximumSize(mida);
        boto.setMinimumSize(mida);
        boto.setForeground(colorText);
        boto.setBackground(colorBoto);
        boto.setMargin(new Insets(0, 0, 0, 0));
        return boto;
    }


    /**
     * Afegeix un MouseListener al botó que canvia el color de fons quan el ratolí entra i surt.
     *
     * @param boto El botó al qual s'afegeix el MouseListener.
     */
    public static void addListenersButton(JButton boto) {
        boto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boto.setBackground(boto.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boto.setBackground(boto.getBackground().brighter());
            }
        });
    }

    /**
     * Crea un botó personalitzat amb les especificacions donades.
     *
     * @param amplada L'amplada del botó.
     * @param altura L'altura del botó.
     * @param text El text del botó.
     * @param colorText El color del text del botó.
     * @param colorBoto El color de fons del botó.
     * @param esArrodonit Si el botó ha de tenir vores arrodonides.
     * @return Un JButton personalitzat segons les especificacions donades.
     */
    public static JButton crearBotoPersonalitzat(int amplada, int altura, String text, Color colorText, Color colorBoto, boolean esArrodonit) {
        JButton boto;
        if (esArrodonit) {
            boto = crearBotoArrodonit();
            boto.setOpaque(false);
        }
        else boto = new JButton();

        return configurarBoto(boto, amplada, altura, text, colorText, colorBoto);
    }

    /**
     * Crea un botó de mida petita amb les especificacions donades.
     *
     * @param text El text del botó.
     * @param colorText El color del text del botó.
     * @param colorBoto El color de fons del botó.
     * @param esArrodonit Si el botó ha de tenir vores arrodonides.
     * @return Un JButton de mida gran personalitzat segons les especificacions donades.
     */
    public static JButton crearBotoPetit(String text, Color colorText, Color colorBoto, boolean esArrodonit) {
        JButton boto;
        if (esArrodonit) {
            boto = crearBotoArrodonit();
            boto.setOpaque(false);
        }
        else boto = new JButton();

        return configurarBoto(boto, AMPLADA_BOTO_PETIT, ALTURA_BOTO_PETIT, text, colorText, colorBoto);
    }

    /**
     * Crea un botó de mida mitjana amb les especificacions donades.
     *
     * @param text El text del botó.
     * @param colorText El color del text del botó.
     * @param colorBoto El color de fons del botó.
     * @param esArrodonit Si el botó ha de tenir vores arrodonides.
     * @return Un JButton de mida mitjana personalitzat segons les especificacions donades.
     */
    public static JButton crearBotoMitja(String text, Color colorText, Color colorBoto, boolean esArrodonit) {
        JButton boto;
        if (esArrodonit) {
            boto = crearBotoArrodonit();
            boto.setOpaque(false);
        }
        else boto = new JButton();

        return configurarBoto(boto, AMPLADA_BOTO_MITJA, ALTURA_BOTO_MITJA, text, colorText, colorBoto);
    }

    /**
     * Crea un botó de mida gran amb les especificacions donades.
     *
     * @param text El text del botó.
     * @param colorText El color del text del botó.
     * @param colorBoto El color de fons del botó.
     * @param esArrodonit Si el botó ha de tenir vores arrodonides.
     * @return Un JButton de mida gran personalitzat segons les especificacions donades.
     */
    public static JButton crearBotoGran(String text, Color colorText, Color colorBoto, boolean esArrodonit) {
        JButton boto;
        if (esArrodonit) {
            boto = crearBotoArrodonit();
            boto.setOpaque(false);
        }
        else boto = new JButton();

        return configurarBoto(boto, AMPLADA_BOTO_GRAN, ALTURA_BOTO_GRAN, text, colorText, colorBoto);
    }

}