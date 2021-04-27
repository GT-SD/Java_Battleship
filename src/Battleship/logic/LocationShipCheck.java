package Battleship.logic;

public class LocationShipCheck extends LocationShip {

    public LocationShipCheck(Ship ship) {
        super(ship);
    }

    @Override
    public boolean setShip(int x, int y) {
        if ( getField().insideBorder(x, y) ) { 
            return ( getField().getCell(x, y).getStatus() == Cell.CELL_WATER );
        } else {
            return false;
        }			
    }

    @Override
    public boolean setBorder(int x, int y) {
        return true;
    }
}
