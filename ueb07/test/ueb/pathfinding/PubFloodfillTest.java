package ueb.pathfinding;

import org.junit.Test;
import ueb.board.Board;
import ueb.board.BoardCellType;
import ueb.board.Position;
import ueb.list.PathList;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ueb.board.BoardCellType.*;
import static ueb.board.BoardCellType.SWAMP;

public class PubFloodfillTest {

    private final static Pathfinder pathfinder = new Floodfill();


    /* ---- Floodfill Tests ---- */
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

}
