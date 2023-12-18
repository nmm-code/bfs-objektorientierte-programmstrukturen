package ueb.pathfinding;

import ueb.board.Board;
import ueb.board.Position;
import ueb.board.cells.BoardCell;
import ueb.list.PathList;

/**
 * Algorithmus zum Berechnen des weges mit Dijkstra
 *
 * @author nima, max
 */
public class Dijkstra extends Pathfinder {

    /**
     * Liefert einen Pfad von der Startposition zur Endposition auf dem übergebenen Board.
     * Mit den Dijkstra algorithmus
     * <p>
     *
     * @param start Startposition des Pfades auf dem Board
     * @param end   Endposition des Pfades auf dem Board
     * @param board Board mit Zellen, die durchquert werden sollen
     * @return ein Pfad auf dem Board von Start- zu Endposition
     *
     * @throws IllegalArgumentException wenn `start` oder `end` nicht auf dem `board` liegen
     */
    @Override
    public PathList getPathFromPosToPos(Position start, Position end, Board board) {
        if (board == null)
            throw new IllegalArgumentException("Board is null");

        if (!board.isPositionValid(start) || !board.isPositionValid(end))
            throw new IllegalArgumentException("Position is not on the board");

        if (!board.getCellAtPosition(start).canBePassed() || !board.getCellAtPosition(end).canBePassed())
            return new PathList();

        PathList done = new PathList();

        // 1. Alle Zellen/Knoten mit unendlichen Gesamtkosten initialisieren
        // 2. Alle Knoten zu einer Knotenliste "To Compute" (C) hinzufügen.
        PathList toCompute = board.getAllCellsInfinityAccumulatedCosts();

        // 3. Startzelle als betrachteten Knoten (N) auswählen,
        //    Gesamtkosten mit 0 initialisieren und
        //    N aus C entfernen.
        //    N in neue Liste "Done" (D) hinzufügen.
        BoardCell tmp = board.getCellAtPosition(start);
        tmp.setAccumulatedCosts(0);
        toCompute.removeAtFirst();
        done.append(tmp);

        // 4. Alle Nachbarn von N betrachten und
        //    deren Gesamtkosten auf die Kosten von N setzen.
        //    Für jeden Nachbarn N als Elternknoten setzen
        BoardCell[] neighbours = board.getNeighbours(tmp.getPosition());
        for (BoardCell boardCell : neighbours) {
            boardCell.setAccumulatedCosts(tmp.getAccumulatedCosts());
            boardCell.setParentCell(tmp);
        }

        boolean path_found = false;
        // 5. Solange C nicht leer ist und kein Pfad gefunden wurde:
        while (!toCompute.isEmpty() && !path_found) {
            //6. N = Knoten mit den kleinsten Gesamtkosten in C
            tmp = toCompute.findMinimumAccumulatedCosts();
            //7. N aus C entfernen
            toCompute.remove(tmp);
            //8. Kann N passiert werden:
            if (tmp.canBePassed()) {
                // 9. Füge N zu D hinzu
                done.append(tmp);
                // 10. Betrachte alle Nachbarn von N
                neighbours = board.getNeighbours(tmp.getPosition());
                int num = tmp.getCosts() + tmp.getAccumulatedCosts();
                for (BoardCell boardCell : neighbours) {
                    // 11. Neue mögliche Gesamtkosten berechnen (Gesamtkosten von N + Kosten von N)
                    // 12. Setze die Gesamtkosten der betrachteten Nachbarzelle auf neue mögliche Gesamtkosten
                    // und ihren Elternknoten auf den Knoten N, wenn:
                    // Die betrachtete Zelle nicht in D ist / die betrachtete Zelle noch in C ist
                    // die neuen Gesamtkosten kleiner als die aktuellen Gesamtkosten der Nachbarzelle sind
                    if (!done.contains(boardCell) || toCompute.contains(boardCell)) {
                        if (num < boardCell.getAccumulatedCosts()) {
                            boardCell.setAccumulatedCosts(num);
                            boardCell.setParentCell(tmp);
                        }
                    }
                }

            }
            //     13. Die Suche kann beendet werden, wenn der Endknoten betrachtet wurde,
            path_found = board.getCellAtPosition(end).getAccumulatedCosts() != Integer.MAX_VALUE;
        }
        PathList path = new PathList();
        // 14. Wenn der Endknoten gefunden wurde und der Startknoten in der Liste existiert
        if (path_found && done.contains(board.getCellAtPosition(start))) {
            // 15. Elternknoten vom letzten gefundenen Knoten zurück bis zum Start folgen und diese
            //    Knoten nacheinander vorne in eine neue Liste hinzufügen.
            path = resolvePath(board.getCellAtPosition(end));
        }

        // 16. Ist der Startknoten im Path vorhanden, soll dieser zurückgegeben werden,
        //     andernfalls existiert kein Weg und ein leerer Pfad soll geliefert werden
        return path.contains(board.getCellAtPosition(start)) ? path : new PathList();
    }
}
