package battleship.model.ship;

public class Battleship extends Ship {

    private static final int LENGTH = 4;

    private boolean[] hit;

    public Battleship() {
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
        return "Battleship";
    }

}
