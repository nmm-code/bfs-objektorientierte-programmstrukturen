package ueb.list;

import ueb.board.cells.BoardCell;

/**
 * Repräsentiert die leere Liste (oder das Ende der Liste). Wird beerbt von der Klasse
 * CellListElement.
 *
 * @author fme, nima ,max
 */
class CellListEmpty implements CellListInterface {

    @Override
    public CellListEmpty getNext() {
        throw new AssertionError("there is no next element in empty list");
    }

    @Override
    public BoardCell getPayload() {
        throw new AssertionError("there is no payload in empty list");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int listCosts() {
        return 0;
    }

    @Override
    public CellListInterface insertAtFront(BoardCell payload) {
        return new CellListElement(payload, this);
    }

    @Override
    public CellListEmpty removeFirst() {
        return this;
    }

    @Override
    public boolean contains(BoardCell payload) {
        return false;
    }

    @Override
    public CellListInterface append(BoardCell payload) {
        return new CellListElement(payload, this);
    }


    @Override
    public CellListInterface remove(BoardCell payload) {
        return this;
    }

    @Override
    public CellListEmpty getLastElement() {
        return this;
    }

    @Override
    public BoardCell findMinimumAccumulatedCosts(int currentMinAccumulatedCosts, BoardCell currentCell) {
        return currentCell;
    }

    @Override
    public String toString() {
        return "EMPTY";
    }

}
