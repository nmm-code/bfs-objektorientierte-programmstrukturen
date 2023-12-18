package ueb.board;


import org.junit.Test;
import ueb.board.cells.BoardCell;
import ueb.board.cells.CanyonCell;
import ueb.board.cells.SwampCell;

import static org.junit.Assert.*;
import static ueb.board.BoardCellType.*;

public class PubBoardTest {


    //<editor-fold defaultstate="collapsed" desc="tests published in ueb06">
    /* Die nachfolgenden Tests benötigen eine korrekte Implementierung von
        - setFieldCellOnBoard()
        - getCallAtPosition()
        - createBoard()
        - Testkonstruktor Board.
        Bei nicht korrekter oder nicht erfolgter Implementierung werden die Tests ggf. dadurch
        bedingt fehlschlagen.

        Benötigt außerdem Implementierung der Klasse "Position".
     */

    @Test
    public void test0Constructor_BoardCellTypeArray() {
        Board board = new Board(new BoardCellType[][]{{CANYON, SWAMP}});
        assertEquals(CANYON, board.getCellAtPosition(new Position(0, 0)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(0, 1)).getType());
    }

    /**
     * Tests default-constructor.
     * Fails if constant in Board is changed.
     * <pre>     private final static BoardCellType[][] START_BOARD = {
     *             {  SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 0
     *             {  SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 1
     *             {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK},
     *             {  SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
     *             {  SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP}
     *     };</pre>
     */
    @Test
    public void test0Constructor_Default() {
        Board board = new Board();
        assertEquals( SWAMP, board.getCellAtPosition(new Position(0, 0)).getType());
        assertEquals(CANYON, board.getCellAtPosition(new Position(0, 1)).getType());
        assertEquals(CANYON, board.getCellAtPosition(new Position(0, 2)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(0, 3)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(0, 4)).getType());

        assertEquals( SWAMP, board.getCellAtPosition(new Position(1, 0)).getType());
        assertEquals(CANYON, board.getCellAtPosition(new Position(1, 1)).getType());
        assertEquals(CANYON, board.getCellAtPosition(new Position(1, 2)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(1, 3)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(1, 4)).getType());

        assertEquals( PLANK, board.getCellAtPosition(new Position(2, 0)).getType());
        assertEquals( PLANK, board.getCellAtPosition(new Position(2, 1)).getType());
        assertEquals( PLANK, board.getCellAtPosition(new Position(2, 2)).getType());
        assertEquals( PLANK, board.getCellAtPosition(new Position(2, 3)).getType());
        assertEquals( PLANK, board.getCellAtPosition(new Position(2, 4)).getType());

        assertEquals( SWAMP, board.getCellAtPosition(new Position(3, 0)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(3, 1)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(3, 2)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(3, 3)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(3, 4)).getType());

        assertEquals( SWAMP, board.getCellAtPosition(new Position(4, 0)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(4, 1)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(4, 2)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(4, 3)).getType());
        assertEquals( SWAMP, board.getCellAtPosition(new Position(4, 4)).getType());
    }

    /*
     * test Board.toString()
     *
     * Jedes Zell-Zeichen wird hierfür mit Leerzeichen umgeben, damit die Proportionen bei Ausgabe besser hinkommen.
     * In der Konstante gibt die erste Zeile die erste Spalte (x=0) an,
     * damit jede Position mit (x, y) angesprochen werden kann.
     * In der toString-Darstellung muss eine Spalte aber als Spalte ausgegeben werden.
     */
    @Test
    public void testToString() {
        Board board = new Board(new BoardCellType[][]{
                {SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 0
                {SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 1
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP}
        });
        String expected =
                " S  S  S  S  S \n" +
                " C  C  S  S  S \n" +
                " C  C  S  S  S \n" +
                " S  S  S  S  S \n" +
                " S  S  S  S  S \n";

        assertEquals(expected, board.toString());
    }

    /*
     * test Board.isPositionValid()
     */
    @Test
    public void testIsPositionValid_Valid() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(1, 2);

        assertTrue(board.isPositionValid(pos1));
        assertTrue(board.isPositionValid(pos2));
    }

