package ueb.list;

import org.junit.Test;
import ueb.board.Position;
import ueb.board.cells.SwampCell;

import static org.junit.Assert.*;

/**
 * Tests for the empty list. Published with assignment.
 *
 * @author cei, cki
 */
public class PubCellListTest {

    @Test
    public void testSize_EmptyList() {
        CellList list = new CellList();
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty_EmptyList() {
        CellList list = new CellList();
        assertTrue(list.isEmpty());
    }


    @Test(expected = AssertionError.class)
    public void testGetPayload_emptyList() {
        CellList list = new CellList();
        list.getPayload();
    }

   @Test(expected = AssertionError.class)
    public void testGetNext_emptyList() {
        CellList list = new CellList();
        list.getNext();
    }

    @Test
    public void testAppend_emptyList() {

        CellList list = new CellList();
        list = list.append(new SwampCell(new Position(0, 0)));

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(new SwampCell(new Position(0, 0)), list.getPayload());
    }

    @Test
    public void testInsertAtFront_emptyList() {
        CellList list = new CellList();
        list = list.insertAtFront(new SwampCell(new Position(0, 0)));
        assertEquals(1, list.size());
        assertEquals(new SwampCell(new Position(0, 0)), list.getPayload());
    }

    @Test
    public void testContains_emptyList() {
        CellList list = new CellList();
        assertFalse(list.contains(new SwampCell(new Position(3, 3))));
    }

    @Test
    public void testToString_EmptyList() {
        CellList list = new CellList();
        assertEquals("EMPTY", list.toString());
    }

    @Test
    public void test_lastElem() {
        CellList list = new CellList();
        list = list.insertAtFront(new SwampCell(new Position(0, 0)));
        list = list.insertAtFront(new SwampCell(new Position(1,0)));
        list = list.insertAtFront(new SwampCell(new Position(2,0)));
        assertEquals("0/0", list.getLastElement().toString());
    }

    @Test
    public void test_append() {
        CellList list = new CellList();
        list = list.insertAtFront(new SwampCell(new Position(0, 0)));
        list = list.insertAtFront(new SwampCell(new Position(1,0)));
        list = list.insertAtFront(new SwampCell(new Position(2,0)));
        list = list.append(new SwampCell(new Position(1,0)));
        assertEquals("2/0 -> 1/0 -> 0/0 -> 1/0", list.toString());
    }
    @Test
    public void test_Empty_append() {
        CellList list = new CellList();
        list = list.append(new SwampCell(new Position(1,0)));
        assertEquals("1/0", list.getLastElement().toString());
    }

}