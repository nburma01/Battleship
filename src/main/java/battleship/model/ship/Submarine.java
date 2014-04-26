package battleship.model.ship;

public class Submarine extends Ship {

    private static final int LENGTH = 3;

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }

}
