package ueb;

/**
 * Eine verkettete Liste mit Elementen vom Typ char.
 * Die Liste unterstützt das Einfügen, Löschen und Durchsuchen von Elementen.
 * Außerdem bietet sie Methoden zur Überprüfung auf Sortierung und zur Ausgabe der Elemente.
 *
 * Die Liste wird durch einen Verweis auf das erste Element (Kopf) repräsentiert.
 * Ein leeres Element bedeutet eine leere Liste.
 *
 * Autor: hs, Nima, Max
 */
public class MyList {

    /* Verkettete Liste mit Knoten Elemente von Chars */
    private Element elements;

    /**
     * Kontrolliert ob das Element leer ist
     *
     * @return gibt ein True zurück, wenn das Element leer ist / False, wenn es nicht leer ist
     * @author hs
     */
    public boolean isEmpty() {
        return elements == null;
    }

    /**
     * Fügt den eingegeben char am kopf der liste ein
     *
     * @param value Char der in die Liste eingefügt wird
     * @author hs
     */
    public void appendElement(char value) {
        if (this.isEmpty())
            elements = new Element(value, null);
        else
            elements = elements.appendElement(value);
    }

    /**
     * Fügt ein char sortiert in die liste ein
     *
     * @param value Char der in die Liste eingefügt wird
     * @author hs
     */
    public void insertElement(char value) {
        if (isEmpty())
            elements = new Element(value, null);
        else
            elements = elements.insertElement(value);

    }

    /**
     * Löscht ein char aus der liste
     *
     * @param value der zu löschende char
     * @author hs
     */
    public void deleteElement(char value) {
        if (!isEmpty())
            elements = elements.deleteElement(value);
    }

    /**
     * Schreibt die Liste auf Stdout
     *
     * @author hs
     */
    public void printList() {
        if (!isEmpty())
            elements.printList();
    }

    /**
     * Liefert die Anzahl der Knoten zurück
     *
     * @return die Anzahl
     *
     * @author Nima, Max
     */
    public int size() {
        return isEmpty() ? 0 : elements.size();
    }

    /**
     * Überprüft, ob die Liste sortiert wurde, eine leere Liste ist nicht sortiert.
     *
     * @return true, wenn die Liste sortiert wurde
     *
     * @author Nima, Max
     */
    public boolean isSorted() {
        return !isEmpty() && elements.isSorted();
    }

    /**
     * Überprüft, ob die Liste das Element enthält
     *
     * @param value das Element
     *
     * @return true, wenn es mindestens einmal vorkommt
     *
     * @author Nima, Max
     */
    public boolean containsElement(char value) {
        return !isEmpty() && elements.containsElement(value);
    }

    /**
     * Liefert die elements als Liste dar
     *
     * @return ein String mit Zeichen
     *
     * @author Nima, Max
     */
    public String toString() {
        String res = isEmpty() ? "" : elements.toString();
        return "{" + res + "}";
    }


    /**
     * Liefert die Zeichen als Char Array
     *
     * @return ein Char Array mit den Zeichen
     *
     * @author Nima, Max
     */
    char[] getValues() {
        int max = size();
        char[] res = new char[max];
        for (int i = 0; i < max; i++) {
            res[i] = elements.getElementAt(i);
        }
        return res;
    }

    /**
     * Gibt der Wert an der angegebenen Position zurück,
     * wenn der index ungültig ist, wird INVALID_VALUE zurückgegeben
     *
     * @param index der Index
     * @return gibt die Position zurück
     *
     * @author Nima, Max
     */
    public char getElementAt(int index) {
        return isEmpty() ? Element.INVALID_VALUE : elements.getElementAt(index);
    }

    /**
     * Fügt ein wert anfang der liste ein
     *
     * @param value Char der am anfang der list eingefügt wird
     * @author Nima, Max
     */
    public void prependElement(char value) {
        if (isEmpty()) {
            elements = new Element(value, null);
        } else {
            elements = elements.prependElement(value);
        }
    }

    /**
     * Fügt ein Char an einer angegebenen Position in der Liste ein
     *
     * @param value Char der eingefügt wird
     * @param index Die Position an der eingefügt wird
     *
     * @author Nima, Max
     */
    public void insertElementAt(char value, int index) {
        if (!isEmpty())
            elements = elements.insertElementAt(value, index);
        else if (index == 0)
            insertElement(value);
    }

    /**
     * Fügt ein char sortiert in die Liste ein
     *
     * @param value Char der eingefügt wird
     *
     * @author Nima, Max
     */
    public void insertSortedIfUnique(char value) {
        if (!containsElement(value))
            insertElement(value);
    }

}