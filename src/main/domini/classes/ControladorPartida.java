package main.domini.classes;

import main.persistencia.ControladorPersistenciaPartida;

import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPartida {
    private Partida partida_;
    private HashMap<String, String> partidesUsuari_;
    private final ControladorPersistenciaPartida controladorPersistenciaPartida_;
    private ControladorKenken controladorKenken_;

    public ControladorPartida() {
        controladorPersistenciaPartida_ = new ControladorPersistenciaPartida();
        partida_ = null;
        partidesUsuari_ = new HashMap<>();
    }
    public boolean setControladorKenken(ControladorKenken controladorKenken) {
        controladorKenken_ = controladorKenken;
        return true;
    }
    public String[] carregarUltimaPartidaGuardada(String nomUsuari){
        String partida = controladorPersistenciaPartida_.carregarUltimaPartida(nomUsuari);
        partida_ = stringToPartida(partida, nomUsuari);
        if (partida_ == null) throw new ExcepcioCarregaPartida("No s'ha pogut carregar la partida");
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTauler().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari){
        ArrayList<String>partides = controladorPersistenciaPartida_.carregarPartides(nomUsuari);
        ArrayList<String> identificadorsPartidesUsuari = new ArrayList<>();
       for (String partida : partides) {
           String[] linies = partida.split("\n");
           String clau = linies[0];
           partidesUsuari_.put(clau, partida);
           identificadorsPartidesUsuari.add(clau);
        }
        if (partidesUsuari_ == null) identificadorsPartidesUsuari.add("No hi ha partides guardades");
        return identificadorsPartidesUsuari;
    }

    public String[] carregarPartidaGuardada(String identificadorPartida, String nomUsuari){
        String partida = partidesUsuari_.get(identificadorPartida);
        partida_ = stringToPartida(partida, nomUsuari);
        if (partida_ == null){
            throw new ExcepcioCarregaPartida("No s'ha pogut carregar la partida amb identificador " + identificadorPartida);
        }
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTauler().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    public String[] començaPartidaIdentificadorTauler(String identificadorTauler,String nomUsuari){
        Tauler tauler = controladorKenken_.carregarKenken(identificadorTauler);
        if (tauler == null) throw new ExcepcioCarregaTauler("No s'ha pogut carregar el tauler amb identificador " + identificadorTauler);
        partida_ = new Partida(tauler.getMida(), nomUsuari, tauler);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTauler().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    public String[] començaPartidaDadesTauler(String dadesTauler, String nomUsuari){
        Tauler tauler = controladorKenken_.creaKenken(dadesTauler);
        if (tauler == null) throw new ExcepcioCarregaTauler("No s'ha pogut crear el tauler amb les dades donades");
        partida_ = new Partida(tauler.getMida(), nomUsuari, tauler);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTauler().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    public String introduirValor(int fila, int columna, int valor){
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        if (partida_.getTauler().getMida() <= fila || partida_.getTauler().getMida() <= columna) throw new ExcepcioValorForaTauler("La posició no és vàlida");
        if (valor < 1 || valor > partida_.getTauler().getMida()) throw new ExcepcioValorIncorrecte("El valor no és vàlid");
        partida_.introduirValor(fila, columna, valor);
        return partida_.generaPartidaText();
    }
    public boolean guardarPartida(String nomUsuari){
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        if (!partida_.getUsuariPartida().equals(nomUsuari)) throw new ExcepcioCarregaPartida("El nom d'usuari no coincideix");
        controladorPersistenciaPartida_.guardarPartida(partida_.guardaPartida());
        return true;
    }
    public boolean tancarIguardarPartida(){
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        controladorPersistenciaPartida_.guardarPartida(partida_.tancaIGuardaPartida());
        partida_= null;
        return true;
    }
    public boolean acabarPartida(){
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        controladorPersistenciaPartida_.arxivarPartida(partida_.acabaPartida());
        partida_= null;
        return true;
    }
    private Partida stringToPartida(String partida, String nomUsuari){
        String[] divisio = partida.split("\n");
        String identificadorPartida = divisio[0];
        String[] parts = identificadorPartida.split(":");
        String identificadorUsuariPartida = parts[0];
        if (!identificadorUsuariPartida.equals(nomUsuari)) throw new ExcepcioCarregaPartida("El nom d'usuari no coincideix");
        String identificadorTaulerPartida = divisio[1];
        int tempsPartida = Integer.parseInt(divisio[2]);
        int midaPartida = Integer.parseInt(divisio[3]);
        int [][] valorsPartida = new int[midaPartida][midaPartida];
        for (int i = 0; i < midaPartida; i++) {
            String[] valors = divisio[4 + i].split(" ");
            for (int j = 0; j < midaPartida; j++) {
                valorsPartida[i][j] = Integer.parseInt(valors[j]);
            }
        }
        Tauler tauler = controladorKenken_.carregarKenken(identificadorTaulerPartida);
        return new Partida(identificadorPartida, identificadorUsuariPartida, tauler, tempsPartida, midaPartida, valorsPartida);
    }
}
