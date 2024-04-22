package main.stubs;
public class CtrlTaulerStub {
    public TaulerStub llegirTaulerJoc(int i) {
        return new TaulerStub("Tauler " + i, 3);
    }
    public TaulerStub creaKenken(String dadesTauler) {
        return new TaulerStub("Kenken", 3);
    }

    public int[][] resolKenken(TaulerStub taulerPartida, int[][] valorsPartida) {
        return new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 1, 2}};
    }
}
