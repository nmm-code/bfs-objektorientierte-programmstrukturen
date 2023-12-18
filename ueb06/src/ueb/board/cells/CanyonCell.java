package ueb.board.cells;

import ueb.board.BoardCellType;
import ueb.board.Position;

/**
 * Stellt eine Schlucht dar.
 *
 * @author nima, max
 */
public class CanyonCell extends BoardCell {

    /**
    * Erstellt eine neue Schlucht an der Position.
    *
    * @param pos Position der Zelle
    */
    public CanyonCell(Position pos) {
        super(pos, BoardCellType.CANYON);
    }


    @Override
    public boolean canBePassed() {
        return false;
    }

    /**
    * Liefert als toString()-Darstellung das Zeichen C und ihre Position.
    *
    * @return die String darstellung mit der Position
    */
    @Override
    public String toString() {
        return "C" + super.toString();
    }
}
