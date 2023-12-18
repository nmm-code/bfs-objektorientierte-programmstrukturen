package ueb;

/**
 * Die Aufzählung (Enum) namens "Side", die verschiedene Seiten repräsentiert.<p>
 * Jede Seite hat eine zugeordnete Abkürzung (shortcut).<p>
 * Die verfügbaren Seiten sind :<p>
     * "TOP" (oben),<p>
     * "RIGHT" (rechts),<p>
     * "BOTTOM" (unten)<p>
     * "LEFT" (links).
 *
 * @author Nima, Max
 */
public enum Side {
    TOP("TP"),
    RIGHT("RT"),
    BOTTOM("BM"),
    LEFT("LT");

    /**
     * Beschreibt eine Abkürzung von einer Aufzählung
     */
    private final String shortcut;

    /**
     * Konstruktor Funktion
     *
     * @param shortcut eine Abkürzung um die Aufzählung zu definieren
     */
    Side(String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * Getter Methode
     *
     * @return gibt die Abkürzung zurück
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Überprüft die Aufzählung auf den Wert LEFT oder RIGHT
     *
     * @return wahr, wenn die Aufzählung den Wert LEFT oder RIGHT ist
     */
    public boolean isLeftRight() {
        return this == LEFT || this == RIGHT;
    }

    /**
     * Liefert abhängig von der Abkürzung (shortcut) die jeweilige Aufzählung
     *
     * @param shortcut die Abkürzung
     * @return die Aufzählung
     *
     * @throws IllegalArgumentException, falls die Abkürzung nicht existiert.
     */
    public static Side toSide(String shortcut) {
        Side[] values = values();
        for (Side value : values) {
            if (value.getShortcut().equals(shortcut) )
                return value;
        }
        throw new IllegalArgumentException("shortcut not exist");
    }
}
