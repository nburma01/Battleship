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

    @Override
    public boolean isSatisfiedBy(Ocean ocean) {
        if ((row < 0) || (row > Ocean.ROWS) || (column < 0) || (column > Ocean.COLUMNS)) {
            return false;
        }

        return true;
    }

}
