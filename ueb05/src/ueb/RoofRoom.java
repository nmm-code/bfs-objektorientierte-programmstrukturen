package ueb;

/**
 * Diese Klasse beschreibt ein Raum mit einer Dachschräge.<p>
 * dieser Raum hat eine Nutzungsangabe und die beiden Positionsangaben,<p>
 * zusätzlich eine angabe wo die Dachschräge anfängt und die höchste Höhe des Raumes <p>
 *
 * @author Nima, Max
 */
public class RoofRoom extends Room {

    /**
     * Ist eine Abkürzung für einen Raum
     */
    public static final String SHORTCUT = "RR";

    /**
     * die Seite wo die Dachschräge anfängt
     */
    private final Side roofSide;

    /**
     * die höchste Höhe des Raumes
     */
    private final int dis;


    /**
     * Konstruktor Funktion
     *
     * @param params ein String um alle Klassenattribute zu setzen
     * @throws IllegalArgumentException roof != null
     *                                  dis <= 0 muss mindestens ein meter groß sein
     */
    RoofRoom(String params) {
        super(params.substring(0, params.lastIndexOf(' ') - 3));
        String res = params.substring(params.lastIndexOf(' ') - 2);
        String[] part = res.split(" ");
        roofSide = Side.toSide(part[0]);
        dis = Integer.parseInt(part[1]);
        if (dis <= 0)
            throw new IllegalArgumentException("dis is invalid");
    }

    /**
     * Konstruktor Funktion
     *
     * @param usage die Nutzangabe
     * @param pos1  die obere und linke Position
     * @param pos2  die untere und rechte Position
     * @param roof  die Seite wo die Dachschräge anfängt
     * @param dis   die höchste Meter angabe in einem Raum
     * @throws IllegalArgumentException roof != null
     *                                  dis <= 0 muss mindestens ein meter groß sein
     */
    public RoofRoom(RoomUsage usage, Position pos1, Position pos2, Side roof, int dis) {
        super(usage, pos1, pos2);
        if (roof == null)
            throw new IllegalArgumentException("roof is null");

        if (dis <= 0)
            throw new IllegalArgumentException("dis is invalid");

        this.roofSide = roof;
        this.dis = dis;
    }


    /**
     * @return liefert die Abkürzung der Klasse
     */
    @Override
    public String getShortcut() {
        return SHORTCUT;
    }


    /**
     * @return Berechnet die Wohnfläche des Raumes
     */
    @Override
    public int calcLivingArea() {
        int width = (getP2().getY() - (getP1().getY()));
        int height = (getP2().getX() - (getP1().getX()));

        if (!roofSide.isLeftRight()) {
            height = (getP2().getY() - (getP1().getY()));
            width = (getP2().getX() - (getP1().getX()));
        }
        int distTo1m = this.dis;

        if (height <= distTo1m)
           return 0;

        if (height <= (distTo1m * 2))
           return (int) ((height-distTo1m)*width*0.5);

        return (int) (distTo1m * 0.5 * width + (height - (2 * distTo1m)) * width);
    }

    /**
     * @return liefert die Seite der Dachschräge
     */
    public Side getSide() {
        return roofSide;
    }

    /**
     * @return liefert die höchste Höhe des Raumes
     */
    public int getDis() {
        return dis;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getSide().getShortcut() + " " + getDis();
    }

}
