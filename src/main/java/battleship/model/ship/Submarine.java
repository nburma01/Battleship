package battleship.model.ship;

public class Submarine extends Ship {

    private static final int LENGTH = 3;

    private boolean[] hit;

    public Submarine() {
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
        return "Submarine";
    }

}
