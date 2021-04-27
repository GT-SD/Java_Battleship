/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.bot;

import Battleship.logic.Cell;
import java.util.ArrayList;


public class BotDirection extends BotBase {
	
    public BotDirection(Bot bot) {
        super(bot);
    }
	
    public void draw(ArrayList<Cell> list, int i, int j) {
        int m = x;
        int n = y;
        do {
            m+= i;
            n+= j;
        } while ( bot.getField().insideBorder(m, n) && (bot.getField().getCell(m, n).getStatus() == Cell.CELL_DAMAGE) );
		
        if (bot.getField().insideBorder(m, n) ) {
            Cell e = bot.getField().getCell(m, n);
            if (! e.getFlag() ) {
                list.add(e);
            }
        }
    }

    @Override
    public int makeShot() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        draw(list, dx, dy);
        draw(list, -dx, -dy);

        if (list.size() > 0) {
            return list.get(bot.rand.nextInt(list.size())).makeShot();
        }

        bot.action = new BotRandom(bot);
        return bot.makeShot();
    }
}
