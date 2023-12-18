package ueb.pathfinding;

import org.junit.Test;
import ueb.board.Board;
import ueb.board.BoardCellType;
import ueb.board.Position;
import ueb.board.cells.BoardCell;
import ueb.board.cells.PlankCell;
import ueb.board.cells.SwampCell;
import ueb.list.PathList;

import java.util.Arrays;

import static ueb.board.BoardCellType.*;
import static ueb.board.BoardCellType.SWAMP;

import static org.junit.Assert.*;

public class PubDijkstraTest {

    private final static Pathfinder pathfinder = new Dijkstra();


    /* --- resolvePath() Tests ---- */

    @Test
    public void testResolvePath_ThreeCells() {
        BoardCell firstCell = new SwampCell(new Position(0, 0));
        BoardCell secondCell = new PlankCell(new Position(1, 0));
        BoardCell thirdCell = new PlankCell(new Position(1, 1));

        thirdCell.setParentCell(secondCell);
        secondCell.setParentCell(firstCell);
        firstCell.setParentCell(null);

        //Letzte Zelle des Pfades zum rückwärts auflösen einfügen
        PathList path = pathfinder.resolvePath(thirdCell);

        assertNotNull(path);
        assertEquals(3, path.cellAmountInPath());
        assertTrue(path.contains(firstCell));
        assertTrue(path.contains(secondCell));
        assertTrue(path.contains(thirdCell));
        assertEquals(firstCell, path.getFirstElement());
    }

    @Test
    public void testResolvePath_NoCells() {

        //Letzte Zelle des Pfades zum rückwärts auflösen einfügen
        PathList path = pathfinder.resolvePath(null);

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

    @Test
    public void testResolvePath_OneCell() {
        BoardCell firstCell = new SwampCell(new Position(0, 0));
        firstCell.setParentCell(null);

        //Letzte Zelle des Pfades zum rückwärts auflösen einfügen
        PathList path = pathfinder.resolvePath(firstCell);

        assertNotNull(path);
        assertEquals(1, path.cellAmountInPath());
        assertEquals(firstCell, path.getFirstElement());
    }

    /* ---- Dijkstra Tests ---- */
    @Test
    public void testGetPathFromPosToPos_Valid_Straight() {
        Board board = new Board(new BoardCellType[][] {
                {SWAMP, CANYON, CANYON}, // x = 0
                {SWAMP, CANYON, CANYON}, // x = 1
                {SWAMP,  SWAMP,  SWAMP}
        });
        PathList result = pathfinder.getPathFromPosToPos(new Position(0,0), new Position(2,0),
                                                         board);

        Position[] expected = new Position[] {
                new Position(0,0), new Position(1, 0), new Position(2, 0)};

        assertNotNull(result);
        assertArrayEquals("expected: " + Arrays.deepToString(expected) + " but got: " + Arrays.deepToString(result.toPositionArray()),
                          expected, result.toPositionArray());
    }

    @Test
    public void testGetPathFromPosToPos_ZicZac() {
        Board board = new Board(new BoardCellType[][] {
                {SWAMP, CANYON, SWAMP}, // x = 0
                {SWAMP, SWAMP,  SWAMP}, // x = 1
                {SWAMP, CANYON, SWAMP}  // x = 2
        });
        PathList result = pathfinder.getPathFromPosToPos(new Position(0,0), new Position(2,2),
                                                         board);

        Position[] expected = new Position[] {new Position(0,0), new Position(1,0),
                new Position(1,1), new Position(1,2), new Position(2,2) };

        assertNotNull(result);
        assertArrayEquals("expected: " + Arrays.deepToString(expected) + " but got: " + Arrays.deepToString(result.toPositionArray()),
                          expected, result.toPositionArray());
    }

    @Test
    public void testGetPathFromPosToPos_Valid_Across() {
        Board board = new Board(new BoardCellType[][] {
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 0
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 1
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP},
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP},
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP}
        });
        PathList result = pathfinder.getPathFromPosToPos(new Position(0,0), new Position(4,4),
                                                         board);

        assertNotNull(result);
        assertEquals(9, result.cellAmountInPath());
    }

    @Test
    public void testGetPathFromPosToPos_Valid_SamePos() {
        Board board = new Board(new BoardCellType[][] {
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 0
                {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 1
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP},
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP},
                {SWAMP,  SWAMP,  SWAMP, SWAMP, SWAMP}
        });
        PathList result = pathfinder.getPathFromPosToPos(new Position(0,0), new Position(0,0),
                                                         board);

        assertNotNull(result);
        assertEquals(1, result.cellAmountInPath());
    }

    @Test
    public void test_4x4_to_0x0() {
        Board board = new Board(new BoardCellType[][] {
                {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK },// x = 0
                {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK }, // x = 1
                {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK },
                {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK },
                {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK },
        });

        PathList result = pathfinder.getPathFromPosToPos(new Position(4,4), new Position(0,0),board);

        assertNotNull(result);
        assertEquals(9, result.cellAmountInPath());
    }
}
