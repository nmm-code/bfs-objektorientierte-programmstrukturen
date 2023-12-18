package ueb;

/**
 * Diese Klasse beschreibt ein Raum mit einer Dachschräge.<p>
 * dieser Raum hat immer die Nutzungsangabe (STAIRWAY) und die beiden Positionsangaben,<p>
 *
 *
 * @author Nima, Max
 */
public class FunctionalSpace extends Room {

    /**
     * Ist eine Abkürzung für ein FunctionalSpace
     */
    public static final String SHORTCUT = "FS";

    /**
     * Konstruktor Funktion
     */
    FunctionalSpace(String str) {
        super(RoomUsage.STAIRWAY.getShortcut() + " " + str);
    }


    /**
     * Konstruktor Funktion
     *
     * @param pos1 die obere und linke Position
     * @param pos2 die untere und rechte Position
     *
     */
    public FunctionalSpace(Position pos1, Position pos2) {
        super(RoomUsage.STAIRWAY, pos1, pos2);
    }

    /**
     * @return Berechnet die Nutzfläche des Raumes
     */
    @Override
    public int calcEffectiveArea() {
        return 0;
    }

    /**
     * @return Berechnet die Wohnfläche des Raumes
     */
    @Override
    public int calcLivingArea() {
        return 0;
    }

    /**
     * @return liefert die Abkürzung der Klasse
     */
    @Override
    public String getShortcut() {
        return SHORTCUT;
    }
}