    @Test
    public void testIsPositionValid_NotValid_TooLow_XOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position(-1, 0);

        assertFalse(board.isPositionValid(pos));
    }

    @Test
    public void testIsPositionValid_NotValid_TooLow_YOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position(0, -1);

        assertFalse(board.isPositionValid(pos));
    }

    @Test
    public void testIsPositionValid_NotValid_TooLow_BothOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position(-1, -10);

        assertFalse(board.isPositionValid(pos));
    }


    @Test
    public void testIsPositionValid_NotValid_TooHigh_XOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position(2, 0);

        assertFalse(board.isPositionValid(pos));
    }

    @Test
    public void testIsPositionValid_NotValid_TooHigh_YOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position(0, 3);

        assertFalse(board.isPositionValid(pos));
    }

    @Test
    public void testIsPositionValid_NotValid_TooHigh_BothOut() {
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        Position pos = new Position( 2, 3);

        assertFalse(board.isPositionValid(pos));
    }

    /*
     * test Board.getCellAtPosition()
     */

    @Test
    public void testGetCellAtPosition_ValidPosition(){
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        BoardCell cell = board.getCellAtPosition(new Position(0,0));

        assertNotNull(cell);
        assertEquals(CANYON, cell.getType());
    }

    @Test
    public void testGetCellAtPosition_InValidPosition(){
        Board board = new Board(new BoardCellType[][]{
                {CANYON, SWAMP, SWAMP},
                {CANYON, SWAMP, SWAMP}});
        BoardCell cell = board.getCellAtPosition(new Position(2,0));

        assertNull(cell);
    }

    /*
     * test Board.getNeighbours()
     */

    @Test
    public void testGetNeighbours_CenterPosition(){
        //JavaDoc: Die Nachbarn werden in der Reihenfolge x-1, x+1, y-1, y+1 aufgeführt.
        //Sind Zelltyp von Erwartung und Ergebnis gleich, muss die Positionsangabe falsch sein.
        Board board = new Board(new BoardCellType[][]{
                {SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 0
                {SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 1
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
                {SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP}
        });
        BoardCell[] neighbours = board.getNeighbours(new Position(2,2));

        BoardCell[] expected = new BoardCell[] {new CanyonCell(new Position(1, 2)),
                new SwampCell(new Position(3, 2)),
                new SwampCell(new Position(2, 1)),
                new SwampCell(new Position(2, 3))};

        assertArrayEquals(expected, neighbours);
    }

    @Test
    public void testGetNeighbours_CornerPosition(){
        //JavaDoc: Die Nachbarn werden in der Reihenfolge x-1, x+1, y-1, y+1 aufgeführt.
        //Sind Zelltyp von Erwartung und Ergebnis gleich, muss die Positionsangabe falsch sein.
        Board board = new Board(new BoardCellType[][]{
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 0
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 1
                {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP},
                {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP},
                {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP}
        });
        BoardCell[] neighbours = board.getNeighbours(new Position(0,0));

        BoardCell[] expected = new BoardCell[] {new SwampCell(new Position(1, 0)),
                new CanyonCell(new Position(0, 1))};

        assertArrayEquals(expected, neighbours);
    }
    //</editor-fold>

    @Test
    public void testGetAllCellsInfinityAccumulatedCosts() {
        Board board = new Board(new BoardCellType[][]{
                {SWAMP, CANYON}, // x = 0
                {SWAMP, CANYON}, // x = 1
        });

        board.getAllCellsInfinityAccumulatedCosts();

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                assertEquals(Integer.MAX_VALUE, board.getCellAtPosition(new Position(x, y)).getAccumulatedCosts());
            }
        }
    }


}
