package battleship.model.ship;

public class EmptySea extends Ship {

    private static final int LENGTH = 1;

    private boolean[] hit;

    public EmptySea() {
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
        return "Empty Sea";
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#shootAt()
     */
    @Override
    public boolean shootAt(final int row, final int column) {
        hit[0] = true;
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#isSunk()
     */
    @Override
    public boolean isSunk() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.model.ship.Ship#isHit()
     */
    @Override
    public boolean isHit(int row, int column) {
        return hit[0];
    }

    @Override
    public String toString() {
        if (hit[0]) {
            return "-";
        } else {
            return ".";
        }
    }

}
