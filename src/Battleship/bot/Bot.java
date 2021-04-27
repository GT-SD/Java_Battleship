/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.bot;

import Battleship.logic.Field;
import java.util.Random;


public class Bot {
    public Field field;
    public BotBase action;
    public Random rand;
	
    public Bot(Field field) {
        this.rand = new Random();
        this.field = field;
        this.action = new BotRandom(this);
    }

    public int makeShot() {
        return action.makeShot();
    }

    public Field getField() {
        return field;
    }
}
