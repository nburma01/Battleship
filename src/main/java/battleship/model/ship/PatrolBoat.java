package battleship.model.ship;

public class PatrolBoat extends Ship {

    private static final int LENGTH = 1;

    private boolean[] hit;

    public PatrolBoat() {
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
        return "Patrol Boat";
    }

}
