package battleship.specification.impl;

import battleship.model.ocean.Ocean;
import battleship.specification.AbstractSpecification;

public class AdjacentShipSpecification extends AbstractSpecification<Ocean> {

    private int row;
    private int column;
    private boolean horizontal;
    private int length;

    public AdjacentShipSpecification(int row, int column, boolean horizontal, int length) {
        this.row = row;
        this.column = column;
        this.horizontal = horizontal;
        this.length = length;
    }

    @Override
    public boolean isSatisfiedBy(Ocean ocean) {
        if (horizontal) {
            for (int i = row - 1; i <= (row + 1); i++) {
                for (int j = column - 1; j <= (column + length); j++) {
                    if (checkOceanContainShip(i, j, ocean)) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = row - 1; i <= (row + length); i++) {
                for (int j = column - 1; j <= (column + 1); j++) {
                    if (checkOceanContainShip(i, j, ocean)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkOceanContainShip(int row, int column, Ocean ocean) {
        if ((row >= 0) && (row < Ocean.ROWS) && (column >= 0) && (column < Ocean.COLUMNS) && ocean.isOccupied(row, column)) {
            return true;
        }

        return false;
    }

}
