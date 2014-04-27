package battleship.model.ship;

public class PatrolBoat extends Ship {

    private static final int LENGTH = 1;

    private boolean[] hit;

    public PatrolBoat() {
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
        return "Patrol Boat";
    }

}
