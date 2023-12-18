package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

// TODO DONE: Nutzt sprechende Namen für die Tests.
public class ReqMyListTest {
    /**
     * Erzeugt eine Liste mit den übergebenen Werten.
     *
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

    @Test
    public void test_isEmpty_ThreeElements() {
        MyList list = createList('a', 'b', 'e');
        assertFalse(list.isEmpty());
    }

    @Test
    public void test_isEmpty_OneElement() {
        MyList list = createList('a');
        assertFalse(list.isEmpty());
    }

    @Test
    public void test_isEmpty_empty() {
        MyList list = new MyList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void test_isSorted_sorted() {
        MyList list = createList('a', 'b', 'e');
        assertTrue(list.isSorted());

    }

    @Test
    public void test_isSorted_sorted_duplicateInList() {
        MyList list = createList('a', 'b', 'b', 'c');
        assertTrue(list.isSorted());
    }

    @Test
    public void test_isSorted_unsorted() {
        MyList list = createList('b', 'a', 'c');
        assertFalse(list.isSorted());
    }

    @Test
    public void test_size_ThreeElements() {
        MyList list = createList('a', 'b', 'e');
        assertEquals(3, list.size());
    }

    @Test
    public void test_size_OneElement() {
        MyList list = createList('a');
        assertEquals(1, list.size());
    }

    @Test
    public void test_size_ZeroElement() {
        MyList list = createList();
        assertEquals(0, list.size());
    }

    @Test
    public void test_containsElement_CheckAllElement() {
        MyList list = createList('a', 'b', 'e');
        assertTrue(list.containsElement('a'));
        assertTrue(list.containsElement('b'));
        assertTrue(list.containsElement('e'));
    }

    @Test
    public void test_containsElement_NotInList() {
        MyList list = createList('a', 'b', 'e');
        assertFalse(list.containsElement('z'));
    }

    @Test
    public void test_containsElement_EmptyList() {
        MyList list = createList();
        assertFalse(list.containsElement('z'));
    }

    @Test
    public void test_getElement_CheckAllElement() {
        MyList list = createList('a', 'b', 'e');
        assertEquals('a',list.getElementAt(0));
        assertEquals('b',list.getElementAt(1));
        assertEquals('e',list.getElementAt(2));

    }
    @Test
    public void test_getElement_InvalidIndex() {
        MyList list = createList('a', 'b', 'e');
        assertEquals(Element.INVALID_VALUE, list.getElementAt(-1));
    }

    @Test
    public void test_getElement_EmptyList_FirstIndex(){
        MyList list = createList();
        assertEquals(Element.INVALID_VALUE,list.getElementAt(0));
    }

}
