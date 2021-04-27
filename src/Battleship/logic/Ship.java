package Battleship.logic;

import java.util.ArrayList;
import java.util.Random;

public class Ship {
    public final static int SHIP_DECK = 1;
    public final static int SHIP_DAMAGE = 2;
    public final static int SHIP_KILL = 3;

    public int x;
    public int y;
    public int dx;
    public int dy;
    private int size;
    private int health;
    private int state;
    private Field field;
    private ArrayList<Cell> listCells;
    private ArrayList<Cell> listBorders;
	
    public ArrayList<Cell> getListCells() {
        return listCells;
    }

    public ArrayList<Cell> getListBorders() {
        return listBorders;
    }

    public Ship(Field field, int size) {
        this.size = size;
        this.health = size;
        this.field = field;
        this.state = Ship.SHIP_DECK;
            
        do {
            this.getLocation();
        } while (! checkLocation() );

        this.listCells = new ArrayList<Cell>();
        this.listBorders = new ArrayList<Cell>();
        this.setShip();
		
        getField().setNumLiveShips(getField().getNumLiveShips() + 1);
    }

    private void getLocation() {
        Random rand = new Random();
        this.x = rand.nextInt(getField().getWidth());
        this.y = rand.nextInt(getField().getHeight());
        this.dx = 0;
        this.dy = 0;
        if (rand.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
    }
	
    //Обход корабля и его окружения
    private boolean bypass(LocationShip loc) {
        int i, m, n;
		
        for(i = 0; i < size; i++) {
            //корабль
            m = y + i * dy;
            n = x + i * dx;
            if (! loc.setShip(m, n) ) {
                return false;
            }
			
            //площадка сверху и снизу корабля
            m = y + i * dy - dx;
            n = x + i * dx - dy;
            if (! loc.setBorder(m, n) ) {
                return false;
            }
            m = y + i * dy + dx;
            n = x + i * dx + dy;
            if (! loc.setBorder(m, n) ) {
                return false;
            }
        }
        //площадка слева и справа корабля
        for(i = -1; i < 2; i++) {
            m = y + i * dx - dy;
            n = x + i * dy - dx;
            if (! loc.setBorder(m, n) ) {
                return false;
            }
            m = y + i * dx + (dy * size);
            n = x + i * dy + (dx * size);
            if (! loc.setBorder(m, n) ) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLocation() {
        return bypass(new LocationShipCheck(this));
    }

    private void setShip() {
        bypass(new LocationShipSet(this));
    }

    public int makeShot() {
        if (health != 0) {
            health--;
            if (health == 0) {
                getField().setNumLiveShips(getField().getNumLiveShips() - 1);
                state = Ship.SHIP_KILL;
                for(Cell e : listCells) {
                    e.setStatus(Cell.CELL_KILL);
                }
                for(Cell e : listBorders) {
                    e.setStatus(Cell.CELL_MISS);
                    e.setFlag(true);
                }
                return Field.SHOT_KILL;
            } else {
                state = SHIP_DAMAGE;
            }
        }
        return Field.SHOT_DAMAGE;
    }
	
    public int getSize() {
        return size;
    }

    public int getState() {
        return state;
    }

    public Field getField() {
        return field;
    }
}
