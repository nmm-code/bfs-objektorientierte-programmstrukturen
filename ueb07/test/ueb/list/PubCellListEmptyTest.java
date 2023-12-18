package ueb.list;

import org.junit.Test;
import ueb.board.Position;
import ueb.board.cells.SwampCell;


import static org.junit.Assert.*;

/**
 * Tests for the empty list. Published with assignment.
 *
 * @author cei, cki, klk
 */
public class PubCellListEmptyTest {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
    @Test
    public void testSize_EmptyList() {
        CellListInterface list = new CellListEmpty();
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty_EmptyList() {
        CellListInterface list = new CellListEmpty();
        assertTrue(list.isEmpty());
    }


    @Test(expected = AssertionError.class)
    public void testGetPayload_emptyList() {
        CellListInterface list = new CellListEmpty();
        list.getPayload();
    }

    @Test(expected = AssertionError.class)
    public void testGetNext_emptyList() {
        CellListInterface list = new CellListEmpty();
        list.getNext();
    }

    @Test
    public void testAppend_emptyList() {

        CellListInterface list = new CellListEmpty();
        list = list.append(new SwampCell(new Position(0, 0)));

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(new SwampCell(new Position(0, 0)), list.getPayload());
    }

    @Test
    public void testInsertAtFront_emptyList() {
        CellListInterface list = new CellListEmpty();
        list = list.insertAtFront(new SwampCell(new Position(0, 0)));
        assertEquals(1, list.size());
        assertEquals(new SwampCell(new Position(0, 0)), list.getPayload());
    }

    @Test
    public void testContains_emptyList() {
        CellListInterface list = new CellListEmpty();
        assertFalse(list.contains(new SwampCell(new Position(3, 3))));
    }

    @Test
    public void testToString_EmptyList() {
        CellListInterface list = new CellListEmpty();
        assertEquals("EMPTY", list.toString());
    }
    //</editor-fold>

    /*
     * test listCosts()
     */
    @Test
    public void testPathCost_EmptyList() {
        CellListInterface list = new CellListEmpty();

        assertEquals(0, list.listCosts());
    }

    /*
     * test remove()
     */
    @Test
    public void testRemove_emptyList() {
        CellListInterface list = new CellListEmpty();
        list = list.remove(new SwampCell(new Position(3, 3)));
        assertNotNull(list);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

}