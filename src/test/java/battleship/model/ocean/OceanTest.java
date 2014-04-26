package battleship.model.ocean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import battleship.model.ship.AircraftCarrier;
import battleship.model.ship.Battleship;
import battleship.model.ship.Destroyer;
import battleship.model.ship.EmptySea;
import battleship.model.ship.Ship;
import battleship.model.ship.Submarine;

public class OceanTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Ocean ocean;
    private Battleship battleship;
    private Submarine submarine;
    private Destroyer destroyer;

    @Before
    public void setup() {
        //build the ocean hardcoding 3 ships
        ocean = new Ocean();

        battleship = new Battleship();
        battleship.placeShipAt(1, 2, false, ocean);

        submarine = new Submarine();
        submarine.placeShipAt(6, 2, true, ocean);

        destroyer = new Destroyer();
        destroyer.placeShipAt(2, 6, true, ocean);
    }

    @Test
    public void okToPlaceShipAt() {
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        assertFalse(aircraftCarrier.okToPlaceShipAt(-1, 0, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(1, 20, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(1, 0, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(2, 3, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(5, 0, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(0, 6, true, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(2, 3, false, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(1, 3, false, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(1, 4, false, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(6, 9, false, ocean));
        assertFalse(aircraftCarrier.okToPlaceShipAt(7, 9, false, ocean));

        assertTrue(aircraftCarrier.okToPlaceShipAt(0, 5, true, ocean));
        assertTrue(aircraftCarrier.okToPlaceShipAt(0, 4, false, ocean));
        assertTrue(aircraftCarrier.okToPlaceShipAt(5, 9, false, ocean));

        assertFalse(aircraftCarrier.okToPlaceShipAt(5, 9, true, ocean));

        // Display the ocean.
        //        System.out.println(ocean.toString());
    }

    @Test
    public void isOccupied() {
        assertTrue(ocean.isOccupied(1, 2));
        assertTrue(ocean.isOccupied(2, 2));
        assertTrue(ocean.isOccupied(3, 2));
        assertTrue(ocean.isOccupied(4, 2));
        assertTrue(ocean.isOccupied(2, 6));
        assertTrue(ocean.isOccupied(2, 7));

        assertFalse(ocean.isOccupied(2, 8));
        assertFalse(ocean.isOccupied(2, 9));

        exception.expect(IllegalArgumentException.class);
        ocean.isOccupied(2, 10);
    }

    @Test
    public void shotAt() {
        assertTrue(ocean.shotAt(1, 2));
        assertTrue(ocean.shotAt(2, 2));

        // multiple shots to the same locations
        assertTrue(ocean.shotAt(2, 2));

        assertTrue(ocean.shotAt(3, 2));
        assertTrue(ocean.shotAt(4, 2));

        // at this point the ship is sunk so shotAt should return false
        assertFalse(ocean.shotAt(1, 2));

        exception.expect(IllegalArgumentException.class);
        ocean.shotAt(-1, 3);
    }

    @Test
    public void getShotsFired() {
        assertTrue(ocean.getShotsFired() == 0);

        ocean.shotAt(1, 2);
        ocean.shotAt(1, 2);
        ocean.shotAt(2, 2);

        assertTrue(ocean.getShotsFired() == 3);

        //shot at a sunk ship
        ocean.shotAt(2, 2);

        assertTrue(ocean.getShotsFired() == 4);
    }

    @Test
    public void getHitCount() {
        assertTrue(ocean.getHitCount() == 0);

        // hitting the Battleship
        ocean.shotAt(1, 2);
        assertTrue(ocean.getHitCount() == 1);

        // if the ship is not sunk, hits on the same location should increase the count
        ocean.shotAt(2, 2);
        assertTrue(ocean.getHitCount() == 2);

        ocean.shotAt(3, 2);
        ocean.shotAt(4, 2);
        assertTrue(ocean.getHitCount() == 4);

        // if the ship is sunk, hits should not increase the count
        ocean.shotAt(1, 2);
        assertTrue(ocean.getHitCount() == 4);

        // shot into an EmptySea should not increase the hit count
        ocean.shotAt(2, 3);
        assertTrue(ocean.getHitCount() == 4);
    }

    @Test
    public void getShipsSunk() {
        assertTrue(ocean.getShipsSunk() == 0);

        // sink the Battleship
        ocean.shotAt(1, 2);
        ocean.shotAt(2, 2);
        assertTrue(ocean.getShipsSunk() == 0);
        ocean.shotAt(3, 2);
        ocean.shotAt(4, 2);

        assertTrue(ocean.getShipsSunk() == 1);

        // sink the Submarine
        sinkShip(submarine);
        assertTrue(ocean.getShipsSunk() == 2);

        // sink the Destroyer
        sinkShip(destroyer);
        assertTrue(ocean.getShipsSunk() == 3);
    }

    @Test
    public void isGameOver() {
        assertFalse(ocean.isGameOver());

        sinkShip(battleship);
        assertFalse(ocean.isGameOver());

        sinkShip(submarine);
        assertFalse(ocean.isGameOver());

        sinkShip(destroyer);
        assertTrue(ocean.isGameOver());
    }

    @Test
    public void getShipArray() {
        Ship[][] ships = ocean.getShipArray();

        assertTrue(ships[0][2] instanceof EmptySea);
        assertTrue(ships[1][2] instanceof Battleship);
        assertTrue(ships[2][2] instanceof Battleship);
        assertTrue(ships[3][2] instanceof Battleship);
        assertTrue(ships[4][2] instanceof Battleship);
        assertTrue(ships[5][2] instanceof EmptySea);

        assertTrue(ships[6][1] instanceof EmptySea);
        assertTrue(ships[6][2] instanceof Submarine);
        assertTrue(ships[6][3] instanceof Submarine);
        assertTrue(ships[6][4] instanceof Submarine);
        assertTrue(ships[6][5] instanceof EmptySea);

        assertTrue(ships[2][5] instanceof EmptySea);
        assertTrue(ships[2][6] instanceof Destroyer);
        assertTrue(ships[2][7] instanceof Destroyer);
        assertTrue(ships[2][8] instanceof EmptySea);
    }

    private void sinkShip(Ship ship) {
        if (!(ship instanceof EmptySea)) {
            if (ship.isHorizontal()) {
                for (int column = ship.getBowColumn(); column < (ship.getBowColumn() + ship.getLength()); column++) {
                    ocean.shotAt(ship.getBowRow(), column);
                }
            } else {
                for (int row = ship.getBowRow(); row < (ship.getBowRow() + ship.getLength()); row++) {
                    ocean.shotAt(row, ship.getBowColumn());
                }
            }
        }
    }

}
