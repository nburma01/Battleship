package battleship.model.ship;

public class Destroyer extends Ship {

    private static final int LENGTH = 2;

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }

}
