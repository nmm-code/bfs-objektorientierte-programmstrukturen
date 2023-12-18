package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mit der Aufgabe 3 herausgegebene Testklasse für Methoden der Klasse {@code Element}.
 * PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein Testat für diese Aufgabe erworben werden.
 * @author klk
 */
public class PubElementTest {

    /**
     * Erzeugt ein Element mit übergebenem Wert, bei mehreren Werten werden
     * weitere Elemente angehängt.
     *
     * @param values Wert(e) des Elements/der Elemente
     * @return Element mit Wert, bei mehreren Werten mehrere aneinandergehängte
     * Elemente
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

    //-----------------------------------------------

    @Test
    public void testConstructor_char_Element() {
        Element el1 = new Element('z', null);
        Element el2 = new Element('y', el1);
        Element el3 = new Element('x', el2);
        assertEquals('x', el3.getValue());
        assertEquals('y', el3.getNext().getValue());
        assertEquals('z', el3.getNext().getNext().getValue());
        assertNull(el3.getNext().getNext().getNext());
    }
    //-----------------------------------------------


    @Test
    public void testAppendElement_ToOneElement() {
        Element el = new Element('a');
        Element result = el.appendElement('b');
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    @Test
    public void testAppendElement_Twice() {
        Element el = new Element('a');
        Element result = el.appendElement('b').appendElement('c');
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertEquals('c', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

    //-----------------------------------------------

    @Test
    public void testInsertElement_AtFront() {
        Element el = createElements('b', 'd');
        Element result = el.insertElement('a');
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertEquals('d', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

    @Test
    public void testInsertElement_InMiddle() {
        Element el = createElements('b', 'd');
        Element result = el.insertElement('c');
        assertEquals('b', result.getValue());
        assertEquals('c', result.getNext().getValue());
        assertEquals('d', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

    @Test
    public void testInsertElement_AtEnd() {
        Element el = createElements('b', 'd');
        Element result = el.insertElement('e');
        assertEquals('b', result.getValue());
        assertEquals('d', result.getNext().getValue());
        assertEquals('e', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

    //-----------------------------------------------

    @Test
    public void testDeleteElement_AtFront() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.deleteElement('a');
        assertEquals('b', result.getValue());
        assertEquals('c', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    @Test
    public void testDeleteElement_InMiddle() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.deleteElement('b');
        assertEquals('a', result.getValue());
        assertEquals('c', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    @Test
    public void testDeleteElement_AtEnd() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.deleteElement('c');
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    @Test
    public void testDeleteElement_NotExisting() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.deleteElement('d');
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertEquals('c', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }


    //-----------------------------------------------


    @Test
    public void testSize() {
        Element el = createElements('a');
        assertEquals(1, el.size());

        el = createElements('a', 'b', 'c');
        assertEquals(3, el.size());
    }

    //-----------------------------------------------

    @Test
    public void testContainsElement_First() {
        Element el = createElements('b', 'c', 'e');
        assertTrue(el.containsElement('b'));
    }

    @Test
    public void testContainsElement_Middle() {
        Element el = createElements('b', 'c', 'e');
        assertTrue(el.containsElement('c'));
    }

    @Test
    public void testContainsElement_Last() {
        Element el = createElements('b', 'c', 'e');
        assertTrue(el.containsElement('e'));
    }

    @Test
    public void testContainsElement_NotExisting() {
        Element el = createElements('b', 'c', 'e');
        assertFalse(el.containsElement('a'));
        assertFalse(el.containsElement('d'));
        assertFalse(el.containsElement('f'));
    }

    //-----------------------------------------------

    @Test
    public void testIsSorted_Gapless() {
        Element el = createElements('b', 'c', 'd');
        assertTrue(el.isSorted());
    }

    @Test
    public void testIsSorted_WithGaps() {
        Element el = createElements('a', 'c', 'e');
        assertTrue(el.isSorted());
    }

    @Test
    public void testIsSorted_DoubleValues_AtFront() {
        Element el = createElements('b', 'b', 'e');
        assertTrue(el.isSorted());
    }

    @Test
    public void testIsSorted_DoubleValues_AtEnd() {
        Element el = createElements('b', 'e', 'e');
        assertTrue(el.isSorted());

    }
    @Test
    public void testIsSorted_DoubleValues_InMiddleAndEnd() {
        Element el = createElements('b', 'c', 'c', 'e', 'e');
        assertTrue(el.isSorted());
    }

    @Test
    public void testIsSorted_NotSorted_LastElementsSwitched() {
        Element el = createElements('a', 'b', 'd', 'c');
        assertFalse(el.isSorted());
    }

    @Test
    public void testIsSorted_NotSorted_MiddleElementsSwitched() {
        Element el = createElements('a', 'c', 'b', 'd');
        assertFalse(el.isSorted());
    }

    @Test
    public void testIsSorted_NotSorted_FirstElementAtThirdPosition() {
        Element el = createElements('b', 'c', 'a', 'd');
        assertFalse(el.isSorted());
    }

    //-----------------------------------------------

    @Test
    public void testToString_ThreeElements() {
        Element el = createElements('a', 'b', 'c');
        assertEquals("a b c", el.toString());
    }

    @Test
    public void testToString_OneElement() {
        Element el = createElements('b');
        assertEquals("b", el.toString());
    }

    //-----------------------------------------------

    @Test
    public void testGetElementAt_First() {
        Element el = createElements('a', 'b', 'c');
        assertEquals('a', el.getElementAt(0));
    }

    @Test
    public void testGetElementAt_Second() {
        Element el = createElements('a', 'b', 'c');
        assertEquals('b', el.getElementAt(1));
    }

    @Test
    public void testGetElementAt_Last() {
        Element el = createElements('a', 'b', 'c');
        assertEquals('c', el.getElementAt(2));
    }

    @Test
    public void testGetElementAt_InvalidArgument_NegativeIndex() {
        Element el = createElements('a', 'b', 'c');
        assertEquals(0, el.getElementAt(-1));
    }

    @Test
    public void testGetElementAt_InvalidArgument_IndexTooBig() {
        Element el = createElements('a', 'b', 'c');
        assertEquals(0, el.getElementAt(3));
    }

    //-----------------------------------------------

    @Test
    public void testInsertElementAt_Front() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.insertElementAt('A', 0);
        assertEquals('A', result.getValue());
        assertEquals('a', result.getNext().getValue());
        assertEquals('b', result.getNext().getNext().getValue());
        assertEquals('c', result.getNext().getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext().getNext());
    }

    @Test
    public void testInsertElementAt_Middle() {
        Element el = createElements('a', 'b', 'c');
        Element result = el.insertElementAt('B', 1);
        assertEquals('a', result.getValue());
        assertEquals('B', result.getNext().getValue());
        assertEquals('b', result.getNext().getNext().getValue());
        assertEquals('c', result.getNext().getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext().getNext());
    }

    @Test
    public void testInsertElementAt_End() {
        Element el = createElements('a', 'b');
        Element result = el.insertElementAt('C', 2);
        assertEquals('a', result.getValue());
        assertEquals('b', result.getNext().getValue());
        assertEquals('C', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

    @Test
    public void testInsertElementAt_InvalidIndex_NegativeIndex() {
        Element el = createElements('b', 'c');
        Element result = el.insertElementAt('A', -1);
        assertEquals(2, result.size());
        assertEquals('b', result.getValue());
        assertEquals('c', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    @Test
    public void testInsertElementAt_InvalidIndex_IndexTooBig() {
        Element el = createElements('b', 'c');
        Element result = el.insertElementAt('A', 3);
        assertEquals(2, result.size());
        assertEquals('b', result.getValue());
        assertEquals('c', result.getNext().getValue());
        assertNull(result.getNext().getNext());
    }

    //-----------------------------------------------

    @Test
    public void testPrependElement() {
        Element el = createElements('a', 'b');
        Element result = el.prependElement('A');
        assertEquals('A', result.getValue());
        assertEquals('a', result.getNext().getValue());
        assertEquals('b', result.getNext().getNext().getValue());
        assertNull(result.getNext().getNext().getNext());
    }

}
