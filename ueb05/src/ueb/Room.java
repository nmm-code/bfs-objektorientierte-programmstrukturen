package ueb;

/**
 * Diese Klasse beschreibt ein Raum.<p>
 * jeder Raum hat eine Nutzungsangabe und die beiden Positionsangaben <p>
 *
 * @author Nima, Max
 */
public class Room {
    /**
     * Ist eine Abkürzung für einen Raum
     */
    public static final String SHORTCUT = "RO";

    /**
     * Eine Nutzungsangabe des Raumes
     */
    private final RoomUsage usage;


    /**
     * Die obere Position
     */
    private Position p1;

    /**
     * Die untere Position
     */
    private Position p2;

    /**
     * Konstruktor Funktion
     *
     * @param usage die Nutzangabe
     * @param p1    die obere und linke Position
     * @param p2    die untere und rechte Position
     * @throws IllegalArgumentException wenn einer der Fälle eintritt <p>
     *                                  * ein parameter null ist <p>
     *                                  * p1,p2 gleich sind <p>
     *                                  * top > bottom oder links > rechts <p>
     */
    public Room(RoomUsage usage, Position p1, Position p2) {
        if (p1 == null || p2 == null || usage == null) throw new IllegalArgumentException("null is invalid");

        if (p1.equals(p2)) throw new IllegalArgumentException("same position");

        if (p1.getY() > p2.getY() || p1.getX() > p2.getX()) throw new IllegalArgumentException("p2 > p1 ");

        this.usage = usage;
        this.p1 = new Position(p1.getX(),p1.getY());
        this.p2 = new Position(p2.getX(),p2.getY());
    }

    /**
     * Konstruktor Funktion
     *
     * @param params ein String um alle Klassenattribute zu setzen
     * @throws IllegalArgumentException wenn einer der Fälle eintritt <p>
     *                                  * ein parameter null ist <p>
     *                                  * der String muss dieses Format besitzen: <p>
     *                                  [a-zA-Z]+ [0-9]+,[0-9]+ [0-9]+,[0-9]+ <p>
     *                                  * p1,p2 gleich sind <p>
     *                                  * top > bottom oder links > rechts <p>
     */
    public Room(String params) {
        if (params == null)
            throw new IllegalArgumentException("params is null");

        String[] parts = params.split(" ");
        this.usage = RoomUsage.toRoomUsage(parts[0]);
        this.p1 = new Position(parts[1]);
        this.p2 = new Position(parts[2]);

        if (p1.getX() == p2.getX() && p1.getY() == p2.getY())
            throw new IllegalArgumentException("same position");

        if (p1.getX() > p2.getX() || p1.getY() > p2.getY())
            throw new IllegalArgumentException("p2 > p1");
    }


    /**
     * @return Berechnet die Fläche des Raumes
     */
    public int calcBaseArea() {
        return (p2.getY() - p1.getY()) * (p2.getX() - p1.getX());
    }

    /**
     * @return Berechnet die Nutzfläche des Raumes
     */
    public int calcEffectiveArea() {
        return calcBaseArea();
    }

    /**
     * @return Berechnet die Wohnfläche des Raumes
     */
    public int calcLivingArea() {
        return calcBaseArea();
    }

    /**
     * Equals Methode die nur auf die Typen schaut, ob die usage, die Fläche gleich ist
     *
     * @param obj, das überprüfende Objekt
     * @return Liefert true, wenn die usage und die Fläche gleich ist
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room other) {
            return (usage == other.usage) &&
                    ((calcLivingArea() == other.calcLivingArea()) || (calcEffectiveArea() == other.calcEffectiveArea()));
        }
        return false;
    }

    /**
     * toString Methode die ein Raum zu einem String umwandelt
     *
     * @return ein String der alle Information von einem Raum hat
     */
    @Override
    public String toString() {
        return "%s %s %d,%d %d,%d".formatted(getShortcut(), usage.getShortcut(), p1.getX(), p1.getY(),p2.getX() ,p2.getY());
    }

    /**
     * @return liefert die Nutzungsangabe
     */
    public RoomUsage getRoomUsage() {
        return usage;
    }

    /**
     * @return liefert die obere und linke angabe
     */
    public Position getPosTL() {
        return new Position(p1.getX(), p1.getY());
    }

    /**
     * @return liefert die untere und rechte angabe
     */
    public Position getPosBR() {
        return new Position(p2.getX(), p2.getY());
    }

    /**
     * @return liefert die Abkürzung des Raumes
     */
    public String getShortcut() {
        return SHORTCUT;
    }

    public Position getP1() {
        return p1;
    }

    public Position getP2() {
        return p2;
    }

    public RoomUsage getUsage() {
        return usage;
    }
}
