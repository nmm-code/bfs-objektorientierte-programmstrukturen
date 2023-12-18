package ueb;

/**
 * Die Klasse Position repräsentiert eine Position im zweidimensionalen Raum.
 *
 * @author Nima, Max
 */
public class Position {

    /**
     * Beschreibung vom x-index
     */
    private final int x;

    /**
     * Beschreibung vom y-index
     */
    private final int y;

    /**
     * Konstruktor der Position-Klasse.
     *
     * @param x Die x-Koordinate der Position.
     * @param y Die y-Koordinate der Position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstruktor der Position-Klasse, der eine Zeichenkette im Format "x,y" als Parameter erwartet.
     *
     * @param str Eine Zeichenkette im Format "x,y" zur Erstellung der Position.
     * @throws IllegalArgumentException Wenn die übergebene Zeichenkette ungültig ist.
     */
    Position(String str) {
        if (str == null) throw new IllegalArgumentException("null is invalid");

        str = str.replace(" ", "");

        String[] num = str.split(",");
        if (  num.length != 2 ) {
            throw new IllegalArgumentException("str is invalid");
        }
        this.x = Integer.parseInt(num[0]);
        this.y = Integer.parseInt(num[1]);
    }

    /**
     * Gibt die x-Koordinate der Position zurück.
     *
     * @return Die x-Koordinate der Position.
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die y-Koordinate der Position zurück.
     *
     * @return Die y-Koordinate der Position.
     */
    public int getY() {
        return y;
    }

    /**
     * Gibt eine Zeichenkette zurück, die die Koordinaten der Position im Format "x,y" repräsentiert.
     *
     * @return Eine Zeichenkette im Format "x,y" der Position.
     */
    @Override
    public String toString() {
        return this.x + "," + this.y;
    }

    /**
     * Überprüft, ob das angegebene Objekt mit dieser Position gleich ist.
     *
     * @param obj Das Objekt, das mit dieser Position verglichen werden soll.
     * @return true, wenn das Objekt die gleichen Koordinaten wie diese Position hat, andernfalls false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Position other)) {
            return false;
        }
        return x == other.x && y == other.y;
    }


}
