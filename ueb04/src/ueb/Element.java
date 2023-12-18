package ueb;

/**
 * Diese Klasse beschreibt eine verkettete Liste enthält
 *      value: char
 *      next:element
 *
 * @author hs, Nima, Max
 */
public class Element {
    /* Beschreibt ein invaliden Char */
    public static final char INVALID_VALUE = 0;
    /* Knoten-Element Wert */
    private final char value;
    /* Next Element */
    private Element next;

    /**
     * Konstruktor Funktion
     *
     * @param value, ein Zeichen für den Knoten
     * @param next,  referenz auf den nächsten
     * @author hs
     */
    public Element(char value, Element next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Konstruktor Funktion
     *
     * @param value, ein Zeichen für den Knoten
     */
    public Element(char value) {
        this(value, null);
    }

    /**
     * Hängt ein Element an die Liste dran
     *
     * @param value, ein Zeichen für das anhängende Element
     * @return der Wurzelknoten
     * @author hs
     */
    public Element appendElement(char value) {
        if (this.next == null)
            this.next = new Element(value, null);
        else
            this.next.appendElement(value);
        return this;
    }


    /**
     * Fügt ein Element zu
     *
     * @param value, das einfügende Zeichen
     * @return den Wurzelknoten
     * @author hs
     */
    public Element insertElement(char value) {
        if (!this.isPredecessor(value))
            return new Element(value, this);
        else if (this.next == null) {
            this.next = new Element(value, null);
            return this;
        } else {
            this.next = this.next.insertElement(value);
            return this;
        }
    }

    /**
     * Löscht ein Element
     * 
     *
     * @param value, das löschende Zeichen
     * @return den Wurzelknoten
     * @author hs
     */
    public Element deleteElement(char value) {

        if (this.value == value) {
            return this.next;
        } else if (this.next != null && this.isPredecessor(value))
            this.next = this.next.deleteElement(value);

        return this;
    }

    /**
     * Gibt die Liste aus
     *
     * @author hs
     */
    public void printList() {
        System.out.println(value);
        if (this.next != null)
            this.next.printList();
    }

    /**
     * Gibt den Value von dem Element
     *
     * @return den Zeichen
     * @author Nima, Max
     */
    public char getValue() {
        return value;
    }

    /**
     * Gibt das nächte Element zurück
     *
     * @return nächstes Element
     * @author Nima, Max
     */
    Element getNext() {
        return next;
    }

    /**
     * Liefert die Anzahl der Elemente
     *
     * @return die Anzahl
     * @author Nima, Max
     */
    public int size() {
        int i = 1;
        if (this.next != null) {
            i += this.next.size();
        }
        return i;
    }

    /**
     * Liefert true, wenn dieses oder eines der folgenden Elemente den übergebenen Wert enthält
     *
     * @return true wenn vorhanden,
     * false wenn nicht
     * @author Nima, Max
     */
    public boolean containsElement(char value) {
        boolean res = true;
        if (this.value != value ) {
            if (!this.isPredecessor(value))
                res = false;
            else if (this.next == null) {
                res = false;
            } else {
                res = this.next.containsElement(value);
            }
        }
        return res;
    }

    /**
     * liefert true, wenn kein Element folgt oder die folgenden Elemente
     * jeweils keinen kleineren Wert enthalten als ihr Vorgänger.
     * Es gilt die Reihenfolge der Ascii-Werte, also (a < b) und (B < a)
     * <p>
     *
     * @return true, wenn die Liste sortiert ist
     * @author Nima, Max
     */
    public boolean isSorted() {
        boolean res = true;
        if (this.next != null) {
            if (this.value > this.next.value) {
                res = false;
            } else {
                res = this.next.isSorted();
            }
        }
        return res;
    }


    /**
     * Verwandelt die Liste in ein String
     *
     * @return die Liste als String
     * @author Nima, Max
     */
    public String toString() {
        String res;
        if (this.next != null) {
            res = this.value + " " + this.next;
        } else
            res = "" + this.value;
        return res;
    }

    /**
     * Liefert den Wert des Elements an der Stelle x (Zählung beginnt bei 0).
     * Wurde ein falscher Index angegeben, wird der INVALID_VALUE zurückgegeben.
     *
     * @param index der Index in der Liste
     * @return das Element an der Position index
     * @author Nima, Max
     */
    public char getElementAt(int index) {
        char res = this.value;

        if (index != 0) {
            if (this.next == null || index < 0)
                res = INVALID_VALUE;
            else
                res = this.next.getElementAt(index - 1);
        }
        return res;
    }

    /**
     * Fügt ein neues Element mit dem übergebenen Wert vor das aktuelle Element ein
     *
     * @param value übergebene Wert
     * @return die neue Liste
     * @author Nima, Max
     */
    Element prependElement(char value) {
        return new Element(value, this);
    }


    /**
     * Gibt true wieder, wenn this.value kleiner als der übergebene Wert ist
     *
     * @param value übergebene Wert
     * @return wenn value kleiner ist
     * @author Nima, Max
     */
    boolean isPredecessor(char value) {
        return ((Character.toUpperCase(this.value) == Character.toUpperCase(value)) && ((this.value) < value))   || (Character.toUpperCase(this.value) < Character.toUpperCase(value)) ;
    }


    /**
     * Überprüft auf Gleichheit
     * @param other , dass andere Objekt
     * @return true, wenn sie gleich sind
     * @author Nima, Max
     */
    public boolean isEqual(Element other) {
        boolean res = true;

        if (other == null) 
            res = false;
        else if (this.value != other.value)
            res = false;
        else if (getNext() != null)
            res = this.next.isEqual(other.next);
        else if (this.next != other.next)
            res = false;
            
        return res;
    }

}
