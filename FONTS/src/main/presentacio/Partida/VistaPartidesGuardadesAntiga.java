//
//        package main.presentacio.Partida;
//
//        import main.presentacio.ObservadorBoto;
//
//        import javax.swing.*;
//        import java.awt.*;
//        import java.util.ArrayList;
//
//        /**
//         * {@code VistaPartidesGuardades} és la vista de les partides guardades d'un usuari,
//         * per a seleccionar-ne una i jugar-la.
//         * En una pàgina s'ensenya la mida, data i temps de les (int) NOMBREPARTIDES_ partides guardades de l'usuari,
//         * i es pot navegar entre pàgines.
//         */
//        public class VistaPartidesGuardadesAntiga extends JPanel {
//            /**
//             * Nombre de partides a mostrar per pantalla.
//             */
//            private final int NOMBREPARTIDES_ = 10;
//            /**
//             * Observadors dels botons de la vista.
//             */
//            private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
//            /**
//             * Array de partides guardades de l'usuari.
//             */
//            private String[] partidesGuardades_;
//            /**
//             * Index actual de les partides guardades. En packs de NOMBREPARTIDES_.
//             */
//            private int indexActual_ = 0;
//            /**
//             * Màxim index de les partides guardades.
//             */
//            private int maxIndex_ = 0;
//            /**
//             * Botons de les partides guardades.
//             */
//            private JButton[] botonsPartida_;
//            /**
//             * Labels de les partides guardades.
//             */
//            private JLabel[] labelsPartida_;
//            /**
//             * Botons per a navegar entre les partides guardades, tirant enrere una pàgina de NOMBREPARTIDES_.
//             */
//            private JButton previousN_;
//            /**
//             * Botons per a navegar entre les partides guardades, tirant endavant una pàgina NOMBREPARTIDES_.
//             */
//            private JButton nextN_;
//
//            /**
//             * Crea una vista de les partides guardades amb les partides guardades de l'usuari.
//             * @param partidesGuardades Array de partides guardades de l'usuari.
//             */
//            VistaPartidesGuardadesAntiga(String[] partidesGuardades) {
//                partidesGuardades_ = partidesGuardades;
//                maxIndex_ = partidesGuardades.length / NOMBREPARTIDES_;
//                if (partidesGuardades.length % NOMBREPARTIDES_ == 0) maxIndex_ -= 1;
//                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//                JLayeredPane layeredPane = new JLayeredPane();
//                JPanel panelText = new JPanel();
//                panelText.setLayout(new GridLayout(NOMBREPARTIDES_, 3));
//                JPanel panelButons = new JPanel();
//                panelButons.setLayout(new GridLayout(NOMBREPARTIDES_, 1));
//                panelButons.setBorder(BorderFactory.createTitledBorder("Partides Guardades"));
//                JButton[] botonsPartida = new JButton[NOMBREPARTIDES_];
//                JLabel[] labelsPartida = new JLabel[NOMBREPARTIDES_ *3];
//                for (int i = 0; i < NOMBREPARTIDES_; ++i) {
//                    botonsPartida[i] = new JButton();
//                    labelsPartida[i*3] = new JLabel();
//                    labelsPartida[i*3 + 1] = new JLabel();
//                    labelsPartida[i*3 + 2] = new JLabel();
//                    if (i < partidesGuardades.length) {
//                        botonsPartida[i].setText("");
//                        String[] midadatatemps = generaMidaDataTemps(partidesGuardades[i]);
//                        labelsPartida[i*3].setText(midadatatemps[0]);
//                        labelsPartida[i*3 + 1].setText(midadatatemps[1]);
//                        labelsPartida[i*3 + 2].setText(midadatatemps[2]+" s");
//                    }
//                    else botonsPartida[i].setVisible(false);
//                    botonsPartida[i].setOpaque(false);
//                    botonsPartida[i].setContentAreaFilled(false);
//                    botonsPartida[i].addActionListener(e -> {
//                        JButton sourceButton = (JButton) e.getSource();
//                        int indexBoto = -1;
//                        for (int j = 0; j < NOMBREPARTIDES_; ++j) {
//                            if (botonsPartida[j] == sourceButton) {
//                                indexBoto = j;
//                                break;
//                            }
//                        }
//                        for (ObservadorBoto ob : observadorsBoto_) ob.carregaPartida(partidesGuardades_[indexActual_ * NOMBREPARTIDES_ + indexBoto]);
//                    });
//                    panelButons.add(botonsPartida[i]);
//                    panelText.add(labelsPartida[i*3]);
//                    panelText.add(labelsPartida[i*3 + 1]);
//                    panelText.add(labelsPartida[i*3 + 2]);
//                }
//                layeredPane.add(panelButons, JLayeredPane.DEFAULT_LAYER);
//                layeredPane.add(panelText, JLayeredPane.PALETTE_LAYER);
//                botonsPartida_ = botonsPartida;
//                labelsPartida_ = labelsPartida;
//                JButton previousN = new JButton(NOMBREPARTIDES_ + " anteriors");
//                previousN.addActionListener(e -> {
//                    previousN();
//                });
//                previousN.setEnabled(false);
//                previousN_ = previousN;
//                JButton nextN = new JButton(NOMBREPARTIDES_ + " següents");
//                nextN.addActionListener(e -> {
//                    nextN();
//                });
//                nextN_ = nextN;
//                JButton tornar = new JButton("Tornar");
//                tornar.addActionListener(e -> {
//                    for (ObservadorBoto ob : observadorsBoto_) ob.tornarMenu();
//                });
//                this.add(layeredPane);
//                this.add(previousN);
//                this.add(nextN);
//                this.add(Box.createHorizontalGlue());
//                this.add(tornar);
//            }
//
//            /**
//             * Actualitza la vista de les partides guardades, mostrant les anteriors NOMBREPARTIDES_ partides.
//             */
//            private void previousN() {
//                indexActual_ -= 1;
//                if (indexActual_ == 0) previousN_.setEnabled(false);
//                if (indexActual_ < maxIndex_) nextN_.setEnabled(true);
//                for (int i = 0; i < NOMBREPARTIDES_; ++i) {
//                    if (indexActual_ * NOMBREPARTIDES_ + i < partidesGuardades_.length){
//                        String [] midadatatemps = generaMidaDataTemps(partidesGuardades_[indexActual_ * NOMBREPARTIDES_ + i]);
//                        labelsPartida_[i*3].setText(midadatatemps[0]);
//                        labelsPartida_[i*3 + 1].setText(midadatatemps[1]);
//                        labelsPartida_[i*3 + 2].setText(midadatatemps[2]+" s");
//                    }
//                    else{
//                        botonsPartida_[i].setVisible(false);
//                        labelsPartida_[i*3].setText("");
//                        labelsPartida_[i*3 + 1].setText("");
//                        labelsPartida_[i*3 + 2].setText("");
//                    }
//                }
//            }
//
//            /**
//             * Actualitza la vista de les partides guardades, mostrant les següents NOMBREPARTIDES_ partides.
//             */
//            private void nextN() {
//                indexActual_ += 1;
//                if (indexActual_ == maxIndex_) nextN_.setEnabled(false);
//                if (indexActual_ > 0) previousN_.setEnabled(true);
//                for (int i = 0; i < NOMBREPARTIDES_; ++i) {
//                    if (indexActual_ * NOMBREPARTIDES_ + i < partidesGuardades_.length){
//                        String[] midadatatemps = generaMidaDataTemps(partidesGuardades_[indexActual_ * NOMBREPARTIDES_ + i]);
//                        labelsPartida_[i*3].setText(midadatatemps[0]);
//                        labelsPartida_[i*3 + 1].setText(midadatatemps[1]);
//                        labelsPartida_[i*3 + 2].setText(midadatatemps[2]+" s");
//                    }
//                    else{
//                        botonsPartida_[i].setVisible(false);
//                        labelsPartida_[i*3].setText("");
//                        labelsPartida_[i*3 + 1].setText("");
//                        labelsPartida_[i*3 + 2].setText("");
//                    }
//                }
//            }
//
//            /**
//             * Afegeix un observador de botons a la vista.
//             * @param ob Observador de botons a afegir.
//             */
//            public void addObservadorBoto(ObservadorBoto ob) {
//                observadorsBoto_.add(ob);
//            }
//
//            /**
//             * Genera la mida, data i temps d'una partida guardada.
//             * @param partidaGuardada Partida guardada a analitzar.
//             * @return Array de Strings amb la mida, data i temps de la partida guardada.
//             */
//            private String[] generaMidaDataTemps(String partidaGuardada){
//                String[] parts = partidaGuardada.split("\n");
//                String identificador = parts[0];
//                String[] identificadorDividit = identificador.split(":");
//                StringBuilder data = new StringBuilder();
//                for (int j = 1; j < identificadorDividit.length; ++j) {
//                    data.append(identificadorDividit[j]);
//                }
//                String dataString = data.toString();
//                String[] dataDividida = dataString.split("T");
//                String dataFormat = String.valueOf(new StringBuilder(dataDividida[0] + " " + dataDividida[1]));
//                String temps = parts[2];
//                String mida = parts[3];
//                return new String[]{mida, dataFormat, temps};
//            }
//        }
