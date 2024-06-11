package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Aquesta classe representa una casella en el joc de Kenken.
 * Cada casella té una posició (posX, posY), un estat de selecció (seleccionada),
 * un estat que indica si pertany a una regió (pertanyRegio) i un índex de regió (indexRegio).
 * També té un JLabel (infoRegioLabel) que mostra informació sobre la regió a la que pertany la casella.
 *
 * @author Ermias Valls Mayor
 */
public class CasellaConstructora extends JPanel {

    /**
     * Colors i gruixos utilitzats en la representació de la casella.
     * COLOR_SELECCIONADA: Color utilitzat per a la casella quan està seleccionada.
     * COLOR_DEFAULT: Color per defecte de la casella.
     * COLOR_VORA_REGIO: Color de la vora de la casella quan pertany a una regió.
     * COLOR_VORA_DEFAULT: Color per defecte de la vora de la casella.
     * GRUIX_VORA_REGIO: Gruix de la vora de la casella quan pertany a una regió.
     * GRUIX_VORA_DEFAULT: Gruix per defecte de la vora de la casella.
     * DEFAULT_FONT_SIZE: Mida de la font per defecte de la informació de la regió.
     */
    private static final Color COLOR_SELECCIONADA = new Color(150, 255, 238, 255);
    private static final Color COLOR_DEFAULT = new Color(255, 255, 255,255);
    private static final Color COLOR_VORA_REGIO = new Color(0, 0, 0, 255);
    private static final Color COLOR_VORA_DEFAULT = new Color(220, 220, 220, 255);

    private static final int GRUIX_VORA_REGIO = 3;
    private static final int GRUIX_VORA_DEFAULT = 1;
    private static final int DEFAULT_FONT_SIZE = 75;

    /**
     * Etiqueta que mostra informació sobre la regió a la qual pertany la casella.
     */
    JLabel infoRegioLabel;

    /**
     * Posició X de la casella.
     */
    int posX;

    /**
     * Posició Y de la casella.
     */
    int posY;

    /**
     * Indica si la casella està seleccionada.
     */
    private boolean seleccionada;

    /**
     * Indica si la casella pertany a una regió.
     */
    private boolean pertanyRegio;

    /**
     * Índex de la regió a la qual pertany la casella.
     */
    private int indexRegio;

    /**
     * Constructor de la classe CasellaConstructora.
     * Inicialitza la casella amb la posició donada i configura l'estat inicial de la casella.
     * També afegeix un MouseListener a la casella per a gestionar els events de ratolí.
     *
     * @param posX Posició X de la casella
     * @param posY Posició Y de la casella
     */
    public CasellaConstructora(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.setLayout(null);
        configInicial();
        addMouseListener();
    }

    /**
     * Configura l'estat inicial de la casella.
     * L'estat inicial és no seleccionada, no pertany a cap regió, i té l'índex de regió -1.
     * També estableix el color de fons i la vora de la casella als valors per defecte.
     */
    public void configInicial() {
        this.seleccionada = false;
        this.pertanyRegio = false;
        this.indexRegio = -1;
        this.setBorder(BorderFactory.createLineBorder(COLOR_VORA_DEFAULT));
        this.setBackground(COLOR_DEFAULT);
    }

