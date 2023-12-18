package ueb;

import java.util.Arrays;

/**
 * Diese Klasse beschreibt eine Wohnung als Array von Räumen.
 *
 * @author Nima, Max
 */
public class Flat {
    Room[] rooms;

    /**
     * Konstruktor Funktion
     *
     * @param r ein Array von Räumen
     *
     */
    Flat(Room... r) {
        rooms = new Room[0];
        for (Room room : r) {
            add(room);
        }
    }

    /**
     * Konstruktor Funktion
     *
     * @param params ein String um alle Klassenattribute zu setzen
     *
     *
     * @throws IllegalArgumentException bei params == null
     */
    Flat(String params) {
        if ( params == null)
            throw new IllegalArgumentException("null");

        String[] parts = params.split("\n");
        rooms = new Room[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String shortcut = parts[i].substring(0, 2);
            String room = parts[i].substring(2).trim();
            switch (shortcut) {
                case  Room.SHORTCUT:
                    rooms[i] = new Room(room);
                    break;

                case  FunctionalSpace.SHORTCUT:
                    rooms[i] = new FunctionalSpace(room);
                    break;

                case CrawlSpace.SHORTCUT:
                    rooms[i] = new CrawlSpace(room);
                    break;

                case  RoofRoom.SHORTCUT:
                    rooms[i] = new RoofRoom(room);
                    break;
            }
        }
    }

    /**
     * Überprüft, ob das angegebene Objekt mit dieser Wohnung gleich ist.
     *
     * @param obj Das Objekt, das mit dieser Flat verglichen werden soll.
     * @return true, wenn das Objekt die gleichen Räume wie diese Wohnung hat,
     *         andernfalls false.
     */
    @Override
    public boolean equals(Object obj) {
        boolean res = true;
        if (obj instanceof Flat fl && fl.rooms.length == rooms.length) {
            for (int i = 0; i < rooms.length && res; i++) {
                res = res && rooms[i].equals(fl.rooms[i]);
            }
        } else
            res = false;
        return res;
    }

    /**
     * Gibt eine Zeichenkette zurück, die die Grundfläche, nutzfläche, wohnfläche und
     * jeden Raum angibt.
     *
     * @return Eine Zeichenkette mit den werten der Räume.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                "%d Räume mit Grundfläche %d, Nutzfläche %d und Wohnfläche %d\n"
                        .formatted(getCountOfRooms(), calcBaseArea(), calcEffectiveArea(), calcLivingArea()));

        for (int i = 0; i < rooms.length; i++) {
            res.append(rooms[i]).append("\n");
        }
        return res.toString();
    }

    /**
     * Gibt die Anzahl der Räume der Wohnung zurück.
     *
     * @return Int Anzahl der Räume.
     */
    public int getCountOfRooms() {
        return rooms.length;
    }

    /**
     * @return Berechnet die Fläche der Wohnung
     */
    public int calcBaseArea() {
        int sum = 0;
        for (int i = 0; i < rooms.length; i++) {
            sum += rooms[i].calcBaseArea();
        }

        return sum;
    }

    /**
     * @return Berechnet die Wohnfläche der Wohnung
     */
    public int calcLivingArea() {
        int sum = 0;
        for (int i = 0; i < rooms.length; i++) {
            sum += rooms[i].calcLivingArea();
        }

        return sum;
    }

    /**
     * @return Berechnet die Nutzfläche der Wohnung
     */
    public int calcEffectiveArea() {
        int sum = 0;
        for (Room room : rooms) {
            sum += room.calcEffectiveArea();
        }

        return sum;
    }
    /**
     * Fügt den übergebenen Raum zu Wohnung hinzu.
     *
     * @param room Der Raum der hinzugefügt wird.
     *
     * @throws IllegalArgumentException bei room == null
     *
     */
    public void add(Room room) {
        if (room == null)
            throw new IllegalArgumentException("room is null");

        rooms = Arrays.copyOf(rooms, rooms.length + 1);
        rooms[rooms.length - 1] = room;
    }

}
