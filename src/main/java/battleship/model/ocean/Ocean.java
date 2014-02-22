package battleship.model.ocean;

import battleship.model.ship.EmptySea;
import battleship.model.ship.Ship;

public class Ocean {
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;

    private Ship ships[][];
    private int shotsFired;
    private int hitsCount;
    private int shipsSunk;

    /**
     * Creates an empty ocean
     */
    public Ocean() {
        ships = new Ship[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                ships[i][j] = new EmptySea();
            }
        }
        shotsFired = 0;
        hitsCount = 0;
        shipsSunk = 0;
    }

    /**
     * Place all the ships randomly on the (initially empty) ocean.
     */
    public void placeAllShipsRandomly() {
        if (!isOceanEmpty()) {
            throw new IllegalStateException("Cannot place ships on a non empty ocean");
        }

        //TODO place ships
    }

    /**
     * @param row
     * @param column
     * @return true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        return !(ships[row][column] instanceof EmptySea);
    }

    /**
     * @param row
     * @param column
     * @return true if the given location contains a real ship and is still afloat, false if it does not.
     */
    public boolean shotAt(int row, int column) {
        if ((row < 0) || (row >= ROWS) || (column < 0) || (column >= COLUMNS)) {
            throw new IllegalArgumentException(String.format("row should be less than %s and column less than %s", ROWS, COLUMNS));
        }

        shotsFired++;

        // this takes care also of EmptySea
        if (ships[row][column].shootAt(row, column)) {
            hitsCount++;
            return true;
        }

        return false;
    }

    /**
     * @return the number of shots fired in this game.
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * @return the number of hits recorded in this game.
     */
    public int getHitCount() {
        return hitsCount;
    }

    /**
     * @return the number of ships sunk in this game.
     */
    public int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * @return true if all the ships have been sunk, otherwise false.
     */
    public boolean isGameOver() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!ships[i][j].isSunk()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return the grid of ships.
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    private boolean isOceanEmpty() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!(ships[i][j] instanceof EmptySea)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * 
     * @return a string representation of the ocean:
     * 
     * 'S' - location that have been fired upon and hit a real ship. '-' - location that have been fired upon and did not hit a ship. 'x' - location that
     * contains a sunken ship. '.' - location that have never been fired upon.
     */
    @Override
    public String toString() {
        //TODO
        return "";
    }

}
