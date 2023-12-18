package ueb.list;

import ueb.board.cells.BoardCell;

/**
 * Repräsentiert die leere Liste (oder das Ende der Liste). Wird beerbt von der Klasse
 * CellListElement.
 *
 * @author fme
 */
class CellList {

    /**
     * Liefert die Nutzlast des aktuellen Elements. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError geworfen, da die leere Liste keine Elemente enthält.
     *
     * @return die Nutzlast des aktuellen Elements
     */
    public BoardCell getPayload() {
        throw new AssertionError("there is no payload in empty list");
    }

    /**
     * Gibt das nächste Element der Liste zurück. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError geworfen, da die leere Liste keine Elemente enthält.
     *
     * @return nächstes Element der Liste
     */
    public CellList getNext() {
        throw new AssertionError("there is no next element in empty list");
    }

    /**
     * Ermittelt, ob die Liste leer ist. In der Vorlesung besprochen.
     *
     * @return true, wenn die Liste leer ist.
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Ermittelt, die Größe der Liste. 0 für die leere Liste. In der Vorlesung besprochen.
     *
     * @return Größe der Liste
     */
    public int size() {
        return 0;
    }

    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll.
     * @return eine neue Liste mit der gegebenen Nutzlast und this als Nachfolger
     */
    public CellList insertAtFront(BoardCell payload) {
        return new CellListElement(payload, this);
    }

    /**
     * Löscht das erste Element aus der Liste.
     *
     * @return dieses Element zurück.
     */
    public CellList removeFirst() {
        return this;
    }

    /**
     * Überprüft, ob die gegebene Nutzlast, ein Teil der Liste ist.
     *
     * @param payload die gegebene Nutzlast
     * @return true, wenn die gegebene Nutzlast ein Teil der Liste ist
     */
    public boolean contains(BoardCell payload) {
        return false;
    }

    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die gegebene Nutzlast
     * @return eine neue Liste mit der angehängten Nutzlast.
     */
    public CellList append(BoardCell payload) {
        return new CellListElement(payload, this);
    }

    /**
     * Gibt das letzte Element der Liste zurück. Dies Element, wenn die Liste leer ist.
     *
     * @return letztes Element der Liste. Dies Element, wenn Liste leer ist.
     */
    public CellList getLastElement() {
        return this;
    }

    @Override
    public String toString() {
        return "EMPTY";
    }

}
