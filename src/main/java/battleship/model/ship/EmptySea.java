package battleship.model.ship;

public class EmptySea extends Ship {

    private static final int LENGTH = 1;

    private boolean[] hit;

    public EmptySea() {
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
        return "Empty Sea";
    }

    @Override
    public boolean shootAt(final int row, final int column) {
        hit[0] = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

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
