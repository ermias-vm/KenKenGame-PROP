package main.domini.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CreadorKenkenParam {
    private int[][] solution;
    private int size;
    private String[][] blocks;
    private String[] blockLetters;
    private int maxBlockSize = 3;
    private int minBlockSize = 2;

    public CreadorKenkenParam(int size) {
        this.size = size;
        this.solution = new int[size][size];
        this.blocks = new String[size][size];
        this.blockLetters = generateBlockLetters(size);
    }

    public void generateSolution() {
        // 1) Generem llista dels numeros adients
        List<Integer> numbers = generateNumberList();

        // 2) Movem la llista perque no es repeteixin numeros
        for (int i = 0; i < size; i++) {
            Collections.rotate(numbers, 1);
            for (int j = 0; j < size; j++) {
                solution[i][j] = numbers.get(j);
            }
        }

        // 3) Barrejem les filesi columnes per fer-ho mes aleatori
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

    //Per veure a terminal com queda, caldra cambiar-ho al final
    public void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    //COMENCA PART BLOCS
    

    public void generateBlocks() {
        List<int[]> coordinates = generateCoordinates();

        for (int[] coordinate : coordinates) {
            int x = coordinate[0];
            int y = coordinate[1];
            if (blocks[x][y] == null) {
                blocks[x][y] = getRandomBlock();
            }
        }

        for (int i = 0; i < size * size; i++) {
            int[] coordinate = coordinates.get(i);
            int x = coordinate[0];
            int y = coordinate[1];
            if (blocks[x][y] == null) {
                blocks[x][y] = getRandomBlockAdjacent(x, y);
            }
        }
    }

    private String[] generateBlockLetters(int size) {
        String[] letters = new String[size * size];
        char currentChar = 'a';
        for (int i = 0; i < size * size; i++) {
            letters[i] = String.valueOf(currentChar);
            currentChar++;
        }
        return letters;
    }

    private List<int[]> generateCoordinates() {
        List<int[]> coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                coordinates.add(new int[]{i, j});
            }
        }
        return coordinates;
    }

    private String getRandomBlock() {
        return String.valueOf(blockLetters[new Random().nextInt(size * size)]);
    }

    private String getRandomBlockAdjacent(int x, int y) {
        List<String> adjacentBlocks = getAdjacentBlocks(x, y);
        if (adjacentBlocks.isEmpty()) {
            return getRandomBlock();
        }
        return adjacentBlocks.get(new Random().nextInt(adjacentBlocks.size()));
    }

    private List<String> getAdjacentBlocks(int x, int y) {
        List<String> adjacentBlocks = new ArrayList<>();
        if (x - 1 >= 0 && blocks[x - 1][y] != null) {
            adjacentBlocks.add(blocks[x - 1][y]);
        }
        if (y - 1 >= 0 && blocks[x][y - 1] != null) {
            adjacentBlocks.add(blocks[x][y - 1]);
        }
        if (x + 1 < size && blocks[x + 1][y] != null) {
            adjacentBlocks.add(blocks[x + 1][y]);
        }
        if (y + 1 < size && blocks[x][y + 1] != null) {
            adjacentBlocks.add(blocks[x][y + 1]);
        }
        return adjacentBlocks;
    }

    public void printBlocks() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(blocks[i][j] + " ");
            }
            System.out.println();
        }
    }

    //EL MAIN ES TEMPORAL
    //Serveix per comprovar rapidament si les coses funcionen com toca
    public static void main(String[] args) {
        int size = 4;
        CreadorKenkenParam generator = new CreadorKenkenParam(size);
        generator.generateSolution();
        generator.printSolution();
        CreadorKenkenParam creator = new CreadorKenkenParam(size);
        creator.generateBlocks();
        creator.printBlocks();
    }
}