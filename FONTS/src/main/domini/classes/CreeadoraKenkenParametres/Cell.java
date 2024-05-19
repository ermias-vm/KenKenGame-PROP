package main.domini.classes.CreeadoraKenkenParametres;

public class Cell {
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