package battleship.model.ship;

public class AircraftCarrier extends Ship {

    private static final int LENGTH = 5;

    private boolean[] hit;

    public AircraftCarrier() {
        hit = new boolean[LENGTH];
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#getHit()
     */
    @Override
    protected boolean[] getHit() {
        return hit;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#getLength()
     */
    @Override
    public int getLength() {
        return LENGTH;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#getShipType()
     */
    @Override
    public String getShipType() {
        return "Aircraft Carrier";
    }

}
