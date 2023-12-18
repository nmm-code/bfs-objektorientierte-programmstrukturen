package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ueb.board.BoardCellType.SWAMP;

public class PubBoardCellTests {


    @Test
    public void testEqual_IsEqual(){
        BoardCell first = new SwampCell(new Position(0, 0));
        BoardCell second = new SwampCell(new Position(0, 0));

        assertEquals("Should be equal, as positions are equal", first, second);
    }

    @Test
    public void testEqual_DifferentCellTypes_SamePosition(){
        BoardCell first = new CanyonCell(new Position(0, 0));
        BoardCell second = new SwampCell(new Position(0, 0));

        assertNotEquals("Should not be equal, as types are different!", first, second);
    }

    @Test
    public void testEqual_SameTypes_DifferentPosition(){
        BoardCell first = new SwampCell(new Position(1, 0));
        BoardCell second = new SwampCell(new Position(0, 0));

        assertNotEquals("Should not be equal, as positions are different", first, second);
    }

    @Test
    public void testEqual_isNotABoardCell(){
        BoardCell swampCell = new BoardCell(new Position(0, 0), SWAMP);
        Position pos = new Position(0, 0);

        assertNotEquals("Should not be equal, as it's not a cell", swampCell, pos);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testCanBePassed(){
        BoardCell cell = new BoardCell(new Position(0, 0), SWAMP);

        boolean unused = cell.canBePassed();
    }

}
