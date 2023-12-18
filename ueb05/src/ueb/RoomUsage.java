package ueb;

/**
 * Aufzählung, die Raumnutzungen repräsentiert.<p>
 * <p>
 * Jede Nutzung ist mit einer Abkürzung verbunden.<p>
 *
 * @author Nima, Max
 */
public enum RoomUsage {
    HALLWAY("walk"), BATH("bath"), COOK("cook"),
    LIVE("live"), WORK("work"), SLEEP("sleep"),
    SAUNA("sauna"), STORE("store"), STAIRWAY("climb");

    /**
     * Beschreibt eine Abkürzung von einer Aufzählung
     */
    private final String shortcut;

    /**
     * Konstruktor Funktion
     *
     * @param shortcut eine Abkürzung um die Aufzählung zu definieren
     */
    RoomUsage(String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * Ruft die entsprechende Raumnutzung basierend auf der angegebenen Abkürzung ab.
     *
     * @param shortcut Die Abkürzung.
     * @return Die entsprechende Raumnutzung.
     * @throws IllegalArgumentException, falls die Abkürzung nicht existiert.
     */
    public static RoomUsage toRoomUsage(String shortcut) {
        RoomUsage[] values = values();
        for (RoomUsage value : values) {
            if (value.getShortcut().equals(shortcut) )
                return value;
        }
        throw new IllegalArgumentException("shortcut not exist");
    }

    /**
     * @return liefert die Abkürzung
     */
    public String getShortcut() {
        return shortcut;
    }
}
