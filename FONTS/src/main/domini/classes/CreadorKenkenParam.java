package main.domini.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CreadorKenkenParam {
    private int[][] solution;
    private char[][] blockSolution;
    private int size;
    private int maxSize;
    private int numMerges;

    public CreadorKenkenParam(int size, int maxSize, int numMerges) {
        this.size = size;
        this.maxSize = maxSize;
        this.numMerges = numMerges;
        this.solution = new int[size][size];
        this.blockSolution = new char[size][size];
    }

    public void generateSolutionAndBoard() {
        generateSolution();
        generateBoard();
    }

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

    private List<Integer> generateNumberList() {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }
        return Arrays.asList(numbers);
    }

    private void shuffleRowsAndColumns() {
        List<int[]> rows = Arrays.asList(solution);
        Collections.shuffle(rows);
        solution = rows.toArray(new int[rows.size()][]);

        transposeSolution();
        rows = Arrays.asList(solution);
        Collections.shuffle(rows);
        solution = rows.toArray(new int[rows.size()][]);
    }

    private void transposeSolution() {
        int[][] transposed = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                transposed[i][j] = solution[j][i];
            }
        }
        solution = transposed;
    }

    //PART BLOCS

    private void generateBoard() {
        Random rnd = new Random();
        Board board = new Board(size);

        List<Candidate> candidates = generateCandidates();

        for (Candidate candidate : candidates) {
            Cell cell1 = board.grid[candidate.x1][candidate.y1].findRoot();
            Cell cell2 = board.grid[candidate.x2][candidate.y2].findRoot();
            if (cell1.size + cell2.size <= maxSize) {
                cell1.merge(cell2);
                if (--numMerges == 0) break;
            }
        }

        updateBlockSolution(board);
    }

    private List<Candidate> generateCandidates() {
        Random rnd = new Random();
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

    private void updateBlockSolution(Board board) {
        char blockLetter = 'A';
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell root = board.grid[x][y].findRoot();
                if (blockSolution[root.x][root.y] == 0) {
                    blockSolution[root.x][root.y] = blockLetter++;
                }
                blockSolution[x][y] = blockSolution[root.x][root.y];
            }
        }
    }

    //IMPRESIONS PER TERMINAL

    public void printSolution() {
        System.out.println("Número:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBlockSolution() {
        System.out.println("Bloques:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(blockSolution[i][j] + " ");
            }
            System.out.println();
        }
    }

    //MAIN TEMPORAL PER ANAAR PROVANTEL CODI
    public static void main(String[] args) {
        int size = 4;
        int maxSize = 3;
        int numMerges = 5;
        CreadorKenkenParam generator = new CreadorKenkenParam(size, maxSize, numMerges);
        generator.generateSolutionAndBoard();
        generator.printSolution();
        generator.printBlockSolution();
    }
}

class Board {
    public Cell[][] grid;

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

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.size = 1;
    }

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

    public Candidate(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}