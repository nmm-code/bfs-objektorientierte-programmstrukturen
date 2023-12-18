package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PubSwampCellTests {

    @Test
    public void testCanBePassed() {
        assertTrue(new SwampCell(new Position(0, 0)).canBePassed());
    }

    @Test
    public void testToString() {
        assertEquals("S at 0/0", new SwampCell(new Position(0,0)).toString());
    }
}
