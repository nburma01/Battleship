package battleship.model.ship;

public class Battleship extends Ship {

    private static final int LENGTH = 4;

    private boolean[] hit;

    public Battleship() {
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
        return "Battleship";
    }

}
