package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.*;


public class PubCanyonCellTests {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
    @Test
    public void testCanBePassed() {
        assertFalse("Canyon Cells can never be passed!", new CanyonCell(new Position(0, 0)).canBePassed());
    }

    @Test
    public void testToString() {
        assertEquals("C at 0/0", new CanyonCell(new Position(0,0)).toString());
    }
    //</editor-fold>

    @Test
    public void testGetAccumulatedCosts_CanyonCell() {
        BoardCell cell = new CanyonCell(new Position(0, 0));
        assertEquals(0, cell.getAccumulatedCosts());
    }

    @Test
    public void testSetAccumulatedCosts_CanyonCell() {
        BoardCell cell = new CanyonCell(new Position(0, 0));
        cell.setAccumulatedCosts(10);
        assertEquals(10, cell.getAccumulatedCosts());
    }
}
