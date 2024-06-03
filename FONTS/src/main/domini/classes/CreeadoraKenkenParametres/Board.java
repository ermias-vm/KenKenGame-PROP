package main.domini.classes.CreeadoraKenkenParametres;

/**
 * La classe Board representa el tauler que s'utilitza provisionalment durant la creacio de Kenken per parametres.
 * @autor David Giribet Casado
 */
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

