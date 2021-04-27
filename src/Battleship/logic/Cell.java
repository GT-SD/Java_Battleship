package Battleship.logic;

public class Cell {
    public final static int CELL_WATER = 1;
    public final static int CELL_BORDER = 2;
    public final static int CELL_DECK = 3;
    public final static int CELL_DAMAGE = 4;
    public final static int CELL_KILL = 5;
    public final static int CELL_MISS = 6;
        
    public int x;
    public int y;
    private int status;
    private Ship ship;
    private boolean flag;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = CELL_WATER;
        this.flag = false;
    }
	
    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int makeShot() {
        setFlag(true);
        if (status == CELL_DECK) {
            setStatus(CELL_DAMAGE);
            return getShip().makeShot();
        } else {
            if ( (status == CELL_BORDER) || (status == CELL_WATER)) {
            setStatus(CELL_MISS);
            }
        }
    return Field.SHOT_MISS;
    }	
}
