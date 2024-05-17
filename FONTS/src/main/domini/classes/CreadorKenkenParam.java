package main.domini.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class CreadorKenkenParam {
    private int[][] solution; // Matriu que representa la solució del Kenken
    private char[][] blockSolution; // Matriu que representa la solució dels blocs
    private int size; // Mida del tauler
    private int maxSize; // Mida màxima dels blocs

    private Set<Character> processedBlocks = new HashSet<>(); // Set de blocs (regions) visitades quan esta creant operacions

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


    //PART NUMEROS (tauler amb numeros aleatoris sense repeticions):

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
            //Cal afegir probabilitats per influir sobre el maxSize
            int midaBlocs;
            Random random = new Random();
            int casellesde2omes = random.nextInt(5);
            if(casellesde2omes != 0) midaBlocs = 2;
            else midaBlocs = maxSize;
            if (cell1.size + cell2.size <= midaBlocs) {
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


    // ASSIGNACIO DE OPERACIONS:

    /**
     * Crea les operacions segons quines estaven seleccionades
     *
     * @param suma indica si el tauler ha de tenir alguna operacio suma o no
     * @param resta indica si el tauler ha de tenir alguna operacio resta o no
     * @param multiplicacio indica si el tauler ha de tenir alguna operacio multiplicacio o no
     * @param divisio indica si el tauler ha de tenir alguna operacio divisio o no
     * @param modul indica si el tauler ha de tenir alguna operacio modul o no
     * @param exponenciacio indica si el tauler ha de tenir alguna operacio exponenciacio o no
     */
    private void crearOperaciones(boolean suma, boolean resta, boolean multiplicacio, boolean divisio, boolean modul, boolean exponenciacio) {
        if (divisio) {
            assignaDivisio();
        }

        assignaBlocsRes(1,false, false, false, false, false);

        if (resta || modul || exponenciacio) {
            assignaBlocsRes(2,false, false, resta, modul, exponenciacio);
        }
        
        assignaBlocsRes(maxSize, suma, multiplicacio, false, false, false);
        
    }

    /**
     * Conta el nombre de caselles que te el bloc d'una lletra en especific
     *
     * @param block la lletra de la casella en concret
     */
    private int countBlockOccurrences(char block) {
        int count = 0;
        for (char[] row : blockSolution) {
            for (char cell : row) {
                if (cell == block) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Conta el nombre de caselles que te el bloc d'una lletra en especific
     *
     * @param mida el size de el conjunt de lletres de del bloc en especific
     * @param block la lletra de la casella en concret
     */
    private int[] findBlockNumbers(int mida, char block) {
        int a = 0;
        int[] blockNumbers = new int[mida];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blockSolution[i][j] == block) {
                    blockNumbers[a] = solution[i][j];
                    a++;
                }
            }
        }
        return blockNumbers;
    }

    /**
     * Assigna les divisions als blocs valids
     */
    private void assignaDivisio() {
        System.out.println("Operacions de Divisió:");
        
        outerLoop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char currentBlock = blockSolution[i][j];
                int count = countBlockOccurrences(currentBlock);
                if (count == 2) {
                    int[] blockNumbers = findBlockNumbers(2,currentBlock);
                    int bigNumber = Math.max(blockNumbers[0], blockNumbers[1]);
                    int smallNumber = Math.min(blockNumbers[0], blockNumbers[1]);
                    if(bigNumber%smallNumber==0){
                        if(!processedBlocks.contains(currentBlock)){    
                            System.out.println(currentBlock);
                            System.out.println(bigNumber + " / " + smallNumber + " = " + (bigNumber / smallNumber));
                            processedBlocks.add(currentBlock);
                            break outerLoop;
                        }

                    }
                }
            }
        }
    }
    
    /**
     * Assigna totes les operacions que no son divisions (i que siguin true) als blocs valids 
     *
     * @param mida el size del conjunt de lletres de del bloc en especific
     * @param suma indica si el tauler ha de tenir alguna operacio suma o no
     * @param resta indica si el tauler ha de tenir alguna operacio resta o no
     * @param multiplicacio indica si el tauler ha de tenir alguna operacio multiplicacio o no
     * @param divisio indica si el tauler ha de tenir alguna operacio divisio o no
     * @param modul indica si el tauler ha de tenir alguna operacio modul o no
     * @param exponenciacio indica si el tauler ha de tenir alguna operacio exponenciacio o no
     */
    private void assignaBlocsRes(int mida, boolean suma, boolean multiplicacio, boolean resta, boolean modul, boolean exponenciacio) {
        System.out.println("Operacions de " + mida + " Caselles:");
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char currentBlock = blockSolution[i][j];
                int count = countBlockOccurrences(currentBlock);
                if (count <= mida) {
                    if (processedBlocks.contains(currentBlock)) {
                        continue;
                    }
                    int[] blockNumbers = findBlockNumbers(mida, currentBlock);
                    boolean operationFound = false;
                    if(mida == 1){
                        operationFound = true;
                        System.out.println(currentBlock);
                        System.out.println(blockNumbers[0]);
                        processedBlocks.add(currentBlock);
                    }
                    while (!operationFound) {
                        int randomOperation = (int) (Math.random() * 5); // Genera un número aleatorio entre 0 i 4 per determinar aleatoriament pel operacions
                        if ((suma && randomOperation == 0) || (multiplicacio && randomOperation == 1) ||
                                (resta && randomOperation == 2) || (modul && randomOperation == 3) ||
                                (exponenciacio && randomOperation == 4)) {
                            switch (randomOperation) {
                                case 0:
                                    int sumatori = 0;
                                    System.out.println(currentBlock);
                                    for (int k = 0; k < mida; k++){
                                        if (blockNumbers[k] == 0 ) break;
                                        if (k > 0) System.out.print(" + ");
                                        sumatori += blockNumbers[k];
                                        System.out.print(blockNumbers[k]);
                                    }
                                    System.out.println(" = " + sumatori);
                                    break;
                                case 1:
                                    int productori = 1;
                                    System.out.println(currentBlock);
                                    for (int k = 0; k < mida; k++){
                                        if (blockNumbers[k] == 0 ) break;
                                        if (k > 0) System.out.print(" * ");
                                        productori *= blockNumbers[k];
                                        System.out.print(blockNumbers[k]);
                                    }
                                    System.out.println(" = " + productori);
                                    break;
                                case 2:
                                    System.out.println(currentBlock);
                                    if(blockNumbers[0] > blockNumbers[1]) System.out.println(blockNumbers[0] + " - " + blockNumbers[1] + " = " + (blockNumbers[0] - blockNumbers[1]));
                                    else System.out.println(blockNumbers[1] + " - " + blockNumbers[0] + " = " + (blockNumbers[1] - blockNumbers[0]));
                                    break;
                                case 3:
                                    System.out.println(currentBlock);
                                    if(blockNumbers[0] > blockNumbers[1]) System.out.println(blockNumbers[0] + " % " + blockNumbers[1] + " = " + (blockNumbers[0] % blockNumbers[1]));
                                    else System.out.println(blockNumbers[1] + " % " + blockNumbers[0] + " = " + (blockNumbers[1] % blockNumbers[0]));
                                    break;
                                case 4:
                                    System.out.println(currentBlock);
                                    System.out.println(blockNumbers[0] + " ^ " + blockNumbers[1] + " = " + (int) Math.pow(blockNumbers[0], blockNumbers[1]));
                                    break;
                            }
                            processedBlocks.add(currentBlock);
                            operationFound = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcio invocada des de CrearKenKenParametres.java quan aquest vol crear un tauler per parametres (es a dir, li dona al boto de crear)
     * La funcio concreetament posa en marxa tota la generacio del tauler
     * 
     * @param grau la dimensio del tauler a generar
     * @param suma indica si el tauler ha de tenir alguna operacio suma o no
     * @param resta indica si el tauler ha de tenir alguna operacio resta o no
     * @param multiplicacio indica si el tauler ha de tenir alguna operacio multiplicacio o no
     * @param divisio indica si el tauler ha de tenir alguna operacio divisio o no
     * @param modul indica si el tauler ha de tenir alguna operacio modul o no
     * @param exponenciacio indica si el tauler ha de tenir alguna operacio exponenciacio o no
     */
    public static void creadora(int grau, boolean suma, boolean resta, boolean multiplicacio, boolean divisio, boolean modul, boolean exponenciacio){
        int size = grau;
        int maxSize = Math.max(grau-3,3);
        CreadorKenkenParam generator = new CreadorKenkenParam(size, maxSize);
        generator.generateSolutionAndBoard();
        generator.printSolution();
        generator.printBlockSolution();
        generator.crearOperaciones(suma, resta, multiplicacio, divisio, modul, exponenciacio);
    }


    // MAIN TEMPORAL PER ANAR PROVANTEL CODI
    public static void main(String[] args) {
        int grau = 9;
        boolean suma = true;
        boolean resta = true;
        boolean multiplicacio = true;
        boolean divisio = true;
        boolean modul = true;
        boolean exponenciacio = true;
        creadora(grau, suma, resta, multiplicacio, divisio, modul, exponenciacio);
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