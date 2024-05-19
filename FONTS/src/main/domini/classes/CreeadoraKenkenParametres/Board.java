package main.domini.classes.CreeadoraKenkenParametres;

public class Board {
    public Cell[][] grid;

    /**
     * Constructor de la classe Board.
     *
     * @param size Mida del tauler.
     */
    public Board(int size) {
        grid = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }
}

