package ueb.list;

import ueb.board.cells.BoardCell;

/**
 * Die Klasse beschreibt eine verkettete Liste
 * die als Information Komponente eine BordCell hat
 *
 * @author nima, max
 */
class CellListElement implements CellListInterface {

    /**
     * Info Komponente
     */
    private final BoardCell info;

    /**
     * Next Referenz
     */
    private CellListInterface next;

    /**
     * Konstruktor
     *
     * @param initCell die info Komponente
     * @param next     die referenz auf die nächste
     */
    CellListElement(BoardCell initCell, CellListInterface next) {
        if (initCell == null || next == null)
            throw new IllegalArgumentException("params null");

        this.info = initCell;
        this.next = next;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        if (next.isEmpty())
            return info.getPosition().toString();
        else
            return info.getPosition() + " -> " + next;
    }

    @Override
    public int size() {
        return this.next.size() + 1;
    }

    @Override
    public BoardCell getPayload() {
        return info;
    }

    @Override
    public boolean contains(BoardCell payload) {
        if (payload == null) {
            return false;
        }
        if (info.equals(payload)) {
            return true;
        }
        return this.next.contains(payload);
    }

    @Override
    public CellListInterface removeFirst() {
        return next;
    }

    @Override
    public CellListInterface getNext() {
        return next;
    }

    @Override
    public CellListInterface append(BoardCell payload) {
       // CellListElement newNode = new CellListElement(this.info,next);
        next = next.append(payload);
        return this;
      //  return newNode;
    }

    @Override
    public CellListInterface insertAtFront(BoardCell payload) {
        return new CellListElement(payload, this);
    }

    @Override
    public CellListInterface getLastElement() {
        if (next.isEmpty())
            return this;

        return next.getLastElement();
    }

    @Override
    public int listCosts() {
        return this.next.listCosts() + getPayload().getCosts();
    }

    @Override
    public CellListInterface remove(BoardCell payload) {
        if (getPayload().equals(payload)) {
            return next;
        } else {
            CellListElement newNode = new CellListElement(this.info,next);
            newNode.next = newNode.next.remove(payload);
            return newNode;
        }
    }

    @Override
    public BoardCell findMinimumAccumulatedCosts(int currentMinAccumulatedCosts, BoardCell currentCell) {
        if (info.getAccumulatedCosts() < currentMinAccumulatedCosts) {
            currentMinAccumulatedCosts = info.getAccumulatedCosts();
            currentCell = info;
        }
        return next.findMinimumAccumulatedCosts(currentMinAccumulatedCosts,currentCell);
    }
}
