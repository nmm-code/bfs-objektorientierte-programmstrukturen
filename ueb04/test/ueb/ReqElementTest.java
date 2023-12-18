package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests für die Klasse Element und isEqual() der Aufgabe 4.
 *
 * @author Max,Nima
 */

public class ReqElementTest {


    /**
     * Erzeugt ein Element mit übergebenem Wert, bei mehreren Werten werden
     * weitere Elemente angehängt.
     *
     * @param values Wert(e) des Elements/der Elemente
     * @return Element mit Wert, bei mehreren Werten mehrere aneinandergehängte
     * Elemente
     * @Author klk
     */
    private Element createElements(char... values) {
        assert values != null && values.length > 0;

        // ein Element mit letztem Wert anlegen
        Element element = new Element(values[values.length-1]);

        // weitere Elemente anlegen, dabei rückwärts durch das Array wandern
        for (int i = values.length-2; i >= 0; i--) {
            element = new Element(values[i], element);
        }
        return element;
    }


    @Test
    public void testIsEqual() {
        Element elem1 = createElements('1', '2', '3');
        Element elem2 = createElements('1', '2', '3');
        assertTrue(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentSet_begin() {
        Element elem1 = createElements('x', '2', '3');
        Element elem2 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentSet_mid() {
        Element elem1 = createElements('1', 'x', '3');
        Element elem2 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentSet_end() {
        Element elem1 = createElements('1', '2', 'x');
        Element elem2 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentLength_first() {
        Element elem1 = createElements('1', '2');
        Element elem2 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentLength_sec() {
        Element elem1 = createElements('1', '2', '3', '4');
        Element elem2 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(elem2));
    }

    @Test
    public void testIsEqual_DifferentLength_secNull() {
        Element elem1 = createElements('1', '2', '3');
        assertFalse(elem1.isEqual(null));
    }

    @Test
    public void test_deleteElement_onlySorted() {
        Element elem1 = createElements('a', 'b', 'e', 'c');
        elem1 = elem1.deleteElement('c');
        Element elem2 = createElements('a', 'b', 'e', 'c');
        assertTrue(elem1.isEqual(elem2));
    }
}
