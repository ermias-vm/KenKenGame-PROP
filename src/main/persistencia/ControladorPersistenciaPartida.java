package main.persistencia;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ControladorPersistenciaPartida {
    public String carregarUltimaPartida(String nomUsuari) {
        String partidesGuardades = "Data/PartidesGuardades.txt";
        try {
            BufferedReader lector = new BufferedReader(new FileReader(partidesGuardades));
            String linia;
            String partida = "";
            int liniesLlegir = 0;
            int i = 0;
            boolean trobat = false;
            while ((linia = lector.readLine()) != null) {
                if (linia.contains(nomUsuari+":")) {
                    partida = linia;
                    trobat = true;
                    liniesLlegir = 4;  //sempre son 4 linies
                } else if (trobat && i < liniesLlegir) {
                    partida += "\n" + linia;
                    i++;
                    if (i == 3) {
                        liniesLlegir += Integer.parseInt(linia);  //es la mida de la partida
                    }
                }
                if (i == liniesLlegir) {
                    break;
                }
            }
            lector.close();
            return partida;
        } catch (IOException e) {
            System.out.println("Error al carregar la partida: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<String> carregarPartides(String nomUsuari) {
        String partidesGuardades = "Data/PartidesGuardades.txt";
        ArrayList<String> partides = new ArrayList<>();
        HashSet<String> identificadors = new HashSet<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(partidesGuardades));
            String linia;
            String partida = "";
            int liniesLlegir = 0;
            int i = 0;
            boolean trobat = false;
            while ((linia = lector.readLine()) != null) {
                if (linia.contains(nomUsuari+":")) {
                    if (identificadors.contains(linia)) {
                        for (int j = 0; j < 4 + Integer.parseInt(lector.readLine()); j++) {
                            lector.readLine();
                        }
                        continue;
                    }
                    identificadors.add(linia);
                    partida = linia;
                    trobat = true;
                    liniesLlegir = 4;  //sempre son 4 linies
                } else if (trobat && i < liniesLlegir) {
                    partida += "\n" + linia;
                    i++;
                    if (i == 3) {
                        liniesLlegir += Integer.parseInt(linia);  //es la mida de la partida
                    }
                }
                if (i == liniesLlegir) {
                    partides.add(partida);
                    partida = "";
                    i = 0;
                    trobat = false;
                }
            }
            lector.close();
            return partides;
        } catch (IOException e) {
            System.out.println("Error al carregar les partides: " + e.getMessage());
            return null;
        }
    }

    public boolean guardarPartida(String partidaGuardada) {
        String partidesGuardades = "Data/PartidesGuardades.txt";
        String tempPartidesGuardades = "Data/tempPartidesGuardades.txt";
        try {
            BufferedWriter escriptor = new BufferedWriter(new FileWriter(tempPartidesGuardades));
            escriptor.write(partidaGuardada);
            escriptor.newLine();
            escriptor.close();
            Files.write(Paths.get(tempPartidesGuardades), Files.readAllBytes(Paths.get(partidesGuardades)), StandardOpenOption.APPEND);
            Files.move(Paths.get(tempPartidesGuardades), Paths.get(partidesGuardades), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
            return false;
        }
    }

    public boolean arxivarPartida(String partidaAcabada) {
        String partidesAcabades = "Data/PartidesAcabades.txt";
        String tempPartidesAcabades = "Data/tempPartidesAcabades.txt";
        String partidesGuardades = "Data/PartidesGuardades.txt";
        String tempPartidesGuardades = "Data/tempPartidesGuardades.txt";
        try {
            String identificador = partidaAcabada.split("\n")[0];
            BufferedReader reader = new BufferedReader(new FileReader(partidesGuardades));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempPartidesGuardades));

            String liniaActual;
            while ((liniaActual = reader.readLine()) != null) {
                String trimmedLine = liniaActual.trim();
                if (trimmedLine.equals(identificador)) {
                    for (int i = 0; i < 4 + Integer.parseInt(reader.readLine()); i++) {
                        reader.readLine();
                    }
                    continue;
                }
                writer.write(liniaActual + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            Files.move(Paths.get(tempPartidesGuardades), Paths.get(partidesGuardades), StandardCopyOption.REPLACE_EXISTING);

            BufferedWriter escriptor = new BufferedWriter(new FileWriter(tempPartidesAcabades));
            escriptor.write(partidaAcabada);
            escriptor.newLine();
            escriptor.close();
            Files.write(Paths.get(tempPartidesAcabades), Files.readAllBytes(Paths.get(partidesAcabades)), StandardOpenOption.APPEND);
            Files.move(Paths.get(tempPartidesAcabades), Paths.get(partidesAcabades), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Error al arxivar la partida: " + e.getMessage());
            return false;
        }
    }
}