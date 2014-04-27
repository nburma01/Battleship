package battleship.model.ocean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import battleship.BattleshipGame;
import battleship.model.ship.EmptySea;
import battleship.model.ship.Ship;
import battleship.model.ship.ShipFactory;

public class Ocean {
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;

    private Ship[][] ships;
    private int shotsFired;
    private int hitsCount;
    private int shipsSunk;

    private List<Ship> fleet;

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
        fleet = new ArrayList<>();

        Properties properties = new Properties();
        try {
            InputStream inputStream = BattleshipGame.class.getResourceAsStream("/fleet.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Entry<Object, Object> entry : properties.entrySet()) {
            int times = Integer.valueOf((String)entry.getValue());
            if (times > 0) {
                for (int i = 0; i < times; i++) {
                    fleet.add(ShipFactory.getShip((String)entry.getKey()));
                }
            }
        }

        Collections.sort(fleet);
    }

    /**
     * Place all the ships randomly on the (initially empty) ocean.
     */
    public void placeAllShipsRandomly() {
        if (!isOceanEmpty()) {
            throw new IllegalStateException("Cannot place ships on a non empty ocean");
        }

        Random random = new Random();
        int row;
        int column;
        boolean isPlaced;

        for (Ship ship : fleet) {
            isPlaced = false;

            while (!isPlaced) {
                row = random.nextInt(ROWS);
                column = random.nextInt(COLUMNS);

                if (ship.okToPlaceShipAt(row, column, true, this)) {
                    ship.placeShipAt(row, column, true, this);
                    isPlaced = true;
                } else if (ship.okToPlaceShipAt(row, column, false, this)) {
                    ship.placeShipAt(row, column, false, this);
                    isPlaced = true;
                }
            }
        }
    }

    /**
     * @param row
     *            Row to check.
     * @param column
     *            Column to check.
     * @return true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        validateRowAndColumn(row, column);
        return !(ships[row][column] instanceof EmptySea);
    }

    /**
     * @param row
     *            Row to shoot at.
     * @param column
     *            Column to shoot at.
     * @return true if the given location contains a real ship and is still afloat, false if it does not.
     */
    public boolean shotAt(int row, int column) {
        validateRowAndColumn(row, column);

        shotsFired++;

        // this takes care also of EmptySea
        if (ships[row][column].shootAt(row, column)) {
            hitsCount++;
            if (ships[row][column].isSunk()) {
                shipsSunk++;
            }
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
                if (!(ships[i][j] instanceof EmptySea) && !ships[i][j].isSunk()) {
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

    /**
     * Check if ocean is empty.
     * 
     * @return True if ocean is empty.
     */
    public boolean isOceanEmpty() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!(ships[i][j] instanceof EmptySea)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void validateRowAndColumn(int row, int column) {
        if ((row < 0) || (row >= ROWS) || (column < 0) || (column >= COLUMNS)) {
            throw new IllegalArgumentException(String.format("row should be less than %s and column less than %s", ROWS, COLUMNS));
        }
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
        StringBuilder buffer = new StringBuilder();
        buffer.append("  ");
        for (int j = 0; j < COLUMNS; j++) {
            buffer.append(j + " ");
        }
        buffer.append("\n");

        for (int i = 0; i < ROWS; i++) {
            buffer.append(i + " ");
            for (int j = 0; j < COLUMNS; j++) {
                if (ships[i][j].isHit(i, j)) {
                    buffer.append(ships[i][j].toString());
                } else {
                    buffer.append(".");
                }
                buffer.append(" ");
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }

}