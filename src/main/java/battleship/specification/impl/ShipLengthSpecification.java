package battleship.specification.impl;

import battleship.model.ocean.Ocean;
import battleship.specification.AbstractSpecification;

public class ShipLengthSpecification extends AbstractSpecification<Ocean> {

    private int row;
    private int column;
    private boolean horizontal;
    private int length;

    public ShipLengthSpecification(int row, int column, boolean horizontal, int length) {
        this.row = row;
        this.column = column;
        this.horizontal = horizontal;
        this.length = length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.AbstractSpecification#isSatisfiedBy(java.lang.Object)
     * 
     * @return true if the ship can be placed entirely on the ocean. False otherwise.
     */
    @Override
    public boolean isSatisfiedBy(Ocean ocean) {
        if (horizontal) {
            if ((column + length) > Ocean.COLUMNS) {
                return false;
            }
        } else {
            if ((row + length) > Ocean.ROWS) {
                return false;
            }
        }

        return true;
    }

}
