package ueb.list;

import ueb.board.cells.BoardCell;

/**
 * Stellt eine verkettete Liste von Spielbrettzellen dar. Interface muss vom Element und dem
 * leeren Element implementiert werden.
 *
 * @author fme
 */
public interface CellListInterface {
    /**
     * Liefert die Nutzlast des aktuellen Elements. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError geworfen, da die leere Liste keine Elemente enthält.
     *
     * @return die Nutzlast des aktuellen Elements
     */
    BoardCell getPayload();

    /**
     * Gibt das nächste Element der Liste zurück. Wenn diese Methode auf der leeren Liste aufgerufen wird, wird ein
     * AssertionError geworfen, da die leere Liste keine Elemente enthält.
     *
     * @return nächstes Element der Liste
     */
    CellListInterface getNext();

    /**
     * Ermittelt, ob die Liste leer ist. In der Vorlesung besprochen.
     *
     * @return true, wenn die Liste leer ist.
     */
    boolean isEmpty();

    /**
     * Ermittelt, die Größe der Liste. 0 für die leere Liste. In der Vorlesung besprochen.
     *
     * @return Größe der Liste
     */
    int size();

    /**
     * Ermittelt die Gesamtkosten der Liste, also die Summe der Kosten jeder Zelle.
     *
     * @return Kosten der Liste
     */
    int listCosts();

    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll.
     * @return eine neue Liste mit der gegebenen Nutzlast und this als Nachfolger
     */
    CellListInterface insertAtFront(BoardCell payload);


    /**
     * Löscht das erste Element aus der Liste.
     *
     * @return dieses Element zurück.
     */
    CellListInterface removeFirst();

    /**
     * Überprüft, ob die gegebene Nutzlast, ein Teil der Liste ist.
     *
     * @param payload die gegebene Nutzlast
     * @return true, wenn die gegebene Nutzlast ein Teil der Liste ist
     */
    boolean contains(BoardCell payload);

    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die gegebene Nutzlast
     * @return eine neue Liste mit der angehängten Nutzlast.
     */
    CellListInterface append(BoardCell payload);

    /**
     * Entfernt die gegebene Nutzlast von der Liste. Die leere Liste bleibt unverändert. Wenn die Nutzlast nicht Teil
     * der Liste ist, wird die Liste ebenfalls nicht verändert.
     *
     * @param payload Zu entfernende Nutzlast
     * @return eine neue Liste mit der entfernten Nutzlast
     */
    CellListInterface remove(BoardCell payload);

    /**
     * Gibt das letzte Element der Liste zurück. CellListEmpty, wenn die Liste leer ist.
     *
     * @return letztes Element der Liste. CellListEmpty, wenn Liste leer ist.
     */
    CellListInterface getLastElement();

    /**
     * Gibt die Zelle aus der Liste zurück, die aktuell am schnellsten von einem Startknoten
     * aus erreicht werden kann, also die kleinsten `accumulatedCosts` hat.
     * Diese akkumulierten Kosten wurden bei Ablauf des Dijkstra-Algorithmus gesetzt.<br>
     * Bei mehreren gleich kleinen Werten soll der erste gefundene zurückgegeben werden.
     *
     * @param currentMinAccumulatedCosts Aktuell kleinste Gesamtkosten (nötig für Rekursion)
     * @param currentCell    Zelle mit den aktuell kleinsten Gesamtkosten (nötig für Rekursion)
     * @return Zelle mit der aktuell schnellsten Strecke von einem Startknoten aus
     */
    BoardCell findMinimumAccumulatedCosts(int currentMinAccumulatedCosts, BoardCell currentCell);

    /**
     * Fügt die Positionen aller in der Liste enthaltenen Zellen durch ` → ` getrennt zusammen.
     * Es entsteht dann beispielsweise der String `2/2 -> 3/2 -> 3/3`. Nach der letzten Position
     * soll also kein Trennzeichen folgen.
     * Ist die Liste leer, wird "EMPTY" zurückgegeben.
     *
     * @return Position dieser payload und der folgenden getrennt durch ` -> `
     */
    @Override
    String toString();

}
