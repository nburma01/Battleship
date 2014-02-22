package battleship.model.ship;

public class PatrolBoat extends Ship {

    private static final int LENGTH = 1;

    public PatrolBoat() {
        super(LENGTH);
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public String getShipType() {
        return "Patrol Boat";
    }

}
