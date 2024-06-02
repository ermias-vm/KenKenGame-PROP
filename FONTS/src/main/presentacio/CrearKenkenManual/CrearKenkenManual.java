package main.presentacio.CrearKenkenManual;
import main.presentacio.CtrlPresentacio;
import main.presentacio.Utils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Classe CrearKenkenManual que permet a l'usuari crear un tauler Kenken de forma manual.
 * L'usuari pot seleccionar el grau del tauler, introduir les operacions i els resultats per a cada regió,
 * i guardar el tauler creat.
 * Aquesta classe utilitza la classe TaulerConstrutor per a la creació del tauler.
 *
 * @author Ermias Valls Mayor
 */
public class CrearKenkenManual {

    private static CrearKenkenManual creardoraKenken;

    private JPanel panelComplet;
    private JPanel panelEsq;
    private JPanel grauPanel;
    private JPanel configPanel;
    private JLabel logoCreateLabel;
    private JLabel grauLabel;
    private JButton guardarButton;
    private JButton sortirButton;
    private JButton aceptarButton;
    private JComboBox grauComboBox;
    private JButton resetButton;
    private JPanel panelDret;
    private JButton importarTaulerButton;
    private JPanel panelSortir;
    private JButton configuracioButton;
    private JPanel panelConfigSortir;
    private JTextArea areaTextIntroduccioTauler;
    private JDialog dialogImportar;
    private JDialog dialogConfiguracio;

    private TaulerConstrutor TaulerKenken;
    private String contingutTaulerKenken;
    private boolean enModeEditor;


    /**
     * Retorna una instància de CrearKenkenManual. Si no existeix, la crea.
     *
     * @return Una instància de CrearKenkenManual.
     */
    public static CrearKenkenManual getInstance() {
        if (creardoraKenken == null) creardoraKenken = new CrearKenkenManual(false);
        return creardoraKenken;
    }

    /**
     * Crea una nova instància de CrearKenkenManual.
     */
    public static void newInstance() {
        creardoraKenken = new CrearKenkenManual(false);
    }
    public static void newInstance(Boolean jugarDespres) {
        creardoraKenken = new CrearKenkenManual(jugarDespres);
    }

    /**
     * Constructor de la classe CrearKenkenManual.
     * Inicialitza els components de la interfície d'usuari i configura els listeners.
     */
    private CrearKenkenManual(Boolean jugarDespres) {
        configInicial();
        setupSortirButtonListener(jugarDespres);
        setupAcceptarButtonListener();
        setupGuardarButtonListener(jugarDespres);
        setupResetButtonListener();
        setupGrauComboBoxListener();
        setupImportarTaulerButtonListener();
        setupConfiguracioButtonListener();
    }

    /**
     * Inicia el mode editor amb un tauler de la mida especificada.
     * Si el tauler és importat, es crea una nova instància de TaulerConstrutor amb el contingut del tauler.
     * Si el tauler no és importat, es crea una nova instància de TaulerConstrutor amb la mida especificada.
     * Es buida el panell esquerre i s'afegeix el tauler al panell esquerre.
     *
     * @param mida La mida del tauler.
     * @param taulerEsImportat Indica si el tauler és importat.
     */
    private void iniciarEditor(int mida, boolean taulerEsImportat) {
        enModeEditor = true;

        importarTaulerButton.setVisible(false);
        aceptarButton.setVisible(false);
        grauLabel.setVisible(false);
        grauComboBox.setVisible(false);
        guardarButton.setVisible(true);
        resetButton.setVisible(true);

        if (taulerEsImportat) {
            System.out.println("Creant tauler importat");
            TaulerConstrutor.newInstance(contingutTaulerKenken);

        } else {
            System.out.println("Creant tauler de mida " + mida);
            TaulerConstrutor.newInstance(mida);
        }
        TaulerKenken = TaulerConstrutor.getInstance();
        panelEsq.removeAll();
        panelEsq.add(TaulerKenken, BorderLayout.CENTER);
    }

    /**
     * Configura l'estat inicial de la pantalla de creació de Kenken.
     * Inicialment previsualitza un tauler de mida 3.
     * En mode editor, mostra els botons de guardar i reiniciar.
     * En mode creació, mostra el botó d'acceptar i el combobox per a seleccionar la mida del tauler, el boto de importar tauler i el boto de sortir.
     *
     */
    public void configInicial () {
        System.out.println("Entrant a la pantalla de crear kenken");
        enModeEditor = false;
        guardarButton.setVisible(false);
        resetButton.setVisible(false);
        configuracioButton.setVisible(true);
        importarTaulerButton.setVisible(true);
        previewTauler(3);
    }


