package ueb.board;

import org.junit.Test;
import ueb.board.cells.BoardCell;
import ueb.board.cells.SwampCell;

import static org.junit.Assert.*;
/*
 * tests published with assignment 6
 */
public class PubPositionTest {

    //<editor-fold defaultstate="collapsed"  desc="tests published in ueb06">
    /*
     * test Position.isIn()
     */

    @Test
    public void testIsIn_NotIn(){
        Position[] positions = new Position[] {
                new Position(0,0),
                new Position(1,1),
                new Position(2,5)
        };

        Position testPos = new Position(2,7);

        assertFalse(testPos.isIn(positions));
    }

    @Test
    public void testIsIn_NotIn_NullArray(){
        Position[] positions = null;

        Position testPos = new Position(2,7);

        assertFalse(testPos.isIn(positions));
    }

    /*
     * test Position.equals()
     */
    @Test
    public void testEquals_IsEqual(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,0);

        assertEquals(pos1, pos2);
    }

    @Test
    public void testEquals_NotEqual_SameClass(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,0);

        assertNotEquals(pos1, pos2);
    }

    @Test
    public void testEquals_NotEqual_DifferentClass(){
        Position pos = new Position(0,0);
        BoardCell cell = new SwampCell(new Position(0,0));

        assertNotEquals(pos, cell);
    }

    @Test
    public void testEquals_NotEqual_Null(){
        Position pos = new Position(0,0);

        assertNotEquals(pos, null);
    }
    //</editor-fold>
}
