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
public class Pathfinder {

    /**
     * Liefert einen Pfad von der Startposition zur Endposition auf dem übergebenen Board.
     * Arbeitet nach dem Floodfill-Algorithmus (übernimm folgende Anweisungen als Zeilenkommentare in den Code)
     * (Hinweis: Variablennamen müssen mit einem Kleinbuchstaben beginnen und aussagekräftig aus mehr als einem
     * Buchstaben bestehen):
     * <p>
     *
     * @param start Startposition des Pfades auf dem Board
     * @param end   Endposition des Pfades auf dem Board
     * @param board Board mit Zellen, die durchquert werden sollen
     * @return ein Pfad auf dem Board von Start- zu Endposition
     *
     * @throws IllegalArgumentException wenn `start` oder `end` nicht auf dem `board` liegen
     */
    public PathList getPathFromPosToPos(Position start, Position end, Board board) {
        if (board == null)
            throw new IllegalArgumentException("Board is null");
        
        if (!board.isPositionValid(start) || !board.isPositionValid(end) )
            throw new IllegalArgumentException("Position is not on the board");

        if (!board.getCellAtPosition(start).canBePassed() || !board.getCellAtPosition(end).canBePassed())
            return new PathList();

        //1.1 Erstellen einer leeren Liste als Warteschlange (queue) Q und den Startknoten zu dieser hinzufügen.
        // Die Warteschlange repräsentiert die Knoten, die geprüft/besucht werden müssen und wir starten von unserem
        // Startknoten aus.
        PathList queueQ = new PathList();
        queueQ.insertAtFront(board.getCellAtPosition(start));

        //1.1.1 Der Elternknoten des Startknotens ist null
        board.getCellAtPosition(start).setParentCell(null);

        //1.2 Leere Liste als Warteschlange (queue) C für bereits geprüfte Knoten erstellen
        //und Startknoten als bereits geprüften Knoten zufügen
        PathList queueC = new PathList();
        queueC.insertAtFront(board.getCellAtPosition(start));

        boolean pfad_gefunden = start.equals(end);
        BoardCell tmp = queueQ.getFirstElement();
        BoardCell[] neighbor;

        //2. Während Q nicht leer ist und kein Pfad gefunden wurde:
        while (!pfad_gefunden && !queueQ.isEmpty()) {
            //3. Ersten Knoten aus Q als zu prüfenden Knoten (N) wählen
            tmp = queueQ.getFirstElement();

            //4. N aus Q entfernen
            queueQ.removeAtFirst();

            neighbor = board.getNeighbours(tmp.getPosition());
            //5. Alle Nachbarn prüfen (nicht diagonal)
            for (int i = 0; i < neighbor.length; i++) {
                //5.1 wurde dieser Nachbarknoten (NB) noch nicht geprüft, zu C hinzufügen
                if (!queueC.contains(neighbor[i])) {
                    queueC.insertAtFront(neighbor[i]);
                    //6. Kann der zu überprüfende Nachbar NB passiert werden, dann:
                    if (neighbor[i].canBePassed()) {
                        //7. Elternknoten des zu überprüfenden Nachbarn NB gleich N setzen
                        neighbor[i].setParentCell(tmp);
                        //8. zu überprüfenden Nachbarn NB zu Q hinzufügen
                        queueQ.append(neighbor[i]);
                        //9. Prüfen, ob dieser Nachbar NB das Ziel ist. Wenn ja, dann Pfad gefunden
                        if (neighbor[i].getPosition().equals(end)) {
                            pfad_gefunden = true;
                        }
                    }


                }
            }
        }
        //11. Elternknoten vom letzten gefundenen Knoten zurück bis zum Start folgen und diese
        // Knoten nacheinander vorne in eine neue Liste hinzufügen.
        //12. Pfadliste zurückgeben
        return pfad_gefunden ? resolvePath(board.getCellAtPosition(end)) : new PathList();
    }

    /**
     * Löst den Pfad auf und gibt diesen als Liste zurück (Punkt 11 des FloodFill-Algorithmus).
     *
     * @param finalNode Letzter / Finaler Knoten
     * @return Pfad, der zum Finalen Knoten führt.
     */
    private PathList resolvePath(BoardCell finalNode) {
        PathList result = new PathList();

        BoardCell currentNode = finalNode;

        while (currentNode != null) {
            result.insertAtFront(currentNode);
            currentNode = currentNode.getParentCell();
        }

        return result;
    }
}
