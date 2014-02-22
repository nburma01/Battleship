package battleship.model.ocean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import battleship.model.ship.AircraftCarrier;
import battleship.model.ship.Battleship;
import battleship.model.ship.Destroyer;
import battleship.model.ship.Submarine;

public class OceanTest {

    private Ocean ocean;

    @Before
    public void setup() {
        //build the ocean hardcoding 3 ships
        ocean = new Ocean();

        Battleship battleship = new Battleship();
        ocean.getShipArray()[1][2] = battleship;
        ocean.getShipArray()[2][2] = battleship;
        ocean.getShipArray()[3][2] = battleship;
        ocean.getShipArray()[4][2] = battleship;

        Submarine submarine = new Submarine();
        ocean.getShipArray()[6][2] = submarine;
        ocean.getShipArray()[6][3] = submarine;
        ocean.getShipArray()[6][4] = submarine;

        Destroyer destroyer = new Destroyer();
        ocean.getShipArray()[2][6] = destroyer;
        ocean.getShipArray()[2][7] = destroyer;
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
    }

}
