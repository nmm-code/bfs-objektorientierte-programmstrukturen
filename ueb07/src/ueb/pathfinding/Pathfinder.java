package ueb.pathfinding;

import ueb.board.Board;
import ueb.board.Position;
import ueb.board.cells.BoardCell;
import ueb.list.PathList;

/**
 * Klasse, die dem Finden von Pfaden auf einem Spielbrett von einem Punkt zu einem anderen Punkt
 * dient.
 *
 * @author fme, nima ,max
 */
public abstract class Pathfinder {

    /**
     * Liefert einen Pfad von der Startposition zur Endposition auf dem übergebenen Board.
     *
     * @param start Startposition des Pfades auf dem Board
     * @param end   Endposition des Pfades auf dem Board
     * @param board Board mit Zellen, die durchquert werden sollen
     * @return ein Pfad auf dem Board von Start- zu Endposition
     *
     * @throws IllegalArgumentException wenn `start` oder `end` nicht auf dem `board` liegen
     */
    public abstract PathList getPathFromPosToPos(Position start, Position end, Board board);

    /**
     * Löst den Pfad auf und gibt diesen als Liste zurück (Punkt 11 des FloodFill-Algorithmus).
     *
     * @param finalNode Letzter / Finaler Knoten
     * @return Pfad, der zum Finalen Knoten führt.
     */

    protected PathList resolvePath(BoardCell finalNode) {
        PathList result = new PathList();

        BoardCell currentNode = finalNode;

        while (currentNode != null) {
            result.insertAtFront(currentNode);
            currentNode = currentNode.getParentCell();
        }

        return result;
    }
}
