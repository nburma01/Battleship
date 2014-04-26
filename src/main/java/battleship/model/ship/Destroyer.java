package battleship.model.ship;

public class Destroyer extends Ship {

    private static final int LENGTH = 2;

    private boolean[] hit;

    public Destroyer() {
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
        return "Destroyer";
    }

}
