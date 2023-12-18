package ueb;

/**
 * Diese Klasse beschreibt ein spezieller Raum (Kriechkeller).<p>
 * Auch dieser hat wie jeder Raum eine Nutzungsangabe und die beiden Positionsangaben,<p>
 * seine Wohnfläche ist allerdings immer 0.<p>
 *
 * @author Nima, Max
 */
public class CrawlSpace extends Room {

    /**
     * Ist eine Abkürzung für ein CrawlSpace
     */
    public static final String SHORTCUT = "CS";

    /**
     * Konstruktor der Crawlspace.
     *
     * @param usage die Nutzangabe
     * @param p1 die erste position
     * @param p2 die zweite position
     */
    public CrawlSpace(RoomUsage usage, Position p1, Position p2) {
        super(usage, p1, p2);
    }

    public CrawlSpace(String params) {
        super(params);
    }

    /**
     * Ein CrawlSpace hat immer eine Wohnfläche von 0.
     *
     * @return Liefert die Wohnfläche
     */
    @Override
    public int calcLivingArea() {
        return 0;
    }


    @Override
    public String getShortcut() {
        return SHORTCUT;
    }
}

