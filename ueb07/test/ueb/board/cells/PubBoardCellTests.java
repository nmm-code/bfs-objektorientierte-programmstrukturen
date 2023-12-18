package ueb.board.cells;

import org.junit.Test;
import ueb.board.Position;

import static org.junit.Assert.*;

/*
 * tests published with ueb06
 */
public class PubBoardCellTests {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
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
        BoardCell swampCell = new SwampCell(new Position(0, 0));
        BoardCell plankCell = new SwampCell(new Position(0, 0));
        BoardCell canyonCell = new SwampCell(new Position(0, 0));
        Position pos = new Position(0, 0);

        assertNotEquals("Position should not be equal swampCell, as it's not a cell", swampCell, pos);
        assertNotEquals("Position should not be equal plankCell, as it's not a cell", plankCell, pos);
        assertNotEquals("Position should not be equal canyonCell, as it's not a cell", canyonCell, pos);
    }

//    nicht mehr nutzbar/notwendig, da BoardCell jetzt abstrakte Klasse ist
//    @Test (expected = UnsupportedOperationException.class)
//    public void testCanBePassed(){
//        BoardCell cell = new BoardCell(new Position(0, 0), SWAMP);
//
//        boolean unused = cell.canBePassed();
//    }
    //</editor-fold>
}
