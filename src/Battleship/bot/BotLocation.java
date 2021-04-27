/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.bot;

import Battleship.logic.Field;
import Battleship.logic.Cell;
import java.util.ArrayList;

public class BotLocation extends BotBase {

    public BotLocation(Bot bot) {
    	super(bot);
    }

    @Override
    public int makeShot() {
        int m, n;
         ArrayList<Cell> list = new ArrayList<Cell>();
        for(int i = 0; i < 2; i++) {
            m = x + i * 2 - 1;
            n = y;
            if (bot.getField().insideBorder(m, n) ) {
                Cell e = bot.getField().getCell(m, n);
                if (! e.getFlag() ) {
                    list.add(e);
                }
            }

            m = x;
            n = y + i * 2 - 1;
            if (bot.getField().insideBorder(m, n) ) {
                Cell e = bot.getField().getCell(m, n);
                if (! e.getFlag() ) {
                    list.add(e);
                }
            }
        }

        if (list.size() > 0) {
            Cell e = list.get(bot.rand.nextInt(list.size()));
            int shot = e.makeShot();
            if (  shot == Field.SHOT_DAMAGE ) {
                bot.action = new BotDirection(bot);
                bot.action.setPosition(x, y);
                bot.action.setDirection(e.x - x, e.y - y);
                }
            return shot;
        }
        bot.action = new BotRandom(bot);
        return bot.makeShot();
    }	
}
