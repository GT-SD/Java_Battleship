/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.bot;

import Battleship.logic.Field;
import Battleship.logic.Cell;
import java.util.ArrayList;


public class BotRandom extends BotBase {
	
    public BotRandom(Bot bot) {
        super(bot);
    }

    public int makeShot() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int j = 0; j < bot.getField().getWidth(); j++) {
            for (int i = 0; i < bot.getField().getHeight(); i++) {
                Cell e = bot.getField().getCell(i, j);
                if (! e.getFlag() ) { 
                    list.add(e);
                }
            }
        }
        if (list.size() == 0) {
            return Field.SHOT_MISS;
        }
        Cell cell = list.get(bot.rand.nextInt(list.size()));
        int shot = cell.makeShot();
        if (shot == Field.SHOT_DAMAGE) {
            bot.action = new BotLocation(bot);
            bot.action.setPosition(cell.x, cell.y);
        }
        return shot;
    }
}
