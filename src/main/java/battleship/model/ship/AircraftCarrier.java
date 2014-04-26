package battleship.model.ship;

public class AircraftCarrier extends Ship {

    private static final int LENGTH = 5;

    private boolean[] hit;

    public AircraftCarrier() {
        hit = new boolean[LENGTH];
    }

    @Override
    protected boolean[] getHit() {
        return hit;
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Aircraft Carrier";
    }

}
