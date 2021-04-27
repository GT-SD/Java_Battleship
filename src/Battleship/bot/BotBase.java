/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.bot;

abstract public class BotBase {
    protected Bot bot;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
	
    public BotBase(Bot bot) {
        this.bot = bot;
    }

    abstract int makeShot();
	
    protected void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

	protected void setDirection(int x, int y) {
            this.dx = x;
            this.dy = y;
	}

}
