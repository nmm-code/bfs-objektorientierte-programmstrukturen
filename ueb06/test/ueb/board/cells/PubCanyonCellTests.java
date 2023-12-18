package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PubCanyonCellTests {

    @Test
    public void testCanBePassed() {
        assertFalse("Canyon Cells can never be passed!", new CanyonCell(new Position(0, 0)).canBePassed());
    }

    @Test
    public void testToString() {
        assertEquals("C at 0/0", new CanyonCell(new Position(0,0)).toString());
    }

}
