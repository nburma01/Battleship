package battleship.model.ship;

public class Submarine extends Ship {

    private static final int LENGTH = 3;

    private boolean[] hit;

    public Submarine() {
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
        return "Submarine";
    }

}
