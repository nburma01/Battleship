package battleship.model.ship;

public class Destroyer extends Ship {

    private static final int LENGTH = 2;

    public Destroyer() {
        super(LENGTH);
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }

}
