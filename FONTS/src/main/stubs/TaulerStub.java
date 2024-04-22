package main.stubs;

public class TaulerStub {
    private String identificador_;
    private int grau_;

    public TaulerStub(String identificador, int grau) {
        this.grau_ = grau;
        this.identificador_ = identificador;
    }

    public String getIdentificador() {
        return identificador_;
    }
    public int getGrau() {
        return grau_;
    }
    public boolean corretgeix(int[][] valors) {
        // Implement the logic for this method or return a hardcoded value
        return true;
    }

    public String generaTaulerText() {
        return "Perdò no em tocava la classe Tauler, soc un Stub\n";
    }
}

