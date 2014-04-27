package battleship.specification.impl;

import battleship.model.ocean.Ocean;
import battleship.specification.AbstractSpecification;

public class RowColumnSpecification extends AbstractSpecification<Ocean> {

    private int row;
    private int column;

    public RowColumnSpecification(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.AbstractSpecification#isSatisfiedBy(java.lang.Object)
     * 
     * @return true if row and column are valid inputs for the Ocean. False otherwise.
     */
    @Override
    public boolean isSatisfiedBy(Ocean ocean) {
        if ((row < 0) || (row > Ocean.ROWS) || (column < 0) || (column > Ocean.COLUMNS)) {
            return false;
        }

        return true;
    }

}
