package Battleship.logic;

public class LocationShipSet extends LocationShip {
	
    private Ship ship;
	
    public LocationShipSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean setShip(int x, int y) {
        getField().getCell(x, y).setStatus(Cell.CELL_DECK);
        ship.getListCells().add(getField().getCell(x, y));
        getField().getCell(x, y).setShip(ship);
        return true;
    }
	
    @Override
    public boolean setBorder(int x, int y) {
        if ( getField().insideBorder(x, y) ) { 
            getField().getCell(x, y).setStatus(Cell.CELL_BORDER);
            ship.getListBorders().add(getField().getCell(x, y));
        }
        return true;
    }
}
