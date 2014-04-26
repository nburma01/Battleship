package battleship.model.ship;

public class AircraftCarrier extends Ship {

    private static final int LENGTH = 5;

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Aircraft Carrier";
    }

}