                //// Configuració dels listeners generals///

    /**
     * Configura l'escoltador d'esdeveniments per al botó de sortir.
     * Quan l'usuari fa clic en aquest botó, es comprova si es troba en mode editor i si el tauler ha estat modificat.
     * Si es compleixen aquestes condicions, es mostra un diàleg de confirmació per a que l'usuari confirmi que vol sortir sense guardar.
     * Si l'usuari confirma, es mostra la pantalla de creació de Kenken.
     * Si l'usuari cancel·la, no es realitza cap acció.
     * Si l'usuari no es troba en mode editor, es mostra el menú principal.
     */
    public void setupSortirButtonListener(Boolean jugarDespres){
        sortirButton.addActionListener(e -> {
            if (enModeEditor) {
                if (TaulerKenken.esModificat()) {
                    int dialogResult = JOptionPane.showConfirmDialog(panelComplet, "<html><div style='text-align: center;'>Estàs segur que vols sortir?" +
                            "<br>Si surts sense guardar es perdran els canvis</div></html>", "Avís", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION) {
                        System.out.println("Sortint de crear kenken");
                        if (jugarDespres){
                            CtrlPresentacio.getInstance().initJugar();
                        }
                        else CtrlPresentacio.getInstance().showCrearKenKen();
                    }
                    else {
                        System.out.println("Sortida mode editor cancelada");
                    }
                }
                else {
                    System.out.println("Sortint del mode editor");
                    CtrlPresentacio.getInstance().showCrearKenKen();
                }
            }
            else {
                System.out.println("Sortint de crear kenken");
                if (jugarDespres){
                    CtrlPresentacio.getInstance().initJugar();
                }
                else CtrlPresentacio.getInstance().showMenuPrincipal();
            }
        });

    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó d'acceptar.
     * Quan l'usuari fa clic en aquest botó, es passa a mode editor, es oculten el botó d'acceptar, l'etiqueta de grau i el combobox de grau,
     * es mostren el botó de guardar i el botó de reiniciar, es crea una nova instància de TaulerConstrutor amb la mida seleccionada en el combobox de grau,
     * es buida el panell esquerre i s'afegeix el tauler al panell esquerre.
     */
    public void setupAcceptarButtonListener() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mida = Integer.parseInt((String) grauComboBox.getSelectedItem());
                iniciarEditor(mida,false);
            }
        });
    }
    /**
     * Configura l'escoltador d'esdeveniments per al botó de guardar.
     * Quan l'usuari fa clic en aquest botó, es comprova si totes les caselles del tauler tenen una regió assignada.
     * Si no és així, es mostra un missatge d'error.
     * Si totes les caselles tenen una regió assignada, es comprova si el tauler és vàlid.
     * Si el tauler és vàlid, es guarda el tauler en la base de dades, es mostra un missatge d'informació amb l'identificador del tauler i la ubicació on s'ha guardat,
     * i es mostra el menú principal.
     * Si el tauler no és vàlid, es mostra un missatge d'error.
     */
    public void setupGuardarButtonListener(boolean jugarDespres) {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mida = TaulerKenken.getMida();
                if (TaulerKenken.getNumCasellesAssignades() != mida * mida) {
                    JOptionPane.showMessageDialog(panelComplet, "Hi ha caselles sense regio assignada", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String contingutTauler = TaulerKenken.getContigutTauler();
                    if (CtrlPresentacio.getInstance().esTaulerValid(contingutTauler)) {
                        System.out.println("Tauler Kenken vàlid");
                        String idTauler = CtrlPresentacio.getInstance().guardarTaulerBD(contingutTauler);
                        String missatge = "Tauler guardat amb id: " + idTauler + " en la ubicacio data/taulers/mida" + mida + "/" + idTauler + ".txt";
                        JOptionPane.showMessageDialog(panelComplet, missatge, "Informació", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println(missatge);
                        if (jugarDespres) {
                            CtrlPresentacio.getInstance().jugarIdentificadorTauler(idTauler);
                        } else CtrlPresentacio.getInstance().showCrearKenKen();
                    } else {
                        JOptionPane.showMessageDialog(panelComplet, "El Tauler Kenken no es vàlid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó de reiniciar.
     * Quan l'usuari fa clic en aquest botó, es comprova si el tauler ha estat modificat.
     * Si el tauler ha estat modificat, es mostra un diàleg de confirmació per a que l'usuari confirmi que vol reiniciar la creació del Kenken.
     * Si l'usuari confirma, es reinicia el tauler.
     */
    public void setupResetButtonListener() {
        resetButton.addActionListener(e -> {
            if (TaulerKenken.esModificat()) {
                int dialogResult = JOptionPane.showConfirmDialog(panelComplet, "Estas segur que vols reiniciar la creacio del Kenken?", "Comfirmacio", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    TaulerKenken.resetTauler();
                }
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al combobox de grau.
     * Quan l'usuari selecciona un element en aquest combobox, es buida el panell esquerre i es mostra una previsualització del tauler amb la mida seleccionada.
     */
    public void setupGrauComboBoxListener() {
        grauComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt((String) Objects.requireNonNull(grauComboBox.getSelectedItem()));
                panelEsq.removeAll();
                previewTauler(size);
            }
        });
    }




                //// Listeners importacio tauler ////

    /**
     * Configura l'escoltador d'esdeveniments per al botó d'acceptar de la finestra d'importació de tauler.
     * Quan l'usuari fa clic en aquest botó, es comprova si el contingut del tauler és vàlid.
     * Si el contingut del tauler no és vàlid, es mostra un missatge d'error.
     * Si el contingut del tauler és vàlid, es tanca la finestra d'importació i es passa a mode editor.
     */
    private void afegirListenerBotoAcceptarImportacio(JButton botoAcceptarImportacio) {
        botoAcceptarImportacio.addActionListener(e -> {
            contingutTaulerKenken = areaTextIntroduccioTauler.getText();
            String error = validarContingutTauler();
            if (error != null) {
                JOptionPane.showMessageDialog(panelComplet, error, "Format del Tauler incorrecte", JOptionPane.ERROR_MESSAGE);
            } else {
                dialogImportar.dispose();
                iniciarEditor(0, true);
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó de sortir de la finestra d'importació de tauler.
     * Quan l'usuari fa clic en aquest botó, es comprova si l'usuari ha introduït contingut en el JTextArea.
     * Si el JTextArea està buit, es tanca la finestra d'importació.
     * Si el JTextArea no està buit, es mostra un diàleg de confirmació per a que l'usuari confirmi que vol sortir sense guardar.
     * Si l'usuari confirma, es tanca la finestra d'importació.
     */
    private void afegurirListenerBotoSortirImportacio(JButton botoSortirImportacio) {
        botoSortirImportacio.addActionListener(e -> {

            if (areaTextIntroduccioTauler == null || areaTextIntroduccioTauler.getText().isEmpty()) {
                dialogImportar.dispose();

            } else {
                int dialogResult = JOptionPane.showConfirmDialog(panelComplet, "<html><div style='text-align: center;'>Estàs segur que vols sortir?" +
                        "<br>Si surts sense guardar es perdran els canvis</div></html>", "Avís", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) dialogImportar.dispose();
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó d'importar tauler.
     * Quan l'usuari fa clic en aquest botó, es mostra un diàleg per a que l'usuari seleccioni un tauler del dispositiu.
     * Si l'usuari selecciona un tauler, es llegeix el contingut del fitxer i es mostra en un JTextArea.

     */
    private void setupImportarTaulerButtonListener() {
        importarTaulerButton.addActionListener(e -> {
            JPanel panelImportarTaulers = crearPanelImportarTaulers();
            JButton botoSeleccionaTauler = crearBotoSeleccionaTauler();
            JLabel etiquetaIntroduccioTauler = crearEtiquetaIntroduccioTauler();
            areaTextIntroduccioTauler = crearAreaTextIntroduccioTauler();
            JPanel panelBotons = crearPanelBotons();

            afegirComponentsAPanel(panelImportarTaulers, botoSeleccionaTauler, etiquetaIntroduccioTauler, areaTextIntroduccioTauler, panelBotons);
            mostrarDialogImportacio(panelImportarTaulers);
        });
    }



                //// Metodes importacio tauler ////

    /**
     * Crea un panell per a importar taulers.
     * @return Un panell per a importar taulers.
     */
    private JPanel crearPanelImportarTaulers() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    /**
     * Crea un botó per a seleccionar un tauler del dispositiu.
     * @return Un botó per a seleccionar un tauler del dispositiu.
     */
    private JButton crearBotoSeleccionaTauler() {
        JButton botoSeleccionaTauler =  Utils.crearBotoPersonalitzat(250, 30,"Selecciona tauler del dispositiu", Color.WHITE, Utils.COLOR_BOTO_BLAU,true);
        botoSeleccionaTauler.setAlignmentX(Component.CENTER_ALIGNMENT);
        botoSeleccionaTauler.addActionListener(e -> seleccionarTauler());
        return botoSeleccionaTauler;
    }

    /**
     * Crea una etiqueta per a indicar a l'usuari que pot introduir el tauler manualment.
     * @return Una etiqueta
     */
    private JLabel crearEtiquetaIntroduccioTauler() {
        JLabel etiquetaIntroduccioTauler = new JLabel("Introdueix tauler manualment:");
        etiquetaIntroduccioTauler.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaIntroduccioTauler;
    }

    /**
     * Crea un JTextArea per a que l'usuari pugui introduir el tauler manualment.
     * @return Un JTextArea
     */
    private JTextArea crearAreaTextIntroduccioTauler() {
        return new JTextArea(10, 10);
    }

    /**
     * Crea un panell amb els botons d'acceptar i sortir i comfigura els seus listeners.
     * @return Un panell amb els botons d'acceptar i sortir.
     */
    private JPanel crearPanelBotons() {
        JButton botoAcceptarImportacio = Utils.crearBotoMitja("Acceptar", Color.WHITE, Utils.COLOR_BOTO_VERD,true);
        JButton botoSortirImportacio = Utils.crearBotoMitja("Sortir", Color.WHITE, Utils.COLOR_BOTO_VERMELL,true);

        afegirListenerBotoAcceptarImportacio(botoAcceptarImportacio);
        afegurirListenerBotoSortirImportacio(botoSortirImportacio);

        JPanel panelBotons = new JPanel();
        panelBotons.setLayout(new BoxLayout(panelBotons, BoxLayout.X_AXIS));
        panelBotons.add(botoAcceptarImportacio);
        panelBotons.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBotons.add(botoSortirImportacio);
        panelBotons.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panelBotons;
    }

    /**
     * Afegeix tost els components per a la importacio de taulers al panell corresponent.
     * @param panelImportacio El panell d'importació de taulers.
     * @param botoSeleccionaTauler El botó per a seleccionar un tauler del dispositiu.
     * @param etiquetaIntroduccioTauler L'etiqueta per a indicar a l'usuari que pot introduir el tauler manualment.
     * @param areaTextIntroduccioTauler El JTextArea per a que l'usuari pugui introduir el tauler manualment.
     * @param panelBotons El panell amb els botons d'acceptar i sortir.
     */
    private void afegirComponentsAPanel(JPanel panelImportacio, JButton botoSeleccionaTauler, JLabel etiquetaIntroduccioTauler, JTextArea areaTextIntroduccioTauler, JPanel panelBotons) {
        panelImportacio.add(Box.createRigidArea(new Dimension(0, 45)));
        panelImportacio.add(botoSeleccionaTauler);
        panelImportacio.add(Box.createRigidArea(new Dimension(0, 20)));
        panelImportacio.add(etiquetaIntroduccioTauler);
        panelImportacio.add(Box.createRigidArea(new Dimension(0, 10)));
        panelImportacio.add(new JScrollPane(areaTextIntroduccioTauler));
        panelImportacio.add(Box.createRigidArea(new Dimension(0, 40)));
        panelImportacio.add(panelBotons);
        panelImportacio.add(Box.createRigidArea(new Dimension(0, 60)));
    }

    /**
     * Elimina els valors fixes de les caselles  del tauler.
     * @return El contingut del tauler sense els valors fixes.
     */
    private void seleccionarTauler() {
        JFileChooser selectorArxius = new JFileChooser(new File("data/taulers"));
        int valorRetornat = selectorArxius.showOpenDialog(null);
        if (valorRetornat == JFileChooser.APPROVE_OPTION) {
            File arxiuSeleccionat = selectorArxius.getSelectedFile();
            try {
                // Llegeix el contingut del fitxer seleccionat, elimina els valors fixes  i el mostra en el JTextArea
                String contingutArxiu = new String(Files.readAllBytes(arxiuSeleccionat.toPath()));
                areaTextIntroduccioTauler.setText(eliminarValorsFixes(contingutArxiu));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mostra el diàleg d'importació de tauler.
     * Aques es visualitza davanter de la finestra principal i es bloqueja fins que l'usuari tanqui el diàleg.
     */
    private void mostrarDialogImportacio(JPanel panel) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(panelComplet.getSize());

        panel.setBounds(0, 0, panelComplet.getWidth() / 3, panelComplet.getHeight());
        layeredPane.add(panel, JLayeredPane.POPUP_LAYER);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelComplet);
        dialogImportar = new JDialog(frame, "Importar", JDialog.ModalityType.MODELESS);
        dialogImportar.setUndecorated(true);
        dialogImportar.setContentPane(layeredPane);
        dialogImportar.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        Dimension midaPanelPrincipal = panelComplet.getSize();
        dialogImportar.setSize(midaPanelPrincipal.width / 3, midaPanelPrincipal.height);
        Point ubicacioPanelPrincipal = panelComplet.getLocationOnScreen();
        int x = ubicacioPanelPrincipal.x + midaPanelPrincipal.width - dialogImportar.getSize().width;
        dialogImportar.setLocation(x, ubicacioPanelPrincipal.y);

        dialogImportar.setVisible(true);
        dialogImportar.toFront();
        dialogImportar.requestFocus();
    }

    /**
     * Es valida el format del contigut importat o introduit manualment.
     * @return Unn missatge d'error si el format no és vàlid, null altrament.
     */
    private String validarContingutTauler() {
        if(contingutTaulerKenken == null || contingutTaulerKenken.isEmpty()) {
            return "<html><div style='text-align: center;'>El contingut del tauler es buit." +
                    "<br>Si us plau, selecioni un tauler o introduexi les dades manualment.</div></html>";
        }

        String[] linies = contingutTaulerKenken.split("\n");
        if (linies.length < 2) {
            return "<html><div style='text-align: center;'>El contingut del tauler no té suficients línies" +
                    "<br>Si us plau, reviseu el format.</div></html>";
        }

        String[] primeraLinia = linies[0].split(" ");
        if (primeraLinia.length != 2) {
            return "<html><div style='text-align: center;'>Error en la primera línia:" +
                    "<br>Ha de contenir exactament dos números enters: [midaTauler numeroRegions]</div></html>";
        }

        int mida = Integer.parseInt(primeraLinia[0]);
        int numRegions = Integer.parseInt(primeraLinia[1]);

        if (mida < 3 || mida > 9) {
            return "<html><div style='text-align: center;'>Error en la primera linea:" +
                    "<br>La mida del tauler ha de ser un número natural en l'interval [3, 9].</div></html>";
        }

        if (numRegions < 1 || numRegions > mida*mida) {
            return "<html><div style='text-align: center;'>Error en la primera linea:" +
                    "<br>El nombre de regions ha de ser un número natural en l'interval: [1,"+ mida*mida +"].</div></html>";
        }

        //Itera sobre les regions i comprova que tinguin el format correcte
        for (int i = 1; i < linies.length; i++) {
            String[] parts = linies[i].split(" ");
            if (parts.length < 5) {
                return "<html><div style='text-align: center;'>Error en la linea: " + (i+1) +
                        "<br>El format de cada línia ha de ser: [operacio resultat numCasellesRegio posX1 posY1 ... posXn posYn].</div></html>";
            }

            int operacio = Integer.parseInt(parts[0]);
            int resultat = Integer.parseInt(parts[1]);
            int numCasellesRegio = Integer.parseInt(parts[2]);

            if (operacio < 0 || operacio > 6) {
                return "<html><div style='text-align: center;'>Error en la linea: " + (i+1) +
                        "<br>El valor de l'operació ha de ser un número enter en l'interval [0, 6].</div></html>";
            }

            if (resultat < 0) {
                return "<html><div style='text-align: center;'>Error en la linea: " + (i+1) +
                        "<br>El valor del resultat ha de ser un número enter major o igual a 0.</div></html>";
            }

            if (numCasellesRegio < 1 || numCasellesRegio > mida*mida) {
                return "<html><div style='text-align: center;'>Error en la linea: " + (i+1)+
                        "<br>El valor de numCasellesRegio ha de ser un número natural en l'interval: [1,"+ mida*mida +"].</div></html>";
            }

            if (parts.length != 3 + 2*numCasellesRegio) {
                return "<html><div style='text-align: center;'>Error en la linea: " + (i+1) +
                        "<br>Reviseu el nombre de posicions de la regió.</div></html>";
            }

            for (int j = 3; j < parts.length; j++) {
                int pos = Integer.parseInt(parts[j]);
                if (pos < 1 || pos > mida) {
                    return "<html><div style='text-align: center;'>Error en la linea: " + i +
                            "<br>Cada posició ha de ser un número natural en l'interval: [1,"+ mida +"].</div></html>";
                }
            }
        }

        if (linies.length != numRegions + 1) {
            return "<html><div style='text-align: center;'>El nombre de regions no coincideix amb el valor numRegions " +
                    "especificat a la primera línia.</div></html>";
        }

        return null;
    }


                //// Metodes Dialog de configuracó ////

    /**
     * Configura l'escoltador d'esdeveniments per al botó de configuració.
     * Quan l'usuari fa clic en aquest botó, es crea un panell de configuració i es mostra en un diàleg.
     */
    public void setupConfiguracioButtonListener() {
        configuracioButton.addActionListener(e -> {
            JPanel panelConfiguracio = crearPanelConfiguracio();
            mostrarDialogConfiguracio(panelConfiguracio);
        });
    }

    /**
     * Crea un panell de configuració amb botons per validar resultats i colorejar regions.
     * També inclou un botó de sortida per tancar el diàleg de configuració.
     *
     * @return Un panell de configuració.
     */
    public JPanel crearPanelConfiguracio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelValidar = new JLabel("Validar Resultats: ");
        JButton botoValidar = getValidarButton();

        JPanel panelValidar = new JPanel();
        panelValidar.setLayout(new BoxLayout(panelValidar, BoxLayout.X_AXIS));
        panelValidar.add(labelValidar);
        panelValidar.add(botoValidar);

        JLabel labelColorejar = new JLabel("Colorejar regions: ");
        JButton botoColorejar = getColorejarButton();

        JPanel panelColorejar = new JPanel();
        panelColorejar.setLayout(new BoxLayout(panelColorejar, BoxLayout.X_AXIS));
        panelColorejar.add(labelColorejar);
        panelColorejar.add(botoColorejar);

        // Crear botó de sortida
        JButton botoSortir = Utils.crearBotoMitja("Sortir", Color.WHITE, Utils.COLOR_BOTO_VERMELL,true);
        botoSortir.addActionListener(e -> dialogConfiguracio.dispose());

        JPanel panelSortir = new JPanel();
        panelSortir.setLayout(new BoxLayout(panelSortir, BoxLayout.X_AXIS));
        panelSortir.add(Box.createHorizontalGlue());
        panelSortir.add(botoSortir);
        panelSortir.add(Box.createHorizontalGlue());

        panel.add(Box.createRigidArea(new Dimension(0, 300)));
        panel.add(panelValidar);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(panelColorejar);
        panel.add(Box.createRigidArea(new Dimension(0, 60)));
        panel.add(panelSortir);

        return panel;
    }

    /**
     * Crea un botó per a la configuració de colorejar regions.
     * El botó mostra l'estat actual de la configuració i permet canviar-lo.
     *
     * @return Un botó per a la configuració de colorejar regions.
     */
    private JButton getColorejarButton() {
        JButton botoColorejar = Utils.crearBotoPetit(null, Color.BLACK, null,false);

        actualitzarEstatBoto(botoColorejar, CtrlPresentacio.getInstance().getConfigColorejarRegions());
        botoColorejar.addActionListener(e -> botoColorejarAction(botoColorejar));
        return botoColorejar;
    }

    /**
     * Crea un botó per a la configuració de validar resultats.
     * El botó mostra l'estat actual de la configuració i permet canviar-lo.
     *
     * @return Un botó per a la configuració de validar resultats.
     */
    private JButton getValidarButton() {
        JButton botoValidar = Utils.crearBotoPetit(null, Color.BLACK, null,false);

        actualitzarEstatBoto(botoValidar, CtrlPresentacio.getInstance().getConfigValidarResultats());
        botoValidar.addActionListener(e -> botoValidarAction(botoValidar));
        return botoValidar;
    }

    /**
     * Canvia l'estat de la configuració de colorejar regions quan l'usuari fa clic en el botó corresponent.
     * També actualitza l'aparença del botó per reflectir el nou estat.
     *
     * @param botoColorejar El botó de la configuració de colorejar regions.
     */
    private void botoColorejarAction(JButton botoColorejar) {
        boolean estatActual =  CtrlPresentacio.getInstance().getConfigColorejarRegions();
        CtrlPresentacio.getInstance().setConfigColorejarRegions(!estatActual);
        actualitzarEstatBoto(botoColorejar, !estatActual);

        if (enModeEditor) TaulerKenken.repintarTaulerActual();
    }

    /**
     * Canvia l'estat de la configuració de validar resultats quan l'usuari fa clic en el botó corresponent.
     * També actualitza l'aparença del botó per reflectir el nou estat.
     *
     * @param botoValidar El botó de la configuració de validar resultats.
     */
    private void botoValidarAction(JButton botoValidar) {
        boolean estatActual = CtrlPresentacio.getInstance().getConfigValidarResultats();
        CtrlPresentacio.getInstance().setConfigValidarResultats(!estatActual);
        actualitzarEstatBoto(botoValidar, !estatActual);
    }

    /**
     * Actualitza l'aparença d'un botó de configuració per reflectir l'estat actual de la configuració.
     * Si la configuració està activada, el botó es mostra en verd i amb el text "Activat".
     * Si la configuració està desactivada, el botó es mostra en rosa i amb el text "Desactivat".
     *
     * @param boto El botó de configuració a actualitzar.
     * @param estat L'estat actual de la configuració.
     */
    private void actualitzarEstatBoto(JButton boto, boolean estat) {
        if (estat) {
            boto.setText("Activat");
            boto.setBackground(Utils.COLOR_BOTO_VERD);
        } else {
            boto.setText("Desactivat");
            boto.setBackground(Utils.COLOR_BOTO_VERMELL);
        }
    }


    /**
     * Mostra un diàleg de configuració amb un panell de configuració.
     * El diàleg es mostra en una nova finestra que es col·loca a la dreta de la finestra principal.
     *
     * @param panelConfig El panell de configuració a mostrar en el diàleg.
     */
    private void mostrarDialogConfiguracio(JPanel panelConfig) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(panelComplet.getSize());

        panelConfig.setBounds(0, 0, panelComplet.getWidth() / 3, panelComplet.getHeight());
        layeredPane.add(panelConfig, JLayeredPane.POPUP_LAYER);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelComplet);
        dialogConfiguracio = new JDialog(frame, "Configuració", JDialog.ModalityType.MODELESS);
        dialogConfiguracio.setUndecorated(true);
        dialogConfiguracio.setContentPane(layeredPane);
        dialogConfiguracio.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        Dimension midaPanelPrincipal = panelComplet.getSize();
        dialogConfiguracio.setSize(midaPanelPrincipal.width / 3, midaPanelPrincipal.height);
        Point ubicacioPanelPrincipal = panelComplet.getLocationOnScreen();
        int x = ubicacioPanelPrincipal.x + midaPanelPrincipal.width - dialogConfiguracio.getSize().width;
        dialogConfiguracio.setLocation(x, ubicacioPanelPrincipal.y);

        dialogConfiguracio.setVisible(true);
        dialogConfiguracio.toFront();
        dialogConfiguracio.requestFocus();
    }




                //// Metodes creació regió ////

    /**
     * Processa l'entrada de l'usuari quan es prem una casella.
     * Mostra un diàleg per a que l'usuari introdueixi l'operació i el resultat per a la regió seleccionada.
     *
     * @param casellaPremuda La casella que l'usuari ha premut.
     */
    public void processarEntradaUsuari(CasellaConstructora casellaPremuda) {
        JPanel operacioPanel = this.crearOperacioPanel();
        JPanel resultatPanel = this.crearResultatPanel();
        int opcio;
        String error = null;

        do {
            opcio = this.mostrarDialogCreacioRegio(operacioPanel, resultatPanel, error, casellaPremuda);

            if (opcio == JOptionPane.OK_OPTION) {
                String operacio = this.obtenerOperacioSeleccionada(operacioPanel);
                String resultat = ((JTextField)resultatPanel.getComponent(1)).getText();
                error = TaulerConstrutor.getInstance().validarEntrada(operacio, resultat);
                if (error == null) {
                    TaulerConstrutor.getInstance().assignarCasellesRegio(operacio, resultat);
                }
            }
        } while (opcio == JOptionPane.OK_OPTION && error != null);
    }

    /**
     * Mostra un diàleg per a que l'usuari introdueixi l'operació i el resultat.
     * El diàleg conté un panel amb els botons de les operacions i un panel amb el camp de text per al resultat.
     * Si hi ha un missatge d'error, es mostra un diàleg d'error.
     *
     * @param operacioPanel El panel que conté els botons de les operacions.
     * @param resultatPanel El panel que conté el camp de text per al resultat.
     * @param errorMessage El missatge d'error a mostrar, si n'hi ha.
     * @param casellaPremuda La casella que l'usuari ha premut.
     * @return El valor de l'opció seleccionada en el diàleg.
     */
    public int mostrarDialogCreacioRegio(JPanel operacioPanel, JPanel resultatPanel, String errorMessage, CasellaConstructora casellaPremuda) {
        Box.Filler separacio = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10));
        Object[] message = {
                "Operació:", operacioPanel,
                separacio,
                resultatPanel
        };

        JOptionPane optionPane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(casellaPremuda, "Introdueix la operació i el resultat");
        dialog.setSize(new Dimension(280, 240));

        if (errorMessage != null) {
            JOptionPane.showMessageDialog(casellaPremuda, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }

        dialog.setVisible(true);
        return optionPane.getValue() == null ? JOptionPane.CANCEL_OPTION : (int) optionPane.getValue();
    }

    /**
     * Crea un panel amb els botons de les operacions.
     * Tots els botons estan agrupats en un ButtonGroup per a que només es pugui seleccionar un.
     *
     * @return Un panel amb els botons de les operacions.
     */
    public JPanel crearOperacioPanel() {
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
        return operacioPanel;
    }

    /**
     * Crea un panel amb el camp de text per al resultat.
     * Limita la longitud del text a 9 caràcters i només permet introduir dígits.
     * @return Un panel amb el camp de text per al resultat.
     */
    public JPanel crearResultatPanel() {
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
        return resultatPanel;
    }

    /**
     * Obté l'operació seleccionada en el panel d'operacions.
     *
     * @param operacioPanel El panel que conté els botons de les operacions.
     * @return L'operació seleccionada.
     */
    public String obtenerOperacioSeleccionada(JPanel operacioPanel) {
        String operacio = "SUMA";
        for (Component comp : operacioPanel.getComponents()) {
            if (comp instanceof JRadioButton) {
                JRadioButton button = (JRadioButton) comp;
                if (button.isSelected()) {
                    operacio = button.getText();
                    break;
                }
            }
        }
        return operacio;
    }

                //// Altres Metodes ////

    /**
     * Mostra una previsualització del tauler amb la mida especificada.
     * Cada casella del tauler es representa com un JPanel cuadrat amb vores grises.
     *
     * @param size La mida del tauler a previsualitzar.
     */
    public void previewTauler (int size) {
        JPanel taulerPreview = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JPanel cuadrat = new JPanel();
                cuadrat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cuadrat.setBackground(Color.GRAY);
                taulerPreview.add(cuadrat);
            }
        }
        panelEsq.add(taulerPreview, BorderLayout.CENTER);
        panelEsq.validate();
    }

    /**
     * Elimina els valors fixos del contingut del tauler.
     *
     * @param contingutTauler El contingut del tauler.
     * @return El contingut del tauler sense els valors fixos.
     */
    private String eliminarValorsFixes(String contingutTauler) {
        String[] dadesTauler = contingutTauler.split("\n");
        for (int i = 0; i < dadesTauler.length; i++) {
            dadesTauler[i] = dadesTauler[i].replaceAll(" \\[\\d+\\]", "").trim();
        }
        return String.join("\n", dadesTauler);
    }

    /**
     * Retorna el panel principal de la pantalla de creació de Kenken.
     *
     * @return El panel principal de la pantalla de creació de Kenken.
     */
    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    /**
     *
     * Crea els components de la interfície d'usuari que no es poden crear en el dissenyador de formularis.
     * Aquest metode es crida automaticament duratn la inicialització de la interficie d'usuari.
     */
    private void createUIComponents() {

        logoCreateLabel = new JLabel(Utils.carregarImatge("resources/imatges/logoKenkenCreador.png", 320, 320));

        configuracioButton  =  Utils.crearBotoMitja("Configuració", Color.WHITE, Utils.COLOR_BOTO_BLAU,true);
        importarTaulerButton = Utils.crearBotoMitja("ImportarTauler", Color.WHITE, Utils.COLOR_BOTO_BLAU,true);
        resetButton = Utils.crearBotoMitja("Reiniciar", Color.WHITE, Utils.COLOR_BOTO_BLAU,true);
        aceptarButton = Utils.crearBotoMitja("Acceptar", Color.WHITE, Utils.COLOR_BOTO_VERD,true);
        guardarButton = Utils.crearBotoMitja("Guardar", Color.WHITE, Utils.COLOR_BOTO_VERD,true);
        sortirButton = Utils.crearBotoMitja("Sortir", Color.WHITE, Utils.COLOR_BOTO_VERMELL,true);
    }

}
