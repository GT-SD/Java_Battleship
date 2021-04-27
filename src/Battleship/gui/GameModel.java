package Battleship.gui;

import Battleship.logic.Field;
import Battleship.logic.BdController;
import Battleship.logic.Ship;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

import Battleship.bot.Bot;

public class GameModel {
    private ArrayList<GameField> list = new ArrayList<GameField>();
    public Field playerFieldPlayer;
    public Field playerFieldEnemy;
    public Bot bot;
    public int flagPlayer;
    public BdController bd;
    private boolean accessShot;
    private boolean accessResult = true;

    public GameModel(int dx, int dy, int numDeck, BdController bd) {
        playerFieldPlayer = new Field(dx, dy, numDeck);
        playerFieldEnemy = new Field(dx, dy, numDeck);
        this.bd = bd;
        bot = new Bot(playerFieldPlayer);
        setDimension(dx, dy, numDeck);
    }

    public void setDimension(int dx, int dy, int numDeck) {
        playerFieldEnemy.setWidth(dx);
        playerFieldEnemy.setHeight(dy);
        playerFieldEnemy.setMaxSizeShip(numDeck);

        playerFieldPlayer.setWidth(dx);
        playerFieldPlayer.setHeight(dy);
        playerFieldPlayer.setMaxSizeShip(numDeck);
        accessShot = true;
        newGame();
        update();
    }
	

    //Реогранизация караблей 
    public void newGame() {
        playerFieldPlayer.setShip();
        playerFieldEnemy.setShip();
        accessShot = true;
        flagPlayer = 0;
        update();
    }
    
    public void getResult(){
        if (!accessResult) {
            return;
        }
        int count = 0;
        int point = 0;      
        int numShip = playerFieldEnemy.getMaxSizeShip();
        int[] m = new int[numShip];
        if (playerFieldPlayer.getNumLiveShips() == 0){
            for(Ship ship : playerFieldEnemy.getShips()) {
		if (ship.getState() != Ship.SHIP_KILL) {
                    m[ship.getSize() - 1] ++;
                    count ++;
		}
            }
            for(int i = 0; i < numShip; i++){
                point+=m[i]*(i+1);
            }
            bd.insertHistory("Компьютер", count, point);
            JOptionPane.showMessageDialog(null, "Вы проиграли!");
            accessResult = false;
        }
        if (playerFieldEnemy.getNumLiveShips() == 0){
            for(Ship ship : playerFieldPlayer.getShips()) {
		if (ship.getState() != Ship.SHIP_KILL) {
                    m[ship.getSize() - 1] ++;
                    count ++;
		}
            }
            for(int i = 0; i < numShip; i++){
                point+=m[i]*(i+1);
            }
            bd.insertHistory("Игрок", count, point);
            JOptionPane.showMessageDialog(null, "Вы выиграли!");
            accessResult = false;
        }
    }


    //Выстрел по текущему игроку
    public void makeShotByPlayer(int x, int y) {
        if (!accessShot) {
            return;
        }
        //если ходит локальный игрок
        if (flagPlayer == 0) {
            if (playerFieldEnemy.getCell(x, y).getFlag()) {
                return;
            }
            if (playerFieldEnemy.makeShot(x, y) == Field.SHOT_MISS) {
                //если промахнулись
                flagPlayer = 1;
            }
        }
        //если ходит противник
        if (flagPlayer ==1) {
            while (bot.makeShot() != Field.SHOT_MISS);
            flagPlayer = 0;
        }
        update();
        //если у одного из игроков больше нет живых кораблей
        if ( (playerFieldPlayer.getNumLiveShips() == 0) || (playerFieldEnemy.getNumLiveShips() == 0) ) {
            accessShot = false;
        }
    }
    
    public BdController getBdController() {
        return bd;
    }
	
    public void signOn(GameField f) {
        list.add(f);
        f.repaint();
    }	
	
    private void update() {
        Iterator<GameField> i = list.iterator();
        while(i.hasNext()) {
            GameField f = (GameField)i.next();
            f.repaint();
        }
    }
}

