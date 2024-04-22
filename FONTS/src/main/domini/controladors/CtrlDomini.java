package main.domini.controladors;


import main.domini.classes.*;
import main.domini.excepcions.*;

import java.io.IOException;

public class CtrlDomini {

    private TaulerJoc  TJ;
    private CtrlUsuari CU;
    private ControladorPartida CP;
    private CtrlTauler CT;

    /* Mètode Constructor */
    public CtrlDomini() {
        CP = new ControladorPartida();
        CU = new CtrlUsuari();
        CT = new CtrlTauler();
    }
    //Usuari
    public void iniciaSessio(String usuari, String contrasenya) {
        CU.iniciarSessio(usuari,contrasenya);
    }

    public boolean existeixUsuari (String u) {
        return CU.existeixUsuari(u);
    }

    public void canviarContrasenya(String usuari, String ctrActual, String ctrNova) {
        CU.canviarContrasenya(usuari, ctrActual, ctrNova);
    }

    //Kenkens

    public void importarKenken(int idTauler) throws Exception {
        TJ = CT.llegirTaulerJoc(idTauler);
        resoldreKenken();
    }

    public void resoldreKenken() throws Exception {
        TJ.solucionarKenken(TJ);
        if (TJ.teSolucion()) {
            System.out.println("Kenken resolt correctament");
            mostrarTaulerJoc(TJ);
        } else {
            System.out.println("Kenken no te solucio");
        }

    }

    public void mostrarTaulerJoc(TaulerJoc TJ) throws Exception {
        int grau = TJ.getGrau();
        for (int i = 1; i <= grau; i++) {
            for (int j = 1; j <= grau; j++) {
                int valor = TJ.getCasella(i, j).getValor();
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
    //Partida
    private void carregarUltimaPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioNoPermisUsuari, ExcepcioPartidaEnCurs, ExcepcioCreacioPartida {
        CP.carregarUltimaPartidaGuardada(nomUsuari);
    }

    private void iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        CP.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }

    private void iniciarNovaPartidaTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler,
            ExcepcioInicialitzacioControladorTauler,
            ExcepcioPartidaEnCurs {
        CP.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }

    private void iniciarNovaPartidaDadesTauler(String dadesTauler, String nomUsuari) throws ExcepcioInicialitzacioControladorTauler,
            ExcepcioPartidaEnCurs, ExcepcioCarregaTauler {
        CP.iniciaPartidaDadesTauler(dadesTauler, nomUsuari);
    }

    private void introduirValorCasella(int fila, int columna, int valor) throws ExcepcioValorInvalid,
            ExcepcioPartidaEnCurs,
            ExcepcionPosicioIncorrecta, ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaAcabada {
        CP.introduirValor(fila, columna, valor);
    }

    private void donarPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcionPosicioIncorrecta {
        CP.donaPista();
    }

    private void guardarPartida(String nomUsuari) throws ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioPartidaAcabada {
        CP.guardarPartida(nomUsuari);
    }

    private void tancarIguardarPartida() throws ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaAcabada {
        CP.tancarIguardarPartida();
    }

    private void acabarPartida() throws ExcepcioPartidaAcabada, ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaMalament {
        CP.acabarPartida();
    }

    private void tancaPartida() {
        CP.tancaPartida();
    }


    //Ranking
}