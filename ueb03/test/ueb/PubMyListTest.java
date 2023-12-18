package ueb;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests der Methoden, die bereits in der Vorlesung vorgestellt werden.
 * PubTests müssen zu Beginn der Abgabe erfolgreich sein, sonst kann kein Testat für diese Aufgabe erworben werden.
 * @author klk
 */
public class PubMyListTest {
    
    /**
     * Erzeugt eine Liste mit den übergebenen Werten.
     * @param values Werte, die in die Liste eingefügt werden sollen.
     * @return Liste mit den Werten.
     */
    private MyList createList(char... values) {
        MyList list = new MyList();
        for (char i : values) {
            list.appendElement(i);
        }
        return list;
    }
    
    //-----------------------------------------------

    @Test
    public void test0GetValues() {
        MyList list = createList('a', 'b', 'c');
        char[] expected = new char[] {'a', 'b', 'c'};
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(list.getValues()),
                expected, list.getValues());
    }

    @Test
    public void test0GetValues_listRemainsUnchanged() {
        MyList list = createList('a', 'b', 'c');
        char[] expected = new char[] {'a', 'b', 'c'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
        //first call of getValues() could have changed list
        result = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void test0GetValues_EmptyList() {
        MyList list = createList();
        assertArrayEquals(new char[]{}, list.getValues());
    }
    //-----------------------------------------------

    @Test
    public void test0AppendElement() {
        MyList list = new MyList();
        list.appendElement('a');

        char[] expected = new char[] {'a'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    //-----------------------------------------------

    @Test
    public void testInsertElement_InEmptyList() {
        MyList list = createList();
        list.insertElement('a');
        char[] expected = new char[] {'a'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testInsertElement_AtFront() {
        MyList list = createList('b', 'c', 'd');
        list.insertElement('a');
        char[] expected = new char[] {'a', 'b', 'c', 'd'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testInsertElement_InMiddle() {
        MyList list = createList('a', 'c', 'd');
        list.insertElement('b');
        char[] expected = new char[] {'a', 'b', 'c', 'd'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testInsertElement_AtEnd() {
        MyList list = createList('a', 'b', 'c');
        list.insertElement('d');
        char[] expected = new char[] {'a', 'b', 'c', 'd'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    //-----------------------------------------------

    @Test
    public void testDeleteElement_AtFront() {
        MyList list = createList('a', 'b', 'c');
        list.deleteElement('a');
        char[] expected = new char[] {'b', 'c'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testDeleteElement_InMiddle() {
        MyList list = createList('a', 'b', 'c');
        list.deleteElement('b');
        char[] expected = new char[] {'a', 'c'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testDeleteElement_AtEnd() {
        MyList list = createList('a', 'b', 'c');
        list.deleteElement('c');
        char[] expected = new char[] {'a', 'b'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testDeleteElement_NotExisting() {
        MyList list = createList('a', 'b', 'c');
        list.deleteElement('d');
        char[] expected = new char[] {'a', 'b', 'c'};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

    @Test
    public void testDeleteElement_EmptyList() {
        MyList list = createList();
        list.deleteElement('d');
        char[] expected = new char[] {};
        char[] result   = list.getValues();
        assertArrayEquals("expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result),
                expected, result);
    }

}
