package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.*;

public class PubSwampCellTests {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
    @Test
    public void testCanBePassed() {
        assertTrue(new SwampCell(new Position(0, 0)).canBePassed());
    }

    @Test
    public void testToString() {
        assertEquals("S at 0/0", new SwampCell(new Position(0,0)).toString());
    }
    //</editor-fold>


    @Test
    public void testGetCosts_SwampCell() {
        BoardCell cell = new SwampCell(new Position(0, 0));
        assertEquals(20, cell.getCosts());
    }

    @Test
    public void testGetAccumulatedCosts_SwampCell() {
        BoardCell cell = new SwampCell(new Position(0, 0));
        assertEquals(0, cell.getAccumulatedCosts());
    }

    @Test
    public void testSetAccumulatedCosts_SwampCell() {
        BoardCell cell = new SwampCell(new Position(0, 0));
        cell.setAccumulatedCosts(10);
        assertEquals(10, cell.getAccumulatedCosts());
    }
}
