package main.persistencia;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Controlador de persistencia per a {@code Partida} que s'encarrega de gestionar la càrrega i el guardat de partides.
 * Es relaciona amb el controlador de partides de domini {@link main.domini.controladors.ControladorPartida}.
 * Opera amb fitxers de text per a guardar les partides.<br>
 * El fitxer per guardar les partides guardades és "Data/PartidesGuardades.txt".<br>
 * Els fitxers per guardar les partides acabades són "Data/PartidesAcabadesGuardades.txt",<br>
 * "Data/PartidesAcabadesGrau%d.txt" on %d és el grau de la partida.
 * @author Nil Beascoechea Vàzquez
 */
public class ControladorPersistenciaPartida {
    /**
     * Fitxer on es guarden les partides guardades.
     */
    private final String fitxerPartidesGuardades_ = "Data/PartidesGuardades.txt";
    /**
     * Vector de Strings on cada una representa un fitxer on s'emmagatzemen les partides acabades.
     * Les que han estat guardades es guarden a l'índex [0].
     * Les que no han estat guardades es guarden a l'índex [n-2] on n és el grau de la partida.
     */
    private final String[] fitxersPartidesAcabades_ =
            {
                    "Data/PartidesAcabadesGuardades.txt",
                    "Data/PartidesAcabadesGrau3.txt", //  Grau 3 = fitxersPartidesAcabades_[1]
                    "Data/PartidesAcabadesGrau4.txt", // Grau 4 = fitxersPartidesAcabades_[2]...
                    "Data/PartidesAcabadesGrau5.txt",
                    "Data/PartidesAcabadesGrau6.txt",
                    "Data/PartidesAcabadesGrau7.txt",
                    "Data/PartidesAcabadesGrau8.txt",
                    "Data/PartidesAcabadesGrau9.txt"
            };      // Grau n = fitxersPartidesAcabades_[n-2]
    /**
     * Carrega l'última partida guardada per un usuari. L'usuari existeix.
     * La informació de la partida guardada es troba al fitxer "Data/PartidesGuardades.txt" i està ordenada per última guardada.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a llegir les dades de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a retornar les dades de la partida.
     * @param nomUsuari Nom de l'usuari que ha guardat la partida i la vol carregar.
     * @return Una cadena de text amb la informació de la partida guardada.
     */
    public String carregarUltimaPartidaGuardada(String nomUsuari) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.fitxerPartidesGuardades_));
            String linia;
            StringBuilder partida = new StringBuilder();
            boolean trobat = false;
            while ((linia = lector.readLine()) != null && !trobat) {
                if (linia.contains(nomUsuari + ":")) {
                    partida.append(linia).append("\n");
                    int mida = 0;
                    for (int j = 0; j < 3 + mida ; j++) {
                        if (j == 2) {
                            mida = Integer.parseInt(lector.readLine());
                            partida.append(mida).append("\n");
                        }
                        else {
                            partida.append(lector.readLine()).append("\n");
                        }
                    }
                    trobat = true;
                }
            }
            lector.close();
            return partida.toString();
        }
        catch (IOException e) {
            System.out.println("Error al carregar les partides: " + e.getMessage());
            return null;
        }
    }
    /**
     * Carrega totes les partides guardades per un usuari. L'usuari existeix.
     * La informació de les partides guardades es troba al fitxer "Data/PartidesGuardades.txt".
     * En utilitzar un HashSet per identificar les partides, es garanteix que no es repeteixen.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a llegir les dades de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a retornar les dades de la partida.<br>
     * @param nomUsuari Nom de l'usuari que ha guardat les partides i les vol carregar.
     * @return Una llista de cadenes de text amb la informació de les partides guardades.
     */
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) {
        ArrayList<String> partides = new ArrayList<>();
        HashSet<String> identificadors = new HashSet<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.fitxerPartidesGuardades_));
            String linia;
            while ((linia = lector.readLine()) != null) {
                if (linia.contains(nomUsuari + ":")) {
                    if (identificadors.contains(linia)) {
                        int mida = 0;
                        for (int j = 0; j < 3 + mida ; j++) {
                            if (j == 2) {
                                mida = Integer.parseInt(lector.readLine());
                            }
                            else {lector.readLine();}
                        }
                        continue;
                    }
                    identificadors.add(linia);
                    StringBuilder partida = new StringBuilder();
                    partida.append(linia).append("\n");
                    int mida = 0;
                    for (int j = 0; j < 3 + mida ; j++) {
                        if (j == 2) {
                            mida = Integer.parseInt(lector.readLine());
                            partida.append(mida).append("\n");
                        }
                        else {
                            partida.append(lector.readLine()).append("\n");
                        }
                    }
                    partides.add(partida.toString());
                }
            }
            lector.close();
            return partides;
        }
        catch (IOException e) {
            System.out.println("Error al carregar les partides: " + e.getMessage());
            return null;
        }
    }
    /**
     * Carrega totes les partides acabades per un usuari. L'usuari existeix.
     * Busca a tots els fitxers de partides acabades per trobar les partides de l'usuari.<br>
     * Utilitza el format descrit a {@link #arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param nomUsuari Nom de l'usuari del qual es volen carregar les partides.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregarPartidesAcabadesUsuari(String nomUsuari) {
        try {
            ArrayList<String> partides = new ArrayList<>();
            for (String partidesAcabades : this.fitxersPartidesAcabades_) {
                BufferedReader lector = new BufferedReader(new FileReader(partidesAcabades));
                String linia;
                while ((linia = lector.readLine()) != null) {
                    if (linia.contains(nomUsuari + ":")) {
                        StringBuilder partida = new StringBuilder();
                        partida.append(linia).append("\n");
                        for (int j = 0; j < 4; j++) {
                            partida.append(lector.readLine()).append("\n");
                        }
                        if (partidesAcabades == this.fitxersPartidesAcabades_[0]) {
                            partida.append("true").append("\n");
                        }
                        else {
                            partida.append("false").append("\n");
                        }
                        partides.add(partida.toString());
                    }
                }
            }
            return partides;
        }
        catch (IOException e) {
            System.out.println("Error al carregar les partides acabades de l'usuari " + nomUsuari + ":" + e.getMessage());
            return null;
        }
    }
    /**
     * Carrega totes les partides acabades d'un grau. El grau és vàlid.
     * La informació de les partides acabades es troba al fitxer "Data/PartidesAcabadesGrau%d.txt" on %d és el grau.<br>
     * Utilitza el format descrit a {@link #arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param grau Grau de les partides que es volen carregar.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregaPartidesAcabadesGrau(int grau) {
        try {
            ArrayList<String> partides = new ArrayList<>();
            BufferedReader lector = new BufferedReader(new FileReader(this.fitxersPartidesAcabades_[grau-2]));
            String linia;
            while ((linia = lector.readLine()) != null) {
                StringBuilder partida = new StringBuilder();
                partida.append(linia).append("\n");
                for (int j = 0; j < 4; j++) {
                    partida.append(lector.readLine()).append("\n");
                }
                partida.append("false").append("\n");
                partides.add(partida.toString());
            }
            lector.close();
            return partides;
        }
        catch (IOException e) {
            System.out.println("Error al carregar les partides acabades de grau " + grau + ":" + e.getMessage());
            return null;
        }
    }
    /**
     * Guarda una partida.
     * La partida es guarda a l'inici del fitxer de partides guardades assegurant ordre cronologic.
     * La informació de la partida es guarda al fitxer "Data/PartidesGuardades.txt".<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a guardar les dades a memòria.
     * @param partidaGuardada Una cadena de text amb la informació de la partida a guardar.
     * @return True si s'ha guardat la partida, false altrament.
     */
    public boolean guardarPartida(String partidaGuardada) {
        String tempPartidesGuardades = "Data/tempPartidesGuardades.txt";
        try {
            BufferedWriter escriptor = new BufferedWriter(new FileWriter(tempPartidesGuardades, true));
            escriptor.write(partidaGuardada);
            escriptor.close();

            FileInputStream llegir = new FileInputStream(this.fitxerPartidesGuardades_);
            FileOutputStream copiar = new FileOutputStream(tempPartidesGuardades, true);
            byte[] buffer = new byte[4096];
            int mida;
            while ((mida = llegir.read(buffer)) > 0) {
                copiar.write(buffer, 0, mida);
            }
            llegir.close();
            copiar.close();
            Files.move(Paths.get(tempPartidesGuardades), Paths.get(this.fitxerPartidesGuardades_), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
            return false;
        }
    }
    /**
     * Arxiva una partida acabada.
     * La partida arxivada es guarda al final del fitxer de partides acabades.
     * La informació de la partida es guarda al fitxer "Data/PartidesAcabadesGuardades.txt" si havia estat guardada.
     * O al fitxer "Data/PartidesAcabadesGrau%d.txt" on %d és el grau de la partida.
     * S'elimina la partida arxivada del fitxer de partides guardades.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per a llegir la partida.
     * Però la guarda al fitxer com a (sense comptar les línies amb / ni |):<br>
     * //////FORMAT GUARDAT PARTIDA ACABADA\\\\\\<br>
     * Identificador de la partida<br>
     * Identificador de l'usuari<br>
     * Identificador del tauler<br>
     * Temps total de la partida<br>
     * Grau del tauler<br>
     *  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||<br>
     * Total: 5 línies.
     * El si és guardada o no ve implicit en el nom del fitxer.
     * @param partidaAcabada Una cadena de text amb la informació de la partida a arxivar.
     * @return True si s'ha arxivat la partida, false altrament.
     */
    public boolean arxivarPartida(String partidaAcabada) {
        String tempPartidesGuardades = "Data/tempPartidesGuardades.txt";
        try {
            String[] linies = partidaAcabada.split("\n");
            StringBuilder formatGuardar = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                formatGuardar.append(linies[i]).append("\n");
            }
            String identificador = linies[0];
            int mida = Integer.parseInt(linies[4]);
            boolean guardada = Boolean.parseBoolean(linies[5]);
            int midaTotal = 4 + mida;
            String fitxerDestinacio;
            if (guardada) {
                fitxerDestinacio = "Data/PartidesAcabadesGuardades.txt";
            } else {
                fitxerDestinacio = "Data/PartidesAcabadesGrau" + mida + ".txt";
            }
            BufferedWriter escriptor = new BufferedWriter(new FileWriter(fitxerDestinacio, true));
            escriptor.write(formatGuardar.toString());
            escriptor.close();
            BufferedReader llegir = new BufferedReader(new FileReader(this.fitxerPartidesGuardades_));
            BufferedWriter copiar = new BufferedWriter(new FileWriter(tempPartidesGuardades));
            String linia;
            while ((linia = llegir.readLine()) != null) {
                if (linia.contains(identificador)) {
                    for (int i = 0; i < midaTotal; i++) {
                        llegir.readLine();
                    }
                } else {
                    copiar.write(linia);
                    copiar.newLine();
                }
            }
            copiar.close();
            llegir.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error al arxivar la partida: " + e.getMessage());
            return false;
        }
    }

    /**
     * Funció de cerca per a saber si un usuari ha jugat ja un tauler.
     * @param identificadorTauler Identificador del tauler
     * @param nomUsuari Nom de l'usuari
     * @return True si l'usuari ha jugat el tauler, false altrament.
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        try {
            for (String partidesAcabades : this.fitxersPartidesAcabades_) {
                BufferedReader lector = new BufferedReader(new FileReader(partidesAcabades));
                String linia;
                while ((linia = lector.readLine()) != null) {
                    if (linia.contains(nomUsuari + ":")) {
                        lector.readLine();
                        String identificador =lector.readLine();
                        if (identificador.equals(identificadorTauler)) {
                            lector.close();
                            return true;
                        }
                    }
                }
                lector.close();
            }
            BufferedReader lector = new BufferedReader(new FileReader(this.fitxerPartidesGuardades_));
            String linia;
            while ((linia = lector.readLine()) != null) {
                if (linia.contains(nomUsuari + ":")) {
                    String identificador =lector.readLine();
                    if (identificador.equals(identificadorTauler)) {
                        lector.close();
                        return true;
                    }
                }
            }
            lector.close();
            return false;
        }
        catch (IOException e) {
            System.out.println("Error al comprovar si ha jugat: " + e.getMessage());
            return false;

        }
    }
}