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
import battleship.model.ship.PatrolBoat;
import battleship.model.ship.Submarine;

public class ShipTest {

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
    public void shipLength() {
        assertTrue((new AircraftCarrier()).getLength() == 5);
        assertTrue((new Battleship()).getLength() == 4);
        assertTrue((new Submarine()).getLength() == 3);
        assertTrue((new Destroyer()).getLength() == 2);
        assertTrue((new PatrolBoat()).getLength() == 1);
        assertTrue((new EmptySea()).getLength() == 1);
    }

    @Test
    public void getBowRowAndColumn() {
        assertTrue(battleship.getBowRow() == 1);
        assertTrue(battleship.getBowColumn() == 2);
        assertFalse(battleship.isHorizontal());
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
    }

    @Test
    public void shotAt() {
        assertFalse(battleship.isSunk());
        assertTrue(battleship.shootAt(1, 2));
        assertTrue(battleship.shootAt(2, 2));

        // multiple shots to the same locations
        assertTrue(battleship.shootAt(2, 2));

        assertTrue(battleship.shootAt(3, 2));
        assertFalse(battleship.isSunk());
        assertTrue(battleship.shootAt(4, 2));
        assertTrue(battleship.isSunk());

        // at this point battleship is sunk so shotAt should return false
        assertFalse(battleship.shootAt(2, 2));
    }

}
