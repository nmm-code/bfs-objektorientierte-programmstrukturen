package ueb;

/**
 * Set-Klasse <p>
 * <p>
 * Die vorliegende Java-Klasse implementiert eine Set-Datenstruktur,
 * die es ermöglicht, Mengen von Zeichen zu erstellen und verschiedene Operationen auf ihnen auszuführen.
 *
 * @author Nima, Max
 */
public class Set {
    /* Beschreibt eine Menge von chars */
    private Element elem;

    /**
     * Konstruktor Funktion die aus beliebigen chars, die Menge füllt
     *
     * @param array die eingabe
     */
    Set(char... array) {
        for (char ch : array)
            addElement(ch);
    }

    /**
     * Konstruktor Funktion die aus ein String, die Menge füllt
     *
     * @param str die eingabe
     */
    Set(String str) {
        if (str.length() > 0) {
            addElements(str);
        }
    }


    /**
     * Liefert ein Array mit allen enthaltenen Werten
     *
     * @return ein char Array mit den jeweiligen Werten
     */
    char[] toArray() {
        int max = size();
        char[] res = new char[max];
        for (int i = 0; i < max; i++) {
            res[i] = elem.getElementAt(i);
        }
        return res;
    }

    /**
     * @return true, wenn die Liste leer ist
     */
    public boolean isEmpty() {
        return elem == null;
    }

    /**
     * Prüft, ob die übergebene Menge die gleichen Werte enthält wie die aktuelle
     *
     * @param other die andere Menge
     * @return true wenn es die gleiche menge ist
     */
    public boolean isEqual(Set other) {
        boolean res = false;

        if (other != null) {
            if (isEmpty() && other.isEmpty()) res = true;
            else if (!other.isEmpty() && !isEmpty()) if (other.elem.isEqual(elem)) res = true;
        }
        return res;
    }

    /**
     * Fügt ein neues Element mit übergebenem Wert zu, wenn der Wert noch nicht
     * existiert.
     * Ein Wert kann also nur ein Mal in der Menge vorkommen.
     *
     * @param value der einfügende Wert
     */
    public void addElement(char value) {
        if (!isEmpty()) {
            if (!elem.containsElement(value)) elem = elem.insertElement(value);
        } else elem = new Element(value);

    }

    /**
     * Prüft, ob der Wert schon in der Elementliste existiert,
     *
     * @param value der überprüfende Wert
     * @return true wenn value vorhanden ist
     */
    public boolean containsElement(char value) {
        return !isEmpty() && elem.containsElement(value);
    }

    /**
     * Löscht ein Element dieses Wertes; falls es nicht vorhanden ist, passiert
     * nichts
     *
     * @param value der löschende Wert
     */
    public void deleteElement(char value) {
        if (!isEmpty()) if (elem.containsElement(value)) elem = elem.deleteElement(value);
    }

    /**
     * @return liefert die Anzahl der Elemente dieser Menge
     */
    public int size() {
        return isEmpty() ? 0 : elem.size();
    }

    /**
     * Liefert eine String darstellung des Mengeninhalts mit umschließenden
     * geschweiften Klammern,
     * jeweils einem Leerzeichen zwischen den Werten,
     */
    public String toString() {
        String res = isEmpty() ? "" : elem.toString();
        return "{" + res + "}";
    }

    /**
     * Fügt alle noch nicht enthaltenen Werte einer übergebenen Liste vom Typ
     * Element als Elemente zu
     *
     * @param list, die einfügende Liste
     */
    void addElementList(Element list) {
        Element run = list;
        while (run != null) {
            addElement(run.getValue());
            run = run.getNext();
        }
    }


    /**
     * Fügt der Menge jedes Zeichen des Strings als Mengenelement zu.
     *
     * @param s die
     */
    public void addElements(String s) {
        for (int i = 0; i < s.length(); i++) {
            addElement((s.charAt(i)));
        }
    }

    /**
     * Erstellt eine Kopie der aktuellen Menge.
     *
     * @return eine kopie von der Menge
     */
    public Set cloneSet() {
        Set res = new Set();
        res.addElementList(elem);
        return res;
    }

    /**
     * Liefert eine neue Menge, die die Vereinigung der aktuellen mit der
     * übergebenen abbildet
     *
     * @param other die andere Menge
     * @return die Vereinigungsmenge
     */
    Set union(Set other) {
        Set res = cloneSet();
        if (other != null)
            res.addElementList(other.elem);
        return res;
    }

    /**
     * Liefert eine neue Menge, die die Schnittmenge der aktuellen mit der
     * übergebenen abbildet,
     *
     * @param other die andere Menge
     * @return liefert die Schnittmenge
     */
    Set intersection(Set other) {
        Set res = new Set();
        if (other != null) {
            Element run = other.elem;
            int sizeList = other.size();

            for (int i = 0; i < sizeList; i++) {
                if (containsElement(run.getValue()))
                    res.addElement(run.getValue());
                run = run.getNext();
            }
        }
        return res;
    }

    /**
     * Liefert eine neue Menge, die die Differenzmenge der aktuellen mit der
     * übergebenen abbildet
     *
     * @param other die andere Menge
     * @return liefert die Differenz
     */
    Set diff(Set other) {
        Set res = cloneSet();
        Element run = elem;
        int sizeList = size();
        if (other != null) {
            for (int i = 0; i < sizeList; i++) {
                if (other.containsElement(run.getValue()))
                    res.deleteElement(run.getValue());
                run = run.getNext();
            }
        }
        return res;

    }

    /**
     * Liefert eine neue Menge, die die symmetrische Differenz dieser und der
     * übergebenen abbildet,
     *
     * @param other die andere Menge
     * @return die symmetrische differenz Menge
     */
    Set symDiff(Set other) {
        Set res = cloneSet();
        if (other != null) {
            Set s1 = res.diff(other);
            Set s2 = other.diff(res);
            res = s1.union(s2);
        }
        return res;
    }

    /**
     * Prüft, ob diese Menge eine echte Teilmenge der übergebenen ist
     *
     * @param other die überprüfende Menge
     * @return true, wenn es eine Teilmenge ist
     */
    Boolean isProperSubSet(Set other) {
        return other != null && union(other).size() == other.size() && size() < other.size();
    }
}
