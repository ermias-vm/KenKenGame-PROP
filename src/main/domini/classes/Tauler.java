package main.domain.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class Tablero {
    private int idTablero;
    private int grado;
    
    private List<Pieza> piezas;

    public Tablero(int idTablero, int grado) {
        this.idTablero = idTablero;
        this.grado = grado;
        this.piezas = new ArrayList<>();
    }

    public void agregarPieza(Pieza pieza) {
        piezas.add(pieza);
    }
}

