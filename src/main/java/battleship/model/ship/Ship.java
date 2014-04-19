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
    private boolean[] hit;

    //TODO confirm this can be done as part of the requirements
    public Ship(int lenght) {
        hit = new boolean[lenght];
    }

    /**
     * @return the length of this ship.
     */
    public abstract int getLength();

    /**
     * @return the type of this ship.
     */
    public abstract String getShipType();

    /**
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
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
     * @param column
     * @param horizontal
     * @param ocean
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
     * @param column
     * @return true if a part of the ship occupies the given row and column and the ship hasn't been sunk, false otherwise.
     */
    public boolean shootAt(int row, int column) {
        if (isSunk()) {
            return false;
        }

        if (horizontal) {
            if (row != bowRow) {
                return false;
            }
            if ((column >= bowColumn) && (column <= ((bowColumn + getLength()) - 1))) {
                hit[column - bowColumn] = true;
                return true;
            }
        } else {
            if (column != bowColumn) {
                return false;
            }
            if ((row >= bowRow) && (row <= ((bowRow + getLength()) - 1))) {
                hit[row - bowRow] = true;
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise.
     */
    public boolean isSunk() {
        for (int i = 0; i < hit.length; i++) {
            if (!hit[i]) {
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
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
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
