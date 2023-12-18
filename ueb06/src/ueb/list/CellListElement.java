package ueb.list;

import ueb.board.Position;
import ueb.board.cells.BoardCell;

/**
 * Die Klasse beschreibt eine verkettete Liste
 * die als Information Komponente eine BordCell hat
 *
 * @author nima, max
 */
class CellListElement extends CellList {

    /**
     * Info Komponente
     */
    private final BoardCell info;

    /**
     * Next Referenz
     */
    private CellList next;

    /**
     * Konstruktor
     *
     * @param initCell die info Komponente
     * @param next     die referenz auf die nächste
     */
    CellListElement(BoardCell initCell, CellList next) {
        if (initCell == null || next == null)
            throw new IllegalArgumentException("params null");

        this.info = initCell;
        this.next = next;
    }

    /**
     * @return eine knoten ist immer !leer
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * toString Methode die alle Elemente in ein String umwandelt
     *
     * @return die String darstellung
     */
    @Override
    public String toString() {
        if (next.isEmpty())
            return info.getPosition().toString();
        else
            return info.getPosition() + " -> " + next;
    }

    /**
     * @return größe der Liste
     */
    @Override
    public int size() {
        return this.next.size() + 1;
    }

    /**
     * Getter Methode
     *
     * @return liefert alle Information von Knoten
     */
    @Override
    public BoardCell getPayload() {
        BoardCell res = new BoardCell(new Position(
                info.getPosition().getX(),
                info.getPosition().getY()), info.getType());
        res.setParentCell(info.getParentCell());
        return res;
    }


    /**
     * Überprüft, ob die gegebene Nutzlast, ein Teil der Liste ist.
     *
     * @param payload die gegebene Nutzlast
     * @return true, wenn die gegebene Nutzlast ein Teil der Liste ist
     */
    @Override
    public boolean contains(BoardCell payload) {
        if (payload == null) {
            return false;
        }
        if (info.equals(payload)) {
            return true;
        }
        return this.next.contains(payload);
    }

    /**
     * Löscht das erste Element aus der Liste.
     *
     * @return dieses Element zurück.
     */
    @Override
    public CellList removeFirst() {
        return next;
    }

    /**
     * Gibt das nächste Element der Liste zurück.
     *
     * @return nächstes Element der Liste
     */
    @Override
    public CellList getNext() {
        return next;
    }


    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die gegebene Nutzlast
     * @return eine neue Liste mit der angehängten Nutzlast.
     */
    @Override
    public CellList append(BoardCell payload) {
        next = next.append(payload);
        return this;
    }


    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll
     */
    public CellList insertAtFront(BoardCell payload) {
        return new CellListElement(payload, this);
    }

    /**
     * Gibt das letzte Element der Liste zurück.
     *
     * @return letztes Element der Liste.
     */
    @Override
    public CellList getLastElement() {

        if (next.isEmpty())
            return this;

        return next.getLastElement();

    }

}
