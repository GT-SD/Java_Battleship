/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;


import Battleship.logic.Field;
import Battleship.logic.Cell;
import java.awt.Color;


public class GameFieldEnemy extends GameField {

    public GameFieldEnemy(Field field) {
            super(field);
    }

    @Override
    protected Color getColorByStateElement(int state) {
        switch (state) {
        case Cell.CELL_BORDER:
            return new Color(179, 225, 255);
        case Cell.CELL_WATER:
            return new Color(179, 225, 255);
        case Cell.CELL_DECK:
            return new Color(179, 225, 255);
        case Cell.CELL_DAMAGE:
             return new Color(206, 32, 32);
        case Cell.CELL_KILL:
            return Color.gray;
        case Cell.CELL_MISS:
                return Color.black;
        }		
        return Color.blue;		
    }   
}
