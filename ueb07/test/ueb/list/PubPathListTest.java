package ueb.list;

import org.junit.Test;
import ueb.board.Position;
import ueb.board.cells.SwampCell;

import static org.junit.Assert.*;

/**
 * Tests for the empty node list. Published with assignment.
 *
 * @author cei, cki
 */
public class PubPathListTest {

    @Test
    public void testSize_EmptyList() {
        PathList list = new PathList();
        assertEquals(0, list.cellAmountInPath());
    }

    @Test
    public void testIsEmpty_EmptyList() {
        PathList list = new PathList();
        assertTrue(list.isEmpty());
    }


    @Test(expected = AssertionError.class)
    public void testGetAt_emptyList() {
        PathList list = new PathList();
        list.getFirstElement();
    }


    /**
     * works with cellAmountInPath() and getAt() -> if one of the method has not been implemented properly yet, this result of this
     * test may be wrong
     */
    @Test
    public void testAppend_emptyList() {
        PathList list = new PathList();
        list.append(new SwampCell(new Position(0, 0)));

        assertFalse(list.isEmpty());
        assertEquals(1, list.cellAmountInPath());
        assertEquals(new SwampCell(new Position(0, 0)), list.getFirstElement());
    }

    @Test
    public void testPrepend_emptyList() {
        PathList list = new PathList();
        list.insertAtFront(new SwampCell(new Position(0, 0)));
        assertEquals(1, list.cellAmountInPath());
        assertEquals(new SwampCell(new Position(0, 0)), list.getFirstElement());
    }

    @Test
    public void testContains_emptyList() {
        PathList list = new PathList();
        assertFalse(list.contains(new SwampCell(new Position(3, 3))));
    }

    @Test
    public void testToString_EmptyList() {
        PathList list = new PathList();
        assertEquals("EMPTY", list.toString());
    }
}