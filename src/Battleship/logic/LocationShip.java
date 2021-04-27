package Battleship.logic;

public abstract class LocationShip {

    public Field field;
	
    public LocationShip(Ship ship) {
        this.field = ship.getField();
    }
	
    protected Field getField() {
        return field;
    }
	
    abstract public boolean setShip(int x, int y);
    abstract public boolean setBorder(int x, int y);
}
