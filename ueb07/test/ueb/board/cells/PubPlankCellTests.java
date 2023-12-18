package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.*;

public class PubPlankCellTests {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
    @Test
    public void testCanBePassed() {
        assertTrue(new PlankCell(new Position(0, 0)).canBePassed());
    }

    @Test
    public void testToString() {
        assertEquals("P at 0/0", new PlankCell(new Position(0, 0)).toString());
    }
    //</editor-fold>

    @Test
    public void testGetCosts_PlankCell() {
        BoardCell cell = new PlankCell(new Position(0, 0));
        assertEquals(1, cell.getCosts());
    }

    @Test
    public void testGetAccumulatedCosts_PlankCell() {
        BoardCell cell = new PlankCell(new Position(0, 0));
        assertEquals(0, cell.getAccumulatedCosts());
    }

    @Test
    public void testSetAccumulatedCosts_PlankCell() {
        BoardCell cell = new PlankCell(new Position(0, 0));
        cell.setAccumulatedCosts(10);
        assertEquals(10, cell.getAccumulatedCosts());
    }

}
