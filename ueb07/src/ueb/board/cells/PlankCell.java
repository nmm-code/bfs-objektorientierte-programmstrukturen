package ueb.board.cells;

import ueb.board.BoardCellType;
import ueb.board.Position;

/**
 * Stellt eine Planke dar.
 *
 * @author nima, max
 */
public class PlankCell extends BoardCell {

    /**
     * Erstellt eine neue Planke an der Position.
     *
     * @param pos Position der Zelle
     */
    public PlankCell(Position pos) {
        super(pos, BoardCellType.PLANK,1);
    }


    @Override
    public boolean canBePassed() {
        return true;
    }

    /**
     * Liefert als toString()-Darstellung das Zeichen P und ihre Position.
     *
     * @return die String darstellung mit der Position
     */
    @Override
    public String toString() {
        return "P" + super.toString();
    }
}
