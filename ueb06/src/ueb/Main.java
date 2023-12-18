package ueb;

import ueb.board.Board;
import ueb.board.Position;
import ueb.list.PathList;
import ueb.pathfinding.Pathfinder;

import java.util.Scanner;

/**
 * Diese Klasse gibt das Spielfeld auf der Konsole aus und nimmt Eingaben des Nutzers auf um eine
 * Start- und Endposition zu ermitteln. Von Start- zu Endposition wird ein Pfad ermittelt. Der
 * Start und Endpunkt werden mit ihren Koordinaten und ihrem Typ ausgegeben.
 * Wird ein Pfad gefunden, wird dieser auf der Konsole ausgegeben inklusive der Kosten und der
 * Anzahl der passierten Zellen.
 * Der Nutzer wird so lange zur Eingabe aufgefordert, bis das Programm mit "exit" verlassen wird.
 *
 * @author fme
 */
public class Main {

    /**
     * Main Methode
     *
     * @param args CommandLine Parameter
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        Pathfinder pathfinder = new Pathfinder();
        boolean shouldExit = false;

        while (!shouldExit) {
            System.out.println(board.toString());
            System.out.println("Type \"exit\" anytime to leave to program");

            // Start Position
            System.out.println("START Position");
            Position start = parsePosition(sc);
            shouldExit = (start == null);

            // End Position
            if (!shouldExit) {
                System.out.println("END Position:");
                Position end = parsePosition(sc);
                shouldExit = (end == null);
                // only execute program, when it shouldn't be exited
                if (!shouldExit) {
                    // Check if both positions are valid (on board, both can be accessed)
                    if (!(board.isPositionValid(start) && board.isPositionValid(end)) ||
                            !board.getCellAtPosition(start).canBePassed() || !board.getCellAtPosition(end).canBePassed()) {
                        System.out.println("ERROR: One position might be not on the game board or " +
                                                   "can't be passed!");
                    } else {
                        System.out.printf("\nStart: %s\n", board.getCellAtPosition(start).toString());
                        System.out.printf("End  : %s\n\n", board.getCellAtPosition(end).toString());

                        // Find path
                        PathList path = pathfinder.getPathFromPosToPos(start, end, board);

                        // if path empty, no path could be found
                        if (path.isEmpty()) {
                            System.out.println("No path could be found...");
                        } else {
                            System.out.println(path.toString() + "\n");
                            System.out.println(board.toString(start, end, path.toPositionArray()));
                            System.out.printf("Total cells: %d%n", path.cellAmountInPath());
                            System.out.println("----------------\n");
                        }
                    }
                }
            }
        }
    }


    /**
     * Parse eine Position anhand der Nutzereingaben.
     *
     * @param sc   Scanner
     * @return Position oder null, wenn das Programm beendet werden soll
     */
    private static Position parsePosition(Scanner sc) {
        Position pos = null;

        System.out.print("  X coordinate: ");
        if (readNumber(sc)) {
            int x = sc.nextInt();

            System.out.print("  Y coordinate: ");
            if (readNumber(sc)) {
                int y = sc.nextInt();

                pos = new Position(x, y);
            }
        }

        return pos;
    }

    /**
     * Liest Nutzereingaben ein, bis Eingabe eine positive Ganzzahl oder der Text "exit" ist.
     *
     * @param sc Scanner nimmt die Eingabe des Nutzers auf
     * @return true, wenn eine Natürliche Zahl eingegeben wurde;
     *         false, wenn "exit" eingegeben wurde
     */
    private static boolean readNumber(Scanner sc) {
        boolean proceed = true;
        while (proceed && !sc.hasNextInt()) {
            String input      = sc.nextLine();
            proceed = !input.equalsIgnoreCase("exit");
            if (proceed && !input.isEmpty()) {
                System.out.print("Please use a natural positive number: ");
            }
        }
        return proceed;
    }
}

