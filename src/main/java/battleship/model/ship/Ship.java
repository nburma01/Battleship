package battleship.model.ship;

import battleship.model.ocean.Ocean;
import battleship.specification.Specification;
import battleship.specification.impl.AdjacentShipSpecification;
import battleship.specification.impl.RowColumnSpecification;
import battleship.specification.impl.ShipLengthSpecification;

public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    private boolean horizontal;

    /**
     * @return the length of this ship.
     */
    public abstract int getLength();

    protected abstract boolean[] getHit();

    /**
     * @return the type of this ship.
     */
    public abstract String getShipType();

    /**
     * @param row
     *            Row to place ship.
     * @param column
     *            Column to place ship.
     * @param horizontal
     *            Ship is horizontal.
     * @param ocean
     *            The ocean where the ship is placed.
     * @return true if is ok to put a ship of this length with its bow at this location with the given orientation, false otherwise. A ship should not be
     *         adjacent to another one either horizontally, vertically or diagonally.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Specification<Ocean> specification = new RowColumnSpecification(row, column).
                and(new ShipLengthSpecification(row, column, horizontal, getLength())).
                and(new AdjacentShipSpecification(row, column, horizontal, getLength()));
        return specification.isSatisfiedBy(ocean);
    }

    /**
     * Places the ship in the ocean.
     * 
     * @param row
     *            Row to place ship.
     * @param column
     *            Column to place ship.
     * @param horizontal
     *            Ship is horizontal.
     * @param ocean
     *            The ocean where the ship is placed.
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (okToPlaceShipAt(row, column, horizontal, ocean)) {
            bowRow = row;
            bowColumn = column;
            this.horizontal = horizontal;

            if (horizontal) {
                for (int i = column; i < (column + getLength()); i++) {
                    ocean.getShipArray()[row][i] = this;
                }
            } else {
                for (int i = row; i < (row + getLength()); i++) {
                    ocean.getShipArray()[i][column] = this;
                }
            }
        }
    }

    /**
     * @param row
     *            Row to shoot.
     * @param column
     *            Column to shoot.
     * @return true if a part of the ship occupies the given row and column and the ship hasn't been sunk, false otherwise.
     */
    public boolean shootAt(int row, int column) {
        validateRowAndColumn(row, column);

        if (isSunk()) {
            return false;
        }

        if (horizontal) {
            if (row != bowRow) {
                return false;
            }
            if ((column >= bowColumn) && (column <= ((bowColumn + getLength()) - 1))) {
                getHit()[column - bowColumn] = true;
                return true;
            }
        } else {
            if (column != bowColumn) {
                return false;
            }
            if ((row >= bowRow) && (row <= ((bowRow + getLength()) - 1))) {
                getHit()[row - bowRow] = true;
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise.
     */
    public boolean isSunk() {
        for (int i = 0; i < getHit().length; i++) {
            if (!getHit()[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the bow row.
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * Sets the bow row.
     * 
     * @param row
     *            Row to set bow.
     */
    public void setBowRow(int row) {
        bowRow = row;
    }

    /**
     * @return the bow column.
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * Sets the bow column.
     * 
     * @param column
     *            Column to set bow.
     */
    public void setBowColumn(int column) {
        bowColumn = column;
    }

    /**
     * @return horizontal.
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * Sets horizontal.
     * 
     * @param horizontal
     *            If is horizontal.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isHit(int row, int column) {
        validateRowAndColumn(row, column);

        if (horizontal) {
            if (row != bowRow) {
                return false;
            }
            if ((column >= bowColumn) && (column <= ((bowColumn + getLength()) - 1))) {
                return getHit()[column - bowColumn];
            }
        } else {
            if (column != bowColumn) {
                return false;
            }
            if ((row >= bowRow) && (row <= ((bowRow + getLength()) - 1))) {
                return getHit()[row - bowRow];
            }
        }

        return false;
    }

    private void validateRowAndColumn(int row, int column) {
        if ((row < 0) || (row >= Ocean.ROWS) || (column < 0) || (column >= Ocean.COLUMNS)) {
            throw new IllegalArgumentException(String.format("row should be less than %s and column less than %s", Ocean.ROWS, Ocean.COLUMNS));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * 
     * @return 'x' if the ship has been sunk, 'S' otherwise.
     */
    @Override
    public String toString() {
        if (isSunk()) {
            return "x";
        }
        return "S";
    }

}