    /**
     * Afegeix un MouseListener a la casella.
     * El MouseListener gestiona els clics del ratolí a la casella.
     * Si es fa clic amb el botó esquerre del ratolí, la casella es selecciona o desselecciona.
     * Si es fa clic amb el botó dret del ratolí i la casella encara no ha sigut assignada a cap regio, es processa
     * l'entrada de l'usuari. Si ja ha sigut assignada es reseteja la configuracio de les caselles de la regio.
     */
    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasellaConstructora casellaPremuda = (CasellaConstructora) e.getSource();
                //Si es fa click amb el boto esquerre del ratoli
                if (SwingUtilities.isLeftMouseButton(e) && !casellaPremuda.teRegioAssignada()) {
                    casellaPremuda.setSeleccionada();
                }
                //Si es fa click amb el boto dret del ratoli
                else if (SwingUtilities.isRightMouseButton(e)) {

                    if (!casellaPremuda.teRegioAssignada() && casellaPremuda.esSeleccionada()) {
                        CrearKenkenManual.getInstance().processarEntradaUsuari(casellaPremuda);
                    }

                    else if (casellaPremuda.teRegioAssignada()) {
                        int confirm = JOptionPane.showConfirmDialog(casellaPremuda, "Estas segur que vols eliminar la regio?", "Confirmar eliminacio", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            TaulerConstrutor.getInstance().eliminarRegioAssignada(casellaPremuda.getIndexRegio());
                        }
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                CasellaConstructora casellaPremuda = (CasellaConstructora) e.getSource();
                if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK && !casellaPremuda.teRegioAssignada() && !casellaPremuda.esSeleccionada()) {
                    casellaPremuda.setSeleccionada();
                }
            }
        });
    }

    /**
     * Retorna si la casella està seleccionada.
     *
     * @return true si la casella està seleccionada, false en cas contrari
     */
    private boolean esSeleccionada() {
        return seleccionada;
    }

    /**
     * Retorna si la casella pertany a una regió.
     *
     * @return true si la casella pertany a una regió, false en cas contrari
     */
    public boolean teRegioAssignada() {
        return pertanyRegio;
    }

    /**
     * Decrementa l'índex de la regió de la casella.
     */
    public void decrementaIndexRegio() {
        this.indexRegio--;
    }

    /**
     * Retorna l'índex de la regió de la casella.
     *
     * @return l'índex de la regió de la casella
     */
    public int getIndexRegio() {
        return this.indexRegio;
    }

    /**
     * Estableix l'estat de selecció de la casella.
     * Si la casella està seleccionada, es desselecciona.
     * Si la casella no està seleccionada i és adjacent a alguna de les caselles selecionades, es selecciona.
     */
    private void setSeleccionada() {
        if (this.seleccionada) {
            this.seleccionada = false;
            this.setBackground(COLOR_DEFAULT);
            TaulerConstrutor.getInstance().eliminarPosCasellaSelecionada(posX, posY);
        } else {
            if (TaulerConstrutor.getInstance().esAdjacent(posX, posY)) {
                this.seleccionada = true;
                this.setBackground(COLOR_SELECCIONADA);
                TaulerConstrutor.getInstance().afegirPosCasellaSelecionada(posX, posY);
            }
        }
    }

    /**
     * Marca la casella com a part d'una regió.
     * Estableix l'índex de la regió de la casella i canvia el color de fons de la casella al color de la regio.
     *
     * @param index l'índex de la regió a la que pertany la casella
     */
    public void marcaComRegio(int index, Color colorRegio) {
        this.pertanyRegio = true;
        this.indexRegio = index;
        this.setBackground(colorRegio);
    }

    /**
     * Desmarca la casella com a part d'una regió.
     * Restableix l'estat inicial de la casella.
     */
    public void desmarcaComRegio() {
        configInicial();
    }

    /**
     * Afegeix informació sobre la regió a la casella.
     * Crea un JLabel amb l'operació i el resultat de la regió, i l'afegeix a la casella.
     *
     * @param operacio l'operació de la regió
     * @param resultat el resultat de la regió
     * @param midaTauler la mida del tauler
     */
    public void addInfoRegio(String operacio, String resultat, int midaTauler,int AMPLADA_TAULER) {
        infoRegioLabel = new JLabel();

        int ampladaCasella = AMPLADA_TAULER/midaTauler;
        int alturaLabel = (int)(ampladaCasella * 0.3);

        infoRegioLabel.setBounds(0, ampladaCasella/8, ampladaCasella, alturaLabel);
        infoRegioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoRegioLabel.setVerticalAlignment(SwingConstants.CENTER);
        // Ajusta la mida de la font en funció de la mida del tauler
        String textInfoRegio = getTextInfoRegio(operacio, resultat, midaTauler);
        infoRegioLabel.setText(textInfoRegio);
        this.add(infoRegioLabel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Retorna el text de l'etiqueta amb l'operació i el resultat de la regió amb la mida de la font ajustada.
     *
     * @param operacio l'operació de la regió
     * @param resultat el resultat de la regió
     * @param midaTauler la mida del tauler
     * @return el text de l'etiqueta amb l'operació i el resultat de la regió amb la mida de la font ajustada
     */
    private static String getTextInfoRegio(String operacio, String resultat, int midaTauler) {
        int fontSizeResultat = DEFAULT_FONT_SIZE / midaTauler;
        int fontSizeOperacio = (int)(fontSizeResultat * 1.5);
        // Assigna el text de l'etiqueta amb l'operació i el resultat de la regió amb la mida de la font ajustada
        return "<html><span style='font-size:" + fontSizeResultat + "px'>" + operacio +
                "</span><span style='font-size:" + fontSizeOperacio+ "px'>" + resultat + "</span></html>";
    }

    /**
     * Estableix el color i gruix de les vores de la casella.
     * Crea un MatteBorder per a cada vora de la casella, amb un gruix i un color que depenen de si la vora és una "vora regio".
     * Després, estableix el borde de la casella com a un CompoundBorder dels quatre bordes creats.
     *
     * @param esVoraRegio un array de booleans que indica si cada vora és una "vora regio"
     */
    void pintarVores (boolean[] esVoraRegio) {
        //esVoraRegio[amunt, esquerra, abaix, dreta]
        int gruixAmunt = esVoraRegio[0] ? GRUIX_VORA_REGIO : GRUIX_VORA_DEFAULT;
        int gruixEsquerra = esVoraRegio[1] ? GRUIX_VORA_REGIO : GRUIX_VORA_DEFAULT;
        int gruixAbaix = esVoraRegio[2] ? GRUIX_VORA_REGIO : GRUIX_VORA_DEFAULT;
        int gruixDreta = esVoraRegio[3] ? GRUIX_VORA_REGIO : GRUIX_VORA_DEFAULT;

        MatteBorder bordeAmunt = new MatteBorder(gruixAmunt, 0, 0, 0, esVoraRegio[0] ? COLOR_VORA_REGIO : COLOR_VORA_DEFAULT);
        MatteBorder bordeEsquerra = new MatteBorder(0, gruixEsquerra, 0, 0, esVoraRegio[1] ? COLOR_VORA_REGIO : COLOR_VORA_DEFAULT);
        MatteBorder bordeAbaix = new MatteBorder(0, 0, gruixAbaix, 0, esVoraRegio[2] ? COLOR_VORA_REGIO : COLOR_VORA_DEFAULT);
        MatteBorder bordeDreta = new MatteBorder(0, 0, 0, gruixDreta, esVoraRegio[3] ? COLOR_VORA_REGIO : COLOR_VORA_DEFAULT);

        this.setBorder(BorderFactory.createCompoundBorder(bordeAmunt, BorderFactory.createCompoundBorder(bordeEsquerra, BorderFactory.createCompoundBorder(bordeAbaix, bordeDreta))));
    }

    /**
     * Elimina la informació de la regió de la casella.
     * Si la casella té un JLabel amb informació de la regió, l'elimina de la casella.
     */
    public void eliminarInfoRegio() {
        if (infoRegioLabel != null) {
            this.remove(infoRegioLabel);
        }
    }

}
