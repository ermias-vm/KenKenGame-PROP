package main.domini.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreadorKenkenParam {
    private int[][] solution; // Matriu que representa la solució del Kenken
    private char[][] blockSolution; // Matriu que representa la solució dels blocs
    private int size; // Mida del tauler
    private int maxSize; // Mida màxima dels blocs

    /**
     * Constructor de la classe CreadorKenkenParam.
     *
     * @param size    Mida del tauler.
     * @param maxSize Mida màxima dels blocs.
     */
    public CreadorKenkenParam(int size, int maxSize) {
        this.size = size;
        this.maxSize = maxSize;
        this.solution = new int[size][size];
        this.blockSolution = new char[size][size];
    }

    /**
     * Genera la solució del Kenken i el tauler de blocs fusionats.
     */
    public void generateSolutionAndBoard() {
        generateSolution();
        generateBoard();
    }


    //PART NUMEROS:

    /**
     * Genera la solució del Kenken (es a dir, els numeros del resultat).
     */
    private void generateSolution() {
        List<Integer> numbers = generateNumberList();

        for (int i = 0; i < size; i++) {
            Collections.rotate(numbers, 1);
            for (int j = 0; j < size; j++) {
                solution[i][j] = numbers.get(j);
            }
        }

        shuffleRowsAndColumns();
    }

    /**
     * Genera una llista de nombres consecutius.
     *
     * @return Llista de nombres consecutius.
     */
    private List<Integer> generateNumberList() {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }
        return Arrays.asList(numbers);
    }

    /**
     * Barreja les files i columnes de la solució.
     */
    private void shuffleRowsAndColumns() {
        List<int[]> rows = Arrays.asList(solution);
        Collections.shuffle(rows);
        solution = rows.toArray(new int[rows.size()][]);

        transposeSolution();
        rows = Arrays.asList(solution);
        Collections.shuffle(rows);
        solution = rows.toArray(new int[rows.size()][]);
    }

    /**
     * Transposa la matriu de solució.
     */
    private void transposeSolution() {
        int[][] transposed = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                transposed[i][j] = solution[j][i];
            }
        }
        solution = transposed;
    }


    // PART BLOCS (generacio de Regions):

    /**
     * Genera el tauler de blocs fusionats.
     */
    private void generateBoard() {
        Board board = new Board(size);

        List<Candidate> candidates = generateCandidates();

        for (Candidate candidate : candidates) {
            Cell cell1 = board.grid[candidate.x1][candidate.y1].findRoot();
            Cell cell2 = board.grid[candidate.x2][candidate.y2].findRoot();
            if (cell1.size + cell2.size <= maxSize) {
                cell1.merge(cell2);
            }
        }

        updateBlockSolution(board);
    }

    /**
     * Genera una llista de candidates per fusionar blocs.
     *
     * @return Llista de candidates per fusionar blocs.
     */
    private List<Candidate> generateCandidates() {
        List<Candidate> candidates = Arrays.asList(new Candidate[size * (size - 1) * 2]);
        int idx = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (y < size - 1) {
                    candidates.set(idx++, new Candidate(x, y, x, y + 1));
                }
                if (x < size - 1) {
                    candidates.set(idx++, new Candidate(x, y, x + 1, y));
                }
            }
        }
        Collections.shuffle(candidates);
        return candidates;
    }

    /**
     * Li posa la lletra a cada block/regio.
     *
     * @param board Tauler amb les cel·les fusionades.
     */
    private void updateBlockSolution(Board board) {
        char blockLetter = 'a';
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell root = board.grid[x][y].findRoot();
                if (blockSolution[root.x][root.y] == 0) {
                    blockSolution[root.x][root.y] = blockLetter++;
                    if ((blockLetter - 1) == 'z') blockLetter = 'A';
                }
                blockSolution[x][y] = blockSolution[root.x][root.y];
            }
        }
    }


    // IMPRESSIONS PER TERMINAL:

    /**
     * Imprimeix la solució del Kenken per la terminal.
     */
    public void printSolution() {
        System.out.println("Número:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Imprimeix la solució dels blocs per la terminal.
     */
    public void printBlockSolution() {
        System.out.println("Bloques:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(blockSolution[i][j] + " ");
            }
            System.out.println();
        }
    }

    // MAIN TEMPORAL PER ANAR PROVANTEL CODI
    public static void main(String[] args) {
        int size = 9;
        int maxSize = 3;
        CreadorKenkenParam generator = new CreadorKenkenParam(size, maxSize);
        generator.generateSolutionAndBoard();
        generator.printSolution();
        generator.printBlockSolution();
    }
}

class Board {
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

class Cell {
    public int x;
    public int y;
    public Cell parent;
    public int size;

    /**
     * Constructor de la classe Cell.
     *
     * @param x Coordenada x de la cel·la.
     * @param y Coordenada y de la cel·la.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.size = 1;
    }

    /**
     * Fusiona 2 cel·les, es el proces de posar-ho a la mateixa regio.
     *
     * @param other Altra cel·la a fusionar.
     */
    public void merge(Cell other) {
        Cell root1 = findRoot();
        Cell root2 = other.findRoot();
        if (root1 == root2) return;

        if (root1.size < root2.size) {
            Cell tmp = root1;
            root1 = root2;
            root2 = tmp;
        }

        root2.parent = root1;
        root1.size += root2.size;
    }

    /**
     * Troba la cel·la parent.
     *
     * @return Cel·la arrel.
     */
    public Cell findRoot() {
        if (parent == null) return this;
        Cell root = parent.findRoot();
        parent = root;
        return root;
    }
}

class Candidate {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    /**
     * Constructor de la classe Candidate.
     *
     * @param x1 Coordenada x de la primera cel·la.
     * @param y1 Coordenada y de la primera cel·la.
     * @param x2 Coordenada x de la segona cel·la.
     * @param y2 Coordenada y de la segona cel·la.
     */
    public Candidate(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}