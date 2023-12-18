package ueb.board;

/**
 * Stelle eine Position dar.
 *
 * @author fme, nima, max
 */
public class Position {

    /**
     * X-Koordinate
     */
    private final int x;

    /**
     * Y-Koordinate
     */
    private final int y;

    /**
     * Konstruktor Funktion
     *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gibt die X-Koordinate der Position zurück.
     *
     * @return X-koordinate der Position
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die Y-Koordinate der Position zurück.
     *
     * @return Y-koordinate der Position
     */
    public int getY() {
        return y;
    }

    /**
     * Prüfte, ob diese Position in einem Array von Positionen enthalten ist.
     *
     * @param positions zu prüfendes Array
     * @return true, wenn die Position in einem Array von Positionen enthalten ist
     */
    public boolean isIn(Position[] positions) {
        if (positions != null)
        for (Position i: positions) {
            if (equals(i))
                return true;
        }
        return false;
    }

    /**
     * Liefert einen String mit den beiden durch einen Schrägstrich getrennten Werten.
     *
     * @return die beiden Werte durch einen Schrägstrich getrennt in einem String
     */
    @Override
    public String toString() {
       return this.x + "/" + this.y;
    }

    /**
     * Gibt an, ob zwei Positionen gleich sind. Zwei Positionen sind gleich, wenn ihre
     * Koordinaten gleich sind.
     *
     * @param obj zu vergleichendes Objekt (i.d.R. Position)
     * @return true, wenn Objekte gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Position other))
            return false;

        return x == other.getX() && y == other.getY();
    }
}
