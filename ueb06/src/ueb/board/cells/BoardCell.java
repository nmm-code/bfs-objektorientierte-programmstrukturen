package ueb.board.cells;

import ueb.board.BoardCellType;
import ueb.board.Position;

/**
 * Repräsentiert eine einzelne Zelle auf dem Spielfeld. Stellt die Grundklasse dar ohne
 * Spezifikation und wird beerbt.
 *
 * @author fme, nima, max
 */
public class BoardCell {

    /**
     * Position der Zelle auf einem Feld
     */
    private final Position pos;
    /**
     * Typ der Zelle
     */
    private final BoardCellType type;
    /**
     * Zelle, die im Pfad vor dieser Zelle kommt. Wichtig für den Pathfinder.
     */
    private BoardCell parent;

    /**
     * Erstellt eine neue Zelle an Position.
     *
     * @param pos  Position der Zelle
     * @param type Typ der Zelle
     */
    public BoardCell(Position pos, BoardCellType type) {
        this.pos = pos;
        this.type = type;
        parent = null;
    }

    /**
     * Gibt die Position der Zelle auf dem Spielbrett an.
     *
     * @return Position der Zelle auf dem Spielbrett
     */
    public Position getPosition() {
        return this.pos;
    }

    /**
     * Gibt den Zelltypen zurück.
     *
     * @return Zelltyp
     */
    public BoardCellType getType() {
        return this.type;
    }

    /**
     * Gibt die Elternzelle zurück. Die Elternzelle ist die Zelle, die in einem Pfad vor dieser
     * Zelle steht.
     *
     * @return Zelle, die in einem Pfad vor dieser Zelle steht
     */
    public BoardCell getParentCell() {
        return this.parent;
    }

    /**
     * Setzt die Elternzelle. Die Elternzelle ist die Zelle, die in einem Pfad vor dieser
     * Zelle steht.
     *
     * @param parent Elternzelle
     */
    public void setParentCell(BoardCell parent) {
        this.parent = parent;
    }

    /**
     * Gibt an, ob eine Zelle passiert werden kann oder nicht.
     *
     * @return true, wenn Zelle passiert werden kann
     */
    public boolean canBePassed() {
        throw new UnsupportedOperationException("Not implemented. Should be called on child class!");
    }

    /**
     * Prüft, ob zwei Zellen gleich sind. Zellen sind gleich, wenn sie denselben Typ und
     * die gleiche Position haben.
     *
     * @param obj Vergleichsobjekt
     * @return true, wenn die Objekte (i.d.R. Zellen) gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof BoardCell other))
            return false;

        return pos.equals(other.getPosition()) &&
                type == other.getType();
    }

    /**
     * Liefert Positionsangabe mit vorgestelltem "at", so dass eine erbende Zelle ihr Kürzel
     * mit dieser Angabe zusammenfügen kann (z.B. "S at 3/4").
     *
     * @return Positionsangabe in Stringform
     */
    @Override
    public String toString() {
        return " at " + pos.toString();
    }
}
