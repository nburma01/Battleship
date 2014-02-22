package battleship.model.ship;

public class Battleship extends Ship {

    private static final int LENGTH = 4;

    public Battleship() {
        super(LENGTH);
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }

}
