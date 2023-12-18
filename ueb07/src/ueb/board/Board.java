package ueb.board;

import ueb.board.cells.BoardCell;
import ueb.board.cells.CanyonCell;
import ueb.board.cells.PlankCell;
import ueb.board.cells.SwampCell;
import ueb.list.PathList;

import static ueb.board.BoardCellType.*;

/**
 * Stellt ein Spielbrett dar.
 *
 * @author fme, nima, max
 */
public class Board {

    /**
     * Array von Zelltypen. Dient der Initialisierung des Feldes mit Instanzen.
     * Erste Dimension (Spalten): x-Achse, Breite
     * Zweite Dimension (Zeilen): y-Achse, Höhe
     */

    private final static BoardCellType[][] START_BOARD = {
            {  SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 0
            {  SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 1
            {  PLANK,  PLANK,  PLANK,  PLANK,  PLANK},
            {  SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
            {  SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP}
    };

//     private final static BoardCellType[][] START_BOARD = {
//            {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 0
//            {SWAMP, CANYON, CANYON, SWAMP, SWAMP}, // x = 1
//            {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP},
//            {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP},
//            {SWAMP, SWAMP, SWAMP, SWAMP, SWAMP}
// };

//    zweite Variante mit schmalem Durchgang
//    private final static BoardCellType[][] START_BOARD = {
//            { SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 0
//            { SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}, // x = 1
//            { SWAMP,  SWAMP,  SWAMP,  SWAMP,  SWAMP},
//            { SWAMP, CANYON, CANYON,  SWAMP,  SWAMP},
//            { SWAMP, CANYON, CANYON,  SWAMP,  SWAMP}
//    };

    /**
     * Array von Zellinstanzen. Stellt das Feld dar.
     * Erste Dimension (Spalten): x-Achse, Breite
     * Zweite Dimension (Zeilen): y-Achse, Höhe
     */
    final private BoardCell[][] board;

    /**
     * Testkonstruktor, der eine Spielfeldvorlage akzeptiert (siehe Board.startBoard).
     * Public, damit der Konstruktor auch für die Tests der Wegfindung genutzt werden kann.
     *
     * @param initBoard Spielfeldvorlage
     */
    public Board(BoardCellType[][] initBoard) {
        this.board = createBoard(initBoard);
    }

    /**
     * Erzeugt ein neues Board, welches mit Initialwerten aus einer Konstante
     * befüllt wird.
     */
    public Board() {
        this.board = createBoard(START_BOARD);
    }

    /**
     * Erzeugt eine Instanz einer Zelle in Abhängigkeit des angegebenen Typen und speichert
     * diese Instanz auf dem Spielfeld unter den angegebenen Koordinaten.
     *
     * @param board Board, auf dem die Zelle gesetzt werden soll
     * @param pos   Position, an die die Zelle gesetzt werden soll
     * @param type  Zelltyp
     */
    private static void setFieldCellOnBoard(BoardCell[][] board, Position pos, BoardCellType type) {
        if ( board == null || pos == null || type == null)
            throw new IllegalArgumentException("params is null");

        if (SWAMP == type)
            board[pos.getX()][pos.getY()] = new SwampCell(pos);
        if (CANYON == type)
            board[pos.getX()][pos.getY()] = new CanyonCell(pos);
        if (PLANK == type)
            board[pos.getX()][pos.getY()] = new PlankCell(pos);
    }

    /**
     * Erstellt ein Spielbrett anhand von vorgegebenen Zelltypen.
     *
     * @param boardCells Array von Zelltypen für das neue Spielbrett;
     *                   ist dies Array leer oder nicht existent, wird eine IllegalArgumentException ausgelöst
     * @return erstelltes Spielbrett
     */
    private BoardCell[][] createBoard(BoardCellType[][] boardCells) {
        if (boardCells == null || boardCells.length == 0 || boardCells[0].length == 0)
            throw new IllegalArgumentException("Array Leer oder Null");

        BoardCell[][] res = new BoardCell[boardCells.length][boardCells[0].length];
        for (int i = 0; i < boardCells.length; i++) {
            for (int j = 0; j < boardCells[0].length; j++) {
                setFieldCellOnBoard(res,new Position(i,j),boardCells[i][j]);
            }
        }
        return res;
    }

    /**
     * Prüft, ob eine Position sich auf dem Feld befindet.
     *
     * @param pos zu prüfende Position
     * @return true, wenn Position auf dem Feld liegt
     */
    public boolean isPositionValid(Position pos) {
        if ( pos == null)
            throw new IllegalArgumentException("position is null");

        return pos.getX() >= 0 && pos.getX() < board.length
                && pos.getY() >= 0 && pos.getY() < board[0].length;
    }

    /**
     * Gibt die Zelle an einer bestimmten Position des Feldes zurück.
     *
     * @param pos Position, deren Zelle erhalten werden soll
     * @return Zelle an der gewünschten Position; `null`, wenn Position nicht valide
     */
    public BoardCell getCellAtPosition(Position pos) {
        if (!isPositionValid(pos))
            return null;

        return board[pos.getX()][pos.getY()];
    }

    /**
     * Sucht die Nachbarn zu einer bestimmten Position raus und gibt diese zurück. Enthält
     * lediglich gültige Zellen. Die Nachbarn werden in der Reihenfolge x-1, x+1, y-1, y+1 aufgeführt.
     *
     * @param currPos Position, deren Nachbarn gesucht werden sollen
     * @return Zellnachbarn als Array mit entsprechender Länge
     */
    public BoardCell[] getNeighbours(Position currPos) {
        if (!isPositionValid(currPos))
            return new BoardCell[0];


        final int ROW = currPos.getX();
        final int COLUMN = currPos.getY();
        Position[] corners = new Position[]{
                new Position(ROW - 1, COLUMN),
                new Position(ROW + 1, COLUMN),
                new Position(ROW, COLUMN - 1),
                new Position(ROW, COLUMN + 1)};


        BoardCell[] tmp = new BoardCell[4];
        int j = 0;
        for (Position corner : corners) {
            if (isPositionValid(corner)) {
                tmp[j] = (getCellAtPosition(corner));
                j++;
            }
        }
        BoardCell[] res = new BoardCell[j];
        System.arraycopy(tmp, 0, res, 0, j);
        return res;
    }

    /**
     * Konvertiert das Feld in eine String-Darstellung.
     * Jedes Zell-Zeichen wird hierfür mit Leerzeichen umgeben, damit die Proportionen bei Ausgabe besser hinkommen.
     * In der Konstante gibt die erste Zeile die erste Spalte (x=0) an,
     * damit jede Position mit (x, y) angesprochen werden kann.
     * In der toString-Darstellung muss eine Spalte aber als Spalte ausgegeben werden.
     *
     * @return String-Darstellung des Feldes
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < board[0].length; y++) {
            for (int x = 0; x < board.length; x++) {
                result.append(" ").append(board[x][y].getType().toString().charAt(0)).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Konvertiert das Feld in eine String-Darstellung,
     * markiert Start- und Endposition purple, jeden Wegpunkt grün.
     *
     * @return String-Darstellung des Feldes
     * @see <a href="https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/">colored output</a>
     */
    public String toString(Position start, Position end, Position[] way) {

        final String ansiReset = "\u001B[0m";   // zum Rücksetzen der Darstellungsfarbe
        final String ansiGreen = "\u001B[32m";  // Darstellungsfarbe grün
        final String ansiPurple = "\u001B[35m";  // Darstellungsfarbe lila

        StringBuilder result = new StringBuilder();

        //Print each row so that field is displayed correctly on console
        for (int y = 0; y < board[0].length; y++) {
            for (int x = 0; x < board.length; x++) {
                result.append(" ");
                Position pos = new Position(x, y);
                if (pos.equals(start) || pos.equals(end))
                    result.append(ansiPurple);
                else if (pos.isIn(way))
                    result.append(ansiGreen);
                result.append((board[x][y]).toString().charAt(0)).append(ansiReset).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * liefert eine Liste mit allen Zellen des Boards,
     * wobei jede dieser Zellen mit maximal möglichen accumulatedCosts
     * (Integer.MAX_VALUE) initialisiert wird
     *
     * @return eine Liste
     */
    public PathList getAllCellsInfinityAccumulatedCosts() {
        PathList res = new PathList();
        for (BoardCell[] boardCells : board) {
            for (BoardCell cell : boardCells) {
                cell.setAccumulatedCosts(Integer.MAX_VALUE);
                res.append(cell);
            }
        }
        return res;
    }

}


