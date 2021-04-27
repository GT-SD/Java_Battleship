package Battleship.logic;

import java.util.ArrayList;

public class Field {
    public final static int SHOT_MISS = 1; 
    public final static int SHOT_DAMAGE = 2; 
    public final static int SHOT_KILL = 3; 

    private Cell[][] cells;
    private ArrayList<Ship> ships;
    private int width;
    private int height;
    private int maxSizeShip;
    private int numLiveShips;

    public Field(int x, int y, int deck) {
        setDimention(x, y, deck);
        setShip();
    }

    public void setDimention(int x, int y, int deck) {
        setWidth(x);
        setHeight(y);
        setMaxSizeShip(deck);
    }

    public void setShip() {
        setNumLiveShips(0);
        // заполняем поле элементами воды
        cells = new Cell[getWidth()][getHeight()]; 
        for(int j = 0; j < getHeight(); j++) {
            for(int i = 0; i < getWidth(); i++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        // заполняем поле короблями
        ships = new ArrayList<Ship>(); 
        for(int i = getMaxSizeShip(); i > 0; i--) {
            for(int j = (getMaxSizeShip() - i +1 ); j > 0; j--) {
                Ship ship=new Ship(this,i);
                ships.add(ship);
            }
        }
        // удаляем окружение коробля
        for(int j = 0; j < getHeight(); j++) {
            for(int i = 0; i < getWidth(); i++) {
                Cell cell = cells[i][j];
                if (cell.getStatus() == Cell.CELL_BORDER) {
                    cell.setStatus(Cell.CELL_WATER);
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
	
    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxSizeShip() {
        return maxSizeShip;
    }

    public void setMaxSizeShip(int maxSizeShip) {
        this.maxSizeShip = maxSizeShip;
    }

    public int getNumLiveShips() {
        return numLiveShips;
    }

    public void setNumLiveShips(int numLiveShips) {
        this.numLiveShips = numLiveShips;
    }
        
    public int makeShot(int x, int y) {
        int shot = getCell(x, y).makeShot();
        return shot;
    }
	
    public boolean insideBorder(int x, int y) {
        return !( (x < 0) || (x > (getWidth() - 1)) || (y < 0) || (y > (getHeight() - 1) ) );
    }
}
