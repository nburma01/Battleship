package battleship.model.ship;

public class EmptySea extends Ship {

    private static final int LENGTH = 1;

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
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

}
