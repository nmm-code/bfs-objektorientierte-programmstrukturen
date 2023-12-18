package ueb.board.cells;

import ueb.board.BoardCellType;
import ueb.board.Position;

/**
 * Stellt eine Sumpf dar.
 *
 * @author nima, max
 */
public class SwampCell extends BoardCell {

    /**
     * Erstellt eine neue Zelle an Position.
     *
     * @param pos Position der Zelle
     */
    public SwampCell(Position pos) {
        super(pos, BoardCellType.SWAMP,20);
    }


    @Override
    public boolean canBePassed() {
        return true;
    }

    /**
     * Liefert als toString()-Darstellung das Zeichen S und ihre Position.
     *
     * @return String mit einem S für Swamp und der Position.
     */
    @Override
    public String toString() {
        return "S"  + super.toString();
    }
}
