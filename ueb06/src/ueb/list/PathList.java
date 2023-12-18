package ueb.list;

import ueb.board.Position;
import ueb.board.cells.BoardCell;

/**
 * Listen-Klasse, die einen Pfad repräsentiert und die Implementierung der Liste verbirgt.
 *
 * @author fme, nima, max
 */
public class PathList {

    /**
     * Einstiegspunkt der Liste. Referenz auf das erste Element.
     */
    private CellList head;

    /**
     * Konstruktor, der eine leere Liste erstellt
     */
    public PathList() {
        head = new CellList();
    }

    /**
     * Konstruktor, welcher nur fürs Testen verwendet wird. Erstellt eine Liste mit den in varArgs übergebenen Werten.
     * Zum Aufruf können beliebig viele Parameter dieses Typs angegeben werden
     * (z.B. <code>new PathList(cellA, cellB)</code>).
     * Zur Verarbeitung kann values wie ein Array genutzt werden (<code>BoardCell firstParam = values[0]</code>).
     * Verwendet die Methode <code>insertAtFront()</code> zum Einfügen.
     *
     * @param values alle Werte, welche zur Liste hinzugefügt werden sollen
     */
    PathList(BoardCell... values) {
        this();
        if (values != null) {
            for (int i = values.length - 1; i >= 0; i--) {
                insertAtFront(values[i]);
            }
        }
    }

    /**
     * Funktion nötig ausschließlich zum Testen der Pfadliste.
     *
     * @return Einstieg in die Liste
     */
    CellList getListStart() {
       return head;
    }

    /**
     * Ermittelt, ob die Liste leer ist
     *
     * @return true, wenn die Liste leer ist
     */
    public boolean isEmpty() {
        return head.isEmpty();
    }

    /**
     * Ermittelt die Anzahl an Zellen/Knoten im Pfad.
     *
     * @return Anzahl an Zellen im Pfad
     */
    public int cellAmountInPath() {
        return head.size();
    }

    /**
     * Fügt die übergebene Nutzlast am Anfang der Liste ein.
     *
     * @param payload die Nutzlast, welche der Liste hinzugefügt werden soll
     */
    public void insertAtFront(BoardCell payload) {
        head = head.insertAtFront(payload);
    }

    /**
     * Löscht das erste Element der Pfadliste.
     */
    public void removeAtFirst() {
        head = head.removeFirst();
    }

    /**
     * Gibt die Nutzlast des ersten Elements zurück.
     *
     * @return Nutzlast des ersten Elements
     */
    public BoardCell getFirstElement() {
        return head.getPayload();
    }

    /**
     * Gibt an, ob die Liste eine bestimmte Nutzlast enthält.
     *
     * @param payload zu prüfende Nutzlast
     * @return true, wenn die Nutzlast (hier eine Board Cell) in der Liste enthalten ist.
     */
    public boolean contains(BoardCell payload) {
        return head.contains(payload);
    }

    /**
     * Hängt die übergebene Nutzlast am Ende der Liste an.
     *
     * @param payload die Nutzlast, welche an die Liste angehängt werden soll
     */
    public void append(BoardCell payload) {
        head = head.append(payload);
    }

    /**
     * Konvertiert die Pfadliste in ein Array von Positionen.
     *
     * @return  Array von Positionen
     */
    public Position[] toPositionArray() {
        Position[] res = new Position[head.size()];
        CellList tmp = head;
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.getPayload().getPosition();
            tmp = tmp.getNext();
        }
        return res;
    }


    /**
     * wandelt die verkettete Liste in einen String um
     *
     * @return liefert die String schreibweise
     */
    @Override
    public String toString() {
        return head.toString();
    }
}
