package ueb;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests für die Klasse Set der Aufgabe 4. 
 * PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein Testat für diese Aufgabe erworben werden.
 *
 * @author cei, klk
 */
public class PubSetTest {
    /**
     * Erzeugt eine Menge mit den Werten.
     *
     * @param values Wert(e) des Elements/der Elemente
     * @return Menge mit den Werten enthalten
     */
    private Set createSet(char... values) {
        Set set = new Set();
        //  Elemente zufügen
        for (char value : values) {
            set.addElement(value);
        }
        return set;
    }

    //-------------------------------------------

    @Test
    public void test0ToArray() {
        Set set = createSet('c', 'h', 'a', 'o', 's');
        assertArrayEquals(new char[]{'a','c','h', 'o', 's'}, set.toArray());
    }

    @Test
    public void test0ToArray_NotChangesSet() {
        Set set = createSet('c', 'h', 'a', 'o', 's');
        char[] result = set.toArray();
        char[] expected = new char[]{'a','c','h', 'o', 's'};
        result = set.toArray(); //snd call
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + Arrays.toString(result), expected, result);
    }

    //-------------------------------------------

    @Test
    public void test1Constructor_String_Empty() {
        Set set = new Set("");
        assertTrue(set.isEmpty());
    }

    @Test
    public void test1Constructor_String_SingleCharacter() {
        //depends on toArray()
        Set set = new Set("M");
        assertFalse(set.isEmpty());
        char[] expected = new char[]{'M'};
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + Arrays.toString(set.toArray()), expected, set.toArray());
    }

    @Test
    public void test1Constructor_String_MultipleCharacters() {
        //depends on toArray()
        Set set = new Set("despair");
        assertFalse(set.isEmpty());
        assertTrue(set.containsElement('d'));
        assertTrue(set.containsElement('e'));
        assertTrue(set.containsElement('s'));
        assertTrue(set.containsElement('p'));
        assertTrue(set.containsElement('a'));
        assertTrue(set.containsElement('i'));
        assertTrue(set.containsElement('r'));

        char[] expected = new char[]{'a','d','e','i','p','r','s'};
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + Arrays.toString(set.toArray()), expected, set.toArray());
    }

    @Test
    public void test1Constructor_String_DoubleCharacters() {
        //depends on toArray()
        Set set = new Set("mermaid");
        assertFalse(set.isEmpty());
        char[] expected = new char[]{'a','d','e','i','m','r'};
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + Arrays.toString(set.toArray()), expected, set.toArray());
    }

    //-----------------------------------------------

    @Test
    public void testConstructor_VarArgs() {
        Set set = new Set('a', 'b', 'c');
        char[] expected = new char[]{'a', 'b', 'c'};
        assertArrayEquals("expected " + Arrays.toString(expected) + " but got " + Arrays.toString(set.toArray()), expected, set.toArray());
    }

    //-----------------------------------------------

    @Test
    public void testIsEmpty() {
        Set set = new Set();
        assertTrue(set.isEmpty());

        set = createSet();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEqual_EqualSets() {
        Set set1 = createSet('a');
        Set set2 = createSet('a');
        assertTrue(set1.isEqual(set2));

        set1 = createSet('a', 'b', 'c');
        set2 = createSet('a', 'b', 'c');
        assertTrue(set1.isEqual(set2));
    }

    @Test
    public void testDeleteElement_First() {
        Set set = createSet('a', 'b', 'c');
        set.deleteElement('a');
        Set expected = createSet('b', 'c');
        assertArrayEquals("expected: " + expected + " but got: " + set, expected.toArray(), set.toArray());
    }

    @Test
    public void testToString() {
        Set set = createSet('a', 'b', 'c');
        assertEquals("{a b c}", set.toString());
    }
}
